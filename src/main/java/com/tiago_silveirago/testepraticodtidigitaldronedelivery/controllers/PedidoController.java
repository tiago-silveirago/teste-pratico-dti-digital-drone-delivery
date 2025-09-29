package com.tiago_silveirago.testepraticodtidigitaldronedelivery.controllers;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.pedido.PedidoRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.pedido.PedidoResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.services.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid PedidoRequestDTO request) {
        pedidoService.criar(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable String id) {
        PedidoResponseDTO response = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> buscarTodos() {
        List<PedidoResponseDTO> response = pedidoService.buscarTodos();
        return ResponseEntity.ok(response);
    }
}
