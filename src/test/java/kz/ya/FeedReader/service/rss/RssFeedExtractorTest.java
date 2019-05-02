/*
 * Unit test for RssFeedExtractor class
 */
package kz.ya.FeedReader.service.rss;

import java.util.List;
import kz.ya.FeedReader.model.FeedItem;
import kz.ya.FeedReader.service.FeedExtractor;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;

/**
 *
 * @author yerlana
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RssFeedExtractorTest {

    @Autowired
    private FeedExtractor feedExtractor;

    @Test
    public void shouldExtractItems() {
        String feedUrl = "https://moikrug.ru/vacancies/rss";
        int expected = 50;

        List<FeedItem> result = feedExtractor.extractItems(feedUrl);

        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.size()).isEqualTo(expected);
    }

    @Test
    public void shouldThrowIAExceptionIfInputIsNull() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> feedExtractor.extractItems(null));
    }

    @Test
    public void shouldThrowIAExceptionIfInputIsEmpty() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> feedExtractor.extractItems(""));
    }

    @Test
    public void shouldThrowResourceAccessExceptionIfSourceIsNotRss() {
        String feedUrl = "https://competitioncorner.net";

        Assertions.assertThatThrownBy(() -> feedExtractor.extractItems(feedUrl))
                .isInstanceOf(ResourceAccessException.class);
    }
}
