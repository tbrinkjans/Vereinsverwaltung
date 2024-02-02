package application.model;

import java.util.UUID;

public class Member extends Person {

    private String address;
    private Team[] teams;
    private Role[] roles;

    public Member(UUID id, String firstName, String lastName, String address, Team[] teams, Role[] roles) {
        super(id, firstName, lastName);
        this.address = address;
        this.teams = teams;
        this.roles = roles;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }

    public Team[] getTeams() {
        return teams;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    public Role[] getRoles() {
        return roles;
    }

}
