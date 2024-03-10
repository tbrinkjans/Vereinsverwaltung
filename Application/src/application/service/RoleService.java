package application.service;

import application.database.DatabaseContext;


import java.util.List;
import java.util.UUID;

import application.model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import application.enumeration.Permission;

public class RoleService {

    private  List<Role> roles = new ArrayList<>();
    private final DatabaseContext context;
    private ResultSet rs = null;
    private Role role;
    
    
    public RoleService(DatabaseContext context) {
        this.context = context;
    }

    public void create(Role role) {
        String  sql = "INSERT INTO role (id, name, description) "
                + "VALUES("+roleToString(role)+")";
        try{
            context.open();
            context.write(sql);
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
    }

    public Role get(UUID id) {
        String sql = "SELECT role.id, name, description, GROUP_CONCAT(permission.permission) AS permissions FROM role " +
                    "LEFT JOIN permission_role " +
                    "ON role.id = permission_role.role_id " +
                    "LEFT JOIN permission " +
                    "ON permission.id = permission_role.permission_id " +
                    "WHERE role.id = " + "'" + id  + "'" +
                    " GROUP BY role.name;";
                    
        try{
            context.open();
            rs = context.read(sql);
            if (!rs.isBeforeFirst()){
                System.out.println("Das ResultSet ist leer");
            }
            while(rs.next()){
                role = this.roleFromResultSet(rs);
            }
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
        
        return role;
    }

    public List<Role> getAll() {
        String sql = "SELECT role.id, name, description, GROUP_CONCAT(permission.permission) AS permissions FROM role " +
                    "LEFT JOIN permission_role " +
                    "ON role.id = permission_role.role_id " +
                    "LEFT JOIN permission " +
                    "ON permission.id = permission_role.permission_id " +
                    "GROUP BY role.name;";
        try{
            context.open();
            rs = context.read(sql);
            if (!rs.isBeforeFirst()){
                System.out.println("Das ResultSet ist leer");
            }
            while(rs.next()){  
                roles.add(roleFromResultSet(rs));
            }
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
        
        return roles;
    }

    public void update(Role role) {
        String  sql = "UPDATE roles SET name = "+ role.getName() + ", description = " + role.getDescription() +" Where id = '" + role.getId().toString() +"';";
        try{
            context.open();
            context.write(sql);
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
    }

    public void delete(UUID id) {
        String  sql = "DELETE FROM role WHERE id = '" + id + "';";
        try{
            context.open();
            context.write(sql);
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
    }
    
    private Role roleFromResultSet(ResultSet rs) throws SQLException{
        String permissions = rs.getString("permissions");
        String[] permissionArray = permissions.split(",");
        List<Permission> permissionsList = new ArrayList<>();
        for (String singlePermission : permissionArray){
            permissionsList.add(Permission.valueOf(singlePermission));
        }       
        return new Role(UUID.fromString(rs.getString("id")),rs.getString("name"),rs.getString("description"),permissionsList);
    }
    
    private String roleToString(Role role){
        
        return role.getId().toString()+","+role.getName()+","+role.getDescription();
    }

}
