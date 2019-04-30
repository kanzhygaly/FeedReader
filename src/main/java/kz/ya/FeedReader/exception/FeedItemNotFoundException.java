/*
 * Not Found Exception for cases when FeedItem was not found by ID
 */
package kz.ya.FeedReader.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author yerlana
 */
// 404 Not Found HTTP status code
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FeedItemNotFoundException extends RuntimeException {
    
    private final Long id;

    public FeedItemNotFoundException(final Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("FeedItem with ID %d was not found", id);
    }
}
