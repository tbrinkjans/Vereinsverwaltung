package application.service;

import java.util.List;
import java.util.UUID;

import application.exception.EntityNotFoundException;
import application.model.Member;

public class MemberService {

    private final List<Member> members;

    public MemberService(List<Member> members) {
        // Datenbankanbindung später
        this.members = members;
    }

    public void create(Member member) {
        members.add(member);
    }

    public Member get(UUID id) {
        return members.stream()
            .filter(m -> m.getId() == id)
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException(id));
    }

    public List<Member> getAll() {
        return members;
    }

    public void update(Member member) {
        Member update = get(member.getId());
        int index = members.indexOf(update);
        members.set(index, member);
    }

    public void delete(UUID id) {
        Member delete = get(id);
        members.remove(delete);
    }

}
