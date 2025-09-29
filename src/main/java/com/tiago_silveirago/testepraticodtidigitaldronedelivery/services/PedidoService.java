package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.pedido.PedidoRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.pedido.PedidoResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Pedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers.PedidoMapper.pedidoParaResponse;
import static com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers.PedidoMapper.requestParaPedido;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public void criar(PedidoRequestDTO request) {
        Pedido pedido = requestParaPedido(request);
        pedidoRepository.save(pedido);
    }

    public PedidoResponseDTO buscarPorId(String id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("ID de pedido inválido"));
        return pedidoParaResponse(pedido);
    }

    public List<PedidoResponseDTO> buscarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidoParaResponse(pedidos);
    }

    public List<Pedido> recuperarPedidosPorPrioridade() {
        return pedidoRepository.findByStatusPedidoOrderByNivelPrioridadeAscDataCriacaoAsc(StatusPedido.CRIADO);
    }

}
