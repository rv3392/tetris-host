package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class YellowTetromino extends Tetromino {
    private static final int WIDTH = 4;
    private static final int HEIGHT = 4;
    private static final Colour TETROMINO_COLOUR = Colour.YELLOW;

    private static final int LEFT_BOUND = 1;
    private static final int RIGHT_BOUND = 3;
    private static final int TOP_BOUND = 1;
    private static final int LOWER_BOUND = 3;

    public YellowTetromino(int x, int y) {
        super(createGrid(), x, y);
    }

    private static Grid createGrid() {
        Grid pieceGrid = new Grid(WIDTH, HEIGHT);
        for (int x = LEFT_BOUND; x < RIGHT_BOUND; x++) {
            for (int y = TOP_BOUND; y < LOWER_BOUND; y++) {
                pieceGrid.setGridSpace(x, y, TETROMINO_COLOUR);
            }
        }

        return pieceGrid;
    }
}
