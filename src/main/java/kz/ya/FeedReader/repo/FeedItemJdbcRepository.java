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

    @Override
    public long count() {
        String query = String.format("SELECT COUNT(*) FROM %s", TABLE_NAME);
        return jdbcTemplate.queryForObject(query, Long.class);
    }

    @Override
    public void deleteById(Long id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        
        findById(id).orElseThrow(() -> new FeedItemNotFoundException(id));

        String query = String.format("DELETE FROM %s WHERE id = %d", TABLE_NAME, id);
        int rows = jdbcTemplate.update(query);
        LOGGER.info("Rows deleted: " + rows);
    }

    @Override
    public List<FeedItem> findAll() {
        String query = String.format("SELECT * FROM %s", TABLE_NAME);
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FeedItem.class));
    }

    @Override
    public List<FeedItem> findLast(int numOfEntries) {
        if (numOfEntries == 0) {
            LOGGER.info("numOfEntries = ZERO, call findAll()");
            return findAll();
        } else if (numOfEntries < 0) {
            throw new IllegalArgumentException("The given 'numOfEntries' must not be less than ZERO!");
        }

        String query = String.format("SELECT * FROM %s ORDER BY pubDate DESC LIMIT %d", TABLE_NAME, numOfEntries);
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(FeedItem.class));
    }

    @Override
    public Optional<FeedItem> findById(Long id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);

        String query = String.format("SELECT * FROM %s WHERE id = %d", TABLE_NAME, id);
        FeedItem result = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(FeedItem.class));

        return Optional.ofNullable(result);
    }

    @Override
    public FeedItem save(FeedItem entity) {
        Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);

        // if entity is New, then INSERT it
        if (entity.getId() == null) {
            Map<String, Object> parameters = new HashMap<>(1);
            parameters.put("title", entity.getTitle());
            parameters.put("link", entity.getLink());
            parameters.put("pubDate", entity.getPubDate());

            Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
            LOGGER.debug("New FeedItem: " + newId);

            return findById((Long) newId).get();
        }

        // else UPDATE it
        int rows = jdbcTemplate.update("UPDATE " + TABLE_NAME + " SET title = ?, link = ?, pub_date = ? WHERE id = ?",
                entity.getTitle(), entity.getLink(), entity.getPubDate(), entity.getId());
        LOGGER.info("Rows updated: " + rows);

        return findById(entity.getId()).get();
    }
}
