package course.Komelin.task6;

import course.Komelin.task6.annotated.image.AnnotatedImage;
import course.Komelin.task6.annotation.Annotation;
import course.Komelin.task6.figure.Circle;
import course.Komelin.task6.figure.Rectangle;

public class Test {
    public static void main(String[] args) {

        Annotation annotation1 =
                new Annotation(new Circle(1, 3, 10),
                        "Здесь кенгуру");
        Annotation annotation2 =
                new Annotation(new Rectangle(3, 5, 6, 7),
                        "А здесь пингвин");
        Annotation annotation3 =
                new Annotation(new Circle(10, 71, 8),
                        "А здесь ученики КРОК пытаются учить джаву");
        Annotation annotation4 =
                new Annotation(new Rectangle(14, 51, 81, 90),
                        "А здесь Глеб очень хочет попасть на стажировку :3");

        AnnotatedImage annotatedImage =
                new AnnotatedImage("IMG_0", annotation1, annotation2, annotation3, annotation4);

        for (Annotation a : annotatedImage.getAnnotations()) {
            System.out.println(a);
        }

        System.out.println("\nПеремещаем аннотацию, где есть 'Глеб'");
        annotatedImage.findByLabel("Глеб").getFigure().move(3, 3);
        System.out.println(annotatedImage.findByLabel("Глеб") + "\n");

        System.out.println("Перемещаем аннотацию, где есть точка (17, 54)");
        annotatedImage.findByPoint(17, 54).getFigure().move(3, 3);
        System.out.println(annotatedImage.findByLabel("Глеб") + "\n");

        System.out.println("Пытаемся переместить аннотацию, содержащую точку (100, 100) через метод findByPoint");
        try {
            annotatedImage.findByPoint(100, 100).getFigure().move(10, 10);
            System.out.println("Все-таки получилось XD");
        } catch (NullPointerException e) {
            System.out.println("Натыкаемся на NPE :)\n");
        }

        System.out.println("А вот если будем использовать findByPointOptional, с таким будет столкнуться труднее");
        annotatedImage.findByPointOptional(100, 100).
                ifPresent(annotation -> annotation.getFigure().move(10, 10));
    }
}
