package com.servicioGestion.supermercado.apiREST.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
	private String nombre;
	private String email;
	private String password;
	private String direccion;
	private String telefono;
} 