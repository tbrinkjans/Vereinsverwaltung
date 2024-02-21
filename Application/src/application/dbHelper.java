package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbHelper {

    private final String url;

    private Connection con;
    private Statement stmt;

    public dbHelper(String url) {
        this.url = url;
    }

    public void open() throws SQLException {
        try {
            System.out.println("DB-Verbindung zu " + url + " wird aufgebaut...");
            con = DriverManager.getConnection("jdbc:sqlite:" + url);
            stmt = con.createStatement();
            System.out.println("DB-Verbindung erfolgreich aufgebaut.");
        } catch (SQLException ex) {
            System.err.println("DB-Verbindung konnte nicht aufgebaut werden!");
            throw ex;
        }
    }

    public void write(String sql) throws SQLException {
        try {
            System.out.println("Führe SQL-Statement aus...\n" + sql);
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            throw ex;
        }
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
            con.close();
            System.out.println("DB-Verbindung erfolgreich abgebaut.");
        } catch (SQLException ex) {
            System.err.println("DB-Verbindung konnte nicht abgebaut werden!");
            throw ex;
        }
    }

}
