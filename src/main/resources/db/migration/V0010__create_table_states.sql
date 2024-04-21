CREATE TABLE states (
    id              CHAR(2)             NOT NULL,
    name            VARCHAR(255)        NOT NULL,
    created_at      TIMESTAMP           NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP           NOT NULL DEFAULT NOW(),
    deleted         BOOLEAN             DEFAULT false,
    CONSTRAINT pk_uf
        PRIMARY KEY (id)
);
