package com.banco.gerenciamento_protocolo_mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;

    private String nome;
    private String cpf;
    private String email;
    private String tipoCliente; // Físico ou Jurídico
    private String telefone;

    // Getters e Setters
}
