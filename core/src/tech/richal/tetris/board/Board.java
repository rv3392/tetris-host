package tech.richal.tetris.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tech.richal.tetris.grid.Grid;
import tech.richal.tetris.tetromino.*;

public class Board {
    private static final int START_Y_POS = 20;
    private static final int START_X_POS = 5;

    private static final int ROW_NOT_FULL = -1;

    private int width;
    private int height;

    private List<Tetromino> pieces;
    private int fallingPieceIndex;

    private Grid grid;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        this.pieces = new ArrayList<>();
        this.fallingPieceIndex = -1;

        this.grid = new Grid(this.width, this.height);
    }

    public BoardUpdateResult update(int x, int y, boolean rotate) {
        if (this.pieces.size() == 0) {
            this.createNewPiece();
            return BoardUpdateResult.SUCCESS;
        }

        Tetromino fallingPiece = this.pieces.get(this.fallingPieceIndex);

        if (fallingPiece.getY() == 0) {
            return this.handlePieceReachedBottom();
        }

        for (Tetromino checkPiece : this.pieces.subList(0, this.fallingPieceIndex)) {
            if (fallingPiece.isOnTopOf(checkPiece)) {
                return this.handlePieceReachedBottom();                
            }
        }

        if (this.isUpdateValid(x, y, rotate)) {
            fallingPiece.update(x, y, rotate);
            return BoardUpdateResult.SUCCESS;
        }

        return BoardUpdateResult.NO_VALID_MOVE;
    }

    public Grid display() {
        Grid displayGrid = new Grid(this.grid);
        if (this.fallingPieceIndex != -1) {
            Tetromino fallingPiece = pieces.get(this.fallingPieceIndex);
            displayGrid.setGridSpaces(fallingPiece.getX(), fallingPiece.getY(),
                    fallingPiece.display());
        }

        return displayGrid;
    }

    private boolean isUpdateValid(int x, int y, boolean rotate) {
        Tetromino fallingPiece = (Tetromino) this.pieces.get(this.fallingPieceIndex).clone();
        fallingPiece.update(x, y, rotate);

        // Iterate over all of the pieces on the board other than the one that's falling.
        for (Tetromino checkPiece : this.pieces.subList(0, this.fallingPieceIndex)) {
            if (fallingPiece.overlaps(checkPiece)) {
                return false;
            }
        }

        if (fallingPiece.getX() < 0 || fallingPiece.getY() < 0
                || fallingPiece.getX() + fallingPiece.display().getWidth() > this.width
                || fallingPiece.getY() + fallingPiece.display().getHeight() > this.height) {
            return false;
        }

        return true;
    }

    private BoardUpdateResult handlePieceReachedBottom() {
        if (this.checkForFullColumn() == true) {
            return BoardUpdateResult.GAME_OVER;
        }

        Tetromino fallingPiece = pieces.get(this.fallingPieceIndex);
        this.grid.setGridSpaces(fallingPiece.getX(), fallingPiece.getY(), fallingPiece.display());

        int fullRowNumber = this.findFullRow();
        if (fullRowNumber != ROW_NOT_FULL) {
            this.deleteRow(fullRowNumber);
        };
        
        this.createNewPiece();
        return BoardUpdateResult.SUCCESS;
    }

    private void createNewPiece() {
        // Pick a random colour and create a Tetromino of that specific colour
        int randomPieceColour = new Random().nextInt(Colour.values().length);
        Tetromino newPiece = TetrominoFactory.createInstance(
                Colour.values()[randomPieceColour], START_X_POS, START_Y_POS);
        this.pieces.add(newPiece);
        this.fallingPieceIndex++;
    }

    private boolean checkForFullColumn() {
        Tetromino fallingPiece = this.pieces.get(this.fallingPieceIndex);
        for (int col = fallingPiece.getX(); 
                col < fallingPiece.getX() + fallingPiece.display().getWidth(); col++) {
            if (isColumnFull(col)) {
                return true;
            }
        }

        return false;
    }

    private boolean isColumnFull(int col) {
        if (this.grid.getGridSpace(col, START_Y_POS) == Colour.NONE) {
            return false;
        }

        return true;
    }

    private int findFullRow() {
        int row = 0;
        for (row = 0; row < this.height; row++) {
            boolean rowFull = true;
            for (int col = 0; col < this.width; col++) { 
                if (this.grid.getGridSpace(col, row) == Colour.NONE) {
                    rowFull = false;
                }
            }

            if (rowFull) {
                return row;
            }
        }

        return ROW_NOT_FULL;
    }

    private void deleteRow(int row) {
        for (int y = row + 1; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.grid.setGridSpace(x, y - 1, this.grid.getGridSpace(x, y));
            }
        }
    }
}
