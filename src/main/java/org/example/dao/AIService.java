package org.example.dao;

import org.example.model.Hand;

public interface AIService {

    String askDoubleUp();

    String askHitOrStay(Hand botHand, Hand dealerHand);

    String askSplitHand();

    int getBetAmount(int chipBalance);

}