package com.banco.gerenciamento_protocolo_mongodb.controller;

import com.banco.gerenciamento_protocolo_mongodb.model.Protocolo;
import com.banco.gerenciamento_protocolo_mongodb.service.ProtocoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/protocolos")
public class  ProtocoloController {

    private final ProtocoloService protocoloService;

    @Autowired
    public ProtocoloController(ProtocoloService protocoloService) {
        this.protocoloService = protocoloService;
    }
    @PostMapping
    public Protocolo criarProtocolo(@RequestBody Protocolo protocolo) {
        if (protocolo.getTipoProtocolo() == null || protocolo.getTipoProtocolo().isEmpty()) {
            throw new IllegalArgumentException("Tipo de protocolo é obrigatório.");
        }
        return protocoloService.criarProtocolo(protocolo);
    }

    @GetMapping
    public List<Protocolo> listarProtocolos() {
        return protocoloService.listarProtocolos();
    }

    @GetMapping("/{numeroProtocolo}")
    public ResponseEntity<Protocolo> obterProtocolo(@PathVariable String numeroProtocolo) {
        Optional<Protocolo> protocolo = protocoloService.buscarProtocolo(numeroProtocolo);
        return protocolo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{numeroProtocolo}")
    public ResponseEntity<Protocolo> atualizarProtocolo(@PathVariable String numeroProtocolo, @RequestBody Protocolo protocoloAtualizado) {
        Protocolo protocolo = protocoloService.atualizarProtocolo(numeroProtocolo, protocoloAtualizado);
        return ResponseEntity.ok(protocolo);
    }

    @PutMapping("/{numeroProtocolo}/mudar-responsavel/{codigoFuncionarioNovo}")
    public ResponseEntity<Protocolo> mudarResponsavel(@PathVariable String numeroProtocolo, @PathVariable String codigoFuncionarioNovo) {
        Protocolo protocoloAtualizado = protocoloService.mudarResponsavel(numeroProtocolo, codigoFuncionarioNovo);
        return ResponseEntity.ok(protocoloAtualizado);
    }

    // Novo endpoint para classificar a prioridade do protocolo
    @PutMapping("/{numeroProtocolo}/prioridade")
    public ResponseEntity<Protocolo> classificarPrioridade(@PathVariable String numeroProtocolo, @RequestParam String prioridade) {
        Protocolo protocoloAtualizado = protocoloService.classificarPrioridade(numeroProtocolo, prioridade);
        return ResponseEntity.ok(protocoloAtualizado);
    }
}
