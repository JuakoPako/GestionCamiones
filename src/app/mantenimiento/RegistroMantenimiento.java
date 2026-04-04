package app.mantenimiento;

import bd.Conexion;
import bd.DAOAlertas;
import bd.DAOMantenimiento;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Alertas;

public class RegistroMantenimiento extends javax.swing.JFrame {

    private Integer alertaIdSeleccionada = null;

    public RegistroMantenimiento() {

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
        jLabel4 = new javax.swing.JLabel();
        cbTipoMantenimiento = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMantenimientos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ingrese la ID del camion:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setText("Rellene los campos:");

        jLabel3.setText("Fecha del mantenimiento");

        jLabel4.setText("Tipo de mantenimiento");

        cbTipoMantenimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kilometraje", "Comustible bajo" }));
        cbTipoMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoMantenimientoActionPerformed(evt);
            }
        });

        jLabel5.setText("Descripcion");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");

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
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(cbTipoMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(118, 118, 118)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnVolver)))
                        .addGap(0, 29, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipoMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnVolver))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbTipoMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoMantenimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoMantenimientoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            String sId = txtBuscar.getText().trim();
            if (sId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese la ID del camión.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idCamion = Integer.parseInt(sId);

            if (alertaIdSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Busque la alerta antes de guardar.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idAlerta = alertaIdSeleccionada;

            // Leer campos del formulario
            java.util.Date fechaUtil = (jdcFecha != null && jdcFecha.getDate() != null) ? jdcFecha.getDate() : new java.util.Date();
            String motivo = (String) cbTipoMantenimiento.getSelectedItem();
            String descripcion = txtDescripcion.getText().trim();

            if (motivo == null || !(motivo.equalsIgnoreCase("KILOMETRAJE") || motivo.equalsIgnoreCase("COMBUSTIBLE_BAJO"))) {
                JOptionPane.showMessageDialog(this, "Motivo inválido. Seleccione KILOMETRAJE o COMBUSTIBLE_BAJO.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            model.Mantenimiento m = new model.Mantenimiento();
            m.setIdCamion(idCamion);
            m.setFecha(fechaUtil);
            m.setMotivo(motivo.toUpperCase());
            m.setDescripcion(descripcion);
            Conexion cx = Conexion.getInstancia();
            try (java.sql.Connection conn = cx.getConnection()) {
                try {
                    conn.setAutoCommit(false);

                    DAOMantenimiento daoM = new DAOMantenimiento();
                    daoM.insertarMantenimiento(conn, m);

                    DAOAlertas daoA = new DAOAlertas();
                    daoA.borrarAlerta(conn, idAlerta);

                    conn.commit();

                    JOptionPane.showMessageDialog(this, "Mantenimiento registrado correctamente. ID: " + m.getId(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarFormulario();
                } catch (Exception ex) {
                    conn.rollback();
                    // Si el DELETE devolvió 0 filas, DAOAlertas lanza SQLException y cae aquí
                    String msg = ex.getMessage() != null ? ex.getMessage() : "Error desconocido";
                    JOptionPane.showMessageDialog(this, "Error al registrar mantenimiento: " + msg, "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } finally {
                    conn.setAutoCommit(true);
                }
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "ID inválida.", "Validación", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(this, "Error BD: " + sqle.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            sqle.printStackTrace();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String sId = txtBuscar.getText().trim();
        if (sId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la ID del camión.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtBuscar.requestFocus();
            return;
        }

        try {
            int idCamion = Integer.parseInt(sId);
            DAOAlertas daoA = new DAOAlertas();
            model.Alertas alerta = daoA.encontrarPrimeraPorCamion(idCamion);
            if (alerta == null) {
                JOptionPane.showMessageDialog(this, "No hay alertas activas para este camión. No se puede generar mantenimiento desde aquí.", "Sin alertas", JOptionPane.INFORMATION_MESSAGE);
                alertaIdSeleccionada = null;
                btnGuardar.setEnabled(false);
                return;
            }

            alertaIdSeleccionada = alerta.getId();

            String tipoAlerta = alerta.getTipo();
            if ("KILOMETRAJE".equalsIgnoreCase(tipoAlerta)) {
                cbTipoMantenimiento.setSelectedItem("KILOMETRAJE");
                txtDescripcion.setText("Mantenimiento por kilometraje: revisar frenos, aceite, filtros.");
            } else if ("COMBUSTIBLE_BAJO".equalsIgnoreCase(tipoAlerta)) {
                cbTipoMantenimiento.setSelectedItem("COMBUSTIBLE_BAJO");
                txtDescripcion.setText("Revisión del sistema de combustible y llenado.");
            } else {
                cbTipoMantenimiento.setSelectedItem(tipoAlerta);
                txtDescripcion.setText("Mantenimiento relacionado con alerta: " + tipoAlerta);
            }

            if (jdcFecha != null) {
                jdcFecha.setDate(new java.util.Date());
            }

            btnGuardar.setEnabled(true);

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "La ID debe ser numérica.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtBuscar.requestFocus();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar alerta: " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroMantenimiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cbTipoMantenimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JTable tblMantenimientos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtDescripcion;
    // End of variables declaration//GEN-END:variables
// Helper: escapar comillas simples en cadenas

    private String escape(String s) {
        if (s == null) {
            return null;
        }
        return s.replace("'", "''");
    }

    private String sugerirDescripcion(String tipo) {
        if ("KILOMETRAJE".equalsIgnoreCase(tipo)) {
            return "Mantenimiento por kilometraje: revisar frenos, aceite, filtros.";
        } else if ("COMBUSTIBLE_BAJO".equalsIgnoreCase(tipo)) {
            return "Revisión del sistema de combustible y llenado.";
        } else {
            return "Mantenimiento relacionado con alerta: " + tipo;
        }
    }

    private void limpiarFormulario() {
        txtBuscar.setText("");
        txtDescripcion.setText("");
        if (jdcFecha != null) {
            jdcFecha.setDate(null);
        }
        cbTipoMantenimiento.setSelectedIndex(0);
        alertaIdSeleccionada = null;
        btnGuardar.setEnabled(false);
    }

}
