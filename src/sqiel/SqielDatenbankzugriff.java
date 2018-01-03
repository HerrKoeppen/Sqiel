package sqiel;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class SqielDatenbankzugriff {
     /**
     * Connect to a sample database
     */
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:sample.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
     public static void kreierebenutzerinfo() {
        // SQLite connection string
        String url = "jdbc:sqlite:BenutzerInfo.db";
        /*
        CREATE Benutzerinfo IF NOT EXISTS( 
        bid Integer NOT NULL,
        benutzer TEXT CONSTRAINT, 
        passwort TEXT ,
        Primary Key(bid)) ;
        */
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS BenutzerInfo (\n"
                + "	bid integer PRIMARY KEY,\n"
                + "	benutzer text NOT NULL,\n"
                + "	passwort text NOT NULL,\n"
                + "	capacity real\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewTable();
        kreierebenutzerinfo();
    }
}