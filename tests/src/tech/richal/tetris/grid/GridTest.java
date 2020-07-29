package tech.richal.tetris.grid;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import tech.richal.tetris.grid.Grid;
import tech.richal.tetris.tetromino.Colour;

public class GridTest {
    private Grid testGrid;

    @Before
    public void setupGrid() {
        testGrid = new Grid(5, 8);
    }

    @Test
    public void rotatedGridHasDimensionsSwapped() {
        testGrid.rotateAnticlockwise();
        Assert.assertEquals(8, testGrid.getWidth());
        Assert.assertEquals(5, testGrid.getHeight());
    }

    @Test
    public void rotatedGridIsProperlyRotated() {
        testGrid.setGridSpace(0, 0, Colour.BLUE);
        testGrid.rotateAnticlockwise();
        Assert.assertEquals(Colour.BLUE, testGrid.getGridSpace(7, 0));
        Assert.assertEquals(Colour.NONE, testGrid.getGridSpace(0, 0));
    }

    @Test
    public void deletedRowHasDimensionsCorrect() {
        testGrid.deleteRow(2);
        Assert.assertEquals(7, testGrid.getHeight());
    }

    @Test
    public void deletedRowIsDeletedCorrectly() {
        testGrid.setGridSpace(0, 2, Colour.BLUE);
        testGrid.setGridSpace(0, 4, Colour.BLUE);
        testGrid.deleteRow(2);

        for (int x = 0; x < testGrid.getHeight(); x++) {
            for (int y = 0; y < testGrid.getWidth(); y++) {
                if (x == 0 && y == 3) {
                    Assert.assertEquals(Colour.BLUE, testGrid.getGridSpace(x, y));
                } else {
                    Assert.assertEquals(Colour.NONE, testGrid.getGridSpace(x, y));
                }
            }
        }
    }

    @Test
    public void clearedGridIsEmpty() {
        testGrid.setGridSpace(0, 0, Colour.BLUE);
        testGrid.clearGrid();

        for (int x = 0; x < testGrid.getWidth(); x++) {
            for (int y = 0; y < testGrid.getHeight(); y++) {
                Assert.assertEquals(Colour.NONE, testGrid.getGridSpace(x, y));
            }
        }
    }

    @Test
    public void clonedGridSameClass() {
        Grid clone = new Grid(testGrid);
        Assert.assertEquals(testGrid.getClass(), clone.getClass());
    }

    @Test
    public void clonedGridNotEqual() {
        Grid clone = new Grid(testGrid);
        Assert.assertNotEquals(testGrid, clone);
    }

    @Test
    public void clonedGridWidthAndHeight() {
        Grid clone = new Grid(testGrid);
        Assert.assertEquals(testGrid.getWidth(), clone.getWidth());
        Assert.assertEquals(testGrid.getHeight(), clone.getHeight());
    }
}