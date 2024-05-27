CREATE TABLE vaccinations (
    id                  BIGSERIAL       NOT NULL,
    date_time           TIMESTAMP       NOT NULL,
    animal_id           BIGINT          NOT NULL,
    veterinarian_id     BIGINT          NOT NULL,
    vaccine_id          BIGINT          NOT NULL,
    created_at          TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMP       NOT NULL DEFAULT NOW(),
    deleted             BOOLEAN         DEFAULT  false,
    CONSTRAINT pk_vaccination
        PRIMARY KEY (id),
    CONSTRAINT fk_vaccination_animal
        FOREIGN KEY (animal_id)
        REFERENCES animals (id),
    CONSTRAINT fk_vaccination_veterinarian
        FOREIGN KEY (veterinarian_id)
        REFERENCES veterinarians (id),
    CONSTRAINT fk_vaccination_vaccine
        FOREIGN KEY (vaccine_id)
        REFERENCES vaccines (id)
);