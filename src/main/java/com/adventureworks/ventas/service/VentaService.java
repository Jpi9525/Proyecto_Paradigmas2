package com.adventureworks.ventas.service;

import com.adventureworks.ventas.model.VentaEmpleado;
import com.adventureworks.ventas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.time.LocalDate;
import java.util.List;

/**
 * CAPA SERVICE (lógica de negocio).
 *
 * Aquí se cumplen dos requisitos:
 *   - COLLECTIONS: Se utiliza List para almacenar y ORDENAR los resultados, aunque ya vienen ORDER BY DESC desde la query SQL.
 *   - JAVA I/O: Se guardan los resultados en un archivo local (.csv).
 */
@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    // Ruta del archivo a generar (está en application.properties).
    @Value("${app.export.path:resultados_ventas.csv}")
    private String exportPath;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<VentaEmpleado> obtenerVentas(int empleadoId, LocalDate fechaInicial, LocalDate fechaFinal) {
        //Pedir los datos al repository y guardarlos en una colección (List).
        List<VentaEmpleado> ventas = ventaRepository.buscarVentas(empleadoId, fechaInicial, fechaFinal);

        //Utilizamos Comparator para el ordenamiento in-situ de la Lista.
        //Es redundante porque en la query SQL ya se hace "ORDER BY OrderDate DESC".
        ventas.sort(Comparator.comparing(VentaEmpleado::getFecha).reversed());

        //Llamar a guardarEnArchivo para generar el (.csv).
        guardarEnArchivo(ventas);

        //Devolver la Lista al Controller.
        return ventas;
    }

    private void guardarEnArchivo(List<VentaEmpleado> ventas) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(exportPath))) {
            //Escribir la cabecera.
            writer.write("Nombre,Apellido,Puesto,Fecha");
            writer.newLine();

            //Escribir los registros.
            for (VentaEmpleado v : ventas) {
                String linea = String.format("%s,%s,%s,%s",
                        v.getNombre(),
                        v.getApellido(),
                        v.getJobTitle(),
                        v.getFecha().toString());
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) { //Manejar un error de escritura del (.csv).
            System.err.println("Error al escribir el archivo CSV: " + e.getMessage());
        }
    }
}
