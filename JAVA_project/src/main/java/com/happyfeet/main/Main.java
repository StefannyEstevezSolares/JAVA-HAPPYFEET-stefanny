package com.happyfeet.main;
import com.happyfeet.model.InsumoProcedimiento;
import com.happyfeet.config.Conexion;
import com.happyfeet.model.Dueno;
import java.sql.Connection;
import javax.swing.JOptionPane;
import com.happyfeet.model.Mascota;
import com.happyfeet.model.Veterinario;
import com.happyfeet.model.Cita;
import java.time.LocalDateTime;
import com.happyfeet.model.ConsultaMedica;
import java.math.BigDecimal;
import com.happyfeet.model.ProcedimientoEspecial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.happyfeet.model.HistorialMedico;
import com.happyfeet.model.Proveedor;
import java.time.LocalDateTime;
import com.happyfeet.model.Inventario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.happyfeet.model.Prescripcion;
import com.happyfeet.model.MovimientoInventario;
import com.happyfeet.model.Factura;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.happyfeet.model.Servicio;
import java.math.BigDecimal;
import com.happyfeet.model.ItemFactura;
import java.math.BigDecimal;
import com.happyfeet.model.MascotaAdopcion;
import java.time.LocalDate;
import com.happyfeet.model.Adopcion;
import java.time.LocalDate;
import com.happyfeet.model.JornadaVacunacion;
import java.time.LocalDate;
import java.time.LocalTime;
import com.happyfeet.model.RegistroJornadaVacunacion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.happyfeet.model.ClubMascotas;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.happyfeet.model.TransaccionPuntos;
import java.time.LocalDateTime;
import com.happyfeet.model.BeneficioClub;
import java.math.BigDecimal;
import com.happyfeet.model.CanjeBeneficio;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.happyfeet.model.Especie;
import com.happyfeet.model.Raza;
import com.happyfeet.model.ProductoTipo;
import com.happyfeet.model.EventoTipo;
import com.happyfeet.model.CitaEstado;


public class Main {

    public static void main(String[] args) {

        int opcion = 0;

        do {

            String menu = """
                    HAPPY FEET VETERINARIA

                    1. Probar conexion a MySQL
                    2. Probar modelo Dueno
                    3. Probar modelo Mascota
                    4. Probar modelo Veterinario
                    5. Probar cita
                    6. Probar modelo Consulta Médica
                    7. Probar modelo Procedimiento Especial
                    8. Probar modelo Historial Médico
                    9. Probar modelo Proveedor
                    10. Probar modelo Inventario
                    11. Probar modelo Prescripcion
                    12. Probar modelo Insumo Procedimiento
                    13. Probar modelo Movimiento Inventario 
                    14. Probar modelo Factura     
                    15. Probar modelo Servicio
                    16. Probar modelo Item Factura
                    17. Probar modelo mascota adopcion
                    18. Probar modelo Adopcion
                    19. Probar modelo Jornada Vacunación
                    20. Probar modelo Registro Jornada Vacunacion.
                    21. Probar modelo Club Mascotas
                    22. Probaar modelo Transaccion Puntos
                    23. Probar modelo Beneficio Club
                    24. Probar modelo Canje Beneficio
                    25. Probar modelo Especie
                    26. Probar modelo Raza
                    27. Probar modelo ProductoTipo
                    28. Probar modelo Evento Tipo
                    29. Probar modelo Cita Estado
                    0. Salir

                    Selecciona una opcion:
                    """;

            String respuesta = JOptionPane.showInputDialog(menu);

            if (respuesta == null) {
                break;
            }

            try {

                opcion = Integer.parseInt(respuesta);

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Debes ingresar un numero valido."
                );

                continue;
            }

            switch (opcion) {

                case 1:

                    Connection conexion = Conexion.getConnection();

                    if (conexion != null) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Happy Feet conectado a MySQL."
                        );

                        try {
                            conexion.close();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    e.getMessage()
                            );
                        }

                    } else {

                        JOptionPane.showMessageDialog(
                                null,
                                "No se pudo conectar a MySQL."
                        );

                    }

                    break;

                case 2:

                    Dueno dueno = new Dueno();

                    dueno.setId(1);
                    dueno.setNombreCompleto("Carlos Ramirez");
                    dueno.setEmail("carlos@gmail.com");
                    dueno.setActivo(true);

                    JOptionPane.showMessageDialog(
                            null,
                            "ID: " + dueno.getId()
                            + "\nNombre: " + dueno.getNombreCompleto()
                            + "\nEmail: " + dueno.getEmail()
                            + "\nActivo: " + dueno.isActivo()
                    );

                    break;

                case 3:

                    Mascota mascota = new Mascota();

                    mascota.setId(1);
                    mascota.setDuenoId(1);
                    mascota.setNombre("Max");
                    mascota.setRazaId(1);
                    mascota.setSexo("Macho");
                    mascota.setPesoActual(new BigDecimal("12.50"));
                    mascota.setActivo(true);

                    JOptionPane.showMessageDialog(
                            null,
                            "ID: " + mascota.getId()
                            + "\nDueno ID: " + mascota.getDuenoId()
                            + "\nNombre: " + mascota.getNombre()
                            + "\nRaza ID: " + mascota.getRazaId()
                            + "\nSexo: " + mascota.getSexo()
                            + "\nPeso: " + mascota.getPesoActual()
                            + "\nActivo: " + mascota.isActivo()
                    );

                    break;

                case 4:

                    Veterinario veterinario = new Veterinario();

                    veterinario.setId(1);
                    veterinario.setNombreCompleto("Ana Martinez");
                    veterinario.setDocumentoIdentidad("1234567890101");
                    veterinario.setLicenciaProfesional("VET-001");
                    veterinario.setEspecialidad("Medicina General");
                    veterinario.setTelefono("5555-1234");
                    veterinario.setEmail("ana@happyfeet.com");
                    veterinario.setFechaContratacion(LocalDate.of(2025, 1, 15));
                    veterinario.setActivo(true);

                    JOptionPane.showMessageDialog(
                            null,
                            "ID: " + veterinario.getId()
                            + "\nNombre: " + veterinario.getNombreCompleto()
                            + "\nDocumento: " + veterinario.getDocumentoIdentidad()
                            + "\nLicencia: " + veterinario.getLicenciaProfesional()
                            + "\nEspecialidad: " + veterinario.getEspecialidad()
                            + "\nTeléfono: " + veterinario.getTelefono()
                            + "\nEmail: " + veterinario.getEmail()
                            + "\nContratación: " + veterinario.getFechaContratacion()
                            + "\nActivo: " + veterinario.isActivo()
                    );

                    break;

                case 5: {

                    Cita cita = new Cita();

                    cita.setId(1);
                    cita.setMascotaId(1);
                    cita.setVeterinarioId(1);
                    cita.setFechaHora(LocalDateTime.of(2026, 11, 5, 10, 30));
                    cita.setMotivo("Consulta general");
                    cita.setEstadoId(1);
                    cita.setObservaciones("Primera consulta de la mascota");
                    cita.setFechaCreacion(LocalDateTime.now());

                    JOptionPane.showMessageDialog(
                            null,
                            "ID: " + cita.getId()
                            + "\nMascota ID: " + cita.getMascotaId()
                            + "\nVeterinario ID: " + cita.getVeterinarioId()
                            + "\nFecha: " + cita.getFechaHora()
                            + "\nMotivo: " + cita.getMotivo()
                            + "\nEstado ID: " + cita.getEstadoId()
                            + "\nObservaciones: " + cita.getObservaciones()
                            + "\nCreada: " + cita.getFechaCreacion()
                    );

                    break;

                }

                case 6: {

                    ConsultaMedica consulta = new ConsultaMedica();

                    consulta.setId(1);
                    consulta.setMascotaId(1);
                    consulta.setVeterinarioId(1);
                    consulta.setCitaId(1);
                    consulta.setFechaHora(LocalDateTime.now());
                    consulta.setMotivo("Consulta general");
                    consulta.setSintomas("Falta de apetito y cansancio");
                    consulta.setDiagnostico("Posible problema digestivo");
                    consulta.setRecomendaciones("Mantener hidratación");
                    consulta.setObservaciones("Revisar evolución en 3 días");
                    consulta.setPesoRegistrado(new BigDecimal("12.50"));
                    consulta.setTemperatura(new BigDecimal("38.50"));

                    JOptionPane.showMessageDialog(
                            null,
                            "ID: " + consulta.getId()
                            + "\nMascota ID: " + consulta.getMascotaId()
                            + "\nVeterinario ID: " + consulta.getVeterinarioId()
                            + "\nCita ID: " + consulta.getCitaId()
                            + "\nFecha: " + consulta.getFechaHora()
                            + "\nMotivo: " + consulta.getMotivo()
                            + "\nSíntomas: " + consulta.getSintomas()
                            + "\nDiagnóstico: " + consulta.getDiagnostico()
                            + "\nRecomendaciones: " + consulta.getRecomendaciones()
                            + "\nObservaciones: " + consulta.getObservaciones()
                            + "\nPeso: " + consulta.getPesoRegistrado()
                            + "\nTemperatura: " + consulta.getTemperatura()
                    );

                    break;
                }

                case 7: {

                    ProcedimientoEspecial procedimiento = new ProcedimientoEspecial();

                    procedimiento.setId(1);
                    procedimiento.setMascotaId(1);
                    procedimiento.setVeterinarioId(1);
                    procedimiento.setTipoProcedimiento("Cirugia");
                    procedimiento.setNombreProcedimiento("Esterilizacion");
                    procedimiento.setFechaHora(LocalDateTime.now());
                    procedimiento.setDuracionEstimadaMinutos(90);
                    procedimiento.setInformacionPreoperatoria("Ayuno de 8 horas");
                    procedimiento.setDetalleProcedimiento("Procedimiento de esterilizacion");
                    procedimiento.setComplicaciones("Ninguna");
                    procedimiento.setSeguimientoPostoperatorio("Reposo y control");
                    procedimiento.setProximoControl(LocalDate.of(2026, 11, 10));
                    procedimiento.setEstado("Programado");
                    procedimiento.setCostoProcedimiento(new BigDecimal("850.00"));

                    JOptionPane.showMessageDialog(
                            null,
                            "ID: " + procedimiento.getId()
                            + "\nMascota ID: " + procedimiento.getMascotaId()
                            + "\nVeterinario ID: " + procedimiento.getVeterinarioId()
                            + "\nTipo: " + procedimiento.getTipoProcedimiento()
                            + "\nNombre: " + procedimiento.getNombreProcedimiento()
                            + "\nFecha: " + procedimiento.getFechaHora()
                            + "\nDuracion: " + procedimiento.getDuracionEstimadaMinutos()
                            + "\nEstado: " + procedimiento.getEstado()
                            + "\nCosto: Q" + procedimiento.getCostoProcedimiento()
                            + "\nProximo control: " + procedimiento.getProximoControl()
                    );

                    break;
                }

                case 8: {
                    HistorialMedico historial = new HistorialMedico();

                    historial.setId(1);
                    historial.setMascotaId(1);
                    historial.setFechaEvento(LocalDate.now());
                    historial.setEventoTipoId(1);
                    historial.setDescripcion("Control médico general");
                    historial.setDiagnostico("Estado de salud estable");
                    historial.setTratamientoRecomendado("Continuar con controles periódicos");
                    historial.setVeterinarioId(1);
                    historial.setConsultaId(1);
                    historial.setProcedimientoId(null);

                    String mensaje = "ID: " + historial.getId()
                            + "\nMascota ID: " + historial.getMascotaId()
                            + "\nFecha: " + historial.getFechaEvento()
                            + "\nTipo de evento ID: " + historial.getEventoTipoId()
                            + "\nDescripción: " + historial.getDescripcion()
                            + "\nDiagnóstico: " + historial.getDiagnostico()
                            + "\nTratamiento: " + historial.getTratamientoRecomendado()
                            + "\nVeterinario ID: " + historial.getVeterinarioId()
                            + "\nConsulta ID: " + historial.getConsultaId()
                            + "\nProcedimiento ID: " + historial.getProcedimientoId();

                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                }

                case 9: {
                    Proveedor proveedor = new Proveedor();

                    proveedor.setId(1);
                    proveedor.setNombreEmpresa("Distribuidora Veterinaria");
                    proveedor.setContacto("Carlos Lopez");
                    proveedor.setTelefono("5555-1234");
                    proveedor.setEmail("contacto@distribuidora.com");
                    proveedor.setDireccion("Zona 10, Guatemala");
                    proveedor.setSitioWeb("www.distribuidora.com");
                    proveedor.setActivo(true);
                    proveedor.setFechaRegistro(LocalDateTime.now());

                    String mensaje = "ID: " + proveedor.getId()
                            + "\nEmpresa: " + proveedor.getNombreEmpresa()
                            + "\nContacto: " + proveedor.getContacto()
                            + "\nTelefono: " + proveedor.getTelefono()
                            + "\nEmail: " + proveedor.getEmail()
                            + "\nDireccion: " + proveedor.getDireccion()
                            + "\nSitio web: " + proveedor.getSitioWeb()
                            + "\nActivo: " + proveedor.isActivo()
                            + "\nFecha de registro: " + proveedor.getFechaRegistro();

                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                }

                case 10: {
                    Inventario producto = new Inventario();

                    producto.setId(1);
                    producto.setNombreProducto("Antibiotico veterinario");
                    producto.setProductoTipoId(1);
                    producto.setDescripcion("Medicamento para uso veterinario");
                    producto.setFabricante("Laboratorio Animal");
                    producto.setProveedorId(1);
                    producto.setLote("LOT-2026-001");
                    producto.setCantidadStock(50);
                    producto.setStockMinimo(10);
                    producto.setUnidadMedida("unidad");
                    producto.setFechaVencimiento(LocalDate.of(2027, 12, 31));
                    producto.setPrecioCompra(new BigDecimal("45.00"));
                    producto.setPrecioVenta(new BigDecimal("65.00"));
                    producto.setRequiereReceta(true);
                    producto.setActivo(true);
                    producto.setFechaRegistro(LocalDateTime.now());

                    String mensaje = "ID: " + producto.getId()
                            + "\nProducto: " + producto.getNombreProducto()
                            + "\nTipo ID: " + producto.getProductoTipoId()
                            + "\nDescripcion: " + producto.getDescripcion()
                            + "\nFabricante: " + producto.getFabricante()
                            + "\nProveedor ID: " + producto.getProveedorId()
                            + "\nLote: " + producto.getLote()
                            + "\nCantidad: " + producto.getCantidadStock()
                            + "\nStock minimo: " + producto.getStockMinimo()
                            + "\nUnidad: " + producto.getUnidadMedida()
                            + "\nVencimiento: " + producto.getFechaVencimiento()
                            + "\nPrecio compra: Q" + producto.getPrecioCompra()
                            + "\nPrecio venta: Q" + producto.getPrecioVenta()
                            + "\nRequiere receta: " + producto.getRequiereReceta()
                            + "\nActivo: " + producto.getActivo()
                            + "\nFecha registro: " + producto.getFechaRegistro();

                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                }

                case 11: {
                    Prescripcion prescripcion = new Prescripcion();

                    prescripcion.setId(1);
                    prescripcion.setConsultaId(1);
                    prescripcion.setProcedimientoId(null);
                    prescripcion.setProductoId(1);
                    prescripcion.setCantidad(2);
                    prescripcion.setDosis("1 tableta");
                    prescripcion.setFrecuencia("Cada 12 horas");
                    prescripcion.setDuracionDias(7);
                    prescripcion.setInstrucciones("Administrar después de comer");
                    prescripcion.setFechaPrescripcion(LocalDateTime.now());

                    String mensaje = "ID: " + prescripcion.getId()
                            + "\nConsulta ID: " + prescripcion.getConsultaId()
                            + "\nProcedimiento ID: " + prescripcion.getProcedimientoId()
                            + "\nProducto ID: " + prescripcion.getProductoId()
                            + "\nCantidad: " + prescripcion.getCantidad()
                            + "\nDosis: " + prescripcion.getDosis()
                            + "\nFrecuencia: " + prescripcion.getFrecuencia()
                            + "\nDuracion: " + prescripcion.getDuracionDias() + " dias"
                            + "\nInstrucciones: " + prescripcion.getInstrucciones()
                            + "\nFecha: " + prescripcion.getFechaPrescripcion();

                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                }

                case 12: {
                    InsumoProcedimiento insumo = new InsumoProcedimiento();

                    insumo.setId(1);
                    insumo.setProcedimientoId(1);
                    insumo.setProductoId(1);
                    insumo.setCantidadUsada(3);
                    insumo.setObservaciones("Se utilizaron tres jeringas durante el procedimiento");

                    String mensaje = "ID: " + insumo.getId()
                            + "\nProcedimiento ID: " + insumo.getProcedimientoId()
                            + "\nProducto ID: " + insumo.getProductoId()
                            + "\nCantidad usada: " + insumo.getCantidadUsada()
                            + "\nObservaciones: " + insumo.getObservaciones();

                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                }

                case 13: {
                    MovimientoInventario movimiento = new MovimientoInventario();

                    movimiento.setId(1);
                    movimiento.setProductoId(1);
                    movimiento.setTipoMovimiento("Entrada");
                    movimiento.setCantidad(20);
                    movimiento.setStockAnterior(30);
                    movimiento.setStockNuevo(50);
                    movimiento.setMotivo("Compra de nuevos productos");
                    movimiento.setReferenciaConsultaId(null);
                    movimiento.setReferenciaProcedimientoId(null);
                    movimiento.setUsuario("Administrador");
                    movimiento.setFechaMovimiento(LocalDateTime.now());

                    String mensaje = "ID: " + movimiento.getId()
                            + "\nProducto ID: " + movimiento.getProductoId()
                            + "\nTipo: " + movimiento.getTipoMovimiento()
                            + "\nCantidad: " + movimiento.getCantidad()
                            + "\nStock anterior: " + movimiento.getStockAnterior()
                            + "\nStock nuevo: " + movimiento.getStockNuevo()
                            + "\nMotivo: " + movimiento.getMotivo()
                            + "\nConsulta ID: " + movimiento.getReferenciaConsultaId()
                            + "\nProcedimiento ID: " + movimiento.getReferenciaProcedimientoId()
                            + "\nUsuario: " + movimiento.getUsuario()
                            + "\nFecha: " + movimiento.getFechaMovimiento();

                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                }

                case 14: {
                    Factura factura = new Factura();

                    factura.setId(1);
                    factura.setDuenoId(1);
                    factura.setNumeroFactura("FAC-2026-001");
                    factura.setFechaEmision(LocalDateTime.now());
                    factura.setSubtotal(new BigDecimal("150.00"));
                    factura.setImpuesto(new BigDecimal("18.00"));
                    factura.setDescuento(new BigDecimal("10.00"));
                    factura.setTotal(new BigDecimal("158.00"));
                    factura.setMetodoPago("Efectivo");
                    factura.setEstado("Pagada");
                    factura.setObservaciones("Pago de consulta general");

                    JOptionPane.showMessageDialog(null,
                            "FACTURA\n\n"
                            + "Número: " + factura.getNumeroFactura()
                            + "\nDueño ID: " + factura.getDuenoId()
                            + "\nSubtotal: Q" + factura.getSubtotal()
                            + "\nImpuesto: Q" + factura.getImpuesto()
                            + "\nDescuento: Q" + factura.getDescuento()
                            + "\nTotal: Q" + factura.getTotal()
                            + "\nMétodo: " + factura.getMetodoPago()
                            + "\nEstado: " + factura.getEstado());

                    break;
                }

                case 15: {
                    Servicio servicio = new Servicio();

                    servicio.setId(1);
                    servicio.setNombre("Consulta general");
                    servicio.setDescripcion("Evaluacion medica general de la mascota");
                    servicio.setCategoria("Consulta");
                    servicio.setPrecioBase(new BigDecimal("150.00"));
                    servicio.setDuracionEstimadaMinutos(30);
                    servicio.setActivo(true);

                    JOptionPane.showMessageDialog(null,
                            "SERVICIO\n\n"
                            + "Nombre: " + servicio.getNombre()
                            + "\nDescripcion: " + servicio.getDescripcion()
                            + "\nCategoria: " + servicio.getCategoria()
                            + "\nPrecio: Q" + servicio.getPrecioBase()
                            + "\nDuracion: " + servicio.getDuracionEstimadaMinutos()
                            + " minutos"
                            + "\nActivo: " + servicio.getActivo());

                    break;
                }

                case 16: {
                    ItemFactura item = new ItemFactura();

                    item.setId(1);
                    item.setFacturaId(1);
                    item.setTipoItem("Servicio");
                    item.setProductoId(null);
                    item.setServicioId(1);
                    item.setServicioDescripcion("Consulta general");
                    item.setCantidad(1);
                    item.setPrecioUnitario(new BigDecimal("150.00"));
                    item.setSubtotal(new BigDecimal("150.00"));

                    JOptionPane.showMessageDialog(null,
                            "ITEM DE FACTURA\n\n"
                            + "ID: " + item.getId()
                            + "\nFactura ID: " + item.getFacturaId()
                            + "\nTipo: " + item.getTipoItem()
                            + "\nProducto ID: " + item.getProductoId()
                            + "\nServicio ID: " + item.getServicioId()
                            + "\nDescripcion: " + item.getServicioDescripcion()
                            + "\nCantidad: " + item.getCantidad()
                            + "\nPrecio unitario: Q" + item.getPrecioUnitario()
                            + "\nSubtotal: Q" + item.getSubtotal());

                    break;
                }

                case 17: {
                    MascotaAdopcion adopcion = new MascotaAdopcion();

                    adopcion.setId(1);
                    adopcion.setMascotaId(1);
                    adopcion.setFechaIngreso(LocalDate.now());
                    adopcion.setMotivoIngreso("Rescate de la calle");
                    adopcion.setEstado("Disponible");
                    adopcion.setHistoria("Fue encontrada sin hogar y necesita una familia.");
                    adopcion.setTemperamento("Cariñosa y juguetona");
                    adopcion.setNecesidadesEspeciales("Alimentacion especial");
                    adopcion.setFotoAdicionalUrl("https://ejemplo.com/mascota.jpg");

                    JOptionPane.showMessageDialog(null,
                            "MASCOTA EN ADOPCION\n\n"
                            + "ID: " + adopcion.getId()
                            + "\nMascota ID: " + adopcion.getMascotaId()
                            + "\nFecha de ingreso: " + adopcion.getFechaIngreso()
                            + "\nMotivo: " + adopcion.getMotivoIngreso()
                            + "\nEstado: " + adopcion.getEstado()
                            + "\nHistoria: " + adopcion.getHistoria()
                            + "\nTemperamento: " + adopcion.getTemperamento()
                            + "\nNecesidades especiales: " + adopcion.getNecesidadesEspeciales()
                            + "\nFoto: " + adopcion.getFotoAdicionalUrl());

                    break;
                }

                case 18: {
                    Adopcion adopcion = new Adopcion();

                    adopcion.setId(1);
                    adopcion.setMascotaAdopcionId(1);
                    adopcion.setDuenoId(2);
                    adopcion.setFechaAdopcion(LocalDate.now());
                    adopcion.setContratoTexto("Contrato de adopcion de mascota.");
                    adopcion.setCondicionesEspeciales("Compromiso de brindar cuidados y alimentacion.");
                    adopcion.setSeguimientoRequerido(true);
                    adopcion.setFechaPrimerSeguimiento(LocalDate.now().plusDays(30));

                    JOptionPane.showMessageDialog(null,
                            "REGISTRO DE ADOPCION\n\n"
                            + "ID: " + adopcion.getId()
                            + "\nMascota en adopcion ID: " + adopcion.getMascotaAdopcionId()
                            + "\nDueno ID: " + adopcion.getDuenoId()
                            + "\nFecha de adopcion: " + adopcion.getFechaAdopcion()
                            + "\nContrato: " + adopcion.getContratoTexto()
                            + "\nCondiciones: " + adopcion.getCondicionesEspeciales()
                            + "\nSeguimiento requerido: " + adopcion.getSeguimientoRequerido()
                            + "\nFecha del primer seguimiento: " + adopcion.getFechaPrimerSeguimiento());

                    break;
                }

                case 19: {
                    JornadaVacunacion jornada = new JornadaVacunacion();

                    jornada.setId(1);
                    jornada.setNombre("Jornada de vacunacion antirrabica");
                    jornada.setFecha(LocalDate.now().plusDays(15));
                    jornada.setHoraInicio(LocalTime.of(8, 0));
                    jornada.setHoraFin(LocalTime.of(16, 0));
                    jornada.setUbicacion("Clinica Happy Feet");
                    jornada.setDescripcion("Campana gratuita de vacunacion para mascotas.");
                    jornada.setCapacidadMaxima(100);
                    jornada.setEstado("Planificada");

                    JOptionPane.showMessageDialog(null,
                            "JORNADA DE VACUNACION\n\n"
                            + "ID: " + jornada.getId()
                            + "\nNombre: " + jornada.getNombre()
                            + "\nFecha: " + jornada.getFecha()
                            + "\nHora de inicio: " + jornada.getHoraInicio()
                            + "\nHora de finalizacion: " + jornada.getHoraFin()
                            + "\nUbicacion: " + jornada.getUbicacion()
                            + "\nDescripcion: " + jornada.getDescripcion()
                            + "\nCapacidad maxima: " + jornada.getCapacidadMaxima()
                            + "\nEstado: " + jornada.getEstado());

                    break;
                }

                case 20: {
                    RegistroJornadaVacunacion registro = new RegistroJornadaVacunacion();

                    registro.setId(1);
                    registro.setJornadaId(1);
                    registro.setMascotaId(1);
                    registro.setDuenoId(2);
                    registro.setVacunaId(5);
                    registro.setVeterinarioId(1);
                    registro.setFechaHora(LocalDateTime.now());
                    registro.setLoteVacuna("VAC-2026-001");
                    registro.setProximaDosis(LocalDate.now().plusDays(30));
                    registro.setObservaciones("Primera dosis aplicada correctamente.");

                    JOptionPane.showMessageDialog(null,
                            "REGISTRO DE VACUNACION\n\n"
                            + "ID: " + registro.getId()
                            + "\nJornada ID: " + registro.getJornadaId()
                            + "\nMascota ID: " + registro.getMascotaId()
                            + "\nDueno ID: " + registro.getDuenoId()
                            + "\nVacuna ID: " + registro.getVacunaId()
                            + "\nVeterinario ID: " + registro.getVeterinarioId()
                            + "\nFecha y hora: " + registro.getFechaHora()
                            + "\nLote de vacuna: " + registro.getLoteVacuna()
                            + "\nProxima dosis: " + registro.getProximaDosis()
                            + "\nObservaciones: " + registro.getObservaciones());

                    break;
                }

                case 21: {
                    ClubMascotas club = new ClubMascotas();

                    club.setId(1);
                    club.setDuenoId(2);
                    club.setPuntosAcumulados(500);
                    club.setPuntosCanjeados(100);
                    club.setPuntosDisponibles(400);
                    club.setNivel("Plata");
                    club.setFechaInscripcion(LocalDate.now());
                    club.setFechaUltimaActualizacion(LocalDateTime.now());
                    club.setActivo(true);

                    JOptionPane.showMessageDialog(null,
                            "CLUB DE MASCOTAS FRECUENTES\n\n"
                            + "ID: " + club.getId()
                            + "\nDueno ID: " + club.getDuenoId()
                            + "\nPuntos acumulados: " + club.getPuntosAcumulados()
                            + "\nPuntos canjeados: " + club.getPuntosCanjeados()
                            + "\nPuntos disponibles: " + club.getPuntosDisponibles()
                            + "\nNivel: " + club.getNivel()
                            + "\nFecha de inscripcion: " + club.getFechaInscripcion()
                            + "\nUltima actualizacion: " + club.getFechaUltimaActualizacion()
                            + "\nActivo: " + club.getActivo());

                    break;
                }

                case 22: {
                    TransaccionPuntos transaccion = new TransaccionPuntos();

                    transaccion.setId(1);
                    transaccion.setClubMascotasId(1);
                    transaccion.setFacturaId(1);
                    transaccion.setPuntos(50);
                    transaccion.setTipo("Ganados");
                    transaccion.setFecha(LocalDateTime.now());
                    transaccion.setDescripcion("Puntos obtenidos por compra de servicios.");
                    transaccion.setSaldoAnterior(400);
                    transaccion.setSaldoNuevo(450);

                    JOptionPane.showMessageDialog(null,
                            "TRANSACCION DE PUNTOS\n\n"
                            + "ID: " + transaccion.getId()
                            + "\nClub Mascotas ID: " + transaccion.getClubMascotasId()
                            + "\nFactura ID: " + transaccion.getFacturaId()
                            + "\nPuntos: " + transaccion.getPuntos()
                            + "\nTipo: " + transaccion.getTipo()
                            + "\nFecha: " + transaccion.getFecha()
                            + "\nDescripcion: " + transaccion.getDescripcion()
                            + "\nSaldo anterior: " + transaccion.getSaldoAnterior()
                            + "\nSaldo nuevo: " + transaccion.getSaldoNuevo());

                    break;
                }

                case 23: {
                    BeneficioClub beneficio = new BeneficioClub();

                    beneficio.setId(1);
                    beneficio.setNombre("Descuento en consulta");
                    beneficio.setDescripcion("Descuento especial para miembros del club.");
                    beneficio.setNivelRequerido("Plata");
                    beneficio.setPuntosNecesarios(300);
                    beneficio.setTipoBeneficio("Descuento");
                    beneficio.setValorBeneficio(new BigDecimal("15.00"));
                    beneficio.setActivo(true);

                    JOptionPane.showMessageDialog(null,
                            "BENEFICIO DEL CLUB\n\n"
                            + "ID: " + beneficio.getId()
                            + "\nNombre: " + beneficio.getNombre()
                            + "\nDescripcion: " + beneficio.getDescripcion()
                            + "\nNivel requerido: " + beneficio.getNivelRequerido()
                            + "\nPuntos necesarios: " + beneficio.getPuntosNecesarios()
                            + "\nTipo de beneficio: " + beneficio.getTipoBeneficio()
                            + "\nValor: Q" + beneficio.getValorBeneficio()
                            + "\nActivo: " + beneficio.getActivo());

                    break;
                }

                case 24: {
                    CanjeBeneficio canje = new CanjeBeneficio();

                    canje.setId(1);
                    canje.setClubMascotasId(1);
                    canje.setBeneficioId(1);
                    canje.setFechaCanje(LocalDateTime.now());
                    canje.setPuntosCanjeados(300);
                    canje.setEstado("Pendiente");
                    canje.setFechaExpiracion(LocalDate.now().plusDays(30));
                    canje.setFacturaId(null);

                    JOptionPane.showMessageDialog(null,
                            "CANJE DE BENEFICIO\n\n"
                            + "ID: " + canje.getId()
                            + "\nID Club Mascotas: " + canje.getClubMascotasId()
                            + "\nID Beneficio: " + canje.getBeneficioId()
                            + "\nFecha de canje: " + canje.getFechaCanje()
                            + "\nPuntos canjeados: " + canje.getPuntosCanjeados()
                            + "\nEstado: " + canje.getEstado()
                            + "\nFecha de expiracion: " + canje.getFechaExpiracion()
                            + "\nID Factura: " + canje.getFacturaId());

                    break;
                }

                case 25: {
                    Especie especie = new Especie();

                    especie.setId(1);
                    especie.setNombre("Perro");
                    especie.setDescripcion("Canis lupus familiaris");

                    JOptionPane.showMessageDialog(null,
                            "ESPECIE\n\n"
                            + "ID: " + especie.getId()
                            + "\nNombre: " + especie.getNombre()
                            + "\nDescripcion: " + especie.getDescripcion());

                    break;
                }

                case 26: {
                    Raza raza = new Raza();

                    raza.setId(1);
                    raza.setEspecieId(1);
                    raza.setNombre("Labrador Retriever");
                    raza.setCaracteristicas("Tamaño grande, amigable y energético");

                    JOptionPane.showMessageDialog(null,
                            "RAZA\n\n"
                            + "ID: " + raza.getId()
                            + "\nID de especie: " + raza.getEspecieId()
                            + "\nNombre: " + raza.getNombre()
                            + "\nCaracteristicas: " + raza.getCaracteristicas());

                    break;
                }

                case 27: {
                    ProductoTipo tipo = new ProductoTipo();

                    tipo.setId(1);
                    tipo.setNombre("Medicamentos");
                    tipo.setDescripcion("Productos farmacéuticos para uso veterinario.");

                    JOptionPane.showMessageDialog(null,
                            "TIPO DE PRODUCTO\n\n"
                            + "ID: " + tipo.getId()
                            + "\nNombre: " + tipo.getNombre()
                            + "\nDescripcion: " + tipo.getDescripcion());

                    break;
                }

                case 28: {
                    EventoTipo evento = new EventoTipo();

                    evento.setId(1);
                    evento.setNombre("Jornada de vacunacion");
                    evento.setDescripcion("Evento especial para vacunacion de mascotas.");

                    JOptionPane.showMessageDialog(null,
                            "TIPO DE EVENTO\n\n"
                            + "ID: " + evento.getId()
                            + "\nNombre: " + evento.getNombre()
                            + "\nDescripcion: " + evento.getDescripcion());

                    break;
                }

                case 29: {
                    CitaEstado estado = new CitaEstado();

                    estado.setId(1);
                    estado.setNombre("Confirmada");
                    estado.setDescripcion("La cita ha sido confirmada por la veterinaria.");

                    JOptionPane.showMessageDialog(null,
                            "ESTADO DE CITA\n\n"
                            + "ID: " + estado.getId()
                            + "\nNombre: " + estado.getNombre()
                            + "\nDescripcion: " + estado.getDescripcion());

                    break;
                }
                case 0:

                    JOptionPane.showMessageDialog(
                            null,
                            "Cerrando Happy Feet."
                    );

                    break;

                default:

                    JOptionPane.showMessageDialog(
                            null,
                            "Opcion no valida."
                    );

            }

        } while (opcion != 0);

    }
}
