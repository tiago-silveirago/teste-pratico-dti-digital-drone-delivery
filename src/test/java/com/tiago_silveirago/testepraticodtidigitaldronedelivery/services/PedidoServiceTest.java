package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.pedido.PedidoRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.pedido.PedidoResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Pedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido.CRIADO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    // Sucesso: criar pedido com dados válidos
    @Test
    void criar_DadosValidos_SalvaPedido() {
        PedidoRequestDTO request = new PedidoRequestDTO(
            "Cliente 1", new double[]{1.0, 2.0}, 5.0, com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.NivelPrioridade.MEDIA
        );
        Pedido pedido = new Pedido();
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
        pedidoService.criar(request);
        verify(pedidoRepository).save(any(Pedido.class));
    }

    // Sucesso: buscar por ID válido
    @Test
    void buscarPorId_IdValido_RetornaResponseDTO() {
        String id = "123";
        Pedido pedido = new Pedido();
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        assertDoesNotThrow(() -> pedidoService.buscarPorId(id));
        verify(pedidoRepository).findById(id);
    }

    // Falha: buscar por ID inválido
    @Test
    void buscarPorId_IdInvalido_LancaExcecao() {
        String id = "invalido";
        when(pedidoRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> pedidoService.buscarPorId(id));
    }

    // Sucesso: buscar todos os pedidos
    @Test
    void buscarTodos_PedidosExistem_RetornaListaResponseDTO() {
        List<Pedido> pedidos = List.of(new Pedido(), new Pedido());
        when(pedidoRepository.findAll()).thenReturn(pedidos);
        assertDoesNotThrow(() -> pedidoService.buscarTodos());
        verify(pedidoRepository).findAll();
    }

    // Sucesso: recuperar pedidos por prioridade
    @Test
    void recuperarPedidosPorPrioridade_StatusCriado_RetornaPedidosOrdenados() {
        List<Pedido> pedidos = List.of(new Pedido(), new Pedido());
        when(pedidoRepository.findByStatusPedidoOrderByNivelPrioridadeAscDataCriacaoAsc(com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido.CRIADO))
                .thenReturn(pedidos);
        List<Pedido> result = pedidoService.recuperarPedidosPorPrioridade();
        assertEquals(pedidos, result);
    }
}
