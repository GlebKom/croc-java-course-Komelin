package course.Komelin.task9.typo.checker;

public class WagnerFischerAlgorithm {

    /**
     * Wagner Fischer Algorithm of finding Levenshtein distance;
     * <a href="https://en.wikipedia.org/wiki/Levenshtein_distance">Find more about this algorithm</a>
     * @param x first word
     * @param y second word
     * @return count of actions required to get
     * the second word from the first word in the shortest possible way.
     * in this case, possible actions are delete, insert and replace
     */

    public static int getLevenshteinDistance(String x, String y) {

        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }
                else {
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j - 1] + m(x.charAt(i - 1), y.charAt(j - 1)), dp[i - 1][j] + 1),
                            dp[i][j - 1] + 1);
                }
            }
        }

        return dp[x.length()][y.length()];
    }

    private static int m(char firstChar, char secondChar) {
        return firstChar == secondChar ? 0 : 1;
    }
}
