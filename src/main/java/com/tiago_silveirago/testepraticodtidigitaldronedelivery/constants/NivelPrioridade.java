package com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NivelPrioridade {
    ALTA(1),
    MEDIA(2),
    BAIXA(3);

    private final Integer ranking;
}
