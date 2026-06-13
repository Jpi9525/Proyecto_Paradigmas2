package com.adventureworks.ventas.model;

import java.time.LocalDate;

/**
 * Representa una fila del resultado de la consulta.
 * Campos pedidos: Nombre, Apellido, JobTitle, Fecha (OrderDate / QuotaDate).
 */
public class VentaEmpleado {

    private String nombre;     // FirstName
    private String apellido;   // LastName
    private String jobTitle;   // JobTitle
    private LocalDate fecha;   // OrderDate o QuotaDate

    public VentaEmpleado() {
    }

    public VentaEmpleado(String nombre, String apellido, String jobTitle, LocalDate fecha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.jobTitle = jobTitle;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
