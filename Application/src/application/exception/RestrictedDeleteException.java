package application.exception;

import java.util.UUID;

public class RestrictedDeleteException extends RuntimeException {

    private final UUID id;

    public RestrictedDeleteException(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
