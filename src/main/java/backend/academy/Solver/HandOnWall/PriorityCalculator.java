package backend.academy.Solver.HandOnWall;

import backend.academy.Maze.Coordinate;

public interface PriorityCalculator {
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

    int calculateNorth(int rowDiff, int colDiff);

    int calculateEast(int rowDiff, int colDiff);

    int calculateSouth(int rowDiff, int colDiff);

    int calculateWest(int rowDiff, int colDiff);
}
