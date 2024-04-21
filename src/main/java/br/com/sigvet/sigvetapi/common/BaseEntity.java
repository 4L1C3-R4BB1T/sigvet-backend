package br.com.sigvet.sigvetapi.common;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class BaseEntity<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @CreationTimestamp
    protected LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @UpdateTimestamp
    protected LocalDateTime updatedAt;

    @Column(columnDefinition = "BOOLEAN")
    protected Boolean deleted;
}
