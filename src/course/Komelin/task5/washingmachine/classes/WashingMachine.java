package course.Komelin.task5.washingmachine.classes;

import course.Komelin.task5.appliance.Appliance;
import course.Komelin.task5.washingmachine.types.WashingMachineTypes;

public class WashingMachine extends Appliance {

    {
        setWashingMachineType(WashingMachineTypes.WASHING_MACHINE);
    }
    private double volume;

    private double maxLoading;

    private double hasDryer;

    private WashingMachineTypes washingMachineType;

    public double getHasDryer() {
        return hasDryer;
    }

    protected void setHasDryer(double hasDryer) {
        this.hasDryer = hasDryer;
    }

    public WashingMachineTypes getWashingMachineType() {
        return washingMachineType;
    }

    public void setWashingMachineType(WashingMachineTypes washingMachineType) {
        this.washingMachineType = washingMachineType;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getMaxLoading() {
        return maxLoading;
    }

    public void setMaxLoading(double maxLoading) {
        this.maxLoading = maxLoading;
    }

    @Override
    public String getDescription() {
        return """
                Название : %s %s
                Объем барабана : %.2f л
                Максимальная загрузка белья : %.2f кг
                """.formatted(getWashingMachineType().getName(), getName(),
                this.getVolume(), this.getMaxLoading()) + super.getDescription();
    }
}
