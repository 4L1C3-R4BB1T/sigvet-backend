package br.com.sigvet.sigvetapi.feature.diagnostic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.sigvetapi.common.entities.DiagnosticEntity;

public interface DiagnosticRepository extends JpaRepository<DiagnosticEntity, Long>, JpaSpecificationExecutor<DiagnosticEntity> {

    @Query(
        value = """
            SELECT d.* FROM diagnostics d 
            INNER JOIN consults c ON c.id = d.consult_id
            INNER JOIN users u ON c.veterinarian_id = u.id 
            WHERE
                d.deleted IS FALSE
                AND 
                (
                    LOWER(unaccent(u.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%'))
                                OR
                    LOWER(unaccent(d.diagnosis)) LIKE unaccent(CONCAT('%', LOWER(?1), '%'))
                                OR
                    LOWER(unaccent(d.comments)) LIKE unaccent(CONCAT('%', LOWER(?1), '%'))
                );  
        """,
        nativeQuery = true
    )
    List<DiagnosticEntity> searchByTerm(String term);
}
