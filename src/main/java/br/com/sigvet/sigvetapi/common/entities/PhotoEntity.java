package br.com.sigvet.sigvetapi.common.entities;

import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "photos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long entityId;

    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    @Column(length = 150, nullable = false)
    private String fileName;

    @Column(nullable = false, columnDefinition = "BYTEA")
    private byte[] data;

    @Column(length = 80)
    private String contentType;

    private long size;
}
