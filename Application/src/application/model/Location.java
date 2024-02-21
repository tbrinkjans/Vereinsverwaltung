package application.model;

import application.common.BaseEntity;
import java.util.UUID;

public class Location extends BaseEntity {

    private String name;
    private String description;
    private boolean indoor;
    private int size;

    public Location(UUID id, String name, String description, boolean indoor, int size) {
        super(id);
        this.name = name;
        this.description = description;
        this.indoor = indoor;
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

}
