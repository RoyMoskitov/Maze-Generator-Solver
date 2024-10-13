package backend.academy.Renderer;

import backend.academy.Maze.Cell;
import backend.academy.Maze.Coordinate;
import backend.academy.Maze.Maze;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static backend.academy.Renderer.SimpleConsoleRenderer.ANSI_GREEN;
import static backend.academy.Renderer.SimpleConsoleRenderer.ANSI_RESET;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleConsoleRendererTest {

    Maze maze;
    Renderer renderer = new SimpleConsoleRenderer();

    @BeforeEach
    void Init() {
        Cell[][] grid = {
            {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.WALL), new Cell(0, 2, Cell.Type.COIN)},
            {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE), new Cell(1, 2, Cell.Type.WALL)},
            {new Cell(2, 0, Cell.Type.PASSAGE), new Cell(2, 1, Cell.Type.WALL), new Cell(2, 2, Cell.Type.PASSAGE)},
        };
        maze = new Maze(3, 3, grid);
    }

    @Test
    void TestRenderingWithoutPath() {
        String output = renderer.render(maze);

        assertTrue(output.contains(".  #  C"));
        assertTrue(output.contains("#  .  #"));
        assertTrue(output.contains(".  #  ."));
    }

    @Test
    void testRenderingWithPath() {
        List<Coordinate> path
            = List.of(new Coordinate(0, 0), new Coordinate(0, 2), new Coordinate(2, 2));
        String output = renderer.render(maze, path);

        assertTrue(output.contains(ANSI_GREEN + "*  " + ANSI_RESET + "#  " + ANSI_GREEN + "*  " + ANSI_RESET));
        assertTrue(output.contains(".  #  " + ANSI_GREEN + "*  " + ANSI_RESET));
    }
}
