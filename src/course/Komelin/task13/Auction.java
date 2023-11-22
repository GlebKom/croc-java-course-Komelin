package course.Komelin.task13;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Auction {
    private Lot lot;
    private List<Participant> participants;

    public Auction(File lot, File participants) throws IOException {
        this.lot = readLotFromFile(lot);
        this.participants = readParticipantsFromFile(participants);
    }

    public void startAuction() {
        makeAnAnnouncement(lot);
        prepareParticipants(participants);
        prepareLot(lot);
        checkPriceChangingOrTimeExceeding(lot);
    }

    private Lot readLotFromFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return new Lot(reader.readLine(), Long.parseLong(reader.readLine()), LocalDateTime.now().plusHours(1));
    }

    private List<Participant> readParticipantsFromFile(File file) throws IOException {
        List<Participant> participantList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String participantName;

        while ((participantName = reader.readLine()) != null) {
            participantList.add(new Participant(participantName, new Random().nextLong(10000000, 1000000000)));
        }

        participantList.forEach(participant -> participant.setCurrentLot(lot));
        return participantList;
    }

    private void prepareParticipants(List<Participant> participants) {
        participants.forEach(participant -> new Thread(participant).start());
    }

    private void prepareLot(Lot lot) {
        lot.startBargaining();
    }

    private void checkPriceChangingOrTimeExceeding(Lot lot) {
        new Thread(new PriceChangingOrTimeExceededChecker(lot)).start();
    }

    private void makeAnAnnouncement(Lot lot) {
        System.out.println("Торги по лоту: " + lot.getName() + " начались!");
        System.out.println("Начальная цена: " + lot.getCurrentPrice());
    }

    private static class PriceChangingOrTimeExceededChecker implements Runnable{
        Lot lot;
        private PriceChangingOrTimeExceededChecker(Lot lot) {
            this.lot = lot;
        }


        @Override
        public void run() {
            while (true) {
                long price = lot.getCurrentPrice();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (lot.getCurrentPrice() == price) {
                    synchronized (lot) {
                        lot.stopBargaining();
                        System.out.println("Торги окончены! Победитель: " + lot.getWinnerName());
                        System.out.println("Итоговая цена: " + lot.getCurrentPrice());
                        break;
                    }
                }
            }
        }
    }
}
