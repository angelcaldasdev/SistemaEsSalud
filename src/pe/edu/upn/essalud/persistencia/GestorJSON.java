
package pe.edu.upn.essalud.persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import pe.edu.upn.essalud.modelo.Paciente;
import pe.edu.upn.essalud.modelo.Cita;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorJSON {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path CARPETA = Path.of("datos");

    private static final Path ARCHIVO_PACIENTES =
            CARPETA.resolve("pacientes.json");

    private static final Path ARCHIVO_CITAS =
            CARPETA.resolve("citas.json");

    private GestorJSON() {
    }

    public static void guardarPacientes(List<Paciente> pacientes) {
        try {
            Files.createDirectories(CARPETA);

            String json = gson.toJson(pacientes);

            Files.writeString(ARCHIVO_PACIENTES, json);

            System.out.println("Pacientes guardados correctamente.");

        } catch (IOException e) {
            System.err.println("Error al guardar pacientes: "
                    + e.getMessage());
        }
    }

    public static List<Paciente> cargarPacientes() {
        try {
            if (!Files.exists(ARCHIVO_PACIENTES)) {
                return new ArrayList<>();
            }

            String json = Files.readString(ARCHIVO_PACIENTES);

            Type tipo = new TypeToken<List<Paciente>>() {
            }.getType();

            List<Paciente> pacientes = gson.fromJson(json, tipo);

            return pacientes != null ? pacientes : new ArrayList<>();

        } catch (IOException e) {
            System.err.println("Error al cargar pacientes: "
                    + e.getMessage());

            return new ArrayList<>();
        }
    }

    public static void guardarCitas(List<Cita> citas) {
        try {
            Files.createDirectories(CARPETA);

            String json = gson.toJson(citas);

            Files.writeString(ARCHIVO_CITAS, json);

            System.out.println("Citas guardadas correctamente.");

        } catch (IOException e) {
            System.err.println("Error al guardar citas: "
                    + e.getMessage());
        }
    }

    public static List<Cita> cargarCitas() {
        try {
            if (!Files.exists(ARCHIVO_CITAS)) {
                return new ArrayList<>();
            }

            String json = Files.readString(ARCHIVO_CITAS);

            Type tipo = new TypeToken<List<Cita>>() {
            }.getType();

            List<Cita> citas = gson.fromJson(json, tipo);

            return citas != null ? citas : new ArrayList<>();

        } catch (IOException e) {
            System.err.println("Error al cargar citas: "
                    + e.getMessage());

            return new ArrayList<>();
        }
    }
}
