import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchingGame {
    private final ArrayList<String> data = new ArrayList<>();
    private Level level = null;
    public MatchingGame() {
        String filepath = System.getProperty("user.dir")
                + "\\src\\data\\Words.txt";
        loadData(filepath);
        System.out.println("Welcome to the Matching game!!!");
    }
    private void loadData(String filepath) {
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                this.data.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void run() {
        chooseLevel();
    }

    private void chooseLevel() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Choose your level: [0] - Easy, [1] - Hard");
            switch (scanner.nextInt()) {
                case 0 -> level = Level.EASY;
                case 1 -> level = Level.HARD;
                default -> System.out.println("There is no such level, try again");
            }
        } while (level == null);
    }
}
