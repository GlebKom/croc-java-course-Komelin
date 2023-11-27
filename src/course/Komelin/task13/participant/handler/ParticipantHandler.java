package course.Komelin.task13.participant.handler;

import course.Komelin.task13.auction.AuctionProducer;
import course.Komelin.task13.lot.Lot;
import course.Komelin.task13.participant.Participant;

public abstract class ParticipantHandler implements Runnable{

    protected final Participant participant;
    protected final Lot lot;
    protected final AuctionProducer auctionProducer;
    ParticipantHandler(Participant participant, Lot lot, AuctionProducer auctionProducer) {
        this.participant = participant;
        this.lot = lot;
        this.auctionProducer = auctionProducer;
    }
}
