package application.common;

public class BaseEntity {

    private final String id;
    
    public BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
}