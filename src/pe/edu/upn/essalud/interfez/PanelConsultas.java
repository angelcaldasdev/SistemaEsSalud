
package pe.edu.upn.essalud.interfez;

// Herramientas para organizar los componentes.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

// Componentes de Swing.
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// Clases de nuestro sistema.
import pe.edu.upn.essalud.modelo.Paciente;
import pe.edu.upn.essalud.modelo.Cita;
import pe.edu.upn.essalud.servicio.PacienteServicio;
import pe.edu.upn.essalud.servicio.CitaServicio;

public class PanelConsultas extends JPanel {

    // Servicios encargados de consultar pacientes y citas.
    private final PacienteServicio pacienteServicio;
    private final CitaServicio citaServicio;

    // Campo donde ingresaremos el DNI.
    private JTextField txtDni;

    // Área que mostrará la información del paciente.
    private JTextArea areaInformacion;

    // Tabla que mostrará el historial de citas.
    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;

    // Constructor principal del panel.
    public PanelConsultas() {

        // Inicializamos los servicios.
        pacienteServicio = new PacienteServicio();
        citaServicio = new CitaServicio();

        // Configuramos el diseño general.
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(243, 246, 250));
        setBorder(new EmptyBorder(25, 25, 25, 25));

        // Construimos las secciones de la pantalla.
        crearEncabezado();
        crearContenido();
    }

    // -----------------------------------------------------
    // 1. ENCABEZADO DEL MODULO
    // -----------------------------------------------------
    private void crearEncabezado() {

        JPanel encabezado = new JPanel(new GridLayout(2, 1, 0, 5));
        encabezado.setBackground(new Color(243, 246, 250));

        JLabel titulo = new JLabel("Consultas de pacientes");
        titulo.setFont(new Font("Arial", Font.BOLD, 27));
        titulo.setForeground(new Color(22, 45, 75));

        JLabel descripcion = new JLabel(
                "Busca un paciente y revisa su información e historial de citas."
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

        // Organizamos el buscador arriba y los resultados abajo.
        JPanel contenido = new JPanel(new BorderLayout(15, 15));
        contenido.setBackground(new Color(243, 246, 250));

        contenido.add(crearBuscador(), BorderLayout.NORTH);
        contenido.add(crearResultados(), BorderLayout.CENTER);

        add(contenido, BorderLayout.CENTER);
    }

    // -----------------------------------------------------
    // 3. BUSCADOR POR DNI
    // -----------------------------------------------------
    private JPanel crearBuscador() {

        JPanel buscador = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buscador.setBackground(Color.WHITE);

        buscador.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 238)),
                new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel etiqueta = new JLabel("Ingrese DNI:");
        etiqueta.setFont(new Font("Arial", Font.BOLD, 14));

        txtDni = new JTextField(15);
        txtDni.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDni.setToolTipText("Ingrese el DNI de 8 dígitos");

        JButton btnBuscar = crearBoton(
                "Buscar paciente", new Color(37, 99, 160)
        );

        JButton btnLimpiar = crearBoton(
                "Limpiar", new Color(100, 110, 125)
        );

        // Conectamos los botones con sus respectivos eventos.
        btnBuscar.addActionListener(e -> buscarPaciente());
        btnLimpiar.addActionListener(e -> limpiarConsulta());

        // Agregamos los componentes al buscador.
        buscador.add(etiqueta);
        buscador.add(txtDni);
        buscador.add(btnBuscar);
        buscador.add(btnLimpiar);

        return buscador;
    }

    // -----------------------------------------------------
    // 4. ZONA DE RESULTADOS
    // -----------------------------------------------------
    private JPanel crearResultados() {

        JPanel resultados = new JPanel(new BorderLayout(12, 12));
        resultados.setBackground(new Color(243, 246, 250));

        // Creamos el área de información del paciente.
        areaInformacion = new JTextArea(6, 30);
        areaInformacion.setEditable(false);
        areaInformacion.setLineWrap(true);
        areaInformacion.setWrapStyleWord(true);
        areaInformacion.setFont(new Font("Arial", Font.PLAIN, 14));
        areaInformacion.setBackground(Color.WHITE);
        areaInformacion.setBorder(new EmptyBorder(12, 12, 12, 12));

        JScrollPane scrollInformacion = new JScrollPane(areaInformacion);
        scrollInformacion.setBorder(BorderFactory.createTitledBorder(
                "Información del paciente"
        ));

        scrollInformacion.setPreferredSize(new Dimension(500, 160));

        // Definimos las columnas del historial de citas.
        String[] columnas = {
            "Código", "Especialidad", "Fecha", "Hora", "Estado"
        };

        // Creamos el modelo de la tabla.
        modeloTabla = new DefaultTableModel(columnas, 0) {

            // Evitamos la edición directa de las celdas.
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

        // Personalizamos los encabezados de la tabla.
        tablaCitas.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        tablaCitas.getTableHeader().setBackground(
                new Color(22, 45, 75)
        );

        tablaCitas.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollTabla = new JScrollPane(tablaCitas);
        scrollTabla.setBorder(BorderFactory.createTitledBorder(
                "Historial de citas médicas"
        ));

        // Organizamos la información y el historial.
        resultados.add(scrollInformacion, BorderLayout.NORTH);
        resultados.add(scrollTabla, BorderLayout.CENTER);

        return resultados;
    }

    // -----------------------------------------------------
    // 5. DISEÑO DE BOTONES
    // -----------------------------------------------------
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
    // 6. BUSCAR PACIENTE Y SUS CITAS
    // -----------------------------------------------------
    private void buscarPaciente() {

        // Recuperamos el DNI ingresado.
        String dni = txtDni.getText().trim();

        // Validamos que el campo no esté vacío.
        if (dni.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debes ingresar el DNI del paciente."
            );

            return;
        }

        // Consultamos al servicio de pacientes.
        Paciente paciente = pacienteServicio.buscarPaciente(dni);

        // Verificamos si el paciente existe.
        if (paciente == null) {

            areaInformacion.setText(
                    "No se encontró ningún paciente con ese DNI."
            );

            modeloTabla.setRowCount(0);

            JOptionPane.showMessageDialog(
                    this,
                    "Paciente no encontrado."
            );

            return;
        }

        // Mostramos la información personal del paciente.
        areaInformacion.setText(
                paciente.mostrarInformacion()
        );

        // Limpiamos el historial anterior.
        modeloTabla.setRowCount(0);

        // Consultamos las citas relacionadas con el paciente.
        for (Cita cita : citaServicio.consultarCitasPaciente(dni)) {

            Object[] fila = {
                cita.getCodigo(),
                cita.getEspecialidad(),
                cita.getFecha(),
                cita.getHora(),
                cita.getEstado()
            };

            modeloTabla.addRow(fila);
        }

        // Confirmamos que la búsqueda se realizó.
        JOptionPane.showMessageDialog(
                this,
                "Consulta realizada correctamente."
        );
    }

    // -----------------------------------------------------
    // 7. LIMPIAR LA CONSULTA
    // -----------------------------------------------------
    private void limpiarConsulta() {

        // Borramos el DNI y la información del paciente.
        txtDni.setText("");
        areaInformacion.setText("");

        // Eliminamos los resultados anteriores.
        modeloTabla.setRowCount(0);

        // Devolvemos el cursor al campo DNI.
        txtDni.requestFocus();
    }
}
