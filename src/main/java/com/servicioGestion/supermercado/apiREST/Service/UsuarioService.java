package com.servicioGestion.supermercado.apiREST.Service;

import com.servicioGestion.supermercado.apiREST.Dto.RegisterRequest;
import com.servicioGestion.supermercado.apiREST.Models.Usuario;

import java.util.Optional;

public interface UsuarioService {
	Usuario registrarUsuario(RegisterRequest request);
	Optional<Usuario> buscarPorEmail(String email);
} 