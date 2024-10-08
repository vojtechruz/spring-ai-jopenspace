package com.vojtechruzicka.springaijopenspace;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class SpringAiConfig {

    @Primary
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @Bean
    public ChatClient ragChatClient(ChatClient.Builder builder, VectorStore vectorStore) {
        return builder.defaultAdvisors(new SimpleLoggerAdvisor(), new QuestionAnswerAdvisor(vectorStore)).build();
    }

        // Custom function definitions - these can be called to provide dynamic information to the model

        @Bean
        public FunctionCallback CurrentMovies() {

            return new FunctionCallbackWrapper.Builder<MovieRequest, List<Movie>>(new MovieService())
                    .withName("CurrentMovies") // By this name is the function referenced when provided to the ai model
                    .withDescription("Get list of movies currently in cinemas based on city.") // This helps model to determine if the function should be used for the current query
                    .build();
        }
    
}
