/*
 * Scheduled task for fetching RSS feeds
 */
package kz.ya.FeedReader.scheduler;

import java.util.List;
import kz.ya.FeedReader.model.FeedItem;
import kz.ya.FeedReader.service.FeedExtractor;
import kz.ya.FeedReader.service.FeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author yerlana
 */
@Component
public class RssFeedTask {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RssFeedTask.class);

    // property from application.properties
    @Value("${rss.feed.url}")
    private String feedUrl;
    
    private final FeedExtractor feedExtractor;
    private final FeedService feedService;

    @Autowired
    public RssFeedTask(FeedExtractor feedExtractor, FeedService feedService) {
        this.feedExtractor = feedExtractor;
        this.feedService = feedService;
    }

    // Scheduled to be invoked every X sec
    @Scheduled(fixedRateString = "${rss.feed.schedule.rate}")
    public void fetchRssFeed() {
        LOGGER.info("Fetching from RSS Feed {}", feedUrl);
        List<FeedItem> items = feedExtractor.extractItems(feedUrl);
        LOGGER.info("Fetch result got {} items", items.size());

        feedService.saveAll(items);
    }
}
