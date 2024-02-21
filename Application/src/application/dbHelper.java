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
    
    public dbHelper(String url){
        this.url = url;
    }
    
    public void openDB() {
        try{
            con = DriverManager.getConnection("jdbc:sqlite:" + url);
        } catch (Exception ex){
            System.out.println("DB-Verbindung fehlgeschlagen "+ ex);
        }
        
        try {
            stmt = con.createStatement();
        } catch (Exception ex){
            System.out.println("Das SQL-Statment kann nicht erzeugt werden. "+ ex);
        }
    }
    
    public ResultSet readRS(String SQL) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(SQL);
        }catch (Exception ex){
            System.out.println("Das SQL-Statment kann nicht ausgeführt werden. "+ ex);
        }
        
        return rs;
    }
    
    public void changeDB (String SQL){
        try{
            stmt.execute(SQL);
        }catch (SQLException ex){
            System.out.println("Das SQL-Statment kann nicht ausgeführt werden. "+ ex);
        }
    }
    
    public void closeDB() {
        try{
            this.stmt.close();
            this.con.close();
        } catch (SQLException ex){
            System.out.println("Die Datenbank konnte nicht richtig geschlossen werden. "+ ex);
        }
    }

}
