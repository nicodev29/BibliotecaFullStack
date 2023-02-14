package com.example.springbiblioteca.services;

import com.example.springbiblioteca.entity.Editorial;
import com.example.springbiblioteca.exception.MiException;
import com.example.springbiblioteca.repository.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EditorialServicio {
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    @Autowired
    private ValidacionServicio validar;

    @Transactional
    public void crearEditorial(String nombre) throws MiException {

        validar.validarNombre(nombre);

        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);

    }

    public List<Editorial> listarEditoriales(){

        List<Editorial> editoriales = new ArrayList<>();

        editoriales = editorialRepositorio.findAll();

        return editoriales;
    }

    public void modificarEditorial (String id, String nombre) throws MiException{

        validar.validarTodoEditorial(id,nombre);

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()){

            Editorial editorial = respuesta.get();

            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);

        }
    }

    public Editorial getOne(String id) {
        return editorialRepositorio.getOne(id);
    }
}
