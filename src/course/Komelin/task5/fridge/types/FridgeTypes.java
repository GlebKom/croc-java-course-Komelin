package course.Komelin.task5.fridge.types;

public enum FridgeTypes {
    MINI_FRIDGE("Мини-холодильник"),
    FRIDGE("Холодильник");

    private final String NAME;

    FridgeTypes(String name) {
        this.NAME = name;
    }

    public String getName() {
        return this.NAME;
    }
}
