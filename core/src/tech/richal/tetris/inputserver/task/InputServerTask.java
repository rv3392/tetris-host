package tech.richal.tetris.inputserver.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import tech.richal.tetris.inputserver.InputServerListener;
import tech.richal.tetris.inputserver.command.Command;

public class InputServerTask implements Runnable, InputServerListener {
    private InputServerListener inputListener;
    private InputServerTaskListener completionListener;
    private Socket client;

    private PrintWriter serverOut;
    private BufferedReader serverIn;

    private boolean isInputBlocked;
    private boolean isExitFlagged;

    public InputServerTask(Socket client, InputServerListener inputListener, 
            InputServerTaskListener completionListener) {
        this.client = client;
        this.inputListener = inputListener;
        this.completionListener = completionListener;
        
        this.isInputBlocked = true;
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
                String inputLine = this.serverIn.readLine();
                if (isInputBlocked) {
                    this.serverOut.println("Input is currently blocked from this device. You are "
                        + "likely waiting in the queue.");
                    continue;
                }

                this.handleInput(inputLine);
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

    private void handleInput(String inputCommand) {
        try {
            Command parsedInputCommand = new Command(inputCommand);
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
            case NEW:
                break;
            default:
                break;
        }
    }
}