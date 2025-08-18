package com.servicioGestion.supermercado.apiREST.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "empresas")
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false) // 1. nullable = false es el equivalente a allowNull: false
    private String nombre;

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    //  Relaciones 

    @OneToMany(mappedBy = "empresa") // 2. Una empresa tiene muchos usuarios
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "empresa") // 3. Una empresa tiene muchos productos
    private List<Producto> productos;
}
