import eci.edu.cvds.parcial02.RepositorioPagos;
import eci.edu.cvds.parcial02.Pago;
import eci.edu.cvds.parcial02.ElementoPago;
import eci.edu.cvds.parcial02.EstadoPago;
import eci.edu.cvds.parcial02.ServicioPagos;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;

class ServicioPagosTest {
    @Mock
    private RepositorioPagos repositorioPagos;

    @InjectMocks
    private ServicioPagos servicioPagos;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void procesarPago_AprobarPagoValido() {
        // Preparar un pago completamente válido
        Pago pago = new Pago();
        pago.setIdUsuario("user123");
        pago.setFechaCompra("15-03-2024");
        pago.setElementos(List.of(
            new ElementoPago("Articulo 1", 2, 10.0),
            new ElementoPago("Articulo 2", 1, 15.0)
        ));
        pago.setMontoTotal(35.0);

        when(repositorioPagos.save(any(Pago.class))).thenReturn(pago);

        Pago resultado = servicioPagos.procesarPago(pago);

        assertNotNull(resultado.getIdTransaccion(), "Debe generar un ID de transacción");
        assertEquals(EstadoPago.APROBADO, resultado.getEstado());
        assertEquals("Pago procesado exitosamente", resultado.getMensajeRespuesta());
        verify(repositorioPagos).save(pago);
    }

    @Test
    void procesarPago_RechazarMontoIncorrecto() {
        Pago pago = new Pago();
        pago.setIdUsuario("user123");
        pago.setFechaCompra("15-03-2024");
        pago.setElementos(List.of(
            new ElementoPago("Articulo 1", 2, 10.0),
            new ElementoPago("Articulo 2", 1, 15.0)
        ));
        pago.setMontoTotal(40.0); // Monto incorrectos

        Pago resultado = servicioPagos.procesarPago(pago);

        assertEquals(EstadoPago.RECHAZADO, resultado.getEstado());
        assertEquals("Monto total no coincide", resultado.getMensajeRespuesta());
    }

    @Test
    void procesarPago_RechazarPagoSinUsuario() {
        Pago pago = new Pago();
        pago.setIdUsuario("");
        pago.setFechaCompra("15-03-2024");
        pago.setElementos(List.of(
            new ElementoPago("Articulo 1", 2, 10.0)
        ));
        pago.setMontoTotal(20.0);

        Pago resultado = servicioPagos.procesarPago(pago);

        assertEquals(EstadoPago.RECHAZADO, resultado.getEstado());
        assertEquals("Pago inválido: Datos incompletos o incorrectos", resultado.getMensajeRespuesta());
    }

    @Test
    void procesarPago_RechazarElementoInvalido() {
        Pago pago = new Pago();
        pago.setIdUsuario("user123");
        pago.setFechaCompra("15-03-2024");
        pago.setElementos(List.of(
            new ElementoPago("Articulo 1", -1, 10.0) // Cantidad inválida
        ));
        pago.setMontoTotal(20.0);

        Pago resultado = servicioPagos.procesarPago(pago);

        assertEquals(EstadoPago.RECHAZADO, resultado.getEstado());
        assertEquals("Pago inválido: Datos incompletos o incorrectos", resultado.getMensajeRespuesta());
    }

    @Test
    void obtenerPagosIdUsuario_RetornarListaVaciaSiNoHayPagos() {
        when(repositorioPagos.findByIdUsuario("user123")).thenReturn(Collections.emptyList());

        List<Pago> pagos = servicioPagos.obtenerPagosPorIdUsuario("user123");

        assertTrue(pagos.isEmpty());
    }

    @Test
    void obtenerPagosIdUsuario_RetornarPagosExistentes() {
        Pago pago1 = new Pago();
        pago1.setIdUsuario("user123");
        Pago pago2 = new Pago();
        pago2.setIdUsuario("user123");

        when(repositorioPagos.findByIdUsuario("user123")).thenReturn(Arrays.asList(pago1, pago2));

        List<Pago> pagos = servicioPagos.obtenerPagosPorIdUsuario("user123");

        assertFalse(pagos.isEmpty());
        assertEquals(2, pagos.size());
    }
}