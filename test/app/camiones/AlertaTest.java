package app.camiones;

import org.junit.Test;
import static org.junit.Assert.*;

public class AlertaTest {

    @Test
    public void testRequiereMantencion() {
        Alerta instance = new Alerta();

        assertTrue(instance.requiereMantencion(5001));
        assertTrue(instance.requiereMantencion(5000));
        assertFalse(instance.requiereMantencion(4999));
    }

    @Test
    public void testGenerarMensajeAlerta() {
        Alerta instance = new Alerta();

        String patente = "ABC1234";
        int kilometraje = 5001;

        String result = instance.generarMensajeAlerta(patente, kilometraje);

        assertNotNull(result);
        assertTrue(result.contains("MANTENCIÓN"));
        assertTrue(result.contains(patente));
    }
}