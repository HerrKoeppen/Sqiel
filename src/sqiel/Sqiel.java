/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqiel;

/**
 *
 * @author koeppen
 */
public class Sqiel {

    SqielDatenbankzugriff sqDB;

    public void setup() {
        //erzeuge den Datenbankzugriff
        sqDB = new SqielDatenbankzugriff("theDB");
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
