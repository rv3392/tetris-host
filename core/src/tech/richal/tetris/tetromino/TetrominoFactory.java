package tech.richal.tetris.tetromino;

public class TetrominoFactory {
    public static Tetromino createInstance(Colour colour, int x, int y) {
        switch (colour) {
            case CYAN:
                return new CyanTetromino(x, y);
            case BLUE:
                return new BlueTetromino(x, y);
            case ORANGE:
                return new OrangeTetromino(x, y);
            case YELLOW:
                return new YellowTetromino(x, y);
            case GREEN:
                return new GreenTetromino(x, y);
            case PURPLE:
                return new PurpleTetromino(x, y);
            case RED:
                return new RedTetromino(x, y);
            default:
                return new CyanTetromino(x, y);
        }
    }
}