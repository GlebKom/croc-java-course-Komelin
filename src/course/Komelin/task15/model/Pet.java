package course.Komelin.task15.model;

import java.util.Objects;

public class Pet {

    private int medCardNumber;

    private String petName;

    private int age;

    private int ownerId;

    public Pet(int medCardNumber, String name, int age, int ownerId) {
        this.medCardNumber = medCardNumber;
        this.petName = name;
        this.age = age;
        this.ownerId = ownerId;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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
                ", ownerId=" + ownerId +
                '}';
    }
}
