CREATE TABLE consults (
    id                  BIGSERIAL       NOT NULL,
    date_time           TIMESTAMP       NOT NULL,
    animal_id           BIGINT          NOT NULL,
    veterinarian_id     BIGINT          NOT NULL,
    status              VARCHAR(50)     NOT NULL,
    created_at          TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMP       NOT NULL DEFAULT NOW(),
    deleted             BOOLEAN         DEFAULT  false,
    CONSTRAINT pk_consult
        PRIMARY KEY (id),
    CONSTRAINT fk_consult_animal
        FOREIGN KEY (animal_id)
        REFERENCES animals (id),
    CONSTRAINT fk_consult_veterinarian
        FOREIGN KEY (veterinarian_id)
        REFERENCES veterinarians (id)
);