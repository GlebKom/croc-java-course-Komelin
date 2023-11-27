package course.Komelin.task13.auction;

import course.Komelin.task13.lot.Lot;
import course.Komelin.task13.participant.Participant;
import course.Komelin.task13.participant.ParticipantPreparer;
import course.Komelin.task13.participant.handler.ParticipantHandlerTypes;

import java.util.List;

public class AuctionProducer {

    private final Lot lot;
    private final List<Participant> participants;
    private boolean auctionIsStarted;
    private final ParticipantHandlerTypes handlingType;

    public AuctionProducer(Lot lot, List<Participant> participants, ParticipantHandlerTypes handlingType) {
        this.lot = lot;
        this.participants = participants;
        this.handlingType = handlingType;
    }

    public boolean auctionIsStarted() {
        return auctionIsStarted;
    }

    public void startBets() {
        ParticipantPreparer.startParticipantsBets(participants, lot, this, handlingType);
        auctionIsStarted = true;
    }
}
