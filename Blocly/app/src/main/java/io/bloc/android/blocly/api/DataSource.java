package io.bloc.android.blocly.api;

import java.util.List;

import io.bloc.android.blocly.api.network.GetFeedsNetworkRequest;

/**
 * Created by Kari on 10/12/2015.
 */
public class DataSource {

    private List<GetFeedsNetworkRequest.RssFeed> feeds;

    public DataSource() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                feeds = new GetFeedsNetworkRequest("http://feeds.feedburner.com/androidcentral?format=xml").performRequest();
            }
        }).start();
    }

    public List<GetFeedsNetworkRequest.RssFeed> getFeeds() {
        return feeds;
    }
    public List<GetFeedsNetworkRequest.RssItem> getItems() {
        return feeds.get(0).channelItems;
    }
}