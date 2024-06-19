package com.gestionbancovf.gestion_banco_backendvf.controller;

import com.gestionbancovf.gestion_banco_backendvf.model.Cliente;
import com.gestionbancovf.gestion_banco_backendvf.model.Cuenta;
import com.gestionbancovf.gestion_banco_backendvf.service.ClienteService;
import com.gestionbancovf.gestion_banco_backendvf.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cuentas")
    public List<Cuenta> listarCuentas() {
        return cuentaService.listarCuentas();
    }

    @PostMapping("/cuentas")
    public ResponseEntity<?> guardarCuenta(@RequestBody Map<String, Object> payload) {
        try {
            // Extraer datos de la cuenta
            String numeroCuenta = (String) payload.get("numeroCuenta");
            String tipoCuenta = (String) payload.get("tipoCuenta");
            Double saldoInicial = ((Number) payload.get("saldoInicial")).doubleValue();
            Boolean estado = (Boolean) payload.get("estado");
            Long clienteId = ((Number) payload.get("cliente")).longValue();

            // Buscar cliente existente
            Cliente cliente = clienteService.obtenerClientePorId(clienteId);
            if (cliente == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente no encontrado");
            }

            // Crear y guardar la nueva cuenta
            Cuenta cuenta = new Cuenta();
            cuenta.setNumeroCuenta(numeroCuenta);
            cuenta.setTipoCuenta(tipoCuenta);
            cuenta.setSaldoInicial(saldoInicial);
            cuenta.setEstado(estado);
            cuenta.setCliente(cliente);

            Cuenta nuevaCuenta = cuentaService.guardarCuenta(cuenta);
            return ResponseEntity.ok(nuevaCuenta);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
        }
    }

    @GetMapping("/cuentas/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(id);
        return ResponseEntity.ok(cuenta);
    }

    @PutMapping("/cuentas/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaRequest) {
        Cuenta cuenta = cuentaService.actualizarCuenta(id, cuentaRequest);
        return ResponseEntity.ok(cuenta);
    }

    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
