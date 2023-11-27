package course.Komelin.task13.auction;

import course.Komelin.task13.lot.Lot;
import course.Komelin.task13.participant.Participant;
import course.Komelin.task13.participant.handler.ParticipantHandlerTypes;

import java.time.LocalDateTime;
import java.util.List;

public class AutoBetsAuction extends Auction{
    @Override
    public void startAuction(Lot lot, LocalDateTime endingTime, List<Participant> participants) {

        if (endingTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Ending time is unreachable");
        }

        lot.setEndingTime(endingTime);
        AuctionProducer auctionProducer = new AuctionProducer(lot, participants, ParticipantHandlerTypes.AUTO);
        auctionProducer.startBets();
    }
}
