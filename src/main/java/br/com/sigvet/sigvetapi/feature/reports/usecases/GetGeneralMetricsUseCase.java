package br.com.sigvet.sigvetapi.feature.reports.usecases;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.ConsultationStatus;
import br.com.sigvet.sigvetapi.feature.reports.GeneralMetricsResponseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class GetGeneralMetricsUseCase {
    
    private final EntityManager entityManager;

    public GeneralMetricsResponseDTO execute() {
        try {
            final var cb = entityManager.getCriteriaBuilder();
            final var query = cb.createQuery(GeneralMetricsResponseDTO.class);

            final var subQueryClient = query.subquery(Long.class);
            final var rootSubQueryClient = subQueryClient.from(ClientEntity.class);
            subQueryClient.select(cb.count(rootSubQueryClient));
            subQueryClient.where(cb.isFalse(rootSubQueryClient.get("deleted")));

            final var subQueryAnimal = query.subquery(Long.class);
            final var rootSubQueryAnimal = subQueryAnimal.from(AnimalEntity.class);
            subQueryAnimal.select(cb.count(rootSubQueryAnimal));
            subQueryAnimal.where(cb.isFalse(rootSubQueryAnimal.get("deleted")));

            final var subQueryConsult = query.subquery(Long.class);
            final var rootSubQueryConsult = subQueryConsult.from(ConsultEntity.class);
            subQueryConsult.select(cb.count(rootSubQueryConsult));
            subQueryConsult.where(cb.and(cb.isFalse(rootSubQueryConsult.get("deleted")), cb.equal(rootSubQueryConsult.get("status"), "COMPLETED")));

            query.multiselect(
                subQueryClient,
                subQueryAnimal,
                subQueryConsult,
                cb.prod(subQueryConsult, 350L).as(BigDecimal.class)
            );

            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            return new GeneralMetricsResponseDTO(0L, 0L, 0L, BigDecimal.ONE);
        }
    }
}
