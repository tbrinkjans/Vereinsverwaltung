package application.service;

import application.exception.EntityNotFoundException;
import application.model.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class RoleService {

    private final List<Role> roles;

    public RoleService() {
        // Datenbankanbindung später
        roles = new ArrayList<>();
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

    public List<Role> searchAll(Predicate<Role> match) {
        return roles.stream().filter(match).toList();
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
