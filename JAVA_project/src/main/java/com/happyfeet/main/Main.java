
package com.happyfeet.main;

import com.happyfeet.view.AdopcionView;
import com.happyfeet.view.CitaView;
import com.happyfeet.view.ClubMascotasView;
import com.happyfeet.view.DuenoView;
import com.happyfeet.view.FacturacionView;
import com.happyfeet.view.HistorialMedicoView;
import com.happyfeet.view.InventarioView;
import com.happyfeet.view.MascotaView;
import com.happyfeet.view.PrescripcionView;
import com.happyfeet.view.ProcedimientoView;
import com.happyfeet.view.ReportesView;
import com.happyfeet.view.VacunacionView;
import com.happyfeet.view.VeterinarioView;

import java.util.Scanner;

public class Main {

    private final Scanner scanner;

    public Main() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main programa = new Main();
        programa.mostrarMenuPrincipal();
    }

    public void mostrarMenuPrincipal() {

        int opcion;

        do {
            System.out.println("\n========================================");
            System.out.println("          HAPPY FEET");
            System.out.println("     SISTEMA DE GESTIÓN VETERINARIA");
            System.out.println("========================================");
            System.out.println("1.  Gestión de dueños");
            System.out.println("2.  Gestión de mascotas");
            System.out.println("3.  Gestión de veterinarios");
            System.out.println("4.  Gestión de citas");
            System.out.println("5.  Historial médico");
            System.out.println("6.  Procedimientos e insumos");
            System.out.println("7.  Inventario");
            System.out.println("8.  Prescripciones");
            System.out.println("9.  Facturación");
            System.out.println("10. Gestión de adopciones");
            System.out.println("11. Jornadas de vacunación");
            System.out.println("12. Club de mascotas");
            System.out.println("13. Reportes");
            System.out.println("0.  Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1:
                        new DuenoView().mostrarMenu();
                        break;

                    case 2:
                        new MascotaView().mostrarMenu();
                        break;

                    case 3:
                        new VeterinarioView().mostrarMenu();
                        break;

                    case 4:
                        new CitaView().mostrarMenu();
                        break;

                    case 5:
                        new HistorialMedicoView().mostrarMenu();
                        break;

                    case 6:
                        new ProcedimientoView().mostrarMenu();
                        break;

                    case 7:
                        new InventarioView().mostrarMenu();
                        break;

                    case 8:
                        new PrescripcionView().mostrarMenu();
                        break;

                    case 9:
                        new FacturacionView().mostrarMenu();
                        break;

                    case 10:
                        new AdopcionView().mostrarMenu();
                        break;

                    case 11:
                        new VacunacionView().mostrarMenu();
                        break;

                    case 12:
                        new ClubMascotasView().mostrarMenu();
                        break;

                    case 13:
                        new ReportesView().mostrarMenu();
                        break;

                    case 0:
                        System.out.println("\nGracias por utilizar HAPPY FEET.");
                        System.out.println("Cerrando el sistema...");
                        break;

                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
                opcion = -1;
            }

        } while (opcion != 0);
    }
}
