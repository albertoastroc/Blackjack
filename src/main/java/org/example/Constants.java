package org.example;

public class Constants {

    /**
     * Starting hand size for Blackjack
     */
    public static int STARTING_HAND_SIZE = 2;

    /**
     * Best possible score
     */
    public static int HIGHEST_POSSIBLE_SCORE = 21;

    /**
     * Dealer has to stop at this value
     */
    public static int DEALER_STOP_SCORE = 17;

    /**
     * How many decks are used to build playing deck
     */
    public static int NUMBER_OF_DECKS = 6;

    /**
     * This value of greater is a bust for the hand
     */
    public static int MIN_BUST_VALUE = 22;

    /**
     * Value of when deck is at 20% of starting size
     */
    public static int MIN_PLAYABLE_DECK_SIZE = (int) ((52 * NUMBER_OF_DECKS) * .20);
}