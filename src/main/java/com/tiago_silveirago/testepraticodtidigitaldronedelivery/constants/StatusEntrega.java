package com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEntrega {

    AGUARDANDO("AGUARDANDO"),
    EM_ENTREGA("EM_ENTREGA"),
    ENTREGUE("ENTREGUE"),
    CANCELADO("CANCELADO");

    private final String descricao;
}
