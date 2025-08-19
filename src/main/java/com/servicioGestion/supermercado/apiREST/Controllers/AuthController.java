package com.servicioGestion.supermercado.apiREST.Controllers;

import com.servicioGestion.supermercado.apiREST.Dto.AuthRequest;
import com.servicioGestion.supermercado.apiREST.Dto.RegisterRequest;
import com.servicioGestion.supermercado.apiREST.Dto.UsuarioPublicDTO;
import com.servicioGestion.supermercado.apiREST.Models.Usuario;
import com.servicioGestion.supermercado.apiREST.Service.UsuarioService;
import com.servicioGestion.supermercado.apiREST.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

	private final UsuarioService usuarioService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest request) {
		return usuarioService.buscarPorEmail(request.getEmail())
				.map(u -> {
					Map<String, Object> resp = new HashMap<>();
					resp.put("message", "El email ya está registrado");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
				})
				.orElseGet(() -> {
					Usuario creado = usuarioService.registrarUsuario(request);
					String token = jwtService.generateToken(creado.getId());
					Map<String, Object> body = new HashMap<>();
					body.put("token", token);
					body.put("usuario", new UsuarioPublicDTO(creado.getId(), creado.getNombre(), creado.getEmail(), creado.getEsAdmin()));
					return ResponseEntity.status(HttpStatus.CREATED).body(body);
				});
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody AuthRequest request) {
		return usuarioService.buscarPorEmail(request.getEmail()).map(usuario -> {
			boolean valido = passwordEncoder.matches(request.getPassword(), usuario.getPassword());
			if (!valido) {
				Map<String, Object> resp = new HashMap<>();
				resp.put("message", "Contraseña incorrecta");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
			}
			String token = jwtService.generateToken(usuario.getId());
			Map<String, Object> body = new HashMap<>();
			body.put("token", token);
			body.put("usuario", new UsuarioPublicDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getEsAdmin()));
			return ResponseEntity.ok(body);
		}).orElseGet(() -> {
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "Usuario no encontrado");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
		});
	}
} 