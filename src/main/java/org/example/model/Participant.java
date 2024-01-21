package org.example.model;

public abstract class Participant {

    private String name;

    private Hand hand;

    private int balance;

    void addToParticipantBalance(int amount) {

    }

    Hand getHand();

    int getHandScore();

    int getParticipantBalance();

    String getParticipantName();

    String hitOrStay(Hand dealerHand);

    boolean isBot();

    void setUpStartingHand(Deck deck);

    void subtractFromParticipantBalance(int amount);

}