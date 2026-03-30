package app.camiones;

public class Alerta {

    private static final int LIMITE_KM = 5000;

    public boolean requiereMantencion(int kilometraje) {
        return kilometraje >= LIMITE_KM;
    }

    public String generarMensajeAlerta(String patente, int kilometraje) {
        if (requiereMantencion(kilometraje)) {
            return "⚠️ ¡MANTENCIÓN REQUERIDA! ⚠️\n\n"
                    + "El camión con patente [" + patente + "] ha superado el límite.\n"
                    + "Kilometraje actual: " + kilometraje + " km.\n\n"
                    + "Por favor, revisa la sección de Alertas para más detalles.";
        }
        return null;
    }
}