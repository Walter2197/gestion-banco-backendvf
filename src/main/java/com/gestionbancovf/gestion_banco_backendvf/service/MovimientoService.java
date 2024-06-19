package com.gestionbancovf.gestion_banco_backendvf.service;

import com.gestionbancovf.gestion_banco_backendvf.model.Cuenta;
import com.gestionbancovf.gestion_banco_backendvf.model.Movimiento;
import com.gestionbancovf.gestion_banco_backendvf.repository.CuentaRepository;
import com.gestionbancovf.gestion_banco_backendvf.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Movimiento> listarMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento guardarMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();

        if (movimiento.getValor() < 0 && nuevoSaldo < 0) {
            throw new RuntimeException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setFecha(new Date());

        return movimientoRepository.save(movimiento);
    }

    public Movimiento obtenerMovimientoPorId(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
    }

    public void eliminarMovimiento(Long id) {
        Movimiento movimiento = obtenerMovimientoPorId(id);
        movimientoRepository.delete(movimiento);
    }

    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }
}
