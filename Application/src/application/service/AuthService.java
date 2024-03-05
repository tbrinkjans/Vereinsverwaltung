package application.service;

import java.util.List;

import application.exception.EntityNotFoundException;
import application.model.Member;

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

}
