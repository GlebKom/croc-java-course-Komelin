package course.Komelin.task9;

import course.Komelin.task9.typo.checker.TypoChecker;

import java.util.List;
import java.util.Set;

public class BlackListFilterWithTypos implements BlackListFilter{
    private int possibleTypos = 1;

    public int getPossibleTypos() {
        return possibleTypos;
    }

    public void setPossibleTypos(int possibleTypos) {
        this.possibleTypos = possibleTypos;
    }

    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        // про лямбда выражения еще не смотрели, но нашел такое решение в гугле,
        comments.replaceAll(comment -> blurBannedWord(comment, blackList, possibleTypos));
    }

    private String blurBannedWord(String comment, Set<String> blackList, int possibleTypos) {

        // небольшой костыль, чтобы не было аномалий со строкой, состоящей только из пробелов
        if (comment.split(" ").length == 0) {
            return comment;
        }

        // получаем список всех слов в комментарии
        String[] commentWords = comment.split(" ");

        // отдельно проходим по каждому слову
        for (int i = 0; i < commentWords.length; i++) {

            // если такое слово уже есть в запрещенном списке, то заблюрим его
            // нужно не забыть, что слово может прийти со знаком препинания, его нужно убрать при помощи регулярки
            //
            // если такого слова нет, то дальше итерируемся по списку запрещенных слов и проверяем на то, нет ли среди
            // них слова, отличающегося от слова в комментарии на заранее заданное количество символов

            String checkWord = commentWords[i].replaceAll("\\p{Punct}", "");

            if (blackList.contains(checkWord)) {
                commentWords[i] = commentWords[i].replaceFirst(checkWord, "*".repeat(checkWord.length()));
            } else {
                for (String banWord : blackList) {
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
