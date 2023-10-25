package course.Komelin.task5.appliance;

public abstract class Appliance {

    private String name;

    private int price;

    private double weight;

    private double height;

    private double width;

    private double depth;

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String getCommonDescription() {
        return """
                Цвет : %s
                Вес : %.2f
                Высота : %.2f см
                Ширина : %.2f см
                Глубина : %.2f см
                
                Цена: %d рублей
                
                """.formatted(getColor(), getWeight(), getHeight(), getWidth(),
                getDepth(), getPrice());
    }

    protected abstract String getSpecialDescription();

    public abstract String getDescription();

}
