
package pe.edu.upn.essalud.servicio;

import java.util.List;
import pe.edu.upn.essalud.modelo.Paciente;
import pe.edu.upn.essalud.modelo.Cita;
import pe.edu.upn.essalud.repositorio.Repositorio;

public class PacienteServicio {

    // 1. CONEXION CON EL REPOSITORIO
    private final Repositorio repositorio;

    // 2. CONSTRUCTOR
    public PacienteServicio() {
        this.repositorio = Repositorio.getInstancia();
    }

    // 3. REGISTRAR PACIENTE
    public boolean registrarPaciente(Paciente paciente) {

        if (paciente == null) {
            return false;
        }

        if (paciente.getCodigo() == null
                || paciente.getCodigo().isBlank()
                || paciente.getNombre() == null
                || paciente.getNombre().isBlank()
                || paciente.getApellido() == null
                || paciente.getApellido().isBlank()
                || paciente.getDni() == null
                || !paciente.getDni().matches("\\d{8}")
                || paciente.getTelefono() == null
                || !paciente.getTelefono().matches("\\d{9}")) {

            return false;
        }

        return repositorio.agregarPaciente(paciente);
    }

    // 4. LISTAR PACIENTES
    public List<Paciente> listarPacientes() {
        return repositorio.listarPacientes();
    }

    // 5. BUSCAR PACIENTE
    public Paciente buscarPaciente(String dni) {

        if (dni == null || !dni.matches("\\d{8}")) {
            return null;
        }

        return repositorio.buscarPacientePorDni(dni);
    }

    // 6. ACTUALIZAR PACIENTE
    public boolean actualizarPaciente(
            String dni,
            String nuevoNombre,
            String nuevoApellido,
            String nuevoTelefono) {

        Paciente paciente = buscarPaciente(dni);

        if (paciente == null
                || nuevoNombre == null
                || nuevoNombre.isBlank()
                || nuevoApellido == null
                || nuevoApellido.isBlank()
                || nuevoTelefono == null
                || !nuevoTelefono.matches("\\d{9}")) {

            return false;
        }

        paciente.setNombre(nuevoNombre);
        paciente.setApellido(nuevoApellido);
        paciente.setTelefono(nuevoTelefono);

        return true;
    }

    // 7. ELIMINAR PACIENTE
    public boolean eliminarPaciente(String dni) {

        Paciente paciente = buscarPaciente(dni);

        if (paciente == null) {
            return false;
        }

        // Comprobar si tiene citas registradas
        for (Cita cita : repositorio.listarCitas()) {

            if (cita.getPaciente() != null
                    && cita.getPaciente().getDni().equals(dni)) {

                return false;
            }
        }

        return repositorio.eliminarPaciente(dni);
    }
}
