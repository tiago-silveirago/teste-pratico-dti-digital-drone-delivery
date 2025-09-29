package com.tiago_silveirago.testepraticodtidigitaldronedelivery.models;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "drones")
public class Drone {

    @Id
    private String id;
    private String nome;
    private Double capacidadePeso;
    private Double capacidadeDeslocamento;
    private StatusDrone statusDrone;

    @GeoSpatialIndexed
    private double[] localizacaoAtual;
}
