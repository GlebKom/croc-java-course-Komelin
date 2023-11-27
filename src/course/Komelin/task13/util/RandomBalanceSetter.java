package course.Komelin.task13.util;

import course.Komelin.task13.participant.Participant;

import java.util.List;
import java.util.Random;

public class RandomBalanceSetter {
    private static final Random random = new Random();

    public static void setRandomBalance(List<Participant> participants, int start, int finish) {

        participants.forEach(participant -> participant.setBalance(random.nextInt(start, finish)));
    }
}
