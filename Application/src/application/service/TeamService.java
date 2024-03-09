package application.service;

import application.database.DatabaseContext;
import java.util.List;
import java.util.UUID;

import application.model.Team;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamService {

    private final List<Team> teams = new ArrayList<>();
    private final DatabaseContext context;
    private ResultSet rs = null;
    private Team team;

    public TeamService(DatabaseContext context) {
         this.context = context;
    }

    public void create(Team team) {
        String  sql = "INSERT INTO teams (id, name, activity) "
                + "VALUES("+roleToString(team)+")";
        try{
            context.open();
            context.write(sql);
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
    }

    public Team get(UUID id) {
        String sql = "SELECT id, name, activity FROM teams WHERE id = " + id +";";
        try{
            context.open();
            rs = context.read(sql);
            while(rs.next()){  
               team = this.teamFromResultSet(rs);
            }
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
        return team;
    }

    public List<Team> getAll() {
        String sql = "SELECT id, name, activity FROM teams;";
        try{
            context.open();
            rs = context.read(sql);
            while(rs.next()){  
                teams.add(teamFromResultSet(rs));
            }
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
        return teams;
    }

    public void update(Team team) {
        String  sql = "UPDATE teams SET name = "+ team.getName() + ", activity = " + team.getActivity() +" Where id = " + team.getId().toString() +";";
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
        String  sql = "DELETE FROM teams WHERE id = " + id + ";";
        try{
            context.open();
            context.write(sql);
            context.close();
        }catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            System.err.println( ex);
        }
    }
    private Team teamFromResultSet(ResultSet rs) throws SQLException{
          
        return new Team(UUID.fromString(rs.getString("id")),rs.getString("name"),rs.getString("activity"));
    }
    
    private String roleToString(Team team){
        
        return team.getId().toString()+","+team.getName()+","+team.getActivity();
    }

}
