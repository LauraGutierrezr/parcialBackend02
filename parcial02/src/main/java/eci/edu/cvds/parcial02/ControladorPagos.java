package eci.edu.cvds.parcial02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class ControladorPagos {

    @Autowired
    private ServicioPagos servicioPagos;

    @PostMapping("/procesar")
    public ResponseEntity<Pago> procesarPago(@RequestBody Pago pago) {
        Pago pagoProc = servicioPagos.procesarPago(pago);
        return ResponseEntity.ok(pagoProc);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Pago>> obtenerPagosUsuario(@PathVariable String idUsuario) {
        List<Pago> pagos = servicioPagos.obtenerPagosPorIdUsuario(idUsuario);
        return ResponseEntity.ok(pagos);
    }
}