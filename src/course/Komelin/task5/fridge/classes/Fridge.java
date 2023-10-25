package course.Komelin.task5.fridge.classes;

import course.Komelin.task5.appliance.Appliance;
import course.Komelin.task5.fridge.types.FridgeTypes;

public class Fridge extends Appliance {

    {
        setFridgeType(FridgeTypes.FRIDGE);
        setCamerasCount(2);
    }
    private boolean hasFreezer;

    private int camerasCount;

    private FridgeTypes fridgeType;

    private double freezerVolume;

    public double getFreezerVolume() {
        return freezerVolume;
    }

    public void setFreezerVolume(double freezerVolume) {
        this.freezerVolume = freezerVolume;
    }

    public void setFridgeType(FridgeTypes fridgeType) {
        this.fridgeType = fridgeType;
    }

    public void setHasFreezer(boolean hasFreezer) {
        this.hasFreezer = hasFreezer;
    }

    public int getCamerasCount() {
        return camerasCount;
    }

    public void setCamerasCount(int camerasCount) {
        this.camerasCount = camerasCount;
    }

    public boolean isHasFreezer() {
        return hasFreezer;
    }

    public FridgeTypes getFridgeType() {
        return fridgeType;
    }

    @Override
    protected String getSpecialDescription() {
        String message = """
                Название : %s %s
                Количество камер : %d
                Наличие морозильной камеры : %s
                """.formatted(fridgeType.getName(), getName(), getCamerasCount(),
                isHasFreezer() ? "Да" : "Нет");

        if (isHasFreezer() && getFreezerVolume() > 0) {
            message += "Объем морозильной камеры: %.2f л\n".formatted(getFreezerVolume());
        }

        return message;
    }

    @Override
    public String getDescription() {
        return getSpecialDescription() + getCommonDescription();
    }
}
