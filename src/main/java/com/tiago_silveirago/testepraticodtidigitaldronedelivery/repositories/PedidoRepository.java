package com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido,String> {

    List<Pedido> findByStatusPedidoOrderByNivelPrioridadeAscDataCriacaoAsc(StatusPedido statusPedido);

    @Query("{ '_id': { $in: ?0 } }")
    @Update("{ '$set': { 'statusPedido': ?1 } }")
    long updateStatusByIds(List<String> ids, StatusPedido statusPedido);

    @Query("{ '_id': { $eq: ?0 } }")
    @Update("{ '$set': { 'statusPedido': ?1 } }")
    long updateStatusById(String id, StatusPedido statusPedido);
}
