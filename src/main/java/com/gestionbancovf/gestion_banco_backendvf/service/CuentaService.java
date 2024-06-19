package com.gestionbancovf.gestion_banco_backendvf.service;


import com.gestionbancovf.gestion_banco_backendvf.model.Cuenta;
import com.gestionbancovf.gestion_banco_backendvf.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta guardarCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public Cuenta actualizarCuenta(Long id, Cuenta cuentaRequest) {
        Cuenta cuenta = obtenerCuentaPorId(id);
        cuenta.setNumeroCuenta(cuentaRequest.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaRequest.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaRequest.getSaldoInicial());
        cuenta.setEstado(cuentaRequest.isEstado());
        cuenta.setCliente(cuentaRequest.getCliente());
        return cuentaRepository.save(cuenta);
    }

    public void eliminarCuenta(Long id) {
        Cuenta cuenta = obtenerCuentaPorId(id);
        cuentaRepository.delete(cuenta);
    }
}
