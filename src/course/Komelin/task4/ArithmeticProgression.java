package course.Komelin.task4;

public class ArithmeticProgression {

    public int calculateProgression(int start, int difference, int cnt) throws IllegalArgumentException{
        int result = 0;

        if (cnt <= 0) {
            throw new IllegalArgumentException("Количество членов А.П. должно быть больше 0");
        }

        for (int i = 0; i < cnt; i++) {
            result += start;
            start += difference;
        }

        return result;
    }
}