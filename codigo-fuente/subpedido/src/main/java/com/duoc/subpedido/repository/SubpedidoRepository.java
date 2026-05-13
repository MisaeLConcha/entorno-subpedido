package com.duoc.subpedido.repository;

import com.duoc.subpedido.model.Subpedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubpedidoRepository extends JpaRepository<Subpedido, Long> {

    List<Subpedido> findByStandId(Long standId);

    List<Subpedido> findByPedidoId(Long pedidoId);
}