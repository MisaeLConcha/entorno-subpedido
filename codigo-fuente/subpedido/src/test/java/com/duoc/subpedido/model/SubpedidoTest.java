package com.duoc.subpedido.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubpedidoTest {

    @Test
    void debeCrearSubpedidoCorrectamente() {

        // Arrange
        Subpedido subpedido = new Subpedido();

        // Act
        subpedido.setId(1L);
        subpedido.setPedidoId(10L);
        subpedido.setStandId(20L);
        subpedido.setDescripcion("Prueba");
        subpedido.setEstado("PENDIENTE");

        // Assert
        assertEquals(1L, subpedido.getId());
        assertEquals(10L, subpedido.getPedidoId());
        assertEquals(20L, subpedido.getStandId());
        assertEquals("Prueba", subpedido.getDescripcion());
        assertEquals("PENDIENTE", subpedido.getEstado());
    }
}