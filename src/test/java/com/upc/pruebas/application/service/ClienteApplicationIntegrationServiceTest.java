package com.upc.pruebas.application.service;

import com.upc.pruebas.application.dto.ClienteRequestDTO;
import com.upc.pruebas.application.dto.ClienteResponseDTO;
import com.upc.pruebas.domain.model.Cliente;
import com.upc.pruebas.domain.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteApplicationIntegrationServiceTest {

    private final ClienteApplicationService clienteService;
    private final ClienteRepository clienteRepository;
    @Autowired
    public ClienteApplicationIntegrationServiceTest(ClienteApplicationService clienteService, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
    }

    @Test
    void crearCliente_deberiaPersistirEnH2() {
        // Arrange
        ClienteRequestDTO dto = new ClienteRequestDTO("Ana Gómez", "ana@example.com");

        // Act
        ClienteResponseDTO response = clienteService.crearCliente(dto);

        // Assert
        Cliente cliente = clienteRepository.listarPorId(response.getId());
        assertNotNull(cliente);
        assertEquals("Ana Gómez", cliente.getNombre());
        assertEquals("ana@example.com", cliente.getEmail());
    }
}