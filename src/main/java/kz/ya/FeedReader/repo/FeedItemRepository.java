/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    /*
    // Deletes a given entity.
    void delete(FeedItem feedItem);
    // Deletes all entities managed by the repository.
    void deleteAll();
    // Deletes the given entities.
    void deleteAll(List<FeedItem> feedItems);
    // Deletes the entity with the given id.
    void deleteById(Long id);
    // Returns whether an entity with the given id exists.
    boolean existsById(Long id);
    // Returns all instances of the type with the given IDs.
    Iterable<T> findAllById(Iterable<ID> ids);
     */
    // Returns the number of entities available.
    long count();
    
    // Returns all instances of the type.
    List<FeedItem> findAll();
    
    List<FeedItem> findLast(int numOfEntries);

    // Retrieves an entity by its id.
    Optional<FeedItem> findById(Long id);

    // Saves a given entity.
    FeedItem save(FeedItem entity);

    // Saves all given entities.
    List<FeedItem> saveAll(List<FeedItem> entities);
}
