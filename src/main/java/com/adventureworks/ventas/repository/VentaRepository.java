package com.adventureworks.ventas.repository;

import com.adventureworks.ventas.model.VentaEmpleado;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * CAPA REPOSITORY (acceso a datos).
 *
 * Aqui va la consulta a AdventureWorks.
 * Pueden usar 'jdbcTemplate' que ya viene inyectado.
 */
@Repository
public class VentaRepository {

    private final JdbcTemplate jdbcTemplate;

    public VentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<VentaEmpleado> buscarVentas(int empleadoId, LocalDate fechaInicial, LocalDate fechaFinal) {
        // TODO: Escribir la consulta SQL a AdventureWorks.
        //   - Campos a traer: FirstName, LastName, JobTitle y la fecha.
        //   - Filtrar por empleado y por el rango de fechas.
        //   - Ordenar por fecha en orden DESCENDENTE.
        //   Tablas utiles (enfoque OrderDate / ventas reales):
        //     Sales.SalesOrderHeader, HumanResources.Employee, Person.Person
        //   (Enfoque alternativo QuotaDate: Sales.SalesPersonQuotaHistory)
        //
        // TODO: Ejecutar con jdbcTemplate.query(...) y mapear cada fila
        //       a un objeto VentaEmpleado.

        return List.of(); // <- placeholder para que compile; reemplazar.
    }
}
