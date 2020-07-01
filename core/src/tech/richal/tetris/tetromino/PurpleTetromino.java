package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class PurpleTetromino extends Tetromino {
    public PurpleTetromino() {
        super(createGrid());
    }

    private static Grid createGrid() {
        return new Grid(0,0);
    }
}
