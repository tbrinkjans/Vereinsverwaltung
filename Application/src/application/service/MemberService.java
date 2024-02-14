package application.service;

import application.exception.EntityNotFoundException;
import application.model.Member;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        Optional<Member> member = members.stream().filter(m -> m.getId() == id).findFirst();
        if (!member.isPresent()) {
            throw new EntityNotFoundException(id);
        }
        return member.get();
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
