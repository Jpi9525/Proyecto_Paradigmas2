# Ventas de un empleado en un periodo

Aplicación web en **Spring Boot** que consulta las ventas de un empleado de la base
**AdventureWorks** (SQL Server) dentro de un periodo de fechas, y muestra los
resultados en una página web.

## Funcionalidad

El usuario indica: **identificador del empleado**, **fecha inicial** y **fecha final**.
La app devuelve, ordenados por fecha descendente:

- Nombre
- Apellido
- Puesto (`JobTitle`)
- Fecha de venta (`OrderDate`)

Además muestra dos valores calculados con hilos: total de registros y la fecha más
reciente / más antigua del conjunto. También guarda los resultados en un `.csv` local.

## Tecnologías

- Java 17, Spring Boot 3.3
- Spring Web + Thymeleaf (vista)
- Spring JDBC (`JdbcTemplate`)
- Driver `mssql-jdbc`
- Maven

## Estructura del proyecto

```
src/main/java/com/adventureworks/ventas/
├── VentasEmpleadoApplication.java     # arranque
├── controller/VentaController.java    # recibe el formulario, devuelve la vista
├── service/VentaService.java          # logica + COLLECTIONS + JAVA I/O (archivo)
├── service/EstadisticasService.java   # THREADS (ExecutorService, 2 hilos)
├── repository/VentaRepository.java    # consulta SQL a AdventureWorks
└── model/
    ├── VentaEmpleado.java             # fila de resultado
    └── Estadisticas.java              # resultado de los hilos
src/main/resources/
├── application.properties             # conexion a SQL Server
└── templates/
    ├── index.html                     # formulario
    └── resultados.html                # tabla + datos de los hilos
```

## Cómo correrlo en local

1. Tener SQL Server con la base **AdventureWorks** restaurada.
2. Editar `src/main/resources/application.properties` con tu usuario y contraseña.
3. Ejecutar:
   ```
   ./mvnw spring-boot:run
   ```
   (en Windows: `mvnw.cmd spring-boot:run`)
4. Abrir http://localhost:8080

> SalesPersonID de prueba en AdventureWorks: del **274 al 290**.
> Rango de fechas con datos: aprox. **2011-05 a 2014-06**.

## Pendientes por completar (checklist)

Cada archivo tiene comentarios `TODO` con instrucciones. Lo que falta hacer:

- [ ] **Repository** (`VentaRepository`): escribir la consulta SQL a AdventureWorks.
- [ ] **Service** (`VentaService`): llamar al repository, guardar en una colección
      ordenada por fecha DESC.
- [ ] **Java I/O** (`VentaService.guardarEnArchivo`): escribir el `.csv`/`.txt`.
- [ ] **Threads** (`EstadisticasService`): crear los 2 hilos (total + fechas).
- [ ] **Vista** (`resultados.html`): pintar la tabla y los datos de los hilos.
- [ ] **Conexión** (`application.properties`): poner usuario/contraseña reales.
- [ ] **Azure**: desplegar y obtener la URL pública.

> El `Controller` y los modelos (`VentaEmpleado`, `Estadisticas`) ya están armados
> como referencia; pueden ajustarlos si lo necesitan.

## Enfoque alternativo (QuotaDate)

Si en lugar de ventas reales quieren usar las cuotas, cambien la consulta en
`VentaRepository` para usar `Sales.SalesPersonQuotaHistory` (campo `QuotaDate`)
en vez de `Sales.SalesOrderHeader` (`OrderDate`).

## Despliegue en Azure

1. Crear un **Azure SQL Database** con AdventureWorks (o usar la versión "lightweight").
2. Crear un **Azure App Service** (Java 17, Tomcat o Java SE).
3. Configurar variables de entorno `DB_USER` y `DB_PASSWORD` en el App Service
   y descomentar el bloque de Azure en `application.properties`.
4. Compilar: `./mvnw clean package` y desplegar el `.jar` generado en `target/`.

## División de tareas sugerida (2 personas)

**Persona A — Backend / datos**
- Configurar la conexión y validar que la consulta funciona (`VentaRepository`).
- Afinar el archivo `.csv` (`VentaService`).
- Validaciones de entrada (fechas, id).

**Persona B — Hilos / vista / despliegue**
- Lógica de los hilos (`EstadisticasService`).
- Vistas Thymeleaf (`index.html`, `resultados.html`).
- Despliegue en Azure + README final.

> Tip Git: trabajen en ramas (`feature/repository`, `feature/threads`) y hagan
> Pull Requests para revisarse entre ustedes antes de mezclar a `main`.
