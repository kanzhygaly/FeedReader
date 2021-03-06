/*
 * JDBC implementation of FeedItemRepository Interface
 */
package kz.ya.FeedReader.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import kz.ya.FeedReader.exception.FeedItemNotFoundException;
import kz.ya.FeedReader.model.FeedItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 *
 * @author yerlana
 */
@Repository
public class FeedItemJdbcRepository implements FeedItemRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedItemJdbcRepository.class);
    private static final String TABLE_NAME = "feed_item";
    private static final String ID_MUST_NOT_BE_NULL = "The given 'id' must not be NULL!";
    private static final String ENTITY_MUST_NOT_BE_NULL = "The entity must not be NULL!";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public FeedItemJdbcRepository(JdbcTemplate jdbcTemplate) {
        Assert.notNull(jdbcTemplate, "JdbcTemplate must not be null!");

        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME).usingGeneratedKeyColumns("id");
    }

    /**
     * Returns the number of entities available.
     * 
     * @return The size of all entities in DB.
     */
    @Override
    public long count() {
        String query = String.format("SELECT COUNT(*) FROM %s", TABLE_NAME);
        return jdbcTemplate.queryForObject(query, Long.class);
    }

    /**
     * Deletes the entity with the given id.
     * 
     * @param id Input param ID.
     * @throws IllegalArgumentException If input param ID is null.
     * @throws FeedItemNotFoundException If entity doesn't exist.
     */
    @Override
    public void deleteById(Long id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        
        findById(id).orElseThrow(() -> new FeedItemNotFoundException(id));

        String query = String.format("DELETE FROM %s WHERE id = %d", TABLE_NAME, id);
        int rows = jdbcTemplate.update(query);
        LOGGER.info("Rows deleted: " + rows);
    }

    /**
     * Returns all entities.
     * 
     * @return All entities from DB.
     */
    @Override
    public List<FeedItem> findAll() {
        String query = String.format("SELECT * FROM %s", TABLE_NAME);
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FeedItem.class));
    }

    /**
     * Returns last entities (the number of entities = numOfEntries)
     * sorted by publication date in descending order.
     * If numOfEntries == 0, then it will return all not sorted entities.
     * 
     * @param numOfEntries Input param 'The number of entries that is needed'.
     * @throws IllegalArgumentException If input param is less than ZERO.
     * @return A list of entities, according to input param.
     */
    @Override
    public List<FeedItem> findLast(int numOfEntries) {
        if (numOfEntries == 0) {
            LOGGER.debug("numOfEntries = ZERO, call findAll()");
            return findAll();
        } else if (numOfEntries < 0) {
            throw new IllegalArgumentException("The given 'numOfEntries' must not be less than ZERO!");
        }

        String query = String.format("SELECT * FROM %s ORDER BY pub_date DESC LIMIT %d", TABLE_NAME, numOfEntries);
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FeedItem.class));
    }

    /**
     * Retrieves an entity by its id.
     * 
     * @param id Input param ID.
     * @throws IllegalArgumentException If input param ID is null.
     * @return If found, An Optional describing the specified value;
     *         Otherwise, An empty Optional instance
     */
    @Override
    public Optional<FeedItem> findById(Long id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);

        String query = String.format("SELECT * FROM %s WHERE id = %d", TABLE_NAME, id);

        FeedItem result;
        try {
            result = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(FeedItem.class));
        } catch (EmptyResultDataAccessException ex) {
            // Incorrect result size: expected 1, actual 0
            return Optional.empty();
        }

        return Optional.ofNullable(result);
    }

    /**
     * Saves a given entity.
     * If ID is null, it will create new one.
     * Else, it will update the existent one.
     * 
     * @param entity Input param entity.
     * @throws IllegalArgumentException If input param entity is null or its required variables are null.
     * @return New or Updated entity.
     */
    @Override
    public FeedItem save(FeedItem entity) {
        Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);
        Assert.notNull(entity.getAuthor(), "The given 'author' must not be NULL!");
        Assert.notNull(entity.getTitle(), "The given 'title' must not be NULL!");
        Assert.notNull(entity.getLink(), "The given 'link' must not be NULL!");
        Assert.notNull(entity.getPubDate(), "The given 'pub_date' must not be NULL!");

        // if entity is New, then INSERT it
        if (entity.getId() == null) {
            Map<String, Object> parameters = new HashMap<>(1);
            parameters.put("author", entity.getAuthor());
            parameters.put("title", entity.getTitle());
            parameters.put("description", entity.getDescription());
            parameters.put("link", entity.getLink());
            parameters.put("pub_date", entity.getPubDate());

            Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
            LOGGER.debug("New FeedItem: " + newId);
            
            entity.setId(newId.longValue());
            return entity;
        }

        // else UPDATE it
        int rows = jdbcTemplate.update("UPDATE " + TABLE_NAME +
                        " SET author = ?, title = ?, description = ?, link = ?, pub_date = ? WHERE id = ?",
                entity.getAuthor(), entity.getTitle(), entity.getDescription(), entity.getLink(), entity.getPubDate(), entity.getId());
        LOGGER.info("Rows updated: " + rows);

        return entity;
    }
}
