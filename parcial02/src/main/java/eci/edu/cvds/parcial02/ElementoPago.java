package eci.edu.cvds.parcial02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementoPago {
    private String nombreArticulo;
    private Integer cantidad;
    private Double precioUnitario;

    // Valido que la cantidad y precio unitario si sean posiivos
    public boolean esValido() {
        return cantidad != null && cantidad > 0 && 
               precioUnitario != null && precioUnitario > 0;
    }

    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }
}