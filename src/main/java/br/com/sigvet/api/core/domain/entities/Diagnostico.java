package br.com.sigvet.api.core.domain.entities;

import java.time.LocalDateTime;

import br.com.sigvet.api.core.exception.DomainInvalidException;

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

    public Diagnostico(String diagnostico,
        String observacoes, Consulta consulta) {
        this.diagnostico = diagnostico;
        this.observacoes = observacoes;
        this.consulta = consulta;
    }

    public void valite() throws DomainInvalidException {
        if (diagnostico == null || diagnostico.trim().isEmpty()) {
            throw new DomainInvalidException("O diagnóstico não pode estar vazio.");
        }
        if (diagnostico.length() > 255) {
            throw new DomainInvalidException("O diagnóstico deve ter no máximo 255 caracteres.");
        }

        if (consulta == null) {
            throw new DomainInvalidException("O a consulta não pode ser nulo.");
        }

        if (consulta.getId() == null) {
            throw new DomainInvalidException("O a consulta precisa ter um id já associado");
        }

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
