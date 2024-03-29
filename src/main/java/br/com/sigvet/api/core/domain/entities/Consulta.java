package br.com.sigvet.api.core.domain.entities;

import java.time.LocalDateTime;

import br.com.sigvet.api.core.domain.enums.ConsultaStatus;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public class Consulta {
    private Long id;
    private LocalDateTime dataHorario;
    private Animal animal;
    private Veterinario veterinario;
    private ConsultaStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Consulta() {
    }

    public Consulta(Long id, LocalDateTime dataHorario, Animal animal,
        Veterinario veterinario, ConsultaStatus status) throws DomainInvalidException {
        this.id = id;
        this.dataHorario = dataHorario;
        this.animal = animal;
        this.veterinario = veterinario;
        this.status = status;
        this.validate();
    }

    public Consulta(LocalDateTime dataHorario, Animal animal,
        Veterinario veterinario, ConsultaStatus status) throws DomainInvalidException {
        this.dataHorario = dataHorario;
        this.animal = animal;
        this.veterinario = veterinario;
        this.status = status;
        this.validate();
    }

      public void validate() throws DomainInvalidException {
        if (dataHorario == null) {
            throw new DomainInvalidException("A data e horário da consulta não podem ser nulos.");
        }
   
        if (animal == null) {
            throw new DomainInvalidException("O animal da consulta não pode ser nulo.");
        }
        if (animal.getId() == null) {
            throw new DomainInvalidException("O ID do animal da consulta não pode ser nulo.");
        }

        if (veterinario == null) {
            throw new DomainInvalidException("O veterinário da consulta não pode ser nulo.");
        }
        if (veterinario.getId() == null) {
            throw new DomainInvalidException("O ID do veterinário da consulta não pode ser nulo.");
        }

        if (status == null) {
            throw new DomainInvalidException("O status da consulta não pode ser nulo.");
        }
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(LocalDateTime dataHorario) {
        this.dataHorario = dataHorario;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public ConsultaStatus getStatus() {
        return status;
    }

    public void setStatus(ConsultaStatus status) {
        this.status = status;
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
