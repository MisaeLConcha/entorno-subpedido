package com.duoc.subpedido.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.duoc.subpedido.dto.SubpedidoDTO;
import com.duoc.subpedido.model.Subpedido;
import com.duoc.subpedido.service.SubpedidoService;

@WebMvcTest(SubpedidoController.class)

public class SubpedidoControllerTest {
    @MockBean
    private SubpedidoService service;

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void debeRetornarListaSubpedidos() throws Exception {

        // Arrange
        List<Subpedido> lista = List.of(new Subpedido());
        when(service.listarTodos())
                .thenReturn(lista);
        // Act + Assert
        mockMvc.perform(get("/api/v1/subpedidos"))
                .andExpect(status().isOk());
    }


    @Test
    void debeRetornarSubpedidoPorId() throws Exception {

            // Arrange
        SubpedidoDTO dto =
                new SubpedidoDTO(
                    1L,
                    1L,
                    1L,
                    "Prueba",
                    "PENDIENTE");
        when(service.findDtoById(1L))
            .thenReturn(dto);
            // Act + Assert
        mockMvc.perform(
            get("/api/v1/subpedidos/1"))
            .andExpect(status().isOk());
    }
}