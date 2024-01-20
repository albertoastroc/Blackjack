package org.example;

import org.example.dao.AIService;
import org.example.model.Hand;
import org.example.openai.Message;
import org.example.openai.OpenAIRequest;
import org.example.openai.OpenAIResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.ConfigurableWebEnvironment;
import retrofit2.Response;

public class OpenAiService implements AIService {

    private static final String API_BASE_URL = "https://api.openai.com/v1/chat/completions";
    private static final String apiKey = System.getenv("OPENAI_API_KEY");
    private static final String model = "gpt-3.5-turbo";
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public String askBetAmount() {
        return null;
    }

    @Override
    public String askDoubleUp() {
        return null;
    }

    @Override
    public String askHitOrStay(Hand botHand, Hand dealerHand) {

        System.out.println(apiKey);

        String message = "Answer 'h' for hit or 's' for stay, do not answer in any other way, do not explain why you made your decision or give advice." +
                "If you are playing blackjack and your hand is made up of " + botHand.getCardsInHand().toString() + " and your hand score is " + botHand.getHandScore() + " and the dealer's up card is " + dealerHand.getCardsInHand().get(0)
                + "and the dealers upcard score is" + dealerHand.getHandScore() + " would you hit or stay?";

        HttpHeaders headers = createHeaders();
        HttpEntity<OpenAIRequest> entity = getOpenAIRequestHttpEntity(message, headers);

        try {

            ResponseEntity<OpenAIResponse> response = restTemplate.exchange(API_BASE_URL, HttpMethod.POST, entity, OpenAIResponse.class);
            if (response.getBody() != null){
                return response.getBody().choices.get(0).message.content;
            }
        } catch (ResourceAccessException e) {
            // This means there is some sort of network error
            System.out.println(e.getMessage());
        } catch (RestClientResponseException e) {
            if (e.getRawStatusCode() >= 500) {
                // Server can't handle the result (database fills up, server can't handle our message format)
                System.out.println("Server Error");
            } else if (e.getRawStatusCode() >= 400) {
                // Probably something wrong with our URL
                System.out.println("Client Error");
                System.out.println(e.getRawStatusCode());
                System.out.println(e.getMessage());
            }
        }
        return "Unable to get a response";

    }

    @Override
    public String askSplitHand() {
        return null;
    }

    private HttpHeaders createHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        return headers;

    }

    private HttpEntity<OpenAIRequest> getOpenAIRequestHttpEntity(String message, HttpHeaders headers) {
        Message systemRoleMessage = new Message();
        systemRoleMessage.setRole("system");
        systemRoleMessage.setContent("You are playing blackjack");

        Message systemUserMessage = new Message();
        systemUserMessage.setRole("user");
        systemUserMessage.setContent(message);

        OpenAIRequest openAIRequest = new OpenAIRequest();
        openAIRequest.setModel(model);
        openAIRequest.addMessage(systemRoleMessage);
        openAIRequest.addMessage(systemUserMessage);

        return new HttpEntity<>(openAIRequest, headers);
    }

}