package tech.richal.tetris;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tech.richal.tetris.grid.Grid;
import tech.richal.tetris.tetromino.Colour;

public class TetrisView {
    private static final Texture[] PIECE_TEXTURES = {
            new Texture("clear.jpg"),
            new Texture("cyan.jpg"),
            new Texture("blue.jpg"),
            new Texture("orange.jpg"),
            new Texture("yellow.jpg"),
            new Texture("green.jpg"),
            new Texture("purple.jpg"),
            new Texture("red.jpg")
    };

    public void draw(SpriteBatch batch, Grid tetrisBoardGrid) {
        batch.begin();
        for (int x = 0; x < tetrisBoardGrid.getWidth(); x++) {
            for (int y = 0; y < tetrisBoardGrid.getHeight(); y++) {
                batch.draw(
                        colourToTexture(tetrisBoardGrid.getGridSpace(x, y)),
                        x * 40, y * 40, //x and y
                        40, 40 //width and height
                );
            }
        }
        batch.end();
    }

    private static Texture colourToTexture(Colour colour) {
        switch (colour) {
            case NONE:
                return PIECE_TEXTURES[0];
            case CYAN:
                return PIECE_TEXTURES[1];
            case BLUE:
                return PIECE_TEXTURES[2];
            case ORANGE:
                return PIECE_TEXTURES[3];
            case YELLOW:
                return PIECE_TEXTURES[4];
            case GREEN:
                return PIECE_TEXTURES[5];
            case PURPLE:
                return PIECE_TEXTURES[6];
            case RED:
                return PIECE_TEXTURES[7];
            default:
                return PIECE_TEXTURES[0];
        }
    }
}
