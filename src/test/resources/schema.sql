CREATE TABLE usuarios (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario     VARCHAR(100)    NOT NULL UNIQUE,
    senha       VARCHAR(100)    NOT NULL,
    email       VARCHAR(100)    NOT NULL UNIQUE,
    nome        VARCHAR(100)    NOT NULL,
    cpf         VARCHAR(14)     NOT NULL UNIQUE,
    telefone    VARCHAR(18),
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE
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
    deleted BOOLEAN DEFAULT FALSE,
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
    updated_at       TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE vacinacoes (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY, 
    data_horario    TIMESTAMP       NOT NULL,
    animal_id       BIGINT          NOT NULL,
    veterinario_id  BIGINT          NOT NULL,
    vacina_id       BIGINT          NOT NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE,
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
    deleted BOOLEAN DEFAULT FALSE,
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
    deleted BOOLEAN DEFAULT FALSE,
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
    deleted BOOLEAN DEFAULT FALSE,
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
    deleted BOOLEAN DEFAULT FALSE,
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


-- Populando a tabela usuarios
INSERT INTO usuarios (usuario, senha, email, nome, cpf, telefone) VALUES 
    ('user1', 'senha123', 'user1@example.com', 'João Silva', '12345678910', '123456789'),
    ('user2', 'password', 'user2@example.com', 'Maria Oliveira', '98765432110', '987654321'),
    ('user3', 'mysecretpass', 'user3@example.com', 'Pedro Santos', '45678912310', NULL),
    ('user4', 'securepass123', 'user4@example.com', 'Ana Souza', '85274196310', '654987321'),
    ('user5', 'letmein', 'user5@example.com', 'Laura Costa', '15935785210', '789654123'),
    ('user6', 'password123', 'user6@example.com', 'Carlos Oliveira', '36925814710', '987654321'),
    ('user7', '123456', 'user7@example.com', 'Mariana Silva', '45612378940', NULL),
    ('user8', 'qwerty', 'user8@example.com', 'Fernando Santos', '98865432110', '123456789'),
    ('user9', 'abc123', 'user9@example.com', 'Patricia Souza', '78945612310', '654987321'),
    ('user10', 'pass123', 'user10@example.com', 'Rafaela Costa', '15936885210', NULL);

-- Populando a tabela clientes
INSERT INTO clientes (id) VALUES 
    (1),
    (2),
    (3),
    (4),
    (5);

-- Populando a tabela veterinarios
INSERT INTO veterinarios (id, especialidade, crmv, crmv_uf) VALUES 
    (6, 'Clínica Geral', 'CRMV12345', 'SP'),
    (7, 'Ortopedia', 'CRMV67890', 'RJ'),
    (8, 'Dermatologia', 'CRMV54321', 'MG'),
    (9, 'Oftalmologia', 'CRMV09876', 'RS'),
    (10, 'Cardiologia', 'CRMV13579', 'PR');

-- Populando a tabela enderecos
INSERT INTO enderecos (rua, bairro, cep, numero, cidade_id, usuario_id) VALUES 
    ('Rua A', 'Bairro 1', '12345678', 100, 1, 1),
    ('Rua B', 'Bairro 2', '23456789', 200, 2, 2),
    ('Rua C', 'Bairro 3', '34567890', NULL, 3, 3),
    ('Rua D', 'Bairro 4', '45678901', 400, 4, 4),
    ('Rua E', 'Bairro 5', '56789012', 500, 5, 5),
    ('Rua F', 'Bairro 6', '67890123', NULL, 6, 6),
    ('Rua G', 'Bairro 7', '78901234', 700, 7, 7),
    ('Rua H', 'Bairro 8', '89012345', 800, 8, 8),
    ('Rua I', 'Bairro 9', '90123456', NULL, 9, 9),
    ('Rua J', 'Bairro 10', '01234567', 1000, 10, 10);

-- Populando a tabela animais
INSERT INTO animais (nome, raca, data_nascimento, cliente_id) VALUES 
    ('Tobby', 'Vira-lata', '2019-05-15', 1),
    ('Rex', 'Labrador', NULL, 2),
    ('Luna', 'Siamese', '2020-08-20', 3),
    ('Max', 'Golden Retriever', NULL, 4),
    ('Bella', 'Persian', '2018-12-10', 5);

-- Populando a tabela vacinas
INSERT INTO vacinas (nome, fabricante, lote, preco_unitario, estoque, data_validade) VALUES 
    ('Vacina A', 'Fabricante A', '123ABC', 50.00, 100, '2024-12-31'),
    ('Vacina B', 'Fabricante B', '456DEF', 60.00, 150, '2024-11-30'),
    ('Vacina C', 'Fabricante C', '789GHI', 70.00, 200, '2024-10-31'),
    ('Vacina D', 'Fabricante D', '101JKL', 80.00, 250, '2024-09-30'),
    ('Vacina E', 'Fabricante E', '112MNO', 90.00, 300, '2024-08-31');

-- Populando a tabela vacinacoes
INSERT INTO vacinacoes (data_horario, animal_id, veterinario_id, vacina_id) VALUES 
    ('2023-03-10 08:00:00', 1, 6, 1),
    ('2023-03-11 09:30:00', 2, 7, 2),
    ('2023-03-12 10:45:00', 3, 8, 3),
    ('2023-03-13 11:20:00', 4, 9, 4),
    ('2023-03-14 13:15:00', 5, 10, 5);

-- Populando a tabela consultas
INSERT INTO consultas (data_horario, animal_id, veterinario_id, status) VALUES 
    ('2023-03-10 08:00:00', 1, 6, 'Agendada'),
    ('2023-03-11 09:30:00', 2, 7, 'Agendada'),
    ('2023-03-12 10:45:00', 3, 8, 'Concluída'),
    ('2023-03-13 11:20:00', 4, 9, 'Agendada'),
    ('2023-03-14 13:15:00', 5, 10, 'Concluída'),
    ('2023-03-15 14:30:00', 1, 7, 'Concluída'),
    ('2023-03-16 15:45:00', 2, 8, 'Concluída'),
    ('2023-03-17 16:20:00', 3, 9, 'Concluída'),
    ('2023-03-18 17:10:00', 4, 10, 'Agendada'),
    ('2023-03-19 18:00:00', 5, 6, 'Agendada');

-- Populando a tabela diagnosticos
INSERT INTO diagnosticos (diagnostico, observacoes, consulta_id) VALUES 
    ('Gripe Canina', 'Prescrever medicação e repouso', 3),
    ('Dor de Ouvido', 'Prescrever antibióticos e monitorar', 5),
    ('Dermatite Alérgica', 'Recomendar dieta especial e tratamento tópico', 6),
    ('Fratura na Pata', 'Encaminhar para cirurgia ortopédica', 7),
    ('Infecção Respiratória', 'Prescrever antibióticos e repouso', 8);
