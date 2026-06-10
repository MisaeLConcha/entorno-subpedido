package com.duoc.subpedido.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.duoc.subpedido.model.Subpedido;

@DataJpaTest
@ActiveProfiles("test")
public class SubpedidoRepositoryTest {
    @Autowired
    private SubpedidoRepository repository;

    @Test
    void debeBuscarPorStandId() {

    // Arrange
    Subpedido s = new Subpedido();
    s.setStandId(100L);
    repository.save(s);
    // Act
    List<Subpedido> resultado =
        repository.findByStandId(100L);
    // Assert
    assertFalse(resultado.isEmpty());
    }
}