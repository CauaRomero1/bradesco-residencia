package com.banco.gerenciamento_protocolo_mongodb.service;

import com.banco.gerenciamento_protocolo_mongodb.model.Funcionario;
import com.banco.gerenciamento_protocolo_mongodb.model.Protocolo;
import com.banco.gerenciamento_protocolo_mongodb.repository.FuncionarioRepository;
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

    @Autowired
    private FuncionarioRepository funcionarioRepository;  // Injeção do repositório de Funcionário

    public Protocolo criarProtocolo(Protocolo protocolo) {
        protocolo.setNumeroProtocolo(UUID.randomUUID().toString());
        protocolo.setDataAbertura(LocalDate.now());
        protocolo.setDataPrazo(calcularPrazo(protocolo.getTipoProtocolo()));
        return protocoloRepository.save(protocolo);
    }

    public List<Protocolo> listarProtocolos() {
        return protocoloRepository.findAll();
    }

    public Optional<Protocolo> buscarProtocolo(String numeroProtocolo) {
        return protocoloRepository.findByNumeroProtocolo(numeroProtocolo);
    }

    public Protocolo atualizarProtocolo(String numeroProtocolo, Protocolo protocoloAtualizado) {
        Optional<Protocolo> optionalProtocolo = protocoloRepository.findByNumeroProtocolo(numeroProtocolo);
        if (optionalProtocolo.isPresent()) {
            Protocolo protocoloExistente = optionalProtocolo.get();
            protocoloExistente.setDescricao(protocoloAtualizado.getDescricao());
            protocoloExistente.setDataAlteracao(LocalDate.now()); // Atualiza a data de alteração
            return protocoloRepository.save(protocoloExistente);
        }
        throw new RuntimeException("Protocolo não encontrado");
    }

    public Protocolo mudarResponsavel(String numeroProtocolo, String codigoFuncionarioNovo) {
        Optional<Protocolo> optionalProtocolo = protocoloRepository.findByNumeroProtocolo(numeroProtocolo);
        if (optionalProtocolo.isPresent()) {
            Protocolo protocolo = optionalProtocolo.get();

            // Buscar o novo funcionário
            Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(codigoFuncionarioNovo);
            if (optionalFuncionario.isPresent()) {
                Funcionario novoFuncionario = optionalFuncionario.get();

                // Atualiza o responsável (funcionário)
                protocolo.setFuncionario(novoFuncionario);
                protocolo.setDataAlteracao(LocalDate.now());  // Atualiza a data de alteração

                // Salva o protocolo com o novo responsável
                return protocoloRepository.save(protocolo);
            } else {
                throw new RuntimeException("Funcionário não encontrado");
            }
        } else {
            throw new RuntimeException("Protocolo não encontrado");
        }
    }

    private LocalDate calcularPrazo(String tipoProtocolo) {
        // Implementação do cálculo de prazo aqui
        return null;
    }
}
