/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package app;

import app.camiones.GestionCamiones;
import app.mantenimiento.GestorMantenimiento;
import app.alertas.GestionAlertas;
import app.camiones.MisCamiones;
import app.camiones.SimuladorKilometraje;
import app.camiones.SimuladorTemperatura;
import model.Sesion;
import model.Usuario;

/**
 *
 * @author Franco
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public MenuPrincipal() {
        initComponents();

        if (Sesion.haySesion()) {
            configurarSegunUsuario();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe iniciar sesión");
            this.dispose();
        }
        
        SimuladorKilometraje.iniciar();
        SimuladorTemperatura.iniciar();
        this.setLocationRelativeTo(null);

    }

    private void configurarSegunUsuario() {
        Usuario u = Sesion.getUsuario();

        lblUsuario.setText("Bienvenido " + u.getNombre());

        btnMisCamiones.setVisible(
                u.getRol().equalsIgnoreCase("conductor")
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuario = new javax.swing.JLabel();
        btnGestionUsuarios = new javax.swing.JButton();
        btnGestionCamiones = new javax.swing.JButton();
        btnMantenimiento = new javax.swing.JButton();
        btnAlertas = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnMisCamiones = new javax.swing.JButton();
        lblIconoMenu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblUsuario.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        lblUsuario.setText("BIENVENIDO");

        btnGestionUsuarios.setBackground(new java.awt.Color(100, 149, 237));
        btnGestionUsuarios.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnGestionUsuarios.setForeground(new java.awt.Color(242, 235, 227));
        btnGestionUsuarios.setText("Gestion Usuarios");
        btnGestionUsuarios.setBorderPainted(false);
        btnGestionUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionUsuariosActionPerformed(evt);
            }
        });

        btnGestionCamiones.setBackground(new java.awt.Color(100, 149, 237));
        btnGestionCamiones.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnGestionCamiones.setForeground(new java.awt.Color(242, 235, 227));
        btnGestionCamiones.setText("Gestion Camiones");
        btnGestionCamiones.setBorderPainted(false);
        btnGestionCamiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionCamionesActionPerformed(evt);
            }
        });

        btnMantenimiento.setBackground(new java.awt.Color(100, 149, 237));
        btnMantenimiento.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnMantenimiento.setForeground(new java.awt.Color(242, 235, 227));
        btnMantenimiento.setText("Mantenimiento");
        btnMantenimiento.setBorderPainted(false);
        btnMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantenimientoActionPerformed(evt);
            }
        });

        btnAlertas.setBackground(new java.awt.Color(100, 149, 237));
        btnAlertas.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnAlertas.setForeground(new java.awt.Color(242, 235, 227));
        btnAlertas.setText("Alertas");
        btnAlertas.setBorderPainted(false);
        btnAlertas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlertasActionPerformed(evt);
            }
        });

        btnCerrarSesion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnMisCamiones.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnMisCamiones.setText("Mis Camiones");
        btnMisCamiones.setBorderPainted(false);
        btnMisCamiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisCamionesActionPerformed(evt);
            }
        });

        lblIconoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/‌icono_menu.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCerrarSesion)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblUsuario)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGestionCamiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMantenimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAlertas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGestionUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMisCamiones, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblIconoMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(109, 109, 109))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(lblUsuario)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGestionUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMisCamiones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(btnGestionCamiones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnAlertas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblIconoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(btnCerrarSesion)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantenimientoActionPerformed
        GestorMantenimiento gestorMantenimiento = new GestorMantenimiento();
        gestorMantenimiento.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMantenimientoActionPerformed

    private void btnGestionCamionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionCamionesActionPerformed
        GestionCamiones gestionCamiones = new GestionCamiones();
        gestionCamiones.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGestionCamionesActionPerformed

    private void btnGestionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionUsuariosActionPerformed
        GestionUsuario gestionUsuario = new GestionUsuario();
        gestionUsuario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGestionUsuariosActionPerformed

    private void btnAlertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertasActionPerformed
        GestionAlertas gestionAlertas = new GestionAlertas();
        gestionAlertas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAlertasActionPerformed

    private void btnMisCamionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisCamionesActionPerformed
        MisCamiones misCamiones = new MisCamiones();
        misCamiones.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btnMisCamionesActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        Sesion.cerrarSesion();
        InicioSesion iniciar = new InicioSesion();
        iniciar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlertas;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnGestionCamiones;
    private javax.swing.JButton btnGestionUsuarios;
    private javax.swing.JButton btnMantenimiento;
    private javax.swing.JButton btnMisCamiones;
    private javax.swing.JLabel lblIconoMenu;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
