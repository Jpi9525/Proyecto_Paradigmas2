package com.adventureworks.ventas.controller;

import com.adventureworks.ventas.model.Estadisticas;
import com.adventureworks.ventas.model.VentaEmpleado;
import com.adventureworks.ventas.service.EstadisticasService;
import com.adventureworks.ventas.service.VentaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class VentaController {

    private final VentaService ventaService;
    private final EstadisticasService estadisticasService;

    public VentaController(VentaService ventaService, EstadisticasService estadisticasService) {
        this.ventaService = ventaService;
        this.estadisticasService = estadisticasService;
    }

    // Pagina inicial con el formulario.
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Procesa la busqueda y muestra los resultados.
    @GetMapping("/buscar")
    public String buscar(
            @RequestParam int empleadoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFinal,
            Model model) {

        List<VentaEmpleado> ventas = ventaService.obtenerVentas(empleadoId, fechaInicial, fechaFinal);

        // Calculos de los DOS HILOS.
        Estadisticas estadisticas = estadisticasService.calcular(ventas);

        model.addAttribute("ventas", ventas);
        model.addAttribute("estadisticas", estadisticas);
        model.addAttribute("empleadoId", empleadoId);
        model.addAttribute("fechaInicial", fechaInicial);
        model.addAttribute("fechaFinal", fechaFinal);

        return "resultados";
    }
}
