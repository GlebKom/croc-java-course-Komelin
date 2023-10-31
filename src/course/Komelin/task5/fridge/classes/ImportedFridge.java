package course.Komelin.task5.fridge.classes;

import course.Komelin.task5.appliance.Imported;

public class ImportedFridge extends Fridge implements Imported {

    private String countryManufacturer;
    private boolean hasWarranty;

    public void setCountryManufacturer(String countryManufacturer) {
        this.countryManufacturer = countryManufacturer;
    }

    public void setHasWarranty(boolean hasWarranty) {
        this.hasWarranty = hasWarranty;
    }

    @Override
    public boolean isHasWarranty() {
        return hasWarranty;
    }

    @Override
    public String getCountryManufacturer() {
        return countryManufacturer;
    }
}
