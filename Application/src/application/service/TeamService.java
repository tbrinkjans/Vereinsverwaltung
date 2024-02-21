package application.service;

import application.exception.EntityNotFoundException;
import application.model.Team;
import java.util.List;
import java.util.UUID;

public class TeamService {

    private final List<Team> teams;

    public TeamService(List<Team> teams) {
        // Datenbankanbindung später
        this.teams = teams;
    }

    public void create(Team team) {
        teams.add(team);
    }

    public Team get(UUID id) {
        return teams.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    public List<Team> getAll() {
        return teams;
    }

    public void update(Team team) {
        Team update = get(team.getId());
        int index = teams.indexOf(update);
        teams.set(index, update);
    }

    public void delete(UUID id) {
        Team delete = get(id);
        teams.remove(delete);
    }

}
