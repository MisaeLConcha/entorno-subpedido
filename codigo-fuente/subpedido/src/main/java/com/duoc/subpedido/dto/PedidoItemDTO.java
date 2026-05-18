package com.duoc.subpedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoItemDTO {

    private Long id;

    private String nombreProducto;

    private int cantidad;

    private double precio;
}