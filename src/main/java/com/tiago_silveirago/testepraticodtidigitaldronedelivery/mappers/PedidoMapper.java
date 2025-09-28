package com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusEntrega;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.PedidoRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Pedido;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
