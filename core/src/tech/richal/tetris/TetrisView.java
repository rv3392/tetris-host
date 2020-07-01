package tech.richal.tetris;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tech.richal.tetris.grid.Grid;
import tech.richal.tetris.tetromino.Colour;

public class TetrisView {
    public void draw(SpriteBatch batch, Grid tetrisBoardGrid) {
        batch.begin();
        batch.end();
    }

    private Texture colourToTexture(Colour colour) {
        switch (colour) {
            case CYAN:
                return new Texture("cyan.jpg");
            //More cases here
            default:
                return new Texture("cyan.jpg");
        }
    }
}
