package course.Komelin.task13.lot;

import course.Komelin.task13.participant.Participant;

import java.time.LocalDateTime;
import java.util.Optional;

public class Lot {

    private final String name;
    private volatile int currentPrice;
    private volatile LocalDateTime endingTime;
    private Participant lastProposer;

    public Lot(String name, int currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public synchronized void bet(int offeredPrice, Participant participant) {
        if (offeredPrice > currentPrice) {
            currentPrice = offeredPrice;
            lastProposer = participant;
        }
    }

    public void setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public Participant getLastProposer() {
        return lastProposer;
    }

    public Optional<Participant> getWinner() {
        if (LocalDateTime.now().isAfter(endingTime)) {
            return Optional.of(lastProposer);
        } else {
            return Optional.empty();
        }
    }
}
