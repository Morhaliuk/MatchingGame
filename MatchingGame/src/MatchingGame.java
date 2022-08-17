import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MatchingGame {
    private GameMap gameMap = null;
    private final ArrayList<String> data = new ArrayList<>();
    private Level level = null;
    private int lives = 0;
    private int choiceRow = -1;
    private int choiceCol = -1;
    public MatchingGame() {
        String filepath = System.getProperty("user.dir")
                + "\\MatchingGame\\src\\data\\Words.txt";
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
        prepareData();
        play();
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

    private void prepareData() {
        Collections.shuffle(data);
        if (level == Level.EASY) {
            setSettings(10, 4);
        } else {
            setSettings(15, 8);
        }
    }
    private void setSettings(int livesNum, int mapSize) {
        lives = livesNum;
        gameMap = new GameMap(data.stream().limit(mapSize).toArray(String[]::new));
    }

    private void play() {
        displayInterface();
        while (lives > 0){
            makeChoice();
            String prevChoice = gameMap.update(choiceRow, choiceCol);
            displayInterface();
            makeChoice();
            String nextChoice = gameMap.update(choiceRow, choiceCol);
            displayInterface();
            if (!nextChoice.equals(prevChoice)){
                gameMap.cancelChoice(nextChoice);
                gameMap.cancelChoice(prevChoice);
                displayInterface();
                lives--;
            }
            if(gameMap.isAllUncovered()){
                System.out.println("You win!");
                break;
            }
        }
        if (lives == 0){
            System.out.println("You lose!");
        }
    }

    private void makeChoice() {
        System.out.println("Provide row: ");
        Scanner scanner = new Scanner(System.in);
        do {
            switch (scanner.next().charAt(0)) {
                case 'A' -> choiceRow = 0;
                case 'B' -> choiceRow = 1;
                default -> System.out.println("Provide correct row!");
            }
        } while (choiceRow == -1);
        System.out.println("Provide column: ");
        do {
            choiceCol = scanner.nextInt();
            if (choiceCol < 1 || choiceCol > gameMap.getColumnsNumber()) {
                choiceCol = -1;
                System.out.println("Provide correct column number!");
            }
        } while (choiceCol == -1);
        choiceCol--;
    }

    private void displayInterface() {
        System.out.flush();
        System.out.println("Level: " + level);
        System.out.println("Guess chance: " + lives);
        //gameMap.printWords();
        gameMap.printMap();
    }
}
