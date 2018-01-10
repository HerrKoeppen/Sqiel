package sqiel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * Alt! Nicht benutzen!
     *
     * @param dieURL der Datenbankname
     */
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

    /**
     * Alt! Nicht benutzen!
     */
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

    /**
     * Testmethode! Nicht benutzen!
     */
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
////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Erzeugt die Tabelle BenutzerInfo auf einem bestehenden
     * SqielDatenbankzugriff-Objekt mit Anbindung an eine bestimmte Datenbank.
     * Die Tablle wird nur erzeugt, sofern diese nicht existiert. Die Tabelle
     * enthält die Felder bid (Primärschlüssel), benutzer (UNIQUE) und passwort
     */
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

    /**
     * Führt ein INSERT auf einem besteheneden Datenbankobjekt in der Tabelle
     * BenutzerInfo aus. Sofern der Nutzer noch nicht existiert (benutzer ist
     * UNIQUE!) wird ein neuer eintrag angelegt.
     *
     * @param benutzerName der Benutzername
     * @param benutzerPasswort das Benutzerpasswort (MD5-Hashwert)
     */
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

    /**
     * Liest sämtliche Einträge der Tabelle BenutzerInfo eines bestehenden
     * Datenbankobjekts aus. Die Ergebnisse werden Reihenweise durchgegangen und
     * mit den Werten bid, benutzer und passwort ausgegeben.
     */
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

    /**
     * Führt ein UPDATE auf der Tabelle benutzerInfo aus. Ein bestehender
     * Benutzer wird durch einen neuen Nutzernamen und ein neues Passwort
     * ersetzt.
     *
     * @param benutzerName nach diesem Nutzernamen wird gesucht
     * @param neuerName der gefundene Benutzer erhält diesen neuen Benutzernamen
     * @param neuesPasswort und dieses Passwort
     */
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

    /**
     * sendet ein DELETE an die BenutzerInfo-Tabelle. Löscht einen bestimten
     * Benutzer.
     *
     * @param benutzerName der zu löschende Benutzer
     */
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
////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * erzeugt die Tabelle RundenInfo auf einem bestehenden Datenbankobjekt.
     * RundenInfo hat diese Felder: rn, m_id, ctipp
     */
    public void erzeugeRundenInfo() {
        String sql = "CREATE TABLE IF NOT EXISTS RundenInfo( \n"
                + "rn Integer,\n"
                + "m_id Integer,\n"
                + "ctipp Integer,\n"
                + "CONSTRAINT rn,\n"
                + "Primary Key(rn));";

        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     *
     * Fügt einen neuen Eintrag in die RundenInfoTabelle ein. Strutkur der
     * Tabelle: RundenInfo(rn,m_id,ctipp)
     *
     * @param mid Die MinMaxID auf die sich die RUnde bezieht. Dort enthalten
     * sind Minimum und Maximum der Runde
     * @param ctipp Der Computertipp für diese Runde
     */
    public void fuegeRundenInfoEin(int mid, int ctipp) {

        String sql = "INSERT INTO RundenInfo(rn,m_id,ctipp) VALUES(?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(2, mid);
            pstmt.setInt(3, ctipp);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * erzeugt die Tabelle MinMaxInfo auf einem bestehenden Datenbankobjekt.
     * MinMaxInfo hat diese Felder: mid, max, min
     */
    public void erzeugeMinMaxInfo() {
        String sql = "CREATE TABLE IF NOT EXISTS MinMaxInfo(\n"
                + "mid INTEGER NOT NULL,\n"
                + "max INTEGER,\n"
                + "min INTEGER,\n"
                + "Primary Key (mid)\n"
                + ");";

        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Fügt einen neuen Eintrag in die MinMaxTabelle ein. Strutkur der Tabelle:
     * MinMaxInfo(mid,max,min)
     *
     * @param min Der eingefügte Wert für das Minimum
     * @param max Der eingefügte Wert für das Maximum
     */
    public void fuegeMinMaxEin(int min, int max) {

        String sql = "INSERT INTO MinMaxInfo(mid,max,min) VALUES(?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(2, max);
            pstmt.setInt(3, min);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void liesAusMinMaxInfo() {

        String sql = "SELECT mid , min, max FROM MinMaxInfo";

        try {

            rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("mid") + "\t"
                        + rs.getInt("min") + "\t"
                        + rs.getInt("max") + "\t"
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loescheMinMaxInfo(int mid) {
        String sql = "DELETE mid FROM MinMaxInfo  = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, mid);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * erzeugt die Tabelle TippInfo auf einem bestehenden Datenbankobjekt.
     * TippInfo hat diese Felder: tid, btipp, hat_getippt, pkstd, rn, bid
     */
    public void erzeugeTippInfo() {
        String sql = "CREATE TABLE IF NOT EXISTS TippInfo (\n"
                + "tid INTEGER NOT NULL,\n"
                + " btipp INTEGER,\n"
                + " hat_getippt BOOLEAN,\n"
                + " pkstd INTEGER,\n"
                + " rn INTEGER,\n"
                + " bid INTEGER,\n"
                + " CONSTRAINT tid,\n"
                + " Primary Key(tid),\n"
                + " FOREIGN KEY(rn) REFERENCES RundenInfo(rn), \n"
                + " FOREIGN KEY(bid) REFERENCES BenutzerInfo(bid));";

        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Erzeugt die TippInfo-Tabelle mit den Attributen
     *
     */
    public void tippAEndern(int bid, int rn, int neuerTip, boolean hat_getippt, int pkstd) {
        String sql = "UPDATE TippInfo SET btipp = ? , "
                + "hat_getippt = ? , "
                + "pkstd = ?"
                + "WHERE bid = ?"
                + "AND rn = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, neuerTip);
            pstmt.setBoolean(2, hat_getippt);
            pstmt.setInt(3, pkstd);
            pstmt.setInt(4, bid);
            pstmt.setInt(5, rn);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * UPDATE: Ändert die Attributwerte btip, hat_getippt und pkstd eines
     * Eintrages mit entsprechenen rn und bid aus der TippInfo Tabelle Input :
     * (int bid, int rn,int neuerTip, boolean hat_getippt, int pkstd)
     */
    public void loescheTipp(int bid, int rn) {
        String sql = "DELETE FROM TippInfo WHERE bid = ?"
                + "AND rn = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            //stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            //stmt.close();
            // set the corresponding param
            pstmt.setInt(1, bid);
            // execute the delete statement
            pstmt.setInt(2, rn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * DELETE: löscht einen Eintrag in der Tippinfo-tabelle nach der
     * Rundennummer und BenutzerID
     */
    public void liesAusTippInfo() {

        String sql = "SELECT tid , btipp, hat_getippt, pkstd, bid,rn FROM TippInfo";

        try {

            rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("tid") + "\t"
                        + rs.getString("btipp") + "\t"
                        + rs.getString("pkstd") + "\t"
                        + rs.getString("hat_getippt") + "\t"
                        + rs.getString("bid") + "\t"
                        + rs.getString("rn"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fuegeTippEin(int bid, int rn, int btipp, boolean hat_getippt, int pkstd) {

        String sql = "INSERT INTO TippInfo(tid,btipp,hat_getippt,pkstd,rn,bid) VALUES(?,?,?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(2, btipp);
            pstmt.setBoolean(3, hat_getippt);
            pstmt.setInt(4, pkstd);
            pstmt.setInt(5, rn);
            pstmt.setInt(6, bid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void aendereTipp(String benutzer, int btipp, int rn) {
        String sql = "SELECT * FROM BenutzerInfo";
        try {
            rs = stmt.executeQuery(sql);
            int bid = -1;
            while (rs.next()) {
                if (rs.getString("benutzer").equals(benutzer)) {
                    bid = rs.getInt("bid");
                }
            }
            if (bid != -1) {
                sql = "UPDATE TippInfo SET btipp=?,hat_getippt=?,rn=? WHERE bid=" + bid;
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, btipp);
                pstmt.setBoolean(2, true);
                pstmt.setInt(3, rn);
                pstmt.executeUpdate();

            }
        } catch (SQLException ex) {
            Logger.getLogger(SqielDatenbankzugriff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int benutzerpkstd(int rn, int bid) throws SQLException {
        int bpkst = 0;
        String sql = "SELECT pkstd FROM TippInfo WHERE rn  = ? AND bid = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(2, bid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        ResultSet k = null;
        for (int i = 1; i <= rn; i++) {
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, i);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            k = fuehreSelectAus(sql);
            bpkst = k.getInt("pkstd") + bpkst;
        }

        return rn;
    }
////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////

    public String gibAlleTabelleninhalteAus(String tabellenName) {
        try {
            String result = "Tabelle: " + tabellenName + "\n";

            rs = stmt.executeQuery("SELECT * FROM " + tabellenName);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                result += rsmd.getColumnLabel(i) + "\t";
            }

            result += "\n";
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
                        result += rs.getInt(rsmd.getColumnLabel(i)) + "\t";
                    } else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
                        result += rs.getString(rsmd.getColumnLabel(i)) + "\t";
                    } else if (rsmd.getColumnType(i) == java.sql.Types.DOUBLE) {
                        result += rs.getDouble(rsmd.getColumnLabel(i)) + "\t";
                    } else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
                        result += rs.getBoolean(rsmd.getColumnLabel(i)) + "\t";
                    }

                }
                result += "\n";
            }
            return result;
            // Do stuff with name

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public void loescheTabellenInhalte(String tabellenName) {
        String sql = "DELETE FROM " + tabellenName;
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Führt eine beliebige SQL-Select Abfrage an die Tabellen durch. Liefert
     * das Ergebnis in Form eines ResultSet zurück. Ein ResultSet rs (das hier
     * zurückgegeben wird) kann man so auf bestimmte Inhalte prüfen: while
     * (rs.next()){ rs.getInt("tid"); (Das würde dann das Tabellenfeld "tid" der
     * Tabelle zeilenweise auslesen) }
     *
     * @param sqlSelectAbfrage Die gewünschte SQL-Select-Abfrage
     * @return das ResultSet als Abfrage-Ergebnis. Bedienung siehe oben oder in
     * der java-API
     */
    public ResultSet fuehreSelectAus(String sqlSelectAbfrage) {
        ResultSet retRS = null;
        try {

            retRS = stmt.executeQuery(sqlSelectAbfrage);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retRS;
    }

    /**
     * Gibt alle Inhalte einer beliebigen Tabelle als String zurück.
     *
     * @param tabellenName Der Name der Tabelle, die ausgegeben wird
     * @return String, der die textuelle Darstellung der Tabelle mit Inhalten
     * ist.
     */
    /**
     * @param args the command line arguments
     */
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        SqielDatenbankzugriff sq = new SqielDatenbankzugriff("theDB");
        sq.createNewTable();
        sq.kreierebenutzerinfo();
        sq.erzeugeBenutzerInfo();
        sq.fuegeBenutzerEin("koeppen", "900150983cd24fb0d6963f7d28e17f72");
        sq.liesAusBenutzerInfo();
        sq.erzeugeRundenInfo();
        sq.erzeugeMinMaxInfo();
        sq.erzeugeTippInfo();
        sq.fuegeTippEin(0, 0, 0, false, 0); //Test- Default
    }
}
