package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        BinaryOperator<BoxArt> smallestBoxArt=(boxArt1, boxArt2) -> {
            return boxArt1.getHeight()* boxArt1.getWidth() < boxArt2.getHeight()* boxArt1.getWidth() ? boxArt1 : boxArt2;
        };
        return movieLists.stream()
                .flatMap(movieList -> movieList.getVideos().stream())
                .map(movie -> {
                    int middle = 1;
                    return Map.of("id", movie.getId(),
                            "title", movie.getTitle(),
                            "time", movie.getInterestingMoments().get(middle).getTime(),
                            "url", movie.getBoxarts().stream().reduce(smallestBoxArt).orElseThrow());
                }).collect(Collectors.toList());


    }
}
