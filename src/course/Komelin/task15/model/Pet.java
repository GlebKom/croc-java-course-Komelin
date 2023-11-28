package course.Komelin.task15.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Pet {

    private int medCardNumber;

    private String petName;

    private int age;
    private List<Client> owners;

    public Pet() {
        this.owners = new ArrayList<>();
    }

    public Pet(int medCardNumber, String name, int age) {
        this.medCardNumber = medCardNumber;
        this.petName = name;
        this.age = age;
        this.owners = new ArrayList<>();
    }

    public int getMedCardNumber() {
        return medCardNumber;
    }

    public void setMedCardNumber(int medCardNumber) {
        this.medCardNumber = medCardNumber;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Client> getOwners() {
        return owners;
    }

    public void setOwners(List<Client> owners) {
        this.owners = owners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return medCardNumber == pet.medCardNumber && age == pet.age && Objects.equals(petName, pet.petName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medCardNumber);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "medCardNumber=" + medCardNumber +
                ", name='" + petName + '\'' +
                ", age=" + age +
                '}';
    }
}
