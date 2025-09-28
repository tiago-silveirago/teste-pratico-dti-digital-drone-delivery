package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.PedidoRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Drone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Pedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.DroneRepository;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers.PedidoMapper.requestParaPedido;

@Service
@RequiredArgsConstructor
public class DroneService {

    private final DroneRepository droneRepository;

   public Drone buscarDroneLivre(){
       Optional<Drone> drone = droneRepository.findFirstByStatusDrone(StatusDrone.IDDLE);
       return drone.orElseThrow(() -> new RuntimeException("Não há drone livre"));
   }
}
