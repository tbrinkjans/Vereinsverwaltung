package application.service;

import application.exception.EntityNotFoundException;
import application.model.Role;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        Optional<Role> role = roles.stream().filter(r -> r.getId() == id).findFirst();
        if (!role.isPresent()) {
            throw new EntityNotFoundException(id);
        }
        return role.get();
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
