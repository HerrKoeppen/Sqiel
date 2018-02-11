/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqiel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author koeppen
 */
public class SqielOberflaeche extends javax.swing.JFrame {

    Sqiel sq;

    /**
     * Creates new form SqielOberflaeche
     */
    public SqielOberflaeche() {
        sq = new Sqiel();
        sq.setup();
        initComponents();
        TAAnzeige.append("Oberfläche geladen.\n");
        TAAnzeige.append("Sqiel sq erzeugt.\n");
        TAAnzeige.append("sq-Setup.Methode ausgeführt. Datenbankanbindung ist da.\n");
        TAAnzeige.append("Administratormodus ist deaktiviert.\n");
        int rundennummer = -1;
        int mid = -1;
        ResultSet rs = sq.sqDB.fuehreSelectAus("SELECT * FROM RundenInfo");
        try {
            while (rs.next()) {
                if (rs.getInt("rn") > rundennummer) {
                    rundennummer = rs.getInt("rn");
                    mid = rs.getInt("m_id");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqielOberflaeche.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rundennummer > -1) {
            rs = sq.sqDB.fuehreSelectAus("SELECT * FROM MinMaxInfo");
            try {
                while (rs.next()) {
                    if (rs.getInt("mid") == mid) {
                        TFMin.setText(Integer.toString(rs.getInt("min")));
                        TFMax.setText(Integer.toString(rs.getInt("max")));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(SqielOberflaeche.class.getName()).log(Level.SEVERE, null, ex);
            }
            TARundennummer.setText((new Integer(rundennummer)).toString());
            if (sq.kontrolliereRundeVollstaendigGetippt(rundennummer)) {
                TAAnzeige.append("Die Runde wurde von allen Mitspielern betippt.\n");
            } else {
                TAAnzeige.append("ACHTUNG: Die Runde ist noch nicht vollständig betippt.\n");
            }
        } else {
            TAAnzeige.append("Es wurde noch keine Runde erzeugt.\n");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TFPasswort = new javax.swing.JPasswordField();
        TFBenutzername = new javax.swing.JTextField();
        TFDeinTipp = new javax.swing.JTextField();
        BGO = new javax.swing.JButton();
        TFMax = new javax.swing.JTextField();
        TFMin = new javax.swing.JTextField();
        LMax = new javax.swing.JLabel();
        LMin = new javax.swing.JLabel();
        LBenutzername = new javax.swing.JLabel();
        LPasswort = new javax.swing.JLabel();
        LDeinTipp = new javax.swing.JLabel();
        RBAdministrator = new javax.swing.JRadioButton();
        TFAMax = new javax.swing.JTextField();
        TFAMin = new javax.swing.JTextField();
        LAMax = new javax.swing.JLabel();
        LAMin = new javax.swing.JLabel();
        BNeuesSpiel = new javax.swing.JButton();
        BNeueRunde = new javax.swing.JButton();
        STrennungswand = new javax.swing.JSeparator();
        SPAnzeige = new javax.swing.JScrollPane();
        TAAnzeige = new javax.swing.JTextArea();
        PBLadebalken = new javax.swing.JProgressBar();
        RBPunktestand = new javax.swing.JRadioButton();
        BAuswerten = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        TARundennummer = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        TFPasswort.setColumns(20);
        TFPasswort.setToolTipText("");

        TFBenutzername.setColumns(20);
        TFBenutzername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFBenutzernameActionPerformed(evt);
            }
        });

        TFDeinTipp.setColumns(20);
        TFDeinTipp.setToolTipText("");
        TFDeinTipp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFDeinTippActionPerformed(evt);
            }
        });

        BGO.setFont(new java.awt.Font("Source Sans Pro", 1, 18)); // NOI18N
        BGO.setText("Go!");
        BGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGOActionPerformed(evt);
            }
        });

        TFMax.setEditable(false);
        TFMax.setColumns(5);
        TFMax.setEnabled(false);
        TFMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFMaxActionPerformed(evt);
            }
        });

        TFMin.setEditable(false);
        TFMin.setColumns(5);
        TFMin.setEnabled(false);

        LMax.setText("Max");

        LMin.setText("Min");

        LBenutzername.setFont(new java.awt.Font("Source Sans Pro", 1, 14)); // NOI18N
        LBenutzername.setText("Benutzername");

        LPasswort.setFont(new java.awt.Font("Source Sans Pro", 1, 14)); // NOI18N
        LPasswort.setText("Passwort");

        LDeinTipp.setFont(new java.awt.Font("Source Sans Pro", 1, 14)); // NOI18N
        LDeinTipp.setText("Dein Tipp");

        RBAdministrator.setFont(new java.awt.Font("Source Sans Pro", 1, 18)); // NOI18N
        RBAdministrator.setText("Administrator");
        RBAdministrator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBAdministratorActionPerformed(evt);
            }
        });

        TFAMax.setColumns(3);
        TFAMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFAMaxActionPerformed(evt);
            }
        });

        TFAMin.setColumns(3);
        TFAMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFAMinActionPerformed(evt);
            }
        });

        LAMax.setText("AMax");

        LAMin.setText("AMin");

        BNeuesSpiel.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        BNeuesSpiel.setText("Neues Sqiel");
        BNeuesSpiel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNeuesSpielActionPerformed(evt);
            }
        });

        BNeueRunde.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        BNeueRunde.setText("Neue Runde");
        BNeueRunde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNeueRundeActionPerformed(evt);
            }
        });

        TAAnzeige.setEditable(false);
        TAAnzeige.setColumns(20);
        TAAnzeige.setRows(5);
        SPAnzeige.setViewportView(TAAnzeige);

        RBPunktestand.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        RBPunktestand.setText("Aktuellen Punktestand anzeigen");
        RBPunktestand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBPunktestandActionPerformed(evt);
            }
        });

        BAuswerten.setFont(new java.awt.Font("Source Sans Pro", 1, 14)); // NOI18N
        BAuswerten.setText("Auswerten");
        BAuswerten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAuswertenActionPerformed(evt);
            }
        });

        jLabel2.setText("Rundennummer:");

        TARundennummer.setEditable(false);
        TARundennummer.setColumns(20);
        TARundennummer.setRows(5);
        TARundennummer.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(STrennungswand)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LPasswort)
                                    .addComponent(LBenutzername)
                                    .addComponent(LDeinTipp))
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TFMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(LMin)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(TFMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(LMax))))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(TFPasswort, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TFBenutzername, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TFDeinTipp)))
                                .addGap(165, 165, 165))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(RBPunktestand)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BGO, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PBLadebalken, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BAuswerten)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TARundennummer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(17, 17, 17)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(SPAnzeige, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(BNeuesSpiel)
                        .addGap(18, 18, 18)
                        .addComponent(BNeueRunde)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(RBAdministrator)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(TFAMin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LAMin)
                        .addGap(48, 48, 48)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TFAMax, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(LAMax)))
                .addGap(213, 213, 213))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TARundennummer, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(RBAdministrator, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LAMin)
                    .addComponent(LAMax, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFAMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFAMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BNeuesSpiel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BNeueRunde, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(BAuswerten, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(STrennungswand, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFBenutzername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBenutzername))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFPasswort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LPasswort))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LMin)
                    .addComponent(LMax))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFDeinTipp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LDeinTipp))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BGO, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RBPunktestand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SPAnzeige, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(PBLadebalken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TFMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFMaxActionPerformed

    private void TFAMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFAMaxActionPerformed
        // TODO add your handling code here:
        String tmax;
        tmax = TFAMax.getText();

        if (tmax.isEmpty() == true) {
            TAAnzeige.append("Es fehlt ein Maximum");

        }

    }//GEN-LAST:event_TFAMaxActionPerformed

    private void TFAMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFAMinActionPerformed
        // TODO add your handling code here:
        String tmin;
        tmin = TFAMin.getText();

        if (tmin.isEmpty() == true) {
            TAAnzeige.append("Es fehlt ein Minimum");

        }
    }//GEN-LAST:event_TFAMinActionPerformed

    private void TFBenutzernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFBenutzernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFBenutzernameActionPerformed

    private void BGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGOActionPerformed
        TAAnzeige.append("Go-Button gedrückt.\n");
        String benutzer = TFBenutzername.getText();
        String passwort = TFPasswort.getText();
        //TAAnzeige.append("Benutzer und Passwort ausgelesen: " + benutzer + " " + passwort + "\n");
        if (sq.kontrolliereAnmeldeinfo(benutzer, passwort)) {
            TAAnzeige.append("Anmeldeinfo geprüft und korrekt.\n");
            String benutzerTipp = TFDeinTipp.getText();
            try {
                int btipp = Integer.parseInt(benutzerTipp);
                TAAnzeige.append("Benutzertipp ausgelesen: " + btipp + "\n");
                //sq.sqDB.fuehreSelectAus("UPDATE TippInfo SET ")
                sq.sqDB.aendereTipp(benutzer, btipp, Integer.parseInt(TARundennummer.getText()));
                TAAnzeige.append("Tipp verändert.\n");
                TFBenutzername.setText("");
                TFPasswort.setText("");
                TFDeinTipp.setText("");
                sq.gibAlleTabelleAus();
                //Ladebalken-Fortschritt
                int anzahlBenutzer;
                ResultSet rs = sq.sqDB.fuehreSelectAus("SELECT COUNT(*) FROM Benutzerinfo");
                anzahlBenutzer =  ((Number) rs.getObject(1)).intValue();
                int Prozentsatz = 100/anzahlBenutzer;
                PBLadebalken.setValue(PBLadebalken.getValue()+ Prozentsatz);      
                



                
            } catch (NumberFormatException nfe) {
                TAAnzeige.append("FEHLER: Benutzertipp ist keine Zahl.");
            } catch (SQLException ex) {
                Logger.getLogger(SqielOberflaeche.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            TAAnzeige.append("FEHLER: Anmeldeinfo falsch.\n");
        }    }//GEN-LAST:event_BGOActionPerformed

    private void TFDeinTippActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFDeinTippActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFDeinTippActionPerformed

    private void RBAdministratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBAdministratorActionPerformed
        // TODO add your handling code here:

        if (RBAdministrator.isSelected()) {
            TAAnzeige.append("Administratormodus ist aktiviert.\n");
        } else {
            TAAnzeige.append("Administratormodus ist deaktiviert.\n");
        }
    }//GEN-LAST:event_RBAdministratorActionPerformed

    private void BNeuesSpielActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNeuesSpielActionPerformed
        // TODO add your handling code here:
        RBAdministrator.setSelected(false); //shall not work!!
        if (RBAdministrator.isSelected()) {
            RBAdministrator.setSelected(false);
            TAAnzeige.append("Ein neues Sqiel wird angelegt.\n");
            String t1, t2;
            int min = -1;
            int max = -1;
            t1 = TFAMin.getText();
            t2 = TFAMax.getText();
            try {
                min = Integer.parseInt(t1);
                max = Integer.parseInt(t2);
            } catch (NumberFormatException nfe) {
                TAAnzeige.append("FEHLER: Problem mit der Zahlenerkennung in Feld Min oder Max!\n");
            }
            if (max <= min) {
                TAAnzeige.append("FEHLER:max ist kleiner als min!\n ");
            }
            if (min <= max) {
                TAAnzeige.append("Felder Min als " + min + " und Max als " + max + " gesetzt.\n");
                //*int rundennummer = sq.neueRundeAnlegen(min, max);
                sq.neuesSpielAnlegen(min, max);
                int rundennummer = 1;
                TARundennummer.setText("1");
                TFMin.setText("");
                TFMax.setText("");
                TFMin.setText("" + t1 + "");
                TFMax.setText("" + t2 + "");
                TAAnzeige.append("Datenbankveränderung für ein neues Spiel durchgeführt.\n");
                TAAnzeige.append("Rundennumer:" + rundennummer + "\n");
                TARundennummer.append("" + rundennummer + "");
            }

        }
    }//GEN-LAST:event_BNeuesSpielActionPerformed

    private void BNeueRundeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNeueRundeActionPerformed
        boolean ok = false;
        if (TARundennummer.getText().equals("")) {
            ok = true;
            TAAnzeige.append("Es gibt noch keine Runde. Eine neue Runde kann erzeugt werden.\n");
        }
        if (ok == false) {
            int rundennummer = Integer.parseInt(TARundennummer.getText());
            ok = sq.kontrolliereRundeVollstaendigGetippt(rundennummer);

        }
        if (ok) {
            TAAnzeige.append("Die Runde " + TARundennummer.getText() + " wurde vollständig betippt. Eine neue Runde darf erzeugt werden.\n");
            if (RBAdministrator.isSelected()) {
                RBAdministrator.setSelected(false);
                TAAnzeige.append("Eine neue Runde wird angelegt.\n");
                String t1, t2;
                int min = -1;
                int max = -1;
                t1 = TFAMin.getText();
                t2 = TFAMax.getText();

                try {
                    min = Integer.parseInt(t1);
                    max = Integer.parseInt(t2);
                } catch (NumberFormatException nfe) {
                    TAAnzeige.append("FEHLER: Problem mit der Zahlenerkennung in Feld Min oder Max!\n");
                }
                TFAMin.setText("");
                TFAMax.setText("");
                TAAnzeige.append("Felder Min als " + min + " und Max als " + max + " gesetzt.\n");
                if (max <= min) {
                    TAAnzeige.append("FEHLER:max ist kleiner als min!\n ");
                }
                if (min <= max) {
                    //*int rundennummer = sq.neueRundeAnlegen(min, max);

                    int rundennummer = sq.neueRundeAnlegen(min, max);
                    String rundennummerS = (new Integer(rundennummer)).toString();
                    TARundennummer.setText(rundennummerS);
                    TFMin.setText("");
                    TFMax.setText("");
                    TFMin.setText("" + t1 + "");
                    TFMax.setText("" + t2 + "");
                    TAAnzeige.append("Rundennumer:" + rundennummer + "\n");
                    //TARundennummer.setText("");
                    //TARundennummer.append("" + rundennummer + "");

                    TAAnzeige.append("Datenbankveränderung für eine neue Runde durchgeführt.\n");

                }
            }
        } else {
            TAAnzeige.append("WARNUNG: Rundennummmer " + TARundennummer.getText() + " noch nicht vollständig betippt! Zunächst alle Tipps eintragen.\n");

        }
		}//GEN-LAST:event_BNeueRundeActionPerformed

    private void BAuswertenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAuswertenActionPerformed
        // TODO add your handling code here:

        if (RBAdministrator.isSelected()) {
            RBAdministrator.setSelected(false);
            TAAnzeige.append("Das Sqiel wird ausgewertet.\n");
            //Ladebalken zurückgesetzt
            PBLadebalken.setValue(0);
            if (TARundennummer.getText().equals("")) {

            } else {
                sq.auswertenButton(Integer.parseInt(TARundennummer.getText()));
            }

        }

    }//GEN-LAST:event_BAuswertenActionPerformed

    private void RBPunktestandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBPunktestandActionPerformed
        // TODO add your handling code here:
        TAAnzeige.append("Dein aktueller Punktestand wird angezeigt.\n");
        ResultSet rs = sq.sqDB.fuehreSelectAus("SELECT COUNT(*) From Benutzerinfo");
        //anzahl der Benutzer herausfinden
        int anzahlBenutzer = 0;
        try {
            anzahlBenutzer = ((Number) rs.getObject(1)).intValue();
        } catch (SQLException ex) {
            Logger.getLogger(SqielOberflaeche.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i;
        for(i = anzahlBenutzer;i>0;i--){
            int Tpkstd = 0;
            int BenutzerString = 0;
            String Benutzer = "";
            ResultSet pk;
            pk = sq.sqDB.fuehreSelectAus("SELECT SUM(pkstd) as Tpkstd FROM Tippinfo WHERE  bid ="+ i +"");
            try {
                Tpkstd = ((Number)pk.getObject(1)).intValue();
            } catch (SQLException ex) {
                Logger.getLogger(SqielOberflaeche.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet BenutzerRs;
            BenutzerRs = sq.sqDB.fuehreSelectAus("SELECT benutzer FROM BenutzerInfo WHERE bid ="+ i +"");
            try {
                Benutzer = BenutzerRs.getString(1);
            } catch (SQLException ex) {
                Logger.getLogger(SqielOberflaeche.class.getName()).log(Level.SEVERE, null, ex);
            }
            TAAnzeige.append(""+Benutzer+" = "+Tpkstd+"\n");

            
            
        }
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_RBPunktestandActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SqielOberflaeche.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SqielOberflaeche.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SqielOberflaeche.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SqielOberflaeche.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SqielOberflaeche().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAuswerten;
    private javax.swing.JButton BGO;
    private javax.swing.JButton BNeueRunde;
    private javax.swing.JButton BNeuesSpiel;
    private javax.swing.JLabel LAMax;
    private javax.swing.JLabel LAMin;
    private javax.swing.JLabel LBenutzername;
    private javax.swing.JLabel LDeinTipp;
    private javax.swing.JLabel LMax;
    private javax.swing.JLabel LMin;
    private javax.swing.JLabel LPasswort;
    private javax.swing.JProgressBar PBLadebalken;
    private javax.swing.JRadioButton RBAdministrator;
    private javax.swing.JRadioButton RBPunktestand;
    private javax.swing.JScrollPane SPAnzeige;
    private javax.swing.JSeparator STrennungswand;
    private javax.swing.JTextArea TAAnzeige;
    private javax.swing.JTextArea TARundennummer;
    private javax.swing.JTextField TFAMax;
    private javax.swing.JTextField TFAMin;
    private javax.swing.JTextField TFBenutzername;
    private javax.swing.JTextField TFDeinTipp;
    private javax.swing.JTextField TFMax;
    private javax.swing.JTextField TFMin;
    private javax.swing.JPasswordField TFPasswort;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
