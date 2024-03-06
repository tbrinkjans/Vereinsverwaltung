package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import application.enumeration.Permission;

public class Member extends Person {

    private String address;
    private List<Team> teams;
    private List<Role> roles;

    public Member(UUID id, String firstName, String lastName, String address, List<Team> teams, List<Role> roles) {
        super(id, firstName, lastName);
        this.address = address;
        this.teams = teams;
        this.roles = roles;
    }

    public Member(UUID id, String firstName, String lastName, String address) {
        this(id, firstName, lastName, address, new ArrayList<>(), new ArrayList<>());
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public boolean hasPermission(Permission permission) {
        return roles.stream()
            .map(Role::getPermissions)
            .flatMap(List::stream)
            .anyMatch(p -> p.equals(permission));
    }

}
