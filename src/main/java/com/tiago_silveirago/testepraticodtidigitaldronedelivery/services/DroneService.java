package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.drone.DroneRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.drone.DroneResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Drone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers.DroneMapper.droneParaResponse;
import static com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers.DroneMapper.requestParaDrone;

@Service
@RequiredArgsConstructor
public class DroneService {

    private final DroneRepository droneRepository;

    public void criar(DroneRequestDTO request) {
        Drone drone = requestParaDrone(request);
        droneRepository.save(drone);
    }

    public DroneResponseDTO buscarPorId(String id) {
        Drone drone = droneRepository.findById(id).orElseThrow(() -> new RuntimeException("ID de drone inválido"));
        return droneParaResponse(drone);
    }

    public List<DroneResponseDTO> buscarTodos() {
        List<Drone> pedidos = droneRepository.findAll();
        return droneParaResponse(pedidos);
    }

   public Drone buscarDroneLivre(){
       Optional<Drone> drone = droneRepository.findFirstByStatusDrone(StatusDrone.IDDLE);
       return drone.orElseThrow(() -> new RuntimeException("Não há drone livre"));
   }
}
