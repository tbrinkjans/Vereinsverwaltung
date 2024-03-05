package application;

import java.util.ArrayList;
import java.util.List;

import application.database.DatabaseContext;
import application.model.Member;
import application.model.Team;
import application.service.MemberService;
import application.service.TeamService;

public class Application {

    private static List<Object> services;
    private static DatabaseContext context;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Pfad zur Datenbank nicht gefunden!");
            return;
        }
        createDbContext(args[0]);
        registerServices();
    }

    public static <T> T getService(Class<T> cls) {
        return services.stream()
            .filter(cls::isInstance)
            .map(cls::cast)
            .findFirst()
            .orElse(null);
    }

    private static void registerServices() {
        List<Member> members = new ArrayList<>();
        List<Team> teams = new ArrayList<>();

        services = new ArrayList<>();
        services.add(new MemberService(members));
        services.add(new TeamService(teams));
    }

    private static void createDbContext(String url) {
        context = new DatabaseContext(url);
    }

}
