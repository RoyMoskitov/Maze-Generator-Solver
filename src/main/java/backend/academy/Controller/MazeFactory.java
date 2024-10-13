package backend.academy.Controller;

import backend.academy.Generator.Generator;
import backend.academy.Generator.Kruskal.KruskalGenerator;
import backend.academy.Generator.RecursiveBacktracker.RecursiveBacktrackingGenerator;
import backend.academy.Solver.BFS.BFSSolver;
import backend.academy.Solver.HandOnWall.HandOnWallSolver;
import backend.academy.Solver.Solver;
import java.security.SecureRandom;

public class MazeFactory {

    private MazeFactory() {
    }

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    public static final int BYTES_LENGTH = 16;

    public static Generator createGenerator(MazeGeneratorType type) {
        byte[] randomBytes = new byte[BYTES_LENGTH];
        SECURE_RANDOM.nextBytes(randomBytes);
        return switch (type) {
            case KRUSKAL -> new KruskalGenerator(randomBytes);
            case RECURSIVE_BACKTRACKING -> new RecursiveBacktrackingGenerator(randomBytes);
        };
    }

    public static Solver createSolver(MazeSolverType type) {
        return switch (type) {
            case BFS -> new BFSSolver();
            case HAND_ON_WALL -> new HandOnWallSolver();
        };
    }
}
