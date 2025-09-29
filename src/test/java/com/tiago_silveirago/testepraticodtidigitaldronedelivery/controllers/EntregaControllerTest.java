package com.tiago_silveirago.testepraticodtidigitaldronedelivery.controllers;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.entregas.EntregaResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.services.ConsultaEntregaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntregaControllerTest {

    @Mock
    private ConsultaEntregaService consultaEntregaService;

    @InjectMocks
    private EntregaController entregaController;

    @Test
    void buscarPorId_IdValido_RetornaOk() {
        String id = "123";
        EntregaResponseDTO dto = new EntregaResponseDTO(
            id,
            com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusEntrega.AGUARDANDO,
            java.time.LocalDateTime.now(),
            java.time.LocalDateTime.now(),
            java.util.Collections.emptyList(),
            "drone-1"
        );
        when(consultaEntregaService.buscarPorId(id)).thenReturn(dto);
        ResponseEntity<EntregaResponseDTO> response = entregaController.buscarPorId(id);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
        verify(consultaEntregaService).buscarPorId(id);
    }

    @Test
    void buscarTodos_ExistemEntregas_RetornaOk() {
        EntregaResponseDTO dto1 = new EntregaResponseDTO(
            "1",
            com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusEntrega.AGUARDANDO,
            java.time.LocalDateTime.now(),
            java.time.LocalDateTime.now(),
            java.util.Collections.emptyList(),
            "drone-1"
        );
        EntregaResponseDTO dto2 = new EntregaResponseDTO(
            "2",
            com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusEntrega.ENTREGUE,
            java.time.LocalDateTime.now(),
            java.time.LocalDateTime.now(),
            java.util.Collections.emptyList(),
            "drone-2"
        );
        java.util.List<EntregaResponseDTO> dtos = java.util.List.of(dto1, dto2);
        when(consultaEntregaService.buscarTodos()).thenReturn(dtos);
        ResponseEntity<java.util.List<EntregaResponseDTO>> response = entregaController.buscarTodos();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dtos, response.getBody());
        verify(consultaEntregaService).buscarTodos();
    }
}
