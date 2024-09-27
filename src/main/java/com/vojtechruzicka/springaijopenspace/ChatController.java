package com.vojtechruzicka.springaijopenspace;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @Operation(description = "Ask question and get response by 19th century aristocrat.")
    @GetMapping("/ask")
    public String ask(@RequestParam(defaultValue = "How can you help me?", required = false) String question) {
        return chatService.ask(question);
    }

    @Operation(description = "Ask using data from vector store about JOpenspace conference.")
    @GetMapping("/ask-vector-store")
    public String askVectorStore(@RequestParam(defaultValue = "How can you help me?", required = false) String question) {
        return chatService.askVectorStore(question);
    }

    @Operation(description = "Ask query and attach image as part of the request. You can ask questions about the image.")
    @GetMapping("/ask-image")
    public String askWithImage(@RequestParam(defaultValue = "Describe me what is in the image?", required = false) String question, @RequestParam String imageUrl) throws MalformedURLException, URISyntaxException {
        return chatService.askWithImage(question, imageUrl);
    }

    @Operation(description = "Ask query which returns a list of movies.")
    @GetMapping("/movies")
    public List<Movie> getMovies(@RequestParam(defaultValue = "What are top movies according to metacritic score?", required = false) String question) {
        return chatService.getMovies(question);
    }

    @Operation(description = "Ask query wich results in returning a movie object.")
    @GetMapping("/movie")
    public Movie getMovie(@RequestParam(defaultValue = "Give me details about Monty Python's life of Brian?", required = false) String question) {
        return chatService.getMovie(question);
    }

    @Operation(description = "Ask about movies currently playing in cinemas in a specific city.")
    @GetMapping("/movies-in-cinemas")
    public String getMovie(@RequestParam(defaultValue = "Give me movies currently in cinemas.", required = false) String question, @RequestParam(required = false, defaultValue = "Prague") String city) {
        return chatService.getMoviesInCinemas(question, city);
    }
}
