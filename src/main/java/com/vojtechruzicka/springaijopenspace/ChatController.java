package com.vojtechruzicka.springaijopenspace;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/chat")
public class ChatController {


    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam(defaultValue = "How can you help me?", required = false) String question) {
        return chatService.ask(question);
    }

    @GetMapping("/ask-image")
    public String askWithImage(@RequestParam(defaultValue = "Describe me what is in the image?", required = false) String question, @RequestParam String imageUrl) throws MalformedURLException, URISyntaxException {
        return chatService.askWithImage(question, imageUrl);
    }
}
