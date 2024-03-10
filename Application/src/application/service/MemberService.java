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
    private final TeamService teamService;
    private final RoleService roleService;

    public MemberService(DatabaseContext context) {
        this.context = context;
        teamService = new TeamService(context);
        roleService = new RoleService(context);
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
        for (Team teams :member.getTeams()){
            if (teamService.get(teams.getId()) == null){
                teamService.create(teams);
            }else{
                setTeamRelation(member.getId(), teams.getId());
            }
        }
        for (Role role :member.getRoles()){
            if (roleService.get(role.getId()) == null){
                roleService.create(role);
            }else{
                setRoleRelation(member.getId(), role.getId());
            }
        }
    }

    public Member get(UUID id) {
        String sql = "SELECT members.id, last_name, first_name, address, role_id, teams_id " +
                    "FROM members " +
                    "LEFT JOIN member_role " +
                    "ON members.id = member_role.members_id " +
                    "LEFT JOIN members_team " +
                    "ON members.id = members_team.members_id " +
                    "WHERE members.id = '" + id +"';";
        try{
            context.open();
            rs = context.read(sql);
            if (!rs.isBeforeFirst()){
                System.out.println("Das ResultSet ist leer");
            }
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
            if (!rs.isBeforeFirst()){
                System.out.println("Das ResultSet ist leer");
            }
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
        for (Team teams :member.getTeams()){
            teamService.update(teams);
            setTeamRelation(member.getId(), teams.getId());
        }
        for (Role role :member.getRoles()){
            roleService.update(role);
            setRoleRelation(member.getId(), role.getId());
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
        List<Team> teams = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        teams.add(teamService.get(UUID.fromString(rs.getString("teams_id"))));
        roles.add(roleService.get(UUID.fromString(rs.getString("role_id"))));
        System.out.println(rs.getString("id"));
        return new Member(UUID.fromString(rs.getString("id")),rs.getString("first_name"),rs.getString("last_name"),rs.getString("address"), teams, roles);
    }
    
    private String roleToString(Member member){
        
        return member.getId().toString()+","+member.getLastName()+","+member.getFirstName()+","+member.getAddress();
    }
    
    private void setTeamRelation(UUID members_id, UUID team_id){
        String sql = "SELECT * FROM member_role WHERE members_id = '"+ members_id +"' AND role_id = '"+ team_id +"';";
        try{
            context.open();
            rs = context.read(sql);
            if (!rs.isBeforeFirst()){
                String updateSQL = "INSERT INTO member_role (id, members_id, teams_id) VALUES ('"+ UUID.randomUUID() +"', '"+ members_id +"', '"+ team_id +"')";
                context.write(updateSQL);
            }
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
    }
    
    private void setRoleRelation(UUID members_id, UUID role_id){
        String sql = "SELECT * FROM member_role WHERE members_id = '"+ members_id +"' AND role_id = '"+ role_id +"';";
        try{
            context.open();
            rs = context.read(sql);
            if (!rs.isBeforeFirst()){
                String updateSQL = "INSERT INTO member_role (id, members_id, role_id) VALUES ('"+ UUID.randomUUID() +"', '"+ members_id +"', '"+ role_id +"')";
                context.write(updateSQL);
            }
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
    }

}
