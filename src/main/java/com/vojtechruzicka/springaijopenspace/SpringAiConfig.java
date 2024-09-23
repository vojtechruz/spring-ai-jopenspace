package com.vojtechruzicka.springaijopenspace;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAiConfig {
    
        @Bean
        public ChatClient chatClient(ChatClient.Builder builder) {
            return builder.build();
        }

    
}
