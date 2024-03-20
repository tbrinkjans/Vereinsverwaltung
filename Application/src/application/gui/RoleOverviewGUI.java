package application.gui;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import application.Application;
import application.enumeration.Permission;
import application.model.Role;
import application.service.AuthService;
import application.service.RoleService;
import application.util.Dialog;
import application.util.ImageLoader;

public class RoleOverviewGUI extends javax.swing.JFrame {

    private final RoleService roleService;
    private final AuthService authService;

    private final boolean readOnly;

    private List<Role> roles;

    public RoleOverviewGUI(RoleService roleService, AuthService authService) {
        this.roleService = roleService;
        this.authService = authService;

        readOnly = !authService.getLoggedInMember().hasPermission(Permission.WRITE_ROLES);

        initComponents();
        updateData(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfSearch = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOverview = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Application.getTitle("Rollen"));
        setIconImage(ImageLoader.getImage("icon.png"));

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                startTimer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                startTimer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                startTimer();
            }
        });

        btnAdd.setText("+");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        tblOverview.setAutoCreateRowSorter(true);
        tblOverview.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Beschreibung"
            }
        ) {
            @SuppressWarnings("rawtypes")
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            @SuppressWarnings({ "rawtypes", "unchecked" })
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOverview.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblOverview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOverviewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOverview);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Event Handling">//GEN-FIRST:event_btnAddActionPerformed
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        openDetailGUI(null);
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblOverviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOverviewMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblOverview.getSelectedRow();
            int column = tblOverview.getColumnModel().getColumnIndex("ID");
            UUID id = UUID.fromString((String) tblOverview.getValueAt(row, column));
            openDetailGUI(id);
        }
    }// </editor-fold>//GEN-LAST:event_tblOverviewMouseClicked

    private void openDetailGUI(UUID id) {
        if (id == null && readOnly) {
            Dialog.showErrorDialog("Keine Berechtigung.", this);
            return;
        }

        new RoleDetailGUI(id, this, roleService, authService).setVisible(true);
    }

    private void startTimer() {
        Timer timer = new Timer(750, (ActionEvent evt) -> updateData(false));
        timer.setRepeats(false);
        timer.start();
    }

    private boolean isFilterable(Role role, String filter) {
        return Arrays.stream(filter.split(" ")).anyMatch(param -> role.getId().toString().toLowerCase().contains(param)
            || role.getName().toLowerCase().contains(param)
            || role.getDescription().toLowerCase().contains(param));
    }

    public final void updateData(boolean fetch) {
        DefaultTableModel tblOverviewModel = (DefaultTableModel) tblOverview.getModel();
        tblOverviewModel.setRowCount(0);

        if (fetch) {
            roles = roleService.getAll();
        }
        roles.stream()
            .filter(role -> isFilterable(role, tfSearch.getText().toLowerCase().trim()))
            .map(role -> new Object[] { role.getId().toString(), role.getName(), role.getDescription() })
            .forEach(tblOverviewModel::addRow);

        tblOverviewModel.fireTableDataChanged();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblOverview;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
