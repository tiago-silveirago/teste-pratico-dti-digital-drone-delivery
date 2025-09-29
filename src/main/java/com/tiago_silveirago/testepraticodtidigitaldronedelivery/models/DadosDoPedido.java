package com.tiago_silveirago.testepraticodtidigitaldronedelivery.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosDoPedido {

    private String pedidoId;
    private double[] pontoDeEntrega;
    private Boolean entregue;
}
