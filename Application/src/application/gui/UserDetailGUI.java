package application.gui;

import application.Application;
import application.model.Member;
import application.service.MemberService;
import java.util.List;
import java.util.UUID;
import javax.swing.ImageIcon;

public class UserDetailGUI extends javax.swing.JFrame {

    private final MemberService memberService;
    
    public UserDetailGUI(MemberService memberService) {
        this.memberService = memberService;
        initComponents();
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        T_Nachname = new javax.swing.JTextField();
        Nachname = new javax.swing.JLabel();
        Vorname = new javax.swing.JLabel();
        T_Vorname = new javax.swing.JTextField();
        Adresse = new javax.swing.JLabel();
        T_Adresse = new javax.swing.JTextField();
        Rolle = new javax.swing.JLabel();
        Gruppe = new java.awt.Label();
        Rolle_choice = new java.awt.Choice();
        gruppe_choice = new java.awt.Choice();
        Benutzer_suchen = new javax.swing.JButton();
        Benutzer_löschen = new javax.swing.JButton();
        Speichern = new javax.swing.JButton();
        textArea1 = new java.awt.TextArea();
        Benutzer_ID = new javax.swing.JLabel();
        T_ID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(Application.getTitle("Benutzer verwalten"));
        setBackground(new java.awt.Color(204, 255, 255));
        setIconImage(new ImageIcon("res\\icon.png").getImage());

        Nachname.setText("Nachname");

        Vorname.setText("Vorname");

        Adresse.setText("Adresse");

        Rolle.setText("Rolle");

        Gruppe.setText("Gruppe");

        Benutzer_suchen.setText("Benutzer suchen");
        Benutzer_suchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Benutzer_suchenActionPerformed(evt);
            }
        });

        Benutzer_löschen.setBackground(new java.awt.Color(255, 0, 51));
        Benutzer_löschen.setText("Benutzer löschen");
        Benutzer_löschen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Benutzer_löschenActionPerformed(evt);
            }
        });

        Speichern.setBackground(new java.awt.Color(0, 255, 153));
        Speichern.setText("Speichern");
        Speichern.setToolTipText("");
        Speichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpeichernActionPerformed(evt);
            }
        });

        Benutzer_ID.setText("Benutzer-ID");

        T_ID.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Vorname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Nachname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Adresse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Benutzer_ID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(T_Nachname)
                    .addComponent(T_Vorname)
                    .addComponent(T_Adresse)
                    .addComponent(Speichern, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(T_ID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Rolle, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Gruppe, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Benutzer_löschen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Rolle_choice, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(gruppe_choice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
            .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(371, 371, 371)
                .addComponent(Benutzer_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Benutzer_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(T_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gruppe_choice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Gruppe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Nachname, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(T_Nachname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Rolle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Vorname)
                                .addComponent(T_Vorname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Adresse)
                            .addComponent(T_Adresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Rolle_choice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Speichern)
                    .addComponent(Benutzer_löschen))
                .addGap(18, 18, 18)
                .addComponent(Benutzer_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        T_Nachname.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Benutzer_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Benutzer_suchenActionPerformed
        // Benutzer suchen
        String nachname = T_Nachname.getText();
        String vorname = T_Vorname.getText();

        // Suchen nach Benutzern in der Datenbank
        List<Member> foundMembers = memberService.findByName(vorname, nachname);

        // Ergebnisse anzeigen
        StringBuilder resultText = new StringBuilder("Gefundene Benutzer:\n");
        for (Member member : foundMembers) {
            resultText.append("Nachname: ").append(member.getLastName()).append("\n");
            resultText.append("Vorname: ").append(member.getFirstName()).append("\n\n");
        }

        if (!foundMembers.isEmpty()) {
            Member member = foundMembers.get(0);
            T_ID.setText(member.getId().toString());
            T_Vorname.setText(member.getFirstName());
            T_Nachname.setText(member.getLastName());
            T_Adresse.setText(member.getAddress());
            
        }
        
        textArea1.setText(resultText.toString());
    }//GEN-LAST:event_Benutzer_suchenActionPerformed

    private void Benutzer_löschenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Benutzer_löschenActionPerformed
        String id = T_ID.getText();
        if (!id.isEmpty()) {
            memberService.delete(UUID.fromString(id));
            T_ID.setText("");
            T_Nachname.setText("");
            T_Vorname.setText("");
            T_Adresse.setText("");
            textArea1.setText("Benutzer gelöscht:\n" + "id: " + id);
        } else {
            textArea1.setText("Löschen nicht möglich!");
            // FEHLER
        }              
    }//GEN-LAST:event_Benutzer_löschenActionPerformed

    private void SpeichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpeichernActionPerformed
        String id = T_ID.getText();
        String vorname = T_Vorname.getText();
        String nachname = T_Nachname.getText();
        String adresse = T_Adresse.getText();
        
        if (id.isEmpty())  {
            Member newMember = new Member(UUID.randomUUID(), vorname, nachname, adresse);
            memberService.create(newMember);
            textArea1.setText("Benutzer neu erstellt.");
            T_ID.setText(newMember.getId().toString());
        } else {
            Member memberToUpdate = new Member(UUID.fromString(id), vorname, nachname, adresse);
            memberService.update(memberToUpdate);
            textArea1.setText("Benutzerdaten aktualisiert.");
        }
    }//GEN-LAST:event_SpeichernActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Adresse;
    private javax.swing.JLabel Benutzer_ID;
    private javax.swing.JButton Benutzer_löschen;
    private javax.swing.JButton Benutzer_suchen;
    private java.awt.Label Gruppe;
    private javax.swing.JLabel Nachname;
    private javax.swing.JLabel Rolle;
    private java.awt.Choice Rolle_choice;
    private javax.swing.JButton Speichern;
    private javax.swing.JTextField T_Adresse;
    private javax.swing.JTextField T_ID;
    private javax.swing.JTextField T_Nachname;
    private javax.swing.JTextField T_Vorname;
    private javax.swing.JLabel Vorname;
    private java.awt.Choice gruppe_choice;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
