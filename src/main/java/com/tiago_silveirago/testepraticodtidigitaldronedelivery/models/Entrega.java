package com.tiago_silveirago.testepraticodtidigitaldronedelivery.models;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusEntrega;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "entregas")
public class Entrega {

    private String id;
    private List<String> pedidoIds = new ArrayList<>();
    private StatusEntrega statusEntrega;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
