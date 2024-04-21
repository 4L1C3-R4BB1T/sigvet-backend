CREATE TABLE animals (
    id              BIGSERIAL       NOT NULL,
    name            VARCHAR(255)    NOT NULL,
    breed           VARCHAR(255)    NULL,
    birth_date      DATE            NULL,
    client_id       BIGINT          NOT NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP       NOT NULL DEFAULT NOW(),
    deleted         BOOLEAN         DEFAULT  false,
    CONSTRAINT pk_animal
        PRIMARY KEY (id),
    CONSTRAINT fk_animal_client
        FOREIGN KEY (client_id)
        REFERENCES clients (id)
);
