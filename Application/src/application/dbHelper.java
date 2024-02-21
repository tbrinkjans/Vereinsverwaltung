
package application;
import java.sql.*;


public class dbHelper {
    private Connection con;
    private Statement stmt;
    
    
    public dbHelper(){
        
    }
    
    public void openDB() {
        try{
            //Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:../../Vereinsverwaltung.db";
            con = DriverManager.getConnection(url);
        } catch (Exception ex){
            //ex.printStackTrace();
            System.out.println("DB-Verbindung fehlgeschlagen "+ ex);
        }
        
        try {
            stmt = con.createStatement();
        } catch (Exception ex){
            //System.out.println("Das SQL-Statment kann nicht erzeugt werden. "+ ex);
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

