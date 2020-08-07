package tech.richal.tetris.inputserver.task;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import tech.richal.tetris.inputserver.InputServerListener;

public class InputServerTaskQueue implements InputServerTaskListener, Iterable<InputServerTask> {
    private ArrayList<InputServerTask> queued;
    private ArrayList<InputServerTask> unqueued;
    private InputServerListener inputListener;

    public InputServerTaskQueue(InputServerListener inputListener) {
        this.queued = new ArrayList<InputServerTask>();
        this.unqueued = new ArrayList<InputServerTask>();
        this.inputListener = inputListener;
    }

    public void newTask(Socket client) {
        this.unqueued.add(new InputServerTask(client, this.inputListener, this));
        new Thread(this.unqueued.get(this.unqueued.size() - 1)).start();
        this.update();
    }
    
    @Override
    public void onTaskCompleted(InputServerTask completedTask) {
        this.queued.remove(completedTask);
        this.update();
    }

    @Override
    public void onTaskQueued(InputServerTask queuedTask) {
        this.unqueued.remove(queuedTask);
        this.queued.add(queuedTask);
        this.update();
    }

    private void update() {
        if (this.queued.size() > 0) {
            this.queued.get(0).unblockInput();
        }
    }

    @Override
    public Iterator<InputServerTask> iterator() {
        return this.queued.iterator();
    }
}