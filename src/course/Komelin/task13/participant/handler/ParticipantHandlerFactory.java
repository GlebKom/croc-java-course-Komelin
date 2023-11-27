package course.Komelin.task13.participant.handler;

import course.Komelin.task13.auction.AuctionProducer;
import course.Komelin.task13.lot.Lot;
import course.Komelin.task13.participant.Participant;

public class ParticipantHandlerFactory {

    public static ParticipantHandler getParticipantHandler(ParticipantHandlerTypes type, Participant participant, Lot lot,
                                                    AuctionProducer auctionProducer) {
        return switch (type) {
            case AUTO -> new ParticipantHandlerWithAutoBet(participant, lot, auctionProducer);
        };
    }
}
