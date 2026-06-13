package com.adventureworks.ventas.service;

import com.adventureworks.ventas.model.Estadisticas;
import com.adventureworks.ventas.model.VentaEmpleado;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * REQUISITO DE THREADS.
 *
 * Deben crear DOS hilos para procesar la informacion:
 *   Hilo 1 -> calcular el TOTAL de registros encontrados.
 *   Hilo 2 -> calcular la fecha MAS RECIENTE y la MAS ANTIGUA.
 *
 * Pueden usar Thread, Runnable o ExecutorService.
 * El resultado de cada hilo se guarda en el objeto Estadisticas.
 */
@Service
public class EstadisticasService {

    public Estadisticas calcular(List<VentaEmpleado> ventas) {
        Estadisticas estadisticas = new Estadisticas();

        // TODO: Crear el HILO 1 que calcule el total de registros
        //       y lo guarde con estadisticas.setTotalRegistros(...).

        // TODO: Crear el HILO 2 que recorra las fechas y calcule la mas
        //       reciente y la mas antigua, guardandolas con
        //       estadisticas.setFechaMasReciente(...) y setFechaMasAntigua(...).

        // TODO: Lanzar ambos hilos y ESPERAR a que terminen antes de
        //       devolver el resultado (join() o Future.get()).

        return estadisticas; // <- por ahora devuelve vacio; completar.
    }
}
