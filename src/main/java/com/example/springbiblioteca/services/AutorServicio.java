package com.example.springbiblioteca.services;

import com.example.springbiblioteca.entity.Autor;
import com.example.springbiblioteca.exception.MiException;
import com.example.springbiblioteca.repository.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorServicio {
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private ValidacionServicio validar;

    @Transactional
    public void crearAutor(String nombre) throws MiException {

        validar.validarNombre(nombre);

        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepositorio.save(autor);

    }

    public List<Autor> listarAutores() {

        List<Autor> autores = new ArrayList<>();

        autores = autorRepositorio.findAll();

        return autores;
    }

    public void modificarAutor(String nombre, String id) throws MiException{

        validar.validarTodoAutor(nombre, id);

        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();

            autor.setNombre(nombre);

            autorRepositorio.save(autor);

        }

    }

    public Autor getOne (String id){
        return autorRepositorio.getOne(id);
    }

}
