
package pe.edu.upn.essalud.interfaz;

// Herramientas para organizar los componentes.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;

// Componentes de Swing.
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// Clases de nuestro sistema.
import pe.edu.upn.essalud.modelo.Cita;
import pe.edu.upn.essalud.modelo.Paciente;
import pe.edu.upn.essalud.servicio.CitaServicio;
import pe.edu.upn.essalud.servicio.PacienteServicio;

public class PanelCitas extends JPanel {

    // Servicios encargados de gestionar las citas y los pacientes.
    private final CitaServicio citaServicio;
    private final PacienteServicio pacienteServicio;

    // Campos del formulario.
    private JTextField txtCodigo;
    private JTextField txtDni;
    private JTextField txtEspecialidad;
    private JTextField txtFecha;
    private JTextField txtHora;

    // Tabla que muestra las citas registradas.
    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;

    // Constructor principal del panel.
    public PanelCitas() {

        // Inicializamos los servicios.
        citaServicio = new CitaServicio();
        pacienteServicio = new PacienteServicio();

        // Configuramos el diseño general.
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(243, 246, 250));
        setBorder(new EmptyBorder(25, 25, 25, 25));

        // Construimos las secciones de la interfaz.
        crearEncabezado();
        crearContenido();

        // Cargamos las citas registradas.
        actualizarTabla();
    }

    // -----------------------------------------------------
    // 1. ENCABEZADO DEL MODULO
    // -----------------------------------------------------
    private void crearEncabezado() {

        JPanel encabezado = new JPanel(new GridLayout(2, 1, 0, 5));
        encabezado.setBackground(new Color(243, 246, 250));

        JLabel titulo = new JLabel("Gestión de citas médicas");
        titulo.setFont(new Font("Arial", Font.BOLD, 27));
        titulo.setForeground(new Color(22, 45, 75));

        JLabel descripcion = new JLabel(
                "Programa, consulta y cancela citas médicas."
        );
        descripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        descripcion.setForeground(Color.GRAY);

        encabezado.add(titulo);
        encabezado.add(descripcion);

        add(encabezado, BorderLayout.NORTH);
    }

    // -----------------------------------------------------
    // 2. ORGANIZACION DEL CONTENIDO
    // -----------------------------------------------------
    private void crearContenido() {

        // Distribuimos el formulario arriba y la tabla abajo.
        JPanel contenido = new JPanel(new BorderLayout(15, 15));
        contenido.setBackground(new Color(243, 246, 250));

        contenido.add(crearFormulario(), BorderLayout.NORTH);
        contenido.add(crearTabla(), BorderLayout.CENTER);

        add(contenido, BorderLayout.CENTER);
    }

    // -----------------------------------------------------
    // 3. FORMULARIO DE REGISTRO DE CITAS
    // -----------------------------------------------------
    private JPanel crearFormulario() {

        JPanel formulario = new JPanel(new BorderLayout(10, 12));
        formulario.setBackground(Color.WHITE);
        formulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 238)),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JLabel titulo = new JLabel("Datos de la cita");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(22, 45, 75));

        // Inicializamos los campos de entrada.
        txtCodigo = new JTextField();
        txtDni = new JTextField();
        txtEspecialidad = new JTextField();
        txtFecha = new JTextField();
        txtHora = new JTextField();

        // Mostramos los formatos esperados como ayuda.
        txtFecha.setToolTipText("Formato: AAAA-MM-DD");
        txtHora.setToolTipText("Formato: HH:mm");
        txtDni.setToolTipText("Ingrese el DNI de 8 dígitos");

        // Organizamos los campos en una cuadrícula.
        JPanel campos = new JPanel(new GridLayout(2, 3, 10, 10));
        campos.setBackground(Color.WHITE);

        campos.add(crearCampo("Código de cita", txtCodigo));
        campos.add(crearCampo("DNI del paciente", txtDni));
        campos.add(crearCampo("Especialidad", txtEspecialidad));
        campos.add(crearCampo("Fecha (AAAA-MM-DD)", txtFecha));
        campos.add(crearCampo("Hora (HH:mm)", txtHora));

        // Creamos los botones de las operaciones.
        JButton btnRegistrar = crearBoton(
                "Registrar cita", new Color(37, 99, 160)
        );

        JButton btnConsultar = crearBoton(
                "Consultar citas", new Color(75, 85, 99)
        );

        JButton btnCancelar = crearBoton(
                "Cancelar cita", new Color(190, 55, 55)
        );

        JButton btnLimpiar = crearBoton(
                "Limpiar", new Color(100, 110, 125)
        );

        // Organizamos los botones horizontalmente.
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 3));
        botones.setBackground(Color.WHITE);

        botones.add(btnRegistrar);
        botones.add(btnConsultar);
        botones.add(btnCancelar);
        botones.add(btnLimpiar);

        // Asociamos cada botón con su evento.
        btnRegistrar.addActionListener(e -> registrarCita());
        btnConsultar.addActionListener(e -> consultarCitas());
        btnCancelar.addActionListener(e -> cancelarCita());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // Organizamos las secciones internas del formulario.
        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.setBackground(Color.WHITE);

        centro.add(titulo, BorderLayout.NORTH);
        centro.add(campos, BorderLayout.CENTER);
        centro.add(botones, BorderLayout.SOUTH);

        formulario.add(centro, BorderLayout.CENTER);

        return formulario;
    }

    // Crea un campo de texto acompañado de su etiqueta.
    private JPanel crearCampo(String nombre, JTextField campo) {

        JPanel panel = new JPanel(new BorderLayout(3, 3));
        panel.setBackground(Color.WHITE);

        JLabel etiqueta = new JLabel(nombre);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 12));

        campo.setFont(new Font("Arial", Font.PLAIN, 13));

        panel.add(etiqueta, BorderLayout.NORTH);
        panel.add(campo, BorderLayout.CENTER);

        return panel;
    }

    // Aplica un diseño uniforme a los botones.
    private JButton crearBoton(String texto, Color color) {

        JButton boton = new JButton(texto);

        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new java.awt.Cursor(
                java.awt.Cursor.HAND_CURSOR
        ));

        return boton;
    }

    // -----------------------------------------------------
    // 4. TABLA DE CITAS MEDICAS
    // -----------------------------------------------------
    private JScrollPane crearTabla() {

        String[] columnas = {
            "Código", "DNI", "Paciente", "Especialidad",
            "Fecha", "Hora", "Estado"
        };

        // Creamos el modelo de datos de la tabla.
        modeloTabla = new DefaultTableModel(columnas, 0) {

            // Impedimos que el usuario edite directamente las celdas.
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaCitas = new JTable(modeloTabla);
        tablaCitas.setRowHeight(30);
        tablaCitas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaCitas.setSelectionBackground(new Color(219, 234, 254));
        tablaCitas.setSelectionForeground(Color.BLACK);

        // Personalizamos los encabezados.
        tablaCitas.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        tablaCitas.getTableHeader().setBackground(
                new Color(22, 45, 75)
        );

        tablaCitas.getTableHeader().setForeground(Color.WHITE);

        // Al seleccionar una fila, cargamos sus datos
        // automáticamente en el formulario.
        tablaCitas.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int fila = tablaCitas.getSelectedRow();

                if (fila != -1) {

                    txtCodigo.setText(
                            modeloTabla.getValueAt(fila, 0).toString()
                    );

                    txtDni.setText(
                            modeloTabla.getValueAt(fila, 1).toString()
                    );

                    txtEspecialidad.setText(
                            modeloTabla.getValueAt(fila, 3).toString()
                    );

                    txtFecha.setText(
                            modeloTabla.getValueAt(fila, 4).toString()
                    );

                    txtHora.setText(
                            modeloTabla.getValueAt(fila, 5).toString()
                    );
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tablaCitas);

        scroll.setBorder(BorderFactory.createTitledBorder(
                "Listado de citas médicas"
        ));

        scroll.setPreferredSize(new Dimension(500, 250));

        return scroll;
    }

    // -----------------------------------------------------
    // 5. REGISTRAR UNA CITA
    // -----------------------------------------------------
    private void registrarCita() {

        // Recuperamos los datos ingresados.
        String codigo = txtCodigo.getText().trim();
        String dni = txtDni.getText().trim();
        String especialidad = txtEspecialidad.getText().trim();
        String fecha = txtFecha.getText().trim();
        String hora = txtHora.getText().trim();

        // Buscamos al paciente mediante su DNI.
        Paciente paciente = pacienteServicio.buscarPaciente(dni);

        // Verificamos que el paciente exista.
        if (paciente == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "El paciente no existe.\n"
                    + "Primero debes registrarlo en el módulo de pacientes."
            );

            return;
        }

        // Creamos el objeto Cita.
        Cita cita = new Cita(
                codigo,
                paciente,
                especialidad,
                fecha,
                hora
        );

        // Enviamos la cita al servicio para su validación.
        if (citaServicio.registrarCita(cita)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cita registrada correctamente."
            );

            actualizarTabla();
            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar la cita.\n"
                    + "Verifica el código, la especialidad, "
                    + "la fecha y el horario disponible."
            );
        }
    }

    // -----------------------------------------------------
    // 6. CONSULTAR CITAS
    // -----------------------------------------------------
    private void consultarCitas() {

        // Actualizamos la información de la tabla.
        actualizarTabla();

        JOptionPane.showMessageDialog(
                this,
                "Listado de citas actualizado correctamente."
        );
    }

    // -----------------------------------------------------
    // 7. CANCELAR UNA CITA
    // -----------------------------------------------------
    private void cancelarCita() {

        String codigo = txtCodigo.getText().trim();

        // Comprobamos que se haya ingresado un código.
        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona una cita o escribe su código."
            );

            return;
        }

        // Solicitamos confirmación antes de cancelar.
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de cancelar esta cita?",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            // El servicio cambia el estado de la cita.
            if (citaServicio.cancelarCita(codigo)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cita cancelada correctamente."
                );

                actualizarTabla();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró la cita o ya está cancelada."
                );
            }
        }
    }

    // -----------------------------------------------------
    // 8. LIMPIAR EL FORMULARIO
    // -----------------------------------------------------
    private void limpiarCampos() {

        txtCodigo.setText("");
        txtDni.setText("");
        txtEspecialidad.setText("");
        txtFecha.setText("");
        txtHora.setText("");

        tablaCitas.clearSelection();
        txtCodigo.requestFocus();
    }

    // -----------------------------------------------------
    // 9. ACTUALIZAR LA TABLA
    // -----------------------------------------------------
    private void actualizarTabla() {

        // Eliminamos las filas anteriores.
        modeloTabla.setRowCount(0);

        // Recuperamos todas las citas del servicio.
        for (Cita cita : citaServicio.listarCitas()) {

            // Obtenemos el paciente asociado a cada cita.
            Paciente paciente = cita.getPaciente();

            // Preparamos la información que aparecerá en la tabla.
            Object[] fila = {
                cita.getCodigo(),
                paciente.getDni(),
                paciente.getNombre() + " " + paciente.getApellido(),
                cita.getEspecialidad(),
                cita.getFecha(),
                cita.getHora(),
                cita.getEstado()
            };

            // Incorporamos la cita como una nueva fila.
            modeloTabla.addRow(fila);
        }
    }
}
