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

    private void createNewPiece() {
        // Pick a random colour and create a Tetromino of that specific colour
        int randomPieceColour = new Random().nextInt(Colour.values().length);
        Tetromino newPiece = Colour.colourToTetronimo(Colour.values()[randomPieceColour]);
        this.pieces.add(newPiece);
    }

    public void update(int x, int y, boolean rotate) {
        Tetromino fallingPiece = this.pieces.get(this.fallingPieceIndex);
        fallingPiece.update(x, y, rotate);

        for (Tetromino checkPiece : this.pieces) {
            if (checkPiece.equals(fallingPiece)) {
                continue;
            }

            if (checkPiece.isOnTopOf(fallingPiece)) {
                this.createNewPiece();
                this.fallingPieceIndex++;
                break;
            }
        }

        if (fallingPiece.getX() == 0) {
            this.createNewPiece();
            this.fallingPieceIndex++;
        }
    }

    public void display() {
        for (Tetromino piece : this.pieces) {
            displayGrid.setGridSpaces(piece.getX(), piece.getY(), piece.display());
        }
    }
}
