package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.entregas.EntregaResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Entrega;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers.EntregaMapper.entregaParaResponse;

@Service
@RequiredArgsConstructor
public class ConsultaEntregaService {

    private final EntregaRepository entregaRepository;

    public EntregaResponseDTO buscarPorId(String id) {
        Entrega entrega = entregaRepository.findById(id).orElseThrow(() -> new RuntimeException("ID de entrega inválido"));
        return entregaParaResponse(entrega);
    }

    public List<EntregaResponseDTO> buscarTodos() {
        List<Entrega> entregas = entregaRepository.findAll();
        return entregaParaResponse(entregas);
    }
}
