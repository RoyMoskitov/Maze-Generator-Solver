package backend.academy;

import backend.academy.Controller.Controller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(System.out, System.in);
        controller.processMazeSolving();
    }
}
