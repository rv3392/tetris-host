package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class CyanTetromino extends Tetromino {
    private static final int WIDTH = 4;
    private static final int HEIGHT = 4;
    private static final Colour COLOUR = Colour.CYAN;

    public CyanTetromino(int x, int y) {
        super(createGrid(), x, y);
    }

    private static Grid createGrid() {
        Grid createdGrid = new Grid(WIDTH, HEIGHT);
        for (int x = 0; x < WIDTH; x++) {
            createdGrid.setGridSpace(x, 2, COLOUR);
        }
        return createdGrid;
    }
}
