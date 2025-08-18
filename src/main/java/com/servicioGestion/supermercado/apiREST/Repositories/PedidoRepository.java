package com.servicioGestion.supermercado.apiREST.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicioGestion.supermercado.apiREST.Models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
