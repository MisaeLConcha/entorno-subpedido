package com.duoc.subpedido.service;

import com.duoc.subpedido.dto.SubpedidoDTO;
import com.duoc.subpedido.dto.SubpedidoCreateDTO;
import com.duoc.subpedido.dto.PpordenDTO;
import com.duoc.subpedido.dto.StandDTO;

import com.duoc.subpedido.client.PpordenClient;
import com.duoc.subpedido.client.StandClient;

import com.duoc.subpedido.model.Subpedido;
import com.duoc.subpedido.repository.SubpedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//importar exception
import com.duoc.subpedido.exception.ResourceNotFoundException;
import feign.FeignException;

//importar loggs
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class SubpedidoService {

    @Autowired
    private SubpedidoRepository repository;
    @Autowired
    private StandClient standClient;
    @Autowired
    private PpordenClient ppordenClient;

    private static final Logger log =
        LoggerFactory.getLogger(SubpedidoService.class);

    // generarSubpedidos
    public Subpedido generarSubpedido(Subpedido subPedido) {
        subPedido.setEstado("PENDIENTE");
        return repository.save(subPedido);
    }

    // obtenerSubpedidoPorId
    public Subpedido obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    //pa listar todo
    public List<Subpedido> listarTodos() {
    return repository.findAll();
    }

    // listarSubpedidosPorStand
    public List<Subpedido> listarPorStand(Long standId) {
        return repository.findByStandId(standId);
    }

    // listarSubpedidosPorPedido
    public List<Subpedido> listarPorPedido(Long pedidoId) {
        return repository.findByPedidoId(pedidoId);
    }

    // asignarSubpedidoAStand
    public Subpedido asignarStand(Long id, Long standId) {
        log.info("Asignando stand a subpedido. subpedidoId={}, standId={}",id,standId);

        Subpedido sp = repository.findById(id)
        .orElseThrow(() ->{
            log.warn("No se pudo asignar stand. Subpedido no encontrado. id={}",id);
        
            return new ResourceNotFoundException(
                    "Subpedido no encontrado con ID: " + id
            );
        });
        sp.setStandId(standId);
        Subpedido actualizado = repository.save(sp);
        log.info("Stand asignado correctamente. subpedidoId={}, standId={}",id,standId);

        return actualizado;
    }

    // actualizarEstadoSubpedido
    public Subpedido actualizarEstado(Long id, String estado) {
        log.info("Actualizando estado de subpedido. id={}, nuevoEstado={}",id,estado);
        Subpedido sp = repository.findById(id)
            .orElseThrow(() -> {
                log.warn("No se pudo actualizar estado. Subpedido no encontrado. id={}",id);
            
                return new ResourceNotFoundException(
                        "Subpedido no encontrado con ID: " + id);
                });
        sp.setEstado(estado);
        Subpedido actualizado = repository.save(sp);
        log.info("Estado actualizado correctamente. id={}, estado={}",id,estado);
        return actualizado;
    }

    public SubpedidoDTO findDtoById(Long id) {
        log.info("Buscando subpedido con id={}", id);

        Subpedido p = repository.findById(id)
            .orElseThrow(() -> {
                log.warn("Subpedido no encontrado. id={}", id);
                return new ResourceNotFoundException(
                        "Subpedido no encontrado con ID: " + id);
            });

        log.info("Subpedido encontrado. id={}", id);
        return new SubpedidoDTO(
            p.getId(),
            p.getPedidoId(),
            p.getStandId(),
            p.getDescripcion(),
            p.getEstado()
        );
    }


    public SubpedidoDTO crearSubpedido(SubpedidoCreateDTO dto) {

    log.info("Creando subpedido para pedidoId={} y standId={}",
        dto.getPedidoId(),
        dto.getStandId());

    obtenerStand(dto.getStandId());
    obtenerPporden(dto.getPedidoId());

    Subpedido p = new Subpedido();

    p.setPedidoId(dto.getPedidoId());
    p.setStandId(dto.getStandId());
    p.setDescripcion(dto.getDescripcion());
    p.setEstado(dto.getEstado());

    Subpedido guardado = repository.save(p);

    log.info("Subpedido creado exitosamente con id={}",
        guardado.getId());

    return new SubpedidoDTO(
        guardado.getId(),
        guardado.getPedidoId(),
        guardado.getStandId(),
        guardado.getDescripcion(),
        guardado.getEstado()
    );
    }

    public StandDTO obtenerStand(Long id) {
    try {
        log.info("Consultando microservicio Stand con id={}", id);
        return standClient.getStandById(id);
    } catch (FeignException.NotFound ex) {
        log.warn("Stand no encontrado. id={}", id);
        throw new ResourceNotFoundException(
            "Stand no encontrado con ID: " + id
        );
    } catch (FeignException ex) {
        log.error(
            "Error comunicándose con microservicio Stand. id={}",id,ex);
        throw new RuntimeException(
            "Error al comunicarse con microservicio Stand"
        );
    }
}
    public PpordenDTO obtenerPporden(Long id) {
    try {
        log.info("Consultando microservicio Pedido con id={}", id);
        return ppordenClient.getPpordenById(id);
    } catch (FeignException.NotFound ex) {
        log.warn("Pedido no encontrado. id={}", id);
        throw new ResourceNotFoundException(
            "Pedido no encontrado con ID: " + id
        );
    } catch (FeignException ex) {
        log.error(
            "Error comunicándose con microservicio Pedido. id={}",id,ex);
        throw new RuntimeException(
            "Error al comunicarse con microservicio Pedido"
        );
    }
}
}