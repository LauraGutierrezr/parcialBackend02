import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;

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
        Pago pago = new Pago();
        pago.setIdUsuario("user123");
        pago.setElementos(List.of(new ElementoPago("Articulo 1", 2, 10.0)));
        pago.setMontoTotal(20.0);

        when(repositorioPagos.save(any(Pago.class))).thenReturn(pago);

        Pago resultado = servicioPagos.procesarPago(pago);

        assertEquals(EstadoPago.APROBADO, resultado.getEstado());
        assertEquals("Pago procesado exitosamentere", resultado.getMensajeRespuesta());
    }

    @Test
    void obtenerPagosIdUsuario_RetornarListaVaciaSiNoHayPagos() {
        when(repositorioPagos.findByIdUsuario("user123")).thenReturn(Collections.emptyList());

        List<Pago> pagos = servicioPagos.obtenerPagosPorIdUsuario("user123");

        assertTrue(pagos.isEmpty());
    }
}
