package tech.richal.tetris.inputserver.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import tech.richal.tetris.inputserver.InputServerListener;
import tech.richal.tetris.inputserver.command.Command;
import tech.richal.tetris.inputserver.command.CommandType;

public class InputServerTask implements Runnable, InputServerListener {
    private InputServerListener inputListener;
    private InputServerTaskListener completionListener;
    private Socket client;
    private String clientName;

    private PrintWriter serverOut;
    private BufferedReader serverIn;

    private boolean isInputBlocked;
    private boolean isQueued;
    private boolean isExitFlagged;

    public InputServerTask(Socket client, InputServerListener inputListener, 
            InputServerTaskListener completionListener) {
        this.client = client;
        this.inputListener = inputListener;
        this.completionListener = completionListener;
        
        this.isInputBlocked = true;
        this.isQueued = false;
        this.isExitFlagged = false;
    }

    @Override
    public void run() {
        try {
            this.serverOut = new PrintWriter(client.getOutputStream(), true);
            this.serverIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            System.err.println("Unable to establish IO with client!");
            e.printStackTrace();
            this.isExitFlagged = true;
        }

        // Continuously read from socket and handle the input until the socket is closed.
        while (!this.isExitFlagged) {
            try {
                this.handleInput(this.serverIn.readLine());
            } catch (IOException | NullPointerException e) {
                System.err.println("Problem reading from IO client.");
                e.printStackTrace();
                this.isExitFlagged = true;
            }   
        }

        this.completionListener.onTaskCompleted(this);
        try {
            this.client.close();
        } catch (IOException e) {
            System.err.println("There was a problem closing the connection with the client.");
        }
    }

    public void sendMessageToConnection(String message) {
        this.serverOut.println(message);
    }

    public void blockInput() {
        this.isInputBlocked = true;
    }

    public void unblockInput() {
        this.isInputBlocked = false;
    }

    public Boolean isQueued() {
        return this.isQueued();
    }

    private void handleInput(String inputCommand) {
        try {
            Command parsedInputCommand = new Command(inputCommand);

            if (!this.isQueued && parsedInputCommand.getType() != CommandType.QUEUE) {
                this.serverOut.println("This agent must send \"queue <device_name>\" before " 
                        + "sending any other command"); 
                return;
            }

            if (this.isInputBlocked && this.isQueued) {
                this.serverOut.println("This device is currently queued."); 
                return;
            }

            inputListener.onCommandReceived(parsedInputCommand);
            this.onCommandReceived(parsedInputCommand);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input received!");
            System.err.println("Input should be one of: new, start, exit,"
                    + " end, left, right, down or rotate in any combination of case");

            this.serverOut.println("Invalid input received!");
            this.serverOut.println("Input should be one of: new, start, exit,"
                    + " end, left, right, down or rotate in any combination of case");
        }
    }

    @Override
    public void onCommandReceived(Command command) {
        switch(command.getType()) {
            case END:
                this.isExitFlagged = true;
                break;
            case QUEUE:
                if (!command.getMessage().equals("")) {
                    this.clientName = command.getMessage();
                    this.isQueued = true;
                    
                    this.completionListener.onTaskQueued(this);
                } else {
                    this.serverOut.println("\"queue\" must be called with a session/device name "
                            + "such as \"queue <device_name>\"");
                }
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return this.clientName;
    }
}