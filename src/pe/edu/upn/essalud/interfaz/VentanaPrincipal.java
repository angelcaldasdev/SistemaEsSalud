
package pe.edu.upn.essalud.interfaz;

// Importaciones para el diseño visual.
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Importaciones de los servicios del sistema.
import pe.edu.upn.essalud.servicio.PacienteServicio;
import pe.edu.upn.essalud.servicio.CitaServicio;
import pe.edu.upn.essalud.modelo.Cita;

public class VentanaPrincipal extends JFrame {

    // ---------------------------------------------
    // 1. COLORES GENERALES DEL SISTEMA
    // ---------------------------------------------
    private final Color AZUL_OSCURO = new Color(22, 45, 75);
    private final Color AZUL = new Color(37, 99, 160);
    private final Color FONDO = new Color(243, 246, 250);
    private final Color BLANCO = Color.WHITE;
    private final Color VERDE = new Color(22, 135, 100);
    private final Color NARANJA = new Color(220, 135, 35);

    // ---------------------------------------------
    // 2. COMPONENTES PRINCIPALES
    // ---------------------------------------------
    private CardLayout cardLayout;
    private JPanel panelContenido;

    // Servicios para consultar la informacion.
    private final PacienteServicio pacienteServicio =
            new PacienteServicio();

    private final CitaServicio citaServicio =
            new CitaServicio();

    // Etiquetas para mostrar estadisticas.
    private JLabel lblTotalPacientes;
    private JLabel lblTotalCitas;
    private JLabel lblCitasPendientes;

    // ---------------------------------------------
    // 3. CONSTRUCTOR DE LA VENTANA
    // ---------------------------------------------
    public VentanaPrincipal() {

        setTitle("Sistema de Gestion de EsSalud");

        setSize(1150, 720);
        setMinimumSize(new Dimension(950, 620));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Construimos el menu y el contenido.
        crearMenuLateral();
        crearPanelContenido();

        // Mostramos el panel inicial.
        mostrarPanel("inicio");
    }

    // ---------------------------------------------
    // 4. CREAR MENU LATERAL
    // ---------------------------------------------
    private void crearMenuLateral() {

        JPanel menu = new JPanel();

        menu.setBackground(AZUL_OSCURO);
        menu.setPreferredSize(new Dimension(235, 700));

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        menu.setBorder(
                new EmptyBorder(25, 15, 20, 15)
        );

        // Nombre del sistema.
        JLabel titulo = new JLabel("ESSALUD");

        titulo.setForeground(BLANCO);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel subtitulo = new JLabel("Sistema de Gestion");

        subtitulo.setForeground(new Color(190, 205, 225));
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 13));

        menu.add(titulo);
        menu.add(Box.createVerticalStrut(5));
        menu.add(subtitulo);

        menu.add(Box.createVerticalStrut(45));

        // Botones de navegacion.
        menu.add(crearBotonMenu("INICIO", "inicio"));
        menu.add(Box.createVerticalStrut(12));

        menu.add(crearBotonMenu("PACIENTES", "pacientes"));
        menu.add(Box.createVerticalStrut(12));

        menu.add(crearBotonMenu("CITAS MEDICAS", "citas"));
        menu.add(Box.createVerticalStrut(12));

        menu.add(crearBotonMenu("CONSULTAS", "consultas"));

        // Espacio flexible para colocar el pie abajo.
        menu.add(Box.createVerticalGlue());

        JLabel version = new JLabel("Sistema EsSalud v1.0");

        version.setForeground(new Color(190, 205, 225));
        version.setFont(new Font("Arial", Font.PLAIN, 11));

        menu.add(version);

        add(menu, BorderLayout.WEST);
    }

    // ---------------------------------------------
    // 5. CREAR BOTONES DEL MENU
    // ---------------------------------------------
    private JButton crearBotonMenu(String texto, String panel) {

        JButton boton = new JButton(texto);

        boton.setMaximumSize(new Dimension(205, 45));
        boton.setPreferredSize(new Dimension(205, 45));

        boton.setBackground(new Color(35, 65, 100));
        boton.setForeground(BLANCO);

        boton.setFont(new Font("Arial", Font.BOLD, 13));

        boton.setFocusPainted(false);
        boton.setBorderPainted(false);

        boton.setHorizontalAlignment(SwingConstants.LEFT);

        boton.setBorder(
                new EmptyBorder(10, 15, 10, 10)
        );

        boton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        // Al pulsar el boton cambiamos de pantalla.
        boton.addActionListener(e -> mostrarPanel(panel));

        return boton;
    }

    // ---------------------------------------------
    // 6. CREAR CONTENIDO DE LA VENTANA
    // ---------------------------------------------
    private void crearPanelContenido() {

        // CardLayout permite cambiar entre diferentes paneles.
        cardLayout = new CardLayout();

        panelContenido = new JPanel(cardLayout);
        panelContenido.setBackground(FONDO);

        // Registramos cada modulo del sistema.
        panelContenido.add(crearInicio(), "inicio");

        panelContenido.add(
                new PanelPacientes(),
                "pacientes"
        );

        panelContenido.add(
                new PanelCitas(),
                "citas"
        );

        panelContenido.add(
                new PanelConsultas(),
                "consultas"
        );

        add(panelContenido, BorderLayout.CENTER);
    }

    // ---------------------------------------------
    // 7. CREAR PANEL PRINCIPAL
    // ---------------------------------------------
    private JPanel crearInicio() {

        JPanel inicio = new JPanel(new BorderLayout(20, 25));

        inicio.setBackground(FONDO);

        inicio.setBorder(
                new EmptyBorder(30, 35, 30, 35)
        );

        // Encabezado.
        JPanel encabezado = new JPanel();

        encabezado.setBackground(FONDO);

        encabezado.setLayout(
                new BoxLayout(encabezado, BoxLayout.Y_AXIS)
        );

        JLabel titulo = new JLabel("Panel principal");

        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(AZUL_OSCURO);

        JLabel bienvenida = new JLabel(
                "Bienvenido al Sistema de Gestion de EsSalud"
        );

        bienvenida.setFont(new Font("Arial", Font.PLAIN, 15));
        bienvenida.setForeground(Color.GRAY);

        encabezado.add(titulo);
        encabezado.add(Box.createVerticalStrut(8));
        encabezado.add(bienvenida);

        // -----------------------------------------
        // TARJETAS DE ESTADISTICAS
        // -----------------------------------------
        JPanel estadisticas = new JPanel(
                new GridLayout(1, 3, 15, 15)
        );

        estadisticas.setBackground(FONDO);

        estadisticas.add(
                crearTarjeta(
                        "Pacientes",
                        "0",
                        "Pacientes registrados",
                        AZUL
                )
        );

        estadisticas.add(
                crearTarjeta(
                        "Citas medicas",
                        "0",
                        "Citas registradas",
                        VERDE
                )
        );

        estadisticas.add(
                crearTarjeta(
                        "Pendientes",
                        "0",
                        "Citas por atender",
                        NARANJA
                )
        );

        // -----------------------------------------
        // ACCESOS RAPIDOS
        // -----------------------------------------
        JPanel centro = new JPanel(new BorderLayout(10, 15));

        centro.setBackground(FONDO);

        JLabel tituloAccesos = new JLabel("Accesos rapidos");

        tituloAccesos.setFont(new Font("Arial", Font.BOLD, 20));
        tituloAccesos.setForeground(AZUL_OSCURO);

        JPanel accesos = new JPanel(
                new GridLayout(1, 3, 15, 15)
        );

        accesos.setBackground(FONDO);

        JButton btnPacientes = new JButton("Gestionar pacientes");
        JButton btnCitas = new JButton("Programar citas");
        JButton btnConsultas = new JButton("Consultar pacientes");

        configurarBotonAcceso(btnPacientes);
        configurarBotonAcceso(btnCitas);
        configurarBotonAcceso(btnConsultas);

        // Conectamos los accesos rapidos.
        btnPacientes.addActionListener(
                e -> mostrarPanel("pacientes")
        );

        btnCitas.addActionListener(
                e -> mostrarPanel("citas")
        );

        btnConsultas.addActionListener(
                e -> mostrarPanel("consultas")
        );

        accesos.add(btnPacientes);
        accesos.add(btnCitas);
        accesos.add(btnConsultas);

        centro.add(tituloAccesos, BorderLayout.NORTH);
        centro.add(accesos, BorderLayout.CENTER);

        // Organizamos el panel principal.
        inicio.add(encabezado, BorderLayout.NORTH);
        inicio.add(estadisticas, BorderLayout.CENTER);
        inicio.add(centro, BorderLayout.SOUTH);

        return inicio;
    }

    // ---------------------------------------------
    // 8. CREAR TARJETAS DE ESTADISTICAS
    // ---------------------------------------------
    private JPanel crearTarjeta(
            String titulo,
            String valor,
            String descripcion,
            Color color
    ) {

        JPanel tarjeta = new JPanel();

        tarjeta.setBackground(BLANCO);

        tarjeta.setLayout(
                new BoxLayout(tarjeta, BoxLayout.Y_AXIS)
        );

        tarjeta.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 238)
                        ),
                        new EmptyBorder(20, 20, 20, 20)
                )
        );

        JLabel textoTitulo = new JLabel(titulo);

        textoTitulo.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        textoTitulo.setForeground(Color.GRAY);

        JLabel textoValor = new JLabel(valor);

        textoValor.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        textoValor.setForeground(color);

        JLabel textoDescripcion = new JLabel(descripcion);

        textoDescripcion.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );

        textoDescripcion.setForeground(Color.GRAY);

        // Guardamos las etiquetas para actualizarlas.
        if (titulo.equals("Pacientes")) {
            lblTotalPacientes = textoValor;
        }

        if (titulo.equals("Citas medicas")) {
            lblTotalCitas = textoValor;
        }

        if (titulo.equals("Pendientes")) {
            lblCitasPendientes = textoValor;
        }

        tarjeta.add(textoTitulo);
        tarjeta.add(Box.createVerticalStrut(15));
        tarjeta.add(textoValor);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(textoDescripcion);

        return tarjeta;
    }

    // ---------------------------------------------
    // 9. FORMATO DE LOS ACCESOS RAPIDOS
    // ---------------------------------------------
    private void configurarBotonAcceso(JButton boton) {

        boton.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        boton.setBackground(AZUL);
        boton.setForeground(BLANCO);

        boton.setFocusPainted(false);
        boton.setBorderPainted(false);

        boton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
    }

    // ---------------------------------------------
    // 10. ACTUALIZAR ESTADISTICAS
    // ---------------------------------------------
    private void actualizarEstadisticas() {

        // Contamos los pacientes registrados.
        int totalPacientes =
                pacienteServicio.listarPacientes().size();

        // Contamos todas las citas registradas.
        int totalCitas =
                citaServicio.listarCitas().size();

        // Contamos solamente las citas pendientes.
        int pendientes = 0;

        for (Cita cita : citaServicio.listarCitas()) {

            if ("PENDIENTE".equalsIgnoreCase(cita.getEstado())) {
                pendientes++;
            }
        }

        // Actualizamos las etiquetas visuales.
        lblTotalPacientes.setText(
                String.valueOf(totalPacientes)
        );

        lblTotalCitas.setText(
                String.valueOf(totalCitas)
        );

        lblCitasPendientes.setText(
                String.valueOf(pendientes)
        );
    }

    // ---------------------------------------------
    // 11. CAMBIAR DE PANTALLA
    // ---------------------------------------------
    private void mostrarPanel(String nombre) {

        // Mostramos el panel seleccionado.
        cardLayout.show(panelContenido, nombre);

        // Actualizamos las estadisticas al navegar.
        actualizarEstadisticas();
    }
}
