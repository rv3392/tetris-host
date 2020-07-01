package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class GreenTetromino extends Tetromino {
    public GreenTetromino() {
        super(createGrid());
    }

    private static Grid createGrid() {
        return new Grid(0,0);
    }
}
