package com.banco.gerenciamento_protocolo_mongodb.service;

import com.banco.gerenciamento_protocolo_mongodb.model.Protocolo;
import com.banco.gerenciamento_protocolo_mongodb.repository.ProtocoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProtocoloService {
    @Autowired
    private ProtocoloRepository protocoloRepository;

    public Protocolo criarProtocolo(Protocolo protocolo) {
        protocolo.setNumeroProtocolo(UUID.randomUUID().toString());
        protocolo.setDataAbertura(LocalDate.now());
        // Supondo que calcularPrazo é um método definido nesta classe
        protocolo.setDataPrazo(calcularPrazo(protocolo.getTipoProtocolo()));
        return protocoloRepository.save(protocolo);
    }

    public List<Protocolo> listarProtocolos() {
        return protocoloRepository.findAll();
    }

    public Optional<Protocolo> buscarProtocolo(String numeroProtocolo) {
        return protocoloRepository.findByNumeroProtocolo(numeroProtocolo);
    }


    private LocalDate calcularPrazo(String tipoProtocolo) {
        // Implementação do cálculo de prazo aqui
        return null;
    }

}
