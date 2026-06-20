package com.adventureworks.ventas.repository;

import com.adventureworks.ventas.model.VentaEmpleado;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * CAPA REPOSITORY (acceso a datos).
 *
 * Aquí va la consulta a AdventureWorks.
 * Se utiliza 'jdbcTemplate' :)
 */
@Repository
public class VentaRepository {

    private final JdbcTemplate jdbcTemplate;

    public VentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<VentaEmpleado> buscarVentas(int empleadoId, LocalDate fechaInicial, LocalDate fechaFinal) {
        //Consulta SQL aplicando ordenamiento descendente by OrderDate.
        String sql = """
            SELECT p.FirstName, p.LastName, e.JobTitle, soh.OrderDate
            FROM Sales.SalesOrderHeader soh
            INNER JOIN HumanResources.Employee e ON soh.SalesPersonID = e.BusinessEntityID
            INNER JOIN Person.Person p ON e.BusinessEntityID = p.BusinessEntityID
            WHERE soh.SalesPersonID = ?
              AND soh.OrderDate >= ? 
              AND soh.OrderDate <= ?
            ORDER BY soh.OrderDate DESC
            """;

        //Usamos una función Lambda para el RowMapper aprovechando constructor de VentaEmpleado.
        return jdbcTemplate.query(sql, (rs, rowNum) -> new VentaEmpleado(
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getString("JobTitle"),
                rs.getDate("OrderDate").toLocalDate() //Convierte el Date de SQL a LocalDate de Java.
        ), empleadoId, Date.valueOf(fechaInicial), Date.valueOf(fechaFinal));
    }
}
