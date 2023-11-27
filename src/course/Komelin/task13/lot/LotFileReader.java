package course.Komelin.task13.lot;

import java.io.*;

public class LotFileReader {

    public static Lot readLot(File file) throws IOException, IllegalArgumentException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String lotName = reader.readLine();
            String priceAsString = reader.readLine();

            if (lotName == null || priceAsString == null) {
                throw new IllegalArgumentException("Lot file wasn't filled correctly");
            }

            int price = Integer.parseInt(priceAsString);

            return new Lot(lotName, price);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Price is not valid");
        }
    }
}
