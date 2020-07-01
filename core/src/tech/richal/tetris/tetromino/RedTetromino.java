package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class RedTetromino extends Tetromino {
    public RedTetromino() {
        super(createGrid());
    }

    private static Grid createGrid() {
        return new Grid(0,0);
    }
}
