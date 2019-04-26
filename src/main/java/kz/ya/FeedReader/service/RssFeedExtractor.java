/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.ya.FeedReader.service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import kz.ya.FeedReader.model.FeedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author yerlana
 */
@Service
public class RssFeedExtractor implements FeedExtractor {

    private final RestTemplate restTemplate;

    @Autowired
    public RssFeedExtractor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<FeedItem> extractItems(String feedUrl) {
        List<FeedItem> result = new LinkedList<>();

        // fetch RSS feed by the given feedUrl
        SyndFeed syndFeed = restTemplate.execute(feedUrl, HttpMethod.GET, null, response -> {
            SyndFeedInput input = new SyndFeedInput();
            try {
                return input.build(new XmlReader(response.getBody()));
            } catch (FeedException e) {
                throw new IOException("Could not parse response", e);
            }
        });
        
        if (syndFeed == null) {
            throw new RuntimeException("Error getting feed from " + feedUrl);
        }

        // populate the result list
        syndFeed.getEntries().forEach((entry) -> {
            System.out.println(entry.getAuthor());
            System.out.println(entry.getDescription());
            System.out.println(entry.getUpdatedDate());
            System.out.println(entry.getUri());
            System.out.println(entry.getContents().get(0).getValue());
            result.add(new FeedItem(entry.getTitle(), entry.getLink(), entry.getPublishedDate()));
        });
        
        return result;
    }
}
