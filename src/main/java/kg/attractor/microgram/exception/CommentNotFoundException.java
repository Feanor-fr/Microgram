package kg.attractor.microgram.exception;

import java.util.NoSuchElementException;

public class CommentNotFoundException extends NoSuchElementException {
    public CommentNotFoundException() {
        super("Comment not found!");
    }
}
