
package com.happyfeet.view;

import com.happyfeet.controller.InventarioController;
import com.happyfeet.controller.ProductoTipoController;
import com.happyfeet.controller.ProveedorController;
import com.happyfeet.controller.MovimientoInventarioController;

import com.happyfeet.model.Inventario;
import com.happyfeet.model.ProductoTipo;
import com.happyfeet.model.Proveedor;
import com.happyfeet.model.MovimientoInventario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class InventarioView {

    private final Scanner scanner;
    private final InventarioController inventarioController;
    private final ProductoTipoController tipoController;
    private final ProveedorController proveedorController;
    private final MovimientoInventarioController movimientoController;

    public InventarioView() {
        scanner = new Scanner(System.in);
        inventarioController = new InventarioController();
        tipoController = new ProductoTipoController();
        proveedorController = new ProveedorController();
        movimientoController = new MovimientoInventarioController();
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE INVENTARIO =====");
            System.out.println("1. Administrar productos");
            System.out.println("2. Administrar tipos de producto");
            System.out.println("3. Administrar proveedores");
            System.out.println("4. Administrar movimientos de inventario");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    menuInventario();
                    break;
                case 2:
                    menuTipos();
                    break;
                case 3:
                    menuProveedores();
                    break;
                case 4:
                    menuMovimientos();
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
    // INVENTARIO
    // ==========================================

    private void menuInventario() {

        int opcion;

        do {
            System.out.println("\n===== PRODUCTOS DE INVENTARIO =====");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 5:
                    eliminarProducto();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void registrarProducto() {

        try {
            Inventario producto = new Inventario();

            System.out.println("\n--- REGISTRAR PRODUCTO ---");

            System.out.print("Nombre del producto: ");
            producto.setNombreProducto(scanner.nextLine());

            System.out.print("ID del tipo de producto: ");
            producto.setProductoTipoId(leerEntero());

            System.out.print("Descripción: ");
            producto.setDescripcion(scanner.nextLine());

            System.out.print("Fabricante: ");
            producto.setFabricante(scanner.nextLine());

            producto.setProveedorId(leerIdOpcional("ID del proveedor (opcional): "));

            System.out.print("Lote: ");
            producto.setLote(scanner.nextLine());

            System.out.print("Cantidad en stock: ");
            producto.setCantidadStock(leerEntero());

            System.out.print("Stock mínimo: ");
            producto.setStockMinimo(leerEntero());

            System.out.print("Unidad de medida: ");
            producto.setUnidadMedida(scanner.nextLine());

            System.out.print("Fecha de vencimiento (AAAA-MM-DD, opcional): ");
            String fecha = scanner.nextLine();
            producto.setFechaVencimiento(
                    fecha.isBlank() ? null : LocalDate.parse(fecha)
            );

            System.out.print("Precio de compra: ");
            producto.setPrecioCompra(new BigDecimal(scanner.nextLine()));

            System.out.print("Precio de venta: ");
            producto.setPrecioVenta(new BigDecimal(scanner.nextLine()));

            producto.setRequiereReceta(
                    leerBooleano("¿Requiere receta? (S/N): ")
            );

            producto.setActivo(true);

            boolean resultado = inventarioController.registrar(producto);

            if (resultado) {
                System.out.println("Producto registrado correctamente.");
            } else {
                System.out.println("No se pudo registrar el producto.");
            }

        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("Error: verifica los números y las fechas.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarProductos() {

        List<Inventario> lista = inventarioController.listarTodos();

        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Inventario producto : lista) {
            mostrarProducto(producto);
        }
    }

    private void buscarProducto() {

        System.out.print("Ingrese el ID del producto: ");
        int id = leerEntero();

        Inventario producto = inventarioController.buscarPorId(id);

        if (producto != null) {
            mostrarProducto(producto);
        } else {
            System.out.println("No se encontró el producto.");
        }
    }

    private void actualizarProducto() {

        System.out.print("Ingrese el ID del producto: ");
        int id = leerEntero();

        Inventario producto = inventarioController.buscarPorId(id);

        if (producto == null) {
            System.out.println("No se encontró el producto.");
            return;
        }

        try {
            System.out.println("\n--- ACTUALIZAR PRODUCTO ---");
            System.out.println("Deja vacío un campo para conservar su valor actual.");

            System.out.print("Nombre [" + producto.getNombreProducto() + "]: ");
            String entrada = scanner.nextLine();
            if (!entrada.isBlank()) producto.setNombreProducto(entrada);

            System.out.print("Tipo de producto ID [" + producto.getProductoTipoId() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) producto.setProductoTipoId(Integer.parseInt(entrada));

            System.out.print("Descripción [" + producto.getDescripcion() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) producto.setDescripcion(entrada);

            System.out.print("Fabricante [" + producto.getFabricante() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) producto.setFabricante(entrada);

            System.out.print("Proveedor ID [" + producto.getProveedorId() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) producto.setProveedorId(Integer.parseInt(entrada));

            System.out.print("Lote [" + producto.getLote() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) producto.setLote(entrada);

            System.out.print("Cantidad en stock [" + producto.getCantidadStock() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) producto.setCantidadStock(Integer.parseInt(entrada));

            System.out.print("Stock mínimo [" + producto.getStockMinimo() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) producto.setStockMinimo(Integer.parseInt(entrada));

            System.out.print("Unidad de medida [" + producto.getUnidadMedida() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) producto.setUnidadMedida(entrada);

            System.out.print("Fecha de vencimiento [" + producto.getFechaVencimiento() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) producto.setFechaVencimiento(LocalDate.parse(entrada));

            System.out.print("Precio de compra [" + producto.getPrecioCompra() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) producto.setPrecioCompra(new BigDecimal(entrada));

            System.out.print("Precio de venta [" + producto.getPrecioVenta() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) producto.setPrecioVenta(new BigDecimal(entrada));

            System.out.print("¿Requiere receta? (S/N, vacío conserva): ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                producto.setRequiereReceta(entrada.equalsIgnoreCase("S"));
            }

            System.out.print("¿Está activo? (S/N, vacío conserva): ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                producto.setActivo(entrada.equalsIgnoreCase("S"));
            }

            boolean resultado = inventarioController.actualizar(producto);

            if (resultado) {
                System.out.println("Producto actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el producto.");
            }

        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("Error: verifica los números y las fechas.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarProducto() {

        System.out.print("Ingrese el ID del producto: ");
        int id = leerEntero();

        if (confirmarEliminacion()) {
            boolean resultado = inventarioController.eliminar(id);

            System.out.println(resultado
                    ? "Producto eliminado correctamente."
                    : "No se pudo eliminar el producto.");
        }
    }

    private void mostrarProducto(Inventario p) {

        System.out.println("\n--- PRODUCTO ---");
        System.out.println("ID: " + p.getId());
        System.out.println("Nombre: " + p.getNombreProducto());
        System.out.println("Tipo ID: " + p.getProductoTipoId());
        System.out.println("Descripción: " + p.getDescripcion());
        System.out.println("Fabricante: " + p.getFabricante());
        System.out.println("Proveedor ID: " + p.getProveedorId());
        System.out.println("Lote: " + p.getLote());
        System.out.println("Stock: " + p.getCantidadStock());
        System.out.println("Stock mínimo: " + p.getStockMinimo());
        System.out.println("Unidad: " + p.getUnidadMedida());
        System.out.println("Vencimiento: " + p.getFechaVencimiento());
        System.out.println("Precio compra: " + p.getPrecioCompra());
        System.out.println("Precio venta: " + p.getPrecioVenta());
        System.out.println("Requiere receta: " + p.getRequiereReceta());
        System.out.println("Activo: " + p.getActivo());
        System.out.println("Fecha de registro: " + p.getFechaRegistro());
    }

    // ==========================================
    // TIPOS DE PRODUCTO
    // ==========================================

    private void menuTipos() {

        int opcion;

        do {
            System.out.println("\n===== TIPOS DE PRODUCTO =====");
            System.out.println("1. Registrar tipo");
            System.out.println("2. Listar tipos");
            System.out.println("3. Buscar tipo por ID");
            System.out.println("4. Actualizar tipo");
            System.out.println("5. Eliminar tipo");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarTipo();
                    break;
                case 2:
                    listarTipos();
                    break;
                case 3:
                    buscarTipo();
                    break;
                case 4:
                    actualizarTipo();
                    break;
                case 5:
                    eliminarTipo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void registrarTipo() {

        ProductoTipo tipo = new ProductoTipo();

        System.out.println("\n--- REGISTRAR TIPO ---");

        System.out.print("Nombre: ");
        tipo.setNombre(scanner.nextLine());

        System.out.print("Descripción: ");
        tipo.setDescripcion(scanner.nextLine());

        boolean resultado = tipoController.registrar(tipo);

        System.out.println(resultado
                ? "Tipo registrado correctamente."
                : "No se pudo registrar el tipo.");
    }

    private void listarTipos() {

        List<ProductoTipo> lista = tipoController.listarTodos();

        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay tipos registrados.");
            return;
        }

        for (ProductoTipo tipo : lista) {
            mostrarTipo(tipo);
        }
    }

    private void buscarTipo() {

        System.out.print("Ingrese el ID del tipo: ");
        int id = leerEntero();

        ProductoTipo tipo = tipoController.buscarPorId(id);

        if (tipo != null) {
            mostrarTipo(tipo);
        } else {
            System.out.println("No se encontró el tipo.");
        }
    }

    private void actualizarTipo() {

        System.out.print("Ingrese el ID del tipo: ");
        int id = leerEntero();

        ProductoTipo tipo = tipoController.buscarPorId(id);

        if (tipo == null) {
            System.out.println("No se encontró el tipo.");
            return;
        }

        System.out.print("Nombre [" + tipo.getNombre() + "]: ");
        String entrada = scanner.nextLine();
        if (!entrada.isBlank()) tipo.setNombre(entrada);

        System.out.print("Descripción [" + tipo.getDescripcion() + "]: ");
        entrada = scanner.nextLine();
        if (!entrada.isBlank()) tipo.setDescripcion(entrada);

        boolean resultado = tipoController.actualizar(tipo);

        System.out.println(resultado
                ? "Tipo actualizado correctamente."
                : "No se pudo actualizar el tipo.");
    }

    private void eliminarTipo() {

        System.out.print("Ingrese el ID del tipo: ");
        int id = leerEntero();

        if (confirmarEliminacion()) {
            boolean resultado = tipoController.eliminar(id);

            System.out.println(resultado
                    ? "Tipo eliminado correctamente."
                    : "No se pudo eliminar el tipo.");
        }
    }

    private void mostrarTipo(ProductoTipo tipo) {

        System.out.println("\n--- TIPO DE PRODUCTO ---");
        System.out.println("ID: " + tipo.getId());
        System.out.println("Nombre: " + tipo.getNombre());
        System.out.println("Descripción: " + tipo.getDescripcion());
    }

    // ==========================================
    // PROVEEDORES
    // ==========================================

    private void menuProveedores() {

        int opcion;

        do {
            System.out.println("\n===== PROVEEDORES =====");
            System.out.println("1. Registrar proveedor");
            System.out.println("2. Listar proveedores");
            System.out.println("3. Buscar proveedor por ID");
            System.out.println("4. Actualizar proveedor");
            System.out.println("5. Eliminar proveedor");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarProveedor();
                    break;
                case 2:
                    listarProveedores();
                    break;
                case 3:
                    buscarProveedor();
                    break;
                case 4:
                    actualizarProveedor();
                    break;
                case 5:
                    eliminarProveedor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void registrarProveedor() {

        Proveedor proveedor = new Proveedor();

        System.out.println("\n--- REGISTRAR PROVEEDOR ---");

        System.out.print("Nombre de la empresa: ");
        proveedor.setNombreEmpresa(scanner.nextLine());

        System.out.print("Contacto: ");
        proveedor.setContacto(scanner.nextLine());

        System.out.print("Teléfono: ");
        proveedor.setTelefono(scanner.nextLine());

        System.out.print("Correo electrónico: ");
        proveedor.setEmail(scanner.nextLine());

        System.out.print("Dirección: ");
        proveedor.setDireccion(scanner.nextLine());

        System.out.print("Sitio web: ");
        proveedor.setSitioWeb(scanner.nextLine());

        proveedor.setActivo(true);

        boolean resultado = proveedorController.registrar(proveedor);

        System.out.println(resultado
                ? "Proveedor registrado correctamente."
                : "No se pudo registrar el proveedor.");
    }

    private void listarProveedores() {

        List<Proveedor> lista = proveedorController.listarTodos();

        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
            return;
        }

        for (Proveedor proveedor : lista) {
            mostrarProveedor(proveedor);
        }
    }

    private void buscarProveedor() {

        System.out.print("Ingrese el ID del proveedor: ");
        int id = leerEntero();

        Proveedor proveedor = proveedorController.buscarPorId(id);

        if (proveedor != null) {
            mostrarProveedor(proveedor);
        } else {
            System.out.println("No se encontró el proveedor.");
        }
    }

    private void actualizarProveedor() {

        System.out.print("Ingrese el ID del proveedor: ");
        int id = leerEntero();

        Proveedor proveedor = proveedorController.buscarPorId(id);

        if (proveedor == null) {
            System.out.println("No se encontró el proveedor.");
            return;
        }

        System.out.print("Empresa [" + proveedor.getNombreEmpresa() + "]: ");
        String entrada = scanner.nextLine();
        if (!entrada.isBlank()) proveedor.setNombreEmpresa(entrada);

        System.out.print("Contacto [" + proveedor.getContacto() + "]: ");
        entrada = scanner.nextLine();
        if (!entrada.isBlank()) proveedor.setContacto(entrada);

        System.out.print("Teléfono [" + proveedor.getTelefono() + "]: ");
        entrada = scanner.nextLine();
        if (!entrada.isBlank()) proveedor.setTelefono(entrada);

        System.out.print("Correo [" + proveedor.getEmail() + "]: ");
        entrada = scanner.nextLine();
        if (!entrada.isBlank()) proveedor.setEmail(entrada);

        System.out.print("Dirección [" + proveedor.getDireccion() + "]: ");
        entrada = scanner.nextLine();
        if (!entrada.isBlank()) proveedor.setDireccion(entrada);

        System.out.print("Sitio web [" + proveedor.getSitioWeb() + "]: ");
        entrada = scanner.nextLine();
        if (!entrada.isBlank()) proveedor.setSitioWeb(entrada);

        System.out.print("¿Está activo? (S/N, vacío conserva): ");
        entrada = scanner.nextLine();
        if (!entrada.isBlank()) {
            proveedor.setActivo(entrada.equalsIgnoreCase("S"));
        }

        boolean resultado = proveedorController.actualizar(proveedor);

        System.out.println(resultado
                ? "Proveedor actualizado correctamente."
                : "No se pudo actualizar el proveedor.");
    }

    private void eliminarProveedor() {

        System.out.print("Ingrese el ID del proveedor: ");
        int id = leerEntero();

        if (confirmarEliminacion()) {
            boolean resultado = proveedorController.eliminar(id);

            System.out.println(resultado
                    ? "Proveedor eliminado correctamente."
                    : "No se pudo eliminar el proveedor.");
        }
    }

    private void mostrarProveedor(Proveedor p) {

        System.out.println("\n--- PROVEEDOR ---");
        System.out.println("ID: " + p.getId());
        System.out.println("Empresa: " + p.getNombreEmpresa());
        System.out.println("Contacto: " + p.getContacto());
        System.out.println("Teléfono: " + p.getTelefono());
        System.out.println("Correo: " + p.getEmail());
        System.out.println("Dirección: " + p.getDireccion());
        System.out.println("Sitio web: " + p.getSitioWeb());
        System.out.println("Activo: " + p.isActivo());
        System.out.println("Fecha de registro: " + p.getFechaRegistro());
    }

    // ==========================================
    // MOVIMIENTOS DE INVENTARIO
    // ==========================================

    private void menuMovimientos() {

        int opcion;

        do {
            System.out.println("\n===== MOVIMIENTOS DE INVENTARIO =====");
            System.out.println("1. Registrar movimiento");
            System.out.println("2. Listar movimientos");
            System.out.println("3. Buscar movimiento por ID");
            System.out.println("4. Actualizar movimiento");
            System.out.println("5. Eliminar movimiento");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarMovimiento();
                    break;
                case 2:
                    listarMovimientos();
                    break;
                case 3:
                    buscarMovimiento();
                    break;
                case 4:
                    actualizarMovimiento();
                    break;
                case 5:
                    eliminarMovimiento();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void registrarMovimiento() {

        MovimientoInventario movimiento = new MovimientoInventario();

        System.out.println("\n--- REGISTRAR MOVIMIENTO ---");

        System.out.print("ID del producto: ");
        movimiento.setProductoId(leerEntero());

        System.out.println("Tipo de movimiento:");
        System.out.println("Entrada");
        System.out.println("Salida");
        System.out.println("Ajuste");
        System.out.println("Vencimiento");
        System.out.print("Seleccione el tipo: ");
        movimiento.setTipoMovimiento(scanner.nextLine());

        System.out.print("Cantidad: ");
        movimiento.setCantidad(leerEntero());

        System.out.print("Stock anterior: ");
        movimiento.setStockAnterior(leerEntero());

        System.out.print("Stock nuevo: ");
        movimiento.setStockNuevo(leerEntero());

        System.out.print("Motivo: ");
        movimiento.setMotivo(scanner.nextLine());

        movimiento.setReferenciaConsultaId(
                leerIdOpcional("ID de consulta (opcional): ")
        );

        movimiento.setReferenciaProcedimientoId(
                leerIdOpcional("ID de procedimiento (opcional): ")
        );

        System.out.print("Usuario responsable: ");
        movimiento.setUsuario(scanner.nextLine());

        boolean resultado = movimientoController.registrar(movimiento);

        System.out.println(resultado
                ? "Movimiento registrado correctamente."
                : "No se pudo registrar el movimiento.");
    }

    private void listarMovimientos() {

        List<MovimientoInventario> lista = movimientoController.listarTodos();

        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay movimientos registrados.");
            return;
        }

        for (MovimientoInventario movimiento : lista) {
            mostrarMovimiento(movimiento);
        }
    }

    private void buscarMovimiento() {

        System.out.print("Ingrese el ID del movimiento: ");
        int id = leerEntero();

        MovimientoInventario movimiento = movimientoController.buscarPorId(id);

        if (movimiento != null) {
            mostrarMovimiento(movimiento);
        } else {
            System.out.println("No se encontró el movimiento.");
        }
    }

    private void actualizarMovimiento() {

        System.out.print("Ingrese el ID del movimiento: ");
        int id = leerEntero();

        MovimientoInventario movimiento = movimientoController.buscarPorId(id);

        if (movimiento == null) {
            System.out.println("No se encontró el movimiento.");
            return;
        }

        try {
            System.out.print("Producto ID [" + movimiento.getProductoId() + "]: ");
            String entrada = scanner.nextLine();
            if (!entrada.isBlank()) movimiento.setProductoId(Integer.parseInt(entrada));

            System.out.print("Tipo [" + movimiento.getTipoMovimiento() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) movimiento.setTipoMovimiento(entrada);

            System.out.print("Cantidad [" + movimiento.getCantidad() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) movimiento.setCantidad(Integer.parseInt(entrada));

            System.out.print("Stock anterior [" + movimiento.getStockAnterior() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) movimiento.setStockAnterior(Integer.parseInt(entrada));

            System.out.print("Stock nuevo [" + movimiento.getStockNuevo() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) movimiento.setStockNuevo(Integer.parseInt(entrada));

            System.out.print("Motivo [" + movimiento.getMotivo() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) movimiento.setMotivo(entrada);

            System.out.print("Consulta ID [" + movimiento.getReferenciaConsultaId() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) movimiento.setReferenciaConsultaId(Integer.parseInt(entrada));

            System.out.print("Procedimiento ID [" + movimiento.getReferenciaProcedimientoId() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) movimiento.setReferenciaProcedimientoId(Integer.parseInt(entrada));

            System.out.print("Usuario [" + movimiento.getUsuario() + "]: ");
            entrada = scanner.nextLine();
            if (!entrada.isBlank()) movimiento.setUsuario(entrada);

            boolean resultado = movimientoController.actualizar(movimiento);

            System.out.println(resultado
                    ? "Movimiento actualizado correctamente."
                    : "No se pudo actualizar el movimiento.");

        } catch (NumberFormatException e) {
            System.out.println("Error: ingresa valores numéricos válidos.");
        }
    }

    private void eliminarMovimiento() {

        System.out.print("Ingrese el ID del movimiento: ");
        int id = leerEntero();

        if (confirmarEliminacion()) {
            boolean resultado = movimientoController.eliminar(id);

            System.out.println(resultado
                    ? "Movimiento eliminado correctamente."
                    : "No se pudo eliminar el movimiento.");
        }
    }

    private void mostrarMovimiento(MovimientoInventario m) {

        System.out.println("\n--- MOVIMIENTO ---");
        System.out.println("ID: " + m.getId());
        System.out.println("Producto ID: " + m.getProductoId());
        System.out.println("Tipo: " + m.getTipoMovimiento());
        System.out.println("Cantidad: " + m.getCantidad());
        System.out.println("Stock anterior: " + m.getStockAnterior());
        System.out.println("Stock nuevo: " + m.getStockNuevo());
        System.out.println("Motivo: " + m.getMotivo());
        System.out.println("Consulta ID: " + m.getReferenciaConsultaId());
        System.out.println("Procedimiento ID: " + m.getReferenciaProcedimientoId());
        System.out.println("Usuario: " + m.getUsuario());
        System.out.println("Fecha: " + m.getFechaMovimiento());
    }

    // ==========================================
    // MÉTODOS AUXILIARES
    // ==========================================

    private int leerEntero() {
        return Integer.parseInt(scanner.nextLine());
    }

    private Integer leerIdOpcional(String mensaje) {

        System.out.print(mensaje);
        String entrada = scanner.nextLine();

        return entrada.isBlank() ? null : Integer.parseInt(entrada);
    }

    private Boolean leerBooleano(String mensaje) {

        System.out.print(mensaje);
        String respuesta = scanner.nextLine();

        return respuesta.equalsIgnoreCase("S");
    }

    private boolean confirmarEliminacion() {

        System.out.print("¿Confirma la eliminación? (S/N): ");
        return scanner.nextLine().equalsIgnoreCase("S");
    }
}
