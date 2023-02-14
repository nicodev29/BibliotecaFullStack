package com.example.springbiblioteca.services;

import com.example.springbiblioteca.exception.MiException;
import org.springframework.stereotype.Service;

@Service
public class ValidacionServicio {
    public void validarNombre(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("Nombre no puede ser vacio ni nulo");
        }
    }

    public void validarIdAutor(String idAutor) throws MiException {
        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("ID Autor no puede ser vacio ni nulo");
        }
    }

    public void validarIdEditorial(String idEditorial) throws MiException {
        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("ID Editorial no puede ser vacio ni nulo");
        }
    }

    public void validarIsbn(Long isbn) throws MiException {
        if (isbn == null) {
            throw new MiException("ISBN no puede ser  nulo");
        }
    }

    public void validarEjemplares(Integer ejemplares) throws MiException {
        if (ejemplares == null) {
            throw new MiException("Ejemplares no puede ser nulo");
        }
    }

    public void validarTitulo(String titulo) throws MiException {
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("Titu no puede ser vacio ni nulo");
        }
    }

    public void validarTodoAutor(String id, String nombre) throws MiException {
        validarIdAutor(id);
        validarNombre(nombre);
    }

    public void validarTodoEditorial(String id, String nombre) throws MiException {
        validarIdEditorial(id);
        validarNombre(nombre);
    }

    public void validarTodoLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial)
            throws MiException {
        validarIsbn(isbn);
        validarTitulo(titulo);
        validarEjemplares(ejemplares);
        validarIdAutor(idAutor);
        validarIdEditorial(idEditorial);
    }
}