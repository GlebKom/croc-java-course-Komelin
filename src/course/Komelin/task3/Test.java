package course.Komelin.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class Test {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static SimpleNumberChecker simpleNumberChecker = new SimpleNumberChecker();
    static TwinNumberChecker twinNumberChecker = new TwinNumberChecker();

    public static void main(String[] args) throws IOException{
        int operationCode = operationChoose();

        try {
            long number = numberInput();
            switch (operationCode) {
                case 0:
                    System.out.println("Такой операции мы пока не придумали :(");
                    break;
                case 1:
                    System.out.println(simpleNumberChecker.isSimple(number) ?
                            "Число простое" : "Число составное");
                    break;
                case 2:
                    System.out.println(twinNumberChecker.isTwin(number) ?
                            "Это число-близнец" : "Это не число близнец");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ввода");
        } finally {
            reader.close();
        }
    }

    public static int operationChoose() throws IOException {
        System.out.print("Какую операцию хотите выбрать?\n" +
                "1 - проверить, является ли число простым\n" +
                "2 - проверить, является ли число числом-близнецом\n" +
                "Ввод: ");

        String operation = reader.readLine();

        switch (operation) {
            case "1" -> {
                return 1;
            } case "2" -> {
                return 2;
            } default -> {
                return 0;
            }
        }
    }

    public static long numberInput() throws IOException, NumberFormatException{
        System.out.print("Введите число: ");
        return Long.parseLong(reader.readLine());
    }
}
