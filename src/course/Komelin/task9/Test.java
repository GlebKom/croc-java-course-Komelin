package course.Komelin.task9;

import java.util.ArrayList;
import java.util.List;

public class Test {
    static List<String> list = new ArrayList<>();

    static {
        list.add("132");
        list.add("413");
    }

    public static void main(String[] args) {
        for (String s : list) {
            System.out.println("1");
            list.remove(s);
        }
    }
}
