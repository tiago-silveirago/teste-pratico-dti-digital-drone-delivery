package com.tiago_silveirago.testepraticodtidigitaldronedelivery.controllers;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.entregas.EntregaResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.services.ConsultaEntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/entregas")
@RequiredArgsConstructor
public class EntregaController {

    private final ConsultaEntregaService consultaEntregaService;

    @GetMapping("{id}")
    public ResponseEntity<EntregaResponseDTO> buscarPorId(@PathVariable String id) {
        EntregaResponseDTO response = consultaEntregaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EntregaResponseDTO>> buscarTodos() {
        List<EntregaResponseDTO> response = consultaEntregaService.buscarTodos();
        return ResponseEntity.ok(response);
    }
}
