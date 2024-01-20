package org.example.openai;

public class Choice {
    public Message message;
    public Object logprobs;
    public String finish_reason;
    public int index;

    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(Object logprobs) {
        this.logprobs = logprobs;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}