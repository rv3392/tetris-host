package tech.richal.tetris.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class InputServerTask implements Runnable {
    private InputServerListener listener;
    private Socket client;

    PrintWriter serverOut;
    BufferedReader serverIn;

    public InputServerTask(Socket client, InputServerListener listener) {
        this.client = client;
        this.listener = listener;       
    }

    @Override
    public void run() {
        try {
            this.serverOut = new PrintWriter(client.getOutputStream(), true);
            this.serverIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            System.err.println("Unable to establish IO with client!");
            e.printStackTrace();
            return;
        }

        // Continuously read from socket and handle the input until the socket is closed.
        while (true) {
            try {
                this.handleInput(this.serverIn.readLine());
            } catch (IOException | NullPointerException e) {
                System.err.println("Problem reading from IO client.");
                e.printStackTrace();
                return;
            }   
        }
    }

    private void handleInput(String inputCommand) {
        try {
            listener.onCommandReceived(InputServerCommand.valueOf(inputCommand.toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input received!");
            System.err.println("Input should be one of: start, exit,"
                    + " restart, left, right, down or rotate in any combination of case");
        }
    }

    
}