package application.model;

import application.common.BaseEntity;
import application.enumeration.Permission;
import java.util.List;

public class Role extends BaseEntity {
    
    private String name;
    private String description;
    private final List<Permission> permissions;
    
    public Role(String id, String name, String description, List<Permission> permissions) {
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

    public List<Permission> getPermissions() {
        return permissions;
    }
    
}