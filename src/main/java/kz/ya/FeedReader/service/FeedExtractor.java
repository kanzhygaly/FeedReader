/*
 * General Feed Extractor Interface
 */
package kz.ya.FeedReader.service;

import java.util.List;
import kz.ya.FeedReader.model.FeedItem;

/**
 *
 * @author yerlana
 */
public interface FeedExtractor {
    
    List<FeedItem> extractItems(String feedUrl);
}
