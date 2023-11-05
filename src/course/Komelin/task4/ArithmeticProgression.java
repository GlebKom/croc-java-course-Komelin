package course.Komelin.task4;

public class ArithmeticProgression {
    public long calculateProgression(int start, int difference, int cnt) throws IllegalArgumentException{
        long result = 0;

        if (cnt <= 0 || cnt > 10000) {
            throw new IllegalArgumentException("Количество членов А.П. должно быть в отрезке [1, 10000]");
        }

        if (difference < -10000 || difference > 10000) {
            throw new IllegalArgumentException("Разность А.П. должна быть в отрезке [-10000; 10000]");
        }

        if (start < -10000 || start > 10000) {
            throw new IllegalArgumentException("Начальный элемент должен быть в отрезке [-10000; 10000]");
        }

        for (int i = 0; i < cnt; i++) {
            result += start;
            start += difference;
        }

        return result;
    }
}