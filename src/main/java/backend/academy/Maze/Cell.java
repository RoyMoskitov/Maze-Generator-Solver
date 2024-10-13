package backend.academy.Maze;

public record Cell(int row, int col, Type type) {
    public enum Type { WALL, PASSAGE, COIN }
}
