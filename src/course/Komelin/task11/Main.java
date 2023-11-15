package course.Komelin.task11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    static CookManager cookManager;
    static MenuMaker menuMaker;

    static Cook cook1;
    static Cook cook2;
    static Cook cook3;
    static Cook cook4;
    static Cook cook5;
    static {
        cookManager = new CookManager();
        cook1 = new Cook("Аркадий");
        cook2 = new Cook("Иннокентий");
        cook3 = new Cook("Тетя Зина");
        cook4 = new Cook("Санёк");
        cook5 = new Cook("Ибрагим");

        cookManager.addCooks(cook1, cook2, cook3, cook4, cook5);

        Dish dish1 = Dish.getBuilder().
                setName("Шаверма").
                setDishCategory(DishCategory.HOT_DISH).
                setIngredients("курица", "помидоры", "лаваш", "огурцы", "капуста", "кетчуп", "майонез").
                setKingGrade(100).
                setCourtierGrade(100).
                setAverageTimeToCook(40).
                build();

        Dish dish2 = Dish.getBuilder().
                setName("Курица гриль").
                setDishCategory(DishCategory.HOT_DISH).
                setIngredients("курица", "масло", "базилик", "горчица", "соль").
                setKingGrade(75).
                setCourtierGrade(81).
                setAverageTimeToCook(140).
                build();

        Dish dish3 = Dish.getBuilder().
                setName("Салат оливье").
                setDishCategory(DishCategory.HOT_DISH).
                setIngredients("картофель", "колбаса", "майонез", "горошек", "яйца").
                setKingGrade(75).
                setCourtierGrade(91).
                setAverageTimeToCook(30).
                build();

        Dish dish4 = Dish.getBuilder().
                setName("Тирамису").
                setDishCategory(DishCategory.DESSERT).
                setIngredients("маскарпоне", "яйца", "сахарная пудра", "шоколад").
                setKingGrade(99).
                setCourtierGrade(90).
                setAverageTimeToCook(30).
                build();

        Dish dish5 = Dish.getBuilder().
                setName("Окрошка").
                setDishCategory(DishCategory.HOT_DISH).
                setIngredients("картофель", "колбаса", "сметана", "яйца", "укроп", "соль", "огурцы", "квас").
                setKingGrade(90).
                setCourtierGrade(82).
                setAverageTimeToCook(30).
                build();

        Dish dish6 = Dish.getBuilder().
                setName("Чипсы").
                setDishCategory(DishCategory.SNACK).
                setIngredients("картофель", "масло", "соль").
                setKingGrade(100).
                setCourtierGrade(67).
                setAverageTimeToCook(20).
                build();

        cookManager.addDishToCook(cook5, dish1);
        cookManager.addDishesToCook(cook1, dish3, dish2);
        cookManager.addDishToCook(cook2, dish4);
        cookManager.addDishToCook(cook3, dish5);
        cookManager.addDishToCook(cook4, dish6);

        menuMaker = new MenuMaker(cookManager);
    }

    public static void main(String[] args) {
        List<Dish> menu;
        List<String> missingIngredients = new ArrayList<>();

        System.out.println("Все повара работают и есть все ингредиенты:\n");
        List<Cook> workingCooks = new ArrayList<>(List.of(cook1, cook2, cook3, cook4, cook5));
        menu = menuMaker.getMenu(workingCooks, missingIngredients, 4);
        menu.forEach(System.out::println);
        System.out.println("----------------------------------------------------------");

        System.out.println("Пусть теперь нам нужно 3 блюда, а не 4");
        menu = menuMaker.getMenu(workingCooks, missingIngredients, 3);
        menu.forEach(System.out::println);
        System.out.println("----------------------------------------------------------");

        System.out.println("Не работает повар, который делает шаверму:\n");
        workingCooks.remove(cook5);
        menu = menuMaker.getMenu(workingCooks, missingIngredients, 4);
        menu.forEach(System.out::println);
        System.out.println("----------------------------------------------------------");

        workingCooks.add(cook5);

        System.out.println("На складе больше нет картошки:\n");
        missingIngredients.add("КАРТОФЕЛь"); // специально написал с маленькой на конце, чтобы показать отсутствие
        // зависимости от регистра
        menu = menuMaker.getMenu(workingCooks, missingIngredients, 4);
        menu.forEach(System.out::println);
        System.out.println("----------------------------------------------------------");

        System.out.println("Пусть теперь не будет работать повар, готовящий шаверму и на складе не будет картошки\n");
        workingCooks.remove(cook5);
        menu = menuMaker.getMenu(workingCooks, missingIngredients, 4);
        menu.forEach(System.out::println);
        System.out.println("----------------------------------------------------------");

        System.out.println("Вернем на склад картошку, но теперь представим, что король решил сесть на диету и убрать\n" +
                "из меню все десерты и снеки\n");
        missingIngredients.remove(0);
        menu = menuMaker.getMenu(workingCooks, missingIngredients, 4, dish ->
                !(dish.getDishCategory().equals(DishCategory.DESSERT) || dish.getDishCategory().equals(DishCategory.SNACK)));
        menu.forEach(System.out::println);
        System.out.println("----------------------------------------------------------");

        System.out.println("Вернем работника \"Ибрагим\", который делал нам шаверму. Теперь у поваров есть только\n" +
                "30 минут, чтобы сделать обед для короля\n");
        workingCooks.add(cook5);
        menu = menuMaker.getMenu(workingCooks, missingIngredients, 4, dish ->
                dish.getAverageTimeToCook() <= 30);
        menu.forEach(System.out::println);
        System.out.println("----------------------------------------------------------");

        System.out.println("Король оказался ужасным женоненавистником и уволил бедную тётю Зину, которая проработала\n" +
                "на него целых 30 лет (только она умела готовить окрошку)\n");
        cookManager.removeCook(cook3);
        menu = menuMaker.getMenu(workingCooks, missingIngredients, 4);
        menu.forEach(System.out::println);
        System.out.println("----------------------------------------------------------");

        System.out.println("""
                После визита в Японию король стал невероятно любить суши, поэтому он привез оттуда лучшего сушиста
                Японии и еще заставил каждого повара научиться готовить суши.
                Теперь любой случайный повар сможет их приготовить
                """);
        Dish sushi = Dish.getBuilder().
                setName("Суши").
                setIngredients("рыба", "рис", "нори").
                setDishCategory(DishCategory.HOT_DISH).
                setKingGrade(100).
                setCourtierGrade(10).
                setAverageTimeToCook(50).
                build();

        Cook sushiMaster = new Cook("Дзиро Оно");
        cookManager.addDishesToManyCooks(List.of(cook1, cook2, cook4, cook5, sushiMaster), sushi);

        Random random = new Random();
        workingCooks = List.of(List.of(cook1, cook2, cook4, cook5, sushiMaster).get(random.nextInt(5)));
        menu = menuMaker.getMenu(workingCooks, missingIngredients, 4);
        menu.forEach(System.out::println);
        System.out.println("----------------------------------------------------------");
    }
}
