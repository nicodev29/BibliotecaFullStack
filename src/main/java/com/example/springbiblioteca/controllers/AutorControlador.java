package com.example.springbiblioteca.controllers;

import com.example.springbiblioteca.entity.Autor;
import com.example.springbiblioteca.exception.MiException;
import com.example.springbiblioteca.services.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    AutorServicio autorServicio;

    @GetMapping("/registrar")
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) throws MiException {

        try {
            autorServicio.crearAutor(nombre);
            modelo.put("exito", "El libro fue cargado correctamente");
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            return "autor_form.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar (ModelMap modelo){

        List<Autor> autores = autorServicio.listarAutores();
        modelo.addAttribute("autores",autores);

        return "autor_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){

        modelo.put("autor",autorServicio.getOne(id));

        return "autor_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo){

        try {
            autorServicio.modificarAutor(nombre, id);
            modelo.put("exito", "Autor modificado correctamente");
            return "redirect:../lista";
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            return "autor_modificar.html";
        }
    }

}
