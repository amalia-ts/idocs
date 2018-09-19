package com.amalia;

import java.util.*;

/**
 * Created by Amalia on 9/19/2018.
 */
public class OrderMovies {

    public OrderMovies(List<MovieDetails> movies) {

    }

    public List orderByTimeWatched(List<MovieDetails> movies) {
        Collections.sort(movies,
                (o1, o2) -> o1.getNumOfMinutesWatched().compareTo(o2.getNumOfMinutesWatched()));
        return movies;
    }
    public List orderByAvgPercentage(List<MovieDetails> movies) {
        Collections.sort(movies,
                (o1, o2) -> Double.compare(o1.getAvgPercentage(),o2.getAvgPercentage()));
        return movies;
    }
    public List orderByFavGenre(List<MovieDetails> movies) {

//        movies.stream().filter(p->(new Double("60")<=(p.getAvgPercentage())));
//
//        Map<String, List<MovieDetails>> moviesPerUser = movies.stream()
//                .collect(groupingBy(MovieDetails::getUser));
//
//        Map<String, Map<String, List<MovieDetails>>> map = movies.stream()
//                .collect(groupingBy(MovieDetails::getUser, groupingBy(MovieDetails::getGenre)));
//
//        Collections.sort(movies,
//                (o1, o2) -> Double.compare(o1.getAvgPercentage(),o2.getAvgPercentage()));
        return movies;
    }
}
