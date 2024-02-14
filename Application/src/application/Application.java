package application;

import application.model.Member;
import application.service.AuthService;
import application.service.MemberService;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static List<Object> services;

    public static void main(String[] args) throws Exception {
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

        services = new ArrayList<>();
        services.add(new AuthService(members));
        services.add(new MemberService(members));
    }

}
