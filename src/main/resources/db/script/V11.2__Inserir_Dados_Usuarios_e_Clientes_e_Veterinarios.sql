-- Populando a tabela usuarios
INSERT INTO usuarios (usuario, senha, email, nome, cpf, telefone) VALUES 
    ('user1', 'senha123', 'user1@example.com', 'João Silva', '123.456.789-10', '123456789'),
    ('user2', 'password', 'user2@example.com', 'Maria Oliveira', '987.654.321-10', '987654321'),
    ('user3', 'mysecretpass', 'user3@example.com', 'Pedro Santos', '456.789.123-10', NULL),
    ('user4', 'securepass123', 'user4@example.com', 'Ana Souza', '852.741.963-10', '654987321'),
    ('user5', 'letmein', 'user5@example.com', 'Laura Costa', '159.357.852-10', '789654123'),
    ('user6', 'password123', 'user6@example.com', 'Carlos Oliveira', '369.258.147-10', '987654321'),
    ('user7', '123456', 'user7@example.com', 'Mariana Silva', '456.123.789-40', NULL),
    ('user8', 'qwerty', 'user8@example.com', 'Fernando Santos', '988.654.321-10', '123456789'),
    ('user9', 'abc123', 'user9@example.com', 'Patricia Souza', '789.456.123-10', '654987321'),
    ('user10', 'pass123', 'user10@example.com', 'Rafaela Costa', '159.368.852-10', NULL);

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