package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.drone.DroneRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.drone.DroneResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Drone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.DroneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers.DroneMapper.droneParaResponse;
import static com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers.DroneMapper.requestParaDrone;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DroneServiceTest {

    @Mock
    private DroneRepository droneRepository;

    @InjectMocks
    private DroneService droneService;

    @Test
    void criar_DadosValidos_SalvaDrone() {
        DroneRequestDTO request = new DroneRequestDTO("Drone 1", 10.0, 100.0);
        Drone drone = new Drone();
        // Simula o comportamento do mapper manualmente
        when(droneRepository.save(any(Drone.class))).thenReturn(drone);
        droneService.criar(request);
        verify(droneRepository).save(any(Drone.class));
    }

    @Test
    void buscarPorId_IdValido_RetornaResponseDTO() {
        String id = "123";
        Drone drone = new Drone();
        when(droneRepository.findById(id)).thenReturn(Optional.of(drone));
        // O método droneParaResponse é chamado dentro do service, mas aqui só testamos se não lança exceção
        assertDoesNotThrow(() -> droneService.buscarPorId(id));
        verify(droneRepository).findById(id);
    }

    @Test
    void buscarPorId_IdInvalido_LancaExcecao() {
        String id = "invalido";
        when(droneRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> droneService.buscarPorId(id));
    }

    @Test
    void buscarTodos_DronesExistem_RetornaListaResponseDTO() {
        List<Drone> drones = List.of(new Drone(), new Drone());
        when(droneRepository.findAll()).thenReturn(drones);
        assertDoesNotThrow(() -> droneService.buscarTodos());
        verify(droneRepository).findAll();
    }

    @Test
    void buscarDroneLivre_DroneDisponivel_RetornaDrone() {
        Drone drone = new Drone();
        when(droneRepository.findFirstByStatusDrone(StatusDrone.IDDLE)).thenReturn(Optional.of(drone));
        Drone result = droneService.buscarDroneLivre();
        assertEquals(drone, result);
    }

    @Test
    void buscarDroneLivre_NenhumDisponivel_LancaExcecao() {
        when(droneRepository.findFirstByStatusDrone(StatusDrone.IDDLE)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> droneService.buscarDroneLivre());
    }
}
