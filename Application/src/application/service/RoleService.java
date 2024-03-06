package application.service;

import java.util.List;
import java.util.UUID;

import application.exception.EntityNotFoundException;
import application.model.Role;

public class RoleService {

    private final List<Role> roles;

    public RoleService(List<Role> roles) {
        // Datenbankanbindung später
        this.roles = roles;
    }

    public void create(Role role) {
        roles.add(role);
    }

    public Role get(UUID id) {
        return roles.stream()
            .filter(r -> r.getId() == id)
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException(id));
    }

    public List<Role> getAll() {
        return roles;
    }

    public void update(Role role) {
        Role update = get(role.getId());
        int index = roles.indexOf(update);
        roles.set(index, role);
    }

    public void delete(UUID id) {
        Role delete = get(id);
        roles.remove(delete);
    }

}
