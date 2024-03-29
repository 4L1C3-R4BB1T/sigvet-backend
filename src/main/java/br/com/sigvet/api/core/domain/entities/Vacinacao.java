package br.com.sigvet.api.core.domain.entities;

import java.time.LocalDateTime;

import br.com.sigvet.api.core.exception.DomainInvalidException;

public class Vacinacao { 
    private Long id;
    private LocalDateTime dataHorario;
    private Veterinario veterinario;
    private Vacina vacina;
    private Animal animal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Vacinacao() {
    }

    public Vacinacao(Long id, LocalDateTime dataHorario,
        Veterinario veterinario, Vacina vacina, Animal animal) throws DomainInvalidException {
        this.id = id;
        this.dataHorario = dataHorario;
        this.veterinario = veterinario;
        this.vacina = vacina;
        this.animal = animal;
        this.validate();
    }

    public Vacinacao(LocalDateTime dataHorario,
        Veterinario veterinario, Vacina vacina, Animal animal) throws DomainInvalidException {
        this.dataHorario = dataHorario;
        this.veterinario = veterinario;
        this.vacina = vacina;
        this.animal = animal;
        this.validate();
    }

     public void validate() throws DomainInvalidException {
        if (dataHorario == null) {
            throw new DomainInvalidException("A data e horário da vacinação não podem ser nulos.");
        }
        if (veterinario == null || vacina == null || animal == null) {
            throw new DomainInvalidException("O veterinário, vacina e animal devem ser especificados.");
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

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
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
