package application.service;

import application.enumeration.Permission;
import application.exception.EntityNotFoundException;
import application.model.Member;
import application.model.Role;
import java.util.List;

public class AuthService {

    private final List<Member> members;

    public AuthService(List<Member> members) {
        this.members = members;
    }

    public Member authMember(String firstName, String lastName) {
        return members.stream()
                .filter(m -> m.getFirstName().equalsIgnoreCase(firstName) && m.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(null));
    }

    public boolean hasPermission(Member member, Permission permission) {
        List<Permission> permissions = member.getRoles().stream()
                .map(Role::getPermissions)
                .flatMap(List::stream)
                .distinct()
                .toList();

        return permissions.contains(permission);
    }

}
