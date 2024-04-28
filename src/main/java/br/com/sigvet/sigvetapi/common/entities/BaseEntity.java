package br.com.sigvet.sigvetapi.common.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "createdAt", "updatedAt", "deleted" })
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data 
@EqualsAndHashCode(callSuper = false)
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
    protected boolean deleted;
    
}
