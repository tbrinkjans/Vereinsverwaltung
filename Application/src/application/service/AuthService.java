package application.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import application.database.DatabaseContext;
import application.enumeration.Permission;
import application.exception.EntityNotFoundException;
import application.model.Member;
import application.model.Role;
import application.model.Team;

public class AuthService {

    private final DatabaseContext context;

    private Member loggedInMember;

    public AuthService(DatabaseContext context) {
        this.context = context;
    }

    public Member authMember(String firstName, String lastName) {
        Member member = null;
        try {
            context.open();

            String memberSqlTemplate = """
                SELECT m."id", m."first_name", m."last_name", m."address", mr."role_id", rrp."name", rrp."description", rrp."permissions", mt."team_id", t."name", t."activity"
                FROM "member" m
                LEFT JOIN "member_role" mr ON m."id" = mr."member_id"
                LEFT JOIN (
                    SELECT r."id", r."name", r."description", GROUP_CONCAT(rp."permission") AS "permissions"
                    FROM "role" r
                    LEFT JOIN "role_permission" rp ON r."id" = rp."role_id"
                    GROUP BY r."id"
                ) rrp ON mr."role_id" = rrp."id"
                LEFT JOIN "member_team" mt ON m."id" = mt."member_id"
                LEFT JOIN "team" t ON mt."team_id" = t."id"
                WHERE (lower(m."first_name") = lower('%s')) AND (lower(m."last_name") = lower('%s'));""";
            String memberSql = String.format(memberSqlTemplate, firstName, lastName);

            ResultSet rs = context.read(memberSql);
            List<String> roleIds = new ArrayList<>();
            List<String> teamIds = new ArrayList<>();
            while (rs.next()) {
                if (member == null) {
                    UUID id = UUID.fromString(rs.getString("id"));
                    firstName = rs.getString("first_name");
                    lastName = rs.getString("last_name");
                    String address = rs.getString("address");
                    member = new Member(id, firstName, lastName, address);
                }

                String roleId = rs.getString("role_id");
                if (roleId != null && !roleIds.contains(roleId)) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String permissions = rs.getString("permissions");

                    if (permissions == null) {
                        member.getRoles().add(new Role(UUID.fromString(roleId), name, description));
                    } else {
                        List<Permission> pList = Arrays.stream(permissions.split(","))
                            .map(Permission::valueOf)
                            .collect(Collectors.toList());

                        member.getRoles().add(new Role(UUID.fromString(roleId), name, description, pList));
                    }

                    roleIds.add(roleId);
                }

                String teamId = rs.getString("team_id");
                if (teamId != null && !teamIds.contains(teamId)) {
                    String name = rs.getString("name");
                    String activity = rs.getString("activity");
                    member.getTeams().add(new Team(UUID.fromString(teamId), name, activity));
                    teamIds.add(teamId);
                }
            }

            if (member != null) {
                loggedInMember = member;
            } else {
                context.close();
                throw new EntityNotFoundException(null);
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return member;
    }

    public Member getLoggedInMember() {
        return loggedInMember;
    }

}
