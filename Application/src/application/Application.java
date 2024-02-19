package application;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import application.gui.LoginGUI;
import application.model.Member;
import application.service.AuthService;
import application.service.MemberService;

public class Application {

    public static final String NAME = "Vereinsverwaltung";
    public static final String VERSION = "0.1";

    private static List<Object> services;

    public static void main(String[] args) throws Exception {
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

        services = new ArrayList<>();
        services.add(new AuthService(members));
        services.add(new MemberService(members));
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
