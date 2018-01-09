/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqiel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author koeppen
 */
public class Sqiel {

    SqielDatenbankzugriff sqDB;
    Random zufall;

    public void setup() {
        zufall = new Random();
        //erzeuge den Datenbankzugriff
        sqDB = new SqielDatenbankzugriff("theRealDB");
        //lege Tabelle BenutzerInfo an
        sqDB.erzeugeBenutzerInfo();
        //fuege alle Benutzer ein
        sqDB.fuegeBenutzerEin("koeppen", "59747f5564a5470b3a191f67ca469301");
        sqDB.fuegeBenutzerEin("louie", "b6ea4320f72d92669405dda0d07eefdd");
        sqDB.fuegeBenutzerEin("JoP", "ee7b630995e7a36b6420696989441e2d");
        sqDB.fuegeBenutzerEin("Lion", "60d28e7d879c0dc48b9a593468cf11e5");
        sqDB.fuegeBenutzerEin("Leon", "e48b7bf1a447021da85214b43f51fd4e");
        sqDB.fuegeBenutzerEin("Timon", "a13fed3ea354889ea7675a4b84365497");
        sqDB.fuegeBenutzerEin("Hannah", "cf82a9577f8e6fa17ce3ccf4daaf94e9");
        //lege MinMaxInfo-Tabelle an
        sqDB.erzeugeMinMaxInfo();
        //lege Tabelle TippInfo an
        sqDB.erzeugeTippInfo();
        //lege Tabelle RundenInfo an
        sqDB.erzeugeRundenInfo();
        //gib bestehende Werte im Log aus
    }

    public int neuesSpielAnlegen(int min, int max) {
        //lösche Inhalte der Tabelle TippInfo
        sqDB.loescheTabellenInhalte("tippInfo");
        //lösche Inhalte der Tabelle RundenInfo
        sqDB.loescheTabellenInhalte("rundenInfo");
        //lösche Inhalte der Tabelle MinMaxInfo
        sqDB.loescheTabellenInhalte("minMaxInfo");

        //ergänze Eintrag in der Tabelle MinMaxInfo: min max
        //speichere mID dieses Eintrags!
        //erzeuge Computertipp zwischen min und max
        //ergänze Eintrag in der Tabelle RundenInfo: mID von eben Computertipp
        //Rundennummer zurückgeben
        int rundennummer;
        rundennummer = neueRundeAnlegen(min,max);
        return rundennummer;
    }

    public int neueRundeAnlegen(int min, int max) {
        //ergänze Eintrag in der Tabelle MinMaxInfo: min max
        sqDB.fuegeMinMaxEin(min, max);
        System.out.println("MinMax eingefügt.");
        //speichere mID dieses Eintrags!
        ResultSet rs = sqDB.fuehreSelectAus("SELECT mid FROM MinMaxInfo WHERE min=" + min + " AND max=" + max + ";");
        System.out.println("Select ausgeführt. Ergebnis: "+rs);
        int rundennummer = -1;
        if (rs != null) {
            try {
                //bei mehreren möglichen EInträgen ist nur der erste wichtig, die anderen sind diesem in min und max gleich...
                int mid = rs.getInt("mid");
                System.out.println("Mid gefunden: "+mid);
                //erzeuge Computertipp zwischen min und max
                int ctipp = Math.min(max, min) + zufall.nextInt(Math.abs(max - min));
                System.out.println("ComputerTipp erzeugt: "+ctipp );
                //ergänze Eintrag in der Tabelle RundenInfo: mID von eben Computertipp
                sqDB.fuegeRundenInfoEin(mid, ctipp);
                System.out.println("Rundeninfo eingefügt.");
                rs = sqDB.fuehreSelectAus("SELECT rn FROM RundenInfo WHERE m_id=" + mid + " AND ctipp=" + ctipp + ";");
                System.out.println("Select ausgeführt. Ergebnis: "+rs);
                rundennummer = rs.getInt("rn");
                System.out.println("rn gefunden: "+rundennummer);

            } catch (SQLException ex) {
                Logger.getLogger(Sqiel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rundennummer;

    }

    public void auswertenButton() {
        //alle Punktestände einer Person werden addiert und das für alle Personen
        // die einzelnen Punktestände werden in TAAnzeige angezeigt
        // danach kann ein neues Spiel gestartet werden

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
