package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.Constants.NUMBER_OF_DECKS;

/**
 * Collection of cards, a Deck contains a minimum of 52 cards, defaults to 312 cards
 */
public class Deck {

    private final List<Card> cardList = new ArrayList<>();
    private final Random random = new Random();

    public int getDeckSize() {

        return cardList.size();

    }

    /**
     * @return a random card and removes it from deck
     */
    public Card getRandomCard() {

        int cardIndex = random.nextInt(cardList.size());
        Card card = cardList.get(cardIndex);
        cardList.remove(cardIndex);

        return card;

    }

    /**
     * Sets up a new deck for the game made up of 1 or more 52 card decks
     */
    public void loadDeck() {

        List<String> suits = new ArrayList<>();
        suits.add("Clubs");
        suits.add("Spades");
        suits.add("Hearts");
        suits.add("Diamonds");

        //Face cards have a value of 10
        List<String> ranks = new ArrayList<>();
        ranks.add("1");
        ranks.add("2");
        ranks.add("3");
        ranks.add("4");
        ranks.add("5");
        ranks.add("6");
        ranks.add("7");
        ranks.add("8");
        ranks.add("9");
        ranks.add("10");
        ranks.add("10");
        ranks.add("10");
        ranks.add("10");

        //Create a certain number of decks, default is 6
        for (int k = 0; k < NUMBER_OF_DECKS; k++) {

            for (int i = 0; i < suits.size(); i++) {

                for (int j = 0; j < ranks.size(); j++) {

                    String cardName;

                    //Give face cards and ace a name to properly show them in hands
                    if (j == 0) {
                        cardName = "Ace";
                    } else if (j == 10) {
                        cardName = "Jack";
                    } else if (j == 11) {
                        cardName = "Queen";
                    } else if (j == 12) {
                        cardName = "King";
                    } else {
                        cardName = ranks.get(j);
                    }

                    cardList.add(new Card(cardName, Integer.parseInt(ranks.get(j)), suits.get(i)));

                }

            }

        }

    }

    @Override
    public String toString() {
        return "Deck{" +
                "cardList=" + cardList +
                ", random=" + random +
                '}';
    }
}