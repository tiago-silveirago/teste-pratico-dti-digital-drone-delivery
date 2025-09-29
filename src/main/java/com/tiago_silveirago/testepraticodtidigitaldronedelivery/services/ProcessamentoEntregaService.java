package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusEntrega;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.DadosDoPedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Entrega;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.DroneRepository;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.EntregaRepository;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.PedidoRepository;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.utils.DistanciaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessamentoEntregaService {

    private final PedidoRepository pedidoRepository;
    private final DroneRepository droneRepository;
    private final EntregaRepository entregaRepository;

    public void processarEntrega(Entrega entrega) throws InterruptedException {
        log.info("Iniciando processamento de entregas");
        List<DadosDoPedido> pontosDeEntrega = entrega.getDadosDosPedidos();
        double[] pontoIncial =  new double[]{0.0, 0.0};

        for (DadosDoPedido dadosDoPedido :  pontosDeEntrega){
            realizandoViagem(entrega, dadosDoPedido, pontoIncial);
            realizandoEntrega(entrega);
            pontoIncial = retomandoVoo(entrega, dadosDoPedido);
        }

        realizandoRetorno(entrega, pontoIncial);
        finalizandoEntrega(entrega);
    }

    private void realizandoViagem(Entrega entrega, DadosDoPedido dadosDoPedido, double[] pontoIncial) throws InterruptedException {
        entrega.setStatusEntrega(StatusEntrega.EM_ENTREGA);
        entregaRepository.save(entrega);
        double distanciaEntrePontos = DistanciaUtil.calcularDistanciaEntrePontos(dadosDoPedido.getPontoDeEntrega(), pontoIncial);
        long tempoDeViagem = Math.round(1000 * distanciaEntrePontos);
        // Tempo de Viagem
        Thread.sleep(tempoDeViagem);
    }

    private void realizandoEntrega(Entrega entrega) throws InterruptedException {
        droneRepository.updateStatusById(entrega.getDroneId(), StatusDrone.ENTREGANDO);
        // Tempo de Entrega
        Thread.sleep(3000);
    }

    private double[] retomandoVoo(Entrega entrega, DadosDoPedido dadosDoPedido) {
        double[] pontoIncial;
        pedidoRepository.updateStatusById(dadosDoPedido.getPedidoId(), StatusPedido.CONCLUIDO);
        droneRepository.updateStatusById(entrega.getDroneId(), StatusDrone.EM_VOO);
        pontoIncial = dadosDoPedido.getPontoDeEntrega();
        return pontoIncial;
    }

    private void realizandoRetorno(Entrega entrega, double[] pontoIncial) throws InterruptedException {
        double distanciaDeRetorno = DistanciaUtil.calcularDistanciaEntrePontos(pontoIncial, new double[]{0.0, 0.0});
        long tempoDeRetornoBase = Math.round(1000 * distanciaDeRetorno);
        droneRepository.updateStatusById(entrega.getDroneId(), StatusDrone.RETORNANDO);
        // Tempo de retorno
        Thread.sleep(tempoDeRetornoBase);
    }

    private void finalizandoEntrega(Entrega entrega) {
        entrega.setStatusEntrega(StatusEntrega.ENTREGUE);
        entregaRepository.save(entrega);
        droneRepository.updateStatusById(entrega.getDroneId(), StatusDrone.IDDLE);
    }
}
