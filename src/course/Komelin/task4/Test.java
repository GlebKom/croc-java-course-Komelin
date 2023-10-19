package course.Komelin.task4;

public class Test {
    public static void main(String[] args) {
        ArithmeticProgression arithmeticProgression = new ArithmeticProgression();

        try {
            int[] initializedArgs = initArgs(args);
            long arithmeticProgressionSum = arithmeticProgression.calculateProgression(initializedArgs[0],
                    initializedArgs[1], initializedArgs[2]);
            System.out.println("Сумма равна: " + arithmeticProgressionSum);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int[] initArgs(String[] args) throws NumberFormatException{
        int[] resultArray = new int[args.length];

        try {
            for (int i = 0; i < args.length; i++) {
                resultArray[i] = Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Введено не число!");
        }

        return resultArray;
    }
}
