package application;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import application.database.DatabaseContext;
import application.gui.LoginGUI;
import application.service.AuthService;
import application.service.MemberService;
import application.service.RoleService;
import application.service.TeamService;

public class Application {

    public static final String NAME = "Vereinsverwaltung";

    private static List<Object> services;
    private static DatabaseContext context;

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("Pfad zur Datenbank nicht gefunden!");
            return;
        }
        createDbContext(args[0]);
        registerServices();
        createStartGUI();
    }

    public static String getTitle(String title) {
        return NAME + ": " + title;
    }

    public static <T> T getService(Class<T> cls) {
        return services.stream()
            .filter(cls::isInstance)
            .map(cls::cast)
            .findFirst()
            .orElse(null);
    }

    private static void registerServices() {
        services = new ArrayList<>();
        services.add(new MemberService(context));
        services.add(new AuthService(context));
        services.add(new RoleService(context));
        services.add(new TeamService(context));
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
