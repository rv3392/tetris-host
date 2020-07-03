package tech.richal.tetris.grid;

import tech.richal.tetris.tetromino.Colour;

public class Grid {
    private int width;
    private int height;
    private Colour[][] grid;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;

        this.grid = new Colour[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.grid[x][y] = Colour.NONE;
            }
        }
    }

    public void setGridSpace(int x, int y, Colour colour) {
        this.grid[x][y] = colour;
    }

    public Colour getGridSpace(int x, int y) {
        return this.grid[x][y];
    }

    public void setGridSpaces(int x, int y, Grid grid) {
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                Colour newColour = grid.getGridSpace(i, j);
                if (newColour == Colour.NONE) {
                    //If the newColour is empty/transparent then do nothing
                    //continue;
                }
                this.setGridSpace(x + i, y + j, newColour);
            }
        }
    }

    public void clearGrid() {
        this.setGridSpaces(0, 0, new Grid(this.width, this.height));
    }

    /**
     * Rotates the grid in a clockwise direction. 
     * 
     * Swaps the height and the width. Transposes the grid (swapping rows and
     * columns) and then reverses the order of the columns.
     */
    public void rotateClockwise() {
        Colour[][] rotatedGrid = new Colour[this.height][this.width];

        // Transpose the Grid for this piece
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                rotatedGrid[y][x] = this.grid[x][y];
            }
        }

        this.grid = rotatedGrid;
        this.width = rotatedGrid.length;
        this.height = rotatedGrid[0].length;

        // Reverse columns of the grid
        for (int x = 0; x < this.width; x++) {
            rotatedGrid[this.width - x - 1] = this.grid[x];
        }

        this.grid = rotatedGrid;
    }

    public int getWidth() { 
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
