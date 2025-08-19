package com.servicioGestion.supermercado.apiREST.Dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductoDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private String imagenUrl;
    private Boolean activo;
    private CategoriaDTO categoria; //  el DTO de Categoría
    // Campo para RECIBIR el ID de la categoría al crear/actualizar un producto
    private Integer idCategoria; 
}
