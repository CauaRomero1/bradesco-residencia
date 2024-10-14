package com.banco.gerenciamento_protocolo_mongodb.service;

import com.banco.gerenciamento_protocolo_mongodb.model.Protocolo;
import com.banco.gerenciamento_protocolo_mongodb.repository.ProtocoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProtocoloService {

    private final ProtocoloRepository protocoloRepository;

    @Autowired
    public ProtocoloService(ProtocoloRepository protocoloRepository) {
        this.protocoloRepository = protocoloRepository;
    }

    public Protocolo criarProtocolo(Protocolo protocolo) {
        protocolo.setDataAbertura(LocalDate.now());
        protocolo.setDataAlteracao(LocalDate.now());
        protocolo.setPrioridade("Média"); // Definir uma prioridade padrão
        protocolo.definirPrazo(); // Define o prazo com base no tipo de protocolo
        return protocoloRepository.save(protocolo);
    }

    public List<Protocolo> listarProtocolos() {
        return protocoloRepository.findAll();
    }

    public Optional<Protocolo> buscarProtocolo(String numeroProtocolo) {
        return protocoloRepository.findByNumeroProtocolo(numeroProtocolo);
    }

    public Protocolo atualizarProtocolo(String numeroProtocolo, Protocolo protocoloAtualizado) {
        Optional<Protocolo> protocoloExistenteOpt = protocoloRepository.findByNumeroProtocolo(numeroProtocolo);

        if (protocoloExistenteOpt.isPresent()) {
            Protocolo protocoloExistente = protocoloExistenteOpt.get();

            // Atualizando os campos
            protocoloExistente.setDescricao(protocoloAtualizado.getDescricao());
            protocoloExistente.setTipoProtocolo(protocoloAtualizado.getTipoProtocolo());
            protocoloExistente.setDataPrazo(protocoloAtualizado.getDataPrazo());
            protocoloExistente.setDataAlteracao(LocalDate.now());
            protocoloExistente.setStatus(protocoloAtualizado.getStatus());
            protocoloExistente.setCanal(protocoloAtualizado.getCanal());
            protocoloExistente.setPrioridade(protocoloAtualizado.getPrioridade()); // Atualiza prioridade se necessário

            return protocoloRepository.save(protocoloExistente);
        } else {
            throw new RuntimeException("Protocolo não encontrado.");
        }
    }

    public Protocolo mudarResponsavel(String numeroProtocolo, String codigoFuncionarioNovo) {
        Optional<Protocolo> protocoloExistenteOpt = protocoloRepository.findByNumeroProtocolo(numeroProtocolo);

        if (protocoloExistenteOpt.isPresent()) {
            Protocolo protocoloExistente = protocoloExistenteOpt.get();
            // Lógica para alterar o responsável do protocolo
            protocoloExistente.setDataAlteracao(LocalDate.now());

            return protocoloRepository.save(protocoloExistente);
        } else {
            throw new RuntimeException("Protocolo não encontrado.");
        }
    }

    // Método para classificar a prioridade de um protocolo
    public Protocolo classificarPrioridade(String numeroProtocolo, String novaPrioridade) {
        Optional<Protocolo> protocoloExistenteOpt = protocoloRepository.findByNumeroProtocolo(numeroProtocolo);

        if (protocoloExistenteOpt.isPresent()) {
            Protocolo protocoloExistente = protocoloExistenteOpt.get();
            protocoloExistente.setPrioridade(novaPrioridade);
            protocoloExistente.setDataAlteracao(LocalDate.now());
            return protocoloRepository.save(protocoloExistente);
        } else {
            throw new RuntimeException("Protocolo não encontrado.");
        }
    }
}

