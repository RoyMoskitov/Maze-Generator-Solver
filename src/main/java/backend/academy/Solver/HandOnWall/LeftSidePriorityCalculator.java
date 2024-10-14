package backend.academy.Solver.HandOnWall;

/**
 * help class that determines the priorities for passing neighboring cells depending
 * on which direction the algorithm is currently directed
 */
@SuppressWarnings("MagicNumber")
public class LeftSidePriorityCalculator implements PriorityCalculator {

    @Override
    public int calculateNorth(int rowDiff, int colDiff) {
        if (colDiff == 1) {
            return 1;
        }
        if (rowDiff == 1) {
            return 2;
        }
        if (colDiff == -1) {
            return 3;
        }
        return 4;
    }

    @Override
    public int calculateEast(int rowDiff, int colDiff) {
        if (rowDiff == 1) {
            return 1;
        }
        if (colDiff == -1) {
            return 2;
        }
        if (rowDiff == -1) {
            return 3;
        }
        return 4;
    }

    @Override
    public int calculateSouth(int rowDiff, int colDiff) {
        if (colDiff == -1) {
            return 1;
        }
        if (rowDiff == -1) {
            return 2;
        }
        if (colDiff == 1) {
            return 3;
        }
        return 4;
    }

    @Override
    public int calculateWest(int rowDiff, int colDiff) {
        if (rowDiff == -1) {
            return 1;
        }
        if (colDiff == 1) {
            return 2;
        }
        if (rowDiff == 1) {
            return 3;
        }
        return 4;
    }
}

