package application.service;

import application.exception.EntityNotFoundException;
import application.model.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class MemberService {

    private final List<Member> members;

    public MemberService() {
        // Datenbankanbindung später
        members = new ArrayList<>();
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
    
    public List <Member> findbyname(String firstname, String lastname){
        return members;
    }

    public List<Member> searchAll(Predicate<Member> match) {
        return members.stream().filter(match).toList();
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
public List<Member> findByName(String firstName, String lastName) {
    return members.stream()
                 .filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
                 .toList();
}

}
