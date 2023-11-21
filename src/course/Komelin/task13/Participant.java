package course.Komelin.task13;

import java.util.Random;

public class Participant implements Runnable {
    private String name;
    private long balance;
    private Lot currentLot;

    public Participant(String name, long balance) {
        this.name = name;
        this.balance = balance;
    }

    public void makeBet(Lot lot) {
        if (lot.getCurrentPrice() < balance &&
                lot.isStarted() &&
                (lot.getProposerName() == null || !lot.getProposerName().equals(name))) {
            Random random = new Random();
            lot.bet(name, lot.getCurrentPrice() + random.nextLong(100, 1000));
        }
    }

    public void setCurrentLot(Lot currentLot) {
        this.currentLot = currentLot;
    }

    @Override
    public void run() {
        int count = 0;
        while (!currentLot.isEnded() && count < 100) {
            synchronized (currentLot) {
                makeBet(currentLot);
            }
            if (currentLot.isStarted())
                count++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
