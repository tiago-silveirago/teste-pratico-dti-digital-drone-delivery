package com.tiago_silveirago.testepraticodtidigitaldronedelivery.controllers;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.drone.DroneRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.drone.DroneResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.services.DroneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DroneControllerTest {

    @Mock
    private DroneService droneService;

    @InjectMocks
    private DroneController droneController;

    @Test
    void criar_DadosValidos_RetornaNoContent() {
        DroneRequestDTO request = new DroneRequestDTO("Drone 1", 10.0, 100.0);
        doNothing().when(droneService).criar(request);
        ResponseEntity<Void> response = droneController.criar(request);
        assertEquals(204, response.getStatusCodeValue());
        verify(droneService).criar(request);
    }

    @Test
    void buscarPorId_IdValido_RetornaOk() {
        String id = "123";
        DroneResponseDTO dto = new DroneResponseDTO(
            id, "Drone 1", 10.0, 100.0, com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone.IDDLE, new double[]{0.0, 0.0}
        );
        when(droneService.buscarPorId(id)).thenReturn(dto);
        ResponseEntity<DroneResponseDTO> response = droneController.buscarPorId(id);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
        verify(droneService).buscarPorId(id);
    }

    @Test
    void buscarTodos_ExistemDrones_RetornaOk() {
        DroneResponseDTO dto1 = new DroneResponseDTO(
            "1", "Drone 1", 10.0, 100.0, com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone.IDDLE, new double[]{0.0, 0.0}
        );
        DroneResponseDTO dto2 = new DroneResponseDTO(
            "2", "Drone 2", 12.0, 120.0, com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone.IDDLE, new double[]{1.0, 1.0}
        );
        List<DroneResponseDTO> dtos = List.of(dto1, dto2);
        when(droneService.buscarTodos()).thenReturn(dtos);
        ResponseEntity<List<DroneResponseDTO>> response = droneController.buscarTodos();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dtos, response.getBody());
        verify(droneService).buscarTodos();
    }
}
