package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.List;
import java.util.function.BinaryOperator;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();
        BinaryOperator<BoxArt> largestBoxArt=(boxArt1, boxArt2) -> {
            return boxArt1.getHeight()* boxArt1.getWidth() > boxArt2.getHeight()* boxArt1.getWidth() ? boxArt1 : boxArt2;
        };
        return movies.stream()
                .flatMap(movie -> movie.getBoxarts().stream())
                .reduce(largestBoxArt)
                .map(boxArt -> boxArt.getUrl()).orElseThrow();

    }
}
