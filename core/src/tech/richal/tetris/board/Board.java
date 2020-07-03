package tech.richal.tetris.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tech.richal.tetris.grid.Grid;
import tech.richal.tetris.tetromino.*;

public class Board {
    private int width;
    private int height;

    private List<Tetromino> pieces;
    private int fallingPieceIndex;

    private Grid displayGrid;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        this.pieces = new ArrayList<>();
        this.fallingPieceIndex = 0;

        this.displayGrid = new Grid(this.width, this.height);
    }

    public void update(int x, int y, boolean rotate) {
        if (this.pieces.size() == 0) {
            this.createNewPiece();
            return;
        }

        Tetromino fallingPiece = this.pieces.get(this.pieces.size() - 1);
        fallingPiece.update(x, y, rotate);

        for (Tetromino checkPiece : this.pieces) {
            if (checkPiece.equals(fallingPiece)) {
                continue;
            }

            if (checkPiece.isOnTopOf(fallingPiece)) {
                this.createNewPiece();
                break;
            }
        }

        if (fallingPiece.getY() == 0) {
            this.createNewPiece();
        }
    }

    public Grid display() {
        displayGrid.clearGrid();
        
        for (Tetromino piece : this.pieces) {
            displayGrid.setGridSpaces(piece.getX(), piece.getY(), piece.display());
        }

        return displayGrid;
    }

    private void createNewPiece() {
        // Pick a random colour and create a Tetromino of that specific colour
        int randomPieceColour = new Random().nextInt(Colour.values().length);

        // Colour.CYAN is temporary until other colours have implemented grids
        Tetromino newPiece = TetrominoFactory.createInstance(Colour.CYAN);
        this.pieces.add(newPiece);

        if (this.fallingPieceIndex != 0) {
            this.fallingPieceIndex++;
        }
    }

    private boolean checkForFullRow(int row) {
        return false;
    }
}
