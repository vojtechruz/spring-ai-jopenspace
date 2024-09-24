package com.vojtechruzicka.springaijopenspace;

import java.util.List;

public class Movie {

    private String title;
    private String year;
    private String director;
    private List<String> genres;
    private String score;
    private String description;


    public Movie() {
    }

    public Movie(String title, String year, String director, List<String> genres, String score, String description) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.genres = genres;
        this.score = score;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", director='" + director + '\'' +
                ", genres=" + genres +
                ", score='" + score + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
