package backend.academy.Maze;

/**
 * class for describing all cell type and position
 * @param row cell's row, Y coordinate
 * @param col cell's column, X coordinate
 * @param type cell's type, can be a wall, passage or coin
 */
public record Cell(int row, int col, Type type) {
    public enum Type { WALL, PASSAGE, COIN }
}
