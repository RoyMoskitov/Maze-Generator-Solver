package backend.academy.Solver;

import backend.academy.Maze.Cell;
import backend.academy.Maze.Coordinate;
import backend.academy.Maze.Maze;
import backend.academy.Solver.HandOnWall.HandOnWallSolver;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NeighboursFinderTest {

        /*      Test maze picture:
          0  1  2  3  4  5  6
        0 #  #  #  #  #  #  #
        1 #  C  .  .  .  .  #
        2 #  .  #  #  #  .  #
        3 #  C  #  .  #  C  #
        4 #  .  #  .  #  #  #
        5 #  .  .  .  C  .  #
        6 #  #  #  #  #  #  #
*/

    Maze maze;
    Solver solver = new HandOnWallSolver();

    @BeforeEach
    void Init() {
        int width = 7;
        int height = 7;
        Cell[][] grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                } else if ((i == 1 && j == 1) || (i == 3 && j == 1) || (i == 3 && j == 5) || (i == 5 && j == 4)) {
                    grid[i][j] = new Cell(i, j, Cell.Type.COIN);
                } else if ((i == 2 && j == 2) || (i == 2 && j == 3) || (i == 2 && j == 4) || (i == 4 && j == 2)
                    || (i == 4 && j == 4) || (i == 3 && j == 2) || (i == 3 && j == 4) || (i == 4 && j == 5)) {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                }
            }
        }
        maze = new Maze(height, width, grid);
    }

    @Test
    void TestFindValidNeighbours() {
        List<Coordinate> rightNeighbours = List.of(new Coordinate(1, 1), new Coordinate(1, 3));

        assertEquals(NeighboursFinder.findValidNeighbours(new Coordinate(1, 2), maze.grid()), rightNeighbours);
    }
}
