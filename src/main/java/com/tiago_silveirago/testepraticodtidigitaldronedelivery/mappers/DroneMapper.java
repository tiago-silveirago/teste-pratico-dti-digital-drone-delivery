package com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.drone.DroneRequestDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.drone.DroneResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Drone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DroneMapper {

    public static Drone requestParaDrone(DroneRequestDTO request) {
        Drone drone = new Drone();

        drone.setNome(request.nome());
        drone.setCapacidadePeso(request.capacidadePeso());
        drone.setCapacidadeDeslocamento(request.capacidadeDeslocamento());
        drone.setStatusDrone(StatusDrone.IDDLE);
        drone.setLocalizacaoAtual(new double[]{0.0, 0,0});

        return drone;
    }

    public static DroneResponseDTO droneParaResponse(Drone drone) {
        return new DroneResponseDTO(
                drone.getId(),
                drone.getNome(),
                drone.getCapacidadePeso(),
                drone.getCapacidadeDeslocamento(),
                drone.getStatusDrone(),
                drone.getLocalizacaoAtual());
    }

    public static List<DroneResponseDTO> droneParaResponse(List<Drone> drones) {
        return drones.stream()
                .map(DroneMapper::droneParaResponse)
                .toList();
    }
}
