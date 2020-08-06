package tech.richal.tetris.inputserver;

import tech.richal.tetris.inputserver.command.Command;

public interface InputServerListener {
    public void onCommandReceived(Command command);
}