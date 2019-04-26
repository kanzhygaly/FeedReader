/*
 * RSS feed controller
 */
package kz.ya.FeedReader.controller;

import java.util.List;
import kz.ya.FeedReader.model.FeedItem;
import kz.ya.FeedReader.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author yerlana
 */
@Controller
@RequestMapping("/rss")
public class RssController {

    private final FeedService service;

    @Autowired
    public RssController(FeedService service) {
        this.service = service;
    }

    @GetMapping()
    public List<FeedItem> all() {
        return service.getLatest10();
    }
}
