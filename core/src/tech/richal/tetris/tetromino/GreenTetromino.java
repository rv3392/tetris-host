package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class GreenTetromino extends Tetromino {
    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;
    private static final Colour TETROMINO_COLOUR = Colour.GREEN;

    public GreenTetromino(int x, int y) {
        super(createGrid(), x, y);
    }

    private static Grid createGrid() {
        Grid pieceGrid = new Grid(WIDTH, HEIGHT);
        pieceGrid.setGridSpace(0, 0, TETROMINO_COLOUR);
        pieceGrid.setGridSpace(1, 0, TETROMINO_COLOUR);
        pieceGrid.setGridSpace(1, 1, TETROMINO_COLOUR);
        pieceGrid.setGridSpace(2, 1, TETROMINO_COLOUR);
        return pieceGrid;
    }
}
