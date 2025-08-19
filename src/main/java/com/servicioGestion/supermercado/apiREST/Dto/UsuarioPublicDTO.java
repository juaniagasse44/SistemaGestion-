package com.servicioGestion.supermercado.apiREST.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioPublicDTO {
	private Integer id;
	private String nombre;
	private String email;
	@JsonProperty("es_admin")
	private Boolean esAdmin;
} 