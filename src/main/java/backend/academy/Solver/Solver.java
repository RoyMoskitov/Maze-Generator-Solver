package backend.academy.Solver;

import backend.academy.Maze.Coordinate;
import backend.academy.Maze.Maze;
import java.util.List;

/**
 * class that find a path in a maze, two accessible implementations find the longest path
 * and also containing the largest number of coins
 * (coins are considered reachable only if they can be reached without passing through the end coordinate,
 * all coins that are located after will not be taken into account
 */
public interface Solver {
    /**
     * main function that returns found path in a maze
     * @param maze maze
     * @param start start coordinate
     * @param end end coordinate
     * @return created path from start to end
     */
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
