package backend.academy.Solver.BFS;

import backend.academy.Maze.Cell;
import backend.academy.Maze.Coordinate;
import backend.academy.Maze.Maze;
import backend.academy.Solver.NeighboursFinder;
import backend.academy.Solver.Solver;
import backend.academy.Solver.SolvingValidator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * modernized version of bfs, that firstly finds paths to the end coordinate and all accessible coins
 * and then merge them, leaving only unique cells
 */
public class BFSSolver implements Solver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        //validation
        SolvingValidator.validate(maze.grid(), start, end);

        List<Coordinate> listOfDestinations = new ArrayList<>();
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(start);

        Coordinate[][] parent = new Coordinate[maze.height()][maze.width()];

        while (!queue.isEmpty()) {
            Coordinate cur = queue.poll();

            List<Coordinate> neighbours = NeighboursFinder.findValidNeighbours(cur, maze.grid());

            if (maze.grid()[cur.row()][cur.col()].type() == Cell.Type.COIN || end.equals(cur)) {
                listOfDestinations.add(cur);
            }
            if (!cur.equals(end)) {
                for (var neighbour : neighbours) {

                    //if element is not visited, add it to queue and set its parent
                    if (parent[neighbour.row()][neighbour.col()] == null) {
                        parent[neighbour.row()][neighbour.col()] = new Coordinate(cur.row(), cur.col());
                        queue.add(neighbour);
                    }
                }
            }
        }
        return mergeLists(makeListOfPaths(listOfDestinations, parent, start));
    }

    /**
     * help function that creates list of paths all of which starts at the start coordinate
     * and end up on the coordinate in listOfDestinations
     * @param listOfDestinations list of end cells
     * @param parent matrix where all parent cells are located, needed to restore the path
     * @param start start cell
     * @return list of paths
     */
    private List<List<Coordinate>> makeListOfPaths(
        List<Coordinate> listOfDestinations, Coordinate[][] parent, Coordinate start
    ) {
        List<List<Coordinate>> listOfPaths = new ArrayList<>(listOfDestinations.size());
        for (var dest : listOfDestinations) {
            List<Coordinate> path = new ArrayList<>();
            Coordinate cur = dest;
            while (!cur.equals(start)) {
                path.add(0, cur);
                cur = parent[cur.row()][cur.col()];
            }
            path.add(0, cur);
            listOfPaths.add(path);
        }
        return listOfPaths;
    }

    /**
     * function that creates one path with unique coordinates out of list of lists of coordinates
     * @param lists list of lists
     * @return list of unique coordinates
     */
    private List<Coordinate> mergeLists(List<List<Coordinate>> lists) {
        return lists.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toSet())
            .stream()
            .toList();
    }
}
