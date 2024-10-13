package backend.academy.Solver;

import backend.academy.Maze.Cell;
import backend.academy.Maze.Coordinate;

public class SolvingValidator {

    private SolvingValidator() {
    }

    public static void validate(Cell[][] grid, Coordinate start, Coordinate end) {
        StringBuilder errorMessage = new StringBuilder();
        if (grid[start.row()][start.col()].type() == Cell.Type.WALL) {
            errorMessage.append("Start coordinate mustn't be a wall%n");
        }
        if (grid[end.row()][end.col()].type() == Cell.Type.WALL) {
            errorMessage.append("End coordinate mustn't be a wall%n");
        }
        if (errorMessage.length() != 0) {
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }
}
