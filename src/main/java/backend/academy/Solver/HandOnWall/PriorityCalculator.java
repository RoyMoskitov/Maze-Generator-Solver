package backend.academy.Solver.HandOnWall;

import backend.academy.Maze.Coordinate;

/**
 * help interface to define two different priority calculators, one for left hand and one for right
 */
public interface PriorityCalculator {
    /**
     * main function that calculates cell priority
     * @param first first cell algorithm is currently standing on
     * @param second second cell for which we want to define the priority
     * @param direction current algorithm direction
     * @return int value, the smaller, the higher the priority
     */
    default int calculate(Coordinate first, Coordinate second, Direction direction) {
        int rowDiff = first.row() - second.row();
        int colDiff = first.col() - second.col();

        return switch (direction) {
            case NORTH -> calculateNorth(rowDiff, colDiff);
            case EAST -> calculateEast(rowDiff, colDiff);
            case SOUTH -> calculateSouth(rowDiff, colDiff);
            case WEST -> calculateWest(rowDiff, colDiff);
        };
    }

    /**
     * help function to define the priority if algorithm's current direction is north
     * @param rowDiff difference between second and first cell rows
     * @param colDiff difference between second and first cell columns
     * @return calculated priority
     */
    int calculateNorth(int rowDiff, int colDiff);

    /**
     * help function to define the priority if algorithm's current direction is east
     * @param rowDiff difference between second and first cell rows
     * @param colDiff difference between second and first cell columns
     * @return calculated priority
     */
    int calculateEast(int rowDiff, int colDiff);

    /**
     * help function to define the priority if algorithm's current direction is south
     * @param rowDiff difference between second and first cell rows
     * @param colDiff difference between second and first cell columns
     * @return calculated priority
     */
    int calculateSouth(int rowDiff, int colDiff);

    /**
     * help function to define the priority if algorithm's current direction is west
     * @param rowDiff difference between second and first cell rows
     * @param colDiff difference between second and first cell columns
     * @return calculated priority
     */
    int calculateWest(int rowDiff, int colDiff);
}
