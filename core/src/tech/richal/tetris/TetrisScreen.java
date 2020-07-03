package tech.richal.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tech.richal.tetris.board.Board;

public class TetrisScreen extends ScreenAdapter {
    Board tetrisBoard;
    TetrisView view;

    Tetris game;
    private float totalDelta;

    public TetrisScreen(Tetris game) {
        this.game = game;
        this.totalDelta = 0.0f;
    }

    @Override
    public void show () {
        tetrisBoard = new Board(10, 24);
        view = new TetrisView();
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0.6f, 0.6f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.totalDelta += delta;
        if (this.totalDelta >= 1) {
            tetrisBoard.update(0, -1, false);
            this.totalDelta = 0;
        }

        this.view.draw(this.game.batch, tetrisBoard.display());
    }

    @Override
    public void dispose () {}
}
