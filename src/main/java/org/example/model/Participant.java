package org.example.model;

public interface Participant {

    void addToParticipantBalance(int amount);

    Hand getHand();

    int getHandScore();

    int getParticipantBalance();

    String getParticipantName();

    String hitOrStay(Hand dealerHand);

    boolean isBot();

    void setUpStartingHand(Deck deck);

    void subtractFromParticipantBalance(int amount);
}