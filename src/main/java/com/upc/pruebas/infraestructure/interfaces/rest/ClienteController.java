package com.upc.pruebas.infraestructure.interfaces.rest;

import com.upc.pruebas.application.dto.ClienteRequestDTO;
import com.upc.pruebas.application.dto.ClienteResponseDTO;
import com.upc.pruebas.application.service.ClienteApplicationService;
import com.upc.pruebas.domain.exception.EMailInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {


        private final ClienteApplicationService clienteService;

        public ClienteController(ClienteApplicationService clienteService) {
            this.clienteService = clienteService;
        }

        @PostMapping
        public ResponseEntity<ClienteResponseDTO> crear(@RequestBody ClienteRequestDTO dto) {
            try {
                ClienteResponseDTO response = clienteService.crearCliente(dto);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } catch (EMailInvalidoException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // Manejo de error
            }
        }
}
