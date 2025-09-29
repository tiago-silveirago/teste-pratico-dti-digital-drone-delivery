package com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.pedido;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.NivelPrioridade;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido;

import java.time.LocalDateTime;

public record PedidoResponseDTO(String id,
                                String cliente,
                                double[] localizacaoDestino,
                                Double pesoPacote,
                                NivelPrioridade nivelPrioridade,
                                LocalDateTime dataCriacao,
                                StatusPedido statusPedido) {
}
