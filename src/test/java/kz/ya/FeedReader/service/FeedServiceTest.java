/*
 * Unit tests for FeedService class
 */
package kz.ya.FeedReader.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import kz.ya.FeedReader.model.FeedItem;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author yerlana
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FeedServiceTest {
    
    @Autowired
    private FeedService service;
    
    @Test
    public void shouldGetLatest10() {
        int expected = 10;
        
        List<FeedItem> result = service.getLatest10();
        
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.size()).isEqualTo(expected);
    }
    
    @Test
    public void shouldNotSaveIfInputIsNull() {
        List<FeedItem> result = service.saveAll(null);
        Assertions.assertThat(result).isEmpty();
    }
    
    @Test
    public void shouldNotSaveIfInputIsEmpty() {
        List<FeedItem> result = service.saveAll(new ArrayList<>());
        Assertions.assertThat(result).isEmpty();
    }
    
    @Test
    public void shouldSaveAll() {
        long expected = 5l;
        
        List<FeedItem> input = new LinkedList<>();
        input.add(new FeedItem("author1", "title1", "link1", LocalDateTime.now()));
        input.add(new FeedItem("author2", "title2", "link2", LocalDateTime.now()));
        input.add(new FeedItem("author3", "title3", "link3", LocalDateTime.now()));
        input.add(new FeedItem("author4", "title4", "link4", LocalDateTime.now()));
        input.add(new FeedItem("author5", "title5", "link5", LocalDateTime.now()));
        
        List<FeedItem> result = service.saveAll(input);
        
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.size()).isEqualTo(expected);
    }
    
    @Test
    public void shouldSaveAllPossible() {
        long expected = 3l;
        
        List<FeedItem> input = new LinkedList<>();
        input.add(new FeedItem("author1", "title1", "link1", LocalDateTime.now())); // SUCCESS
        input.add(new FeedItem(null, "title2", "link2", LocalDateTime.now())); // FAIL
        input.add(new FeedItem("author3", "title3", "link3", LocalDateTime.now())); // SUCCESS
        input.add(new FeedItem("author4", null, "link4", LocalDateTime.now())); // FAIL
        input.add(new FeedItem("author5", "title5", "link5", LocalDateTime.now())); // SUCCESS
        
        List<FeedItem> result = service.saveAll(input);
        
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.size()).isEqualTo(expected);
    }
}
