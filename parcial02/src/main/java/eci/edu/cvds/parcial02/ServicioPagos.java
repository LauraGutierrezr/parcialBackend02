package eci.edu.cvds.parcial02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicioPagos {

    @Autowired
    private RepositorioPagos repositorioPagos;

    public Pago procesarPago(Pago pago) {
        if (!pago.esValido()) {
            pago.setEstado(EstadoPago.RECHAZADO);
            pago.setMensajeRespuesta("Pago inválido: Datos incompletos o incorrectos");
            return pago;
        }

        double montoCalculado = pago.calcularMontoTotal();

        // El monto total si coincide?
        if (Math.abs(montoCalculado - pago.getMontoTotal()) > 0.01) {
            pago.setEstado(EstadoPago.RECHAZADO);
            pago.setMensajeRespuesta("Monto total no coincide");
            return pago;
        }

        // Generar ID de transacción del usre
        pago.generarIdTransaccion();
        pago.setEstado(EstadoPago.APROBADO);
        pago.setMensajeRespuesta("Pago procesado exitosamente");

        return repositorioPagos.save(pago);
    }

    public List<Pago> obtenerPagosPorIdUsuario(String idUsuario) {
        return repositorioPagos.findByIdUsuario(idUsuario);
    }
}