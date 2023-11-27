package course.Komelin.task13;

import course.Komelin.task13.auction.Auction;
import course.Komelin.task13.auction.AutoBetsAuction;
import course.Komelin.task13.lot.Lot;
import course.Komelin.task13.lot.LotFileReader;
import course.Komelin.task13.participant.Participant;
import course.Komelin.task13.participant.ParticipantFileReader;
import course.Komelin.task13.util.RandomBalanceSetter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
        List<Participant> participants =
                ParticipantFileReader.readParticipants(new File("src/course/Komelin/task13new/participants.txt"));

        RandomBalanceSetter.setRandomBalance(participants, 1000000, 10000000);

        Lot lot = LotFileReader.readLot(new File("src/course/Komelin/task13new/lot.txt"));

        Auction auction = new AutoBetsAuction();

        auction.startAuction(lot, LocalDateTime.now().plusSeconds(1), participants);

        while (lot.getWinner().isEmpty()) {

        }

        System.out.println("Winner is: " + lot.getWinner().get().getName() + ". Final price: " + lot.getCurrentPrice());
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IO exception occurred");
        }
    }
}
