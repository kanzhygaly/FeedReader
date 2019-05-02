/*
 * Integration tests for RssFeedTask
 */
package kz.ya.FeedReader.scheduler;

import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author yerlana
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RssFeedTaskIT {
    
    @SpyBean
    private RssFeedTask rssFeedTask;
    
    @Test
    public void shouldInvokeFetchRssFeedAfter180sec() {
        Awaitility.await().atMost(Duration.ONE_SECOND)
               .untilAsserted(() -> Mockito.verify(rssFeedTask, Mockito.times(1)).fetchRssFeed());
    }
}
