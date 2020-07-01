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

    private Colour getGridSpace(int x, int y) {
        return this.grid[x][y];
    }

    public void setGridSpaces(int x, int y, Grid grid) {
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                Colour newColour = grid.getGridSpace(i, j);
                if (newColour == Colour.NONE) {
                    //If the newColour is empty/transparent then do nothing
                    continue;
                }
                this.setGridSpace(x + i, y + j, newColour);
            }
        }
    }

    public void rotateGrid() {}

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
