package com.servicioGestion.supermercado.apiREST.Service;

import com.servicioGestion.supermercado.apiREST.Dto.RegisterRequest;
import com.servicioGestion.supermercado.apiREST.Models.Usuario;
import com.servicioGestion.supermercado.apiREST.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public Usuario registrarUsuario(RegisterRequest request) {
		Usuario usuario = new Usuario();
		usuario.setNombre(request.getNombre());
		usuario.setEmail(request.getEmail());
		usuario.setPassword(passwordEncoder.encode(request.getPassword()));
		usuario.setDireccion(request.getDireccion());
		usuario.setTelefono(request.getTelefono());
		usuario.setEsAdmin(false);
		return usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
} 