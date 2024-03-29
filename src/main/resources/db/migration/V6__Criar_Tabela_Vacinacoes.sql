CREATE TABLE vacinacoes (
    id              BIGSERIAL       NOT NULL, 
    data_horario     TIMESTAMP       NOT NULL,
    animal_id       BIGINT          NOT NULL,
    veterinario_id  BIGINT          NOT NULL,
    vacina_id       BIGINT          NOT NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMP       NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_vacinacao 
        PRIMARY KEY (id),
    CONSTRAINT fk_vacinacao_animal 
        FOREIGN KEY (animal_id) 
        REFERENCES animais (id),
    CONSTRAINT fk_vacinacao_veterinario 
        FOREIGN KEY (veterinario_id) 
        REFERENCES veterinarios (id),
    CONSTRAINT fk_vacinacao_vacina 
        FOREIGN KEY (vacina_id) 
        REFERENCES vacinas (id)
);