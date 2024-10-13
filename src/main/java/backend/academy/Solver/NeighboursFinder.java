package backend.academy.Solver;

import backend.academy.Maze.Cell;
import backend.academy.Maze.Coordinate;
import java.util.ArrayList;
import java.util.List;

public class NeighboursFinder {

    private NeighboursFinder() {
    }

    public static List<Coordinate> findValidNeighbours(Coordinate cur, Cell[][] grid) {
        List<Coordinate> res = new ArrayList<>();

        if (inBounds(cur.row() + 1, cur.col(), grid) && grid[cur.row() + 1][cur.col()].type() != Cell.Type.WALL) {
            res.add(new Coordinate(cur.row() + 1, cur.col()));
        }
        if (inBounds(cur.row() - 1, cur.col(), grid) && grid[cur.row() - 1][cur.col()].type() != Cell.Type.WALL) {
            res.add(new Coordinate(cur.row() - 1, cur.col()));
        }
        if (inBounds(cur.row(), cur.col() - 1, grid) && grid[cur.row()][cur.col() - 1].type() != Cell.Type.WALL) {
            res.add(new Coordinate(cur.row(), cur.col() - 1));
        }
        if (inBounds(cur.row(), cur.col() + 1, grid) && grid[cur.row()][cur.col() + 1].type() != Cell.Type.WALL) {
            res.add(new Coordinate(cur.row(), cur.col() + 1));
        }
        return res;
    }

    private static boolean inBounds(int row, int col, Cell[][] grid) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length;
    }
}
