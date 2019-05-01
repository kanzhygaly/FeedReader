/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.ya.FeedReader.repo;

import kz.ya.FeedReader.model.FeedItem;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

/**
 *
 * @author yerlana
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FeedItemJdbcRepositoryTest {

    @Autowired
    private FeedItemRepository repository;

    @Test
    public void shouldGetFeedItemWhenItExists() {
        final FeedItem expResult = repository.save(new FeedItem("author1","title1", "link1", new Date()));

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
    public void shouldCreateNewFeedItem() {
        FeedItem newItem = new FeedItem("author1","title1", "link1", new Date());
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
        final FeedItem expResult = repository.save(new FeedItem("author1","title1", "link1", new Date()));

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
    public void shouldUpdateFeedItem() {
        final FeedItem newItem = repository.findById(1l).get();

        Assertions.assertThat(newItem.getAuthor()).isEqualTo("yerlan");
        newItem.setAuthor("author1");

        final FeedItem updated = repository.save(newItem);

        Assertions.assertThat(updated.getAuthor()).isEqualTo("author1");
    }

    @Test
    public void shouldNotCreateFeedItemIfAuthorIsNull() {
        FeedItem newItem = new FeedItem();
        newItem.setTitle("title1");
        newItem.setLink("link1");
        newItem.setPubDate(new Date());

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> repository.save(newItem));
    }

    @Test
    public void shouldNotCreateFeedItemIfTitleIsNull() {
        FeedItem newItem = new FeedItem();
        newItem.setAuthor("author1");
        newItem.setLink("link1");
        newItem.setPubDate(new Date());

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> repository.save(newItem));
    }

    @Test
    public void shouldNotCreateFeedItemIfLinkIsNull() {
        FeedItem newItem = new FeedItem();
        newItem.setAuthor("author1");
        newItem.setTitle("title1");
        newItem.setPubDate(new Date());

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
}
