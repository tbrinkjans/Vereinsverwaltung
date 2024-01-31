package application.model;

import java.util.ArrayList;
import java.util.List;

public class Member extends Person {
    
    private String address;
    private final List<Team> teams;
    private final List<Role> roles;
    
    public Member(String id, String firstName, String lastName, String address, List<Team> teams, List<Role> roles) {
        super(id, firstName, lastName);
        this.address = address;
        this.teams = teams;
        this.roles = roles;
    }
    
    public Member(String id, String firstName, String lastName, String address) {
        this(id, firstName, lastName, address, new ArrayList<>(), new ArrayList<>());
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Role> getRoles() {
        return roles;
    }

}