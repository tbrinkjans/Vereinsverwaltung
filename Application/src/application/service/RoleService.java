package application.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.sqlite.SQLiteErrorCode;
import org.sqlite.SQLiteException;

import application.database.DatabaseContext;
import application.enumeration.Permission;
import application.exception.EntityNotFoundException;
import application.exception.RestrictedDeleteException;
import application.model.Role;

public class RoleService {

    private final DatabaseContext context;

    public RoleService(DatabaseContext context) {
        this.context = context;
    }

    public void create(Role role) {
        try {
            context.open();

            String roleSqlTemplate =
                "INSERT INTO \"role\" (\"id\", \"name\", \"description\") " +
                "VALUES ('%s', '%s', '%s');";
            String roleSql = String.format(roleSqlTemplate, role.getId().toString(), role.getName(), role.getDescription());
            context.write(roleSql);

            if (!role.getPermissions().isEmpty()) {
                Optional<String> permissions = role.getPermissions().stream()
                    .map(permission -> "('" + role.getId().toString() + "', '" + permission.toString() + "')")
                    .reduce((a, b) -> a + ", " + b);

                String permissionsSqlTemplate =
                    "INSERT INTO \"role_permission\" (\"role_id\", \"permission\") " +
                    "VALUES %s;";
                String permissionsSql = String.format(permissionsSqlTemplate, permissions.get());
                context.write(permissionsSql);
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Role role) {
        try {
            context.open();

            String roleSqlTemplate =
                "UPDATE \"role\" " +
                "SET \"name\" = '%s', \"description\" = '%s' " +
                "WHERE \"id\" = '%s';";
            String roleSql = String.format(roleSqlTemplate, role.getName(), role.getDescription(), role.getId().toString());

            int rows = context.write(roleSql);
            if (rows == 0) {
                context.close();
                throw new EntityNotFoundException(role.getId());
            }

            String permissionsSqlTemplate =
                "DELETE FROM \"role_permission\" " +
                "WHERE \"role_id\" = '%s';";
            String permissionsSql = String.format(permissionsSqlTemplate, role.getId().toString());
            context.write(permissionsSql);

            if (!role.getPermissions().isEmpty()) {
                Optional<String> permissions = role.getPermissions().stream()
                    .map(permission -> "('" + role.getId().toString() + "', '" + permission.toString() + "')")
                    .reduce((a, b) -> a + ", " + b);

                permissionsSqlTemplate =
                    "INSERT INTO \"role_permission\" (\"role_id\", \"permission\") " +
                    "VALUES %s;";
                permissionsSql = String.format(permissionsSqlTemplate, permissions.get());
                context.write(permissionsSql);
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Role get(UUID id) {
        Role role = null;
        try {
            context.open();

            String roleSqlTemplate =
                "SELECT r.\"name\", r.\"description\", GROUP_CONCAT(rp.\"permission\") AS \"permissions\" " +
                "FROM \"role\" r " +
                "LEFT JOIN \"role_permission\" rp ON r.\"id\" = rp.\"role_id\" " +
                "WHERE r.\"id\" = '%s';";
            String roleSql = String.format(roleSqlTemplate, id.toString());

            ResultSet rs = context.read(roleSql);
            if (!rs.next()) {
                context.close();
                throw new EntityNotFoundException(id);
            } else {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String permissions = rs.getString("permissions");

                if (permissions == null) {
                    role = new Role(id, name, description);
                } else {
                    List<Permission> pList = Arrays.stream(permissions.split(","))
                        .map(Permission::valueOf)
                        .collect(Collectors.toList());

                    role = new Role(id, name, description, pList);
                }
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return role;
    }

    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        try {
            context.open();

            String sql =
                "SELECT \"id\", \"name\", \"description\" " +
                "FROM \"role\";";

            ResultSet rs = context.read(sql);
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                String name = rs.getString("name");
                String description = rs.getString("description");
                roles.add(new Role(id, name, description));
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return roles;
    }

    public void delete(UUID id) {
        try {
            context.open();

            String sqlTemplate =
                "DELETE FROM \"role\" " +
                "WHERE \"id\" = '%s';";
            String sql = String.format(sqlTemplate, id.toString());

            int rows = context.write(sql);
            if (rows == 0) {
                context.close();
                throw new EntityNotFoundException(id);
            }

            context.close();
        } catch (SQLException ex) {
            if (ex instanceof SQLiteException) {
                SQLiteException exception = (SQLiteException) ex;
                if (exception.getResultCode() == SQLiteErrorCode.SQLITE_CONSTRAINT_TRIGGER) {
                    throw new RestrictedDeleteException(id);
                }
            }
            ex.printStackTrace();
        }
    }

}
