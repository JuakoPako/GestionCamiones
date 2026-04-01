/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.camiones;

import bd.DAOCamion;
import java.util.ArrayList;
import javax.swing.Timer;
import model.Camion;

/**
 *
 * @author bevod
 */
public class SimuladorKilometraje {

    private static Timer timer;

    public static void iniciar() {
        if (timer != null) {
            return; 
        }
        timer = new Timer(5000, e -> { 
            try {
                DAOCamion dao = new DAOCamion();
                ArrayList<Camion> camiones = dao.getListaCamiones();

                for (Camion c : camiones) {

                    int km = (int) (Math.random() * 3); 

                    if (km > 0) {
                        dao.sumarKilometraje(c.getIdCamion(), km);
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        timer.start();
    }

}
