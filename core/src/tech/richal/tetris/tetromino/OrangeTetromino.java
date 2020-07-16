package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public class OrangeTetromino extends Tetromino {
    private static final int WIDTH = 3;
    private static final int HEIGHT = 2;
    private static final Colour TETROMINO_COLOUR = Colour.ORANGE;

    public OrangeTetromino(int x, int y) {
        super(createGrid(), x, y);
    }

    /**
     * Creates a grid with a "L" shape when vertical.
     * (Starts horizontal).
     * @return the Grid for the Orange Tetromino (L-Tetromino)
     */
    private static Grid createGrid() {
        Grid pieceGrid = new Grid(WIDTH, HEIGHT);
        pieceGrid.setGridSpace(2,1, TETROMINO_COLOUR);
        for (int x = 0; x < WIDTH; x++) {
            pieceGrid.setGridSpace(x, 0, TETROMINO_COLOUR);
        }

        return pieceGrid;
    }
}
