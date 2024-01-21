package org.example.model;

import org.example.dao.AIService;
import org.springframework.stereotype.Component;

import static org.example.Constants.STARTING_HAND_SIZE;

@Component
public class OpenAIBot extends Participant {

    private final AIService openAIDao;

    public OpenAIBot(AIService openAIDao) {
        this.openAIDao = openAIDao;
    }

    private final String botName = "Oddie";

    private Hand botHand;

    private int botBalance = 5000;

    @Override
    public void addToParticipantBalance(int amount) {
        botBalance += amount;
    }

    @Override
    public Hand getHand() {
        return botHand;
    }

    @Override
    public int getHandScore() {
        return botHand.getHandScore();
    }

    @Override
    public int getParticipantBalance() {
        return botBalance;
    }

    @Override
    public String getParticipantName() {
        return botName;
    }

    @Override
    public String hitOrStay(Hand dealerHand) {

        return openAIDao.askHitOrStay(botHand, dealerHand);

    }
    @Override
    public boolean isBot() {
        return true;
    }

    @Override
    public void setUpStartingHand(Deck deck) {

        botHand = new Hand();

        for (int j = 0; j < STARTING_HAND_SIZE; j++) {

            botHand.addCardToHand(deck.getRandomCard());

        }

    }

    @Override
    public void subtractFromParticipantBalance(int amount) {
        botBalance -= amount;
    }
}