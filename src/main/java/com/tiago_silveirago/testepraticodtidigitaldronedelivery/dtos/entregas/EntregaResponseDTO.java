package com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.entregas;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusEntrega;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.DadosDoPedido;

import java.time.LocalDateTime;
import java.util.List;

public record EntregaResponseDTO(String id,
                                 StatusEntrega statusEntrega,
                                 LocalDateTime dataCriacao,
                                 LocalDateTime dataAtualizacao,
                                 List<DadosDoPedido> dadosDosPedidos,
                                 String droneId) {
}
