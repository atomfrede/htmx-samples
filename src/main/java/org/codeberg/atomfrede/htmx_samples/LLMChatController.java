package org.codeberg.atomfrede.htmx_samples;

import gg.jte.TemplateEngine;
import gg.jte.TemplateOutput;
import gg.jte.output.StringOutput;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import org.codeberg.atomfrede.htmx_samples.llm_chat.AnswerEvent;
import org.codeberg.atomfrede.htmx_samples.llm_chat.QuestionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.view.FragmentsRendering;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@RequestMapping("llm-chat")
public class LLMChatController {

    Logger logger = LoggerFactory.getLogger(LLMChatController.class);
    private final Map<UUID, SseEmitter> emitters = new HashMap<>();
    private ApplicationEventPublisher eventPublisher;
    private TemplateEngine templateEngine;

    public LLMChatController(ApplicationEventPublisher eventPublisher, TemplateEngine templateEngine) {
        this.eventPublisher = eventPublisher;
        this.templateEngine = templateEngine;
    }

    @GetMapping
    public String index(){
        return "chat/index";
    }

    @HxRequest
    @PostMapping
    public View enterMessage(String message, Model model){
        UUID streamIdentifier = UUID.randomUUID();
        model.addAttribute("userMessage", message);
        model.addAttribute("streamIdentifier", streamIdentifier);

        eventPublisher.publishEvent(new QuestionEvent(this, streamIdentifier, message));

        return FragmentsRendering
                .with("chat/message-form-input")
                .fragment("chat/user-message")
                .fragment("chat/llm-answer")
                .build();
    }

    @GetMapping("/stream/{identifier}")
    public SseEmitter subscribe(@PathVariable String identifier){
        UUID streamIdentifier = UUID.fromString(identifier);
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.put(streamIdentifier, emitter);
        emitter.onCompletion(() -> emitters.remove(streamIdentifier));
        emitter.onTimeout(() -> emitters.remove(streamIdentifier));
        emitter.onError((e) -> emitters.remove(streamIdentifier));
        return emitter;
    }

    @EventListener
    @Async
    public void handleLLMAnswer(AnswerEvent event) throws IOException {
        logger.info("Received LLM Answer Event");
        SseEmitter sseEmitter = emitters.get(event.getStreamIdentifier());
        UUID streamIdentifier = event.getStreamIdentifier();
        TemplateOutput output = new StringOutput();
        templateEngine.render("chat/llm-answer-done.jte", Map.of("streamIdentifier", streamIdentifier, "answer", event.getAnswer()), output);
        sseEmitter.send(output.toString(), MediaType.TEXT_HTML);
        sseEmitter.complete();
        emitters.remove(event.getStreamIdentifier());
    }






}
