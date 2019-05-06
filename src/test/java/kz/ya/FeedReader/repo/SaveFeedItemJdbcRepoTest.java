/*
 * FeedItemJdbcRepository tests for save method (create/update)
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

/**
 *
 * @author yerlana
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SaveFeedItemJdbcRepoTest {

    @Autowired
    private FeedItemRepository repository;

    @Test
    public void shouldCreateNewFeedItem() {
        FeedItem newItem = new FeedItem("author1", "title1", "link1", LocalDateTime.now());
        newItem.setDescription("description1");
        final FeedItem expResult = repository.save(newItem);

        final FeedItem result = repository.findById(expResult.getId()).get();

        Assertions.assertThat(result).isEqualTo(expResult);
        Assertions.assertThat(result.getId()).isEqualTo(expResult.getId());
        Assertions.assertThat(result.getAuthor()).isEqualTo(expResult.getAuthor());
        Assertions.assertThat(result.getTitle()).isEqualTo(expResult.getTitle());
        Assertions.assertThat(result.getDescription()).isEqualTo(expResult.getDescription());
        Assertions.assertThat(result.getLink()).isEqualTo(expResult.getLink());
        Assertions.assertThat(result.getPubDate()).isEqualTo(expResult.getPubDate());
    }

    @Test
    public void shouldCreateNewFeedItemIfDescriptionIsNull() {
        final FeedItem expResult = repository.save(new FeedItem("author1", "title1", "link1", LocalDateTime.now()));

        final FeedItem result = repository.findById(expResult.getId()).get();

        Assertions.assertThat(result.getDescription()).isNull();
        Assertions.assertThat(result).isEqualTo(expResult);
        Assertions.assertThat(result.getId()).isEqualTo(expResult.getId());
        Assertions.assertThat(result.getAuthor()).isEqualTo(expResult.getAuthor());
        Assertions.assertThat(result.getTitle()).isEqualTo(expResult.getTitle());
        Assertions.assertThat(result.getLink()).isEqualTo(expResult.getLink());
        Assertions.assertThat(result.getPubDate()).isEqualTo(expResult.getPubDate());
    }
    
    @Test
    public void shouldNotCreateFeedItemIfEntityIsNull() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> repository.save(null));
    }

    @Test
    public void shouldNotCreateFeedItemIfAuthorIsNull() {
        FeedItem newItem = new FeedItem();
        newItem.setTitle("title1");
        newItem.setLink("link1");
        newItem.setPubDate(LocalDateTime.now());

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> repository.save(newItem));
    }

    @Test
    public void shouldNotCreateFeedItemIfTitleIsNull() {
        FeedItem newItem = new FeedItem();
        newItem.setAuthor("author1");
        newItem.setLink("link1");
        newItem.setPubDate(LocalDateTime.now());

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> repository.save(newItem));
    }

    @Test
    public void shouldNotCreateFeedItemIfLinkIsNull() {
        FeedItem newItem = new FeedItem();
        newItem.setAuthor("author1");
        newItem.setTitle("title1");
        newItem.setPubDate(LocalDateTime.now());

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> repository.save(newItem));
    }

    @Test
    public void shouldNotCreateFeedItemIfPubDateIsNull() {
        FeedItem newItem = new FeedItem();
        newItem.setAuthor("author1");
        newItem.setTitle("title1");
        newItem.setLink("link1");

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> repository.save(newItem));
    }

    @Test
    public void shouldUpdateFeedItem() {
        final FeedItem newItem = repository.findById(2l).get();

        Assertions.assertThat(newItem.getAuthor()).isEqualTo("yerlan");
        newItem.setAuthor("author1");

        final FeedItem updated = repository.save(newItem);

        Assertions.assertThat(updated.getAuthor()).isEqualTo("author1");
    }

    @Test
    public void shouldUpdateFeedItemDescription() {
        final FeedItem newItem = repository.findById(3l).get();

        Assertions.assertThat(newItem.getDescription()).isNull();
        newItem.setDescription("desc1");

        final FeedItem updated = repository.save(newItem);

        Assertions.assertThat(updated.getDescription()).isEqualTo("desc1");
    }
}
