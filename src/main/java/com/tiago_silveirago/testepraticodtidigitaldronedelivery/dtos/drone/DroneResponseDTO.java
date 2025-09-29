package com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.drone;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone;

public record DroneResponseDTO(String id,
                               String nome,
                               Double capacidadePeso,
                               Double capacidadeDeslocamento,
                               StatusDrone statusDrone,
                               double[] localizacaoAtual) {
}
