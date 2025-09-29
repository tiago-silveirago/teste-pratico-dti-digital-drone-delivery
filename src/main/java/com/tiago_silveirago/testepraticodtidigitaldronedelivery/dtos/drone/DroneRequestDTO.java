package com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.drone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DroneRequestDTO(@NotBlank String nome,
                              @NotNull Double capacidadePeso,
                              @NotNull Double capacidadeDeslocamento) {
}
