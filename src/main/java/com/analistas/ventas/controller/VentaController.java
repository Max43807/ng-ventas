/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.ventas.controller;

import com.analistas.ventas.model.domain.Articulo;
import com.analistas.ventas.model.domain.Venta;
import com.analistas.ventas.model.service.IArticuloService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author ander
 */
@Controller
@RequestMapping("/ventas")
@SessionAttributes({"venta"})
public class VentaController {
    
    @Autowired
    IArticuloService productoService;
    
    @GetMapping("/nueva")
    public String nuevaVenta(Model model) {
        
        Venta venta = new Venta();
        venta.setFechaHora(LocalDateTime.now());
    
        model.addAttribute("titulo", "Nueva Venta");
        model.addAttribute("venta", venta);
        
        return "ventas/form";
    }
    
    @ModelAttribute("productos")
    public List<Articulo> getProductos() {
        return productoService.buscarTodo();
    }
}
