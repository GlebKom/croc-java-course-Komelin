package course.Komelin.task5.stove.classes;

import course.Komelin.task5.appliance.Appliance;
import course.Komelin.task5.stove.types.StoveTypes;

public class Stove extends Appliance {

    {
        setStoveType(StoveTypes.GAS_STOVE);
        setBurnerCount(4);
    }

    private int burnerCount;
    private double burnerPower;
    private StoveTypes stoveType;

    public int getBurnerCount() {
        return burnerCount;
    }

    public void setBurnerCount(int burnerCount) {
        this.burnerCount = burnerCount;
    }

    public double getBurnerPower() {
        return burnerPower;
    }

    public void setBurnerPower(double burnerPower) {
        this.burnerPower = burnerPower;
    }

    public StoveTypes getStoveType() {
        return stoveType;
    }

    public void setStoveType(StoveTypes stoveType) {
        this.stoveType = stoveType;
    }

    @Override
    public String getDescription() {
        return """
                Название : %s %s
                Количество конфорок : %d
                Мощность конфорок : %.2f
                """.formatted(getStoveType().getName(), getName(), getBurnerCount(),
                getBurnerPower()) + super.getDescription();
    }

}
