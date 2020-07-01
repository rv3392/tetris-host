package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class YellowTetromino extends Tetromino {
    public YellowTetromino() {
        super(createGrid());
    }

    private static Grid createGrid() {
        return new Grid(0,0);
    }
}
