package course.Komelin.task13.participant.handler;

import course.Komelin.task13.auction.AuctionProducer;
import course.Komelin.task13.lot.Lot;
import course.Komelin.task13.participant.Participant;

import java.time.LocalDateTime;
import java.util.Random;

public class ParticipantHandlerWithAutoBet extends ParticipantHandler{
    ParticipantHandlerWithAutoBet(Participant participant, Lot lot, AuctionProducer auctionProducer) {
        super(participant, lot, auctionProducer);
    }

    private boolean betCouldBeMade(Lot lot, Participant participant) {
        return participant.getBalance() > lot.getCurrentPrice() && lot.getEndingTime().isAfter(LocalDateTime.now())
                && auctionProducer.auctionIsStarted() && lot.getLastProposer() != participant;
    }

    @Override
    public void run() {
        int countOfTries = 0;
        while (LocalDateTime.now().isBefore(lot.getEndingTime()) && participant.getBalance() > lot.getCurrentPrice()) {
            if (countOfTries < 100 && betCouldBeMade(lot, participant)) {
                synchronized (lot) {
                    int randomPriceAddition = new Random().nextInt(1000, 10000);
                    int newPrice = Math.min(randomPriceAddition + lot.getCurrentPrice(), participant.getBalance());
                    lot.bet(newPrice, participant);
                    countOfTries++;
                }
            }
        }
    }
}
