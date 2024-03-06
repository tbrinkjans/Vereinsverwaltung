package application;


import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import application.database.DatabaseContext;
import application.model.Member;

import application.model.Team;
import application.service.TeamService;
import application.model.Role;
import application.service.MemberService;
import application.service.RoleService;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import application.gui.LoginGUI;
import application.service.AuthService;

public class Application {

    public static final String NAME = "Vereinsverwaltung";
    public static final String VERSION = "0.1";
  
    private static List<Object> services;
    private static DatabaseContext context;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Pfad zur Datenbank nicht gefunden!");
            return;
        }
        createDbContext(args[0]);
        registerServices();
        createStartGUI();
    }

    public static String getTitle(String title) {
        return NAME + ": " + title + " (v." + VERSION + ")";

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
        List<Role> roles = new ArrayList<>();

        services = new ArrayList<>();
        services.add(new MemberService(members));
        services.add(new TeamService(teams));
        services.add(new RoleService(roles));
    }

    private static void createDbContext(String url) {
        context = new DatabaseContext(url);
    }

    private static void createStartGUI() throws Exception {
        // Look and Feel einrichten
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        // GUI erstellen
        EventQueue.invokeLater(() -> {
            AuthService authService = getService(AuthService.class);
            new LoginGUI(authService).setVisible(true);
        });
    }

}
