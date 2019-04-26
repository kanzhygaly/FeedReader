/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
