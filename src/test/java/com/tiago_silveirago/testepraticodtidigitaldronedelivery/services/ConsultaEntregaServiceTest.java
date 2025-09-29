package com.tiago_silveirago.testepraticodtidigitaldronedelivery.services;

import com.tiago_silveirago.testepraticodtidigitaldronedelivery.dtos.entregas.EntregaResponseDTO;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.models.Entrega;
import com.tiago_silveirago.testepraticodtidigitaldronedelivery.repositories.EntregaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.tiago_silveirago.testepraticodtidigitaldronedelivery.mappers.EntregaMapper.entregaParaResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaEntregaServiceTest {

    @Mock
    private EntregaRepository entregaRepository;

    @InjectMocks
    private ConsultaEntregaService consultaEntregaService;

    @Test
    void buscarPorId_IdValido_RetornaResponseDTO() {
        String id = "123";
        Entrega entrega = new Entrega();
        when(entregaRepository.findById(id)).thenReturn(Optional.of(entrega));
        assertDoesNotThrow(() -> consultaEntregaService.buscarPorId(id));
        verify(entregaRepository).findById(id);
    }

    @Test
    void buscarPorId_IdInvalido_LancaExcecao() {
        String id = "invalido";
        when(entregaRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> consultaEntregaService.buscarPorId(id));
    }

    @Test
    void buscarTodos_EntregasExistem_RetornaListaResponseDTO() {
        List<Entrega> entregas = List.of(new Entrega(), new Entrega());
        when(entregaRepository.findAll()).thenReturn(entregas);
        assertDoesNotThrow(() -> consultaEntregaService.buscarTodos());
        verify(entregaRepository).findAll();
    }
}
