package course.Komelin.task5.washingmachine.types;

public enum WashingMachineTypes {
    WASHING_MACHINE("Стиральная машина");

    private String name;

    WashingMachineTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
