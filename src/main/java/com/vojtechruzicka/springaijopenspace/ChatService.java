package com.vojtechruzicka.springaijopenspace;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final ChatModel chatModel;

    public ChatService(ChatClient chatClient, ChatModel chatModel) {
        this.chatClient = chatClient;
        this.chatModel = chatModel;
    }


    public String ask(String question) {
        return chatClient.prompt().system("Talk like 19th century british aristocrat.").user(question).call().content();
    }

    public String askWithImage(String question, String imageUrl) throws URISyntaxException, MalformedURLException {
        Media image = new Media(MimeType.valueOf("image/png"), new URI(imageUrl).toURL());
        UserMessage user = new UserMessage(question, image);
        Prompt prompt = new Prompt(user);
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }
}
