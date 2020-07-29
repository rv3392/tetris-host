package tech.richal.tetris.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import tech.richal.tetris.tetromino.Colour;
import tech.richal.tetris.tetromino.Tetromino;
import tech.richal.tetris.tetromino.TetrominoFactory;

public class Bag {
    /* The number of Tetrominos to be contained within the Bag */
    private final int BAG_SIZE = 7;
    private final int MIN_FOR_REFILL = 1;
    private ArrayList<Tetromino> bag;

    private int pieceStartX, pieceStartY;

    /**
     * Constructs a Bag with exactly 7 Tetrominos.
     * 
     * There is exactly 1 of each type of Tetromino and it is in
     * a random order. Calling pop() essentially draws from the Bag
     * in a similar way to drawing a coloured-ball from a real bag.
     *
     * @param pieceStartX - Starting x-pos for each Tetromino
     * @param pieceStartY - Starting y-pos for each Tetromino
     */
    public Bag(int pieceStartX, int pieceStartY) {
        this.pieceStartX = pieceStartX;
        this.pieceStartY = pieceStartY;

        this.bag = new ArrayList<>();
        this.fill();
    }

    /**
     * Gets the next Tetromino from the bag in the order decided at creation.
     * This Tetromino is then removed from the bag.
     * 
     * If the Bag does not contain anymore pieces, it is refilled with
     * exactly 7 unique Tetromino pieces in a random order.
     * 
     * @return the next Tetromino
     */
    public Tetromino pop() {
        if (bag.size() <= this.MIN_FOR_REFILL) {
            this.fill();
        }

        Tetromino nextPiece = this.bag.get(this.bag.size() - 1);
        this.bag.remove(this.bag.get(this.bag.size() - 1));
        return nextPiece;
    }

    /**
     * Peeks into the Bag and retrieves a Tetromino without removing.
     * 
     * Looks n pieces forward into the bag. e.g. if n = 1, the next
     * Tetromino that will be popped from the bag is returned. n must
     * be between 1 and the size of the bag (inclusive).
     * @param n - how much to peek forward
     * @return the piece that is n pieces forward
     */
    public Tetromino peek(int n) {
        if (n <= 0 || n > this.bag.size()) {
            
        }

        Tetromino peekPiece = this.bag.get(this.bag.size() - n);
        return peekPiece;
    }

    /**
     * Randomly fills the bag with exactly 7 Tetrominos. There should be
     * exactly 1 of each type of Tetromino.
     */
    private void fill() {
        // Pick a random colour and create a Tetromino of that specific colour
        ArrayList<Colour> possibleColours = new ArrayList<>(Arrays.asList(Colour.values()));
        possibleColours.remove(Colour.NONE);
        
        // Fill the bag using the array of possible colours.
        // There should only be one of each type of Tetromino in the bag.
        while (possibleColours.size() > 0) {
            int randomPieceColour = new Random().nextInt(possibleColours.size());
            Tetromino newPiece = TetrominoFactory.createInstance(
                    possibleColours.get(randomPieceColour), this.pieceStartX, this.pieceStartY);
            this.bag.add(0, newPiece); // Add to start of list in case the bag already has pieces
            possibleColours.remove(randomPieceColour);
        }
    }
}