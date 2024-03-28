package br.com.sigvet.api.core.domain;

import java.time.LocalDateTime;

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
        Veterinario veterinario, Vacina vacina, Animal animal,
        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.dataHorario = dataHorario;
        this.veterinario = veterinario;
        this.vacina = vacina;
        this.animal = animal;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
