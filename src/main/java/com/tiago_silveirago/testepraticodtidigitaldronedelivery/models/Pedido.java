package com.tiago_silveirago.testepraticodtidigitaldronedelivery.models;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.NivelPrioridade;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pedidos")
public class Pedido {

    @Id
    private String id;
    private String cliente;

    @GeoSpatialIndexed()
    private double[] localizacaoDestino;

    private Double pesoPacote;
    private NivelPrioridade nivelPrioridade;
    private LocalDateTime dataCriacao;
    private StatusPedido  statusPedido;
}
