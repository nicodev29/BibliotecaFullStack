package com.example.springbiblioteca.services;

import com.example.springbiblioteca.entity.Usuario;
import com.example.springbiblioteca.enums.Rol;
import com.example.springbiblioteca.exception.MiException;
import com.example.springbiblioteca.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private ValidacionServicio validar;

    public void registrarUsuario(String nombre, String email, String password, String password2) throws MiException {

    validarUsuario(nombre, email, password, password2);

    Usuario usuario = new Usuario();

    usuario.setNombre(nombre);
    usuario.setEmail(email);
    usuario.setPassword(password);
    usuario.setRol(Rol.USER);

    usuarioRepositorio.save(usuario);
    }

    public void validarUsuario(String nombre, String email,String password, String password2) throws MiException {

        if(nombre == null || nombre.isEmpty()){
            throw new MiException("El nombre no puede estar vacio");
        }
        if (email == null || email.isEmpty()){
            throw new MiException("El email no puede estar vacio");
        }
        if (password == null || password.length() <= 6){
            throw new MiException("El password no puede estar vacio");
        }
        if(!password.equals(password2)){
            throw new MiException("Las contraseñas no coinciden");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);

        if (usuario != null){

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString());

            permisos.add(p);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        }

        return null;
    }
}
