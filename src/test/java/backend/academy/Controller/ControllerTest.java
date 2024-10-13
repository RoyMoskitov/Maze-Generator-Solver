package backend.academy.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static backend.academy.Renderer.SimpleConsoleRenderer.ANSI_GREEN;
import static backend.academy.Renderer.SimpleConsoleRenderer.ANSI_RESET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {

    private ByteArrayOutputStream outputStream;
    private InputStream inputStream;
    private Controller controller;
    private PrintStream printStream;

    @BeforeEach
    public void SetUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @Test
    public void TestQuitOption() {
        inputStream = new ByteArrayInputStream("q\n".getBytes());
        controller = new Controller(printStream, inputStream);

        controller.processMazeSolving();
        String output = outputStream.toString();

        assertTrue(output.contains("Press \"Enter\" to create and solve maze or \"q\" to quit"));
    }

    @Test
    public void TestWrongGeneratorChoice() {
        inputStream = new ByteArrayInputStream("\nioefm\n1\n3\n3\n1\n1\n1\n1\n1\nq\n".getBytes());
        controller = new Controller(printStream, inputStream);

        controller.processMazeSolving();
        String output = outputStream.toString();
        List<String> lines = Arrays.asList(output.split(System.lineSeparator()));
        long occurrences = lines.stream()
            .filter(line -> line.equals("Choose option for maze generator type"))
            .count();

        assertEquals(occurrences, 2);
    }

    @Test
    public void TestWrongSizeChoice() {
        inputStream = new ByteArrayInputStream("\n1\n1\n3\n3\n1\n1\n1\n1\n1\nq\n".getBytes());
        controller = new Controller(printStream, inputStream);

        controller.processMazeSolving();
        String output = outputStream.toString();
        List<String> lines = Arrays.asList(output.split(System.lineSeparator()));
        long occurrences = lines.stream()
            .filter(line -> line.equals("Choose maze height"))
            .count();

        assertEquals(occurrences, 2);
    }

    @Test
    public void TestWrongStartCoordinateChoice() {
        inputStream = new ByteArrayInputStream("\n1\n3\n3\n1\n0\n1\n1\n1\n1\n1\nq\n".getBytes());
        controller = new Controller(printStream, inputStream);

        controller.processMazeSolving();
        String output = outputStream.toString();

        assertTrue(output.contains("start coordinate mustn't be a wall"));
    }

    @Test
    public void TestWrongEndCoordinateChoice() {
        inputStream = new ByteArrayInputStream("\n1\n3\n3\n1\n1\n1\n0\n1\n1\n1\nq\n".getBytes());
        controller = new Controller(printStream, inputStream);

        controller.processMazeSolving();
        String output = outputStream.toString();

        assertTrue(output.contains("end coordinate mustn't be a wall"));
    }

    @Test
    public void testNormalProgramProcessing() {
        inputStream = new ByteArrayInputStream("\n1\n3\n3\n1\n1\n1\n1\n1\nq\n".getBytes());
        controller = new Controller(printStream, inputStream);

        controller.processMazeSolving();

        String output = outputStream.toString();
        assertTrue(output.contains("#  #  #  "));
        assertTrue(output.contains("#  .  #  "));
        assertTrue(output.contains("#  " + ANSI_GREEN + "*  " + ANSI_RESET + "#  "));
    }
}
