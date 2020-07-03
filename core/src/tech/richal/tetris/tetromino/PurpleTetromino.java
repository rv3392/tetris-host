package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class PurpleTetromino extends Tetromino {
    private static final int WIDTH = 3;
    private static final int HEIGHT = 2;
    private static final Colour TETROMINO_COLOUR = Colour.PURPLE;

    public PurpleTetromino() {
        super(createGrid());
    }

    private static Grid createGrid() {
        Grid pieceGrid = new Grid(WIDTH, HEIGHT);
        pieceGrid.setGridSpace(1,1, TETROMINO_COLOUR);
        for (int x = 0; x < WIDTH; x++) {
            pieceGrid.setGridSpace(x, 0, TETROMINO_COLOUR);
        }

        return pieceGrid;
    }
}
