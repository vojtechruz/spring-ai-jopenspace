package com.vojtechruzicka.springaijopenspace;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;
    private final ChatModel chatModel;

    public ChatController(ChatClient chatClient, ChatModel chatModel) {
        this.chatClient = chatClient;
        this.chatModel = chatModel;
    }

    @GetMapping("/x")
    public String getChat() {
        return chatClient.prompt().system("Talk like 19th century british aristocrat.").user("Give me 10 cute names for cat").call().content();
    }

    @GetMapping("/z")
    public String xxx() throws URISyntaxException, MalformedURLException {
        return chatModel.call(new Prompt(new UserMessage("Generate name for the animal in the picture based on how it looks and its characteristics.", new Media(MimeType.valueOf("image/png"), new URI("https://oaidalleapiprodscus.blob.core.windows.net/private/org-lhcTqnaUvRiU5SZ7KRgD7AQh/user-IrZ3dGTfHObAmJTsmpUC4xla/img-h3ZNAZMugaxt9ncIi8j4jhIu.png?st=2024-09-21T18%3A31%3A05Z&se=2024-09-21T20%3A31%3A05Z&sp=r&sv=2024-08-04&sr=b&rscd=inline&rsct=image/png&skoid=d505667d-d6c1-4a0a-bac7-5c84a87759f8&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-09-20T23%3A42%3A22Z&ske=2024-09-21T23%3A42%3A22Z&sks=b&skv=2024-08-04&sig=YzR1KCDWU8vrgIGBQsAjHy%2B4E2eVnoyRnr%2Bqkr/qilM%3D").toURL())))).getResult().getOutput().getContent();
    }
}
