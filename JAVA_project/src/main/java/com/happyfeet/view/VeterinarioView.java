
package com.happyfeet.view;

import com.happyfeet.util.ControladorErrores;
import com.happyfeet.controller.VeterinarioController;
import com.happyfeet.model.Veterinario;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class VeterinarioView {

    private final VeterinarioController veterinarioController;
    private final Scanner scanner;

    public VeterinarioView() {
        this.veterinarioController = new VeterinarioController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE VETERINARIOS ---");
            System.out.println("1. Registrar veterinario");
            System.out.println("2. Listar veterinarios");
            System.out.println("3. Buscar veterinario por ID");
            System.out.println("4. Actualizar veterinario");
            System.out.println("5. Desactivar veterinario");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1:
                        registrarVeterinario();
                        break;

                    case 2:
                        listarVeterinarios();
                        break;

                    case 3:
                        buscarVeterinario();
                        break;

                    case 4:
                        actualizarVeterinario();
                        break;

                    case 5:
                        desactivarVeterinario();
                        break;

                    case 0:
                        System.out.println("Regresando al menú principal...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (NumberFormatException e) {
                ControladorErrores.registrar(e);
                System.out.println("Debe ingresar un número válido.");
                opcion = -1;

            } catch (DateTimeParseException e) {
                ControladorErrores.registrar(e);
                System.out.println("Formato de fecha incorrecto. Utilice AAAA-MM-DD.");
                opcion = -1;

            } catch (SQLException e) {
                ControladorErrores.registrar(e);
                System.out.println("Error de base de datos: " + e.getMessage());
                opcion = -1;
            }

        } while (opcion != 0);
    }

    private void registrarVeterinario() throws SQLException {

        Veterinario veterinario = new Veterinario();

        System.out.println("\n--- REGISTRAR VETERINARIO ---");

        System.out.print("Nombre completo: ");
        veterinario.setNombreCompleto(scanner.nextLine());

        System.out.print("Documento de identidad: ");
        veterinario.setDocumentoIdentidad(scanner.nextLine());

        System.out.print("Licencia profesional: ");
        veterinario.setLicenciaProfesional(scanner.nextLine());

        System.out.print("Especialidad: ");
        veterinario.setEspecialidad(scanner.nextLine());

        System.out.print("Teléfono: ");
        veterinario.setTelefono(scanner.nextLine());

        System.out.print("Correo electrónico: ");
        veterinario.setEmail(scanner.nextLine());

        System.out.print("Fecha de contratación (AAAA-MM-DD): ");
        String fecha = scanner.nextLine();

        if (!fecha.trim().isEmpty()) {
            veterinario.setFechaContratacion(LocalDate.parse(fecha));
        }

        veterinario.setActivo(true);

        int resultado = veterinarioController.registrar(veterinario);

        if (resultado > 0) {
            System.out.println("Veterinario registrado correctamente. ID: " + resultado);
        } else {
            System.out.println("No fue posible registrar al veterinario.");
        }
    }

    private void listarVeterinarios() throws SQLException {

        System.out.println("\n--- LISTA DE VETERINARIOS ---");

        List<Veterinario> veterinarios = veterinarioController.listarTodos();

        if (veterinarios.isEmpty()) {
            System.out.println("No hay veterinarios registrados.");
            return;
        }

        for (Veterinario veterinario : veterinarios) {
            mostrarDatosVeterinario(veterinario);
        }
    }

    private void buscarVeterinario() throws SQLException {

        System.out.println("\n--- BUSCAR VETERINARIO ---");

        System.out.print("Ingrese el ID del veterinario: ");
        int id = Integer.parseInt(scanner.nextLine());

        Veterinario veterinario = veterinarioController.buscarPorId(id);

        if (veterinario != null) {
            mostrarDatosVeterinario(veterinario);
        } else {
            System.out.println("No se encontró el veterinario.");
        }
    }

    private void actualizarVeterinario() throws SQLException {

        System.out.println("\n--- ACTUALIZAR VETERINARIO ---");

        System.out.print("Ingrese el ID del veterinario: ");
        int id = Integer.parseInt(scanner.nextLine());

        Veterinario veterinario = veterinarioController.buscarPorId(id);

        if (veterinario == null) {
            System.out.println("No se encontró el veterinario.");
            return;
        }

        System.out.println("Deje vacío el campo para conservar el dato actual.");

        System.out.print("Nombre completo [" + veterinario.getNombreCompleto() + "]: ");
        String nombre = scanner.nextLine();

        if (!nombre.trim().isEmpty()) {
            veterinario.setNombreCompleto(nombre);
        }

        System.out.print("Documento [" + veterinario.getDocumentoIdentidad() + "]: ");
        String documento = scanner.nextLine();

        if (!documento.trim().isEmpty()) {
            veterinario.setDocumentoIdentidad(documento);
        }

        System.out.print("Licencia profesional [" + veterinario.getLicenciaProfesional() + "]: ");
        String licencia = scanner.nextLine();

        if (!licencia.trim().isEmpty()) {
            veterinario.setLicenciaProfesional(licencia);
        }

        System.out.print("Especialidad [" + veterinario.getEspecialidad() + "]: ");
        String especialidad = scanner.nextLine();

        if (!especialidad.trim().isEmpty()) {
            veterinario.setEspecialidad(especialidad);
        }

        System.out.print("Teléfono [" + veterinario.getTelefono() + "]: ");
        String telefono = scanner.nextLine();

        if (!telefono.trim().isEmpty()) {
            veterinario.setTelefono(telefono);
        }

        System.out.print("Correo electrónico [" + veterinario.getEmail() + "]: ");
        String email = scanner.nextLine();

        if (!email.trim().isEmpty()) {
            veterinario.setEmail(email);
        }

        System.out.print("Fecha de contratación [" +
                veterinario.getFechaContratacion() + "]: ");
        String fecha = scanner.nextLine();

        if (!fecha.trim().isEmpty()) {
            veterinario.setFechaContratacion(LocalDate.parse(fecha));
        }

        boolean actualizado = veterinarioController.actualizar(veterinario);

        if (actualizado) {
            System.out.println("Veterinario actualizado correctamente.");
        } else {
            System.out.println("No fue posible actualizar al veterinario.");
        }
    }

    private void desactivarVeterinario() throws SQLException {

        System.out.println("\n--- DESACTIVAR VETERINARIO ---");

        System.out.print("Ingrese el ID del veterinario: ");
        int id = Integer.parseInt(scanner.nextLine());

        Veterinario veterinario = veterinarioController.buscarPorId(id);

        if (veterinario == null) {
            System.out.println("No se encontró el veterinario.");
            return;
        }

        System.out.print("¿Desea desactivar a " +
                veterinario.getNombreCompleto() + "? (S/N): ");

        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {

            boolean resultado = veterinarioController.desactivar(id);

            if (resultado) {
                System.out.println("Veterinario desactivado correctamente.");
            } else {
                System.out.println("No fue posible desactivar al veterinario.");
            }

        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private void mostrarDatosVeterinario(Veterinario veterinario) {

        System.out.println("\nID: " + veterinario.getId());
        System.out.println("Nombre: " + veterinario.getNombreCompleto());
        System.out.println("Documento: " + veterinario.getDocumentoIdentidad());
        System.out.println("Licencia: " + veterinario.getLicenciaProfesional());
        System.out.println("Especialidad: " + veterinario.getEspecialidad());
        System.out.println("Teléfono: " + veterinario.getTelefono());
        System.out.println("Correo: " + veterinario.getEmail());
        System.out.println("Fecha de contratación: " + veterinario.getFechaContratacion());
        System.out.println("Estado: " + (veterinario.isActivo() ? "Activo" : "Inactivo"));
        System.out.println("--------------------------------");
    }
}
