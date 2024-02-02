package application.model;

import application.common.BaseEntity;
import application.enumeration.Permission;
import java.util.UUID;

public class Role extends BaseEntity {

    private String name;
    private String description;
    private Permission[] permissions;

    public Role(UUID id, String name, String description, Permission[] permissions) {
        super(id);
        this.name = name;
        this.description = description;
        this.permissions = permissions;
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

    public void setPermissions(Permission[] permissions) {
        this.permissions = permissions;
    }

    public Permission[] getPermissions() {
        return permissions;
    }

}
