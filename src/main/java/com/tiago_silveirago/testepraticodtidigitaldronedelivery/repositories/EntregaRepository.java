package com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Entrega;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntregaRepository extends MongoRepository<Entrega,String> {

}
