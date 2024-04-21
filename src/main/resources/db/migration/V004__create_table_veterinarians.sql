CREATE TABLE veterinarians (
    id              BIGSERIAL           NOT NULL,
    specialty       VARCHAR(255)        NOT NULL,
    crmv            VARCHAR(45)         NOT NULL,
    crmv_uf         CHAR(2)             NOT NULL,
    CONSTRAINT pk_veterinarian
        PRIMARY KEY (id),
    CONSTRAINT fk_veterinarian_user
        FOREIGN KEY (id)
        REFERENCES users (id)
);

CREATE INDEX veterinarians_index_crmv ON veterinarians (crmv);