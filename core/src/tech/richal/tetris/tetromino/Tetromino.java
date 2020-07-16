package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public abstract class Tetromino implements Cloneable {
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
    protected Tetromino(Grid pieceGrid, int x, int y) {
        this.x = x;
        this.y = y;
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
    public boolean isOnTopOf(Tetromino otherPiece) {
        if (otherPiece.x > this.x + this.grid.getWidth()
                || otherPiece.x + otherPiece.grid.getWidth() < this.x) {
            return false;
        }

        if (otherPiece.y > this.y + this.grid.getHeight()
                || otherPiece.y + otherPiece.grid.getHeight() < this.y) {
            return false;
        }

        Tetromino above, below;
        if (otherPiece.y < this.y) {
            above = this;
            below = otherPiece;
        } else {
            above = otherPiece;
            below = this;
        }

        int leftBound = 0;
        int rightBound = 0;

        if (above.x >= below.x) {
            leftBound = above.x;
        } else {
            leftBound = below.x;
        }

        if (above.x + above.grid.getWidth() >= below.x + below.grid.getWidth()) {
            rightBound = below.x + below.grid.getWidth();
        } else {
            rightBound = above.x + above.grid.getWidth();
        }

        for (int col = leftBound; col < rightBound; col++) {
            int aboveLowestColoured = -1;
            int belowHighestColoured = -3;
            for (int aboveRow = 0; aboveRow < above.grid.getHeight(); aboveRow++) {
                if (above.grid.getGridSpace(col - above.x, aboveRow) != Colour.NONE) {
                    aboveLowestColoured = aboveRow + above.y;
                    break;
                }
            }

            for (int belowRow = below.grid.getHeight() - 1; belowRow >= 0; belowRow--) {
                if (below.grid.getGridSpace(col - below.x, belowRow) != Colour.NONE) {
                    belowHighestColoured = belowRow + below.y;
                    break;
                }
            }

            if (aboveLowestColoured - 1 == belowHighestColoured) {
                return true;
            }
        }

        return false;
    }

    public boolean overlaps(Tetromino otherPiece) {
        // Find the common region where the grids of "this" and "otherPiece" overlap.
        // Pick the furthest right x-coordinate
        int leftBound = this.x < otherPiece.x ? otherPiece.x : this.x;
        // Pick the furthest left x-coordinate + width
        int rightBound = this.x + this.grid.getWidth() < otherPiece.x + otherPiece.grid.getWidth() ?
                this.x + this.grid.getWidth() : otherPiece.x + otherPiece.grid.getWidth();
        // Pick the highest y-coordinate + height
        int upperBound = this.y + this.grid.getHeight() < otherPiece.y + otherPiece.grid.getHeight() ?
                this.y + this.grid.getHeight() : otherPiece.y + otherPiece.grid.getHeight();
        // Pick the lowest y-coordinate
        int lowerBound = this.y < otherPiece.y ? otherPiece.y : this.y;

        // Use the determined common region and check if there is an overlap within.
        for (int col = leftBound; col < rightBound; col++) {
            for (int row = lowerBound; row < upperBound; row++) {
                if (this.grid.getGridSpace(col - this.x, row - this.y) == Colour.NONE) {
                    continue;
                }
                if (otherPiece.grid.getGridSpace(col - otherPiece.x, row - otherPiece.y) == Colour.NONE) {
                    continue;
                }

                return true;
            }
        }

        return false;
    }

    /*
     * Using clone() rather than a copy-constructor since Tetromino has many
     * subclasses and it would be redundant to create a copy-constructor for
     * each one.
     */
    @Override
    public Object clone() {
        Tetromino cloned;

        try {
            cloned = (Tetromino) super.clone();
            cloned.grid = new Grid(this.grid);
            return cloned;
        } catch (CloneNotSupportedException e) {
            System.err.println("Error encountered while trying to clone a Tetromino.");
            System.err.println(e.getStackTrace());
            System.exit(1);
        }

        return new Object();
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
