/*
 * Entity class for storing and transfering RSS feed entry data
 */
package kz.ya.FeedReader.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author yerlana
 */
public class FeedItem {
    
    private Long id;
    private String author;
    private String title;
    private String description;
    private String link;
    private LocalDateTime pubDate;

    public FeedItem() {
    }

    public FeedItem(String author, String title, String link, LocalDateTime pubDate) {
        this.author = author;
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
    }

    public FeedItem(Long id, String author, String title, String link, LocalDateTime pubDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDateTime pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedItem feedItem = (FeedItem) o;
        return Objects.equals(id, feedItem.id) &&
                Objects.equals(author, feedItem.author) &&
                Objects.equals(title, feedItem.title) &&
                Objects.equals(link, feedItem.link) &&
                Objects.equals(pubDate, feedItem.pubDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, link, pubDate);
    }

    @Override
    public String toString() {
        return String.format("FeedItem [id=%d, title=%s, link=%s, pubDate=%s]",
                id, title, link, pubDate);
    }
}
