package org.codeberg.atomfrede.htmx_samples;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.view.FragmentsRendering;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@RequestMapping("llm-chat")
public class LLMChatController {
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping
    public String index(){
        return "chat/index";
    }

    @HxRequest
    @PostMapping
    public View enterMessage(String message, Model model){
        model.addAttribute("userMessage", message);
        model.addAttribute("streamIdentifier", UUID.randomUUID());
        return FragmentsRendering
                .with("chat/message-form-input")
                .fragment("chat/user-message")
                .fragment("chat/llm-answer")
                .build();
    }

    @GetMapping("/stream/{identifier}")
    public SseEmitter subscribe(@PathVariable String identifier){
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        return emitter;
    }




}
