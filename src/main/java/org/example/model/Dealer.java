package org.example.model;

public class Dealer {

    private boolean hidingCard = true;
    private Hand dealerHand = new Hand();

    /**
     * @return Returns dealer's hand depending on the dealer's card being hidden or not
     */
    public Hand getHand() {

        return dealerHand;

    }

    /**
     * @return Returns dealer's hand score depending on the dealer's card being hidden or not
     */
    public int getHandScore() {

        return dealerHand.getHandScore();

    }

    public boolean isHidingCard() {
        return hidingCard;
    }

    public void setHidingCard(boolean hidingCard) {
        this.hidingCard = hidingCard;
    }

    /**
     * Resets and deals new starting hand for the dealer, sets card hiding to true
     *
     * @param deck Deck to get cards from
     */
    public void setUpStartingHand(Deck deck) {

        dealerHand = new Hand();
        hidingCard = true;
        dealerHand.addCardToHand(deck.getRandomCard());

    }

    @Override
    public String toString() {
        return "Dealer{" +
                "hidingCard=" + hidingCard +
                ", dealerHand=" + dealerHand +
                '}';
    }
}