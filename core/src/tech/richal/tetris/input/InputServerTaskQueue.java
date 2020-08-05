package tech.richal.tetris.input;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class InputServerTaskQueue implements InputServerTaskListener, Iterable<InputServerTask> {
    private ArrayList<InputServerTask> queue;
    private InputServerListener inputListener;

    public InputServerTaskQueue(InputServerListener inputListener) {
        this.queue = new ArrayList<InputServerTask>();
        this.inputListener = inputListener;
    }

    public void newTask(Socket client) {
        this.queue.add(new InputServerTask(client, this.inputListener, this));
        new Thread(this.queue.get(this.queue.size() - 1)).start();
        this.update();
    }

    @Override
    public void onTaskCompleted(InputServerTask completedTask) {
        this.queue.remove(completedTask);
        this.update();
    }

    private void update() {
        if (this.queue.size() > 0) {
            this.queue.get(0).unblockInput();
        }
    }

    @Override
    public Iterator<InputServerTask> iterator() {
        return this.queue.iterator();
    }
}