package backend.academy.Generator;

public class GenerationValidator {

    private GenerationValidator() {
    }

    /**
     * checks whether height and width are within boundaries
     * @param height height
     * @param width width
     */
    public static void validate(int height, int width) {
        StringBuilder errorMessage = new StringBuilder();
        if (height < Generator.MIN_MAZE_HEIGHT) {
            errorMessage.append(String.format("Height must be greater than %d%n", (Generator.MIN_MAZE_HEIGHT - 1)));
        } else if (height > Generator.MAX_MAZE_HEIGHT) {
            errorMessage.append(String.format("Height must be less than %d%n", (Generator.MAX_MAZE_HEIGHT - 1)));
        }
        if (width < Generator.MIN_MAZE_WIDTH) {
            errorMessage.append(String.format("Width must be greater than %d%n", (Generator.MIN_MAZE_WIDTH - 1)));
        } else if (width > Generator.MAX_MAZE_WIDTH) {
            errorMessage.append(String.format("Width must be less than %d%n", (Generator.MAX_MAZE_WIDTH - 1)));
        }
        if (errorMessage.length() != 0) {
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }
}
