
package com.happyfeet.view;

import com.happyfeet.controller.DuenoController;
import com.happyfeet.model.Dueno;
import com.happyfeet.util.ValidacionTexto;
import com.happyfeet.util.ControladorErrores;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DuenoView {

    private final DuenoController duenoController;
    private final Scanner scanner;

    public DuenoView() {
        this.duenoController = new DuenoController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n--- GESTION DE DUEÑOS ---");
            System.out.println("1. Registrar dueño");
            System.out.println("2. Listar dueños");
            System.out.println("3. Buscar dueño por ID");
            System.out.println("4. Actualizar dueño");
            System.out.println("5. Desactivar dueño");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1:
                        registrarDueno();
                        break;

                    case 2:
                        listarDuenos();
                        break;

                    case 3:
                        buscarDueno();
                        break;

                    case 4:
                        actualizarDueno();
                        break;

                    case 5:
                        desactivarDueno();
                        break;

                    case 0:
                        System.out.println("Regresando al menú principal...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (Exception e) {
                ControladorErrores.registrar(e);
                System.out.println("Ocurrió un error al procesar la operación.");
                opcion = -1;
            }

        } while (opcion != 0);
    }

    private void registrarDueno() throws SQLException {

        Dueno dueno = new Dueno();

        System.out.println("\n--- REGISTRAR DUEÑO ---");

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(nombre, "Nombre completo")) {
            return;
        }
        dueno.setNombreCompleto(ValidacionTexto.limpiarTexto(nombre));

        System.out.print("Documento de identidad: ");
        String documento = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(documento, "Documento de identidad")) {
            return;
        }
        dueno.setDocumentoIdentidad(ValidacionTexto.limpiarTexto(documento));

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(direccion, "Dirección")) {
            return;
        }
        dueno.setDireccion(ValidacionTexto.limpiarTexto(direccion));

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(telefono, "Teléfono")) {
            return;
        }
        dueno.setTelefono(ValidacionTexto.limpiarTexto(telefono));

        System.out.print("Correo electrónico: ");
        String email = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(email, "Correo electrónico")) {
            return;
        }
        dueno.setEmail(ValidacionTexto.limpiarTexto(email));

        System.out.print("Contacto de emergencia: ");
        dueno.setContactoEmergencia(
                ValidacionTexto.limpiarTexto(scanner.nextLine())
        );

        dueno.setActivo(true);

        int resultado = duenoController.registrar(dueno);

        if (resultado > 0) {
            System.out.println("Dueño registrado correctamente. ID: " + resultado);
        } else {
            System.out.println("No fue posible registrar al dueño.");
        }
    }

    private void listarDuenos() throws SQLException {

        System.out.println("\n--- LISTA DE DUEÑOS ---");

        List<Dueno> duenos = duenoController.listarTodos();

        if (duenos.isEmpty()) {
            System.out.println("No hay dueños registrados.");
            return;
        }

        for (Dueno dueno : duenos) {
            mostrarDatosDueno(dueno);
        }
    }

    private void buscarDueno() throws SQLException {

        System.out.println("\n--- BUSCAR DUEÑO ---");

        System.out.print("Ingrese el ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Dueno dueno = duenoController.buscarPorId(id);

        if (dueno != null) {
            mostrarDatosDueno(dueno);
        } else {
            System.out.println("No se encontró el dueño.");
        }
    }

    private void actualizarDueno() throws SQLException {

        System.out.println("\n--- ACTUALIZAR DUEÑO ---");

        System.out.print("Ingrese el ID del dueño: ");
        int id = Integer.parseInt(scanner.nextLine());

        Dueno dueno = duenoController.buscarPorId(id);

        if (dueno == null) {
            System.out.println("No se encontró el dueño.");
            return;
        }

        System.out.println("Deje vacío el campo si desea conservar el dato actual.");

        System.out.print("Nombre completo [" + dueno.getNombreCompleto() + "]: ");
        String nombre = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!nombre.isEmpty()) {
            dueno.setNombreCompleto(nombre);
        }

        System.out.print("Documento [" + dueno.getDocumentoIdentidad() + "]: ");
        String documento = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!documento.isEmpty()) {
            dueno.setDocumentoIdentidad(documento);
        }

        System.out.print("Dirección [" + dueno.getDireccion() + "]: ");
        String direccion = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!direccion.isEmpty()) {
            dueno.setDireccion(direccion);
        }

        System.out.print("Teléfono [" + dueno.getTelefono() + "]: ");
        String telefono = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!telefono.isEmpty()) {
            dueno.setTelefono(telefono);
        }

        System.out.print("Correo electrónico [" + dueno.getEmail() + "]: ");
        String email = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!email.isEmpty()) {
            dueno.setEmail(email);
        }

        System.out.print("Contacto de emergencia [" + dueno.getContactoEmergencia() + "]: ");
        String contacto = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!contacto.isEmpty()) {
            dueno.setContactoEmergencia(contacto);
        }

        boolean actualizado = duenoController.actualizar(dueno);

        if (actualizado) {
            System.out.println("Dueño actualizado correctamente.");
        } else {
            System.out.println("No fue posible actualizar al dueño.");
        }
    }

    private void desactivarDueno() throws SQLException {

        System.out.println("\n--- DESACTIVAR DUEÑO ---");

        System.out.print("Ingrese el ID del dueño: ");
        int id = Integer.parseInt(scanner.nextLine());

        Dueno dueno = duenoController.buscarPorId(id);

        if (dueno == null) {
            System.out.println("No se encontró el dueño.");
            return;
        }

        System.out.print("¿Desea desactivar a " +
                dueno.getNombreCompleto() + "? (S/N): ");

        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {

            boolean desactivado = duenoController.desactivar(id);

            if (desactivado) {
                System.out.println("Dueño desactivado correctamente.");
            } else {
                System.out.println("No fue posible desactivar al dueño.");
            }

        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private void mostrarDatosDueno(Dueno dueno) {

        System.out.println("\nID: " + dueno.getId());
        System.out.println("Nombre: " + dueno.getNombreCompleto());
        System.out.println("Documento: " + dueno.getDocumentoIdentidad());
        System.out.println("Dirección: " + dueno.getDireccion());
        System.out.println("Teléfono: " + dueno.getTelefono());
        System.out.println("Correo: " + dueno.getEmail());
        System.out.println("Contacto de emergencia: " + dueno.getContactoEmergencia());
        System.out.println("Fecha de registro: " + dueno.getFechaRegistro());
        System.out.println("Estado: " + (dueno.isActivo() ? "Activo" : "Inactivo"));
        System.out.println("--------------------------------");
    }
}
