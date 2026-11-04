package com.happyfeet.view;

import com.happyfeet.util.ControladorErrores;
import com.happyfeet.controller.ProcedimientoEspecialController;
import com.happyfeet.controller.InsumoProcedimientoController;
import com.happyfeet.model.ProcedimientoEspecial;
import com.happyfeet.model.InsumoProcedimiento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ProcedimientoView {

    private final Scanner scanner;
    private final ProcedimientoEspecialController procedimientoController;
    private final InsumoProcedimientoController insumoController;

    public ProcedimientoView() {
        scanner = new Scanner(System.in);
        procedimientoController = new ProcedimientoEspecialController();
        insumoController = new InsumoProcedimientoController();
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n===== PROCEDIMIENTOS E INSUMOS =====");
            System.out.println("1. Administrar procedimientos especiales");
            System.out.println("2. Administrar insumos utilizados");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    menuProcedimientos();
                    break;
                case 2:
                    menuInsumos();
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ==========================================
    // MENÚ DE PROCEDIMIENTOS
    // ==========================================
    private void menuProcedimientos() {

        int opcion;

        do {
            System.out.println("\n===== PROCEDIMIENTOS ESPECIALES =====");
            System.out.println("1. Registrar procedimiento");
            System.out.println("2. Listar procedimientos");
            System.out.println("3. Buscar procedimiento por ID");
            System.out.println("4. Actualizar procedimiento");
            System.out.println("5. Eliminar procedimiento");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarProcedimiento();
                    break;
                case 2:
                    listarProcedimientos();
                    break;
                case 3:
                    buscarProcedimiento();
                    break;
                case 4:
                    actualizarProcedimiento();
                    break;
                case 5:
                    eliminarProcedimiento();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void registrarProcedimiento() {

        try {
            ProcedimientoEspecial procedimiento = new ProcedimientoEspecial();

            System.out.println("\n--- REGISTRAR PROCEDIMIENTO ---");

            System.out.print("ID de la mascota: ");
            procedimiento.setMascotaId(leerEntero());

            System.out.print("ID del veterinario: ");
            procedimiento.setVeterinarioId(leerEntero());

            System.out.print("Tipo de procedimiento: ");
            procedimiento.setTipoProcedimiento(scanner.nextLine());

            System.out.print("Nombre del procedimiento: ");
            procedimiento.setNombreProcedimiento(scanner.nextLine());

            System.out.print("Fecha y hora (AAAA-MM-DDTHH:MM): ");
            procedimiento.setFechaHora(
                    LocalDateTime.parse(scanner.nextLine())
            );

            System.out.print("Duración estimada en minutos: ");
            String duracion = scanner.nextLine();
            procedimiento.setDuracionEstimadaMinutos(
                    duracion.isBlank() ? null : Integer.parseInt(duracion)
            );

            System.out.print("Información preoperatoria: ");
            procedimiento.setInformacionPreoperatoria(scanner.nextLine());

            System.out.print("Detalle del procedimiento: ");
            procedimiento.setDetalleProcedimiento(scanner.nextLine());

            System.out.print("Complicaciones: ");
            procedimiento.setComplicaciones(scanner.nextLine());

            System.out.print("Seguimiento postoperatorio: ");
            procedimiento.setSeguimientoPostoperatorio(scanner.nextLine());

            System.out.print("Próximo control (AAAA-MM-DD, opcional): ");
            String control = scanner.nextLine();
            procedimiento.setProximoControl(
                    control.isBlank() ? null : LocalDate.parse(control)
            );

            System.out.println("Estado:");
            System.out.println("Programado");
            System.out.println("En Proceso");
            System.out.println("Finalizado");
            System.out.println("Cancelado");
            System.out.print("Seleccione el estado: ");
            procedimiento.setEstado(scanner.nextLine());

            System.out.print("Costo del procedimiento: ");
            procedimiento.setCostoProcedimiento(
                    new BigDecimal(scanner.nextLine())
            );

            boolean resultado = procedimientoController.registrar(procedimiento);

            if (resultado) {
                System.out.println("Procedimiento registrado correctamente.");
            } else {
                System.out.println("No se pudo registrar el procedimiento.");
            }

        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("Error: verifica los números y las fechas ingresadas.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarProcedimientos() {

        List<ProcedimientoEspecial> lista
                = procedimientoController.listarTodos();

        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay procedimientos registrados.");
            return;
        }

        for (ProcedimientoEspecial procedimiento : lista) {
            mostrarProcedimiento(procedimiento);
        }
    }

    private void buscarProcedimiento() {

        System.out.print("Ingrese el ID del procedimiento: ");
        int id = leerEntero();

        ProcedimientoEspecial procedimiento
                = procedimientoController.buscarPorId(id);

        if (procedimiento != null) {
            mostrarProcedimiento(procedimiento);
        } else {
            System.out.println("No se encontró el procedimiento.");
        }
    }

    private void actualizarProcedimiento() {

        System.out.print("Ingrese el ID del procedimiento: ");
        int id = leerEntero();

        ProcedimientoEspecial procedimiento
                = procedimientoController.buscarPorId(id);

        if (procedimiento == null) {
            System.out.println("No se encontró el procedimiento.");
            return;
        }

        try {
            System.out.println("\n--- ACTUALIZAR PROCEDIMIENTO ---");
            System.out.println("Deja vacío un campo para conservar su valor actual.");

            System.out.print("ID de mascota [" + procedimiento.getMascotaId() + "]: ");
            String entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setMascotaId(Integer.parseInt(entrada));
            }

            System.out.print("ID de veterinario [" + procedimiento.getVeterinarioId() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setVeterinarioId(Integer.parseInt(entrada));
            }

            System.out.print("Tipo [" + procedimiento.getTipoProcedimiento() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setTipoProcedimiento(entrada);
            }

            System.out.print("Nombre [" + procedimiento.getNombreProcedimiento() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setNombreProcedimiento(entrada);
            }

            System.out.print("Fecha y hora [" + procedimiento.getFechaHora() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setFechaHora(LocalDateTime.parse(entrada));
            }

            System.out.print("Duración en minutos [" + procedimiento.getDuracionEstimadaMinutos() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setDuracionEstimadaMinutos(Integer.parseInt(entrada));
            }

            System.out.print("Información preoperatoria [" + procedimiento.getInformacionPreoperatoria() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setInformacionPreoperatoria(entrada);
            }

            System.out.print("Detalle [" + procedimiento.getDetalleProcedimiento() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setDetalleProcedimiento(entrada);
            }

            System.out.print("Complicaciones [" + procedimiento.getComplicaciones() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setComplicaciones(entrada);
            }

            System.out.print("Seguimiento postoperatorio [" + procedimiento.getSeguimientoPostoperatorio() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setSeguimientoPostoperatorio(entrada);
            }

            System.out.print("Próximo control [" + procedimiento.getProximoControl() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setProximoControl(LocalDate.parse(entrada));
            }

            System.out.print("Estado [" + procedimiento.getEstado() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setEstado(entrada);
            }

            System.out.print("Costo [" + procedimiento.getCostoProcedimiento() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                procedimiento.setCostoProcedimiento(new BigDecimal(entrada));
            }

            boolean resultado = procedimientoController.actualizar(procedimiento);

            if (resultado) {
                System.out.println("Procedimiento actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el procedimiento.");
            }

        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("Error: verifica los números y las fechas ingresadas.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarProcedimiento() {

        System.out.print("Ingrese el ID del procedimiento: ");
        int id = leerEntero();

        System.out.print("¿Desea eliminar este procedimiento? (S/N): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {

            boolean resultado = procedimientoController.eliminar(id);

            if (resultado) {
                System.out.println("Procedimiento eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el procedimiento.");
            }
        }
    }

    private void mostrarProcedimiento(ProcedimientoEspecial p) {

        System.out.println("\n--- PROCEDIMIENTO ---");
        System.out.println("ID: " + p.getId());
        System.out.println("Mascota ID: " + p.getMascotaId());
        System.out.println("Veterinario ID: " + p.getVeterinarioId());
        System.out.println("Tipo: " + p.getTipoProcedimiento());
        System.out.println("Nombre: " + p.getNombreProcedimiento());
        System.out.println("Fecha y hora: " + p.getFechaHora());
        System.out.println("Duración: " + p.getDuracionEstimadaMinutos());
        System.out.println("Información preoperatoria: " + p.getInformacionPreoperatoria());
        System.out.println("Detalle: " + p.getDetalleProcedimiento());
        System.out.println("Complicaciones: " + p.getComplicaciones());
        System.out.println("Seguimiento: " + p.getSeguimientoPostoperatorio());
        System.out.println("Próximo control: " + p.getProximoControl());
        System.out.println("Estado: " + p.getEstado());
        System.out.println("Costo: " + p.getCostoProcedimiento());
    }

    
    // MENÚ DE INSUMOS
    private void menuInsumos() {

        int opcion;

        do {
            System.out.println("\n===== INSUMOS DE PROCEDIMIENTOS =====");
            System.out.println("1. Registrar insumo");
            System.out.println("2. Listar insumos");
            System.out.println("3. Buscar insumo por ID");
            System.out.println("4. Actualizar insumo");
            System.out.println("5. Eliminar insumo");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarInsumo();
                    break;
                case 2:
                    listarInsumos();
                    break;
                case 3:
                    buscarInsumo();
                    break;
                case 4:
                    actualizarInsumo();
                    break;
                case 5:
                    eliminarInsumo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void registrarInsumo() {

        try {
            InsumoProcedimiento insumo = new InsumoProcedimiento();

            System.out.println("\n--- REGISTRAR INSUMO ---");

            System.out.print("ID del procedimiento: ");
            insumo.setProcedimientoId(leerEntero());

            System.out.print("ID del producto: ");
            insumo.setProductoId(leerEntero());

            System.out.print("Cantidad utilizada: ");
            insumo.setCantidadUsada(leerEntero());

            System.out.print("Observaciones: ");
            insumo.setObservaciones(scanner.nextLine());

            boolean resultado = insumoController.registrar(insumo);

            if (resultado) {
                System.out.println("Insumo registrado correctamente.");
            } else {
                System.out.println("No se pudo registrar el insumo.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarInsumos() {

        List<InsumoProcedimiento> lista = insumoController.listarTodos();

        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay insumos registrados.");
            return;
        }

        for (InsumoProcedimiento insumo : lista) {
            mostrarInsumo(insumo);
        }
    }

    private void buscarInsumo() {

        System.out.print("Ingrese el ID del insumo: ");
        int id = leerEntero();

        InsumoProcedimiento insumo = insumoController.buscarPorId(id);

        if (insumo != null) {
            mostrarInsumo(insumo);
        } else {
            System.out.println("No se encontró el insumo.");
        }
    }

    private void actualizarInsumo() {

        System.out.print("Ingrese el ID del insumo: ");
        int id = leerEntero();

        InsumoProcedimiento insumo = insumoController.buscarPorId(id);

        if (insumo == null) {
            System.out.println("No se encontró el insumo.");
            return;
        }

        try {
            System.out.println("\n--- ACTUALIZAR INSUMO ---");

            System.out.print("ID del procedimiento [" + insumo.getProcedimientoId() + "]: ");
            String entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                insumo.setProcedimientoId(Integer.parseInt(entrada));
            }

            System.out.print("ID del producto [" + insumo.getProductoId() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                insumo.setProductoId(Integer.parseInt(entrada));
            }

            System.out.print("Cantidad utilizada [" + insumo.getCantidadUsada() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                insumo.setCantidadUsada(Integer.parseInt(entrada));
            }

            System.out.print("Observaciones [" + insumo.getObservaciones() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                insumo.setObservaciones(entrada);
            }

            boolean resultado = insumoController.actualizar(insumo);

            if (resultado) {
                System.out.println("Insumo actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el insumo.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: ingresa valores numéricos válidos.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarInsumo() {

        System.out.print("Ingrese el ID del insumo: ");
        int id = leerEntero();

        System.out.print("¿Desea eliminar este insumo? (S/N): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {

            boolean resultado = insumoController.eliminar(id);

            if (resultado) {
                System.out.println("Insumo eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el insumo.");
            }
        }
    }

    private void mostrarInsumo(InsumoProcedimiento insumo) {

        System.out.println("\n--- INSUMO ---");
        System.out.println("ID: " + insumo.getId());
        System.out.println("Procedimiento ID: " + insumo.getProcedimientoId());
        System.out.println("Producto ID: " + insumo.getProductoId());
        System.out.println("Cantidad utilizada: " + insumo.getCantidadUsada());
        System.out.println("Observaciones: " + insumo.getObservaciones());
    }

    // MÉTODO AUXILIAR
    private int leerEntero() {
        int numero = Integer.parseInt(scanner.nextLine());
        return numero;
    }
}
