package eci.edu.cvds.parcial02;

import lombok.Data;

@Data
public class ElementoPago {
    private String nombreArticulo;
    private Integer cantidad;
    private Double precioUnitario;
}
