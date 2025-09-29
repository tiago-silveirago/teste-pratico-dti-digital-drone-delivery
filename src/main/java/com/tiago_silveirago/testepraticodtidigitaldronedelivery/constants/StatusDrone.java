package com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusDrone {
    IDDLE("IDDLE"),
    EM_VOO("EM_VOO"),
    ENTREGANDO("ENTREGANDO"),
    RETORNANDO("RETORNANDO");

    private final String descricao;
}
