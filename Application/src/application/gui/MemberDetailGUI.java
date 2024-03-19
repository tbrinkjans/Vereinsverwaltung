package application.gui;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import application.Application;
import application.enumeration.Permission;
import application.exception.EntityNotFoundException;
import application.model.Member;
import application.model.Role;
import application.model.Team;
import application.service.AuthService;
import application.service.MemberService;
import application.service.RoleService;
import application.service.TeamService;
import application.util.Dialog;

public class MemberDetailGUI extends javax.swing.JFrame {

    private final MemberOverviewGUI overviewGUI;
    private final MemberService memberService;

    private final List<Role> allRoles;
    private final List<Team> allTeams;

    private final boolean readOnly;
    private final boolean isSelf;
    private final boolean isNew;

    private Member member;

    public MemberDetailGUI(UUID id, MemberOverviewGUI overviewGUI, MemberService memberService, AuthService authService, RoleService roleService, TeamService teamService) {
        this.overviewGUI = overviewGUI;
        this.memberService = memberService;

        allRoles = roleService.getAll();
        allTeams = teamService.getAll();

        Member loggedInMember = authService.getLoggedInMember();
        readOnly = !loggedInMember.hasPermission(Permission.WRITE_MEMBERS);
        isSelf = loggedInMember.getId().equals(id);
        isNew = loadMember(id);

        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Benutzer_löschen = new javax.swing.JButton();
        Speichern = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pMitglied = new javax.swing.JPanel();
        Vorname = new javax.swing.JLabel();
        T_Vorname = new javax.swing.JTextField();
        Adresse = new javax.swing.JLabel();
        T_Adresse = new javax.swing.JTextField();
        Benutzer_ID = new javax.swing.JLabel();
        T_ID = new javax.swing.JTextField();
        T_Nachname = new javax.swing.JTextField();
        Nachname = new javax.swing.JLabel();
        pRollen = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lRollen = new javax.swing.JList<>();
        pTeams = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lTeams = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Application.getTitle("Mitglied"));
        setBackground(new java.awt.Color(204, 255, 255));
        setIconImage(new ImageIcon("res\\icon.png").getImage());

        Benutzer_löschen.setBackground(new java.awt.Color(255, 0, 51));
        Benutzer_löschen.setText("Löschen");
        Benutzer_löschen.setEnabled(!readOnly && !isNew && !isSelf);
        Benutzer_löschen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Benutzer_löschenActionPerformed(evt);
            }
        });

        Speichern.setBackground(new java.awt.Color(0, 255, 153));
        Speichern.setText("Speichern");
        Speichern.setToolTipText("");
        Speichern.setEnabled(!readOnly);
        Speichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpeichernActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(68, 171));

        Vorname.setText("Vorname:");

        T_Vorname.setEditable(!readOnly);
        T_Vorname.setText(member.getFirstName());

        Adresse.setText("Adresse:");

        T_Adresse.setEditable(!readOnly);
        T_Adresse.setText(member.getAddress());

        Benutzer_ID.setText("ID:");

        T_ID.setEditable(false);
        T_ID.setText(member.getId().toString());

        T_Nachname.setEditable(!readOnly);
        T_Nachname.setText(member.getLastName());

        Nachname.setText("Nachname:");

        javax.swing.GroupLayout pMitgliedLayout = new javax.swing.GroupLayout(pMitglied);
        pMitglied.setLayout(pMitgliedLayout);
        pMitgliedLayout.setHorizontalGroup(
            pMitgliedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pMitgliedLayout.createSequentialGroup()
                    .addGroup(pMitgliedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Vorname, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                        .addComponent(Benutzer_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(6, 6, 6)
                    .addGroup(pMitgliedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(T_Vorname)
                        .addComponent(T_ID)))
                .addGroup(pMitgliedLayout.createSequentialGroup()
                    .addGroup(pMitgliedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Nachname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(6, 6, 6)
                    .addGroup(pMitgliedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(T_Nachname, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                        .addComponent(T_Adresse))));
        pMitgliedLayout.setVerticalGroup(
            pMitgliedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pMitgliedLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pMitgliedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Benutzer_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(T_ID))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pMitgliedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(T_Vorname)
                        .addComponent(Vorname, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pMitgliedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Nachname, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(T_Nachname))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pMitgliedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(T_Adresse))
                    .addGap(8, 8, 8)));

        T_Nachname.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("Mitglied", pMitglied);

        DefaultListModel<String> lRollenModel = new DefaultListModel<>();
        lRollenModel.addAll(allRoles.stream().map(role -> role.getName()).collect(Collectors.toList()));
        lRollen.setModel(lRollenModel);
        lRollen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        List<UUID> allRoleIds = allRoles.stream().map(role -> role.getId()).collect(Collectors.toList());
        Stream<UUID> selectedRoleIds = member.getRoles().stream().map(role -> role.getId());
        int[] lRollenIndices = selectedRoleIds.mapToInt(id -> allRoleIds.indexOf(id)).toArray();
        lRollen.setSelectedIndices(lRollenIndices);
        lRollen.setEnabled(!readOnly && !isSelf);
        jScrollPane1.setViewportView(lRollen);

        javax.swing.GroupLayout pRollenLayout = new javax.swing.GroupLayout(pRollen);
        pRollen.setLayout(pRollenLayout);
        pRollenLayout.setHorizontalGroup(
            pRollenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE));
        pRollenLayout.setVerticalGroup(
            pRollenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pRollenLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addContainerGap()));

        jTabbedPane1.addTab("Rollen", pRollen);

        DefaultListModel<String> lTeamsModel = new DefaultListModel<>();
        lTeamsModel.addAll(allTeams.stream().map(team -> team.getName()).collect(Collectors.toList()));
        lTeams.setModel(lTeamsModel);
        lTeams.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        List<UUID> allTeamIds = allTeams.stream().map(team -> team.getId()).collect(Collectors.toList());
        Stream<UUID> selectedTeamIds = member.getTeams().stream().map(team -> team.getId());
        int[] lTeamsIndices = selectedTeamIds.mapToInt(id -> allTeamIds.indexOf(id)).toArray();
        lTeams.setSelectedIndices(lTeamsIndices);
        lTeams.setEnabled(!readOnly && !isSelf);
        jScrollPane2.setViewportView(lTeams);

        javax.swing.GroupLayout pTeamsLayout = new javax.swing.GroupLayout(pTeams);
        pTeams.setLayout(pTeamsLayout);
        pTeamsLayout.setHorizontalGroup(
            pTeamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE));
        pTeamsLayout.setVerticalGroup(
            pTeamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pTeamsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addContainerGap()));

        jTabbedPane1.addTab("Teams", pTeams);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Speichern, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Benutzer_löschen))
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()));
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Speichern)
                            .addComponent(Benutzer_löschen))
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Event Handling">//GEN-FIRST:event_SpeichernActionPerformed
    private void SpeichernActionPerformed(java.awt.event.ActionEvent evt) {
        member.setFirstName(T_Vorname.getText().trim());
        member.setLastName(T_Nachname.getText().trim());
        member.setAddress(T_Adresse.getText().trim());
        member.setRoles(
            allRoles.stream()
                .filter(role -> lRollen.getSelectedValuesList().contains(role.getName()))
                .collect(Collectors.toList()));
        member.setTeams(
            allTeams.stream()
                .filter(team -> lTeams.getSelectedValuesList().contains(team.getName()))
                .collect(Collectors.toList()));
        saveMember();
    }//GEN-LAST:event_SpeichernActionPerformed

    private void Benutzer_löschenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Benutzer_löschenActionPerformed
        deleteMember();
    }// </editor-fold>//GEN-LAST:event_Benutzer_löschenActionPerformed

    private boolean loadMember(UUID id) {
        try {
            if (id == null) {
                throw new EntityNotFoundException(id);
            }

            member = memberService.get(id);
            return false;
        } catch (EntityNotFoundException ex) {
            member = new Member(UUID.randomUUID(), "", "", "");
            return true;
        }
    }

    private void saveMember() {
        if (member.getFirstName().isEmpty() || member.getLastName().isEmpty()) {
            Dialog.showErrorDialog("Ungültige Eingabe.", this);
            return;
        }

        if (isNew) {
            memberService.create(member);
            Dialog.showInfoDialog("Mitglied erstellt.", this);
        } else {
            memberService.update(member);
            Dialog.showInfoDialog("Mitglied aktualisiert.", this);
        }

        overviewGUI.updateData(true);
        dispose();
    }

    private void deleteMember() {
        if (Dialog.showConfirmDialog("Mitglied wirklich löschen?", this)) {
            memberService.delete(member.getId());
            Dialog.showInfoDialog("Mitglied gelöscht.", this);
            overviewGUI.updateData(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> lRollen;
    private javax.swing.JList<String> lTeams;
    private javax.swing.JPanel pMitglied;
    private javax.swing.JPanel pRollen;
    private javax.swing.JPanel pTeams;
    // End of variables declaration//GEN-END:variables
}
