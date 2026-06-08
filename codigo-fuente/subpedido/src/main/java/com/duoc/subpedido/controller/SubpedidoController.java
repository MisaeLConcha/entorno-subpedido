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

// Imports de Swagger (agregar estos)
import com.duoc.subpedido.config.SwaggerConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name= "Subpedidos", description = "Operaciones para añadir un Subpedido a nuestro sistema")
@RestController
@RequestMapping("/api/v1/subpedidos")
public class SubpedidoController {

    @Autowired
    private SubpedidoService service;

    // LISTAR TODOS
    @Operation(summary = "Listar todas los subpedidos",
               description = "Retorna la lista completa de subpedidos registradas en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public List<Subpedido> listarTodos() {
        return service.listarTodos();
    }

    // OBTENER POR ID
    @Operation(summary = "Buscar Subpedidos por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Subpedido encontrada"),
        @ApiResponse(responseCode = "404", description = "Subpedido no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SubpedidoDTO> obtenerPorId(
        @PathVariable Long id) {

        return ResponseEntity.ok(
            service.findDtoById(id)
    );
}

    // CREAR SUBPEDIDO
    @Operation(summary = "Registrar nuevo Subpedido con endopoint /generar")
    @ApiResponse(responseCode = "201", description = "Subpedido creado exitosamente")
    @PostMapping("/generar")
    public ResponseEntity<SubpedidoDTO> crearSubpedido(
            @Valid @RequestBody SubpedidoCreateDTO dto) {
        SubpedidoDTO creado = service.crearSubpedido(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // LISTAR POR STAND
    @Operation(summary = "Buscar Subpedidos por Stand")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Subpedido encontrada"),
        @ApiResponse(responseCode = "404", description = "Subpedido no encontrada")
    })
    @GetMapping("/stand/{standId}")
    public List<Subpedido> listarPorStand(
            @PathVariable Long standId) {

        return service.listarPorStand(standId);
    }

    // LISTAR POR PEDIDO
    @Operation(summary = "Buscar Subpedidos por IDSubpedido")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Subpedido encontrada"),
        @ApiResponse(responseCode = "404", description = "Subpedido no encontrada")
    })
    @GetMapping("/pedido/{pedidoId}")
    public List<Subpedido> listarPorPedido(
            @PathVariable Long pedidoId) {

        return service.listarPorPedido(pedidoId);
    }

    // ASIGNAR STAND
    @Operation(summary = "Actualizar Stand existente con endpoint /{id}/asignarStand/{standId}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actualización exitosa"),
        @ApiResponse(responseCode = "404", description = "Subpedido no encontrada")
    })
    @PutMapping("/{id}/asignarStand/{standId}")
    public ResponseEntity<Subpedido> asignarStand(
        @PathVariable Long id,
        @PathVariable Long standId) {

    return ResponseEntity.ok(
        service.asignarStand(id, standId)
    );
}

    // ACTUALIZAR ESTADO
    @Operation(summary = "Actualizar Estado existente con endpoint /{id}/estado")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actualización exitosa"),
        @ApiResponse(responseCode = "404", description = "Subpedido no encontrada")
    })
    @PutMapping("/{id}/estado")
    public ResponseEntity<Subpedido> actualizarEstado(
        @PathVariable Long id,
        @RequestBody Subpedido subPedido) {

    return ResponseEntity.ok(
        service.actualizarEstado(id, subPedido.getEstado())
    );
}

    // PROBAR MICROSERVICIO STAND
    @Operation(summary = "Buscar Stand en otro microservicio")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Stand encontrada"),
        @ApiResponse(responseCode = "404", description = "Stand no encontrada")
    })
    @GetMapping("/stand/externo/{id}")
    public StandDTO probarStand(
            @PathVariable Long id) {

        return service.obtenerStand(id);
    }

    // PROBAR MICROSERVICIO PEDIDO
    @Operation(summary = "Buscar Pedido en otro microservicio")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pedido encontrada"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrada")
    })
    @GetMapping("/pedido/externo/{id}")
    public PpordenDTO probarPporden(
            @PathVariable Long id) {

        return service.obtenerPporden(id);
    }

    /*
    @Operation(summary = "Eliminar Subpedido")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Eliminación exitosa"),
        @ApiResponse(responseCode = "404", description = "Subpedido no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @Parameter(description = "ID del Subpedido a eliminar")
            @PathVariable Long id) {
    */
}