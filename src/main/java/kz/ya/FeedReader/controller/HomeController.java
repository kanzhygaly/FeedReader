/*
 * Default controller
 */
package kz.ya.FeedReader.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author yerlana
 */
@Controller
public class HomeController {
    
    @RequestMapping("/")
    public ResponseEntity<String> index() {
        // return 200 OK HTTP status code
        return ResponseEntity.ok("Welcome to RSS Feed! If you want to get latest feeds please go to /rss");
    }
}
