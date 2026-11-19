
package pe.edu.upn.essalud.util;

// Herramientas para validar fechas y horas.
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Validador {

    // Constructor privado: no necesitamos crear objetos
    // porque utilizaremos métodos estáticos.
    private Validador() {
    }

    // Comprueba que el DNI tenga exactamente 8 dígitos.
    public static boolean validarDni(String dni) {
        return dni != null && dni.matches("\\d{8}");
    }

    // Comprueba que el teléfono tenga exactamente 9 dígitos.
    public static boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("\\d{9}");
    }

    // Comprueba que un texto no sea nulo ni esté vacío.
    public static boolean validarTexto(String texto) {
        return texto != null && !texto.isBlank();
    }

    // Comprueba que la fecha tenga formato AAAA-MM-DD.
    public static boolean validarFecha(String fecha) {
        try {
            LocalDate.parse(fecha);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }

    // Comprueba que la hora tenga formato HH:mm.
    public static boolean validarHora(String hora) {
        try {
            LocalTime.parse(hora);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }
}
