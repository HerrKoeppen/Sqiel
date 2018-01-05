package sqiel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private String url;
    private String dbUrl;
    Connection conn;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;

    public SqielDatenbankzugriff(String dieURL) {
        url = dieURL;
        dbUrl = "jdbc:sqlite:" + url;
        try {
            conn = DriverManager.getConnection(dbUrl);
            stmt = conn.createStatement();
            pstmt = null;
            rs = null;

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";

        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }

    public void kreierebenutzerinfo() {
        String sql = "CREATE TABLE IF NOT EXISTS BenutzerInfo (\n"
                + "	bid integer PRIMARY KEY,\n"
                + "	benutzer text NOT NULL,\n"
                + "	passwort text NOT NULL,\n"
                + "	capacity real\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(dbUrl);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void erzeugeBenutzerInfo() {
        String sql = "CREATE TABLE IF NOT EXISTS BenutzerInfo (\n"
                + "	bid integer PRIMARY KEY,\n"
                + "	benutzer text NOT NULL UNIQUE,\n"
                + "	passwort text NOT NULL,\n"
                + "	capacity real\n"
                + ");";

        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fuegeBenutzerEin(String benutzerName, String benutzerPasswort) {
        String sql = "INSERT INTO BenutzerInfo(bid,benutzer,passwort) VALUES(?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(2, benutzerName);
            pstmt.setString(3, benutzerPasswort);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void liesAusBenutzerInfo() {
        String sql = "SELECT bid, benutzer, passwort FROM BenutzerInfo";

        try {

            rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("bid") + "\t"
                        + rs.getString("benutzer") + "\t"
                        + rs.getString("passwort"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void aendereBenutzer(String benutzerName, String neuerName, String neuesPasswort) {
        String sql = "UPDATE BenutzerInfo SET benutzer = ? , "
                + "passwort = ? "
                + "WHERE benutzer = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, neuerName);
            pstmt.setString(2, neuesPasswort);
            pstmt.setString(3, benutzerName);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loescheBenutzer(String benutzerName) {
        String sql = "DELETE FROM BenutzerInfo WHERE benutzer = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, benutzerName);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SqielDatenbankzugriff sq = new SqielDatenbankzugriff("theDB");
        //sq.createNewTable();
        //sq.kreierebenutzerinfo();
        sq.erzeugeBenutzerInfo();
        sq.fuegeBenutzerEin("koeppen", "900150983cd24fb0d6963f7d28e17f72");
        sq.liesAusBenutzerInfo();
    }
}
