package course.Komelin.task9;


import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBlackList {
    static Set<String> blackList;
    static List<String> comments;

    static List<String> expectedNoTypos;

    static List<String> expectedWithTypos;

    @BeforeAll
    public static void initCommentsAndBlackList() {
        blackList = new HashSet<>(Set.of("плохоеслово1", "плохоеслово2", "плохоеслово3", "блин", "черт"));

        comments = new ArrayList<>(List.of("Как же вы уже меня все плохоеслово1",
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

        expectedNoTypos = new ArrayList<>(List.of("Как же вы уже меня все ************",
                "Да иди ты ************ ************ ************",
                "Вы все ************, ************ должно говорить громче",
                "Кто сдает мусорную лабу, тот - ************",
                "Да ты вообще плахоеслово1",
                "Как они плхоеслово3 это делают",
                "Вы вообще плхслв3",
                "Я тут вообще-то по делу говорю и маты не использую...",
                "",
                "              ",
                "****, как так можно"));

        expectedWithTypos = new ArrayList<>(List.of("Как же вы уже меня все ************",
                "Да иди ты ************ ************ ************",
                "Вы все ************, ************ должно говорить громче",
                "Кто сдает мусорную лабу, тот - ************",
                "Да ты вообще ************",
                "Как они *********** это делают",
                "Вы вообще плхслв3",
                "Я тут вообще-то по делу говорю и маты не использую...",
                "",
                "              ",
                "****, как так можно"));
    }

    @Test
    public void makeCommentsBlurredWithoutTypos() {
        BlackListFilter blackListFilter = new BlackListFilterWithoutTypos();
        List<String> testList = new ArrayList<>(comments);
        blackListFilter.filterComments(testList, blackList);
        assertEquals(expectedNoTypos, testList);
    }

    @Test
    public void makeCommentsBlurredWithTypos() {
        BlackListFilter blackListFilter = new BlackListFilterWithTypos();
        List<String> testList = new ArrayList<>(comments);
        blackListFilter.filterComments(testList, blackList);
        assertEquals(expectedWithTypos, testList);
    }
}
