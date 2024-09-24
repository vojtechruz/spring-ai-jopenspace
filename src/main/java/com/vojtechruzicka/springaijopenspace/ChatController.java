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


    @GetMapping("/movies")
    public String getMovies(@RequestParam(defaultValue = "What are top movies according to metacritic score?", required = false) String question) {
        return chatService.getMovies(question).toString();
    }

    @GetMapping("/movie")
    public String getMovie(@RequestParam(defaultValue = "Give me details about Monty Python's life of brian?", required = false) String question) {
        return chatService.getMovie(question).toString();
    }

    @GetMapping("/movies-in-cinemas")
    public String getMovie(@RequestParam(defaultValue = "Give me movies currently in cinemas.", required = false) String question, @RequestParam(required = false, defaultValue = "Prague") String city) {
        return chatService.getMoviesInCinemas(question, city);
    }
}
