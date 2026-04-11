package app.mantenimiento;

import bd.ConexionBD;
import bd.DAOMantenimiento;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.Mantenimiento;
import java.sql.Connection;
import java.util.Date;

/**
 *
 * @author Franco
 */
public class ActualizarMantenimiento extends javax.swing.JFrame {

    private Integer selectedIdMantenimiento = null;

    public ActualizarMantenimiento() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtKilometraje = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbTipoMantenimiento = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMantenimientos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setText("Ingrese la ID del camion:");

        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        btnBuscar.setBackground(new java.awt.Color(100, 149, 237));
        btnBuscar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(242, 235, 227));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorderPainted(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setText("Ingrese los datos:");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setText("Fecha del mantenimiento");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setText("Descripcion");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setText("Kilometraje");

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        txtKilometraje.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        btnGuardar.setBackground(new java.awt.Color(100, 149, 237));
        btnGuardar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(242, 235, 227));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorderPainted(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnVolver.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel4.setText("Tipo de mantenimiento");

        cbTipoMantenimiento.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbTipoMantenimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kilometraje", "Comustible bajo" }));
        cbTipoMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoMantenimientoActionPerformed(evt);
            }
        });

        tblMantenimientos.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tblMantenimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "ID Camion", "Fecha", "Motivo", "Descripcion"
            }
        ));
        jScrollPane1.setViewportView(tblMantenimientos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtKilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cbTipoMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnVolver))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipoMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnVolver))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String s = txtBuscar.getText().trim();
        if (s.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese ID de mantenimiento.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtBuscar.requestFocus();
            return;
        }
        try {
            int id = Integer.parseInt(s);
            DAOMantenimiento daoM = new DAOMantenimiento();
            Mantenimiento m = daoM.encontrarPorId(id);
            if (m == null) {
                JOptionPane.showMessageDialog(this, "No se encontró mantenimiento con ID: " + id, "No encontrado", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioActualizar();
                return;
            }
            selectedIdMantenimiento = m.getId();
            if (m.getFecha() != null) {
                jDateChooser1.setDate(m.getFecha());
            } else {
                jDateChooser1.setDate(null);
            }
            cbTipoMantenimiento.setSelectedItem(m.getMotivo());
            txtDescripcion.setText(m.getDescripcion() != null ? m.getDescripcion() : "");
            try {
                java.lang.reflect.Method gm = Mantenimiento.class.getMethod("getKilometraje");
                Object km = gm.invoke(m);
                txtKilometraje.setText(km != null ? String.valueOf(km) : "");
            } catch (NoSuchMethodException nsme) {
                txtKilometraje.setText("");
            } catch (Exception ex) {
                txtKilometraje.setText("");
            }
            btnGuardar.setEnabled(true);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "La ID debe ser numérica.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtBuscar.requestFocus();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error BD al buscar: " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            if (selectedIdMantenimiento == null) {
                JOptionPane.showMessageDialog(this, "Primero busque y seleccione un mantenimiento.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            java.util.Date fecha = jDateChooser1.getDate();
            String motivo = (String) cbTipoMantenimiento.getSelectedItem();
            String descripcion = txtDescripcion.getText().trim();

            if (motivo == null || motivo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un motivo.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Construir objeto mantenimiento con los datos del formulario
            model.Mantenimiento m = new model.Mantenimiento();
            m.setId(selectedIdMantenimiento);
            m.setFecha(fecha);
            m.setMotivo(motivo);
            m.setDescripcion(descripcion);

            // Abrir conexión y usar transacción
            try (java.sql.Connection conn = bd.ConexionBD.getInstancia().getConnection()) {
                conn.setAutoCommit(false);
                try {
                    // 1) Obtener id_camion asociado al mantenimiento (si no lo tienes en el formulario)
                    Integer idCamion = null;
                    try (java.sql.PreparedStatement ps = conn.prepareStatement(
                            "SELECT id_camion FROM Mantenimiento WHERE id = ?")) {
                        ps.setInt(1, m.getId());
                        try (java.sql.ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                idCamion = rs.getInt("id_camion");
                            } else {
                                throw new java.sql.SQLException("Mantenimiento no encontrado (id=" + m.getId() + ")");
                            }
                        }
                    }

                    try (java.sql.PreparedStatement ps = conn.prepareStatement(
                            "UPDATE Mantenimiento SET fecha = ?, motivo = ?, descripcion = ? WHERE id = ?")) {
                        // convertir java.util.Date a java.sql.Date o Timestamp según tu columna
                        java.sql.Date sqlDate = new java.sql.Date(m.getFecha().getTime());
                        ps.setDate(1, sqlDate);
                        ps.setString(2, m.getMotivo());
                        ps.setString(3, m.getDescripcion());
                        ps.setInt(4, m.getId());
                        ps.executeUpdate();
                    }

                    // 3) Borrar todas las alertas del camión (id_camion)
                    try (java.sql.PreparedStatement ps = conn.prepareStatement(
                            "DELETE FROM Alertas WHERE id_camion = ?")) {
                        ps.setInt(1, idCamion);
                        int deleted = ps.executeUpdate();
                        System.out.println("Alertas borradas: " + deleted);
                    }

                    // 4) Commit si todo OK
                    conn.commit();

                    JOptionPane.showMessageDialog(this, "Mantenimiento actualizado correctamente. Alertas del camión eliminadas.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarTablaMantenimientos();
                    limpiarFormularioActualizar();

                } catch (Exception innerEx) {
                    // Rollback ante cualquier fallo
                    try {
                        conn.rollback();
                    } catch (Exception rbEx) {
                        rbEx.printStackTrace();
                    }
                    throw innerEx;
                } finally {
                    conn.setAutoCommit(true);
                }
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Valor numérico inválido.", "Validación", JOptionPane.WARNING_MESSAGE);
        } catch (java.sql.SQLException sqle) {
            JOptionPane.showMessageDialog(this, "Error BD al actualizar: " + sqle.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            sqle.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbTipoMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoMantenimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoMantenimientoActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        GestorMantenimiento gestorMantenimiento = new GestorMantenimiento();
        gestorMantenimiento.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

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
            java.util.logging.Logger.getLogger(ActualizarMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActualizarMantenimiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cbTipoMantenimiento;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblMantenimientos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtKilometraje;
    // End of variables declaration//GEN-END:variables
private String escape(String s) {
        if (s == null) {
            return null;
        }
        return s.replace("'", "''");
    }

    private void limpiarFormularioActualizar() {
        selectedIdMantenimiento = null;
        jDateChooser1.setDate(null);
        cbTipoMantenimiento.setSelectedIndex(0);
        txtDescripcion.setText("");
        txtKilometraje.setText("");
        txtBuscar.setText("");
        btnGuardar.setEnabled(false);
    }

    // Carga la tabla de mantenimientos (implementación simple)
    private void cargarTablaMantenimientos() {
        try {
            DAOMantenimiento dao = new DAOMantenimiento();
            List<Mantenimiento> lista = dao.encontrarTodos(null); // todos
            DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "ID Camión", "Fecha", "Motivo", "Descripción"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            for (Mantenimiento m : lista) {
                Object fecha = m.getFecha() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(m.getFecha()) : null;
                model.addRow(new Object[]{m.getId(), m.getIdCamion(), fecha, m.getMotivo(), m.getDescripcion()});
            }
            tblMantenimientos.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar tabla: " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

}
