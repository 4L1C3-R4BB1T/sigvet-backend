package br.com.sigvet.api.core.domain;

import java.time.LocalDateTime;

public class Consulta {
    private Long id;
    private LocalDateTime dataHorario;
    private Animal animal;
    private Veterinario veterinario;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Consulta() {
    }

    public Consulta(Long id, LocalDateTime dataHorario, Animal animal,
        Veterinario veterinario, String status, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.id = id;
        this.dataHorario = dataHorario;
        this.animal = animal;
        this.veterinario = veterinario;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
