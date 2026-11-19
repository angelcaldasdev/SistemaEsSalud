
package pe.edu.upn.essalud.servicio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import pe.edu.upn.essalud.modelo.Cita;
import pe.edu.upn.essalud.modelo.Paciente;
import pe.edu.upn.essalud.repositorio.Repositorio;

public class CitaServicio {

    private final Repositorio repositorio;

    // CONSTRUCTOR
    public CitaServicio() {
        this.repositorio = Repositorio.getInstancia();
    }

    // 1. REGISTRAR CITA
    public boolean registrarCita(Cita cita) {

        if (cita == null || cita.getPaciente() == null) {
            return false;
        }

        if (cita.getCodigo() == null
                || cita.getCodigo().isBlank()
                || cita.getEspecialidad() == null
                || cita.getEspecialidad().isBlank()) {
            return false;
        }

        // Comprobar que el paciente este registrado
        Paciente paciente = repositorio.buscarPacientePorDni(
                cita.getPaciente().getDni()
        );

        if (paciente == null) {
            return false;
        }

        // Validar fecha y hora
        try {
            LocalDate fecha = LocalDate.parse(cita.getFecha());
            LocalTime hora = LocalTime.parse(cita.getHora());

            if (fecha.isBefore(LocalDate.now())) {
                return false;
            }

            // Horario de atencion: 08:00 a 17:00
            if (hora.isBefore(LocalTime.of(8, 0))
                    || !hora.isBefore(LocalTime.of(17, 0))) {
                return false;
            }

        } catch (DateTimeParseException e) {
            return false;
        }

        // Evitar citas duplicadas en el mismo horario
        for (Cita existente : repositorio.listarCitas()) {

            if (existente.getCodigo().equals(cita.getCodigo())) {
                return false;
            }

            if (existente.getFecha().equals(cita.getFecha())
                    && existente.getHora().equals(cita.getHora())
                    && !"CANCELADA".equals(existente.getEstado())) {
                return false;
            }
        }

        return repositorio.agregarCita(cita);
    }

    // 2. LISTAR CITAS
    public List<Cita> listarCitas() {
        return repositorio.listarCitas();
    }

    // 3. CONSULTAR CITAS DE UN PACIENTE
    public List<Cita> consultarCitasPaciente(String dni) {

        return repositorio.listarCitas()
                .stream()
                .filter(cita ->
                        cita.getPaciente().getDni().equals(dni))
                .toList();
    }

    // 4. CANCELAR CITA
    public boolean cancelarCita(String codigo) {

        for (Cita cita : repositorio.listarCitas()) {

            if (cita.getCodigo().equals(codigo)
                    && !"CANCELADA".equals(cita.getEstado())) {

                cita.cambiarEstado("CANCELADA");
                return true;
            }
        }

        return false;
    }
}
