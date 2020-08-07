package tech.richal.tetris.inputserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import tech.richal.tetris.inputserver.task.InputServerTaskQueue;

import java.util.ArrayList;

public class InputServer implements Runnable {
    private static final int PORT_NUMBER = 35354;
    private InputServerListener listener;

    private InputServerTaskQueue tasks;


    public InputServer(InputServerListener listener) {
        this.listener = listener;
        this.tasks = new InputServerTaskQueue(listener);
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(PORT_NUMBER);
            while (true) {
                this.tasks.newTask(server.accept());
            }
        } catch (IOException e) {
            System.err.println("Unable to start Input Server!");
            e.printStackTrace();
        }
    }

    public void sendMessageToConnections(String message) {
        this.tasks.forEach((task) -> task.sendMessageToConnection(message));
    }

    public ArrayList<String> getQueuedClientNames() {
        return this.tasks.getQueuedClientNames();
    }
}