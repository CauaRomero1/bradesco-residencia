package com.banco.gerenciamento_protocolo_mongodb.repository;

import com.banco.gerenciamento_protocolo_mongodb.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
}
