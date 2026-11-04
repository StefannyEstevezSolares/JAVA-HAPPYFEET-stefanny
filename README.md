# HAPPY FEET – Sistema de Gestión Veterinaria

## 1. Descripción del proyecto

**HAPPY FEET** es un sistema de gestión veterinaria desarrollado con Java y MySQL. Su objetivo principal es facilitar la administración de las actividades de una clínica veterinaria, centralizando la información de las mascotas, sus dueños, las consultas médicas, las citas, los tratamientos, el inventario, la facturación y otras actividades.

La idea de desarrollar este sistema surgió de la necesidad de organizar los procesos de una veterinaria en una sola aplicación, evitando llevar los registros de manera manual y facilitando el acceso a la información.

El sistema permite administrar diferentes áreas de la veterinaria mediante un menú principal, desde el cual se puede acceder a los módulos correspondientes.

## 2. Análisis y levantamiento de requerimientos

Antes de comenzar con el desarrollo de HAPPY FEET, realicé el levantamiento de requerimientos funcionales y no funcionales. Esta fue la primera etapa del proyecto, ya que necesitaba identificar las necesidades de la veterinaria, establecer el alcance del sistema y definir las funcionalidades que debía incluir.

Los requerimientos funcionales describen las operaciones que el sistema debe permitir realizar, mientras que los no funcionales establecen las tecnologías, la arquitectura y los criterios técnicos que se deben cumplir durante el desarrollo.

### 2.1 Requerimientos funcionales

Los requerimientos funcionales se organizaron en los siguientes módulos:

#### Módulo 1. Gestión de pacientes (mascotas y dueños)

**Registro de mascotas**

| No. | Requerimiento |
|---|---|
| 1 | El aplicativo debe permitir crear una ficha completa con datos básicos de la mascota. |
| 2 | El aplicativo debe permitir desactivar una mascota, mas no eliminarla del programa. |
| 3 | El aplicativo debe permitir crear un historial detallado que incluya historial médico. |
| 4 | El aplicativo debe permitir registrar un microchip o tatuaje de identificación única. |

**Registro de dueños**

| No. | Requerimiento |
|---|---|
| 5 | El aplicativo debe permitir registrar al dueño con datos básicos y la opción de agregar un contacto de emergencia. |
| 6 | El aplicativo debe permitir desactivar un dueño, mas no eliminarlo del programa. |
| 7 | El aplicativo debe relacionar a un único dueño por mascota. |
| 8 | El aplicativo debe permitir la transferencia de propiedad de una mascota a otro dueño. |

**Club de mascotas frecuentes**

| No. | Requerimiento |
|---|---|
| 9 | El aplicativo debe permitir acumular puntos por servicio o compra. |
| 10 | El aplicativo debe permitir aplicar un servicio gratis. |
| 11 | El aplicativo debe permitir aplicar a una consulta gratis. |
| 12 | El aplicativo debe permitir hacer un descuento por puntos. |
| 13 | El aplicativo debe permitir acumular puntos extra al utilizar un servicio. |

#### Módulo 2. Servicios médicos y citas

**Agenda de citas**

| No. | Requerimiento |
|---|---|
| 14 | El aplicativo debe permitir programar una cita. |
| 15 | El aplicativo debe permitir consultar el estado de una cita. |
| 16 | El aplicativo debe permitir cambiar el estado de una cita (programada, finalizada o cancelada). |
| 17 | El aplicativo debe permitir asociar una cita a una mascota. |

**Gestión de consultas médicas (Dashboard)**

| No. | Requerimiento |
|---|---|
| 18 | El aplicativo debe permitir registrar una consulta con detalles básicos. |
| 19 | El aplicativo debe permitir registrar procedimientos especiales preoperatorios y postoperatorios. |
| 20 | El aplicativo debe permitir deducir automáticamente la cantidad correspondiente del inventario. |

#### Módulo 3. Inventario y farmacia

**Control de stock**

| No. | Requerimiento |
|---|---|
| 21 | El aplicativo debe permitir registrar productos nuevos en el stock. |
| 22 | El aplicativo debe permitir eliminar un producto del stock cuando su cantidad llegue a cero. |
| 23 | El aplicativo debe permitir disminuir y aumentar el stock. |
| 24 | El aplicativo debe generar notificaciones cuando el stock esté por debajo del nivel mínimo definido. |
| 25 | El aplicativo debe generar notificaciones cuando un producto esté próximo a su fecha de vencimiento. |
| 26 | El aplicativo debe restringir el uso de medicamentos o vacunas vencidos. |

**Proveedores**

| No. | Requerimiento |
|---|---|
| 27 | El aplicativo debe permitir registrar un proveedor con datos básicos. |
| 28 | El aplicativo debe permitir editar la información de un proveedor. |
| 29 | El aplicativo debe permitir desactivar a un proveedor en lugar de eliminarlo del sistema. |

#### Módulo 4. Facturación y reportes

**Facturas**

| No. | Requerimiento |
|---|---|
| 30 | El aplicativo debe permitir la impresión de una factura después de una consulta. |
| 31 | El aplicativo debe permitir la impresión de una factura después de una venta. |
| 32 | El aplicativo debe permitir la impresión de una factura después de un procedimiento. |

**Reportes**

| No. | Requerimiento |
|---|---|
| 33 | El aplicativo debe permitir generar un reporte de los servicios más solicitados. |
| 34 | El aplicativo debe permitir generar un reporte del desempeño del equipo veterinario. |
| 35 | El aplicativo debe permitir generar un reporte del estado del inventario, incluyendo productos próximos a vencer y necesidades de reabastecimiento. |
| 36 | El aplicativo debe permitir desplegar el análisis de facturación por período parametrizable. |

#### Módulo 5. Actividades especiales

**Días de adopción**

| No. | Requerimiento |
|---|---|
| 37 | El aplicativo debe permitir el registro de mascotas para dar en adopción. |
| 38 | El aplicativo debe permitir editar la información de la mascota en adopción. |
| 39 | El aplicativo debe permitir desactivar las mascotas para dar en adopción. |

**Jornadas de vacunación**

| No. | Requerimiento |
|---|---|
| 40 | El aplicativo debe permitir el registro de mascotas participantes en jornadas de vacunación. |
| 41 | El aplicativo debe permitir editar la información de los registros de vacunación. |
| 42 | El aplicativo debe permitir desactivar los registros correspondientes a las jornadas de vacunación. |

### 2.2 Requerimientos no funcionales

Además de las funcionalidades, se establecieron los siguientes requerimientos técnicos que debían cumplirse durante el desarrollo:

| No. | Requerimiento |
|---|---|
| 1 | El aplicativo debe utilizar JDK 17 de Java y MySQL. |
| 2 | El aplicativo debe emplear JDBC para la conexión a la base de datos. |
| 3 | El aplicativo debe implementar estrictamente el modelo MVC (Modelo-Vista-Controlador). |
| 4 | El aplicativo debe aplicar obligatoriamente los principios de diseño SOLID. |
| 5 | El aplicativo debe implementar cinco patrones de diseño según las necesidades del problema. |
| 6 | El aplicativo debe utilizar expresiones lambda y API Stream para realizar operaciones eficientes sobre colecciones. |
| 7 | El aplicativo debe generar logs en un archivo de texto ubicado en la ruta principal del proyecto, registrando los errores y excepciones del programa. |

### 2.3 Resultado del levantamiento de requerimientos

Una vez definidos los requerimientos, se estableció la estructura general del sistema y se identificaron los módulos que debían desarrollarse.

Este levantamiento sirvió como guía durante todo el proceso de programación, permitiendo organizar las tareas, determinar las responsabilidades de cada componente y seleccionar los patrones de diseño adecuados para resolver las necesidades identificadas.

A partir de esta etapa comenzó la implementación de HAPPY FEET, utilizando una arquitectura MVC, conexión a MySQL mediante JDBC y una organización por paquetes que facilita el mantenimiento y la ampliación del sistema.

## 3. Tecnologías utilizadas

Las principales tecnologías y herramientas utilizadas en el desarrollo son:

- **Java:** lenguaje de programación utilizado para desarrollar la lógica del sistema.
- **JDK:** entorno de desarrollo de Java. El proyecto está configurado actualmente con Java 26.
- **MySQL:** sistema de gestión de bases de datos relacional.
- **JDBC:** tecnología utilizada para conectar Java con MySQL y ejecutar operaciones sobre la base de datos.
- **Maven:** herramienta utilizada para administrar las dependencias y la configuración del proyecto.
- **Apache PDFBox:** biblioteca utilizada para generar documentos PDF, como las facturas.
- **NetBeans:** entorno de desarrollo utilizado para trabajar con el proyecto.
- **Git y GitHub:** herramientas utilizadas para el control de versiones y almacenamiento del código fuente.

## 4. Arquitectura del proyecto

El sistema está organizado utilizando el patrón arquitectónico **Modelo-Vista-Controlador (MVC)**. Esta estructura permite separar las responsabilidades y mantener el código organizado.

La aplicación está dividida en los siguientes paquetes principales:

| Paquete | Responsabilidad |
|---|---|
| `config` | Configuración y conexión con la base de datos. |
| `model` | Clases que representan las entidades del sistema. |
| `dao` | Operaciones de acceso y manipulación de datos en MySQL. |
| `service` | Lógica de negocio y reglas del sistema. |
| `controller` | Comunicación entre las vistas y los servicios. |
| `view` | Menús e interfaces de interacción con el usuario. |
| `main` | Punto de entrada y menú principal de la aplicación. |
| `reportes` | Generación y administración de reportes. |
| `facturacion` | Procesos relacionados con los cálculos de facturación. |
| `notificaciones` | Gestión de notificaciones y alertas. |
| `util` | Clases auxiliares, validaciones y manejo de errores. |

Esta organización permite que cada parte del sistema tenga una responsabilidad específica y facilita el mantenimiento y futuras modificaciones.

## 5. Patrones de diseño implementados

Durante el desarrollo se implementaron cinco patrones de diseño para resolver necesidades específicas del sistema:

### 5.1 Builder – Construcción de mascotas

Se utiliza para facilitar la creación de objetos de tipo `Mascota`, permitiendo establecer los atributos obligatorios y opcionales de una manera organizada.

La clase principal utilizada es `MascotaBuilder`.

### 5.2 Prototype – Clonación de mascotas

Permite crear una nueva mascota tomando como referencia los datos de una mascota existente. El objeto clonado recibe un nuevo identificador y se ajustan los datos que deben ser únicos, como el microchip.

Se implementa mediante el método `clonar()` de la clase `Mascota`.

### 5.3 Factory Method – Generación de reportes

Se utiliza para crear diferentes tipos de reportes sin tener que concentrar toda la lógica de creación en una sola clase.

Entre los reportes implementados se encuentran:

- Reporte de servicios.
- Reporte de veterinarios.
- Reporte de stock bajo.
- Reporte de vencimientos.
- Reporte de facturación.

Se utilizan clases como `ReporteFactory` y las fábricas específicas de cada reporte.

### 5.4 Strategy – Cálculo de facturación

Permite utilizar diferentes estrategias para realizar los cálculos de facturación, dependiendo de las condiciones de cada operación.

Se implementaron estrategias para:

- Cálculo normal.
- Cálculo con descuento.

Las clases principales son `CalculoFacturacion`, `CalculoNormal`, `CalculoDescuento` y `CalculadoraFacturacion`.

### 5.5 Observer – Notificaciones de inventario

Se utiliza para notificar cuando ocurre una situación importante dentro del inventario.

El sistema contempla alertas relacionadas con:

- Productos cuyo stock está por debajo del mínimo establecido.
- Productos próximos a vencer.

Se utilizan las clases `ObservadorInventario`, `NotificadorInventario` y `AlertaInventario`.

## 6. Funcionalidades implementadas y módulos

### 6.1 Gestión de pacientes

Este módulo permite administrar la información de las mascotas y sus dueños.

**Mascotas:**

- Registrar mascotas con sus datos principales.
- Consultar y listar mascotas.
- Actualizar información.
- Desactivar mascotas sin eliminarlas definitivamente.
- Registrar información de identificación, como microchip o tatuaje.
- Crear mascotas utilizando Builder.
- Clonar mascotas utilizando Prototype.

**Dueños:**

- Registrar información personal y datos de contacto.
- Agregar contacto de emergencia.
- Consultar, actualizar y listar dueños.
- Desactivar dueños.

### 6.2 Gestión de citas y servicios médicos

Este módulo permite administrar las actividades relacionadas con la atención veterinaria.

Incluye:

- Programación y administración de citas.
- Consulta y actualización de información.
- Gestión de consultas médicas.
- Registro de información médica.
- Administración de procedimientos y tratamientos.

### 6.3 Gestión de veterinarios

Permite administrar la información del personal veterinario.

Incluye:

- Registro de veterinarios.
- Consulta y listado.
- Búsqueda por identificador.
- Actualización de información.
- Desactivación de veterinarios.

### 6.4 Inventario y farmacia

Este módulo permite controlar los productos y movimientos del inventario.

Incluye:

- Registro de productos.
- Administración de tipos de productos.
- Administración de proveedores.
- Consulta y actualización de productos.
- Control de cantidades disponibles.
- Registro de entradas, salidas, ajustes y vencimientos.
- Alertas de stock bajo.
- Alertas de productos próximos a vencer.

### 6.5 Facturación

Permite administrar los procesos de facturación de los servicios y operaciones de la veterinaria.

Incluye:

- Registro de facturas.
- Cálculo de importes.
- Aplicación de descuentos.
- Generación automática de documentos PDF después del registro de una factura.

### 6.6 Reportes

Este módulo permite generar información organizada para consultar diferentes aspectos de la veterinaria.

Incluye reportes de:

- Servicios más solicitados.
- Veterinarios.
- Productos con stock bajo.
- Productos próximos a vencer.
- Facturación.

Para la creación de reportes se utiliza el patrón Factory Method.

### 6.7 Adopciones

Permite administrar las mascotas disponibles para adopción.

Incluye:

- Registro de mascotas.
- Consulta de información.
- Actualización de datos.
- Desactivación de registros de adopción.

### 6.8 Jornadas de vacunación

Permite registrar y administrar las vacunaciones realizadas durante las jornadas.

Incluye:

- Registro de jornadas de vacunación.
- Registro de mascotas participantes.
- Asociación de vacunas, dueños y veterinarios.
- Registro del lote de la vacuna.
- Registro de la próxima dosis.
- Observaciones de la vacunación.
- Consulta, actualización y eliminación de registros.

## 7. Modelo de la base de datos

El sistema utiliza MySQL para almacenar la información de manera estructurada y mantener las relaciones entre los diferentes elementos de la veterinaria.

La base de datos está organizada en tablas que representan las entidades principales del sistema, como:

- Dueños.
- Mascotas.
- Veterinarios.
- Citas.
- Consultas médicas.
- Procedimientos.
- Productos.
- Tipos de productos.
- Proveedores.
- Movimientos de inventario.
- Facturas.
- Jornadas de vacunación.
- Registros de vacunación.
- Adopciones.

Las relaciones entre las tablas permiten asociar las mascotas con sus dueños, las citas con las mascotas, las consultas con los veterinarios y los movimientos con los productos del inventario.

**Diagrama Entidad-Relación:**

Agregar aquí la imagen del diagrama Entidad-Relación de la base de datos.

## 8. Instalación y ejecución del proyecto

### 8.1 Requisitos previos

Antes de ejecutar el proyecto es necesario tener instalados:

- JDK de Java compatible con la versión configurada en el proyecto.
- Apache Maven.
- MySQL Server.
- MySQL Workbench (recomendado).
- NetBeans u otro IDE compatible con Maven.

### 8.2 Clonar el repositorio

Abrir una terminal y ejecutar:

```bash
git clone URL_DEL_REPOSITORIO
```

Después, ingresar a la carpeta del proyecto:

```bash
cd JAVA_project
```

### 8.3 Configurar la conexión a la base de datos

El proyecto utiliza JDBC para establecer la conexión entre Java y MySQL.

La configuración de conexión se encuentra dentro del paquete:

`com.happyfeet.config`

Para conectar el proyecto con una base de datos local, es necesario revisar la clase encargada de la conexión y verificar los siguientes datos:

- URL de conexión.
- Nombre de la base de datos.
- Usuario de MySQL.
- Contraseña de MySQL.

Un ejemplo de configuración JDBC sería:

```java
String url = "jdbc:mysql://localhost:3306/happyfeet";
String usuario = "root";
String password = "TU_CONTRASEÑA";
```

Se debe reemplazar `TU_CONTRASEÑA` por la contraseña configurada en MySQL y verificar que el nombre de la base de datos coincida con el utilizado en los scripts SQL.

Es importante no compartir credenciales personales ni subir contraseñas reales al repositorio.

### 8.4 Crear y configurar la base de datos

1. Abrir MySQL Workbench.
2. Conectarse al servidor MySQL local.
3. Abrir el archivo `schema.sql`.
4. Ejecutar el script para crear la estructura de la base de datos.
5. Abrir el archivo `data.sql`.
6. Ejecutar el script para insertar los datos iniciales, si corresponde.
7. Verificar que las tablas se hayan creado correctamente.

**Orden de ejecución:**

1. `schema.sql`
2. `data.sql`

Los nombres anteriores deben coincidir con los archivos SQL incluidos en el repositorio.

### 8.5 Abrir y ejecutar el proyecto

1. Abrir NetBeans.
2. Seleccionar **File → Open Project**.
3. Buscar la carpeta del proyecto.
4. Esperar a que Maven cargue las dependencias.
5. Verificar que la conexión a MySQL esté configurada.
6. Ejecutar **Clean and Build**.
7. Ejecutar la clase principal `Main.java`.

También se puede utilizar la terminal desde la carpeta del proyecto:

```bash
mvn clean install
```

Para ejecutar la aplicación:

```bash
mvn exec:java
```

## 9. Guía de uso

Al iniciar el programa se muestra el menú principal de HAPPY FEET. Desde este menú se puede seleccionar el módulo que se desea utilizar.

El funcionamiento general es el siguiente:

1. Ejecutar la aplicación.
2. Visualizar el menú principal.
3. Seleccionar el módulo correspondiente.
4. Elegir la operación que se desea realizar.
5. Ingresar los datos solicitados.
6. Confirmar la operación cuando corresponda.
7. Regresar al menú anterior o al menú principal para continuar utilizando el sistema.

Cada módulo cuenta con opciones para registrar, consultar, listar, actualizar o desactivar información, según las operaciones disponibles.

Se recomienda registrar primero los datos relacionados que sean necesarios para otras operaciones. Por ejemplo, registrar un dueño antes de asociarle una mascota y registrar los productos antes de realizar movimientos de inventario.

## 10. Manejo de errores

El proyecto incluye una clase llamada `ControladorErrores`, ubicada en el paquete `com.happyfeet.util`.

Su función es registrar las excepciones que ocurren durante la ejecución del sistema en un archivo de texto llamado:

`happyfeet-errores.txt`

Este archivo se genera automáticamente cuando ocurre un error que es registrado por la aplicación. Guarda la fecha, el mensaje y el detalle de la excepción, facilitando la identificación de problemas durante el uso del sistema.

## 11. Consideraciones finales

HAPPY FEET fue desarrollado con el objetivo de integrar en una sola aplicación los principales procesos administrativos y operativos de una veterinaria.

Durante su desarrollo se trabajó desde el levantamiento de requerimientos hasta la implementación de los módulos, la conexión con la base de datos y la aplicación de principios y patrones de diseño.

La organización del proyecto permite mantener el código separado por responsabilidades y facilita la incorporación de nuevas funcionalidades en el futuro.
