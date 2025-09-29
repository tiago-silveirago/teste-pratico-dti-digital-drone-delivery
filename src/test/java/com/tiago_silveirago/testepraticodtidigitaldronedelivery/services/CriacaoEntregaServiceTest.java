package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Drone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Entrega;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Pedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.DroneRepository;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.EntregaRepository;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriacaoEntregaServiceTest {

    @Mock
    private DroneService droneService;
    @Mock
    private PedidoService pedidoService;
    @Mock
    private ProcessamentoEntregaService processamentoEntregaService;
    @Mock
    private EntregaRepository entregaRepository;
    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private DroneRepository droneRepository;

    @InjectMocks
    private CriacaoEntregaService criacaoEntregaService;

    @Test
    void criarEntrega_PedidosDisponiveis_CriaEntrega() throws InterruptedException {
        Drone droneLivre = new Drone();
        droneLivre.setId("drone-1");
        droneLivre.setNome("Drone 1");
        droneLivre.setCapacidadePeso(10.0);
        droneLivre.setCapacidadeDeslocamento(100.0);
        droneLivre.setStatusDrone(com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone.IDDLE);
        droneLivre.setLocalizacaoAtual(new double[]{0.0, 0.0});
        Pedido pedido = new Pedido();
        pedido.setId("pedido-1");
        pedido.setCliente("Cliente 1");
        pedido.setLocalizacaoDestino(new double[]{1.0, 2.0});
        pedido.setPesoPacote(5.0);
        pedido.setNivelPrioridade(com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.NivelPrioridade.MEDIA);
        pedido.setDataCriacao(java.time.LocalDateTime.now());
        pedido.setStatusPedido(com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido.CRIADO);
        List<Pedido> pedidos = List.of(pedido);
        Entrega entrega = new Entrega();
        Entrega entregaCriada = new Entrega();

        when(droneService.buscarDroneLivre()).thenReturn(droneLivre);
        when(pedidoService.recuperarPedidosPorPrioridade()).thenReturn(pedidos);
        criacaoEntregaService.criarEntrega();
        verify(droneService).buscarDroneLivre();
        verify(pedidoService).recuperarPedidosPorPrioridade();
    }

    @Test
    void criarEntrega_SemPedidos_NaoCriaEntrega() throws InterruptedException {
        Drone droneLivre = new Drone();
        when(droneService.buscarDroneLivre()).thenReturn(droneLivre);
        when(pedidoService.recuperarPedidosPorPrioridade()).thenReturn(List.of());
        criacaoEntregaService.criarEntrega();
        verify(droneService).buscarDroneLivre();
        verify(pedidoService).recuperarPedidosPorPrioridade();
        verifyNoInteractions(entregaRepository);
    }
}
