package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class YellowTetromino extends Tetromino {
    private static final int WIDTH = 2;
    private static final int HEIGHT = 2;
    private static final Colour TETROMINO_COLOUR = Colour.YELLOW;

    public YellowTetromino(int x, int y) {
        super(createGrid(), x, y);
    }

    private static Grid createGrid() {
        Grid pieceGrid = new Grid(WIDTH, HEIGHT);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                pieceGrid.setGridSpace(x, y, TETROMINO_COLOUR);
            }
        }

        return pieceGrid;
    }
}
