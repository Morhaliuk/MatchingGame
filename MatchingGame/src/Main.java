import java.util.Scanner;

public class Main {
    private static String restartAnswer = "N";
    public static void main(String[] args) {
        MatchingGame game = new MatchingGame();
        do {
            game.run();
            prompt();
        } while (restartAnswer.equals("Y"));
    }

    private static void prompt() {
        System.out.println("Restart game? Y/N");
        Scanner scanner = new Scanner(System.in);
        do{
            restartAnswer = scanner.nextLine();
            switch (restartAnswer) {
                case "y", "Y" -> restartAnswer = "Y";
                case "n", "N" -> restartAnswer = "N";
                default -> System.out.println("Provide correct answer!");
            }
        } while (!restartAnswer.equals("Y") && !restartAnswer.equals("N"));
    }
}