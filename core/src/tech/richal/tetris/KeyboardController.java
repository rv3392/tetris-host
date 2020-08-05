package tech.richal.tetris;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

import tech.richal.tetris.input.InputServerCommand;

public class KeyboardController implements InputProcessor {
    private static final int PORT_NUMBER = 35354;
    private static final String HOST_NAME = "localhost";

    private PrintWriter clientOut;

    public KeyboardController() {
        try {
            Socket connection = new Socket(HOST_NAME, PORT_NUMBER);
            this.clientOut = new PrintWriter(connection.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Unable to setup connection with InputServer");
            e.printStackTrace();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.LEFT:
                this.clientOut.println(InputServerCommand.LEFT.toString());
                return true;
            case Keys.RIGHT:
                this.clientOut.println(InputServerCommand.RIGHT.toString());
                return true;
            case Keys.DOWN:
                this.clientOut.println(InputServerCommand.DOWN.toString());
                return true;
            case Keys.UP:
                this.clientOut.println(InputServerCommand.ROTATE.toString());
                return true;
            case Keys.ESCAPE:
                this.clientOut.println(InputServerCommand.EXIT.toString());
                return true;
            case Keys.N:
                this.clientOut.println(InputServerCommand.NEW.toString());
                return true;
            case Keys.S:
                this.clientOut.println(InputServerCommand.START.toString());
                return true;
            case Keys.E:
                this.clientOut.println(InputServerCommand.END.toString());
            default:
                return false;
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }    
}