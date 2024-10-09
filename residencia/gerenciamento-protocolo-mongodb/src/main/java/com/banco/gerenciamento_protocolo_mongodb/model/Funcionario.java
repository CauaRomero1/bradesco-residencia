package com.banco.gerenciamento_protocolo_mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "funcionarios")
public class Funcionario {

    @Id
    private String id;

    private String codigoFuncional;
    private String nome;
    private String email;
    private String departamento;
    private String cargo;
    private String status; // Ativo ou Inativo

    // Getters e Setters
}
