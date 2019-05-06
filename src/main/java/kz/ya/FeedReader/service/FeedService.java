/*
 * Feed Service class
 */
package kz.ya.FeedReader.service;

import java.util.LinkedList;
import java.util.List;
import kz.ya.FeedReader.model.FeedItem;
import kz.ya.FeedReader.repo.FeedItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yerlana
 */
@Service
public class FeedService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedService.class);
    
    private final FeedItemRepository repository;

    @Autowired
    public FeedService(FeedItemRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns a list of latest 10 feed items.
     *
     * @return Latest 10 feed items.
     */
    public List<FeedItem> getLatest10() {
        return repository.findLast(10);
    }

    /**
     * Tries to save all given items from the list and returns a list of saved.
     * If one fails to save, then skips it and continues with others.
     * 
     * @param items Input list of items to save.
     * @return If input list is null or empty, then returns an empty list.
     *         Else, returns a list of all successfully saved items.
     */
    public List<FeedItem> saveAll(List<FeedItem> items) {
        List<FeedItem> result = new LinkedList<>();
        if (items == null) {
            return result;
        }

        items.forEach((item) -> {
            try {
                result.add(repository.save(item));
            } catch (IllegalArgumentException ex) {
                LOGGER.error("Failed to save {} due to: {}", item, ex.getMessage());
            }
        });

        return result;
    }
}
