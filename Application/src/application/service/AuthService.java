package application.service;

import application.database.DatabaseContext;
import java.util.List;

import application.exception.EntityNotFoundException;
import application.model.Member;

public class AuthService {

    private final List<Member> members;
    private final DatabaseContext context;
    private final MemberService memberService;

    public AuthService(DatabaseContext context) {
        this.context = context;
        memberService = new MemberService(context);
        members = memberService.getAll();
    }

    public Member authMember(String firstName, String lastName) {
        return members.stream()
            .filter(m -> m.getFirstName().equalsIgnoreCase(firstName) && m.getLastName().equalsIgnoreCase(lastName))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException(null));
    }

}
