package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.MovieList;
import util.DataUtil;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        Predicate<BoxArt> correctBoxArt= boxArt -> boxArt.getHeight().equals(150)&&boxArt.getWidth().equals(200);

        return movieLists.stream()
                .flatMap(movieList->movieList.getVideos().stream())
                .map(movie -> ImmutableMap.of("id",movie.getId(),
                        "title",movie.getTitle(),
                        "boxart",movie.getBoxarts()
                                .stream()
                                .filter(correctBoxArt)))
                .collect(Collectors.toList());

    }
}
