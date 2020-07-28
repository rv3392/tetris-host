package tech.richal.tetris;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import tech.richal.tetris.board.Board;
import tech.richal.tetris.board.exception.NullNextTetromino;
import tech.richal.tetris.grid.Grid;
import tech.richal.tetris.tetromino.Colour;

public class TetrisView {
    private BitmapFont smallFont, bigFont;

    private Texture backgroundImage;
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

    public TetrisView() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
                Gdx.files.internal("fonts/FiraCode-Regular.ttf"));

        FreeTypeFontParameter smallFontParameter = new FreeTypeFontParameter();
        smallFontParameter.size = 12;
        smallFontParameter.color = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        smallFont = generator.generateFont(smallFontParameter);

        FreeTypeFontParameter bigFontParameter = new FreeTypeFontParameter();
        bigFontParameter.color = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        bigFont = generator.generateFont(bigFontParameter);

        backgroundImage = new Texture(Gdx.files.internal("background.png"));
    }

    public void draw(SpriteBatch batch, Board board) {
        batch.begin();
        batch.draw(this.backgroundImage, 0, 0);
        this.drawBoard(batch, board.display());
        this.drawScore(batch, board.getScore(), board.getLevel());
        try {
            this.drawNextTetromino(batch, board.getNextTetromino());
        } catch (NullNextTetromino e) {
            
        }
        batch.end();
    }

    private void drawBoard(SpriteBatch batch, Grid tetrisBoardGrid) {
        for (int x = 0; x < tetrisBoardGrid.getWidth(); x++) {
            for (int y = 0; y < tetrisBoardGrid.getHeight(); y++) {
                batch.draw(
                        colourToTexture(tetrisBoardGrid.getGridSpace(x, y)),
                        x * 40, y * 40, //x and y
                        40, 40 //width and height
                );
            }
        }
    }

    private void drawNextTetromino(SpriteBatch batch, Grid nextTetromino) {
        int leftX = (int)(495 - (nextTetromino.getWidth() / 2.0f) * 30);
        int bottomY = (int)(490 - (nextTetromino.getHeight() / 2.0f) * 30);
        for (int x = 0; x < nextTetromino.getWidth(); x++) {
            for (int y = 1; y < 3; y++) {
                batch.draw(
                        colourToTexture(nextTetromino.getGridSpace(x, y)),
                        leftX + x * 30, bottomY + y * 30, //x and y
                        30, 30 //width and height
                );
            }
        }
    }

    private void drawScore(SpriteBatch batch, int score, int level) {
        CharSequence scoreString = Integer.toString(score);
        CharSequence levelString = Integer.toString(level);
        bigFont.draw(batch, scoreString, 490, 686);
        bigFont.draw(batch, levelString, 490, 636);
    }

    private void drawAgents(SpriteBatch batch, List<String> agents) {
        for (String agent : agents) {
            CharSequence agentString = agent;
            bigFont.draw(batch, agentString, 425, 310);
        }
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
