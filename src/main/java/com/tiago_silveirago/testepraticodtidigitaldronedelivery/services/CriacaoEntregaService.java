package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusEntrega;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.DadosDoPedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Drone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Entrega;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Pedido;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.DroneRepository;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.EntregaRepository;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.PedidoRepository;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.utils.DistanciaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class CriacaoEntregaService {

    private final DroneService droneService;
    private final PedidoService pedidoService;
    private final ProcessamentoEntregaService processamentoEntregaService;
    private final EntregaRepository entregaRepository;
    private final PedidoRepository pedidoRepository;
    private final DroneRepository droneRepository;


    public void criarEntrega() throws InterruptedException {
        log.info("Iniciando criação da entrega");
        Drone droneLivre = droneService.buscarDroneLivre();
        List<Pedido> pedidos = pedidoService.recuperarPedidosPorPrioridade();

        if (pedidos.isEmpty()) {
            log.warn("Não há pedidos para ser entregue");
            return;
        }

        Entrega entrega = calculaECriaEntrega(droneLivre, pedidos);

        Entrega entregaCriada = atualizaEntrega(droneLivre, entrega);
        log.info("Entrega criada com sucesso: {}", entregaCriada);

        processarEntregaAssincrono(entregaCriada);
    }

    private void processarEntregaAssincrono(Entrega entregaCriada) {
        CompletableFuture.runAsync(() -> {
            try {
                processamentoEntregaService.processarEntrega(entregaCriada);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Entrega atualizaEntrega(Drone droneLivre, Entrega entrega) {
        droneLivre.setStatusDrone(StatusDrone.EM_VOO);
        droneRepository.save(droneLivre);
        List<String> listaIds = entrega.getDadosDosPedidos().stream()
                .map(DadosDoPedido::getPedidoId)
                .toList();
        pedidoRepository.updateStatusByIds(listaIds, StatusPedido.EM_ANDAMENTO);
        entrega.setStatusEntrega(StatusEntrega.AGUARDANDO);
        entrega.setDataCriacao(LocalDateTime.now());
        return entregaRepository.save(entrega);
    }

    private Entrega calculaECriaEntrega(Drone droneLivre, List<Pedido> pedidos) {
        double capacidadePesoAtual = droneLivre.getCapacidadePeso();
        double capacidadeDeslocamentoAtual = droneLivre.getCapacidadeDeslocamento();
        double[] ultimaLocalizacao = droneLivre.getLocalizacaoAtual();

        Entrega entrega = new Entrega();
        entrega.setDroneId(droneLivre.getId());
        percorrePedidosEAdicionaNaEntrega(pedidos, ultimaLocalizacao, capacidadePesoAtual, capacidadeDeslocamentoAtual, entrega);
        return entrega;
    }

    private void percorrePedidosEAdicionaNaEntrega(List<Pedido> pedidos, double[] ultimaLocalizacao, double capacidadePesoAtual, double capacidadeDeslocamentoAtual, Entrega entrega) {
        for (Pedido pedido : pedidos) {
            log.info("Analisando pedido: {}", pedido);
            double distanciaDronePedido = DistanciaUtil.calcularDistanciaEntrePontos(ultimaLocalizacao, pedido.getLocalizacaoDestino());
            double distanciaRetornoBase = DistanciaUtil.calcularDistanciaEntrePontos(new double[]{0.0, 0.0}, pedido.getLocalizacaoDestino());
            double distanciaTotal = distanciaDronePedido + distanciaRetornoBase;

            if (estaDentroDaCapacidadeDoDrone(capacidadePesoAtual, capacidadeDeslocamentoAtual, pedido, distanciaTotal)) {
                log.info("Adicionando pedido na entrega: {}", pedido);
                entrega.getDadosDosPedidos().add(new DadosDoPedido(pedido.getId(), pedido.getLocalizacaoDestino(), false));
                capacidadePesoAtual -= pedido.getPesoPacote();
                capacidadeDeslocamentoAtual -= distanciaDronePedido;
                ultimaLocalizacao = pedido.getLocalizacaoDestino();
            }

            log.info("Status atual: capacidadePesoAtual {}, capacidadeDeslocamentoAtual {}, ultimaLocalizacao {}",
                    capacidadePesoAtual, capacidadeDeslocamentoAtual, ultimaLocalizacao);

            if (atingiuLimite(capacidadePesoAtual, capacidadeDeslocamentoAtual, pedido, distanciaTotal)) {
                log.info("Limite de entrega atingido");
                break;
            }
        }
    }

    private boolean atingiuLimite(double capacidadePesoAtual, double capacidadeDeslocamentoAtual, Pedido pedido, double distanciaTotal) {
        return pedido.getPesoPacote() == capacidadePesoAtual || distanciaTotal == capacidadeDeslocamentoAtual;
    }

    private boolean estaDentroDaCapacidadeDoDrone(double capacidadePesoAtual, double capacidadeDeslocamentoAtual, Pedido pedido, double distanciaTotal) {
        return pedido.getPesoPacote() <= capacidadePesoAtual && distanciaTotal <= capacidadeDeslocamentoAtual;
    }
}
