package org.codeberg.atomfrede.htmx_samples.llm_chat;

import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LLMChatService {
    Logger logger = LoggerFactory.getLogger(LLMChatService.class);
    private final Faker faker = new Faker();
    private ApplicationEventPublisher eventPublisher;

    public LLMChatService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    @Async
    public void handleLLMQuestion(QuestionEvent event) {

        logger.info("Received LLM Question Event");

        try {
            Thread.sleep(faker.random().nextInt(555, 3555));
            eventPublisher.publishEvent(new AnswerEvent(this, event.getStreamIdentifier(), faker.buffy().quotes()));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
