package tech.richal.tetris.tetromino;

public enum Colour {
    NONE, CYAN, BLUE, ORANGE, YELLOW, GREEN, PURPLE, RED;

    public static Tetromino colourToTetronimo(Colour colour) {
        switch (colour) {
            case CYAN:
                return new CyanTetromino();
            case BLUE:
                return new BlueTetromino();
            case ORANGE:
                return new OrangeTetromino();
            case YELLOW:
                return new YellowTetromino();
            case GREEN:
                return new GreenTetromino();
            case PURPLE:
                return new PurpleTetromino();
            case RED:
                return new RedTetromino();
            default:
                return new CyanTetromino();
        }
    }
}
