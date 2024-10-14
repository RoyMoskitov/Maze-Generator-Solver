package backend.academy.Controller;

import backend.academy.Generator.Generator;
import backend.academy.Generator.Kruskal.KruskalGenerator;
import backend.academy.Generator.RecursiveBacktracker.RecursiveBacktrackingGenerator;
import backend.academy.Solver.BFS.BFSSolver;
import backend.academy.Solver.HandOnWall.HandOnWallSolver;
import backend.academy.Solver.Solver;
import java.security.SecureRandom;

/**
 * Utility class for maze generator and solver creating
 */
public class MazeFactory {

    private MazeFactory() {
    }

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    public static final int BYTES_LENGTH = 16;

    /**
     * function that returns maze generator
     * @param type enum value that defines generator type
     * @return maze generator object
     */
    public static Generator createGenerator(MazeGeneratorType type) {
        byte[] randomBytes = new byte[BYTES_LENGTH];
        SECURE_RANDOM.nextBytes(randomBytes);
        return switch (type) {
            case KRUSKAL -> new KruskalGenerator(randomBytes);
            case RECURSIVE_BACKTRACKING -> new RecursiveBacktrackingGenerator(randomBytes);
        };
    }

    /**
     * function that returns maze solver
     * @param type enum value that defines solver type
     * @return maze solver object
     */
    public static Solver createSolver(MazeSolverType type) {
        return switch (type) {
            case BFS -> new BFSSolver();
            case HAND_ON_WALL -> new HandOnWallSolver();
        };
    }
}
