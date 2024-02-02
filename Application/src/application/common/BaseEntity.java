package application.common;

import java.util.UUID;

public class BaseEntity {

    private final UUID id;

    public BaseEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
