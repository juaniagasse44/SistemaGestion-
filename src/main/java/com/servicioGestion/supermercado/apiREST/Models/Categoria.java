package com.servicioGestion.supermercado.apiREST.Models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity // 1. Le dice a Spring que esta clase es una tabla de la base de datos.
@Table(name = "categorias") // 2. Especifica el nombre exacto de la tabla.
@Getter // 1. Agrega todos los getters
@Setter // 2. Agrega todos los setters
public class Categoria {

    @Id // 3. Marca este campo como la clave primaria (primaryKey).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. Le dice a la BD que genere el valor automáticamente (autoIncrement).
    private Integer id;

    @Column(name = "nombre") // 5. Mapea este campo a la columna "nombre".
    private String nombre;

    // 6. Define la relación: Una categoría tiene muchos productos.
    @OneToMany(mappedBy = "categoria") 
    private List<Producto> productos;

    

    
}
