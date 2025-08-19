package com.servicioGestion.supermercado.apiREST.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servicioGestion.supermercado.apiREST.Models.HistorialMovimiento;

@Repository
public interface HistoriaMovimientoRepository extends JpaRepository<HistorialMovimiento, Integer>{

}
