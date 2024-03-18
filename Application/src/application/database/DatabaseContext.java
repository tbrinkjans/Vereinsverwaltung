package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

import application.util.Time;

public class DatabaseContext {

    private final String url;
    private final SQLiteConfig config;

    private Connection conn;
    private Statement stmt;

    public DatabaseContext(String url) {
        this.url = url;

        this.config = new SQLiteConfig();
        this.config.enforceForeignKeys(true);
    }

    public void open() throws SQLException {
        try {
            System.out.printf("\n[%s] DB-Verbindung wird aufgebaut... ", Time.getTime());
            conn = DriverManager.getConnection("jdbc:sqlite:" + url, config.toProperties());
            stmt = conn.createStatement();
            System.out.println("ERFOLGREICH!");
        } catch (SQLException ex) {
            System.out.println("FEHLGESCHLAGEN!");
            throw ex;
        }
    }

    public int write(String sql) throws SQLException {
        int rows;
        try {
            System.out.printf("[%s] Führe SQL-Statement aus:\n%s\n", Time.getTime(), sql);
            rows = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            throw ex;
        }
        return rows;
    }

    public ResultSet read(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            System.out.printf("[%s] Führe SQL-Statement aus:\n%s\n", Time.getTime(), sql);
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Das SQL-Statement konnte nicht ausgeführt werden!");
            throw ex;
        }
        return rs;
    }

    public void close() throws SQLException {
        try {
            System.out.printf("[%s] DB-Verbindung wird abgebaut... ", Time.getTime());
            stmt.close();
            conn.close();
            System.out.println("ERFOLGREICH!");
        } catch (SQLException ex) {
            System.out.println("FEHLGESCHLAGEN!");
            throw ex;
        }
    }

}
