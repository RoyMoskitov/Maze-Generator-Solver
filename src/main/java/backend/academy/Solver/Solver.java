package backend.academy.Solver;

import backend.academy.Maze.Coordinate;
import backend.academy.Maze.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
