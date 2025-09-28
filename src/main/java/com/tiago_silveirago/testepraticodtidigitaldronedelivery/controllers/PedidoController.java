package com.tiago_silveirago.testepraticodtidigitaldronedelivery.controllers;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.PedidoRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.services.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
