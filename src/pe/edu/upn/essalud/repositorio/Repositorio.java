
package pe.edu.upn.essalud.repositorio;

import java.util.ArrayList;
import java.util.List;
import pe.edu.upn.essalud.modelo.Paciente;
import pe.edu.upn.essalud.modelo.Cita;

public class Repositorio {

    // 1. PATRON SINGLETON
    private static final Repositorio INSTANCIA =
            new Repositorio();

    // 2. COLECCIONES
    private final List<Paciente> pacientes =
            new ArrayList<>();

    private final List<Cita> citas =
            new ArrayList<>();

    // 3. CONSTRUCTOR PRIVADO
    private Repositorio() {
    }

    // 4. OBTENER INSTANCIA UNICA
    public static Repositorio getInstancia() {
        return INSTANCIA;
    }

    // 5. REGISTRAR PACIENTE
    public boolean agregarPaciente(Paciente paciente) {

        if (paciente == null
                || buscarPacientePorDni(paciente.getDni()) != null) {
            return false;
        }

        pacientes.add(paciente);
        return true;
    }

    // 6. LISTAR PACIENTES
    public List<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes);
    }

    // 7. BUSCAR PACIENTE POR DNI
    public Paciente buscarPacientePorDni(String dni) {

        for (Paciente paciente : pacientes) {
            if (paciente.getDni().equals(dni)) {
                return paciente;
            }
        }

        return null;
    }

    // 8. ELIMINAR PACIENTE
    public boolean eliminarPaciente(String dni) {

        return pacientes.removeIf(
                paciente -> paciente.getDni().equals(dni)
        );
    }

    // 9. REGISTRAR CITA
    public boolean agregarCita(Cita cita) {

        if (cita == null) {
            return false;
        }

        citas.add(cita);
        return true;
    }

    // 10. LISTAR CITAS
    public List<Cita> listarCitas() {
        return new ArrayList<>(citas);
    }

    // 11. ELIMINAR CITA
    public boolean eliminarCita(String codigo) {

        return citas.removeIf(
                cita -> cita.getCodigo().equals(codigo)
        );
    }
}
