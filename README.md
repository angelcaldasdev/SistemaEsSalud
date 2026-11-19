# Sistema de Citas Médicas – EsSalud

Aplicación de escritorio en **Java (Swing)** para gestionar pacientes y citas médicas, con una interfaz gráfica de navegación lateral. Proyecto académico desarrollado en la carrera de Ingeniería de Sistemas (UPN).

## Funcionalidades

- **Pacientes:** registrar, buscar, actualizar y eliminar pacientes.
- **Citas médicas:** registrar y consultar citas (código, especialidad, fecha, hora y estado).
- **Consultas:** buscar un paciente por DNI y revisar su información e historial de citas.
- **Validación de datos:** control de campos como el DNI antes de guardar.

## Tecnologías

- Java
- Java Swing (interfaz gráfica)
- Apache NetBeans 32 (proyecto Ant)
- Git y GitHub

## Estructura del proyecto

```
src/pe/edu/upn/essalud/
├── Main.java            → punto de entrada de la aplicación
├── interfaz/            → ventana principal y paneles (Pacientes, Citas, Consultas)
├── modelo/              → clases Paciente y Cita
├── repositorio/         → almacenamiento de datos
├── servicio/            → lógica de negocio (PacienteServicio, CitaServicio)
└── util/                → utilidades (Validador)
```

El proyecto sigue una organización por capas: la interfaz usa los servicios, los servicios usan el repositorio y el repositorio trabaja con las clases del modelo.

## Cómo ejecutarlo

1. Instala un **JDK** (21 o superior) y **Apache NetBeans**.
2. Clona el repositorio:
   ```
   git clone https://github.com/angelcaldasdev/SistemaEsSalud.git
   ```
3. En NetBeans: **File → Open Project...** y selecciona la carpeta clonada.
4. Ejecuta con clic derecho sobre el proyecto → **Run**, o sobre `Main.java` → **Run File**.

## Nota sobre el origen

Este proyecto parte de un modelo base recibido en el curso y fue reconstruido paso a paso como práctica personal. El historial de commits refleja ese proceso.

## Autor

**Elber Nuñez Iguavel**

**Angel Caldas Ocmín**

**Milovan Rojas Romero**

GitHub: [@angelcaldasdev](https://github.com/angelcaldasdev)
