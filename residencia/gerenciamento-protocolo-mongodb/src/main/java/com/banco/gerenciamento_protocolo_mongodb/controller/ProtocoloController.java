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
public class ProtocoloController {

    private final ProtocoloService protocoloService;

    @Autowired
    public ProtocoloController(ProtocoloService protocoloService) {
        this.protocoloService = protocoloService;
    }

    @PostMapping
    public Protocolo criarProtocolo(@RequestBody Protocolo protocolo) {
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
}

