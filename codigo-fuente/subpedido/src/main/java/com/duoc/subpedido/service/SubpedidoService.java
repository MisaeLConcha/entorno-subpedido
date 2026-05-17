package com.duoc.subpedido.service;

import com.duoc.subpedido.dto.SubpedidoDTO;
import com.duoc.subpedido.dto.SubpedidoCreateDTO;

import com.duoc.subpedido.model.Subpedido;
import com.duoc.subpedido.repository.SubpedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubpedidoService {

    @Autowired
    private SubpedidoRepository repository;
    @Autowired
    private StandClient standClient;
    @Autowired
    private PpordenClient ppordenClient;

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

        Subpedido sp = repository.findById(id).orElse(null);
        if (sp != null) {
            sp.setStandId(standId);
            return repository.save(sp);
        }
        return null;
    }

    // actualizarEstadoSubpedido
    public Subpedido actualizarEstado(Long id, String estado) {

        Subpedido sp = repository.findById(id).orElse(null);
        if (sp != null) {
            sp.setEstado(estado);
            return repository.save(sp);
        }
        return null;
    }

    public SubpedidoDTO findDtoById(Long id) {

        Subpedido p = repository.findById(id).orElse(null);

        if (p == null) {
            return null;
        }

        return new SubpedidoDTO(
            p.getId(),
            p.getPedidoId(),
            p.getStandId(),
            p.getDescripcion(),
            p.getEstado()
        );
    }


    public SubpedidoDTO crearSubpedido(SubpedidoCreateDTO dto) {

        // 1. DTO -> ENTITY
        Subpedido p = new Subpedido();

        p.setPedidoId(dto.getPedidoId());
        p.setStandId(dto.getStandId());
        p.setDescripcion(dto.getDescripcion());
        p.setEstado(dto.getEstado());

        // 2. GUARDAR EN BD
        Subpedido guardado = repository.save(p);

        // 3. ENTITY -> DTO
        return new SubpedidoDTO(
            guardado.getId(),
            guardado.getPedidoId(),
            guardado.getStandId(),
            guardado.getDescripcion(),
            guardado.getEstado()
        );
    }

    public StandDTO obtenerStand(Long id) {
        return standClient.getStandById(id);
    }

    public PpordenDTO obtenerPporden(Long id){
        return ppordenClient.getPpordenById(id)
    }
}