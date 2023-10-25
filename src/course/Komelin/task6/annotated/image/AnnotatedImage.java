package course.Komelin.task6.annotated.image;

import course.Komelin.task6.annotation.Annotation;

import java.util.Optional;

public class AnnotatedImage {
    private final String imagePath;

    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    public Annotation findByPoint(int x, int y) {

        for (Annotation annotation : getAnnotations()) {
            if (annotation.getFigure().hasDot(x, y)) {
                return annotation;
            }
        }

        return null;
    }

    /* Просто ради интереса поверхностно изучил класс Optional, возможно такая реализация была бы поприкольнее, но следующий метод
    не имеет отношения к решаемой задаче. Здесь мы "защищаем" себя от NPE, так как аннотации с полученной точкой может и не быть
    */

    public Optional<Annotation> findByPointOptional(int x, int y) {
        for (Annotation a : getAnnotations()) {
            if (a.getFigure().hasDot(x, y)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    public Annotation findByLabel(String label) {
        for (Annotation a : getAnnotations()) {
            if (a.getLabel().contains(label)) {
                return a;
            }
        }

        return null;
    }


    // Аналогично с findByPoint, сделано только ради эксперимента, к решению поставленной задачи не относится
    public Optional<Annotation> findByLabelOptional(String label) {
        for (Annotation a : getAnnotations()) {
            if (a.getLabel().contains(label)) {
                return Optional.of(a);
            }
        }

        return Optional.empty();
    }
}
