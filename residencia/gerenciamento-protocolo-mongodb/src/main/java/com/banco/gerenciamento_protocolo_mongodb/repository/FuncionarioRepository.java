package com.banco.gerenciamento_protocolo_mongodb.repository;

import com.banco.gerenciamento_protocolo_mongodb.model.Funcionario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {
}
