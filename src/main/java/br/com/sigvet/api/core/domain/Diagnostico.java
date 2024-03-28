package br.com.sigvet.api.core.domain;

import java.time.LocalDateTime;

public class Diagnostico {
    private Long id;
    private String diagnostico;
    private String observacoes;
    private Consulta consulta;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Diagnostico() {
    }

    public Diagnostico(Long id, String diagnostico,
        String observacoes, Consulta consulta,
        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.diagnostico = diagnostico;
        this.observacoes = observacoes;
        this.consulta = consulta;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
