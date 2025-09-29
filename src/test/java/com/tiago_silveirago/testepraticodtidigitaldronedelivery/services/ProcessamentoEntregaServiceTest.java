package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusEntrega;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.DadosDoPedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Entrega;
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
class ProcessamentoEntregaServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private DroneRepository droneRepository;
    @Mock
    private EntregaRepository entregaRepository;

    @InjectMocks
    private ProcessamentoEntregaService processamentoEntregaService;

    @Test
    void processarEntrega_EntregaComPedidos_ProcessaCorretamente() throws InterruptedException {
        Entrega entrega = new Entrega();
        DadosDoPedido dados = new DadosDoPedido("pedido-1", new double[]{1.0, 2.0}, false);
        entrega.setDadosDosPedidos(List.of(dados));
        entrega.setDroneId("drone-1");
        // Apenas métodos void devem usar doNothing()
        // save retorna o objeto salvo, então pode-se usar when(...).thenReturn(...)
        when(entregaRepository.save(any(Entrega.class))).thenReturn(entrega);
        when(droneRepository.updateStatusById(any(), any())).thenReturn(1L);
        when(pedidoRepository.updateStatusById(any(), any())).thenReturn(1L);
        assertDoesNotThrow(() -> processamentoEntregaService.processarEntrega(entrega));
        verify(entregaRepository, atLeastOnce()).save(entrega);
        verify(droneRepository, atLeastOnce()).updateStatusById(anyString(), any(StatusDrone.class));
        verify(pedidoRepository, atLeastOnce()).updateStatusById(anyString(), any(com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido.class));
    }
}
