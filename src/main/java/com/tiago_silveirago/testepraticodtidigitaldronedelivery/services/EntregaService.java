package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusDrone;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.constants.StatusPedido;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class EntregaService {

    private final DroneService droneService;
    private final PedidoService pedidoService;
    private final EntregaRepository entregaRepository;
    private final PedidoRepository pedidoRepository;
    private final DroneRepository droneRepository;


    public void criarEntrega() {
        log.info("Iniciando criação da entrega");
        Drone droneLivre = droneService.buscarDroneLivre();
        List<Pedido> pedidos = pedidoService.recuperarPedidosPorPrioridade();

        if (pedidos.isEmpty()) {
            log.warn("Não há pedidos para ser entregue");
            return;
        }

        double capacidadePesoAtual = droneLivre.getCapacidadePeso();
        double capacidadeDeslocamentoAtual = droneLivre.getCapacidadeDeslocamento();
        double[] ultimaLocalizacao = droneLivre.getLocalizacaoAtual();

        Entrega entrega = new Entrega();

        for (Pedido pedido : pedidos) {
            log.info("Analisando pedido: {}", pedido);
            double distanciaDronePedido = DistanciaUtil.calcularDistanciaEntrePontos(ultimaLocalizacao, pedido.getLocalizacaoDestino());
            double distanciaRetornoBase = DistanciaUtil.calcularDistanciaEntrePontos(new double[]{0.0, 0.0}, pedido.getLocalizacaoDestino());
            double distanciaTotal = distanciaDronePedido + distanciaRetornoBase;

            if (pedido.getPesoPacote() <= capacidadePesoAtual && distanciaTotal <= capacidadeDeslocamentoAtual) {
                log.info("Adicionando pedido na entrega: {}", pedido);
                entrega.getPedidoIds().add(pedido.getId());
                capacidadePesoAtual -= pedido.getPesoPacote();
                capacidadeDeslocamentoAtual -= distanciaDronePedido;
                ultimaLocalizacao = pedido.getLocalizacaoDestino();
            }

            log.info("Status atual: capacidadePesoAtual {}, capacidadeDeslocamentoAtual {}, ultimaLocalizacao {}",
                    capacidadePesoAtual, capacidadeDeslocamentoAtual, ultimaLocalizacao);

            if (pedido.getPesoPacote() == capacidadePesoAtual || distanciaTotal == capacidadeDeslocamentoAtual) {
                log.info("Limite de entrega atingido");
                break;
            }
        }

        droneLivre.setStatusDrone(StatusDrone.EM_VOO);
        droneRepository.save(droneLivre);
        pedidoRepository.updateStatusByIds(entrega.getPedidoIds(), StatusPedido.EM_ANDAMENTO);
        entrega.setDataCriacao(LocalDateTime.now());
        Entrega entregaCriada = entregaRepository.save(entrega);
        log.info("Entrega criada com sucesso: {}", entregaCriada);
    }
}
