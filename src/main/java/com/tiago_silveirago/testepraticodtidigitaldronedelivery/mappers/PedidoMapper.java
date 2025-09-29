package com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.pedido.PedidoRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.pedido.PedidoResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Pedido;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoMapper {

    public static Pedido requestParaPedido(PedidoRequestDTO request) {
        Pedido pedido = new Pedido();

        pedido.setCliente(request.cliente());
        pedido.setLocalizacaoDestino(request.localizacaoDestino());
        pedido.setPesoPacote(request.pesoPacote());
        pedido.setNivelPrioridade(request.nivelPrioridade());
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.CRIADO);

        return pedido;
    }

    public static PedidoResponseDTO pedidoParaResponse(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getCliente(),
                pedido.getLocalizacaoDestino(),
                pedido.getPesoPacote(),
                pedido.getNivelPrioridade(),
                pedido.getDataCriacao(),
                pedido.getStatusPedido());
    }

    public static List<PedidoResponseDTO> pedidoParaResponse(List<Pedido> pedidos){
        return pedidos.stream().map(PedidoMapper::pedidoParaResponse).toList();
    }
}
