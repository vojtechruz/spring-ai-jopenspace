package com.vojtechruzicka.springaijopenspace;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final ChatModel chatModel;
    private final OpenAiChatModel openAiChatModel;

    public ChatService(ChatClient chatClient, ChatModel chatModel, OpenAiChatModel openAiChatModel) {
        this.chatClient = chatClient;
        this.chatModel = chatModel;
        this.openAiChatModel = openAiChatModel;
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

    public List<Movie> getMovies(String question) {
        return chatClient.prompt()
                .system("Your only purpose is to return movies based on user query. Score means metacritic score. Politely decline all other requests.")
                .user(question)
                .call()
                .entity(new ParameterizedTypeReference<List<Movie>>() {});
    }

    public Movie getMovie(String question) {
        return chatClient.prompt()
                .system("Your only purpose is to return single movie based on user query. Score means metacritic score. Politely decline all other requests.")
                .user(question)
                .call()
                .entity(Movie.class);
    }

    public String getMoviesInCinemas(String question, String city) {
        List<Message> messages = List.of(new UserMessage(question + " my current city is " + city), new SystemMessage("Your only purpose is to return movies based on user query. Score means metacritic score. Politely decline all other requests. "));
        OpenAiChatOptions options = OpenAiChatOptions.builder().withFunction("CurrentMovies").build();
        Prompt prompt = new Prompt(messages, options);

        return openAiChatModel.call(prompt).getResult().getOutput().getContent();
    }
}
