
package com.happyfeet.view;

import com.happyfeet.controller.MascotaController;
import com.happyfeet.model.Mascota;
import com.happyfeet.model.MascotaBuilder;
import com.happyfeet.util.ValidacionTexto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MascotaView {

    private final MascotaController mascotaController;
    private final Scanner scanner;

    public MascotaView() {
        this.mascotaController = new MascotaController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE MASCOTAS ---");
            System.out.println("1. Registrar mascota");
            System.out.println("2. Listar mascotas");
            System.out.println("3. Buscar mascota por ID");
            System.out.println("4. Actualizar mascota");
            System.out.println("5. Desactivar mascota");
            System.out.println("6. Clonar mascota");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1:
                        registrarMascota();
                        break;

                    case 2:
                        listarMascotas();
                        break;

                    case 3:
                        buscarMascota();
                        break;

                    case 4:
                        actualizarMascota();
                        break;

                    case 5:
                        desactivarMascota();
                        break;

                    case 6:
                        clonarMascota();
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

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                opcion = -1;

            } catch (SQLException e) {
                System.out.println("Error de base de datos: " + e.getMessage());
                opcion = -1;
            }

        } while (opcion != 0);
    }

    private void registrarMascota() throws SQLException {

        System.out.println("\n--- REGISTRAR MASCOTA ---");

        System.out.print("ID del dueño: ");
        String duenoTexto = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(duenoTexto, "ID del dueño")) {
            return;
        }

        int duenoId = Integer.parseInt(ValidacionTexto.limpiarTexto(duenoTexto));

        System.out.print("Nombre de la mascota: ");
        String nombre = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(nombre, "Nombre de la mascota")) {
            return;
        }

        nombre = ValidacionTexto.limpiarTexto(nombre);

        System.out.print("ID de la raza: ");
        String razaTexto = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(razaTexto, "ID de la raza")) {
            return;
        }

        int razaId = Integer.parseInt(ValidacionTexto.limpiarTexto(razaTexto));

        MascotaBuilder builder = new MascotaBuilder(
                duenoId,
                nombre,
                razaId
        );

        System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
        String fecha = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!fecha.isEmpty()) {
            builder.fechaNacimiento(LocalDate.parse(fecha));
        }

        System.out.print("Sexo: ");
        String sexo = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!sexo.isEmpty()) {
            builder.sexo(sexo);
        }

        System.out.print("Peso actual: ");
        String peso = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!peso.isEmpty()) {
            builder.pesoActual(new BigDecimal(peso));
        }

        System.out.print("Microchip: ");
        String microchip = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!microchip.isEmpty()) {
            builder.microchip(microchip);
        }

        System.out.print("Tatuaje: ");
        String tatuaje = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!tatuaje.isEmpty()) {
            builder.tatuaje(tatuaje);
        }

        System.out.print("URL de fotografía: ");
        String urlFoto = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!urlFoto.isEmpty()) {
            builder.urlFoto(urlFoto);
        }

        System.out.print("Alergias: ");
        String alergias = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!alergias.isEmpty()) {
            builder.alergias(alergias);
        }

        System.out.print("Condiciones preexistentes: ");
        String condiciones = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!condiciones.isEmpty()) {
            builder.condicionesPreexistentes(condiciones);
        }

        Mascota mascota = builder.build();

        int resultado = mascotaController.registrar(mascota);

        if (resultado > 0) {
            System.out.println("Mascota registrada correctamente. ID: " + resultado);
        } else {
            System.out.println("No fue posible registrar la mascota.");
        }
    }

    private void listarMascotas() throws SQLException {

        System.out.println("\n--- LISTA DE MASCOTAS ---");

        List<Mascota> mascotas = mascotaController.listarTodos();

        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
            return;
        }

        for (Mascota mascota : mascotas) {
            mostrarDatosMascota(mascota);
        }
    }

    private void buscarMascota() throws SQLException {

        System.out.println("\n--- BUSCAR MASCOTA ---");

        System.out.print("Ingrese el ID de la mascota: ");
        String idTexto = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(idTexto, "ID de la mascota")) {
            return;
        }

        int id = Integer.parseInt(ValidacionTexto.limpiarTexto(idTexto));

        Mascota mascota = mascotaController.buscarPorId(id);

        if (mascota != null) {
            mostrarDatosMascota(mascota);
        } else {
            System.out.println("No se encontró la mascota.");
        }
    }

    private void actualizarMascota() throws SQLException {

        System.out.println("\n--- ACTUALIZAR MASCOTA ---");

        System.out.print("Ingrese el ID de la mascota: ");
        String idTexto = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(idTexto, "ID de la mascota")) {
            return;
        }

        int id = Integer.parseInt(ValidacionTexto.limpiarTexto(idTexto));

        Mascota mascota = mascotaController.buscarPorId(id);

        if (mascota == null) {
            System.out.println("No se encontró la mascota.");
            return;
        }

        System.out.println("Deje vacío el campo para conservar el dato actual.");

        System.out.print("ID del dueño [" + mascota.getDuenoId() + "]: ");
        String dueno = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!dueno.isEmpty()) {
            mascota.setDuenoId(Integer.parseInt(dueno));
        }

        System.out.print("Nombre [" + mascota.getNombre() + "]: ");
        String nombre = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!nombre.isEmpty()) {
            mascota.setNombre(nombre);
        }

        System.out.print("ID de la raza [" + mascota.getRazaId() + "]: ");
        String raza = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!raza.isEmpty()) {
            mascota.setRazaId(Integer.parseInt(raza));
        }

        System.out.print("Fecha de nacimiento [" + mascota.getFechaNacimiento() + "]: ");
        String fecha = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!fecha.isEmpty()) {
            mascota.setFechaNacimiento(LocalDate.parse(fecha));
        }

        System.out.print("Sexo [" + mascota.getSexo() + "]: ");
        String sexo = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!sexo.isEmpty()) {
            mascota.setSexo(sexo);
        }

        System.out.print("Peso actual [" + mascota.getPesoActual() + "]: ");
        String peso = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!peso.isEmpty()) {
            mascota.setPesoActual(new BigDecimal(peso));
        }

        System.out.print("Microchip [" + mascota.getMicrochip() + "]: ");
        String microchip = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!microchip.isEmpty()) {
            mascota.setMicrochip(microchip);
        }

        System.out.print("Tatuaje [" + mascota.getTatuaje() + "]: ");
        String tatuaje = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!tatuaje.isEmpty()) {
            mascota.setTatuaje(tatuaje);
        }

        System.out.print("URL de fotografía [" + mascota.getUrlFoto() + "]: ");
        String url = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!url.isEmpty()) {
            mascota.setUrlFoto(url);
        }

        System.out.print("Alergias [" + mascota.getAlergias() + "]: ");
        String alergias = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!alergias.isEmpty()) {
            mascota.setAlergias(alergias);
        }

        System.out.print("Condiciones preexistentes [" +
                mascota.getCondicionesPreexistentes() + "]: ");
        String condiciones = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (!condiciones.isEmpty()) {
            mascota.setCondicionesPreexistentes(condiciones);
        }

        boolean actualizado = mascotaController.actualizar(mascota);

        if (actualizado) {
            System.out.println("Mascota actualizada correctamente.");
        } else {
            System.out.println("No fue posible actualizar la mascota.");
        }
    }

    private void desactivarMascota() throws SQLException {

        System.out.println("\n--- DESACTIVAR MASCOTA ---");

        System.out.print("Ingrese el ID de la mascota: ");
        String idTexto = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(idTexto, "ID de la mascota")) {
            return;
        }

        int id = Integer.parseInt(ValidacionTexto.limpiarTexto(idTexto));

        Mascota mascota = mascotaController.buscarPorId(id);

        if (mascota == null) {
            System.out.println("No se encontró la mascota.");
            return;
        }

        System.out.print("¿Desea desactivar a " +
                mascota.getNombre() + "? (S/N): ");

        String confirmacion = scanner.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {

            boolean resultado = mascotaController.desactivar(id);

            if (resultado) {
                System.out.println("Mascota desactivada correctamente.");
            } else {
                System.out.println("No fue posible desactivar la mascota.");
            }

        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private void clonarMascota() throws SQLException {

        System.out.println("\n--- CLONAR MASCOTA ---");

        System.out.print("Ingrese el ID de la mascota que desea clonar: ");
        String idTexto = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(idTexto, "ID de la mascota")) {
            return;
        }

        int id = Integer.parseInt(ValidacionTexto.limpiarTexto(idTexto));

        Mascota original = mascotaController.buscarPorId(id);

        if (original == null) {
            System.out.println("No se encontró la mascota.");
            return;
        }

        System.out.println("\nMascota original:");
        mostrarDatosMascota(original);

        Mascota copia = original.clonar();

        System.out.print("Ingrese el nuevo nombre de la mascota: ");
        String nuevoNombre = scanner.nextLine();

        if (!ValidacionTexto.validarCampoObligatorio(nuevoNombre, "Nombre de la nueva mascota")) {
            return;
        }

        copia.setNombre(ValidacionTexto.limpiarTexto(nuevoNombre));

        System.out.print("Ingrese un nuevo microchip (opcional): ");
        String nuevoMicrochip = ValidacionTexto.limpiarTexto(scanner.nextLine());

        if (nuevoMicrochip.isEmpty()) {
            copia.setMicrochip(null);
        } else {
            copia.setMicrochip(nuevoMicrochip);
        }

        int resultado = mascotaController.registrar(copia);

        if (resultado > 0) {
            System.out.println("\n¡Mascota clonada correctamente!");
            System.out.println("Nuevo ID: " + resultado);
            System.out.println("Nombre: " + copia.getNombre());
            System.out.println("Microchip: " + copia.getMicrochip());
        } else {
            System.out.println("No fue posible clonar la mascota.");
        }
    }

    private void mostrarDatosMascota(Mascota mascota) {

        System.out.println("\nID: " + mascota.getId());
        System.out.println("Dueño ID: " + mascota.getDuenoId());
        System.out.println("Nombre: " + mascota.getNombre());
        System.out.println("Raza ID: " + mascota.getRazaId());
        System.out.println("Fecha de nacimiento: " + mascota.getFechaNacimiento());
        System.out.println("Sexo: " + mascota.getSexo());
        System.out.println("Peso actual: " + mascota.getPesoActual());
        System.out.println("Microchip: " + mascota.getMicrochip());
        System.out.println("Tatuaje: " + mascota.getTatuaje());
        System.out.println("Fotografía: " + mascota.getUrlFoto());
        System.out.println("Alergias: " + mascota.getAlergias());
        System.out.println("Condiciones preexistentes: " +
                mascota.getCondicionesPreexistentes());
        System.out.println("Fecha de registro: " + mascota.getFechaRegistro());
        System.out.println("Estado: " + (mascota.isActivo() ? "Activo" : "Inactivo"));
        System.out.println("--------------------------------");
    }
}
