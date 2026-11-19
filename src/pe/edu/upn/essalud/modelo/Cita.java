
package pe.edu.upn.essalud.modelo;

public class Cita {

    // 1. ATRIBUTOS
    private String codigo;
    private Paciente paciente;
    private String especialidad;
    private String fecha;
    private String hora;
    private String estado;

    // 2. CONSTRUCTOR
    public Cita(String codigo, Paciente paciente,
                String especialidad, String fecha,
                String hora) {

        this.codigo = codigo;
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = "PENDIENTE";
    }

    // 3. GETTERS
    public String getCodigo() {
        return codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    // 4. SETTERS
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // 5. CAMBIAR ESTADO DE LA CITA
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    // 6. MOSTRAR INFORMACION
    public String mostrarInformacion() {

        return "Codigo: " + codigo
                + "\nPaciente: " + paciente.getNombre()
                + " " + paciente.getApellido()
                + "\nEspecialidad: " + especialidad
                + "\nFecha: " + fecha
                + "\nHora: " + hora
                + "\nEstado: " + estado;
    }
}
