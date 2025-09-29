package com.tiago_silveirago.testepraticodtidigitaldronedelivery.controllers;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.pedido.PedidoRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.pedido.PedidoResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.services.PedidoService;
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
class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @Test
    void criar_DadosValidos_RetornaNoContent() {
        PedidoRequestDTO request = new PedidoRequestDTO(
            "Cliente 1", new double[]{1.0, 2.0}, 5.0, com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.NivelPrioridade.MEDIA
        );
        doNothing().when(pedidoService).criar(request);
        ResponseEntity<Void> response = pedidoController.criar(request);
        assertEquals(204, response.getStatusCodeValue());
        verify(pedidoService).criar(request);
    }

    @Test
    void buscarPorId_IdValido_RetornaOk() {
        String id = "123";
        PedidoResponseDTO dto = new PedidoResponseDTO(
            id, "Cliente 1", new double[]{1.0, 2.0}, 5.0,
            com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.NivelPrioridade.MEDIA,
            java.time.LocalDateTime.now(),
            com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido.CRIADO
        );
        when(pedidoService.buscarPorId(id)).thenReturn(dto);
        ResponseEntity<PedidoResponseDTO> response = pedidoController.buscarPorId(id);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
        verify(pedidoService).buscarPorId(id);
    }

    @Test
    void buscarTodos_ExistemPedidos_RetornaOk() {
        PedidoResponseDTO dto1 = new PedidoResponseDTO(
            "1", "Cliente 1", new double[]{1.0, 2.0}, 5.0,
            com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.NivelPrioridade.MEDIA,
            java.time.LocalDateTime.now(),
            com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido.CRIADO
        );
        PedidoResponseDTO dto2 = new PedidoResponseDTO(
            "2", "Cliente 2", new double[]{3.0, 4.0}, 7.0,
            com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.NivelPrioridade.ALTA,
            java.time.LocalDateTime.now(),
            com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido.EM_ANDAMENTO
        );
        List<PedidoResponseDTO> dtos = List.of(dto1, dto2);
        when(pedidoService.buscarTodos()).thenReturn(dtos);
        ResponseEntity<List<PedidoResponseDTO>> response = pedidoController.buscarTodos();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dtos, response.getBody());
        verify(pedidoService).buscarTodos();
    }
}
