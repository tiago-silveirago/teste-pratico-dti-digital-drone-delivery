package com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusPedido {

    CRIADO("CRIADO"),
    EM_ANDAMENTO("EM_ANDAMENTO"),
    CONCLUIDO("CONCLUIDO");

    private final String descricao;
}
