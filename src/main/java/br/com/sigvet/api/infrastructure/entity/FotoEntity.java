package br.com.sigvet.api.infrastructure.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fotos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    private String tipoMime;

    @Column(nullable = false, columnDefinition = "BYTEA")
    private byte[] dados;

    @Column(nullable = false)
    private Long entidadeId;

    @Column(nullable = false)
    private String entidadeTipo;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @CreationTimestamp
    protected LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @UpdateTimestamp
    protected LocalDateTime updatedAt;
}
