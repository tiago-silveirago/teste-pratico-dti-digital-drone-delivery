package com.tiago_silveirago.testepraticodtidigitaldronedelivery.controllers;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.drone.DroneRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.drone.DroneResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.services.DroneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/drones")
@RequiredArgsConstructor
public class DroneController {

    private final DroneService droneService;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid DroneRequestDTO request) {
        droneService.criar(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<DroneResponseDTO> buscarPorId(@PathVariable String id) {
        DroneResponseDTO response = droneService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DroneResponseDTO>> buscarTodos() {
        List<DroneResponseDTO> response = droneService.buscarTodos();
        return ResponseEntity.ok(response);
    }
}
