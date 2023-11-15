package course.Komelin.task11;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

public class Cook {
    private static int countOfCook = 0;
    private final int id;
    private String name;

    public Cook(String name) {
        this.id = ++countOfCook;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cook cook = (Cook) o;
        return id == cook.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
