package com.adventureworks.ventas.service;

import com.adventureworks.ventas.model.VentaEmpleado;
import com.adventureworks.ventas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * CAPA SERVICE (logica de negocio).
 *
 * Aqui se cumplen dos requisitos:
 *   - COLLECTIONS: usar una List/Set/Map para almacenar y ORDENAR los resultados.
 *   - JAVA I/O: guardar los resultados en un archivo local (.txt o .csv).
 */
@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    // Ruta del archivo a generar (esta en application.properties).
    @Value("${app.export.path:resultados_ventas.csv}")
    private String exportPath;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<VentaEmpleado> obtenerVentas(int empleadoId, LocalDate fechaInicial, LocalDate fechaFinal) {
        // TODO 1: Pedir los datos al repository.
        // TODO 2: Guardarlos en una COLLECTION (por ejemplo una List) y
        //         asegurar el orden por fecha descendente.
        // TODO 3: Llamar a guardarEnArchivo(...) para cumplir Java I/O.
        // TODO 4: Devolver la coleccion.

        return List.of(); // <- placeholder; reemplazar.
    }

    private void guardarEnArchivo(List<VentaEmpleado> ventas) {
        // TODO: Escribir los resultados en 'exportPath'.
        //   Sugerencia: FileWriter + BufferedWriter dentro de un try-with-resources.
        //   Formato CSV: una linea de encabezado y luego una linea por venta.
    }
}
