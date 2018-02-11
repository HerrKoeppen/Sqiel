/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqiel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
        /*
        sqDB.fuegeBenutzerEin("koeppen", "59747f5564a5470b3a191f67ca469301");
        //sqDB.fuegeBenutzerEin("koeppen2", "59747f5564a5470b3a191f67ca469301");
        
        sqDB.fuegeBenutzerEin("louie", "b6ea4320f72d92669405dda0d07eefdd");
        sqDB.fuegeBenutzerEin("JoP", "ee7b630995e7a36b6420696989441e2d");
        sqDB.fuegeBenutzerEin("Lion", "60d28e7d879c0dc48b9a593468cf11e5");
        sqDB.fuegeBenutzerEin("Leon", "e48b7bf1a447021da85214b43f51fd4e");
        sqDB.fuegeBenutzerEin("Timon", "d41d8cd98f00b204e9800998ecf8427e");
        sqDB.fuegeBenutzerEin("Hannah", "cf82a9577f8e6fa17ce3ccf4daaf94e9");*/
        
        sqDB.fuegeBenutzerEin("test", "d41d8cd98f00b204e9800998ecf8427e");
        sqDB.fuegeBenutzerEin("test2", "d41d8cd98f00b204e9800998ecf8427e");
        sqDB.fuegeBenutzerEin("test3", "d41d8cd98f00b204e9800998ecf8427e");
  

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
        rundennummer = neueRundeAnlegen(min, max);
        return rundennummer;
    }

    public int neueRundeAnlegen(int min, int max) {
        //ergänze Eintrag in der Tabelle MinMaxInfo: min max
        sqDB.fuegeMinMaxEin(min, max);
        System.out.println("MinMax eingefügt.");
        //speichere mID dieses Eintrags!
        ResultSet rs = sqDB.fuehreSelectAus("SELECT mid FROM MinMaxInfo WHERE min=" + min + " AND max=" + max + ";");
        System.out.println("Select ausgeführt. Ergebnis: " + rs);
        int rundennummer = -1;
        if (rs != null) {
            try {
                //bei mehreren möglichen EInträgen ist nur der erste wichtig, die anderen sind diesem in min und max gleich...
                int mid = rs.getInt("mid");
                System.out.println("Mid gefunden: " + mid);
                //erzeuge Computertipp zwischen min und max
                int ctipp = Math.min(max, min) + zufall.nextInt(Math.abs(max - min));
                System.out.println("ComputerTipp erzeugt: " + ctipp);
                //ergänze Eintrag in der Tabelle RundenInfo: mID von eben Computertipp
                sqDB.fuegeRundenInfoEin(mid, ctipp);
                System.out.println("Rundeninfo eingefügt.");
                rs = sqDB.fuehreSelectAus("SELECT rn FROM RundenInfo WHERE m_id=" + mid + " AND ctipp=" + ctipp + ";");
                System.out.println("Select ausgeführt. Ergebnis: " + rs);
                rundennummer = rs.getInt("rn");
                System.out.println("rn gefunden: " + rundennummer);
                int anzahlBenutzer = 0;
                rs = sqDB.fuehreSelectAus("SELECT COUNT(*) FROM BenutzerInfo");
                if (rs.next()) {
                    anzahlBenutzer = rs.getInt(1);
                }
                System.out.println("Es gibt " + anzahlBenutzer + " Benutzer.");
                rs = sqDB.fuehreSelectAus("SELECT bid FROM BenutzerInfo");
                System.out.println("Select durchgeführt. Ergebnis: " + rs);
                while (rs.next()) {
                    sqDB.fuegeTippEin(rs.getInt("bid"), rundennummer, -1, false, -1);
                    System.out.println("Tipp eingefügt mit diesen Werten: btipp=-1, hat_getippt=false, pkstd=-1, rn=" + rundennummer + " ,bid=" + rs.getInt("bid"));
                }
                gibAlleTabelleAus();

            } catch (SQLException ex) {
                Logger.getLogger(Sqiel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rundennummer;

    }

    public void gibAlleTabelleAus() {
        System.out.println(sqDB.gibAlleTabelleninhalteAus("BenutzerInfo"));
        System.out.println(sqDB.gibAlleTabelleninhalteAus("RundenInfo"));
        System.out.println(sqDB.gibAlleTabelleninhalteAus("MinMaxInfo"));
        System.out.println(sqDB.gibAlleTabelleninhalteAus("TippInfo"));

    }

    public boolean kontrolliereAnmeldeinfo(String benutzerName, String passwort) {
        gibAlleTabelleAus();
        boolean anmeldungKorrekt = false;
        ResultSet rs = sqDB.fuehreSelectAus("SELECT * FROM BenutzerInfo");
        String hashtext = "";
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(passwort.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            while (rs.next()) {
                if (rs.getString("benutzer").equals(benutzerName)) {
                    String tabellenPasswort = rs.getString("passwort");
                    if (tabellenPasswort.equals(hashtext)) {
                        anmeldungKorrekt = true;

                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Sqiel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return anmeldungKorrekt;
    }

    public boolean kontrolliereRundeVollstaendigGetippt(int rundennummer) {
        boolean retVal = false;
        boolean kontrolle = true;
        ResultSet rs = sqDB.fuehreSelectAus("SELECT * FROM TippInfo WHERE rn = " + rundennummer);
        try {
            while (rs.next()) {
                if (rs.getBoolean("hat_getippt") == false) {
                    kontrolle = false;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Sqiel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (kontrolle == true) {
            retVal = true;
        }
        return retVal;
    }

    public void auswertenButton(int aktuelleRunde) {
        //haben schon alle getippt?
        if (!kontrolliereRundeVollstaendigGetippt(aktuelleRunde)) {
            System.out.println("ACHTUNG: Aktuelle Runde " + aktuelleRunde + " noch nicht vollständig getippt. Zuerst zuende tippen!");
        } else {
            //anzahl Benutzer finden
            int anzahlBenutzer = 0;
            ResultSet rs = sqDB.fuehreSelectAus("SELECT COUNT(*) FROM BenutzerInfo");
            System.out.println("Abfrage durchgeführt. Ergebnis: " + rs);

            try {
                if (rs.next()) {
                    anzahlBenutzer = rs.getInt(1);
                    System.out.println("Gefundene Anzahl Benutzer: " + anzahlBenutzer);
                }
                //sammle alle Tipps für diese Runde
                ArrayList<BidTippSammelobjekt> al = new ArrayList();
                rs = sqDB.fuehreSelectAus("SELECT * FROM TippInfo WHERE rn=" + aktuelleRunde);
                System.out.println("Abfrage durchgeführt. Ergebnis: " + rs);
                while (rs.next()) {
                    al.add(new BidTippSammelobjekt(rs.getInt("bid"), rs.getInt("btipp")));
                    System.out.println("Objekt mit diesen Werten erzeugt: bid=" + rs.getInt("bid") + " btipp= " + rs.getInt("btipp"));
                }
                rs = sqDB.fuehreSelectAus("SELECT * FROM RundenInfo WHERE rn=" + aktuelleRunde);
                System.out.println("Abfrage durchgeführt. Ergebnis: " + rs);
                int ctipp = rs.getInt("ctipp");
                System.out.println("Computertipp ausgelesen" + ctipp);
                al = ergaenzePunkteZuAl(al, ctipp);
                int pkstdAlt = 0;
                for (BidTippSammelobjekt obj : al) {
                    //alten Punktestand sammeln
                    rs = sqDB.fuehreSelectAus("SELECT * FROM TippInfo WHERE bid=" + obj.bid);
                    System.out.println("Abfrage durchgeführt. Ergebnis: " + rs);
                    pkstdAlt = rs.getInt("pkstd");
                    if (pkstdAlt == -1) {
                        pkstdAlt = 0;
                    }
                    System.out.println("Alten Punktestand ausgelesen:" + pkstdAlt);
                    sqDB.fuehreSelectAus("UPDATE TippInfo SET pkstd=" + (pkstdAlt + obj.punkte) + " WHERE bid=" + obj.bid);
                    System.out.println("Update durchgeführt.");
                }
                //Tipptabelle sortiert nach Punktestand auslesen
                //Benutzernamen zu bid finden
                gibAlleTabelleAus();
                //Tabelle benutzer | punktestand in TAAnzeige ausgeben
            } catch (SQLException ex) {
                Logger.getLogger(Sqiel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<BidTippSammelobjekt> ergaenzePunkteZuAl(ArrayList<BidTippSammelobjekt> al, int ctipp) {
        ArrayList<BidTippSammelobjekt> retVal = (ArrayList<BidTippSammelobjekt>) al.clone();
        int min = -1;
        int abs = -1;
        System.out.println(Arrays.toString(retVal.toArray()));
        System.out.println("Größe von retVal: " + retVal.size());
        int verbleibendeSpieler = 0;
        //boolean spielerUebrig = true;
        for (int i = 0; i <= retVal.size(); i = i + 1) {

            for (BidTippSammelobjekt obj : retVal) {
                if (obj.punkte == -1) {
                    verbleibendeSpieler += 1;
                    abs = Math.abs(obj.Tipp - ctipp);
                    System.out.println("Neuer abs-Wert: " + abs);
                    if (min == -1 || abs < min) {
                        System.out.println("min-Wert: " + min);
                        min = abs;
                        System.out.println("geänderter min-Wert: " + min);

                    }
                } else {
                    System.out.println("Objekt zum Zählen ignoriert!");
                }
                System.out.println("Ermittelte verbleibende Spieler: " + verbleibendeSpieler);

            }
            /*
            if (verbleibendeSpieler == 0) {
                spielerUebrig = false;
            }
             */
            for (BidTippSammelobjekt obj : retVal) {
                if (obj.punkte == -1) {
                    System.out.println("Unbepunktetes Objekt gefunden: " + obj.bid);
                    if (Math.abs(obj.Tipp - ctipp) == min) {
                        if (verbleibendeSpieler == 0) {
                            obj.punkte = 0;
                            System.out.println("Objektbepunktung: 0");
                        } else {
                            obj.punkte = verbleibendeSpieler - 1;
                            System.out.println("Objektbepunktung: " + obj.punkte);
                        }
                    }
                }
            }
            verbleibendeSpieler = 0;
            min = -1;
            abs = -1;
        }
        //Ausgabe zu Kontrollzwecken
        for (BidTippSammelobjekt obj : retVal) {
            System.out.println(obj.bid + " " + obj.Tipp + " " + obj.punkte);
        }
        return retVal;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
