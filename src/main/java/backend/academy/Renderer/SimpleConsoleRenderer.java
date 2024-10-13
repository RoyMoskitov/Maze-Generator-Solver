package backend.academy.Renderer;

import backend.academy.Maze.Cell;
import backend.academy.Maze.Coordinate;
import backend.academy.Maze.Maze;
import java.util.List;

public class SimpleConsoleRenderer implements Renderer {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    @Override
    @SuppressWarnings("MultipleStringLiterals")
    public String render(Maze maze) {
        StringBuilder res = new StringBuilder();

        res.append("  ");
        for (int i = 0; i < maze.width(); ++i) {
            res.append(String.format("%2d ", i));
        }
        res.append(System.lineSeparator());

        for (int i = 0; i < maze.height(); ++i) {
            res.append(String.format("%2d ", i));
            for (int j = 0; j < maze.width(); ++j) {
                if (maze.grid()[i][j].row() == -1) {
                    res.append(ANSI_GREEN + "*  " + ANSI_RESET);
                } else {
                    switch (maze.grid()[i][j].type()) {
                        case PASSAGE -> res.append(".  ");
                        case COIN -> res.append("C  ");
                        case WALL -> res.append("#  ");
                        default -> throw new UnsupportedOperationException("Cell type "
                            + maze.grid()[i][j].type() + " is not supported");
                    }
                }
            }
            res.append(System.lineSeparator());
        }
        return res.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        Cell[][] gridCopy = new Cell[maze.height()][maze.width()];

        for (int i = 0; i < maze.height(); ++i) {
            for (int j = 0; j < maze.width(); ++j) {
                gridCopy[i][j] = maze.grid()[i][j];
            }
        }

        for (var coordinate : path) {
            gridCopy[coordinate.row()][coordinate.col()] = new Cell(-1, -1, Cell.Type.PASSAGE);
        }

        Maze tempMaze = new Maze(maze.width(), maze.height(), gridCopy);
        return render(tempMaze);
    }

}
