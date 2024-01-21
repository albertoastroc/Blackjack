package org.example.dao;

import org.example.model.Hand;

public interface AIService {

    int getBetAmount(int chipBalance);

    String askDoubleUp();

    String askHitOrStay(Hand botHand, Hand dealerHand);

    String askSplitHand();

}