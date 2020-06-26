package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class BlueTetromino extends Tetromino {
    public BlueTetromino() {
        super();
    }

    public Grid createGrid() {
        return new Grid(0,0);
    }
}
