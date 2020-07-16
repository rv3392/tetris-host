package tech.richal.tetris.input;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

public class InputServer implements Runnable {
    private static final int PORT_NUMBER = 35354;
    private InputServerListener listener;

    private List<InputServerTask> runningTasks;

    public InputServer(InputServerListener listener) {
        this.listener = listener;
        this.runningTasks = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(PORT_NUMBER);
            while (true) {
                Socket connection = server.accept();
                this.runningTasks.add(new InputServerTask(connection, this.listener));
                new Thread(this.runningTasks.get(this.runningTasks.size() - 1)).start();
            }
        } catch (IOException e) {
            System.err.println("Unable to start Input Server!");
            e.printStackTrace();
        }
    }

    public void sendMessageToConnections(String message) {
        for (InputServerTask runningTask : this.runningTasks) {
            runningTask.sendMessageToConnection(message);
        }
    }
}