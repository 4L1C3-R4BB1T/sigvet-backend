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