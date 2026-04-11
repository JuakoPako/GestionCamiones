/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package app.camiones;

import bd.DAOCamion;
import bd.DAOUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Camion;
import model.Usuario;

/**
 *
 * @author bevod
 */
public class AgregarCamion extends javax.swing.JFrame {

    /**
     * Creates new form AgregarCamion
     */
    public AgregarCamion() {
        initComponents();
        cargarComboConductores();
    }

    private void cargarComboConductores() {
        try {
            DAOUsuario daoUsuario = new DAOUsuario();

            ArrayList<Usuario> lista = daoUsuario.getListaConductores();

            cmbAsignado.removeAllItems();

            for (Usuario u : lista) {
                cmbAsignado.addItem(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgregarCamion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAgregarCamion = new javax.swing.JLabel();
        lblPatente = new javax.swing.JLabel();
        txtPatente = new javax.swing.JTextField();
        lblMarca = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        lblModelo = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        lblAno = new javax.swing.JLabel();
        txtAno = new javax.swing.JTextField();
        lblKilometraje = new javax.swing.JLabel();
        txtKilometraje = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        cmbAsignado = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAgregarCamion.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        lblAgregarCamion.setText("Agregar Camion");

        lblPatente.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        lblPatente.setText("Patente");

        txtPatente.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        lblMarca.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        lblMarca.setText("Marca");

        txtMarca.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        lblModelo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        lblModelo.setText("Modelo");

        txtModelo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        lblAno.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        lblAno.setText("Año");

        txtAno.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        lblKilometraje.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        lblKilometraje.setText("Kilometraje");

        txtKilometraje.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        btnOk.setBackground(new java.awt.Color(100, 149, 237));
        btnOk.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnOk.setForeground(new java.awt.Color(242, 235, 227));
        btnOk.setText("OK");
        btnOk.setBorderPainted(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        cmbAsignado.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cmbAsignado.setToolTipText("");
        cmbAsignado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAsignadoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel1.setText("Conductor Asignado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblKilometraje)
                    .addComponent(lblAno)
                    .addComponent(lblModelo)
                    .addComponent(lblMarca)
                    .addComponent(lblPatente)
                    .addComponent(txtAno, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(txtModelo)
                    .addComponent(txtMarca)
                    .addComponent(txtPatente)
                    .addComponent(txtKilometraje))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cmbAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(lblAgregarCamion))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(btnOk)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblAgregarCamion)
                        .addGap(163, 163, 163)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblPatente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPatente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(lblMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(lblModelo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(lblAno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(lblKilometraje)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btnOk)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private boolean validarFormulario() {
        String patente = txtPatente.getText().trim().toUpperCase();
        String marca = txtMarca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String anioStr = txtAno.getText().trim();

        if (patente.isEmpty()) {
            mostrarError(txtPatente, "La patente es obligatoria");
            return false;
        }
        if (!patente.matches("^[A-Z0-9-]{4,8}$")) {
            mostrarError(txtPatente, "Patente inválida (solo letras, números y guión, 4-8 caracteres)");
            return false;
        }

        if (marca.length() > 50) {
            mostrarError(txtMarca, "Marca demasiado larga (máx. 50 caracteres)");
            return false;
        }
        if (modelo.length() > 50) {
            mostrarError(txtModelo, "Modelo demasiado largo (máx. 50 caracteres)");
            return false;
        }

        if (anioStr.isEmpty()) {
            mostrarError(txtAno, "El año es obligatorio");
            return false;
        }
        int anio;
        try {
            anio = Integer.parseInt(anioStr);
        } catch (NumberFormatException e) {
            mostrarError(txtAno, "Año debe ser un número entero");
            return false;
        }
        int anioActual = java.time.Year.now().getValue();
        if (anio < 1900 || anio > anioActual + 1) {
            mostrarError(txtAno, "Año fuera de rango (" + 1900 + " - " + (anioActual + 1) + ")");
            return false;
        }

        // Si todo OK, limpiar errores visuales
        limpiarErrores();
        return true;
    }

    private void mostrarError(javax.swing.JComponent campo, String mensaje) {
        campo.setToolTipText(mensaje);
        campo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED));
        campo.requestFocus();
    }

    private void limpiarErrores() {
        javax.swing.border.Border defaultBorder = javax.swing.BorderFactory.createLineBorder(java.awt.Color.GRAY);
        txtPatente.setBorder(defaultBorder);
        txtPatente.setToolTipText(null);
        txtMarca.setBorder(defaultBorder);
        txtMarca.setToolTipText(null);
        txtModelo.setBorder(defaultBorder);
        txtModelo.setToolTipText(null);
        txtAno.setBorder(defaultBorder);
        txtAno.setToolTipText(null);
    }

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        try {
            if (!validarFormulario()) {
                return;
            }

            String patente = txtPatente.getText().trim().toUpperCase();
            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();
            int anio = 0;
            if (!txtAno.getText().trim().isEmpty()) {
                anio = Integer.parseInt(txtAno.getText().trim());
            }
            int kilometraje = 0;
            if (!txtKilometraje.getText().trim().isEmpty()) {
                kilometraje = Integer.parseInt(txtKilometraje.getText().trim());
            }

            Usuario conductorSeleccionado = (Usuario) cmbAsignado.getSelectedItem();
            int idConductor = 0;
            if (conductorSeleccionado != null) {
                idConductor = conductorSeleccionado.getIdUsuario();
            }

            DAOCamion dao = new DAOCamion();
            if (dao.patenteExiste(patente)) {
                JOptionPane.showMessageDialog(this,
                        "La patente ya está registrada en la base de datos.",
                        "Patente duplicada", JOptionPane.WARNING_MESSAGE);
                txtPatente.requestFocus();
                return;
            }

            Camion nuevo = new Camion();
            nuevo.setPatenteCamion(patente);
            nuevo.setMarca(marca.isEmpty() ? null : marca);
            nuevo.setModelo(modelo.isEmpty() ? null : modelo);
            nuevo.setAnio(anio);
            nuevo.setKilometraje(kilometraje);
            nuevo.setIdConductor(idConductor);

            dao.crearCamion(nuevo);

            JOptionPane.showMessageDialog(this, "Camión agregado correctamente. ID: " + nuevo.getIdCamion(),
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Año o kilometraje inválido.", "Validación", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar en la base de datos:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            java.util.logging.Logger.getLogger(AgregarCamion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnOkActionPerformed

    private void cmbAsignadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAsignadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAsignadoActionPerformed

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
            java.util.logging.Logger.getLogger(AgregarCamion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarCamion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarCamion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarCamion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarCamion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox<model.Usuario> cmbAsignado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAgregarCamion;
    private javax.swing.JLabel lblAno;
    private javax.swing.JLabel lblKilometraje;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblPatente;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtKilometraje;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPatente;
    // End of variables declaration//GEN-END:variables
}
