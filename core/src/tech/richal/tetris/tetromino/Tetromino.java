package tech.richal.tetris.tetromino;

import tech.richal.tetris.grid.Grid;

public abstract class Tetromino {
    private int x;
    private int y;
    private Direction pieceDirection;
    private Colour pieceColour;
    private Grid grid;

    public Tetromino() {
        this.x = 0;
        this.y = 0;
        this.pieceDirection = Direction.NORTH;
    }

    public abstract Grid createGrid();

    public void update(int x, int y, boolean rotation) {
        this.y += y;
        this.x += x;

        if (rotation) {
            this.pieceDirection = Direction.rotate(this.pieceDirection);
        }
    }

    public boolean isOnTopOf(Tetromino piece) {
        return this.grid.onTopOf(piece.grid);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Direction getPieceDirection() {
        return this.pieceDirection;
    }

    public Colour getPieceColour() {
        return this.pieceColour;
    }

    public Grid display() {
        return this.grid;
    }
}
