package course.Komelin.task6.figure;

public class Rectangle extends Figure{

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    public Rectangle(double x1, double y1, double x2, double y2) {
        if (x1 > x2 || y1 > y2) {
            throw new IllegalArgumentException();
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public boolean hasDot(int x, int y) {
        return (x >= x1) && (x <= x2) && (y >= y1) && (y <= y2);
    }

    @Override
    public String getCoords() {
        return "Rectangle (%.1f, %.1f), (%.1f, %.1f)".formatted(x1, y1, x2, y2);
    }

    @Override
    public void move(int dx, int dy) {
        this.x1 += dx;
        this.x2 += dx;
        this.y1 += dy;
        this.y2 += dy;
    }
}
