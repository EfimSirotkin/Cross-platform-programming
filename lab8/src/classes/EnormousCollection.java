package classes;

import java.util.ArrayList;
import java.util.List;

public class EnormousCollection {
    public static List<Double> list = new ArrayList<>();

    public void populateList() {
        for (int i = 0; i < 1000000000; i++) {
            list.add(Math.random());
        }

    }
}
