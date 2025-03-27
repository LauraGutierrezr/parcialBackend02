package eci.edu.cvds.parcial02;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Date;

@Data
@Document(collection = "pagos")
public class Pago {
    @Id
    private String idTransaccion;
    private String idUsuario;
    private List<ElementoPago> elementos;
    private Double montoTotal;
    private Date fechaCompra;
    private EstadoPago estado;
    private String mensajeRespuesta;
}



