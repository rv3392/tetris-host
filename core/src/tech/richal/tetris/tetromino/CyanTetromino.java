package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class CyanTetromino extends Tetromino {
    private final int WIDTH = 1;
    private final int HEIGHT = 4;
    private final Colour COLOUR = Colour.CYAN;

    private Grid grid;

    public CyanTetromino() {
        super();

        this.grid = this.createGrid();
    }

    @Override
    public Grid createGrid() {
        Grid createdGrid = new Grid(this.WIDTH, this.HEIGHT);
        for (int y = 0; y < HEIGHT; y++) {
            createdGrid.setGridSpace(0, y, this.COLOUR);
        }
        return createdGrid;
    }
}
