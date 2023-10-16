package course.Komelin.task3;

public class TwinNumberChecker {

    private SimpleNumberChecker simpleNumberChecker = new SimpleNumberChecker();

    public boolean isTwin(long number) {

        return simpleNumberChecker.isSimple(number) && (simpleNumberChecker.isSimple(number - 2) ||
                simpleNumberChecker.isSimple(number + 2));
    }
}
