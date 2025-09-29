package com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.entregas.EntregaResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Entrega;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntregaMapper {

    public static EntregaResponseDTO entregaParaResponse(Entrega entrega) {
        return new EntregaResponseDTO(
                entrega.getDroneId(),
                entrega.getStatusEntrega(),
                entrega.getDataCriacao(),
                entrega.getDataAtualizacao(),
                entrega.getDadosDosPedidos(),
                entrega.getDroneId());
    }

    public static List<EntregaResponseDTO> entregaParaResponse(List<Entrega> entregas) {
        return entregas.stream()
                .map(EntregaMapper::entregaParaResponse)
                .toList();
    }
}
