package javaOOP;

public class Dog extends Animals {
    public static void main(String[] args) {
        Dog dog = new Dog();

        System.out.println(dog.animalAge);

        dog.setAnimalName("Huskey");

        System.out.println(dog.getAnimalName());
    }
}
