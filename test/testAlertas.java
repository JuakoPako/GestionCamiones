
import app.camiones.Alerta;
import bd.DAOAlertas;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bevod
 */
public class testAlertas {

    @Test
    public void testRequiereMantencion() {
        Alerta alerta = new Alerta();

        
        assertTrue(alerta.requiereMantencion(5000));
        assertTrue(alerta.requiereMantencion(6000));

        
        assertFalse(alerta.requiereMantencion(4999));
    }

    @Test
    public void testGenerarMensajeAlerta_CuandoSuperaLimite() {
        Alerta alerta = new Alerta();

        String patente = "ABC1234";
        int kilometraje = 5500;

        String mensaje = alerta.generarMensajeAlerta(patente, kilometraje);

        assertNotNull(mensaje);
        assertTrue(mensaje.contains("MANTENCIÓN"));
        assertTrue(mensaje.contains(patente));
        assertTrue(mensaje.contains("5500"));
    }

    @Test
    public void testGenerarMensajeAlerta_CuandoNoSuperaLimite() {
        Alerta alerta = new Alerta();

        String mensaje = alerta.generarMensajeAlerta("ABC1234", 3000);

        assertNull(mensaje);
    }
    
}
