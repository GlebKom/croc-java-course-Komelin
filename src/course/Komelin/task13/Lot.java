package course.Komelin.task13;

import java.time.LocalDateTime;

public class Lot {
    private String name;
    private long currentPrice;
    private String proposerName;
    private final LocalDateTime endingTime;
    private boolean isEnded;
    private boolean isStarted;

    public Lot(String name, long currentPrice, LocalDateTime endingTime) {
        this.currentPrice = currentPrice;
        this.name = name;
        this.endingTime = endingTime;
    }

    public void bet(String proposerName, long newPrice) {
        if (newPrice > currentPrice && LocalDateTime.now().isBefore(endingTime)) {
            this.currentPrice = newPrice;
            this.proposerName = proposerName;
            System.out.println("Новая ставка! " + proposerName + " поставил(a) " + currentPrice);
        }
    }

    public long getCurrentPrice() {
        return currentPrice;
    }

    public String getWinnerName() {
        return isEnded ? proposerName : null;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public String getProposerName() {
        return this.proposerName;
    }

    public void startBargaining() {
        this.isStarted = true;
    }

    public void stopBargaining() {
        this.isEnded = true;
    }

    public String getName() {
        return name;
    }
}
