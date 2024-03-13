package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseContext {

    private final String url;

    private Connection conn;
    private Statement stmt;

    public DatabaseContext(String url) {
        this.url = url;
    }

    public void open() throws SQLException {
        try {
            System.out.println("DB-Verbindung zu " + url + " wird aufgebaut...");
            conn = DriverManager.getConnection("jdbc:sqlite:" + url);
            stmt = conn.createStatement();
            System.out.println("DB-Verbindung erfolgreich aufgebaut.");
        } catch (SQLException ex) {
            System.err.println("DB-Verbindung konnte nicht aufgebaut werden!");
            throw ex;
        }
    }

    public int write(String sql) throws SQLException {
        int rows;
        try {
            System.out.println("Führe SQL-Statement aus...\n" + sql);
            rows = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            throw ex;
        }
        return rows;
    }

    public ResultSet read(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            System.out.println("Führe SQL-Statement aus...\n" + sql);
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            throw ex;
        }
        return rs;
    }

    public void close() throws SQLException {
        try {
            System.out.println("DB-Verbindung zu " + url + " wird abgebaut...");
            stmt.close();
            conn.close();
            System.out.println("DB-Verbindung erfolgreich abgebaut.");
        } catch (SQLException ex) {
            System.err.println("DB-Verbindung konnte nicht abgebaut werden!");
            throw ex;
        }
    }

}
