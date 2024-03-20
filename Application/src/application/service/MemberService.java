package application.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import application.database.DatabaseContext;
import application.enumeration.Permission;
import application.exception.EntityNotFoundException;
import application.model.Member;
import application.model.Role;
import application.model.Team;

public class MemberService {

    private final DatabaseContext context;

    public MemberService(DatabaseContext context) {
        this.context = context;
    }

    public void create(Member member) {
        try {
            context.open();

            String memberSqlTemplate =
                "INSERT INTO \"member\" (\"id\", \"first_name\", \"last_name\", \"address\") " +
                "VALUES ('%s', '%s', '%s', '%s');";
            String memberSql = String.format(memberSqlTemplate, member.getId().toString(), member.getFirstName(), member.getLastName(), member.getAddress());
            context.write(memberSql);

            if (!member.getRoles().isEmpty()) {
                Optional<String> roles = member.getRoles().stream()
                    .map(role -> "('" + member.getId().toString() + "', '" + role.getId().toString() + "')")
                    .reduce((a, b) -> a + ", " + b);

                String rolesSqlTemplate =
                    "INSERT INTO \"member_role\" (\"member_id\", \"role_id\") " +
                    "VALUES %s;";
                String rolesSql = String.format(rolesSqlTemplate, roles.get());
                context.write(rolesSql);
            }

            if (!member.getTeams().isEmpty()) {
                Optional<String> teams = member.getTeams().stream()
                    .map(team -> "('" + member.getId().toString() + "', '" + team.getId().toString() + "')")
                    .reduce((a, b) -> a + ", " + b);

                String teamsSqlTemplate =
                    "INSERT INTO \"member_team\" (\"member_id\", \"team_id\") " +
                    "VALUES %s;";
                String teamsSql = String.format(teamsSqlTemplate, teams.get());
                context.write(teamsSql);
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Member member) {
        try {
            context.open();

            String memberSqlTemplate =
                "UPDATE \"member\" " +
                "SET \"first_name\" = '%s', \"last_name\" = '%s', \"address\" = '%s' " +
                "WHERE \"id\" = '%s';";
            String memberSql = String.format(memberSqlTemplate, member.getFirstName(), member.getLastName(), member.getAddress(), member.getId().toString());

            int rows = context.write(memberSql);
            if (rows == 0) {
                context.close();
                throw new EntityNotFoundException(member.getId());
            }

            String rolesSqlTemplate =
                "DELETE FROM \"member_role\" " +
                "WHERE \"member_id\" = '%s';";
            String rolesSql = String.format(rolesSqlTemplate, member.getId().toString());
            context.write(rolesSql);

            if (!member.getRoles().isEmpty()) {
                Optional<String> roles = member.getRoles().stream()
                    .map(role -> "('" + member.getId().toString() + "', '" + role.getId().toString() + "')")
                    .reduce((a, b) -> a + ", " + b);

                rolesSqlTemplate =
                    "INSERT INTO \"member_role\" (\"member_id\", \"role_id\") " +
                    "VALUES %s;";
                rolesSql = String.format(rolesSqlTemplate, roles.get());
                context.write(rolesSql);
            }

            String teamsSqlTemplate =
                "DELETE FROM \"member_team\" " +
                "WHERE \"member_id\" = '%s';";
            String teamsSql = String.format(teamsSqlTemplate, member.getId().toString());
            context.write(teamsSql);

            if (!member.getTeams().isEmpty()) {
                Optional<String> teams = member.getTeams().stream()
                    .map(team -> "('" + member.getId().toString() + "', '" + team.getId().toString() + "')")
                    .reduce((a, b) -> a + ", " + b);

                teamsSqlTemplate =
                    "INSERT INTO \"member_team\" (\"member_id\", \"team_id\") " +
                    "VALUES %s;";
                teamsSql = String.format(teamsSqlTemplate, teams.get());
                context.write(teamsSql);
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Member get(UUID id) {
        Member member = null;
        try {
            context.open();

            String memberSqlTemplate =
                "SELECT m.\"first_name\", m.\"last_name\", m.\"address\", mr.\"role_id\", rrp.\"name\", rrp.\"description\", rrp.\"permissions\", mt.\"team_id\", t.\"name\", t.\"activity\" " +
                "FROM \"member\" m " +
                "LEFT JOIN \"member_role\" mr ON m.\"id\" = mr.\"member_id\" " +
                "LEFT JOIN (" +
                    "SELECT r.\"id\", r.\"name\", r.\"description\", GROUP_CONCAT(rp.\"permission\") AS \"permissions\" " +
                    "FROM \"role\" r " +
                    "LEFT JOIN \"role_permission\" rp ON r.\"id\" = rp.\"role_id\" " +
                    "GROUP BY r.\"id\"" +
                ") rrp ON mr.\"role_id\" = rrp.\"id\" " +
                "LEFT JOIN \"member_team\" mt ON m.\"id\" = mt.\"member_id\" " +
                "LEFT JOIN \"team\" t ON mt.\"team_id\" = t.\"id\" " +
                "WHERE m.\"id\" = '%s';";
            String memberSql = String.format(memberSqlTemplate, id.toString());

            ResultSet rs = context.read(memberSql);
            List<String> roleIds = new ArrayList<>();
            List<String> teamIds = new ArrayList<>();
            while (rs.next()) {
                if (member == null) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
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

            if (member == null) {
                context.close();
                throw new EntityNotFoundException(id);
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return member;
    }

    public List<Member> getAll() {
        List<Member> members = new ArrayList<>();
        try {
            context.open();

            String sql =
                "SELECT \"id\", \"first_name\", \"last_name\", \"address\" " +
                "FROM \"member\";";

            ResultSet rs = context.read(sql);
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String address = rs.getString("address");
                members.add(new Member(id, firstName, lastName, address));
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return members;
    }

    public void delete(UUID id) {
        try {
            context.open();

            String sqlTemplate =
                "DELETE FROM \"member\" " +
                "WHERE \"id\" = '%s';";
            String sql = String.format(sqlTemplate, id.toString());

            int rows = context.write(sql);
            if (rows == 0) {
                context.close();
                throw new EntityNotFoundException(id);
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
