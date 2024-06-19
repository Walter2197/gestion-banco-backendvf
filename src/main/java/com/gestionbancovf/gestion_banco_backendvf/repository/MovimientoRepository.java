package com.gestionbancovf.gestion_banco_backendvf.repository;


import com.gestionbancovf.gestion_banco_backendvf.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}
