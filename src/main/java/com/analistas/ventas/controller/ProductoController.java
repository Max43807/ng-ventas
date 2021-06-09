/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.ventas.controller;

import com.analistas.ventas.model.domain.Articulo;
import com.analistas.ventas.model.service.IArticuloService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @GetMapping("/nuevo")
    public ResponseEntity<?> nuevo(Model m) {
        
        Articulo articulo = new Articulo();
        
        return ResponseEntity.ok(articulo);
    }
    
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar (@Valid Articulo articulo, BindingResult result) {
    
        //Construir un diccionario de errores...
        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for(FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.unprocessableEntity().body(errors);
        }
        
        Articulo a = new Articulo();
        if(articulo.getId() != 0) {
            a = articuloService.buscarPorId(articulo.getId());
        }
        
        a.setCodBarras(articulo.getCodBarras());
        a.setDescripcion(articulo.getDescripcion());
        a.setStock(articulo.getStock());
        a.setPrecio(articulo.getPrecio());
        
        articuloService.guardar(a);
        
        return ResponseEntity.ok().build();
    }
    
}
