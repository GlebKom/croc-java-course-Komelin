package course.Komelin.task6.figure;

public class Circle extends Figure{

    private double x0;
    private double y0;
    private double radius;

    public Circle(double x0, double y0, double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException();
        }

        this.x0 = x0;
        this.y0 = y0;
        this.radius = radius;
    }

    @Override
    public void move(int dx, int dy) {
        this.x0 += dx;
        this.y0 += dx;
    }

    @Override
    public boolean hasDot(int x, int y) {
        return Math.pow(x - x0, 2) + Math.pow(y - y0, 2) <= Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return "Circle (%.1f, %.1f), %.1f".formatted(x0, y0, radius);
    }
}
