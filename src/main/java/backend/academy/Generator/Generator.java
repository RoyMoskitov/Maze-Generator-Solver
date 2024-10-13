package backend.academy.Generator;

import backend.academy.Maze.Maze;

public interface Generator {
    //coin will spawn with (1/COIN_SPAWN_CHANCE_BOUND * 100%) percent chance
    int COIN_SPAWN_CHANCE_BOUND = 4;
    int MAX_MAZE_HEIGHT = 59;
    int MAX_MAZE_WIDTH = 59;
    int MIN_MAZE_HEIGHT = 3;
    int MIN_MAZE_WIDTH = 3;

    Maze generate(int height, int width) throws IllegalArgumentException;
}
