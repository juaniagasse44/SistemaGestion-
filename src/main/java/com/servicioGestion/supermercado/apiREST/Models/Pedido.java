package com.servicioGestion.supermercado.apiREST.Models;

 // 1. Importamos nuestro Enum
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime; // 2. Usamos LocalDateTime para fechas y horas
import java.util.List;

import com.servicioGestion.supermercado.apiREST.Enums.EstadoPedido;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_pedido")
    private LocalDateTime fechaPedido;

    @Column(name = "total")
    private BigDecimal total;

    @Enumerated(EnumType.STRING) // 3. Le decimos a JPA cómo guardar el Enum
    @Column(name = "estado")
    private EstadoPedido estado;

    //  Relaciones

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido") // 4. "mappedBy" apunta al campo en la clase DetallePedido
    private List<DetallePedido> detalles;

}
