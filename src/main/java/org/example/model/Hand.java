package org.example.model;

import java.util.ArrayList;
import java.util.List;

import static org.example.Constants.*;

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

    /**
     * @param dealerHand the object to be compared.
     * @return 1 if the player's hand wins, -1 if the player's hand loses, 0 if the hands tie
     * This function assumes that both hands are eligible hands for scoring (not busted)
     * If hand score goes beyond 21 it should not be eligible for comparison
     */
    @Override
    public int compareTo(Hand dealerHand) {

        int playerScore = this.getHandScore();
        int dealerScore = dealerHand.getHandScore();
        int playerCardCount = this.cardsInHand.size();
        int dealerCardCount = dealerHand.cardsInHand.size();

        boolean playerNaturalBlackjack = playerScore == HIGHEST_POSSIBLE_SCORE && playerCardCount == STARTING_HAND_SIZE;
        boolean dealerNaturalBlackjack = dealerScore == HIGHEST_POSSIBLE_SCORE && dealerCardCount == STARTING_HAND_SIZE;

        if (playerNaturalBlackjack && !dealerNaturalBlackjack) {
            return 1;
        } else if (!playerNaturalBlackjack && dealerNaturalBlackjack) {
            return -1;
        } else {
            if (playerScore > dealerScore) {
                return 1;
            } else if (playerScore < dealerScore) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public List<Card> getCardsInHand() {

        return cardsInHand;

    }

    /**
     * @return Hand score based on total score of cards in hand, adjusts for highest score that won't bust if hand has one or more Aces
     */
    public int getHandScore() {

        int numberOfAces = getNumberOfAces();
        int score = handScore;

        if (numberOfAces > 0) {
            int handScoreWithBigAce = handScore + 10;

            if (handScoreWithBigAce < MIN_BUST_VALUE) {
                score = handScoreWithBigAce;

            }
        }

        return score;
    }

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