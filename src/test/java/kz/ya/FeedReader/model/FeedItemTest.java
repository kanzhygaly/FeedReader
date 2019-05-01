/*
 * Unit Tests for FeedItem
 */
package kz.ya.FeedReader.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Date;

/**
 *
 * @author yerlana
 */
public class FeedItemTest {

    @Test
    public void objectsShouldBeEqual() {
        Date pubDate = new Date();
        FeedItem obj1 = new FeedItem(1l, "sameAuthor","sameTitle", "sameLink", pubDate);
        FeedItem obj2 = new FeedItem(1l, "sameAuthor","sameTitle", "sameLink", pubDate);

        boolean isTheSame = obj1.equals(obj2);

        Assertions.assertThat(isTheSame).isTrue();
    }

    @Test
    public void objectsShouldBeEqualWhenTheyAreTheSameInstance() {
        FeedItem obj = new FeedItem(1l, "sameAuthor","sameTitle", "sameLink", new Date());

        boolean isTheSame = obj.equals(obj);

        Assertions.assertThat(isTheSame).isTrue();
    }

    @Test
    public void objectsShouldNotBeEqualWhenOneIsNull() {
        FeedItem obj = new FeedItem(1l, "sameAuthor","sameTitle", "sameLink", new Date());

        boolean isTheSame = obj.equals(null);

        Assertions.assertThat(isTheSame).isFalse();
    }

    @Test
    public void objectsShouldBeInTheSameBucket() {
        Date pubDate = new Date();
        FeedItem obj1 = new FeedItem(1l, "sameAuthor","sameTitle", "sameLink", pubDate);
        FeedItem obj2 = new FeedItem(1l, "sameAuthor","sameTitle", "sameLink", pubDate);

        boolean isTheSame = obj1.hashCode() == obj2.hashCode();

        Assertions.assertThat(isTheSame).isTrue();
    }

    @Test
    public void shouldNotBeTheSameAsAnotherObject() {
        FeedItem obj = new FeedItem(1l, "sameAuthor","sameTitle", "sameLink", new Date());
        Object anotherObject = new Object();

        boolean isTheSame = obj.equals(anotherObject);

        Assertions.assertThat(isTheSame).isFalse();
    }

    @Test
    public void objectsShouldNotBeEqualWhenIdIsDifferent() {
        Date pubDate = new Date();
        FeedItem obj1 = new FeedItem(1l, "sameAuthor","sameTitle", "sameLink", pubDate);
        FeedItem obj2 = new FeedItem(2l, "sameAuthor","sameTitle", "sameLink", pubDate);

        boolean isTheSame = obj1.equals(obj2);

        Assertions.assertThat(isTheSame).isFalse();
    }

    @Test
    public void objectsShouldNotBeEqualWhenAuthorIsDifferent() {
        Date pubDate = new Date();
        FeedItem obj1 = new FeedItem(1l, "author1","sameTitle", "sameLink", pubDate);
        FeedItem obj2 = new FeedItem(1l, "author2","sameTitle", "sameLink", pubDate);

        boolean isTheSame = obj1.equals(obj2);

        Assertions.assertThat(isTheSame).isFalse();
    }

    @Test
    public void objectsShouldNotBeEqualWhenTitleIsDifferent() {
        Date pubDate = new Date();
        FeedItem obj1 = new FeedItem(1l, "sameAuthor","title1", "sameLink", pubDate);
        FeedItem obj2 = new FeedItem(1l, "sameAuthor","title2", "sameLink", pubDate);

        boolean isTheSame = obj1.equals(obj2);

        Assertions.assertThat(isTheSame).isFalse();
    }
    
    @Test
    public void objectsShouldNotBeEqualWhenLinkIsDifferent() {
        Date pubDate = new Date();
        FeedItem obj1 = new FeedItem(1l, "sameAuthor","sameTitle", "link1", pubDate);
        FeedItem obj2 = new FeedItem(1l, "sameAuthor","sameTitle", "link2", pubDate);

        boolean isTheSame = obj1.equals(obj2);

        Assertions.assertThat(isTheSame).isFalse();
    }
    
    @Test
    public void objectsShouldNotBeEqualWhenPubDateIsDifferent() {
        FeedItem obj1 = new FeedItem(1l, "sameAuthor","sameTitle", "sameLink", new Date());
        FeedItem obj2 = new FeedItem(1l, "sameAuthor","sameTitle", "sameLink", new Date(1220227200L * 1000));

        boolean isTheSame = obj1.equals(obj2);

        Assertions.assertThat(isTheSame).isFalse();
    }

    @Test
    public void objectsShouldBeEqualWhenDescriptionIsDifferent() {
        Date pubDate = new Date();
        FeedItem obj1 = new FeedItem(1l, "sameAuthor","sameTitle", "sameLink", pubDate);
        obj1.setDescription("description1");
        FeedItem obj2 = new FeedItem(1l, "sameAuthor","sameTitle", "sameLink", pubDate);
        obj1.setDescription("description2");

        boolean isTheSame = obj1.equals(obj2);

        Assertions.assertThat(isTheSame).isTrue();
    }
}
