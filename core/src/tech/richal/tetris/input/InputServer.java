package tech.richal.tetris.input;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class InputServer implements Runnable {
    private static final int PORT_NUMBER = 35354;
    private InputServerListener listener;

    public InputServer(InputServerListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(PORT_NUMBER);
            while (true) {
                Socket connection = server.accept();
                new Thread(new InputServerTask(connection, this.listener)).start();
            }
        } catch (IOException e) {
            System.err.println("Unable to start Input Server!");
            e.printStackTrace();
        }
    }
}