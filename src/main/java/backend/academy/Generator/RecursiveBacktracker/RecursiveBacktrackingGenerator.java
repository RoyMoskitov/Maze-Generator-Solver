package backend.academy.Generator.RecursiveBacktracker;

import backend.academy.Generator.GenerationValidator;
import backend.academy.Generator.Generator;
import backend.academy.Maze.Cell;
import backend.academy.Maze.Maze;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * algorithm for maze generation, a randomized version of the depth-first search algorithm
 */
public class RecursiveBacktrackingGenerator implements Generator {

    private final SecureRandom random;
    final static Integer[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public RecursiveBacktrackingGenerator(byte[] seed) {
        this.random = new SecureRandom(seed);
    }

    @Override
    public Maze generate(int height, int width) throws IllegalArgumentException {
        //validation
        GenerationValidator.validate(height, width);

        Cell[][] grid = new Cell[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                grid[i][j] = new Cell(i, j, Cell.Type.WALL);
            }
        }
        int startingPoint = 1;
        grid[startingPoint][startingPoint] = new Cell(1, 1, Cell.Type.PASSAGE);
        List<Integer[]> directions = new ArrayList<>(Arrays.asList(DIRECTIONS));

        addPassage(directions, grid, 1, 1);
        return new Maze(height, width, grid);
    }

    /**
     * function for passage creating
     * @param directions list of directions in which algorithm can move
     * @param grid maze grid
     * @param curRow cell's row, Y coordinate
     * @param curCol cell's col, X coordinate
     */
    private void addPassage(List<Integer[]> directions, Cell[][] grid, int curRow, int curCol) {
        Collections.shuffle(directions, random);
        int rowDirection = 0;
        int colDirection = 1;
        for (var direction : directions) {
            int newRow = curRow + direction[rowDirection];
            int newCol = curCol + direction[colDirection];
            if (inBounds(newRow, newCol, grid) && !hasMoreThanOnePassage(newRow, newCol, grid, directions)) {

                int randomNum = random.nextInt(COIN_SPAWN_CHANCE_BOUND);
                if (randomNum >= COIN_SPAWN_CHANCE_BOUND - 1) {
                    grid[newRow][newCol] = new Cell(newRow, newCol, Cell.Type.COIN);
                } else {
                    grid[newRow][newCol] = new Cell(newRow, newCol, Cell.Type.PASSAGE);
                }

                addPassage(directions, grid, newRow, newCol);
            }
        }
    }

    /**
     * checks whether cell is in maze bounds
     * @param row cell's row, Y coordinate
     * @param col cell's col, X coordinate
     * @param grid maze grid
     * @return true if cell is on bounds, false otherwise
     */
    private boolean inBounds(int row, int col, Cell[][] grid) {
        return row > 0 && col > 0 && row < grid.length - 1 && col < grid[0].length - 1;
    }

    /**
     * checks whether cell already has one or more passages
     * @param row cell's row, Y coordinate
     * @param col cell's col, X coordinate
     * @param grid maze grid
     * @param directions list of directions in which algorithm can move
     * @return true whether the number of passages is greater than 1, false otherwise
     */
    private boolean hasMoreThanOnePassage(int row, int col, Cell[][] grid, List<Integer[]> directions) {
        int count = 0;
        int rowDirection = 0;
        int colDirection = 1;
        for (var direction : directions) {
            int newRow = row + direction[rowDirection];
            int newCol = col + direction[colDirection];
            if (grid[newRow][newCol].type() != Cell.Type.WALL) {
                ++count;
                if (count > 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
