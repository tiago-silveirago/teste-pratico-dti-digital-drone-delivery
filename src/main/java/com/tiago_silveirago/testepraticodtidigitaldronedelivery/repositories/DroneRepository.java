package com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Drone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.Optional;

public interface DroneRepository extends MongoRepository<Drone,String> {

    Optional<Drone> findFirstByStatusDrone(StatusDrone statusDrone);

    @Query("{ '_id': { $eq: ?0 } }")
    @Update("{ '$set': { 'statusDrone': ?1 } }")
    long updateStatusById(String id, StatusDrone statusDrone);
}
