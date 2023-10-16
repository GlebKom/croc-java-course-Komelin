package course.Komelin.task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test{

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static ArithmeticProgression arithmeticProgression = new ArithmeticProgression();

    public static void main(String[] args) throws IOException {

        try {
            System.out.print("Введите начальный элемент: ");
            int start = Integer.parseInt(reader.readLine());

            System.out.print("Введите разность арифметической прогрессии: ");
            int difference = Integer.parseInt(reader.readLine());

            System.out.print("Введите количество членов а.п.: ");
            int cnt = Integer.parseInt(reader.readLine());

            System.out.println("Сумма равна: " +
                    arithmeticProgression.calculateProgression(start, difference, cnt));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            reader.close();
        }
    }
}