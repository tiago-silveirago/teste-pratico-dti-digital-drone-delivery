package com.tiago_silveirago.testepraticodtidigitaldronedelivery.schedulers;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.services.CriacaoEntregaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class EntregaScheduler {

    private final CriacaoEntregaService criacaoEntregaService;

    @Scheduled(cron = "*/15 * * * * *")
    public void executarEntrega() throws InterruptedException {
        log.info("Iniciando Nova Execução. Horário: {}", LocalDateTime.now());
        criacaoEntregaService.criarEntrega();
    }
}
