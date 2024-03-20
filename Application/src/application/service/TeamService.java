package application.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.sqlite.SQLiteErrorCode;
import org.sqlite.SQLiteException;

import application.database.DatabaseContext;
import application.exception.EntityNotFoundException;
import application.exception.RestrictedDeleteException;
import application.model.Team;

public class TeamService {

    private final DatabaseContext context;

    public TeamService(DatabaseContext context) {
        this.context = context;
    }

    public void create(Team team) {
        try {
            context.open();

            String sqlTemplate = 
                "INSERT INTO \"team\" (\"id\", \"name\", \"activity\") "+
                "VALUES ('%s', '%s', '%s');";
            String sql = String.format(sqlTemplate, team.getId().toString(), team.getName(), team.getActivity());
            context.write(sql);

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Team team) {
        try {
            context.open();

            String sqlTemplate = 
                "UPDATE \"team\" "+
                "SET \"name\" = \"%s\", \"activity\" = \"%s\" "+
                "WHERE \"id\" = '%s';";
            String sql = String.format(sqlTemplate, team.getName(), team.getActivity(), team.getId().toString());

            int rows = context.write(sql);
            if (rows == 0) {
                context.close();
                throw new EntityNotFoundException(team.getId());
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Team get(UUID id) {
        Team team = null;
        try {
            context.open();

            String sqlTemplate =
                "SELECT \"name\", \"activity\" "+
                "FROM \"team\" "+
                "WHERE \"id\" = '%s';";
            String sql = String.format(sqlTemplate, id.toString());

            ResultSet rs = context.read(sql);
            if (!rs.next()) {
                context.close();
                throw new EntityNotFoundException(id);
            } else {
                String name = rs.getString("name");
                String activity = rs.getString("activity");
                team = new Team(id, name, activity);
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return team;
    }

    public List<Team> getAll() {
        List<Team> teams = new ArrayList<>();
        try {
            context.open();

            String sql = 
                "SELECT \"id\", \"name\", \"activity\" "+
                "FROM \"team\";";

            ResultSet rs = context.read(sql);
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                String name = rs.getString("name");
                String activity = rs.getString("activity");
                teams.add(new Team(id, name, activity));
            }

            context.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return teams;
    }

    public void delete(UUID id) {
        try {
            context.open();

            String sqlTemplate = 
                "DELETE FROM \"team\" "+
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
