
package pe.edu.upn.essalud.interfez;

// Importamos las herramientas para organizar los componentes.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;

// Importamos los componentes de Swing.
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// Importamos las clases de nuestro sistema.
import pe.edu.upn.essalud.modelo.Paciente;
import pe.edu.upn.essalud.servicio.PacienteServicio;

public class PanelPacientes extends JPanel {

    // Servicio encargado de gestionar las operaciones de pacientes.
    private final PacienteServicio servicio;

    // Campos de texto para registrar y consultar información.
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDni;
    private JTextField txtTelefono;

    // Componentes para mostrar los pacientes registrados.
    private JTable tablaPacientes;
    private DefaultTableModel modeloTabla;

    // Constructor principal del panel.
    public PanelPacientes() {

        // Creamos la conexión con la capa de servicios.
        servicio = new PacienteServicio();

        // Configuramos el diseño general de la pantalla.
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(243, 246, 250));
        setBorder(new EmptyBorder(25, 25, 25, 25));

        // Construimos la interfaz.
        crearEncabezado();
        crearContenido();

        // Mostramos los pacientes registrados.
        actualizarTabla();
    }

    // ---------------------------------------------------------
    // 1. ENCABEZADO DEL MODULO
    // ---------------------------------------------------------
    private void crearEncabezado() {

        JPanel encabezado = new JPanel(new GridLayout(2, 1, 0, 5));
        encabezado.setBackground(new Color(243, 246, 250));

        JLabel titulo = new JLabel("Gestión de pacientes");
        titulo.setFont(new Font("Arial", Font.BOLD, 27));
        titulo.setForeground(new Color(22, 45, 75));

        JLabel descripcion = new JLabel(
                "Registra, consulta, actualiza y elimina pacientes."
        );
        descripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        descripcion.setForeground(Color.GRAY);

        encabezado.add(titulo);
        encabezado.add(descripcion);

        add(encabezado, BorderLayout.NORTH);
    }

    // ---------------------------------------------------------
    // 2. ORGANIZACION DEL CONTENIDO
    // ---------------------------------------------------------
    private void crearContenido() {

        // Este panel permite colocar el formulario arriba
        // y la tabla abajo, distribuyendo mejor el espacio.
        JPanel contenido = new JPanel(new BorderLayout(15, 15));
        contenido.setBackground(new Color(243, 246, 250));

        contenido.add(crearFormulario(), BorderLayout.NORTH);
        contenido.add(crearTabla(), BorderLayout.CENTER);

        add(contenido, BorderLayout.CENTER);
    }

    // ---------------------------------------------------------
    // 3. FORMULARIO DE PACIENTES
    // ---------------------------------------------------------
    private JPanel crearFormulario() {

        JPanel formulario = new JPanel(new BorderLayout(10, 12));
        formulario.setBackground(Color.WHITE);
        formulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 238)),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JLabel tituloFormulario = new JLabel("Datos del paciente");
        tituloFormulario.setFont(new Font("Arial", Font.BOLD, 18));
        tituloFormulario.setForeground(new Color(22, 45, 75));

        // Inicializamos los campos de texto.
        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtDni = new JTextField();
        txtTelefono = new JTextField();

        // Organizamos los campos en una cuadrícula.
        JPanel campos = new JPanel(new GridLayout(2, 5, 10, 8));
        campos.setBackground(Color.WHITE);

        campos.add(crearCampo("Código", txtCodigo));
        campos.add(crearCampo("Nombres", txtNombre));
        campos.add(crearCampo("Apellidos", txtApellido));
        campos.add(crearCampo("DNI", txtDni));
        campos.add(crearCampo("Teléfono", txtTelefono));

        // Creamos los botones de las operaciones.
        JButton btnRegistrar = crearBoton(
                "Registrar", new Color(37, 99, 160)
        );

        JButton btnBuscar = crearBoton(
                "Buscar DNI", new Color(75, 85, 99)
        );

        JButton btnActualizar = crearBoton(
                "Actualizar", new Color(22, 135, 100)
        );

        JButton btnEliminar = crearBoton(
                "Eliminar", new Color(190, 55, 55)
        );

        JButton btnLimpiar = crearBoton(
                "Limpiar", new Color(100, 110, 125)
        );

        // Organizamos los botones horizontalmente.
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 3));
        botones.setBackground(Color.WHITE);

        botones.add(btnRegistrar);
        botones.add(btnBuscar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

        // Asociamos cada botón con su respectivo evento.
        btnRegistrar.addActionListener(e -> registrarPaciente());
        btnBuscar.addActionListener(e -> buscarPaciente());
        btnActualizar.addActionListener(e -> actualizarPaciente());
        btnEliminar.addActionListener(e -> eliminarPaciente());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // Organizamos las secciones internas del formulario.
        JPanel parteCentral = new JPanel(new BorderLayout(10, 10));
        parteCentral.setBackground(Color.WHITE);

        parteCentral.add(tituloFormulario, BorderLayout.NORTH);
        parteCentral.add(campos, BorderLayout.CENTER);
        parteCentral.add(botones, BorderLayout.SOUTH);

        formulario.add(parteCentral, BorderLayout.CENTER);

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

    // ---------------------------------------------------------
    // 4. TABLA DE PACIENTES
    // ---------------------------------------------------------
    private JScrollPane crearTabla() {

        String[] columnas = {
            "Código", "Nombres", "Apellidos", "DNI", "Teléfono"
        };

        // El modelo administra los datos de la tabla.
        modeloTabla = new DefaultTableModel(columnas, 0) {

            // Evitamos que se modifiquen directamente las celdas.
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaPacientes = new JTable(modeloTabla);
        tablaPacientes.setRowHeight(30);
        tablaPacientes.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaPacientes.setSelectionBackground(new Color(219, 234, 254));
        tablaPacientes.setSelectionForeground(Color.BLACK);

        // Personalizamos los encabezados de las columnas.
        tablaPacientes.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        tablaPacientes.getTableHeader().setBackground(
                new Color(22, 45, 75)
        );

        tablaPacientes.getTableHeader().setForeground(Color.WHITE);

        // Al seleccionar una fila, cargamos sus datos
        // automáticamente en los campos del formulario.
        tablaPacientes.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int fila = tablaPacientes.getSelectedRow();

                if (fila != -1) {

                    txtCodigo.setText(modeloTabla.getValueAt(fila, 0).toString());
                    txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                    txtApellido.setText(modeloTabla.getValueAt(fila, 2).toString());
                    txtDni.setText(modeloTabla.getValueAt(fila, 3).toString());
                    txtTelefono.setText(modeloTabla.getValueAt(fila, 4).toString());
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tablaPacientes);
        scroll.setBorder(BorderFactory.createTitledBorder(
                "Listado de pacientes registrados"
        ));

        scroll.setPreferredSize(new Dimension(500, 250));

        return scroll;
    }

    // ---------------------------------------------------------
    // 5. REGISTRAR PACIENTE
    // ---------------------------------------------------------
    private void registrarPaciente() {

        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDni.getText().trim();
        String telefono = txtTelefono.getText().trim();

        // Construimos el objeto con los datos ingresados.
        Paciente paciente = new Paciente(
                codigo, nombre, apellido, dni, telefono
        );

        // El servicio valida los datos antes de registrarlos.
        if (servicio.registrarPaciente(paciente)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Paciente registrado correctamente."
            );

            actualizarTabla();
            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar el paciente.\n"
                    + "Verifica los campos, el DNI de 8 dígitos "
                    + "y el teléfono de 9 dígitos."
            );
        }
    }

    // ---------------------------------------------------------
    // 6. BUSCAR PACIENTE POR DNI
    // ---------------------------------------------------------
    private void buscarPaciente() {

        String dni = txtDni.getText().trim();

        Paciente paciente = servicio.buscarPaciente(dni);

        if (paciente != null) {

            txtCodigo.setText(paciente.getCodigo());
            txtNombre.setText(paciente.getNombre());
            txtApellido.setText(paciente.getApellido());
            txtTelefono.setText(paciente.getTelefono());

            JOptionPane.showMessageDialog(
                    this,
                    "Paciente encontrado correctamente."
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontró un paciente con ese DNI."
            );
        }
    }

    // ---------------------------------------------------------
    // 7. ACTUALIZAR PACIENTE
    // ---------------------------------------------------------
    private void actualizarPaciente() {

        String dni = txtDni.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (servicio.actualizarPaciente(
                dni, nombre, apellido, telefono)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Paciente actualizado correctamente."
            );

            actualizarTabla();
            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo actualizar el paciente.\n"
                    + "Verifica el DNI y los datos ingresados."
            );
        }
    }

    // ---------------------------------------------------------
    // 8. ELIMINAR PACIENTE
    // ---------------------------------------------------------
    private void eliminarPaciente() {

        String dni = txtDni.getText().trim();

        // Solicitamos confirmación antes de eliminar.
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de eliminar este paciente?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            if (servicio.eliminarPaciente(dni)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Paciente eliminado correctamente."
                );

                actualizarTabla();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo eliminar el paciente.\n"
                        + "Puede que no exista o tenga citas registradas."
                );
            }
        }
    }

    // ---------------------------------------------------------
    // 9. LIMPIAR FORMULARIO
    // ---------------------------------------------------------
    private void limpiarCampos() {

        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDni.setText("");
        txtTelefono.setText("");

        tablaPacientes.clearSelection();
        txtCodigo.requestFocus();
    }

    // ---------------------------------------------------------
    // 10. ACTUALIZAR TABLA
    // ---------------------------------------------------------
    private void actualizarTabla() {

        // Limpiamos las filas anteriores.
        modeloTabla.setRowCount(0);

        // Recuperamos los pacientes desde la capa de servicios.
        for (Paciente paciente : servicio.listarPacientes()) {

            Object[] fila = {
                paciente.getCodigo(),
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getDni(),
                paciente.getTelefono()
            };

            modeloTabla.addRow(fila);
        }
    }
}
