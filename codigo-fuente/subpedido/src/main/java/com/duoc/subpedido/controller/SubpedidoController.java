package com.duoc.subpedido.controller;

import com.duoc.subpedido.model.Subpedido;
import com.duoc.subpedido.service.SubpedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subpedidos")
public class SubpedidoController {

    @Autowired
    private SubpedidoService service;

    //obtener tdo
    @GetMapping
    public List<Subpedido> listarTodos() {
    return service.listarTodos();
}

    // generarSubpedidos
    @PostMapping("/generar") //probar con ester endopoint
    public Subpedido generar(@RequestBody Subpedido subPedido) {
        return service.generarSubpedido(subPedido);
    }

    // obtenerSubpedidoPorId
    @GetMapping("/{id}")
    public Subpedido obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // listarSubpedidosPorStand
    @GetMapping("/stand/{standId}")
    public List<Subpedido> listarPorStand(@PathVariable Long standId) {
        return service.listarPorStand(standId);
    }

    // listarSubpedidosPorPedido
    @GetMapping("/pedido/{pedidoId}")
    public List<Subpedido> listarPorPedido(@PathVariable Long pedidoId) {
        return service.listarPorPedido(pedidoId);
    }

    // asignarSubpedidoAStand
    @PutMapping("/{id}/asignarStand/{standId}")
    public Subpedido asignarStand(
            @PathVariable Long id,
            @PathVariable Long standId) {

        return service.asignarStand(id, standId);
    }

    // actualizarEstadoSubpedido
    @PutMapping("/{id}/estado")
    public Subpedido actualizarEstado(
        @PathVariable Long id,
        @RequestBody Subpedido subPedido) {

    return service.actualizarEstado(id, subPedido.getEstado());
}
}