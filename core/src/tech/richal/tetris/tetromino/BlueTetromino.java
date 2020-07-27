package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class BlueTetromino extends Tetromino {
    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;
    private static final Colour TETROMINO_COLOUR = Colour.BLUE;

    public BlueTetromino(int x, int y) {
        super(createGrid(), x, y);
    }

    /**
     * Creates a grid with a "J" shape when vertical.
     * (Starts horizontal).
     * @return the Grid for the Blue Tetromino (J-Tetromino)
     */
    private static Grid createGrid() {
        Grid pieceGrid = new Grid(WIDTH, HEIGHT);
        pieceGrid.setGridSpace(0,1, TETROMINO_COLOUR);
        for (int x = 0; x < WIDTH; x++) {
            pieceGrid.setGridSpace(x, 0, TETROMINO_COLOUR);
        }

        return pieceGrid;
    }
}
