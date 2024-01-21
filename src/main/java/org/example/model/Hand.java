package org.example.model;

import java.util.ArrayList;
import java.util.List;

import static org.example.Constants.HIGHEST_POSSIBLE_SCORE;
import static org.example.Constants.MIN_BUST_VALUE;

/**
 * A Hand is made up of Card objects that add up to a Hand score
 */
public class Hand implements Comparable<Hand> {

    private final List<Card> cardsInHand = new ArrayList<>();
    private int handScore;

    public Hand() {
    }

    /**
     * Adds a card and it's score to the hand
     *
     * @param card card being added
     */
    public void addCardToHand(Card card) {
        cardsInHand.add(card);
        handScore += card.getCardScore();
    }

    @Override
    public int compareTo(Hand hand) {

        //Higher score wins
        if (this.getHandScore() > hand.getHandScore()) {
            return 1;

        }

        //Person wins if they have blackjack and dealer has 21 with 3 or more cards
        if (this.cardsInHand.size() == 2 && hand.cardsInHand.size() > 2
                && this.getHandScore() == HIGHEST_POSSIBLE_SCORE
                && hand.getHandScore() >= HIGHEST_POSSIBLE_SCORE
        ) {
            return 1;

        }

        //Tie if player and dealer have blackjack
        if (this.getHandScore() == hand.getHandScore() && this.cardsInHand.size() == hand.cardsInHand.size()) {
            return 0;

        }

        //Dealer wins if they have blackjack and player has 21 with 3 or more cards
        if (this.cardsInHand.size() > 2 && hand.cardsInHand.size() == 2
                && this.getHandScore() == HIGHEST_POSSIBLE_SCORE
                && hand.getHandScore() == HIGHEST_POSSIBLE_SCORE
        ) {
            return -1;

        }

        //Tie if score is the same no blackjacks involved
        if (this.getHandScore() == hand.getHandScore()) {
            return 0;

        }

        return -1;

    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    /**
     * @return Hand score based on total score of cards in hand, adjusts for highest score that won't bust if hand has one
     * Ace
     */
    public int getHandScore() {

        int numberOfAces = getNumberOfAces();

        int score = handScore;

        if (numberOfAces > 0) {

            //Check if hand with ace value 11 would bust
            int handScoreWithBigAce = handScore + 10;

            if (handScoreWithBigAce < MIN_BUST_VALUE) {

                score = handScoreWithBigAce;

            }
        }

        return score;
    }

    /**
     * @return Size of hand Used to decide winner if both players have natural 21s
     */
    public int getHandSize() {
        return cardsInHand.size();
    }

    /**
     * Uses faceCardName to find number of Aces
     *
     * @return Number of aces found
     * @see Card
     */
    public int getNumberOfAces() {

        return (int) cardsInHand.stream().filter(c -> c.getFaceCardName().equals("Ace")).count();

    }

    @Override
    public String toString() {
        return cardsInHand.toString();
    }

}