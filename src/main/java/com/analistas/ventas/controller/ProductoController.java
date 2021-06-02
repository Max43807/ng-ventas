/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.ventas.controller;

import com.analistas.ventas.model.service.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ander
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    IArticuloService articuloService;
    
    @GetMapping("/listado")
    public String listar(Model m) {
        
        m.addAttribute("titulo", "Listado de Productos");
        m.addAttribute("articulos", articuloService.buscarTodo());
        
        return "productos/list";
    }
    
}
