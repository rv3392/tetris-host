package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class BlueTetromino extends Tetromino {
    public BlueTetromino() {
        super(createGrid());
    }

    private static Grid createGrid() {
        return new Grid(0,0);
    }
}
