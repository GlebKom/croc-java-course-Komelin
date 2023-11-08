package course.Komelin.task9;

import course.Komelin.task9.typo.checker.TypoChecker;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlackListFilterWithoutTypos implements BlackListFilter{
    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        comments.replaceAll(comment -> blurBannedWord(comment, blackList));
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
}
