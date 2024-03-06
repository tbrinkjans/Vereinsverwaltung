package application.gui;

import java.util.UUID;

import javax.swing.ImageIcon;

import application.Application;
import application.exception.EntityNotFoundException;
import application.model.Member;
import application.service.MemberService;
import application.util.Dialog;

public class MemberDetailGUI extends javax.swing.JFrame {

    private final MemberService memberService;

    private Member initial;

    public MemberDetailGUI(UUID id, MemberService memberService) {
        this.memberService = memberService;
        try {
            initial = memberService.get(id);
        } catch (EntityNotFoundException ex) {
            initial = null;
        }
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        T_Nachname = new javax.swing.JTextField();
        Nachname = new javax.swing.JLabel();
        Vorname = new javax.swing.JLabel();
        T_Vorname = new javax.swing.JTextField();
        Adresse = new javax.swing.JLabel();
        T_Adresse = new javax.swing.JTextField();
        Benutzer_löschen = new javax.swing.JButton();
        Speichern = new javax.swing.JButton();
        Benutzer_ID = new javax.swing.JLabel();
        T_ID = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Application.getTitle("Mitglied"));
        setBackground(new java.awt.Color(204, 255, 255));
        setIconImage(new ImageIcon("res\\icon.png").getImage());
        setResizable(false);

        T_Nachname.setText(initial != null ? initial.getLastName() : "");

        Nachname.setText("Nachname");

        Vorname.setText("Vorname");

        T_Vorname.setText(initial != null ? initial.getFirstName() : "");

        Adresse.setText("Adresse");

        T_Adresse.setText(initial != null ? initial.getAddress() : "");

        Benutzer_löschen.setBackground(new java.awt.Color(255, 0, 51));
        Benutzer_löschen.setText("Löschen");
        Benutzer_löschen.setEnabled(initial != null);
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

        Benutzer_ID.setText("ID");

        T_ID.setEditable(false);
        T_ID.setText(initial != null ? initial.getId().toString() : "");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Nachname, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                .addComponent(Adresse, javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(6, 6, 6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(T_Nachname)
                                .addComponent(T_Adresse)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Vorname, javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Benutzer_ID, javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(T_ID, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                .addComponent(T_Vorname)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Speichern, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Benutzer_löschen)))
                    .addContainerGap()));
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Benutzer_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(T_ID, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(T_Vorname, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Vorname, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Nachname, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(T_Nachname, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(T_Adresse))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Speichern)
                            .addComponent(Benutzer_löschen))
                        .addComponent(jSeparator2))
                    .addContainerGap()));

        T_Nachname.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Event
    // Handling">//GEN-FIRST:event_SpeichernActionPerformed
    private void SpeichernActionPerformed(java.awt.event.ActionEvent evt) {
        String firstName = T_Vorname.getText().trim();
        String lastName = T_Nachname.getText().trim();
        String address = T_Adresse.getText().trim();
        saveMember(firstName, lastName, address);
    }// GEN-LAST:event_SpeichernActionPerformed

    private void Benutzer_löschenActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_Benutzer_löschenActionPerformed
        deleteMember();
    }// </editor-fold>//GEN-LAST:event_Benutzer_löschenActionPerformed

    private void saveMember(String firstName, String lastName, String address) {
        if (firstName.isEmpty() || lastName.isEmpty()) {
            Dialog.showErrorDialog("Ungültige Eingabe.", this);
            return;
        }

        if (initial == null) {
            Member create = new Member(UUID.randomUUID(), firstName, lastName, address);
            memberService.create(create);
            Dialog.showInfoDialog("Mitglied erstellt.", this);
        } else {
            Member update = new Member(initial.getId(), firstName, lastName, address, initial.getTeams(), initial.getRoles());
            memberService.update(update);
            Dialog.showInfoDialog("Mitglied aktualisiert.", this);
        }

        dispose();
    }

    private void deleteMember() {
        if (Dialog.showConfirmDialog("Mitglied wirklich löschen?", this)) {
            memberService.delete(initial.getId());
            Dialog.showInfoDialog("Mitglied gelöscht.", this);

            dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Adresse;
    private javax.swing.JLabel Benutzer_ID;
    private javax.swing.JButton Benutzer_löschen;
    private javax.swing.JLabel Nachname;
    private javax.swing.JButton Speichern;
    private javax.swing.JTextField T_Adresse;
    private javax.swing.JTextField T_ID;
    private javax.swing.JTextField T_Nachname;
    private javax.swing.JTextField T_Vorname;
    private javax.swing.JLabel Vorname;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
