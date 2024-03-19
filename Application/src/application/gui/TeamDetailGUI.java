package application.gui;

import java.util.UUID;

import javax.swing.ImageIcon;

import application.Application;
import application.enumeration.Permission;
import application.exception.EntityNotFoundException;
import application.exception.RestrictedDeleteException;
import application.model.Member;
import application.model.Team;
import application.service.AuthService;
import application.service.TeamService;
import application.util.Dialog;

public class TeamDetailGUI extends javax.swing.JFrame {

    private final TeamOverviewGUI overviewGUI;
    private final TeamService teamService;

    private final boolean readOnly;
    private final boolean isNew;

    private Team team;

    public TeamDetailGUI(UUID id, TeamOverviewGUI overviewGUI, TeamService teamService, AuthService authService) {
        this.overviewGUI = overviewGUI;
        this.teamService = teamService;

        Member loggedInMember = authService.getLoggedInMember();
        readOnly = !loggedInMember.hasPermission(Permission.WRITE_TEAMS);
        isNew = loadTeam(id);

        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pTeam = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        lblId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        tfActivity = new javax.swing.JTextField();
        lblActivity = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Application.getTitle("Team"));
        setIconImage(new ImageIcon("res\\icon.png").getImage());

        lblName.setText("Name:");

        tfName.setEditable(!readOnly);
        tfName.setText(team.getName());

        lblId.setText("ID:");

        tfId.setEditable(false);
        tfId.setText(team.getId().toString());

        tfActivity.setEditable(!readOnly);
        tfActivity.setText(team.getActivity());

        lblActivity.setText("Aktivität:");

        javax.swing.GroupLayout pTeamLayout = new javax.swing.GroupLayout(pTeam);
        pTeam.setLayout(pTeamLayout);
        pTeamLayout.setHorizontalGroup(
            pTeamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTeamLayout.createSequentialGroup()
                    .addGroup(pTeamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblActivity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pTeamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                        .addComponent(tfId)
                        .addComponent(tfActivity))));
        pTeamLayout.setVerticalGroup(
            pTeamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pTeamLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pTeamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfId))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                    .addGroup(pTeamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfName)
                        .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                    .addGroup(pTeamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblActivity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfActivity))
                    .addGap(11, 11, 11)));

        jTabbedPane1.addTab("Team", pTeam);

        btnDelete.setBackground(new java.awt.Color(255, 0, 51));
        btnDelete.setText("Löschen");
        btnDelete.setEnabled(!readOnly && !isNew);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(0, 255, 153));
        btnSave.setText("Speichern");
        btnSave.setEnabled(!readOnly);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDelete)))
                    .addContainerGap()));
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave)
                            .addComponent(btnDelete))
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Event Handling">//GEN-FIRST:event_btnSaveActionPerformed
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        team.setName(tfName.getText().trim());
        team.setActivity(tfActivity.getText().trim());
        saveTeam();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteTeam();
    }// </editor-fold>//GEN-LAST:event_btnDeleteActionPerformed

    private boolean loadTeam(UUID id) {
        try {
            if (id == null) {
                throw new EntityNotFoundException(id);
            }

            team = teamService.get(id);
            return false;
        } catch (EntityNotFoundException ex) {
            team = new Team(UUID.randomUUID(), "", "");
            return true;
        }
    }

    private void saveTeam() {
        if (team.getName().isEmpty() || team.getActivity().isEmpty()) {
            Dialog.showErrorDialog("Ungültige Eingabe.", this);
            return;
        }

        if (isNew) {
            teamService.create(team);
            Dialog.showInfoDialog("Team erstellt.", this);
        } else {
            try {
                teamService.update(team);
                Dialog.showInfoDialog("Team aktualisiert.", this);
            } catch (EntityNotFoundException ex) {
                Dialog.showErrorDialog("Team nicht vorhanden.", this);
            }
        }

        overviewGUI.updateData(true);
        dispose();
    }

    private void deleteTeam() {
        if (Dialog.showConfirmDialog("Team wirklich löschen?", this)) {
            try {
                teamService.delete(team.getId());
                Dialog.showInfoDialog("Team gelöscht.", this);
            } catch (RestrictedDeleteException ex) {
                Dialog.showErrorDialog("Team ist in Verwendung.", this);
            } catch (EntityNotFoundException ex) {
                Dialog.showErrorDialog("Team nicht vorhanden.", this);
            }
            overviewGUI.updateData(true);
            dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblActivity;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblName;
    private javax.swing.JPanel pTeam;
    private javax.swing.JTextField tfActivity;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}
