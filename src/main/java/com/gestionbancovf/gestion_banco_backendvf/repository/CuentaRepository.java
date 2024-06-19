package com.gestionbancovf.gestion_banco_backendvf.repository;


import com.gestionbancovf.gestion_banco_backendvf.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
