package course.Komelin.task5.stove.types;

public enum StoveTypes {
    GAS_STOVE("Газовая плита"),
    ELECTRIC_STOVE("Электрическая плита"),
    INDUCTION_STOVE("Индукционная плита");

    private String name;

    StoveTypes(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
