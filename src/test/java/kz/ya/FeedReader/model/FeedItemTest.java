/*
 * Unit Tests for FeedItem
 */
package kz.ya.FeedReader.model;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author yerlana
 */
public class FeedItemTest {

    @Test
    public void objectsShouldBeEqual() {
        Date pubDate = new Date();
        FeedItem obj1 = new FeedItem(1l, "sameTitle", "sameLink", pubDate);
        FeedItem obj2 = new FeedItem(1l, "sameTitle", "sameLink", pubDate);

        boolean isTheSame = obj1.equals(obj2);

        Assert.assertTrue(isTheSame);
    }

    @Test
    public void objectsShouldBeEqualWhenTheyAreTheSameInstance() {
        FeedItem obj = new FeedItem(1l, "sameTitle", "sameLink", new Date());

        boolean isTheSame = obj.equals(obj);

        Assert.assertTrue(isTheSame);
    }

    @Test
    public void objectsShouldNotBeEqualWhenOneIsNull() {
        FeedItem obj = new FeedItem(1l, "sameTitle", "sameLink", new Date());

        boolean isTheSame = obj.equals(null);

        Assert.assertFalse(isTheSame);
    }

    @Test
    public void objectsShouldBeInTheSameBucket() {
        Date pubDate = new Date();
        FeedItem obj1 = new FeedItem(1l, "sameTitle", "sameLink", pubDate);
        FeedItem obj2 = new FeedItem(1l, "sameTitle", "sameLink", pubDate);

        boolean isTheSame = obj1.hashCode() == obj2.hashCode();

        Assert.assertTrue(isTheSame);
    }

    @Test
    public void shouldNotBeTheSameAsAnotherObject() {
        FeedItem obj = new FeedItem(1l, "sameTitle", "sameLink", new Date());
        Object anotherObject = new Object();

        boolean isTheSame = obj.equals(anotherObject);

        Assert.assertFalse(isTheSame);
    }

    @Test
    public void objectsShouldNotBeEqualWhenIdIsDifferent() {
        Date pubDate = new Date();
        FeedItem obj1 = new FeedItem(1l, "sameTitle", "sameLink", pubDate);
        FeedItem obj2 = new FeedItem(2l, "sameTitle", "sameLink", pubDate);

        boolean isTheSame = obj1.equals(obj2);

        Assert.assertFalse(isTheSame);
    }

    @Test
    public void objectsShouldNotBeEqualWhenTitleIsDifferent() {
        Date pubDate = new Date();
        FeedItem obj1 = new FeedItem(1l, "title1", "sameLink", pubDate);
        FeedItem obj2 = new FeedItem(1l, "title2", "sameLink", pubDate);

        boolean isTheSame = obj1.equals(obj2);

        Assert.assertFalse(isTheSame);
    }
    
    @Test
    public void objectsShouldNotBeEqualWhenLinkIsDifferent() {
        Date pubDate = new Date();
        FeedItem obj1 = new FeedItem(1l, "sameTitle", "link1", pubDate);
        FeedItem obj2 = new FeedItem(1l, "sameTitle", "link2", pubDate);

        boolean isTheSame = obj1.equals(obj2);

        Assert.assertFalse(isTheSame);
    }
    
    @Test
    public void objectsShouldNotBeEqualWhenPubDateIsDifferent() {
        FeedItem obj1 = new FeedItem(1l, "sameTitle", "sameLink", new Date());
        FeedItem obj2 = new FeedItem(1l, "sameTitle", "sameLink", new Date(1220227200L * 1000));

        boolean isTheSame = obj1.equals(obj2);

        Assert.assertFalse(isTheSame);
    }
}
