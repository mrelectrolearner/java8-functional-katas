package katas;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/*
    Goal: Combine videos and bookmarks by index (StreamUtils.zip) (https://github.com/poetix/protonpack)
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("videoId", "5", "bookmarkId", "3")
*/
public class Kata8 {
    public static List<Map> execute() {
        List<Movie> movies = DataUtil.getMovies();
        List<Bookmark> bookMarks = DataUtil.getBookMarks();


        BiFunction<Movie, Bookmark, Map<String, String>> movieBookmarkMapBiFunction = (movie, bookMark) -> {
            return ImmutableMap.of("videoId", movie.getId().toString(), "bookmarkId", bookMark.getId().toString());
        };
        return StreamUtils
                 .zip(movies.stream(),bookMarks.stream(), movieBookmarkMapBiFunction).collect(Collectors.toList());

    }
}
