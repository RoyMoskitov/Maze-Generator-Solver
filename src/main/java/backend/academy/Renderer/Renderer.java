package backend.academy.Renderer;

import backend.academy.Maze.Coordinate;
import backend.academy.Maze.Maze;
import java.util.List;

/**
 * class for rendering mazes with and without founded path
 */
public interface Renderer {

    /**
     * renders maze without path
     * @param maze maze
     * @return string picture of maze
     */
    String render(Maze maze);

    /**
     * renders maze with path
     * @param maze maze
     * @param path path
     * @return string picture of maze and path there
     */
    String render(Maze maze, List<Coordinate> path);
}
