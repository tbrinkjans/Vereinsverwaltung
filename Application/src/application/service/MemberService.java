package application.service;

import application.database.DatabaseContext;
import java.util.List;
import java.util.UUID;

import application.model.Member;
import application.model.Role;
import application.model.Team;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberService {

    private final List<Member> members = new ArrayList<>();
    private final DatabaseContext context;
    private ResultSet rs = null;
    private Member member;

    public MemberService(DatabaseContext context) {
        this.context = context;
    }

    public void create(Member member) {
        String  sql = "INSERT INTO members (id, last_name, first_name, address) "
                + "VALUES("+roleToString(member)+")";
        try{
            context.open();
            context.write(sql);
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
    }

    public Member get(UUID id) {
        String sql = "SELECT members.id, last_name, first_name, address, role_id, teams_id " +
                    "FROM members " +
                    "LEFT JOIN member_role " +
                    "ON members.id = member_role.members_id " +
                    "LEFT JOIN members_team " +
                    "ON members.id = members_team.members_id " +
                    "WHERE id = " + id +";";
        try{
            context.open();
            rs = context.read(sql);
            while(rs.next()){  
               member = this.memberFromResultSet(rs);
            }
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
        return member;
    }

    public List<Member> getAll() {
        String sql = "SELECT members.id, last_name, first_name, address, role_id, teams_id " +
                    "FROM members " +
                    "LEFT JOIN member_role " +
                    "ON members.id = member_role.members_id " +
                    "LEFT JOIN members_team " +
                    "ON members.id = members_team.members_id;";
        try{
            context.open();
            rs = context.read(sql);
            while(rs.next()){  
                members.add(memberFromResultSet(rs));
            }
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
        return members;
    }

    public void update(Member member) {
        String  sql = "UPDATE teams SET last_name = "+ member.getLastName() + ", first_name = " + member.getFirstName() +", address = "+ member.getAddress() +" Where id = " + member.getId().toString() +";";
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
        String  sql = "DELETE FROM members WHERE id = " + id + ";";
        try{
            context.open();
            context.write(sql);
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
    }
    
    private Member memberFromResultSet(ResultSet rs) throws SQLException{
        TeamService teamService = new TeamService(context);
        RoleService roleService = new RoleService(context);
        List<Team> teams = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        teams.add(teamService.get(UUID.fromString(rs.getString("teams_id"))));
        roles.add(roleService.get(UUID.fromString(rs.getString("role_id"))));
        return new Member(UUID.fromString(rs.getString("id")),rs.getString("firstName"),rs.getString("lastName"),rs.getString("address"), teams, roles);
    }
    
    private String roleToString(Member member){
        
        return member.getId().toString()+","+member.getLastName()+","+member.getFirstName()+","+member.getAddress();
    }

}
