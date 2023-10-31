package course.Komelin.task5;

import course.Komelin.task5.appliance.Appliance;
import course.Komelin.task5.appliance.Imported;
import course.Komelin.task5.fridge.classes.Fridge;
import course.Komelin.task5.fridge.classes.ImportedFridge;
import course.Komelin.task5.fridge.types.FridgeTypes;
import course.Komelin.task5.stove.classes.ImportedStove;
import course.Komelin.task5.stove.classes.Stove;
import course.Komelin.task5.stove.types.StoveTypes;
import course.Komelin.task5.washingmachine.classes.WashingMachine;

import java.util.ArrayList;
import java.util.List;

public class Test {

    static List<Appliance> applianceList = new ArrayList<>();

    public static void main(String[] args) {
        printDescription(applianceList);
    }

    static {
        Fridge fridge = new Fridge();
        fridge.setName("Liebherr CNsff 5703-20 001");
        fridge.setColor("Серебристый");
        fridge.setHeight(201.5);
        fridge.setHasFreezer(true);
        fridge.setFridgeType(FridgeTypes.MINI_FRIDGE);
        fridge.setWidth(59.7);
        fridge.setDepth(67.5);
        fridge.setWeight(52);
        fridge.setCamerasCount(2);
        fridge.setPrice(10000);
        fridge.setFreezerVolume(15);

        ImportedFridge importedFridge = new ImportedFridge();
        importedFridge.setName("Gorenje NRR9185EAXL");
        importedFridge.setWeight(85);
        importedFridge.setHeight(178.6);
        importedFridge.setWidth(91);
        importedFridge.setDepth(64.3);
        importedFridge.setPrice(83999);
        importedFridge.setHasFreezer(true);
        importedFridge.setFreezerVolume(223);
        importedFridge.setCamerasCount(2);
        importedFridge.setColor("Серебристый металлик");
        importedFridge.setCountryManufacturer("Сербия");
        importedFridge.setHasWarranty(true);

        Stove gasStove = new Stove();
        gasStove.setName("GEFEST 6500-03 0045");
        gasStove.setColor("Коричневый");
        gasStove.setWeight(50.2);
        gasStove.setHeight(85);
        gasStove.setWidth(60);
        gasStove.setDepth(60);
        gasStove.setPrice(41325);
        gasStove.setStoveType(StoveTypes.GAS_STOVE);
        gasStove.setBurnerCount(4);
        gasStove.setBurnerPower(3.2);

        applianceList.add(gasStove);
        applianceList.add(importedFridge);
        applianceList.add(fridge);
    }

    public static void printDescription(List<Appliance> appliances) {
        for (Appliance appliance : appliances) {
            if (appliance instanceof Imported imported) {
                System.out.println("Страна производитель : " + imported.getCountryManufacturer());
                System.out.println("Наличие гарантии : " + (imported.isHasWarranty() ? "Да" : "Нет"));
            }
            System.out.println(appliance.getDescription());
        }
    }


}
