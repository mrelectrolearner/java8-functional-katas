package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/*
    Goal: Retrieve the largest rating using reduce()
    DataSource: DataUtil.getMovies()
    Output: Double
*/
public class Kata5 {
    public static Double execute() {
        List<Movie> movies = DataUtil.getMovies();
        BinaryOperator<Movie> largestRating=(movie1, movie2) -> movie1.getRating()> movie2.getRating()?movie1:movie2;
        return movies.stream()
                .reduce(largestRating)
                .map(movie -> movie.getRating())
                .orElseThrow();


    }
}
