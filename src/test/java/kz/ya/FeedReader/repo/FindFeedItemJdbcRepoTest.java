/*
 * FeedItemJdbcRepository tests for retrieve methods
 */
package kz.ya.FeedReader.repo;

import java.time.LocalDateTime;
import kz.ya.FeedReader.model.FeedItem;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 *
 * @author yerlana
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FindFeedItemJdbcRepoTest {

    @Autowired
    private FeedItemRepository repository;

    @Test
    public void shouldGetFeedItemWhenItExists() {
        final FeedItem expResult = repository.save(new FeedItem("author1","title1", "link1", LocalDateTime.now()));

        final FeedItem result = repository.findById(expResult.getId()).get();

        Assertions.assertThat(result).isEqualTo(expResult);
    }

    @Test
    public void shouldGetEmptyResultWhenFeedItemDoesNotExist() {
        long wrongId = -1l;

        Optional<FeedItem> result = repository.findById(wrongId);

        Assertions.assertThat(result.isPresent()).isFalse();
    }
    
    @Test
    public void shouldGetNoSuchElementExceptionWhenIdIsWrong() {
        long wrongId = -1l;
        Assertions.assertThatThrownBy(() -> repository.findById(wrongId).get())
                .isInstanceOf(NoSuchElementException.class);
    }
    
    @Test
    public void shouldGetIllegalArgumentExceptionWhenIdIsNull() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> repository.findById(null));
    }
    
    @Test
    public void shouldGetLastFiveEntries() {
        int numberOfEntries = 5;
        
        List<FeedItem> result = repository.findLast(numberOfEntries);
        
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.size()).isEqualTo(numberOfEntries);
    }
    
    @Test
    public void shouldGetIllegalArgumentExceptionWhenNumOfEntriesIsNegative() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> repository.findLast(-1));
    }
    
    @Test
    public void shouldGetAllEntriesWhenNumOfEntriesIsZero() {
        long expected = repository.count();
        
        List<FeedItem> result = repository.findLast(0);
        
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.size()).isEqualTo(expected);
    }
    
    @Test
    public void shouldGetAllEntries() {
        long expected = repository.count();
        
        List<FeedItem> result = repository.findAll();
        
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.size()).isEqualTo(expected);
    }
    
    @Test
    public void shouldGetSizeOfAllEntries() {
        // 10 entries that were inserted from data.sql
        long expected = 10l;
        
        long result = repository.count();
        
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
