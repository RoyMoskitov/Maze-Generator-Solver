package backend.academy.Generator.RecursiveBacktracker;

import backend.academy.Generator.Generator;
import backend.academy.Maze.Cell;
import backend.academy.Maze.Maze;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecursiveBacktrackingGeneratorTest {

    Generator generator = new RecursiveBacktrackingGenerator("test".getBytes());

    @Test
    void TestGenerationWithWrongLargeSize() {
        assertThrows(IllegalArgumentException.class, () -> generator.generate(100, 6));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(6, 699));
    }

    @Test
    void TestGenerationWithWrongSmallSize() {
        assertThrows(IllegalArgumentException.class, () -> generator.generate(1, 6));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(6, 0));
    }

    @Test
    void TestNormalGeneration() {
        Maze maze = generator.generate(25, 25);

        assertEquals(maze.height(), 25);
        assertEquals(maze.width(), 25);
        for (int i = 0; i < maze.height(); ++i) {
            for (int j = 0; j < maze.width(); ++j) {
                if (i == 0 || j == 0 || i == maze.height() - 1 || j == maze.width() - 1) {
                    assertEquals(maze.grid()[i][j].type(), Cell.Type.WALL);
                }
            }
        }
    }

}
