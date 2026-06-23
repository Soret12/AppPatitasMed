package com.sena.patitasmed.service;

import java.util.List;
import java.util.Optional;

import com.sena.patitasmed.entity.Usuario;

public interface UsuarioService {

    public Optional<Usuario> findById(Integer id);
    public List<Usuario> findAll();
    public Usuario save(Usuario u);
    public void deleteById(Integer id);
    public Usuario login(String username, String password);

}
