package com.servicioGestion.supermercado.apiREST.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicioGestion.supermercado.apiREST.Models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
