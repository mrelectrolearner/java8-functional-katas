package katas;

import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Create a data structure from the given data:

    This time we have 4 seperate arrays each containing lists, videos, boxarts, and bookmarks respectively.
    Each object has a parent id, indicating its parent.
    We want to build an array of list objects, each with a name and a videos array.
    The videos array will contain the video's id, title, bookmark time, and smallest boxart url.
    In other words we want to build the following structure:

    [
        {
            "name": "New Releases",
            "videos": [
                {
                    "id": 65432445,
                    "title": "The Chamber",
                    "time": 32432,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/TheChamber130.jpg"
                },
                {
                    "id": 675465,
                    "title": "Fracture",
                    "time": 3534543,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/Fracture120.jpg"
                }
            ]
        },
        {
            "name": "Thrillers",
            "videos": [
                {
                    "id": 70111470,
                    "title": "Die Hard",
                    "time": 645243,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/DieHard150.jpg"
                },
                {
                    "id": 654356453,
                    "title": "Bad Boys",
                    "time": 984934,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/BadBoys140.jpg"
                }
            ]
        }
    ]

    DataSource: DataUtil.getLists(), DataUtil.getVideos(), DataUtil.getBoxArts(), DataUtil.getBookmarkList()
    Output: the given datastructure
*/
public class Kata11 {
    public static List<Map> execute() {
        List<Map> lists = DataUtil.getLists();
        List<Map> videos = DataUtil.getVideos();
        List<Map> boxArts = DataUtil.getBoxArts();
        List<Map> bookmarkList = DataUtil.getBookmarkList();


        return lists.stream().map(list-> {
            Stream<Map> videosOfTheList = videos
                    .stream()
                    .filter(video -> video.get("listId").equals(list.get("id")));

            var videoOBject= geVideoObjectInformation(boxArts, bookmarkList, videosOfTheList);

            return Map.of("name", list.get("name"),
                    "video",videoOBject
                    );
        }).collect(Collectors.toList());

    }

    private static List<Map<String, Object>> geVideoObjectInformation(List<Map> boxArts, List<Map> bookmarkList, Stream<Map> videosOfTheList) {
        return videosOfTheList
                .map(video -> {
                    var boxArtsVideoElement = getElementOfTheVideo(boxArts, video);

                    var bookmarkVideoElement = getElementOfTheVideo(bookmarkList, video);

                    return Map.of("id", video.get("id"),
                            "title", video.get("title"),
                            "boxArt", boxArtsVideoElement.get("url"),
                            "time", bookmarkVideoElement.get("time"));
                }).collect(Collectors.toList());
    }

    private static Map getElementOfTheVideo(List<Map> ListOfElements, Map video) {
        return ListOfElements
                .stream()
                .filter(bookMark -> bookMark.get("videoId").equals(video.get("id")))
                .findFirst()
                .orElseThrow();
    }
}
