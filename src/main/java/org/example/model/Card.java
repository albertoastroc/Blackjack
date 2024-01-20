package org.example.model;

/**
 * Cards are used to create hands, cards have a score value and name associated with them
 *
 * @property faceCardName : String represantion of the card's name Ex. Ace, King, 9
 */

public class Card {

    private final String faceCardName;

    private final int cardScore;
    private final String cardSuit;

    public Card(String faceCardName, int cardScore, String cardSuit) {
        this.faceCardName = faceCardName;
        this.cardScore = cardScore;
        this.cardSuit = cardSuit;
    }

    public int getCardScore() {
        return cardScore;
    }

    public String getFaceCardName() {
        return faceCardName;
    }

    @Override
    public String toString() {
        return faceCardName + " of " + cardSuit;
    }
}