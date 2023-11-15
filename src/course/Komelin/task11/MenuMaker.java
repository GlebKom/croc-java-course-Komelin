package course.Komelin.task11;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MenuMaker {
    private final CookManager cookManager;

    public MenuMaker(CookManager cookManager) {
        Objects.requireNonNull(cookManager);
        this.cookManager = cookManager;
    }

    public List<Dish> getMenu(Collection<Cook> workingCooks, Collection<String> missingIngredients, int countOfDishes) {
        Objects.requireNonNull(workingCooks);
        Objects.requireNonNull(missingIngredients);
        return getFirstElemsWithSameOrder(getPossibleAndSortedDishes(workingCooks, missingIngredients), countOfDishes);
    }

    public List<Dish> getMenu(Collection<Cook> workingCooks,
                              Collection<String> missingIngredients,
                              int countOfDishes,
                              Predicate<Dish> filter) {
        Objects.requireNonNull(workingCooks);
        Objects.requireNonNull(missingIngredients);
        Objects.requireNonNull(filter);
        Set<Dish> allDishes = getPossibleAndSortedDishes(workingCooks, missingIngredients);
        return getFirstElemsWithSameOrder(allDishes.stream().filter(filter).collect(Collectors.toList()), countOfDishes);
    }

    private Set<Dish> getPossibleAndSortedDishes(Collection<Cook> workingCooks, Collection<String> missingIngredients) {
        TreeSet<Dish> sortedUniqueDishes = new TreeSet<>((dish1, dish2) -> dish1.getKingGrade() - dish2.getKingGrade() == 0 ?
                dish1.getCourtierGrade() - dish2.getCourtierGrade() : dish1.getKingGrade() - dish2.getKingGrade());

        Set<Dish> allDishesFromCooks = getAllDishesFromCooks(workingCooks);
        removeDishesWithMissingIngredients(allDishesFromCooks, missingIngredients);
        sortedUniqueDishes.addAll(allDishesFromCooks);

        return sortedUniqueDishes.descendingSet();
    }

    private Set<Dish> getAllDishesFromCooks(Collection<Cook> cooks) {
        Set<Dish> dishes = new HashSet<>();

        for (Cook cook : cooks) {
            cookManager.getCookDishes(cook).ifPresent(dishes::addAll);
        }

        return dishes;
    }

    private void removeDishesWithMissingIngredients(Collection<Dish> dishes, Collection<String> missingIngredients) {

        List<String> missingIngredientsCopy = new ArrayList<>(missingIngredients);
        missingIngredients.forEach(string -> missingIngredientsCopy.add(string.toUpperCase()));

        dishes.removeIf(dish -> {
            for (String ingredient : dish.getIngredients()) {
                if (missingIngredientsCopy.contains(ingredient)) {
                    return true;
                }
            }
            return false;
        });
    }

    private List<Dish> getFirstElemsWithSameOrder(Collection<Dish> dishes, int count) {
        List<Dish> result = new ArrayList<>();
        Iterator<Dish> iterator = dishes.iterator();
        int amount = 0;

        while (iterator.hasNext() && amount < count) {
            result.add(iterator.next());
            amount++;
        }

        return result;
    }
}
