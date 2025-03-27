package eci.edu.cvds.parcial02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ServicioPagos {
    
    @Autowired
    private RepositorioPagos repositorioPagos;

    public Pago procesarPago(Pago pago) {
        // las Validaciones
        if (!validarPago(pago)) {
            pago.setEstado(EstadoPago.RECHAZADO);
            pago.setMensajeRespuesta("Pago inválido");
            return pago;
        }

        // Genero ID de transacción
        pago.setIdTransaccion(UUID.randomUUID().toString());
        pago.setEstado(EstadoPago.APROBADO);
        pago.setMensajeRespuesta("Pago procesado exitosamente");

        // Calculo el monto total
        double montoTotal = pago.getElementos().stream()
            .mapToDouble(elemento -> elemento.getCantidad() * elemento.getPrecioUnitario())
            .sum();

        if (Math.abs(montoTotal - pago.getMontoTotal()) > 0.01) {
            pago.setEstado(EstadoPago.RECHAZADO);
            pago.setMensajeRespuesta("Monto total no coincide");
            return pago;
        }

        return repositorioPagos.save(pago);
    }

    public List<Pago> obtenerPagosPorIdUsuario(String idUsuario) {
        return repositorioPagos.findByIdUsuario(idUsuario);
    }

    private boolean validarPago(Pago pago) {
        return pago.getIdUsuario() != null && !pago.getIdUsuario().isEmpty()
            && pago.getElementos() != null && !pago.getElementos().isEmpty();
    }
}
