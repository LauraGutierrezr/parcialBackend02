package eci.edu.cvds.parcial02;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "pagos")
public class Pago {
    @Id
    private String idTransaccion;
    private String idUsuario;
    private List<ElementoPago> elementos;
    private Double montoTotal;
    private LocalDate fechaCompra;
    private EstadoPago estado;
    private String mensajeRespuesta;

    //establecer fecha de compra
    public void setFechaCompra(String fecha) {
        if (fecha == null || !fecha.matches("\\d{2}-\\d{2}-\\d{4}")) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use DD-MM-YYYY");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.fechaCompra = LocalDate.parse(fecha, formatter);
    }

    public double calcularMontoTotal() {
        return elementos.stream()
            .mapToDouble(ElementoPago::calcularSubtotal)
            .sum();
    }

    // Verificar si el pago es válido
    public boolean esValido() {
        if (idUsuario == null || idUsuario.trim().isEmpty()) return false;
        if (elementos == null || elementos.isEmpty()) return false;
        
        return elementos.stream().allMatch(ElementoPago::esValido);
    }

    public void generarIdTransaccion() {
        this.idTransaccion = UUID.randomUUID().toString();
    }
}