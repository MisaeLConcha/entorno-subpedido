package com.duoc.subpedido.controller;

import com.duoc.subpedido.dto.PpordenDTO;
import com.duoc.subpedido.dto.StandDTO;
import com.duoc.subpedido.dto.SubpedidoCreateDTO;
import com.duoc.subpedido.dto.SubpedidoDTO;

import com.duoc.subpedido.model.Subpedido;
import com.duoc.subpedido.service.SubpedidoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subpedidos")
public class SubpedidoController {

    @Autowired
    private SubpedidoService service;

    // LISTAR TODOS
    @GetMapping
    public List<Subpedido> listarTodos() {
        return service.listarTodos();
    }

    // OBTENER POR ID
    @GetMapping("/{id}")
    public ResponseEntity<SubpedidoDTO> obtenerPorId(
            @PathVariable Long id) {

        SubpedidoDTO dto = service.findDtoById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    // CREAR SUBPEDIDO
    @PostMapping("/generar")
    public ResponseEntity<SubpedidoDTO> crearSubpedido(
            @Valid @RequestBody SubpedidoCreateDTO dto) {
        SubpedidoDTO creado = service.crearSubpedido(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // LISTAR POR STAND
    @GetMapping("/stand/{standId}")
    public List<Subpedido> listarPorStand(
            @PathVariable Long standId) {

        return service.listarPorStand(standId);
    }

    // LISTAR POR PEDIDO
    @GetMapping("/pedido/{pedidoId}")
    public List<Subpedido> listarPorPedido(
            @PathVariable Long pedidoId) {

        return service.listarPorPedido(pedidoId);
    }

    // ASIGNAR STAND
    @PutMapping("/{id}/asignarStand/{standId}")
    public ResponseEntity<Subpedido> asignarStand(
            @PathVariable Long id,
            @PathVariable Long standId) {

        Subpedido actualizado = service.asignarStand(id, standId);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    // ACTUALIZAR ESTADO
    @PutMapping("/{id}/estado")
    public ResponseEntity<Subpedido> actualizarEstado(
            @PathVariable Long id,
            @RequestBody Subpedido subPedido) {

        Subpedido actualizado =
                service.actualizarEstado(id, subPedido.getEstado());
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    // PROBAR MICROSERVICIO STAND
    @GetMapping("/stand/externo")
    public StandDTO probarStand(
            @PathVariable Long id) {

        return service.obtenerStand(id);
    }

    // PROBAR MICROSERVICIO PEDIDO
    @GetMapping("/pedido/externo")
    public PpordenDTO probarPporden(
            @PathVariable Long id) {

        return service.obtenerPporden(id);
    }
}