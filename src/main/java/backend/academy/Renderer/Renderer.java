package backend.academy.Renderer;

import backend.academy.Maze.Coordinate;
import backend.academy.Maze.Maze;
import java.util.List;

public interface Renderer {

    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}
