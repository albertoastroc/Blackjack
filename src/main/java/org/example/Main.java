package org.example;


public class Main {

    public static void main(String[] args) {

//        RestTemplate restTemplate = new RestTemplate();
//        String apiKey = System.getenv("OpenAiApiKey");
//        OpenAiRequest postData = new OpenAiRequest();
//        OpenAiResponse response = new OpenAiResponse();

//        ChatService chatService = new ChatService();
//        chatService.sendRequest("What is the biggest known planet");


        GameCLI game = new GameCLI();
        game.run();

    }
}