package course.Komelin.task11;

import java.util.*;
import java.util.stream.Collectors;

public class CookManager {

    private final Map<Cook, Set<Dish>> cooksAndDishes = new HashMap<>();

    public void addCook(Cook cook) {
        if (!cooksAndDishes.containsKey(cook)) {
            cooksAndDishes.put(cook, new HashSet<>());
        } else {
            throw new IllegalArgumentException(cook + " is already working there");
        }
    }

    public void addCook(Cook cook, Collection<Dish> dishes) {
        if (!cooksAndDishes.containsKey(cook)) {
            cooksAndDishes.put(cook, new HashSet<>(dishes));
        } else {
            throw new IllegalArgumentException(cook + " is already working there");
        }
    }

    public void addCooks(Cook... cooks) {
        for (Cook cook : cooks) {
            addCook(cook);
        }
    }

    public void removeCook(Cook cook) {
        cooksAndDishes.remove(cook);
    }

    public void addDishToCook(Cook cook, Dish dish) {
        if (cooksAndDishes.get(cook) == null) {
            throw new RuntimeException("This cook is not working there");
        }
        cooksAndDishes.get(cook).add(dish);
    }

    public void addDishesToCook(Cook cook, Dish... dishes) {
        if (cooksAndDishes.get(cook) == null) {
            throw new RuntimeException("This cook is not working there");
        }
        cooksAndDishes.get(cook).addAll(Arrays.asList(dishes));
    }

    public void addDishesToManyCooks(Collection<Cook> cooks, Dish... dishes) {
        for (Cook cook : cooks) {
            if (!cooksAndDishes.containsKey(cook)) {
                cooksAndDishes.put(cook, new HashSet<>());
            }
            cooksAndDishes.get(cook).addAll(Arrays.asList(dishes));
        }
    }

    public void removeDishFromCook(Cook cook, Dish dish) {
        if (cooksAndDishes.get(cook) == null) {
            throw new RuntimeException("This cook is not working there");
        }
        cooksAndDishes.get(cook).remove(dish);
    }

    public Optional<Set<Dish>> getCookDishes(Cook cook) {
        if (cooksAndDishes.containsKey(cook)) {
            return Optional.of(cooksAndDishes.get(cook));
        }

        return Optional.empty();
    }
}
