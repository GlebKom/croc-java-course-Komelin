package course.Komelin.task6.annotation;

import course.Komelin.task6.figure.Figure;

public class Annotation {

    private final Figure figure;
    private final String label;

    public Annotation(Figure figure, String sign) {
        this.figure = figure;
        this.label = sign;
    }

    public Figure getFigure() {
        return this.figure;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return figure.getCoords() + ": " + label;
    }
}
