package com.servicioGestion.supermercado.apiREST.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp; // 1. Import para la fecha de actualización
import com.servicioGestion.supermercado.apiREST.Enums.TipoMovimiento;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_movimientos")
@Getter
@Setter
public class HistorialMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimiento tipoMovimiento;

    @Column(name = "cantidad_afectada")
    private Integer cantidadAfectada;

    @CreationTimestamp // 2. Se establece al crear la entidad
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // 3. Se establece al crear y se actualiza en cada modificación
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    
    // --- Relaciones ---

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_usuario") // nullable = true por defecto
    private Usuario usuario;
}
