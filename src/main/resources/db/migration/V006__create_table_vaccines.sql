CREATE TABLE vaccines (
    id                  BIGSERIAL       NOT NULL,
    name                VARCHAR(255)    NOT NULL,
    manufacturer        VARCHAR(255)    NOT NULL,
    lot                 VARCHAR(255)    NULL,
    unit_price          DECIMAL(10, 2)  NOT NULL DEFAULT 0,
    stock               INTEGER         NOT NULL DEFAULT 0,
    expiration_date     TIMESTAMP       NOT NULL,
    created_at          TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMP       NOT NULL DEFAULT NOW(),
    deleted     BOOLEAN                 DEFAULT  false,
    CONSTRAINT pk_vaccine
        PRIMARY KEY (id)
);