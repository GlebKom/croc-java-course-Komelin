package course.Komelin.task13.participant;

import course.Komelin.task13.auction.AuctionProducer;
import course.Komelin.task13.lot.Lot;
import course.Komelin.task13.participant.handler.ParticipantHandlerFactory;
import course.Komelin.task13.participant.handler.ParticipantHandlerTypes;

import java.util.List;

public class ParticipantPreparer {


    public static void startParticipantsBets(List<Participant> participants, Lot lot, AuctionProducer auctionProducer,
                                             ParticipantHandlerTypes handlingType) {

        participants.forEach(participant -> new Thread(
                ParticipantHandlerFactory.getParticipantHandler(handlingType,
                        participant, lot, auctionProducer)).start());
    }
}
