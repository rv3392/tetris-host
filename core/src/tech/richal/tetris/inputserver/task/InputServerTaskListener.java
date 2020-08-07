package tech.richal.tetris.inputserver.task;

public interface InputServerTaskListener {
    public void onTaskCompleted(InputServerTask completedTask);
    public void onTaskQueued(InputServerTask queuedTask);
}