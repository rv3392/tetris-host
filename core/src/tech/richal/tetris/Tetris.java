package tech.richal.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tech.richal.tetris.board.Board;
import tech.richal.tetris.grid.Grid;

public class Tetris extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	Board tetrisBoard;
	TetrisController controller;
	TetrisView view;
	
	@Override
	public void create () {
		tetrisBoard = new Board(10, 24);
		controller = new TetrisController();
		view = new TetrisView();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tetrisBoard.update(0, -1, false);
		this.view.draw(batch, tetrisBoard.display());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
