package backend.academy.Generator.Kruskal;

import backend.academy.Generator.GenerationValidator;
import backend.academy.Generator.Generator;
import backend.academy.Maze.Cell;
import backend.academy.Maze.Maze;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//works only with odd height and width that are greater than 1
public class KruskalGenerator implements Generator {
    final static Integer[][] DIRECTIONS = {{0, 2}, {2, 0}, {-2, 0}, {0, -2}};
    private final SecureRandom random;
    //private

    public KruskalGenerator(byte[] seed) {
        this.random = new SecureRandom(seed);
    }

    @Override
    public Maze generate(int height, int width) throws IllegalArgumentException {
        //validation
        GenerationValidator.validate(height, width);

        Cell[][] grid = new Cell[height][width];
        FindUnion findUnion = new FindUnion(((height - 1) / 2) * ((width - 1) / 2));
        List<Cell> passages = new ArrayList<>();

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                //Math.abs added to pass spotbugs
                if (Math.abs(i) % 2 == 1 && Math.abs(j) % 2 == 1 && i != height - 1 && j != width - 1) {
                    int randomNum = random.nextInt(COIN_SPAWN_CHANCE_BOUND);
                    if (randomNum >= COIN_SPAWN_CHANCE_BOUND - 1) {
                        grid[i][j] = new Cell(i, j, Cell.Type.COIN);
                    } else {
                        grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                    }
                    passages.add(grid[i][j]);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                }
            }
        }

        int count = 0;
        int maxConnections = ((height - 1) / 2) * ((width - 1) / 2) - 1;
        while (count < maxConnections) {
            Cell current = passages.get(random.nextInt(passages.size()));

            List<Cell> neighbours = getValidNeighbours(current, grid);

            if (neighbours.isEmpty()) {
                continue; //надо будет подумать, возможно лучше passages.remove(current)
            }
            Cell neighbour = neighbours.get(random.nextInt(neighbours.size()));

            int curIdx = ((current.row() / 2) * ((width - 1) / 2)) + current.col() / 2;
            int neighbourIdx = ((neighbour.row() / 2) * ((width - 1) / 2)) + neighbour.col() / 2;
            if (findUnion.find(curIdx) != findUnion.find(neighbourIdx)) {

                findUnion.union(curIdx, neighbourIdx);
                removeWall(grid, current, neighbour);
                ++count;
            }
        }
        return new Maze(height, width, grid);
    }

    private void removeWall(Cell[][] grid, Cell cell1, Cell cell2) {
        int row = (int) ((long) cell1.row() + (long) cell2.row()) / 2;
        int col = (int) ((long) cell1.col() + (long) cell2.col()) / 2;
        int randomNum = random.nextInt(COIN_SPAWN_CHANCE_BOUND);
        if (randomNum >= COIN_SPAWN_CHANCE_BOUND - 1) {
            grid[row][col] = new Cell(row, col, Cell.Type.COIN);
        } else {
            grid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
        }
    }

    private List<Cell> getValidNeighbours(Cell current, Cell[][] grid) {
        List<Integer[]> directions = new ArrayList<>(Arrays.asList(DIRECTIONS));
        List<Cell> neighbours = new ArrayList<>();
        //rowDirection and colDirection added to pass spotbugs
        int rowDirection = 0;
        int colDirection = 1;
        for (var direction : directions) {
            int newRow = current.row() + direction[rowDirection];
            int newCol = current.col() + direction[colDirection];
            if (inBounds(newRow, newCol, grid) && wallExists(grid, current, grid[newRow][newCol])) {
                neighbours.add(grid[newRow][newCol]);
            }
        }
        return neighbours;
    }

    private boolean wallExists(Cell[][] grid, Cell cell1, Cell cell2) {
        return grid[(int) ((long) cell1.row() + (long) cell2.row()) / 2]
            [(int) ((long) cell1.col() + (long) cell2.col()) / 2].type() == Cell.Type.WALL;
    }

    private boolean inBounds(int row, int col, Cell[][] grid) {
        return row > 0 && col > 0 && row < grid.length - 1 && col < grid[0].length - 1;
    }

}
