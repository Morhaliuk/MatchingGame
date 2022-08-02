import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchingGame {
    private final ArrayList<String> data = new ArrayList<>();
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
        System.out.println("Game Run");
    }
}
