package course.Komelin.task9;

import course.Komelin.task9.typo.checker.TypoChecker;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlackListFilterImpl implements BlackListFilter{
    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        comments.replaceAll(comment -> blurBannedWord(comment, blackList));
    }


    /**
     * From the given list of comments blurs ones
     * that contain words from the black list with possible count of typos.
     *
     * @param comments list of comments; every comment
     *                 is a sequence of words, separated
     *                 by spaces, punctuation or line breaks
     * @param blackList list of words that should not
     *                  be present in a comment
     * @param possibleTypos how many typos a word can have
     *                     compared to blacklisted words
     *
     */
    public void filterComments(List<String> comments, Set<String> blackList, int possibleTypos) {
        // про лямбда выражения еще не смотрели, но нашел такое решение в гугле,
        comments.replaceAll(comment -> blurBannedWord(comment, blackList, possibleTypos));
    }

    private String blurBannedWord(String comment, Set<String> blackList) {

        // проходимся по списку запрещенных слов и при помощи регулярки заменяем их в комментарии
        for (String banWord : blackList) {
            Pattern pattern = Pattern.compile(banWord, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(comment);
            comment = matcher.replaceAll("*".repeat(banWord.length()));
        }

        // вернем строку с заблюренными плохими словами
        return comment;
    }

    private String blurBannedWord(String comment, Set<String> blackList, int possibleTypos) {
        // получаем список всех слов в комментарии
        String[] commentWords = comment.split(" ");

        // отдельно проходим по каждому слову
        for (int i = 0; i < commentWords.length; i++) {

            // если такое слово уже есть в запрещенном списке, то заблюрим его
            // нужно не забыть, что слово может прийти со знаком препинания, его нужно убрать при помощи регулярки
            //
            // если такого слова нет, то дальше итерируемся по списку запрещенных слов и проверяем на то, нет ли среди
            // них слова, отличающегося от слова в комментарии на заранее заданное количество символов

            if (blackList.contains(commentWords[i].replaceAll("\\p{Punct}", ""))) {
                commentWords[i] = "*".repeat(commentWords[i].length());
            } else {
                for (String banWord : blackList) {
                    String checkWord = commentWords[i].replaceAll("\\p{Punct}", "");
                    if (TypoChecker.isTypoWord(checkWord, banWord, possibleTypos)) {
                        commentWords[i] = commentWords[i].replaceFirst(checkWord, "*".repeat(checkWord.length()));
                        break;
                    }
                }
            }
        }


        // обратно соберем строку и вернем ее.
        return String.join(" ", commentWords);
    }
}
