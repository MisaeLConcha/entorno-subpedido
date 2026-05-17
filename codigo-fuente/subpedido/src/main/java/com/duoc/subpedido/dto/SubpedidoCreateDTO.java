package com.duoc.subpedido.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubpedidoCreateDTO {

    @NotNull(message = "El pedidoId es obligatorio")
    private Long pedidoId;

    @NotNull(message = "El standId es obligatorio")
    private Long standId;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 3, max = 255,
            message = "La descripción debe tener entre 3 y 255 caracteres")
    private String descripcion;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}