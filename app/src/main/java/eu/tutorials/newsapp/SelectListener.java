package eu.tutorials.newsapp;

import eu.tutorials.newsapp.model.NewsHeadlines;

public interface SelectListener {
    void onFetch(NewsHeadlines headlines);

}
