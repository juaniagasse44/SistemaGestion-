package com.servicioGestion.supermercado.apiREST.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp; // 1. Anotación de Hibernate
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 2. Agregamos el ID que Sequelize asume por defecto

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email", unique = true) // 3. el email sea único
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "es_admin")
    private Boolean esAdmin;

    @CreationTimestamp // 4. Le dice a Hibernate que llene este campo al crear
    @Column(name = "created_at", updatable = false) // 5. Mapea la columna y evita que se actualice
    private LocalDateTime createdAt;

    // Relación 
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
}
