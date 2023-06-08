package eu.tutorials.newsapp;

import java.util.List;

import eu.tutorials.newsapp.model.NewsHeadlines;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData(List<NewsHeadlines> list, String message);

    void onError(String message);
}
