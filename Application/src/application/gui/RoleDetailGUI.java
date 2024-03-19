package application.gui;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import application.Application;
import application.enumeration.Permission;
import application.exception.EntityNotFoundException;
import application.exception.RestrictedDeleteException;
import application.model.Member;
import application.model.Role;
import application.service.AuthService;
import application.service.RoleService;
import application.util.Dialog;

public class RoleDetailGUI extends javax.swing.JFrame {

    private final RoleOverviewGUI overviewGUI;
    private final RoleService roleService;

    private final List<Permission> allPermissions;

    private final boolean readOnly;
    private final boolean isNew;

    private Role role;

    public RoleDetailGUI(UUID id, RoleOverviewGUI overviewGUI, RoleService roleService, AuthService authService) {
        this.overviewGUI = overviewGUI;
        this.roleService = roleService;

        allPermissions = Arrays.asList(Permission.values());

        Member loggedInMember = authService.getLoggedInMember();
        readOnly = !loggedInMember.hasPermission(Permission.WRITE_ROLES);
        isNew = loadRole(id);

        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pRole = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        lblId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        tfDescription = new javax.swing.JTextField();
        lblDescription = new javax.swing.JLabel();
        pPermissions = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lPermissions = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Application.getTitle("Rolle"));
        setIconImage(new ImageIcon("res\\icon.png").getImage());

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

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(68, 171));

        lblName.setText("Name:");

        tfName.setEditable(!readOnly);
        tfName.setText(role.getName());

        lblId.setText("ID:");

        tfId.setEditable(false);
        tfId.setText(role.getId().toString());

        tfDescription.setEditable(!readOnly);
        tfDescription.setText(role.getDescription());

        lblDescription.setText("Beschreibung:");

        javax.swing.GroupLayout pRoleLayout = new javax.swing.GroupLayout(pRole);
        pRole.setLayout(pRoleLayout);
        pRoleLayout.setHorizontalGroup(
            pRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pRoleLayout.createSequentialGroup()
                    .addGroup(pRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(lblDescription))
                    .addGap(5, 5, 5)
                    .addGroup(pRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfDescription)
                        .addComponent(tfId, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                        .addComponent(tfName))));
        pRoleLayout.setVerticalGroup(
            pRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pRoleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfId))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                    .addGroup(pRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfName)
                        .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                    .addGroup(pRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfDescription))
                    .addGap(11, 11, 11)));

        jTabbedPane1.addTab("Rolle", pRole);

        DefaultListModel<String> lPermissionsModel = new DefaultListModel<>();
        lPermissionsModel.addAll(allPermissions.stream().map(permission -> permission.toString()).collect(Collectors.toList()));
        lPermissions.setModel(lPermissionsModel);
        lPermissions.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        List<String> allPermissionNames = allPermissions.stream().map(permission -> permission.toString()).collect(Collectors.toList());
        Stream<String> selectedPermissionNames = role.getPermissions().stream().map(permission -> permission.toString());
        int[] lPermissionIndices = selectedPermissionNames.mapToInt(name -> allPermissionNames.indexOf(name)).toArray();
        lPermissions.setSelectedIndices(lPermissionIndices);
        lPermissions.setEnabled(!readOnly);
        jScrollPane1.setViewportView(lPermissions);

        javax.swing.GroupLayout pPermissionsLayout = new javax.swing.GroupLayout(pPermissions);
        pPermissions.setLayout(pPermissionsLayout);
        pPermissionsLayout.setHorizontalGroup(
            pPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE));
        pPermissionsLayout.setVerticalGroup(
            pPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pPermissionsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addContainerGap()));

        jTabbedPane1.addTab("Berechtigungen", pPermissions);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDelete))
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()));
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave)
                            .addComponent(btnDelete))
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Event Handling">//GEN-FIRST:event_btnSaveActionPerformed
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        role.setName(tfName.getText().trim());
        role.setDescription(tfDescription.getText().trim());
        role.setPermissions(
            allPermissions.stream()
                .filter(permission -> lPermissions.getSelectedValuesList().contains(permission.toString()))
                .collect(Collectors.toList()));
        saveRole();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteRole();
    }// </editor-fold>//GEN-LAST:event_btnDeleteActionPerformed

    private boolean loadRole(UUID id) {
        try {
            if (id == null) {
                throw new EntityNotFoundException(id);
            }

            role = roleService.get(id);
            return false;
        } catch (EntityNotFoundException ex) {
            role = new Role(UUID.randomUUID(), "", "");
            return true;
        }
    }

    private void saveRole() {
        if (role.getName().isEmpty()) {
            Dialog.showErrorDialog("Ungültige Eingabe.", this);
            return;
        }

        if (isNew) {
            roleService.create(role);
            Dialog.showInfoDialog("Rolle erstellt.", this);
        } else {
            try {
                roleService.update(role);
                Dialog.showInfoDialog("Rolle aktualisiert.", this);
            } catch (EntityNotFoundException ex) {
                Dialog.showErrorDialog("Rolle nicht vorhanden.", this);
            }
        }

        overviewGUI.updateData(true);
        dispose();
    }

    private void deleteRole() {
        if (Dialog.showConfirmDialog("Rolle wirklich löschen?", this)) {
            try {
                roleService.delete(role.getId());
                Dialog.showInfoDialog("Rolle gelöscht.", this);
            } catch (RestrictedDeleteException ex) {
                Dialog.showErrorDialog("Rolle ist in Verwendung.", this);
            } catch (EntityNotFoundException ex) {
                Dialog.showErrorDialog("Rolle nicht vorhanden.", this);
            }
            overviewGUI.updateData(true);
            dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> lPermissions;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblName;
    private javax.swing.JPanel pPermissions;
    private javax.swing.JPanel pRole;
    private javax.swing.JTextField tfDescription;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}
