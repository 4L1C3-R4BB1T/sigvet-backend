CREATE TABLE addresses (
    id                  BIGSERIAL       NOT NULL,
    street              VARCHAR(255)    NOT NULL,
    neighborhood        VARCHAR(255)    NOT NULL,
    zipCode             CHAR(8)         NOT NULL,
    number              INTEGER         NULL,
    city_id             BIGINT          NOT NULL,
    user_id             BIGINT          NOT NULL,
    created_at          TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMP       NOT NULL DEFAULT NOW(),
    deleted             BOOLEAN         DEFAULT  false,
    CONSTRAINT pk_address
        PRIMARY KEY (id),
    CONSTRAINT fk_address_city
        FOREIGN KEY (city_id)
        REFERENCES cities (id),
    CONSTRAINT fk_address_user
        FOREIGN KEY (user_id)
        REFERENCES users (id)
);