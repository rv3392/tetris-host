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

    private InputServer inputServer;

    private boolean gameRunningFlag = false;

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
        this.inputServer = new InputServer(this);
        new Thread(this.inputServer).start();
        // Setup the keyboard as a client for the InputServer
        // A client is used for the keyboard in order to not clutter this class.
        Gdx.input.setInputProcessor(new KeyboardController());
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (gameRunningFlag) {
            this.totalDelta += delta;
            // Move down each second
            if (this.totalDelta >= 1.0f) {
                this.updateBoard(0, -1, false);
                this.totalDelta = 0;
            }
        }

        this.view.draw(this.game.batch, tetrisBoard);
    }

    @Override
    public void dispose () {}

    @Override
    public void onCommandReceived(InputServerCommand command) {
        BoardUpdateResult commandResult = BoardUpdateResult.SUCCESS;
        switch (command) {
            case NEW:
                System.out.println("new");
                break;
            case START:
                System.out.println("start");
                this.gameRunningFlag = true;
                break;
            case EXIT:
                this.onCommandResult(BoardUpdateResult.GAME_OVER);
                System.exit(0);
                break;
            case RESET:
                System.out.println("reset");
                this.reset();
                break;
            case LEFT:
                commandResult = this.updateBoard(-1, 0, false);
                break;
            case RIGHT:
                commandResult = this.updateBoard(1, 0, false);
                break;
            case DOWN:
                commandResult = BoardUpdateResult.SUCCESS;
                while (commandResult != BoardUpdateResult.REACHED_BOTTOM) {
                    commandResult = this.updateBoard(0, -1, false);
                }
                break;
            case ROTATE:
                commandResult = this.updateBoard(0, 0, true);
                break;
            default:
                commandResult = this.updateBoard(0, 0, false);
                break;
        }

        this.onCommandResult(commandResult);
    }

    private void reset() {
        this.tetrisBoard.reset();
    }

    private void onCommandResult(BoardUpdateResult commandResult) {
        if (commandResult == BoardUpdateResult.GAME_OVER) {
            System.out.println("Game Over");
            this.gameRunningFlag = false;
            this.reset();
        }
    }

    private BoardUpdateResult updateBoard(int x, int y, boolean rotate) {
        BoardUpdateResult boardResult = this.tetrisBoard.update(x, y, rotate);
        this.inputServer.sendMessageToConnections(tetrisBoard.toString() + "\n");
        return boardResult;
    }
}
