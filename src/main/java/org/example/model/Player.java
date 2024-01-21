package org.example.model;

import static org.example.Constants.STARTING_HAND_SIZE;

public abstract class Player {

    private String name;
    private Hand hand;
    private int balance = 5000;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
    }

    public void addToBalance(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getHandScore() {
        return hand.getHandScore();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Resets and deals new starting hand
     *
     * @param deck Deck to get cards from
     */
    public void setUpStartingHand(Deck deck) {
        hand = new Hand();

        for (int j = 0; j < STARTING_HAND_SIZE; j++) {

            hand.addCardToHand(deck.getRandomCard());

        }
    }

    public void subtractFromBalance(int amount) {
        if (amount > 0) {
            balance -= amount;
        }
    }

}