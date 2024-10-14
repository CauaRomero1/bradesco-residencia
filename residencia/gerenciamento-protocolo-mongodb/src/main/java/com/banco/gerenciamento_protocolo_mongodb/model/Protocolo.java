package com.banco.gerenciamento_protocolo_mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Document(collection = "protocolos") // Define que essa entidade será armazenada na coleção "protocolos"
public class Protocolo {

    @Id
    private String id;

    private String numeroProtocolo;   // Número único de protocolo
    private String tipoProtocolo;     // Tipo do protocolo (exemplo: Reclamação, Solicitação)
    private String descricao;         // Descrição detalhada do protocolo
    private LocalDate dataAbertura;   // Data de abertura do protocolo
    private LocalDate dataPrazo;      // Data limite para resolver o protocolo
    private LocalDate dataAlteracao;  // Data da última alteração no protocolo
    private Cliente cliente;          // Cliente associado ao protocolo
    private Funcionario funcionario;  // Funcionário responsável pelo protocolo
    private String status;            // Status do protocolo (exemplo: Aberto, Fechado, Pendente)
    private String canal;             // Canal pelo qual o protocolo foi criado (exemplo: Email, Telefone)

    private String prioridade;        // Prioridade do protocolo (exemplo: Alta, Média, Baixa)

    // Map para armazenar os tipos de protocolo e seus prazos em dias
    private static final Map<String, Integer> PRAZOS_POR_TIPO = new HashMap<>();

    static {
        PRAZOS_POR_TIPO.put("Reclamação", 5);
        PRAZOS_POR_TIPO.put("Elogio", 10);
        PRAZOS_POR_TIPO.put("Denúncia", 3);
    }

    // Método para definir o prazo com base no tipo de protocolo
    public void definirPrazo() {
        Integer prazo = PRAZOS_POR_TIPO.get(this.tipoProtocolo);
        if (prazo != null) {
            this.dataPrazo = this.dataAbertura.plusDays(prazo);
        } else {
            throw new IllegalArgumentException("Tipo de protocolo inválido: " + this.tipoProtocolo);
        }
    }

    // Getters e Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroProtocolo() {
        return numeroProtocolo;
    }

    public void setNumeroProtocolo(String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }

    public String getTipoProtocolo() {
        return tipoProtocolo;
    }

    public void setTipoProtocolo(String tipoProtocolo) {
        this.tipoProtocolo = tipoProtocolo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataPrazo() {
        return dataPrazo;
    }

    public void setDataPrazo(LocalDate dataPrazo) {
        this.dataPrazo = dataPrazo;
    }

    public LocalDate getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDate dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}

