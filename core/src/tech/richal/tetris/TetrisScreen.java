package tech.richal.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import tech.richal.tetris.board.Board;
import tech.richal.tetris.board.BoardUpdateResult;
import tech.richal.tetris.input.InputServer;
import tech.richal.tetris.input.InputServerCommand;
import tech.richal.tetris.input.InputServerListener;

public class TetrisScreen extends ScreenAdapter implements InputServerListener {
    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 24;

    private Board tetrisBoard;
    private TetrisView view;

    private Tetris game;
    private float totalDelta;

    public TetrisScreen(Tetris game) {
        this.game = game;
        this.totalDelta = 0.0f;
    }

    @Override
    public void show () {
        tetrisBoard = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        view = new TetrisView();

        // Create a new thread for the input server using the onCommandReceived
        // implemented in this class.
        new Thread(new InputServer(this)).start();
        // Setup the keyboard as a client for the InputServer
        // A client is used for the keyboard in order to not clutter this class.
        Gdx.input.setInputProcessor(new KeyboardController());
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0.6f, 0.6f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.totalDelta += delta;
        // Move down each second
        if (this.totalDelta >= 1.0f) {
            tetrisBoard.update(0, -1, false);
            this.totalDelta = 0;
        }

        this.view.draw(this.game.batch, tetrisBoard.display());
    }

    @Override
    public void dispose () {}

    @Override
    public void onCommandReceived(InputServerCommand command) {
        BoardUpdateResult commandResult = BoardUpdateResult.SUCCESS;
        switch (command) {
            case START:
                System.out.println("START");
                break;
            case EXIT:
                this.onCommandResult(BoardUpdateResult.GAME_OVER);
                break;
            case RESTART:
                System.out.println("START");
                break;
            case LEFT:
                commandResult = this.tetrisBoard.update(-1, 0, false);
                break;
            case RIGHT:
                commandResult = this.tetrisBoard.update(1, 0, false);
                break;
            case DOWN:
                commandResult = this.tetrisBoard.update(0, -1, false);
                break;
            case ROTATE:
                commandResult = this.tetrisBoard.update(0, 0, true);
                break;
            default:
                commandResult = this.tetrisBoard.update(0, 0, false);
                break;
        }

        commandResult = BoardUpdateResult.SUCCESS;
        this.onCommandResult(commandResult);
    }

    private void onCommandResult(BoardUpdateResult commandResult) {
        if (commandResult == BoardUpdateResult.GAME_OVER) {
            System.out.println("Game Over");
            System.exit(0);
        }
    }
}
