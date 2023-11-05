package course.Komelin.task9.typo.checker;

public class TypoChecker {
    /**
     * Check if the string is a possible typo with given
     * acceptable count of typos. Uses algorithm to Levenshtein distance
     * for typo check.
     * <a href="https://en.wikipedia.org/wiki/Levenshtein_distance">Find more about this algorithm</a>
     *
     * @param possibleTypoWord possible typo word
     * @param reference check word
     * @param acceptableTypos how many typos are acceptable;
     *                        1 typos means that 1 letter was changed, deleted or added
     * @return true - if given word is a typo with given typos acceptable count; false - in other cases
     */

    public static boolean isTypoWord(String possibleTypoWord, String reference, int acceptableTypos) {
        if (acceptableTypos < 0) {
            throw new IllegalArgumentException("Acceptable Typos should be greater or equals 0");
        }

        return WagnerFischerAlgorithm.getLevenshteinDistance(possibleTypoWord, reference) <= acceptableTypos;
    }
}
