package course.Komelin.task13.auction;

import course.Komelin.task13.lot.Lot;
import course.Komelin.task13.participant.Participant;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Auction {

    public abstract void startAuction(Lot lot, LocalDateTime endingTime, List<Participant> participants);
}
