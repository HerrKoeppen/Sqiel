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
        int rundennummer = -1;
        ResultSet rs = sq.sqDB.fuehreSelectAus("SELECT * FROM RundenInfo");
        try {
            while (rs.next()) {
                if (rs.getInt("rn") > rundennummer) {
                    rundennummer = rs.getInt("rn");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqielOberflaeche.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rundennummer > -1) {
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

        RBAdministrator.setFont(new java.awt.Font("Source Sans Pro", 1, 14)); // NOI18N
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

        BNeuesSpiel.setText("Neues Sqiel");
        BNeuesSpiel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNeuesSpielActionPerformed(evt);
            }
        });

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

        RBPunktestand.setText("Aktuellen Punktestand anzeigen");

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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(SPAnzeige, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(72, 72, 72))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LPasswort)
                                        .addComponent(LBenutzername)
                                        .addComponent(LDeinTipp))
                                    .addGap(59, 59, 59)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(TFPasswort, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TFBenutzername, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(TFMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(11, 11, 11)
                                                        .addComponent(LMin)))
                                                .addGap(42, 42, 42)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(TFMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(10, 10, 10)
                                                        .addComponent(LMax))))
                                            .addComponent(TFDeinTipp, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(165, 165, 165)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(RBPunktestand)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BGO)
                                .addGap(38, 38, 38))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PBLadebalken, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(BAuswerten))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TARundennummer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RBAdministrator)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BNeuesSpiel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BNeueRunde))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFAMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LAMin))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(LAMax)
                                .addGap(3, 3, 3))
                            .addComponent(TFAMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TARundennummer, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RBAdministrator)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(BNeuesSpiel)
                        .addGap(18, 18, 18)
                        .addComponent(BNeueRunde))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LAMin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TFAMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LAMax)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TFAMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(33, 33, 33)
                .addComponent(BAuswerten, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
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
                        .addComponent(LMin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LMax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFDeinTipp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LDeinTipp))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RBPunktestand)
                    .addComponent(BGO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SPAnzeige, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
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
    }//GEN-LAST:event_TFAMaxActionPerformed

    private void TFAMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFAMinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFAMinActionPerformed

    private void TFBenutzernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFBenutzernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFBenutzernameActionPerformed

    private void BGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGOActionPerformed
        TAAnzeige.append("Go-Button gedrückt.\n");
        String benutzer = TFBenutzername.getText();
        String passwort = TFPasswort.getText();
        TAAnzeige.append("Benutzer und Passwort ausgelesen: " + benutzer + " " + passwort + "\n");
        if (sq.kontrolliereAnmeldeinfo(benutzer, passwort)) {
            TAAnzeige.append("Anmeldeinfo geprüft und korrekt.\n");
            String benutzerTipp = TFDeinTipp.getText();
            try {
                int btipp = Integer.parseInt(benutzerTipp);
                TAAnzeige.append("Benutzertipp ausgelesen: " + btipp + "\n");
                sq.sqDB.aendereTipp(benutzer, btipp, Integer.parseInt(TARundennummer.getText()));
                TAAnzeige.append("Tipp verändert.\n");
                sq.gibAlleTabelleAus();
            } catch (NumberFormatException nfe) {
                TAAnzeige.append("FEHLER: Benutzertipp ist keine Zahl.");
            }
        } else {
            TAAnzeige.append("FEHLER: Anmeldeinfo falsch.\n");
        }    }//GEN-LAST:event_BGOActionPerformed

    private void TFDeinTippActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFDeinTippActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFDeinTippActionPerformed

    private void RBAdministratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBAdministratorActionPerformed
        // TODO add your handling code here:
        TAAnzeige.append("Administratormodus ist deaktiviert.\n");

        if (RBAdministrator.isSelected()) {
            TAAnzeige.append("Administratormodus ist aktiviert.\n");
        } else {
            TAAnzeige.append("Administratormodus ist deaktiviert.\n");
        }
    }//GEN-LAST:event_RBAdministratorActionPerformed

    private void BNeuesSpielActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNeuesSpielActionPerformed
        // TODO add your handling code here:

        if (RBAdministrator.isSelected()) {
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
        int rundennummer = Integer.parseInt(TARundennummer.getText());
        if (sq.kontrolliereRundeVollstaendigGetippt(rundennummer)) {
            TAAnzeige.append("Runde " + rundennummer + " vollständig betippt. Neue Runde wird erzeugt.");
            if (RBAdministrator.isSelected()) {
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
                TAAnzeige.append("Felder Min als " + min + " und Max als " + max + " gesetzt.\n");
                if (max <= min) {
                    TAAnzeige.append("FEHLER:max ist kleiner als min!\n ");
                }
                if (min <= max) {
                    //*int rundennummer = sq.neueRundeAnlegen(min, max);

                    rundennummer = sq.neueRundeAnlegen(min, max);
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


        }    }//GEN-LAST:event_BNeueRundeActionPerformed

    private void BAuswertenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAuswertenActionPerformed
        // TODO add your handling code here:

        if (RBAdministrator.isSelected()) {
            TAAnzeige.append("Das Sqiel wird ausgewertet.\n");

            sq.auswertenButton();

        }

    }//GEN-LAST:event_BAuswertenActionPerformed

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
