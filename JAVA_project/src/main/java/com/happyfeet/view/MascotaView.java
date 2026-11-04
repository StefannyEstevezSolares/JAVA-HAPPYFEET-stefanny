
package com.happyfeet.view;

import com.happyfeet.controller.MascotaController;
import com.happyfeet.model.Mascota;
import com.happyfeet.model.MascotaBuilder;

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

    // REGISTRAR MASCOTA - PATRÓN BUILDER

    private void registrarMascota() throws SQLException {

        System.out.println("\n--- REGISTRAR MASCOTA ---");

        System.out.print("ID del dueño: ");
        int duenoId = Integer.parseInt(scanner.nextLine());

        System.out.print("Nombre de la mascota: ");
        String nombre = scanner.nextLine();

        System.out.print("ID de la raza: ");
        int razaId = Integer.parseInt(scanner.nextLine());

        MascotaBuilder builder = new MascotaBuilder(
                duenoId,
                nombre,
                razaId
        );

        System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
        String fecha = scanner.nextLine();

        if (!fecha.trim().isEmpty()) {
            builder.fechaNacimiento(LocalDate.parse(fecha));
        }

        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();

        if (!sexo.trim().isEmpty()) {
            builder.sexo(sexo);
        }

        System.out.print("Peso actual: ");
        String peso = scanner.nextLine();

        if (!peso.trim().isEmpty()) {
            builder.pesoActual(new BigDecimal(peso));
        }

        System.out.print("Microchip: ");
        String microchip = scanner.nextLine();

        if (!microchip.trim().isEmpty()) {
            builder.microchip(microchip);
        }

        System.out.print("Tatuaje: ");
        String tatuaje = scanner.nextLine();

        if (!tatuaje.trim().isEmpty()) {
            builder.tatuaje(tatuaje);
        }

        System.out.print("URL de fotografía: ");
        String urlFoto = scanner.nextLine();

        if (!urlFoto.trim().isEmpty()) {
            builder.urlFoto(urlFoto);
        }

        System.out.print("Alergias: ");
        String alergias = scanner.nextLine();

        if (!alergias.trim().isEmpty()) {
            builder.alergias(alergias);
        }

        System.out.print("Condiciones preexistentes: ");
        String condiciones = scanner.nextLine();

        if (!condiciones.trim().isEmpty()) {
            builder.condicionesPreexistentes(condiciones);
        }

        Mascota mascota = builder.build();

        int resultado = mascotaController.registrar(mascota);

        if (resultado > 0) {
            System.out.println(
                    "Mascota registrada correctamente. ID: " + resultado
            );
        } else {
            System.out.println("No fue posible registrar la mascota.");
        }
    }

    // LISTAR MASCOTAS

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

    // BUSCAR MASCOTA

    private void buscarMascota() throws SQLException {

        System.out.println("\n--- BUSCAR MASCOTA ---");

        System.out.print("Ingrese el ID de la mascota: ");
        int id = Integer.parseInt(scanner.nextLine());

        Mascota mascota = mascotaController.buscarPorId(id);

        if (mascota != null) {
            mostrarDatosMascota(mascota);
        } else {
            System.out.println("No se encontró la mascota.");
        }
    }

    // ACTUALIZAR MASCOTA

    private void actualizarMascota() throws SQLException {

        System.out.println("\n--- ACTUALIZAR MASCOTA ---");

        System.out.print("Ingrese el ID de la mascota: ");
        int id = Integer.parseInt(scanner.nextLine());

        Mascota mascota = mascotaController.buscarPorId(id);

        if (mascota == null) {
            System.out.println("No se encontró la mascota.");
            return;
        }

        System.out.println("Deje vacío el campo para conservar el dato actual.");

        System.out.print("ID del dueño [" + mascota.getDuenoId() + "]: ");
        String dueno = scanner.nextLine();

        if (!dueno.trim().isEmpty()) {
            mascota.setDuenoId(Integer.parseInt(dueno));
        }

        System.out.print("Nombre [" + mascota.getNombre() + "]: ");
        String nombre = scanner.nextLine();

        if (!nombre.trim().isEmpty()) {
            mascota.setNombre(nombre);
        }

        System.out.print("ID de la raza [" + mascota.getRazaId() + "]: ");
        String raza = scanner.nextLine();

        if (!raza.trim().isEmpty()) {
            mascota.setRazaId(Integer.parseInt(raza));
        }

        System.out.print("Fecha de nacimiento [" + mascota.getFechaNacimiento() + "]: ");
        String fecha = scanner.nextLine();

        if (!fecha.trim().isEmpty()) {
            mascota.setFechaNacimiento(LocalDate.parse(fecha));
        }

        System.out.print("Sexo [" + mascota.getSexo() + "]: ");
        String sexo = scanner.nextLine();

        if (!sexo.trim().isEmpty()) {
            mascota.setSexo(sexo);
        }

        System.out.print("Peso actual [" + mascota.getPesoActual() + "]: ");
        String peso = scanner.nextLine();

        if (!peso.trim().isEmpty()) {
            mascota.setPesoActual(new BigDecimal(peso));
        }

        System.out.print("Microchip [" + mascota.getMicrochip() + "]: ");
        String microchip = scanner.nextLine();

        if (!microchip.trim().isEmpty()) {
            mascota.setMicrochip(microchip);
        }

        System.out.print("Tatuaje [" + mascota.getTatuaje() + "]: ");
        String tatuaje = scanner.nextLine();

        if (!tatuaje.trim().isEmpty()) {
            mascota.setTatuaje(tatuaje);
        }

        System.out.print("URL de fotografía [" + mascota.getUrlFoto() + "]: ");
        String url = scanner.nextLine();

        if (!url.trim().isEmpty()) {
            mascota.setUrlFoto(url);
        }

        System.out.print("Alergias [" + mascota.getAlergias() + "]: ");
        String alergias = scanner.nextLine();

        if (!alergias.trim().isEmpty()) {
            mascota.setAlergias(alergias);
        }

        System.out.print("Condiciones preexistentes [" +
                mascota.getCondicionesPreexistentes() + "]: ");
        String condiciones = scanner.nextLine();

        if (!condiciones.trim().isEmpty()) {
            mascota.setCondicionesPreexistentes(condiciones);
        }

        boolean actualizado = mascotaController.actualizar(mascota);

        if (actualizado) {
            System.out.println("Mascota actualizada correctamente.");
        } else {
            System.out.println("No fue posible actualizar la mascota.");
        }
    }

    // DESACTIVAR MASCOTA

    private void desactivarMascota() throws SQLException {

        System.out.println("\n--- DESACTIVAR MASCOTA ---");

        System.out.print("Ingrese el ID de la mascota: ");
        int id = Integer.parseInt(scanner.nextLine());

        Mascota mascota = mascotaController.buscarPorId(id);

        if (mascota == null) {
            System.out.println("No se encontró la mascota.");
            return;
        }

        System.out.print("¿Desea desactivar a " +
                mascota.getNombre() + "? (S/N): ");

        String confirmacion = scanner.nextLine();

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

    // INICIO: PATRÓN PROTOTYPE

    private void clonarMascota() throws SQLException {

        System.out.println("\n--- CLONAR MASCOTA ---");

        System.out.print("Ingrese el ID de la mascota que desea clonar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Mascota original = mascotaController.buscarPorId(id);

        if (original == null) {
            System.out.println("No se encontró la mascota.");
            return;
        }

        System.out.println("\nMascota original:");
        mostrarDatosMascota(original);

        // Crear una copia de la mascota original
        Mascota copia = original.clonar();

        System.out.print("Ingrese el nuevo nombre de la mascota: ");
        String nuevoNombre = scanner.nextLine();

        if (nuevoNombre.trim().isEmpty()) {
            System.out.println("El nombre de la nueva mascota es obligatorio.");
            return;
        }

        copia.setNombre(nuevoNombre);

        System.out.print("Ingrese un nuevo microchip (opcional): ");
        String nuevoMicrochip = scanner.nextLine();

        if (nuevoMicrochip.trim().isEmpty()) {
            copia.setMicrochip(null);
        } else {
            copia.setMicrochip(nuevoMicrochip);
        }

        // Registrar la nueva mascota
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

    // FIN: PATRÓN PROTOTYPE

    // MOSTRAR DATOS DE MASCOTA

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
