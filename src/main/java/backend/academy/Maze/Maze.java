package backend.academy.Maze;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * class to describe the properties of maze
 */
@AllArgsConstructor
@Getter
@Setter
public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;
}
