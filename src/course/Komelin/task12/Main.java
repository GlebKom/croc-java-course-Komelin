package course.Komelin.task12;

import javax.sound.midi.Soundbank;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        System.out.println("""
                Для примера попробуем сделать тернарный оператор для гипотезы Коллатца
                Если число четное - делим его на два
                Если число нечетное - умножаем на 3 и прибавляем единицу
                """);

        Function<Integer, Integer> ifEven = (integer -> integer / 2);
        Function<Integer, Integer> ifNotEven = (integer -> integer * 3 + 1);
        Predicate<Integer> isEven = (integer -> integer % 2 == 0);

        System.out.print("Следующее число для числа 2: ");
        System.out.println(TernaryOperator.ternaryOperator(2, isEven, ifEven, ifNotEven).apply(2) + "\n");

        System.out.print("Следующее число для числа 3: ");
        System.out.println(TernaryOperator.ternaryOperator(3, isEven, ifEven, ifNotEven).apply(3) + "\n");

        System.out.print("Следующее число для числа 18: ");
        System.out.println(TernaryOperator.ternaryOperator(18, isEven, ifEven, ifNotEven).apply(18) + "\n");

        System.out.println("Теперь давайте посмотрим, что будет с числом 19, если последовательно выполнять этот алгоритм");
        int number = 19;
        int count = 0;
        while (number != 1) {
            Function<Integer, Integer> whatToDo = TernaryOperator.ternaryOperator(number, isEven, ifEven, ifNotEven);
            System.out.println("Число №" + ++count + ": " + number);
            number = whatToDo.apply(number);
        }
        System.out.println("Число №" + ++count + ": " + number + "\n");

        System.out.println("Далее проверять не стоит, так как дальше будет просто '4, 2, 1, 4, 2, 1...'");
        System.out.println("По гипотезе Коллатца любое число, если следовать этому алгоритму, придет в цикл 4, 2, 1");
        System.out.println("Гипотеза сформулирована еще в 1932 году. Несмотря на простоту своей формулировки, является все еще недоказанной!");
        System.out.println("""
                По состоянию на апрель 2021 года проверены все натуральные числа до 9 789 690 303 392 599 179 037 включительно,
                и каждое из них продемонстрировало соответствие гипотезе Коллатца.
                 """);
    }
}
