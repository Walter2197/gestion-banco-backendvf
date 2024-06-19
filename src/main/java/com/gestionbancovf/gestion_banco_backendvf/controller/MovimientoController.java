package com.gestionbancovf.gestion_banco_backendvf.controller;

import com.gestionbancovf.gestion_banco_backendvf.model.Cuenta;
import com.gestionbancovf.gestion_banco_backendvf.model.Movimiento;
import com.gestionbancovf.gestion_banco_backendvf.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("/movimientos")
    public List<Movimiento> listarMovimientos() {
        return movimientoService.listarMovimientos();
    }

    @PostMapping("/movimientos")
    public ResponseEntity<?> guardarMovimiento(@RequestBody Map<String, Object> payload) {
        try {
            // Extraer datos del movimiento
            String tipoMovimiento = (String) payload.get("tipoMovimiento");
            Double valor = ((Number) payload.get("valor")).doubleValue();
            String fechaStr = (String) payload.get("fecha");
            Long cuentaId = ((Number) ((Map<String, Object>) payload.get("cuenta")).get("id")).longValue();

            // Convertir la fecha de String a Date
            Date fecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(fechaStr);

            // Buscar cuenta existente
            Cuenta cuenta = movimientoService.obtenerCuentaPorId(cuentaId);
            if (cuenta == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cuenta no encontrada");
            }

            // Crear y guardar el nuevo movimiento
            Movimiento movimiento = new Movimiento();
            movimiento.setTipoMovimiento(tipoMovimiento);
            movimiento.setValor(valor);
            movimiento.setFecha(fecha);
            movimiento.setCuenta(cuenta);

            Movimiento registrado = movimientoService.guardarMovimiento(movimiento);
            return ResponseEntity.ok(registrado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
        }
    }

    @GetMapping("/movimientos/{id}")
    public ResponseEntity<Movimiento> obtenerMovimientoPorId(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.obtenerMovimientoPorId(id);
        return ResponseEntity.ok(movimiento);
    }

    @DeleteMapping("/movimientos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarMovimiento(@PathVariable Long id) {
        movimientoService.eliminarMovimiento(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
