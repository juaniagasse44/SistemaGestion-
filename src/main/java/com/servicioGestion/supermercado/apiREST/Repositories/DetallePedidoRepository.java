package com.servicioGestion.supermercado.apiREST.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicioGestion.supermercado.apiREST.Models.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

}
