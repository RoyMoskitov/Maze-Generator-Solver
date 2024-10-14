package backend.academy.Controller;

import backend.academy.Generator.Generator;
import backend.academy.Generator.Kruskal.KruskalGenerator;
import backend.academy.Maze.Cell;
import backend.academy.Maze.Coordinate;
import backend.academy.Maze.Maze;
import backend.academy.Renderer.Renderer;
import backend.academy.Renderer.SimpleConsoleRenderer;
import backend.academy.Solver.Solver;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import static org.apache.commons.validator.GenericValidator.isInt;

/**
 * Controller of maze creation and solving process
 */
public class Controller {
    private final PrintStream output;
    private final Scanner scannerInput;
    private final Renderer renderer;

    public Controller(PrintStream output, InputStream input) {
        this.output = output;
        this.scannerInput = new Scanner(input, StandardCharsets.UTF_8);
        renderer = new SimpleConsoleRenderer();
    }

    /**
     * main function that starts the application
     */
    public void processMazeSolving() {
        while (true) {
            while (true) {
                output.println("Press \"Enter\" to create and solve maze or \"q\" to quit: ");
                String input = scannerInput.nextLine();
                if ("q".equals(input)) {
                    return;
                }
                if ("".equals(input)) {
                    break;
                }
                output.println("Incorrect input");
            }
            Maze maze = createMaze();
            output.println(renderer.render(maze));
            Solver solver = createSolver();
            Coordinate start = chooseCoordinate(maze, "start coordinate");
            Coordinate end = chooseCoordinate(maze, "end coordinate");
            output.println(renderer.render(maze, solver.solve(maze, start, end)));
        }
    }

    /**
     * function that returns a coordinate depending on user input
     * @param maze maze
     * @param type name of requested coordinate
     * @return coordinate
     */
    private Coordinate chooseCoordinate(Maze maze, String type) {
        int height;
        int width;
        while (true) {
            height = chooseNumber(0, maze.height() - 1, type + " height");
            width = chooseNumber(0, maze.width() - 1, type + " width");
            if (maze.grid()[height][width].type() != Cell.Type.WALL) {
                break;
            }
            output.println(type + " mustn't be a wall");
        }
        return new Coordinate(height, width);
    }

    /**
     * function that returns one enum option depending on user input
     * @param clazz enum to choose from
     * @return one of enum values
     * @param <T> any enum class
     */
    private <T extends Enum<T>> T chooseEnumOption(Class<T> clazz) {
        String choice;
        T[] enumConstants = clazz.getEnumConstants();
        do {
            output.println("Choose option for "
                + clazz.getSimpleName().replaceAll("([A-Z])", " $1").toLowerCase().trim());
            output.println("Number should be between 1 and " + enumConstants.length);
            for (int i = 0; i < enumConstants.length; i++) {
                output.println((i + 1) + ". " + enumConstants[i]);
            }
            choice = scannerInput.nextLine();
        } while (!isInt(choice) || Integer.parseInt(choice) < 1
            || Integer.parseInt(choice) > enumConstants.length);
        return enumConstants[Integer.parseInt(choice) - 1];
    }

    /**
     * function for maze creating
     * @return maze
     */
    private Maze createMaze() {
        Generator generator = MazeFactory.createGenerator(chooseEnumOption(MazeGeneratorType.class));
        if (generator.getClass() == KruskalGenerator.class) {
            output.println("Even numbers for height and width are not recommended");
        }
        int height = chooseNumber(Generator.MIN_MAZE_HEIGHT, Generator.MAX_MAZE_HEIGHT, "maze height");
        int width = chooseNumber(Generator.MIN_MAZE_WIDTH, Generator.MAX_MAZE_WIDTH, "maze width");
        return generator.generate(height, width);
    }

    /**
     * function for maze solver creating
     * @return maze solver
     */
    private Solver createSolver() {
        return MazeFactory.createSolver(chooseEnumOption(MazeSolverType.class));
    }

    /**
     * function that returns integer number depending on user input
     * @param min lower bound
     * @param max upper bound
     * @param type requested parameter name
     * @return chosen number
     */
    private int chooseNumber(int min, int max, String type) {
        String choice;
        do {
            output.println("Choose " + type);
            output.println(type + " should be between " + min + " and " + max);
            choice = scannerInput.nextLine();
        } while (!isInt(choice) || Integer.parseInt(choice) < min
            || Integer.parseInt(choice) > max);
        return Integer.parseInt(choice);
    }
}
