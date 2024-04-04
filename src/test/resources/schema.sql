CREATE TABLE usuarios (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario     VARCHAR(100)    NOT NULL UNIQUE,
    senha       VARCHAR(100)    NOT NULL,
    email       VARCHAR(100)    NOT NULL UNIQUE,
    nome        VARCHAR(100)    NOT NULL,
    cpf         VARCHAR(14)     NOT NULL UNIQUE,
    telefone    VARCHAR(18),
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX usuarios_index_usuario ON usuarios (usuario);
CREATE INDEX usuarios_index_cpf ON usuarios (cpf);
CREATE INDEX usuarios_index_email ON usuarios (email);

CREATE TABLE clientes (
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    CONSTRAINT fk_cliente_usuario 
        FOREIGN KEY (id)
        REFERENCES usuarios (id)
);

CREATE TABLE veterinarios (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    especialidade   VARCHAR(255)    NOT NULL,
    crmv            VARCHAR(45)     NOT NULL,
    crmv_uf         CHAR(2)         NOT NULL,
    CONSTRAINT fk_veterinario_usuario 
        FOREIGN KEY (id)
        REFERENCES usuarios (id)
);

CREATE INDEX veterinarios_index_crmv ON veterinarios (crmv);

CREATE TABLE animais (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY, 
    nome            VARCHAR(255)    NOT NULL,
    raca            VARCHAR(255),
    data_nascimento DATE,
    cliente_id      BIGINT          NOT NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_animal_cliente 
        FOREIGN KEY (cliente_id) 
        REFERENCES clientes (id)
);

CREATE TABLE vacinas (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY, 
    nome            VARCHAR(255)    NOT NULL,
    fabricante      VARCHAR(255)    NOT NULL,
    lote            VARCHAR(255)    NOT NULL,
    preco_unitario  DECIMAL(10, 2)  NOT NULL DEFAULT 0,
    estoque         INTEGER         NOT NULL DEFAULT 0,
    data_validade   TIMESTAMP       NOT NULL,
    created_at       TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE vacinacoes (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY, 
    data_horario    TIMESTAMP       NOT NULL,
    animal_id       BIGINT          NOT NULL,
    veterinario_id  BIGINT          NOT NULL,
    vacina_id       BIGINT          NOT NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
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

CREATE TABLE consultas (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY, 
    data_horario    TIMESTAMP       NOT NULL,
    animal_id       BIGINT          NOT NULL,
    veterinario_id  BIGINT          NOT NULL,
    status          VARCHAR(50)     NOT NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_consulta_animal 
        FOREIGN KEY (animal_id) 
        REFERENCES animais (id),
    CONSTRAINT fk_consulta_veterinario 
        FOREIGN KEY (veterinario_id) 
        REFERENCES veterinarios (id)
);

CREATE TABLE diagnosticos (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY, 
    diagnostico     VARCHAR(255)    NOT NULL,
    observacoes     TEXT,
    consulta_id     BIGINT          NOT NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_diagnostico_consulta 
        FOREIGN KEY (consulta_id)
        REFERENCES consultas (id)
);

CREATE TABLE ufs (
    sigla       CHAR(2)         NOT NULL PRIMARY KEY,
    nome        VARCHAR(255)    NOT NULL,
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cidades (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(255)    NOT NULL,
    uf_sigla    CHAR(2)         NOT NULL,
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cidade_uf 
        FOREIGN KEY (uf_sigla) 
        REFERENCES ufs (sigla)
);

CREATE TABLE enderecos (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    rua         VARCHAR(255)    NOT NULL,
    bairro      VARCHAR(255)    NOT NULL,
    cep         CHAR(8)         NOT NULL,
    numero      INTEGER,
    cidade_id   BIGINT          NOT NULL,
    usuario_id  BIGINT          NOT NULL,
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_endereco_cidade 
        FOREIGN KEY (cidade_id) 
        REFERENCES cidades (id),
    CONSTRAINT fk_endereco_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios (id)
);


-- Populando a tabela ufs
INSERT INTO ufs (sigla, nome) VALUES 
    ('AC', 'Acre'),
    ('AL', 'Alagoas'),
    ('AP', 'Amapá'),
    ('AM', 'Amazonas'),
    ('BA', 'Bahia'),
    ('CE', 'Ceará'),
    ('DF', 'Distrito Federal'),
    ('ES', 'Espírito Santo'),
    ('GO', 'Goiás'),
    ('MA', 'Maranhão'),
    ('MT', 'Mato Grosso'),
    ('MS', 'Mato Grosso do Sul'),
    ('MG', 'Minas Gerais'),
    ('PA', 'Pará'),
    ('PB', 'Paraíba'),
    ('PR', 'Paraná'),
    ('PE', 'Pernambuco'),
    ('PI', 'Piauí'),
    ('RJ', 'Rio de Janeiro'),
    ('RN', 'Rio Grande do Norte'),
    ('RS', 'Rio Grande do Sul'),
    ('RO', 'Rondônia'),
    ('RR', 'Roraima'),
    ('SC', 'Santa Catarina'),
    ('SP', 'São Paulo'),
    ('SE', 'Sergipe'),
    ('TO', 'Tocantins');

-- Populando a tabela cidades
INSERT INTO cidades (nome, uf_sigla) VALUES 
    ('Rio Branco', 'AC'),
    ('Maceió', 'AL'),
    ('Macapá', 'AP'),
    ('Manaus', 'AM'),
    ('Salvador', 'BA'),
    ('Fortaleza', 'CE'),
    ('Brasília', 'DF'),
    ('Vitória', 'ES'),
    ('Goiânia', 'GO'),
    ('São Luís', 'MA'),
    ('Cuiabá', 'MT'),
    ('Campo Grande', 'MS'),
    ('Belo Horizonte', 'MG'),
    ('Belém', 'PA'),
    ('João Pessoa', 'PB'),
    ('Curitiba', 'PR'),
    ('Recife', 'PE'),
    ('Teresina', 'PI'),
    ('Rio de Janeiro', 'RJ'),
    ('Natal', 'RN'),
    ('Porto Alegre', 'RS'),
    ('Porto Velho', 'RO'),
    ('Boa Vista', 'RR'),
    ('Florianópolis', 'SC'),
    ('São Paulo', 'SP'),
    ('Aracaju', 'SE'),
    ('Palmas', 'TO');
