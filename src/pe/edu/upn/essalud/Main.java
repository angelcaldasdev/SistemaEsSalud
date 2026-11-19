package pe.edu.upn.essalud;

import javax.swing.SwingUtilities;
import pe.edu.upn.essalud.interfaz.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            VentanaPrincipal ventana;
            ventana = new VentanaPrincipal();

            ventana.setVisible(true);

        });
    }
}