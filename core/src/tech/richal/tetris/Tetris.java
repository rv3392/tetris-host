package tech.richal.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tetris extends Game {
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new TetrisScreen(this));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
