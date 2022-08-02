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
        String[] both = Stream.concat(Arrays.stream(words), Arrays.stream(words))
                .toArray(String[]::new);
        Collections.shuffle(Arrays.asList(both));
        answers.add(Arrays.stream(both).limit(both.length / 2).collect(Collectors.toList()));
        answers.add(Arrays.stream(both).skip(both.length / 2).collect(Collectors.toList()));
    }
}
