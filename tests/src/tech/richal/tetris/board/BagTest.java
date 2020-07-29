package tech.richal.tetris.board;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tech.richal.tetris.tetromino.Tetromino;

public class BagTest {
    private Bag testBag;

    @Before
    public void setupBag() {
        testBag = new Bag(0, 0);
    }

    @Test
    public void noRepeatedTetrominos() {
        ArrayList<Tetromino> piecesPopped = new ArrayList<Tetromino>();
        for (int i = 0; i < 7; i++) {
            Tetromino justPopped = testBag.pop();
            for (Tetromino previous : piecesPopped) {
                Assert.assertNotEquals(previous.getClass(), justPopped.getClass());
            }
            piecesPopped.add(justPopped);
        }
    }

    @Test
    public void correctNextTetrominoForFirst() {
            Tetromino next = testBag.peek(1);
            Tetromino popped = testBag.pop();
            Assert.assertEquals(next, popped);
    }

    @Test
    public void bagAutoRefill() {
        // Pop an entire bag-full of Tetrominos
        for (int i = 0; i < 7; i++) {
            testBag.pop();
        }

        // Check if the bag still works
        ArrayList<Tetromino> piecesPopped = new ArrayList<Tetromino>();
            for (int i = 0; i < 7; i++) {
                Tetromino justPopped = testBag.pop();
                for (Tetromino previous : piecesPopped) {
                    Assert.assertNotEquals(previous.getClass(), justPopped.getClass());
                }
                piecesPopped.add(justPopped);
            }
    }

    @Test
    public void correctNextTetrominoAfterRefill() {
        // Pop one less than an entire bag-full of Tetrominos
        for (int i = 0; i < 7; i++) {
            testBag.pop();
        }

        Tetromino next = testBag.peek(1);
        Tetromino popped = testBag.pop();
        Assert.assertEquals(next, popped);
    }
}