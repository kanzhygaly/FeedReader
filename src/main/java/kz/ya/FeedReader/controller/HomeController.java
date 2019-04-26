/*
 * Default controller
 */
package kz.ya.FeedReader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author yerlana
 */
@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String index() {
        return "Welcome to RSS Feed! If you want to get latest feeds please go to /rss";
    }
}
