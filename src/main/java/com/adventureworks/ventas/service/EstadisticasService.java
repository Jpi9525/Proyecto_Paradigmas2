package com.adventureworks.ventas.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.adventureworks.ventas.model.Estadisticas;
import com.adventureworks.ventas.model.VentaEmpleado;

/**
 * Calcula estadísticas del listado de ventas usando DOS HILOS en paralelo.
 *
 *   Hilo 1  ->  total de registros.
 *   Hilo 2  ->  fecha más reciente y fecha más antigua.
 *
 * Se usa un ExecutorService (pool de 2 hilos) en lugar de crear objetos Thread
 * "a mano", porque con Future podemos recoger fácilmente el resultado que devuelve
 * cada hilo y esperar a que terminen.
 */
@Service
public class EstadisticasService {

    /**
     * @param ventas listado ya obtenido por VentaService (puede venir vacío).
     * @return objeto Estadisticas con el total y las fechas extremas.
     */
    public Estadisticas calcular(List<VentaEmpleado> ventas) {

        // Pool con 2 hilos: las dos tareas se ejecutan AL MISMO TIEMPO.
        ExecutorService pool = Executors.newFixedThreadPool(2);

        try {
            // ---------------- HILO 1: total de registros ----------------
            Callable<Integer> tareaTotal = () -> {
                System.out.println("Hilo total  -> " + Thread.currentThread().getName());
                return (ventas == null) ? 0 : ventas.size();
            };

            // ---------------- HILO 2: fecha más reciente y más antigua ----------------
            Callable<LocalDate[]> tareaFechas = () -> {
                System.out.println("Hilo fechas -> " + Thread.currentThread().getName());
                if (ventas == null || ventas.isEmpty()) {
                    return new LocalDate[]{ null, null };
                }
                LocalDate masReciente = ventas.stream()
                        .map(VentaEmpleado::getFecha)
                        .max(Comparator.naturalOrder())
                        .orElse(null);
                LocalDate masAntigua = ventas.stream()
                        .map(VentaEmpleado::getFecha)
                        .min(Comparator.naturalOrder())
                        .orElse(null);
                return new LocalDate[]{ masReciente, masAntigua };
            };

            // Lanzamos las dos tareas a los hilos del pool.
            Future<Integer>     futuroTotal  = pool.submit(tareaTotal);
            Future<LocalDate[]> futuroFechas = pool.submit(tareaFechas);

            // get() BLOQUEA hasta que cada hilo termina y devuelve su resultado.
            int total = futuroTotal.get();
            LocalDate[] fechas = futuroFechas.get();

            // Armamos el objeto de resultado que verá la vista.
            Estadisticas estadisticas = new Estadisticas();
            estadisticas.setTotalRegistros(total);
            estadisticas.setFechaMasReciente(fechas[0]);
            estadisticas.setFechaMasAntigua(fechas[1]);
            return estadisticas;

        } catch (InterruptedException | ExecutionException e) {
            // Atrapamos aquí las excepciones de los hilos para no obligar
            // al controller a manejarlas. Las envolvemos en una no chequeada.
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error al calcular las estadísticas con los hilos", e);
        } finally {
            pool.shutdown(); // siempre cerramos el pool, pase lo que pase.
        }
    }
}