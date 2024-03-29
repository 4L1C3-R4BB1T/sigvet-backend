CREATE TABLE consultas (
    id              BIGSERIAL       NOT NULL, 
    data_horario     TIMESTAMP       NOT NULL,
    animal_id       BIGINT          NOT NULL,
    veterinario_id  BIGINT          NOT NULL,
    status        VARCHAR(50)     NOT NULL,
    created_at       TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMP       NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_consulta 
        PRIMARY KEY (id),
    CONSTRAINT fk_consulta_animal 
        FOREIGN KEY (animal_id) 
        REFERENCES animais (id),
    CONSTRAINT fk_consulta_veterinario 
        FOREIGN KEY (veterinario_id) 
        REFERENCES veterinarios (id)
);