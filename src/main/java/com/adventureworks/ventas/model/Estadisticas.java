package com.adventureworks.ventas.model;

import java.time.LocalDate;

/**
 * Resultados que calculan los DOS HILOS:
 *  - totalRegistros        -> hilo 1
 *  - fechaMasReciente / fechaMasAntigua -> hilo 2
 */
public class Estadisticas {

    private int totalRegistros;
    private LocalDate fechaMasReciente;
    private LocalDate fechaMasAntigua;

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public LocalDate getFechaMasReciente() {
        return fechaMasReciente;
    }

    public void setFechaMasReciente(LocalDate fechaMasReciente) {
        this.fechaMasReciente = fechaMasReciente;
    }

    public LocalDate getFechaMasAntigua() {
        return fechaMasAntigua;
    }

    public void setFechaMasAntigua(LocalDate fechaMasAntigua) {
        this.fechaMasAntigua = fechaMasAntigua;
    }
}
