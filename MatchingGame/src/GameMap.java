import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameMap {
    private ArrayList<List<String>> map = new ArrayList<>();
    private ArrayList<List<String>> answers = new ArrayList<>();

    public GameMap(String[] words) {
        prepareAnswers(words);
        prepareMap();
    }

    private void prepareAnswers(String[] words) {
        String[] both = Stream.concat(Arrays.stream(words), Arrays.stream(words))
                .toArray(String[]::new);
        Collections.shuffle(Arrays.asList(both));
        answers.add(Arrays.stream(both).limit(both.length / 2).collect(Collectors.toList()));
        answers.add(Arrays.stream(both).skip(both.length / 2).collect(Collectors.toList()));
    }
    private void prepareMap() {
        for (int i = 0; i < answers.size(); i++) {
            int size = answers.get(i).size();
            map.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                map.get(i).add("X");
            }
        }
    }

    public void printWords() {
        for (List<String> item : answers) {
            for (String s : item) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    public void printMap() {
        System.out.print("  ");
        for (int i = 0; i < map.get(0).size(); i++) {
            System.out.print(i + 1);
            System.out.print(' ');
        }
        System.out.println();
        for (int i = 0; i < map.size(); i++) {
            char letter = (char) (65 + i);
            System.out.print(letter);
            for (int j = 0; j < map.get(i).size(); j++) {

                System.out.print(" " + map.get(i).get(j));
            }
            System.out.println();
        }
    }

    public String update(int i, int j){
        map.get(i).set(j, answers.get(i).get(j));
        return answers.get(i).get(j);
    }

    public void cancelChoice(String word){
        for (List<String> strings : map) {
            for (int j = 0; j < strings.size(); j++) {
                if (strings.get(j).equals(word)) {
                    strings.set(j, "X");
                }
            }
        }
    }

    public boolean isAllUncovered(){
        boolean allUncovered = true;
        for (List<String> strings : map) {
            for (int j = 0; j < strings.size(); j++) {
                if (strings.get(j).equals("X")){
                    allUncovered = false;
                    break;
                }
            }
        }
        return allUncovered;
    }

    public int getColumnsNumber(){
        return map.get(0).size();
    }
}
