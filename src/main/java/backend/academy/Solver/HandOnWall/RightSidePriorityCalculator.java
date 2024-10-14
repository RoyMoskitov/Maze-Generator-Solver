package backend.academy.Solver.HandOnWall;

/**
 * exactly the same as LeftSidePriorityCalculator, but for right hand on wall path creation
 */
@SuppressWarnings("MagicNumber")
public class RightSidePriorityCalculator implements PriorityCalculator {

    @Override
    public int calculateNorth(int rowDiff, int colDiff) {
        if (colDiff == -1) {
            return 1;
        }
        if (rowDiff == 1) {
            return 2;
        }
        if (colDiff == 1) {
            return 3;
        }
        return 4;
    }

    @Override
    public int calculateEast(int rowDiff, int colDiff) {
        if (rowDiff == -1) {
            return 1;
        }
        if (colDiff == -1) {
            return 2;
        }
        if (rowDiff == 1) {
            return 3;
        }
        return 4;
    }

    @Override
    public int calculateSouth(int rowDiff, int colDiff) {
        if (colDiff == 1) {
            return 1;
        }
        if (rowDiff == -1) {
            return 2;
        }
        if (colDiff == -1) {
            return 3;
        }
        return 4;
    }

    @Override
    public int calculateWest(int rowDiff, int colDiff) {
        if (rowDiff == 1) {
            return 1;
        }
        if (colDiff == 1) {
            return 2;
        }
        if (rowDiff == -1) {
            return 3;
        }
        return 4;
    }

}
