/*
 * Unit tests for FeedService class
 */
package kz.ya.FeedReader.service;

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
}
