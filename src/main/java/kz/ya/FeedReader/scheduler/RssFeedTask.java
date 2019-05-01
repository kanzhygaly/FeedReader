package kz.ya.FeedReader.scheduler;

import java.util.List;
import kz.ya.FeedReader.model.FeedItem;
import kz.ya.FeedReader.repo.FeedItemRepository;
import kz.ya.FeedReader.service.FeedExtractor;
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
    
    @Value("${rss.feed.url}")
    private String feedUrl;
    
    private final FeedExtractor feedExtractor;
    private final FeedItemRepository repository;

    @Autowired
    public RssFeedTask(FeedExtractor feedExtractor, FeedItemRepository repository) {
        this.feedExtractor = feedExtractor;
        this.repository = repository;
    }

    // Scheduled to be invoked every 180 sec
    @Scheduled(fixedRate = 180000)
    public void fetchRssFeed() {
        LOGGER.info("Fetching from RSS Feed {}", feedUrl);
        List<FeedItem> items = feedExtractor.extractItems(feedUrl);
        LOGGER.info("Fetch result got {} items", items.size());
        items.forEach((entry) -> {
            repository.save(entry);
        });
    }
}
