package application.gui;

import java.awt.Window;
import java.util.Arrays;

import application.Application;
import application.enumeration.Permission;
import application.service.AuthService;
import application.service.MemberService;
import application.service.RoleService;
import application.service.TeamService;
import application.util.Dialog;
import application.util.ImageLoader;

public class DashboardGUI extends javax.swing.JFrame {

    private final AuthService authService;

    public DashboardGUI(AuthService authService) {
        this.authService = authService;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnMember = new javax.swing.JButton();
        btnRoles = new javax.swing.JButton();
        btnTeams = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(Application.getTitle("Dashboard"));
        setIconImage(ImageLoader.getImage("icon.png"));

        lblName.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblName.setText("Willkommen, " + authService.getLoggedInMember().getFirstName() + "!");

        btnMember.setText("Mitglieder verwalten");
        btnMember.setEnabled(authService.getLoggedInMember().hasPermission(Permission.READ_MEMBERS)
        );
        btnMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMemberActionPerformed(evt);
            }
        });

        btnRoles.setText("Rollen verwalten");
        btnRoles.setEnabled(authService.getLoggedInMember().hasPermission(Permission.READ_ROLES));
        btnRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRolesActionPerformed(evt);
            }
        });

        btnTeams.setText("Teams verwalten");
        btnTeams.setEnabled(authService.getLoggedInMember().hasPermission(Permission.READ_TEAMS));
        btnTeams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTeamsActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(153, 153, 153));
        btnLogout.setText("Abmelden");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(btnMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRoles, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                    .addComponent(btnTeams, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMember, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRoles, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTeams, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Event Handling">//GEN-FIRST:event_btnMemberActionPerformed
    private void btnMemberActionPerformed(java.awt.event.ActionEvent evt) {
        openMemberOverview();
    }//GEN-LAST:event_btnMemberActionPerformed

    private void btnRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRolesActionPerformed
        openRoleOverview();
    }//GEN-LAST:event_btnRolesActionPerformed

    private void btnTeamsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTeamsActionPerformed
        openTeamOverview();
    }//GEN-LAST:event_btnTeamsActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        logout();
    }// </editor-fold>//GEN-LAST:event_btnLogoutActionPerformed

    private void openMemberOverview() {
        MemberService memberService = Application.getService(MemberService.class);
        new MemberOverviewGUI(memberService, authService).setVisible(true);
    }

    private void openRoleOverview() {
        RoleService roleService = Application.getService(RoleService.class);
        new RoleOverviewGUI(roleService, authService).setVisible(true);
    }

    private void openTeamOverview() {
        TeamService teamService = Application.getService(TeamService.class);
        new TeamOverviewGUI(teamService, authService).setVisible(true);
    }

    private void logout() {
        if (Dialog.showConfirmDialog("Wirklich abmelden?", this)) {
            Arrays.stream(Window.getWindows()).forEach(win -> win.dispose());
            new LoginGUI(authService).setVisible(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMember;
    private javax.swing.JButton btnRoles;
    private javax.swing.JButton btnTeams;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblName;
    // End of variables declaration//GEN-END:variables
}
