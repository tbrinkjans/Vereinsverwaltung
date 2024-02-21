package application.model;

import application.common.BaseEntity;
import application.enumeration.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Role extends BaseEntity {

    private String name;
    private String description;
    private final List<Permission> permissions;

    public Role(UUID id, String name, String description, List<Permission> permissions) {
        super(id);
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }

    public Role(UUID id, String name, String description) {
        this(id, name, description, new ArrayList<>());
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

    public List<Permission> getPermissions() {
        return permissions;
    }

}
