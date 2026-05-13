package com.duoc.subpedido.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "subpedidos")
public class Subpedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private Long pedidoId;
    private Long standId;
    private String descripcion;
    private String estado;
}