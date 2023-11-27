package course.Komelin.task13.participant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantFileReader {

    public static List<Participant> readParticipants(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            List<Participant> participants = new ArrayList<>();
            String participantName;
            while ((participantName = reader.readLine()) != null) {
                participants.add(new Participant(participantName));
            }

            return participants;
        }
    }
}
