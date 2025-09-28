package com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.NivelPrioridade;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusEntrega;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;

public record PedidoResponseDTO(String cliente,
                                double[] localizacaoDestino,
                                Double pesoPacote,
                                NivelPrioridade nivelPrioridade,
                                LocalDateTime dataCriacao,
                                StatusEntrega statusEntrega) {
}
