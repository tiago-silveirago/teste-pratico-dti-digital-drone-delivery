package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.PedidoRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Pedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers.PedidoMapper.requestParaPedido;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public void criar(PedidoRequestDTO request) {
        Pedido pedido = requestParaPedido(request);
        pedidoRepository.save(pedido);
    }

    public List<Pedido> recuperarPedidosPorPrioridade() {
        List<Pedido> pedidos = pedidoRepository.findByStatusPedidoOrderByNivelPrioridadeAscDataCriacaoAsc(StatusPedido.CRIADO);
        return pedidos;
    }
}
