
package pe.edu.upn.essalud.modelo;

public class Paciente {

    // 1. ATRIBUTOS
    private String codigo;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;

    // 2. CONSTRUCTOR
    public Paciente(String codigo, String nombre,
                    String apellido, String dni,
                    String telefono) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
    }

    // 3. GETTERS
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    // 4. SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // 5. METODO PARA MOSTRAR INFORMACION
    public String mostrarInformacion() {
        return "Codigo: " + codigo
                + "\nNombre: " + nombre
                + "\nApellido: " + apellido
                + "\nDNI: " + dni
                + "\nTelefono: " + telefono;
    }
}
