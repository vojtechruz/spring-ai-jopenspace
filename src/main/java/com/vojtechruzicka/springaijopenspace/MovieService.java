package com.vojtechruzicka.springaijopenspace;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.function.Function;

public class MovieService implements Function<MovieRequest, List<Movie>> {
    @Override
    public List<Movie> apply(MovieRequest city) {

        Movie movie1 = new Movie("title1", "2024", "director1", List.of("genre1"), "90", "description");
        Movie movie2 = new Movie("title2", "2024", "director2", List.of("genre2"), "80", "description");
        Movie movie3 = new Movie("title3", "2024", "director3", List.of("genre3"), "70", "description");

        if(StringUtils.equalsIgnoreCase(city.getCity(), "Prague")) {
            return List.of(movie1, movie2, movie3);
        } else {
            return List.of(movie3);
        }

    }
}
