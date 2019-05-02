/*
 * FeedItemJdbcRepository tests for delete methods
 */
package kz.ya.FeedReader.repo;

import kz.ya.FeedReader.model.FeedItem;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import kz.ya.FeedReader.exception.FeedItemNotFoundException;

/**
 *
 * @author yerlana
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DeleteFeedItemJdbcRepoTest {

    @Autowired
    private FeedItemRepository repository;

    @Test
    public void shouldDeleteFeedItemWhenItExists() {
        Optional<FeedItem> toDelete = repository.findById(1l);
        Assertions.assertThat(toDelete.isPresent()).isTrue();

        long expectedSize = 10l;
        long size = repository.count();
        Assertions.assertThat(size).isEqualTo(expectedSize);

        repository.deleteById(1l);

        Optional<FeedItem> deleted = repository.findById(1l);
        Assertions.assertThat(deleted.isPresent()).isFalse();

        size = repository.count();
        Assertions.assertThat(size).isEqualTo(expectedSize - 1);
    }

    @Test
    public void shouldNotDeleteWhenFeedItemDoesNotExist() {
        long wrongId = -1l;
        Optional<FeedItem> notExistItem = repository.findById(wrongId);
        Assertions.assertThat(notExistItem.isPresent()).isFalse();

        Assertions.assertThatThrownBy(() -> repository.deleteById(wrongId))
                .isInstanceOf(FeedItemNotFoundException.class);
    }

    @Test
    public void shouldGetIllegalArgumentExceptionWhenIdIsNull() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> repository.deleteById(null));
    }
}
