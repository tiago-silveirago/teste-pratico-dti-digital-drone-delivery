package com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.NivelPrioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoRequestDTO(@NotBlank String cliente,
                               @NotNull double[] localizacaoDestino,
                               @NotNull Double pesoPacote,
                               @NotNull NivelPrioridade nivelPrioridade) {
}
