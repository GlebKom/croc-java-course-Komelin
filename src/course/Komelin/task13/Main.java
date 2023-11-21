package course.Komelin.task13;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Auction auction = new Auction(new File("src/course/Komelin/task13/lot.txt"),
                new File("src/course/Komelin/task13/participants.txt"));

        auction.startAuction();
    }
}
