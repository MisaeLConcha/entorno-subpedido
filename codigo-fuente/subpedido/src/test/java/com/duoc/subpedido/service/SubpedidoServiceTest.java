package com.duoc.subpedido.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.duoc.subpedido.repository.SubpedidoRepository;
import com.duoc.subpedido.client.PpordenClient;
import com.duoc.subpedido.client.StandClient;
import com.duoc.subpedido.exception.ResourceNotFoundException;
import com.duoc.subpedido.model.Subpedido;


@ExtendWith(MockitoExtension.class)
public class SubpedidoServiceTest {
    @Mock
    private SubpedidoRepository repository;

    @Mock
    private StandClient standClient;

    @Mock
    private PpordenClient ppordenClient;

    @InjectMocks
    private SubpedidoService service;

    @Test
    void debeGenerarSubpedidoConEstadoPendiente() {

        // Arrange
        Subpedido subpedido = new Subpedido();
        when(repository.save(any(Subpedido.class)))
                .thenAnswer(i -> i.getArgument(0));
        // Act
        Subpedido resultado = service.generarSubpedido(subpedido);
        // Assert
        assertEquals("PENDIENTE", resultado.getEstado());
        verify(repository).save(any(Subpedido.class));
    }


    @Test
    void debeRetornarSubpedidoCuandoExiste() {

        // Arrange
        Subpedido subpedido = new Subpedido();
        subpedido.setId(1L);
        when(repository.findById(1L))
                .thenReturn(Optional.of(subpedido));
        // Act
        Subpedido resultado = service.obtenerPorId(1L);
        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void debeAsignarStandCorrectamente() {

        // Arrange
        Subpedido subpedido = new Subpedido();
        when(repository.findById(1L))
                .thenReturn(Optional.of(subpedido));
        when(repository.save(any(Subpedido.class)))
                .thenAnswer(i -> i.getArgument(0));
        // Act
        Subpedido resultado =
                service.asignarStand(1L, 100L);
        // Assert
        assertEquals(100L, resultado.getStandId());
        verify(repository).save(subpedido);
    }


    @Test
    void debeActualizarEstadoCorrectamente() {

        // Arrange
        Subpedido subpedido = new Subpedido();
        when(repository.findById(1L))
                .thenReturn(Optional.of(subpedido));
        when(repository.save(any(Subpedido.class)))
                .thenAnswer(i -> i.getArgument(0));
        // Act
        Subpedido resultado =
                service.actualizarEstado(1L, "ENTREGADO");
        // Assert
        assertEquals("ENTREGADO", resultado.getEstado());
    }
    @Test
    void debeLanzarExcepcionCuandoSubpedidoNoExiste() {

    // Arrange
        when(repository.findById(999L))
                .thenReturn(Optional.empty());
        // Act + Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> service.asignarStand(999L, 100L)
        );
        }
}