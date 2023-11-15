package course.Komelin.task12;

import java.util.function.Function;
import java.util.function.Predicate;

public class TernaryOperator {

    public static <T1, T2, R> Function<T2, R> ternaryOperator(T1 condition,
                                                              Predicate<T1> predicate,
                                                              Function<T2, R> function1,
                                                              Function<T2, R> function2) {

        return predicate.test(condition) ? function1 : function2;
    }

    // Прошу меня простить за баловство, но это доставило мне искреннее удовольствие ахахха
    public static <ТИП1, ТИП2, РЕЗ> Функция<ТИП2, РЕЗ> тернарныйОператор(ТИП1 условие,
                                                                         Предикат<ТИП1> предикат,
                                                                         Функция<ТИП2, РЕЗ> функция1,
                                                                         Функция<ТИП2, РЕЗ> функция2) {

        return предикат.test(условие) ? функция1 : функция2;
    }
}
