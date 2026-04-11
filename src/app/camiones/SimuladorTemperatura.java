/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.camiones;

import bd.DAOCamion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import model.Camion;

/**
 *
 * @author bevod
 */
public class SimuladorTemperatura {

    private static Timer timer;

    public static void iniciar() {
        if (timer != null) {
            return;
        }

        timer = new Timer(5000, e -> {
            actualizarTemperaturas();
        });

        timer.start();
    }

    private static void actualizarTemperaturas() {
        try {
            DAOCamion dao = new DAOCamion();
            ArrayList<Camion> camiones = dao.getListaCamiones();

            for (Camion c : camiones) {
                double actual = c.getTemperatura();
                double temp = generarTemperatura(actual, c.getKilometraje());
                c.setTemperatura(temp);

                if (temp > 90) {
                    System.out.println("⚠️ Sobrecalentamiento: " + c.getPatenteCamion());
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(SimuladorTemperatura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static double generarTemperatura(double actual, int kilometraje) {
        
        double cambio = (Math.random() * 2) - 1; 

        
        double probabilidad = 0.01; // 1%

        // aumentar probabilidad segun kilometraje
        if (kilometraje > 3000) {
            probabilidad = 0.03;
        }
        if (kilometraje > 5000) {
            probabilidad = 0.07;
        }
        if (kilometraje > 8000) {
            probabilidad = 0.12;
        }

        
        if (Math.random() < probabilidad) {
            cambio += 10 + Math.random() * 10; 
        }

        double nueva = actual + cambio;

        
        if (nueva < 60) {
            nueva = 60;
        }
        if (nueva > 110) {
            nueva = 110;
        }

        return nueva;
    }
    
    

}
