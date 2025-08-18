package com.servicioGestion.supermercado.apiREST.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_pedido")
@Getter
@Setter
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario; // 1. Usamos BigDecimal para precisión decimal

    // Relaciones 

    @ManyToOne // 2. Muchos detalles pueden pertenecer a un Pedido
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne // 3. Muchos detalles pueden referirse a un Producto
    @JoinColumn(name = "id_producto")
    private Producto producto;

}
