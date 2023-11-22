package course.Komelin.task10;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFilter {
    static Set<String> blackList = new HashSet<>(Set.of("плохоеслово1", "плохоеслово2", "плохоеслово3", "блин", "черт"));

    static List<String> commentsAsString = new ArrayList<>(List.of(
            "Как же вы уже меня все плохоеслово1",
            "Да иди ты плохоеслово1 плохоеслово2 плохоеслово3",
            "Вы все плохоеслово3, плохоеслово3 должно говорить громче",
            "Кто сдает мусорную лабу, тот - плохоеслово1",
            "Да ты вообще плахоеслово1",
            "Как они плхоеслово3 это делают",
            "Вы вообще плхслв3",
            "Я тут вообще-то по делу говорю и маты не использую...",
            "",
            "              ",
            "черт, как так можно"));

    static List<Comment> commentsAsClass = new ArrayList<>(List.of(
            new Comment("Какие же все плохоеслово1", "Да иди ты плохоеслово1 плохоеслово2 плохоеслово3"),
            new Comment("Рецепт пирога от бабушки", "Просто нужно делать с любовью <3"),
            new Comment("Гневый отзыв от покупателя", "Консультанты просто плохоеслово2")
    ));

    static List<String> expectedCommentsAsString = new ArrayList<>(List.of(
            "Да ты вообще плахоеслово1",
            "Как они плхоеслово3 это делают",
            "Вы вообще плхслв3",
            "Я тут вообще-то по делу говорю и маты не использую...",
            "",
            "              "
    ));

    static List<Comment> expectedCommentsAsClass = new ArrayList<>(List.of(
            new Comment("Рецепт пирога от бабушки", "Просто нужно делать с любовью <3")
    ));

    static BlackListFilter<String> blackListFilterString = new BlackListFilter<>() {
    };

    static BlackListFilter<Comment> blackListFilterComment = new BlackListFilter<>() {
    };

    @Test
    public void testBlackListFilterWithCommentsAsString() {
        Iterable<String> result = blackListFilterString.filterComments((comment) -> {
            List<String> blackListCopy = blackList.stream().map(String::toUpperCase).toList();

            for (String word : blackListCopy) {
                if (comment.toUpperCase().contains(word)) {
                    return false;
                }
            }
            return true;
        }
        , commentsAsString );

        assertEquals(expectedCommentsAsString, result);
    }

    @Test
    public void testBlackListFilterWithCommentsAsClass() {
        Iterable<Comment> result = blackListFilterComment.filterComments((comment) -> {
            List<String> blackListCopy = blackList.stream().map(String::toUpperCase).toList();
            for (String word : blackListCopy) {
                if (comment.getContent().toUpperCase().contains(word)
                        || comment.getTitle().toUpperCase().contains(word)) {
                    return false;
                }
            }
            return true;
        }
        , commentsAsClass);

        Iterator<Comment> iterator1 = result.iterator();
        Iterator<Comment> iterator2 = expectedCommentsAsClass.iterator();

        assertEquals(expectedCommentsAsClass, result);
    }
}
