package org.example.model;

import org.example.OpenAiService;
import org.example.dao.AIService;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OpenAIBot extends Player {

    private final AIService openAIDao;

    Random random = new Random();

    public OpenAIBot(OpenAiService openAIDao) {



        this.setName("Bot " + random.nextInt(100));
        this.openAIDao = openAIDao;
    }

    public int getBetAmount() {

        return openAIDao.getBetAmount(getBalance());

    }

    public String hitOrStay(Hand dealerHand) {

        return openAIDao.askHitOrStay(getHand(), dealerHand);

    }
}