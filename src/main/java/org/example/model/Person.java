package org.example.model;

public class Person extends Player {

    public Person(String name) {
        super(name);
    }

    @Override
    public int hashCode() {
        return getName().toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;

        return getName().equalsIgnoreCase(person.getName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "playerName='" + getName() + '\'' +
                ", playerHand=" + getHand() +
                ", playerBalance=" + getBalance() +
                '}';
    }
}