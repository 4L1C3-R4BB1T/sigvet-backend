package br.com.sigvet.sigvetapi.feature.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.sigvetapi.common.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByDocument(String document);

    boolean existsByEmailAndDocument(String email, String document);

    public interface UserResponseProjection {
        Long getId();
        String getName();
        String getUsername();
        String getEmail();
        @Value("#{target.document.replaceAll(\"(\\d{3})(\\d{3})(\\d{3})(\\d{2})\", \"$1.$2.$3-$4\").trim()}")
        String getDocument();
        @Value("#{target.phone.replaceAll(\"(\\d{2})(\\d{5})(\\d{4})\", \"($1) $2-$3\").trim()}")
        String getPhone();
        LocalDateTime getCreatedAt();
    }

    @Query(value = """
        SELECT 
            u.*, u.created_at as "createdAt"
        FROM 
            users u 
        INNER JOIN 
            roles r 
        ON 
            r.user_id = u.id
        WHERE 
            'UNKNOWN' IN (SELECT r.role FROM roles r WHERE r.user_id = u.id)
        AND 
            (
                LOWER(unaccent(u.name)) LIKE LOWER(unaccent(CONCAT('%', LOWER(?1), '%'))) 
            OR 
                LOWER(unaccent(u.username)) LIKE LOWER(unaccent(CONCAT('%', LOWER(?1), '%'))) 
            OR 
                LOWER(unaccent(u.email)) LIKE LOWER(unaccent(CONCAT('%', LOWER(?1), '%'))) 
            OR
                LOWER(unaccent(u.document)) LIKE LOWER(unaccent(CONCAT('%', LOWER(?1), '%'))) 
            OR 
                LOWER(unaccent(u.phone)) LIKE LOWER(unaccent(CONCAT('%', LOWER(?1), '%'))) 
            )
        GROUP BY 
            u.id
        HAVING 
            COUNT(u.id) = 1;
    """, nativeQuery = true)
    List<UserResponseProjection> searchByTermAndUnknownRole(String term);

    @Query(value = """
            select u.*, u.created_at as "createdAt" from users u inner join roles r on r.user_id = u.id
            where 'UNKNOWN' IN (SELECT r.role FROM roles r WHERE r.user_id = u.id)
            group by u.id, r.user_id
            having count(u.id) = 1;
            """, nativeQuery = true)
    List<UserResponseProjection> findByUnknownRole();

    Optional<UserEntity> findByEmailOrUsername(String email, String username);

    Optional<UserEntity> findByEmail(String email);

}
