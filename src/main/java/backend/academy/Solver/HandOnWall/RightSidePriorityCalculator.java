package backend.academy.Solver.HandOnWall;

@SuppressWarnings("MagicNumber")
public class RightSidePriorityCalculator implements PriorityCalculator {
    /*@Override
    public int calculate(Coordinate first, Coordinate second, Direction direction) {
        int rowDiff = first.row() - second.row();
        int colDiff = first.col() - second.col();
        return switch (direction) {
            case NORTH -> (colDiff == -1) ? 1 : ((rowDiff == 1) ? 2 : ((colDiff == 1) ? 3 : 4));
            case EAST -> (rowDiff == -1) ? 1 : ((colDiff == -1) ? 2 : ((rowDiff == 1) ? 3 : 4));
            case SOUTH -> (colDiff == 1) ? 1 : ((rowDiff == -1) ? 2 : ((colDiff == -1) ? 3 : 4));
            case WEST -> (rowDiff == 1) ? 1 : ((colDiff == 1) ? 2 : ((rowDiff == -1) ? 3 : 4));
        };
    }*/

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
