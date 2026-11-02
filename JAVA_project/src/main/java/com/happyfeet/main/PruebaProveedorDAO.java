
package com.happyfeet.main;

import com.happyfeet.dao.ProveedorDAO;
import com.happyfeet.model.Proveedor;
import java.util.List;
import javax.swing.JOptionPane;

public class PruebaProveedorDAO {

    public static void main(String[] args) {

        ProveedorDAO dao = new ProveedorDAO();

        // Crear proveedor de prueba
        Proveedor proveedor = new Proveedor();

        proveedor.setNombreEmpresa("Proveedor de Prueba Happy Feet");
        proveedor.setContacto("Carlos Ramirez");
        proveedor.setTelefono("5555-1234");
        proveedor.setEmail("prueba@happyfeet.com");
        proveedor.setDireccion("Ciudad de Guatemala");
        proveedor.setSitioWeb("https://www.ejemplo.com");
        proveedor.setActivo(true);

        // Registrar
        System.out.println("REGISTRAR PROVEEDOR");

        if (!dao.registrar(proveedor)) {
            System.out.println("No se pudo registrar el proveedor.");
            return;
        }

        System.out.println("Proveedor registrado correctamente.");

        // Obtener el registro más reciente
        List<Proveedor> proveedores = dao.listarTodos();

        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
            return;
        }

        Proveedor registrado = proveedores.get(0);
        int id = registrado.getId();

        System.out.println("ID registrado: " + id);

        // Buscar
        System.out.println("\nBUSCAR PROVEEDOR");

        Proveedor encontrado = dao.buscarPorId(id);

        if (encontrado != null) {
            System.out.println("Empresa: " + encontrado.getNombreEmpresa());
            System.out.println("Contacto: " + encontrado.getContacto());
            System.out.println("Telefono: " + encontrado.getTelefono());
        }

        // Actualizar
        System.out.println("\nACTUALIZAR PROVEEDOR");

        registrado.setContacto("Carlos Ramirez - Actualizado");
        registrado.setTelefono("5555-9876");

        if (dao.actualizar(registrado)) {
            System.out.println("Proveedor actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el proveedor.");
        }

        // Listar
        System.out.println("\nLISTA DE PROVEEDORES");

        proveedores = dao.listarTodos();

        for (Proveedor p : proveedores) {
            System.out.println(
                    "ID: " + p.getId()
                    + " | Empresa: " + p.getNombreEmpresa()
                    + " | Contacto: " + p.getContacto()
                    + " | Activo: " + p.isActivo()
            );
        }

        // Eliminar opcionalmente
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Deseas eliminar el proveedor de prueba?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            if (dao.eliminar(id)) {
                System.out.println("Proveedor eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el proveedor.");
            }

        } else {
            System.out.println("El proveedor de prueba se conservó.");
        }
    }
}
