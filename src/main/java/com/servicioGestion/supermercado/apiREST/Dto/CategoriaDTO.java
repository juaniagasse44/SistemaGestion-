package com.servicioGestion.supermercado.apiREST.Dto;

import lombok.Getter;
import lombok.Setter;
//Un DTO es una clase simple que define exactamente qué datos queremos enviar o recibir a través de nuestra API.
@Getter
@Setter
public class CategoriaDTO {
    private Integer id;
    private String nombre;
}
