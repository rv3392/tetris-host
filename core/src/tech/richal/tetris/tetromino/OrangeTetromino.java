package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class OrangeTetromino extends Tetromino {
    public OrangeTetromino() {
        super();
    }

    public Grid createGrid() {
        return new Grid(0,0);
    }
}