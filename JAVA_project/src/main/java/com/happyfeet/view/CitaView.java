
package com.happyfeet.view;
import com.happyfeet.util.ControladorErrores;
import com.happyfeet.controller.CitaController;
import com.happyfeet.model.Cita;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class CitaView {

    private final CitaController citaController;
    private final Scanner scanner;

    public CitaView() {
        this.citaController = new CitaController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE CITAS ---");
            System.out.println("1. Registrar cita");
            System.out.println("2. Listar citas");
            System.out.println("3. Buscar cita por ID");
            System.out.println("4. Actualizar cita");
            System.out.println("5. Eliminar cita");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1:
                        registrarCita();
                        break;

                    case 2:
                        listarCitas();
                        break;

                    case 3:
                        buscarCita();
                        break;

                    case 4:
                        actualizarCita();
                        break;

                    case 5:
                        eliminarCita();
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
                System.out.println("Formato incorrecto. Utilice AAAA-MM-DDTHH:MM.");
                opcion = -1;

            } catch (SQLException e) {
                System.out.println("Error de base de datos: " + e.getMessage());
                opcion = -1;
            }

        } while (opcion != 0);
    }

    private void registrarCita() throws SQLException {

        Cita cita = new Cita();

        System.out.println("\n--- REGISTRAR CITA ---");

        System.out.print("ID de la mascota: ");
        cita.setMascotaId(Integer.parseInt(scanner.nextLine()));

        System.out.print("ID del veterinario (vacío si no está asignado): ");
        String veterinario = scanner.nextLine();

        if (veterinario.trim().isEmpty()) {
            cita.setVeterinarioId(null);
        } else {
            cita.setVeterinarioId(Integer.parseInt(veterinario));
        }

        System.out.print("Fecha y hora (AAAA-MM-DDTHH:MM): ");
        cita.setFechaHora(LocalDateTime.parse(scanner.nextLine()));

        System.out.print("Motivo de la cita: ");
        cita.setMotivo(scanner.nextLine());

        System.out.print("ID del estado de la cita: ");
        cita.setEstadoId(Integer.parseInt(scanner.nextLine()));

        System.out.print("Observaciones: ");
        cita.setObservaciones(scanner.nextLine());

        int resultado = citaController.registrar(cita);

        if (resultado > 0) {
            System.out.println("Cita registrada correctamente. ID: " + resultado);
        } else {
            System.out.println("No fue posible registrar la cita.");
        }
    }

    private void listarCitas() throws SQLException {

        System.out.println("\n--- LISTA DE CITAS ---");

        List<Cita> citas = citaController.listarTodos();

        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }

        for (Cita cita : citas) {
            mostrarDatosCita(cita);
        }
    }

    private void buscarCita() throws SQLException {

        System.out.println("\n--- BUSCAR CITA ---");

        System.out.print("Ingrese el ID de la cita: ");
        int id = Integer.parseInt(scanner.nextLine());

        Cita cita = citaController.buscarPorId(id);

        if (cita != null) {
            mostrarDatosCita(cita);
        } else {
            System.out.println("No se encontró la cita.");
        }
    }

    private void actualizarCita() throws SQLException {

        System.out.println("\n--- ACTUALIZAR CITA ---");

        System.out.print("Ingrese el ID de la cita: ");
        int id = Integer.parseInt(scanner.nextLine());

        Cita cita = citaController.buscarPorId(id);

        if (cita == null) {
            System.out.println("No se encontró la cita.");
            return;
        }

        System.out.println("Deje vacío el campo para conservar el dato actual.");

        System.out.print("ID de la mascota [" + cita.getMascotaId() + "]: ");
        String mascota = scanner.nextLine();

        if (!mascota.trim().isEmpty()) {
            cita.setMascotaId(Integer.parseInt(mascota));
        }

        System.out.print("ID del veterinario [" + cita.getVeterinarioId() + "]: ");
        String veterinario = scanner.nextLine();

        if (!veterinario.trim().isEmpty()) {
            cita.setVeterinarioId(Integer.parseInt(veterinario));
        }

        System.out.print("Fecha y hora [" + cita.getFechaHora() + "]: ");
        String fechaHora = scanner.nextLine();

        if (!fechaHora.trim().isEmpty()) {
            cita.setFechaHora(LocalDateTime.parse(fechaHora));
        }

        System.out.print("Motivo [" + cita.getMotivo() + "]: ");
        String motivo = scanner.nextLine();

        if (!motivo.trim().isEmpty()) {
            cita.setMotivo(motivo);
        }

        System.out.print("ID del estado [" + cita.getEstadoId() + "]: ");
        String estado = scanner.nextLine();

        if (!estado.trim().isEmpty()) {
            cita.setEstadoId(Integer.parseInt(estado));
        }

        System.out.print("Observaciones [" + cita.getObservaciones() + "]: ");
        String observaciones = scanner.nextLine();

        if (!observaciones.trim().isEmpty()) {
            cita.setObservaciones(observaciones);
        }

        boolean actualizado = citaController.actualizar(cita);

        if (actualizado) {
            System.out.println("Cita actualizada correctamente.");
        } else {
            System.out.println("No fue posible actualizar la cita.");
        }
    }

    private void eliminarCita() throws SQLException {

        System.out.println("\n--- ELIMINAR CITA ---");

        System.out.print("Ingrese el ID de la cita: ");
        int id = Integer.parseInt(scanner.nextLine());

        Cita cita = citaController.buscarPorId(id);

        if (cita == null) {
            System.out.println("No se encontró la cita.");
            return;
        }

        System.out.print("¿Desea eliminar la cita seleccionada? (S/N): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {

            boolean resultado = citaController.eliminar(id);

            if (resultado) {
                System.out.println("Cita eliminada correctamente.");
            } else {
                System.out.println("No fue posible eliminar la cita.");
            }

        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private void mostrarDatosCita(Cita cita) {

        System.out.println("\nID: " + cita.getId());
        System.out.println("Mascota ID: " + cita.getMascotaId());
        System.out.println("Veterinario ID: " + cita.getVeterinarioId());
        System.out.println("Fecha y hora: " + cita.getFechaHora());
        System.out.println("Motivo: " + cita.getMotivo());
        System.out.println("Estado ID: " + cita.getEstadoId());
        System.out.println("Observaciones: " + cita.getObservaciones());
        System.out.println("Fecha de creación: " + cita.getFechaCreacion());
        System.out.println("--------------------------------");
    }
}
