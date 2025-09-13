package org.codeberg.atomfrede.htmx_samples.llm_chat;

import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public class QuestionEvent extends ApplicationEvent {

    private UUID streamIdentifier;
    private String question;

    public QuestionEvent(Object source, UUID streamIdentifier, String question) {
        super(source);
        this.streamIdentifier = streamIdentifier;
        this.question = question;
    }

    public UUID getStreamIdentifier() {
        return streamIdentifier;
    }

    public String getQuestion() {
        return question;
    }
}
