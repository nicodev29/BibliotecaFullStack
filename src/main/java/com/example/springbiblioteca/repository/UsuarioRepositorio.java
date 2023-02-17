package com.example.springbiblioteca.repository;

import com.example.springbiblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{

    @Query("SELECT u from Usuario u where u.email = :email")
    Usuario buscarPorEmail(@Param("email") String email);


}
