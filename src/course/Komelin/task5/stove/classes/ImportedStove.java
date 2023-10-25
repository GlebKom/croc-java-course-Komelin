package course.Komelin.task5.stove.classes;

import course.Komelin.task5.appliance.Imported;

public class ImportedStove extends Stove implements Imported {
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
