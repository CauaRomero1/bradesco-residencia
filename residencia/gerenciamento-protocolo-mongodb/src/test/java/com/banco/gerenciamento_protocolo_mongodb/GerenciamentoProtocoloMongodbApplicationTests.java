package com.banco.gerenciamento_protocolo_mongodb;

import com.banco.gerenciamento_protocolo_mongodb.model.Protocolo;
import com.banco.gerenciamento_protocolo_mongodb.repository.ProtocoloRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

@SpringBootTest
class GerenciamentoProtocoloMongodbApplicationTests {

	@Test
	void contextLoads() {
	}

}

@SpringBootTest
class ProtocoloServiceTests {

    @Autowired
    private ProtocoloRepository protocoloRepository;

    @Test
    void testCriarProtocolo() {
        
    }

    @Test
    void testAlterarProtocolo() {
       
    }
}


