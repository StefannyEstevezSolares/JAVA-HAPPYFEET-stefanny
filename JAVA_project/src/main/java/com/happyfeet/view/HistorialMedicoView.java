
package com.happyfeet.view;

import com.happyfeet.controller.HistorialMedicoController;
import com.happyfeet.model.HistorialMedico;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class HistorialMedicoView {

    private final HistorialMedicoController historialController;
    private final Scanner scanner;

    public HistorialMedicoView() {
        this.historialController = new HistorialMedicoController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n--- HISTORIAL MÉDICO ---");
            System.out.println("1. Registrar historial médico");
            System.out.println("2. Listar historiales");
            System.out.println("3. Buscar historial por ID");
            System.out.println("4. Actualizar historial");
            System.out.println("5. Eliminar historial");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1:
                        registrarHistorial();
                        break;

                    case 2:
                        listarHistoriales();
                        break;

                    case 3:
                        buscarHistorial();
                        break;

                    case 4:
                        actualizarHistorial();
                        break;

                    case 5:
                        eliminarHistorial();
                        break;

                    case 0:
                        System.out.println("Regresando al menú principal...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
                opcion = -1;

            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Utilice AAAA-MM-DD.");
                opcion = -1;

            } catch (SQLException e) {
                System.out.println("Error de base de datos: " + e.getMessage());
                opcion = -1;
            }

        } while (opcion != 0);
    }

    private void registrarHistorial() throws SQLException {

        HistorialMedico historial = new HistorialMedico();

        System.out.println("\n--- REGISTRAR HISTORIAL MÉDICO ---");

        System.out.print("ID de la mascota: ");
        historial.setMascotaId(Integer.parseInt(scanner.nextLine()));

        System.out.print("Fecha del evento (AAAA-MM-DD): ");
        historial.setFechaEvento(LocalDate.parse(scanner.nextLine()));

        System.out.print("ID del tipo de evento: ");
        historial.setEventoTipoId(Integer.parseInt(scanner.nextLine()));

        System.out.print("Descripción: ");
        historial.setDescripcion(scanner.nextLine());

        System.out.print("Diagnóstico: ");
        historial.setDiagnostico(scanner.nextLine());

        System.out.print("Tratamiento recomendado: ");
        historial.setTratamientoRecomendado(scanner.nextLine());

        historial.setVeterinarioId(
                solicitarIdOpcional("ID del veterinario")
        );

        historial.setConsultaId(
                solicitarIdOpcional("ID de la consulta")
        );

        historial.setProcedimientoId(
                solicitarIdOpcional("ID del procedimiento")
        );

        boolean resultado = historialController.registrar(historial);

        if (resultado) {
            System.out.println("Historial médico registrado correctamente.");
        } else {
            System.out.println("No fue posible registrar el historial.");
        }
    }

    private void listarHistoriales() throws SQLException {

        System.out.println("\n--- LISTA DE HISTORIALES MÉDICOS ---");

        List<HistorialMedico> historiales =
                historialController.listarTodos();

        if (historiales.isEmpty()) {
            System.out.println("No hay historiales registrados.");
            return;
        }

        for (HistorialMedico historial : historiales) {
            mostrarDatosHistorial(historial);
        }
    }

    private void buscarHistorial() throws SQLException {

        System.out.println("\n--- BUSCAR HISTORIAL ---");

        System.out.print("Ingrese el ID del historial: ");
        int id = Integer.parseInt(scanner.nextLine());

        HistorialMedico historial =
                historialController.buscarPorId(id);

        if (historial != null) {
            mostrarDatosHistorial(historial);
        } else {
            System.out.println("No se encontró el historial.");
        }
    }

    private void actualizarHistorial() throws SQLException {

        System.out.println("\n--- ACTUALIZAR HISTORIAL ---");

        System.out.print("Ingrese el ID del historial: ");
        int id = Integer.parseInt(scanner.nextLine());

        HistorialMedico historial =
                historialController.buscarPorId(id);

        if (historial == null) {
            System.out.println("No se encontró el historial.");
            return;
        }

        System.out.println("Deje vacío el campo para conservar el dato actual.");

        System.out.print("ID de la mascota [" +
                historial.getMascotaId() + "]: ");
        String mascota = scanner.nextLine();

        if (!mascota.trim().isEmpty()) {
            historial.setMascotaId(Integer.parseInt(mascota));
        }

        System.out.print("Fecha del evento [" +
                historial.getFechaEvento() + "]: ");
        String fecha = scanner.nextLine();

        if (!fecha.trim().isEmpty()) {
            historial.setFechaEvento(LocalDate.parse(fecha));
        }

        System.out.print("ID del tipo de evento [" +
                historial.getEventoTipoId() + "]: ");
        String evento = scanner.nextLine();

        if (!evento.trim().isEmpty()) {
            historial.setEventoTipoId(Integer.parseInt(evento));
        }

        System.out.print("Descripción [" +
                historial.getDescripcion() + "]: ");
        String descripcion = scanner.nextLine();

        if (!descripcion.trim().isEmpty()) {
            historial.setDescripcion(descripcion);
        }

        System.out.print("Diagnóstico [" +
                historial.getDiagnostico() + "]: ");
        String diagnostico = scanner.nextLine();

        if (!diagnostico.trim().isEmpty()) {
            historial.setDiagnostico(diagnostico);
        }

        System.out.print("Tratamiento recomendado [" +
                historial.getTratamientoRecomendado() + "]: ");
        String tratamiento = scanner.nextLine();

        if (!tratamiento.trim().isEmpty()) {
            historial.setTratamientoRecomendado(tratamiento);
        }

        historial.setVeterinarioId(
                actualizarIdOpcional(
                        "ID del veterinario",
                        historial.getVeterinarioId()
                )
        );

        historial.setConsultaId(
                actualizarIdOpcional(
                        "ID de la consulta",
                        historial.getConsultaId()
                )
        );

        historial.setProcedimientoId(
                actualizarIdOpcional(
                        "ID del procedimiento",
                        historial.getProcedimientoId()
                )
        );

        boolean actualizado =
                historialController.actualizar(historial);

        if (actualizado) {
            System.out.println("Historial actualizado correctamente.");
        } else {
            System.out.println("No fue posible actualizar el historial.");
        }
    }

    private void eliminarHistorial() throws SQLException {

        System.out.println("\n--- ELIMINAR HISTORIAL ---");

        System.out.print("Ingrese el ID del historial: ");
        int id = Integer.parseInt(scanner.nextLine());

        HistorialMedico historial =
                historialController.buscarPorId(id);

        if (historial == null) {
            System.out.println("No se encontró el historial.");
            return;
        }

        System.out.print("¿Desea eliminar este historial? (S/N): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {

            boolean resultado = historialController.eliminar(id);

            if (resultado) {
                System.out.println("Historial eliminado correctamente.");
            } else {
                System.out.println("No fue posible eliminar el historial.");
            }

        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private Integer solicitarIdOpcional(String mensaje) {

        System.out.print(mensaje + " (opcional): ");
        String valor = scanner.nextLine();

        if (valor.trim().isEmpty()) {
            return null;
        }

        return Integer.parseInt(valor);
    }

    private Integer actualizarIdOpcional(
            String mensaje, Integer valorActual) {

        System.out.print(mensaje + " [" + valorActual + "] (vacío conserva): ");
        String valor = scanner.nextLine();

        if (valor.trim().isEmpty()) {
            return valorActual;
        }

        return Integer.parseInt(valor);
    }

    private void mostrarDatosHistorial(HistorialMedico historial) {

        System.out.println("\nID: " + historial.getId());
        System.out.println("Mascota ID: " + historial.getMascotaId());
        System.out.println("Fecha del evento: " + historial.getFechaEvento());
        System.out.println("Tipo de evento ID: " + historial.getEventoTipoId());
        System.out.println("Descripción: " + historial.getDescripcion());
        System.out.println("Diagnóstico: " + historial.getDiagnostico());
        System.out.println("Tratamiento recomendado: " +
                historial.getTratamientoRecomendado());
        System.out.println("Veterinario ID: " + historial.getVeterinarioId());
        System.out.println("Consulta ID: " + historial.getConsultaId());
        System.out.println("Procedimiento ID: " + historial.getProcedimientoId());
        System.out.println("--------------------------------");
    }
}
