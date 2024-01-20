package org.example.openai;

import java.util.ArrayList;

public class OpenAIRequest {

    public String model;
    public ArrayList<Message> messages = new ArrayList<>();
    public void addMessage(Message message) {
        this.messages.add(message);

    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "OpenAIRequest{" +
                "model='" + model + '\'' +
                ", messages=" + messages +
                '}';
    }
}