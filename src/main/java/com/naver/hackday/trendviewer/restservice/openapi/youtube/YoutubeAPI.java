package com.naver.hackday.trendviewer.restservice.openapi.youtube;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.naver.hackday.trendviewer.restservice.openapi.youtube.model.Youtube;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class YoutubeAPI {

  private static final Logger logger = LoggerFactory.getLogger(YoutubeAPI.class);

  private static final String API_KEY = "AIzaSyBL2Zs3XK54xK7Ta3Nb6cqz54GvAlt_l2g";

  private static final String YOUTUBE_URL = "https://www.youtube.com/watch?v=";

  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

  private static final JsonFactory JSON_FACTORY = new JacksonFactory();

  private static YouTube youtube;

  public List<Youtube> request(String query, long display) {
    List<Youtube> response = new ArrayList<>();

    try {
      youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
        public void initialize(HttpRequest request) throws IOException { }
      }).setApplicationName("youtube-cmdline-search-sample").build();
      YouTube.Search.List search = youtube.search().list("id, snippet");
      search.setKey(API_KEY);
      search.setQ(query);

      search.setType("video");

      search.setFields("items(id/kind,id/videoId,snippet/title,snippet/publishedAt,snippet/thumbnails/default/url)");
      search.setMaxResults(display);
      search.setOrder("date");

      SearchListResponse searchResponse = search.execute();

      List<SearchResult> searchResultList = searchResponse.getItems();

      if (searchResultList != null) {
        response = searchResultList.stream().map(
                this::mapSearchResultToYoutube)
                .collect(Collectors.toList());
      }

    } catch (GoogleJsonResponseException e) {
      logger.error("There was a service error: " + e.getDetails().getCode() + " : "
          + e.getDetails().getMessage());
    } catch (IOException e) {
      logger.error("There was an IO error: " + e.getCause() + " : " + e.getMessage());
    } catch (Throwable t) {
      t.printStackTrace();
    }

    return response;
  }

  private Youtube mapSearchResultToYoutube(SearchResult singleVideo) {
    ResourceId rId = singleVideo.getId();
    String title = singleVideo.getSnippet().getTitle();
    Thumbnail thumbnail = (Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
    String url = YOUTUBE_URL + rId.getVideoId();

    return new Youtube().builder()
        .title(title)
        .thumbnail(thumbnail.getUrl())
        .url(url)
        .build();
  }
}
