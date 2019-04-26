package kz.ya.FeedReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author yerlana
 */
@SpringBootApplication
@EnableScheduling
@Configuration
public class FeedReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedReaderApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
