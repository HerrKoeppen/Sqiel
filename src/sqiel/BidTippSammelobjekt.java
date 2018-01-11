/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqiel;

/**
 *
 * @author K
 */
public class BidTippSammelobjekt {
    int bid;
    int Tipp;
    int punkte;
    public BidTippSammelobjekt(int benutzerID, int benutzerTipp){
        bid = benutzerID;
        Tipp = benutzerTipp;
        punkte=-1;
    }
    
}
