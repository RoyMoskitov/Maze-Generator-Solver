package backend.academy.Solver.HandOnWall;

import backend.academy.Maze.Cell;
import backend.academy.Maze.Coordinate;
import backend.academy.Maze.Maze;
import backend.academy.Solver.NeighboursFinder;
import backend.academy.Solver.Solver;
import backend.academy.Solver.SolvingValidator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HandOnWallSolver implements Solver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        //validation
        SolvingValidator.validate(maze.grid(), start, end);

        List<List<Coordinate>> paths = new ArrayList<>();
        List<Coordinate> rightPath = new ArrayList<>();
        List<Coordinate> leftPath = new ArrayList<>();
        PriorityCalculator leftSidePriorityCalculator = new LeftSidePriorityCalculator();
        PriorityCalculator rightSidePriorityCalculator = new RightSidePriorityCalculator();

        makeOneHandPath(maze, start, end, leftSidePriorityCalculator, leftPath);
        makeOneHandPath(maze, start, end, rightSidePriorityCalculator, rightPath);

        paths.add(rightPath);
        paths.add(leftPath);
        return mergeLists(paths);
    }

    @SuppressWarnings("MagicNumber")
    public void makeOneHandPath(
        Maze maze, Coordinate start, Coordinate end,
        PriorityCalculator priorityCalculator, List<Coordinate> path
    ) {
        Coordinate cur = start;
        Direction direction = Direction.SOUTH;
        path.add(cur);
        while (!path.get(path.size() - 1).equals(end)) {

            final Coordinate currentCopy = cur;
            final Direction directionCopy = direction;

            List<Coordinate> neighbours = NeighboursFinder.findValidNeighbours(cur, maze.grid());

            neighbours.sort(Comparator.comparingInt(coord ->
                priorityCalculator.calculate(currentCopy, coord, directionCopy)));

            int rowDiff = cur.row() - neighbours.get(0).row();
            int colDiff = cur.col() - neighbours.get(0).col();
            if (rowDiff == 1) {
                direction = Direction.NORTH;
            } else if (rowDiff == -1) {
                direction = Direction.SOUTH;
            } else if (colDiff == -1) {
                direction = Direction.EAST;
            } else {
                direction = Direction.WEST;
            }

            cur = neighbours.get(0);
            path.add(cur);
            if (cur.equals(end)) {
                break;
            }
            while (path.size() - 1 >= 3 && path.get(path.size() - 3).equals(path.get(path.size() - 1))
                && maze.grid()[path.get(path.size() - 2).row()][path.get(path.size() - 2).col()].type()
                != Cell.Type.COIN) {
                path.remove(path.size() - 1);
                path.remove(path.size() - 1);
            }
        }

    }

    private List<Coordinate> mergeLists(List<List<Coordinate>> lists) {
        return lists.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toSet())
            .stream()
            .toList();
    }

}