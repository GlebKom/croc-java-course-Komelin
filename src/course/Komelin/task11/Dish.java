package course.Komelin.task11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dish {

    private String name;
    private DishCategory dishCategory;
    private List<String> ingredients;
    private int kingGrade;
    private int courtierGrade;
    private int averageTimeToCook;

    private Dish(){
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public int getKingGrade() {
        return kingGrade;
    }

    public int getCourtierGrade() {
        return courtierGrade;
    }

    public int getAverageTimeToCook() {
        return averageTimeToCook;
    }

    public static Builder getBuilder(){
        return new Dish().new Builder();
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' + "\n" +
                ", dishCategory=" + dishCategory + "\n" +
                ", ingredients=" + ingredients + "\n" +
                ", kingGrade=" + kingGrade + "\n" +
                ", courtierGrade=" + courtierGrade + "\n" +
                ", averageTimeToCook=" + averageTimeToCook + " минут" + "\n";
    }

    public class Builder {
        private Builder(){}

        public Builder setName(String name) {
            Dish.this.name = name;
            return this;
        }

        public Builder setDishCategory(DishCategory dishCategory) {
            Dish.this.dishCategory = dishCategory;
            return this;
        }

        public Builder setIngredients(String... ingredients) {
            for (String ingredient : ingredients) {
                Dish.this.ingredients.add(ingredient.toUpperCase());
            }

            return this;
        }

        public Builder addIngredients(String ingredient) {
            Dish.this.ingredients.add(ingredient.toUpperCase());
            return this;
        }

        public Builder setKingGrade(int grade) {
            if (grade < 0 || grade > 100) {
                throw new IllegalArgumentException("King grade should be in [0, 100]");
            }

            Dish.this.kingGrade = grade;
            return this;
        }

        public Builder setCourtierGrade(int grade) {
            if (grade < 0 || grade > 100) {
                throw new IllegalArgumentException("King grade should be in [0, 100]");
            }

            Dish.this.courtierGrade = grade;
            return this;
        }

        public Builder setAverageTimeToCook(int time) {
            if (time < 0) {
                throw new IllegalArgumentException("Time to cooke should be more than 0");
            }

            Dish.this.averageTimeToCook = time;
            return this;
        }

        public Dish build() {
            return Dish.this;
        }
    }
}
