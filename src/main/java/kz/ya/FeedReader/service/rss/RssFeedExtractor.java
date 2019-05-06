/*
 * Implementation service class of FeedExtractor
 * for extracting RSS feeds
 */
package kz.ya.FeedReader.service.rss;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import kz.ya.FeedReader.model.FeedItem;
import kz.ya.FeedReader.service.FeedExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;
import org.springframework.util.Assert;

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
        Assert.notNull(feedUrl, "The given 'feedUrl' must not be NULL!");
        if (feedUrl.isEmpty()) {
            throw new IllegalArgumentException("The given 'feedUrl' must not be EMPTY!");
        }
        
        // fetch RSS feed by the given feedUrl
        SyndFeed syndFeed = restTemplate.execute(feedUrl, HttpMethod.GET, null, response -> {
            SyndFeedInput input = new SyndFeedInput();
            try (final XmlReader reader = new XmlReader(response.getBody())) {
                return input.build(reader);
            } catch (FeedException e) {
                throw new IOException("Could not parse response", e);
            }
        });
        
        if (syndFeed == null) {
            throw new RuntimeException("Error getting feed from " + feedUrl);
        }

        List<FeedItem> result = new LinkedList<>();

        // populate the result list
        syndFeed.getEntries().forEach((entry) -> {
            LocalDateTime ldt = LocalDateTime.ofInstant(entry.getPublishedDate().toInstant(), ZoneId.systemDefault());
            FeedItem item = new FeedItem(entry.getAuthor(), entry.getTitle(), entry.getLink(), ldt);
            item.setDescription(entry.getDescription().getValue());
            result.add(item);
        });
        
        return result;
    }
}
