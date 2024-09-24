package com.vojtechruzicka.springaijopenspace;

import java.util.List;
import java.util.function.Function;

public class MovieService implements Function<MovieRequest, Movie> {
    @Override
    public Movie apply(MovieRequest city) {

        Movie movie1 = new Movie("title1", "2024", "director1", List.of("genre1"), "90", "description");
        Movie movie2 = new Movie("title2", "2024", "director2", List.of("genre2"), "80", "description");
        Movie movie3 = new Movie("title3", "2024", "director3", List.of("genre3"), "70", "description");

        return movie1;
    }
}
