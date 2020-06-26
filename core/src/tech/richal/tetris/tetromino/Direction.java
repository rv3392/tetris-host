package tech.richal.tetris.tetromino;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public static Direction rotate(Direction direction) {
        switch(direction) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                return NORTH;
        }
    }
}
