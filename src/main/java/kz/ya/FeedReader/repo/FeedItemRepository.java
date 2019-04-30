/*
 * FeedItem CRUD Repository Interface
 */
package kz.ya.FeedReader.repo;

import java.util.List;
import java.util.Optional;
import kz.ya.FeedReader.model.FeedItem;

/**
 *
 * @author yerlana
 */
public interface FeedItemRepository {

    // Returns the number of entities available.
    long count();
    
    // Deletes the entity with the given id.
    void deleteById(Long id);
    
    // Returns all instances of the type.
    List<FeedItem> findAll();
    
    List<FeedItem> findLast(int numOfEntries);

    // Retrieves an entity by its id.
    Optional<FeedItem> findById(Long id);

    // Saves a given entity.
    FeedItem save(FeedItem entity);
}
