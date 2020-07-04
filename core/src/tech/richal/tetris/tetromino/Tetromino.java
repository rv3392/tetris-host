package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public abstract class Tetromino {
    private int x;
    private int y;
    private Direction pieceDirection;

    private Grid grid;

    /**
     * <h4> Used to create a new Tetromino with the provided Grid. </h4>
     * 
     * <p> 
     * Sets the position to (x,y) = (0,0) and direction to Direction.NORTH. 
     * </p>
     * 
     * <p>
     * This constructor can only be called from a subclass in the from of
     * super(pieceGrid). Such a call should provide a Grid generated for the
     * specific concrete Tetromino being created. See CyanTetromino.java for
     * an example of a concrete Tetromino. 
     * </p>
     * 
     * @param pieceGrid - the Grid for a particular concrete Tetromino
     */
    protected Tetromino(Grid pieceGrid) {
        this.x = 5;
        this.y = 19;
        this.pieceDirection = Direction.NORTH;

        grid = pieceGrid;
    }

    /**
     * Updates the position and rotation of this tetromino based on x, y 
     * and rotation.
     *
     * @param x
     * @param y
     * @param rotation
     */
    public void update(int x, int y, boolean rotation) {
        this.y += y;
        this.x += x;

        if (rotation) {
            this.pieceDirection = Direction.rotate(this.pieceDirection);
            this.grid.rotateAnticlockwise();
        }
    }

    /**
     * Checks to see if the tetromino has landed on top of another one. 
     * A tetromino cannot move past this point.
     *
     * @param piece
     * @return
     */
    public boolean isOnTopOf(Tetromino piece) {
        for (int i = 0; i < this.grid.getWidth(); i++) {

        }

        return false;
    }

    /**
     * Get the x-coordinate of this Tetromino on the board.
     * Measured from the bottom-left corner of the board.
     * @return this.x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get the y-coordinate of this Tetromino on the board.
     * Measured from the bottom-left corner of the board.
     * @return this.y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Get the direction of this Tetromino.
     * @return this.pieceDirection
     */
    public Direction getPieceDirection() {
        return this.pieceDirection;
    }

    /**
     * Get the Grid representation of this Tetromino
     * @return this.grid
     */
    public Grid display() {
        return this.grid;
    }
}
