package com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Drone;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DroneRepository extends MongoRepository<Drone,String> {

    Optional<Drone> findFirstByStatusDrone(StatusDrone statusDrone);
}
