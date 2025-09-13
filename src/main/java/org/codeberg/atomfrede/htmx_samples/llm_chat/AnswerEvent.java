package org.codeberg.atomfrede.htmx_samples.llm_chat;

import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public class AnswerEvent extends ApplicationEvent {

    private UUID streamIdentifier;
    private String answer;

    public AnswerEvent(Object eventSource, UUID streamIdentifier, String answer) {
        super(eventSource);
        this.streamIdentifier = streamIdentifier;
        this.answer = answer;
    }

    public UUID getStreamIdentifier() {
        return streamIdentifier;
    }

    public String getAnswer() {
        return answer;
    }
}
