/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.ya.FeedReader.service;

import java.util.List;
import kz.ya.FeedReader.model.FeedItem;
import kz.ya.FeedReader.repo.FeedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yerlana
 */
@Service
public class FeedService {
    
    private final FeedItemRepository repository;

    @Autowired
    public FeedService(FeedItemRepository repository) {
	this.repository = repository;
    }
    
    public List<FeedItem> getLatest10() {
	return repository.findLast(10);
    }
}
