CREATE TABLE cities (
    id          BIGSERIAL       NOT NULL,
    name        VARCHAR(255)    NOT NULL,
    state_id    CHAR(2)         NOT NULL,
    created_at   TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMP       NOT NULL DEFAULT NOW(),
    deleted     BOOLEAN         DEFAULT  false,
    CONSTRAINT pk_city
        PRIMARY KEY (id),
    CONSTRAINT fk_city_state
        FOREIGN KEY (state_id)
        REFERENCES states (id)
);

CREATE INDEX cities_index_name ON cities (name);