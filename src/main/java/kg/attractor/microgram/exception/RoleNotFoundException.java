package kg.attractor.microgram.exception;

import java.util.NoSuchElementException;

public class RoleNotFoundException extends NoSuchElementException {
    public RoleNotFoundException() {
        super("Role not found!");
    }
}
