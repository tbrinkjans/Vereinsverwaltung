package application.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {

    private final UUID id;

    public EntityNotFoundException(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
