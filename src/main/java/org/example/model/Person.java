package org.example.model;

import static org.example.Constants.STARTING_HAND_SIZE;

public class Person extends Participant {

    private final String playerName;
    private Hand playerHand = new Hand();

    private int playerBalance;

    public Person(String playerName) {
        this.playerName = playerName;
        this.playerBalance = 5000;
    }

    public void addToParticipantBalance(int amount) {
        if (amount > 0) {
            playerBalance += amount;
        }
    }



    @Override
    public Hand getHand() {
        return playerHand;
    }

    @Override
    public int getHandScore() {
        return playerHand.getHandScore();
    }

    public int getParticipantBalance() {
        return playerBalance;
    }

    public String getParticipantName() {
        return playerName;
    }

    @Override
    public String hitOrStay(Hand dealerHand) {
        return null;
    }

    @Override
    public boolean isBot() {
        return false;
    }

    /**
     * Resets and deals new starting hand
     *
     * @param deck Deck to get cards from
     */
    @Override
    public void setUpStartingHand(Deck deck) {

        playerHand = new Hand();

        for (int j = 0; j < STARTING_HAND_SIZE; j++) {

            playerHand.addCardToHand(deck.getRandomCard());

        }
    }

    @Override
    public void subtractFromParticipantBalance(int amount) {
        if (amount > 0) {
            playerBalance -= amount;
        }
    }

    @Override
    public int hashCode() {
        return playerName.toLowerCase().hashCode();
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

        return playerName.equalsIgnoreCase(person.playerName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "playerName='" + playerName + '\'' +
                ", playerHand=" + playerHand +
                ", playerBalance=" + playerBalance +
                '}';
    }
}