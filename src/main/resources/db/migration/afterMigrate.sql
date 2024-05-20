DELETE FROM vaccinations;
DELETE FROM vaccines;
DELETE FROM diagnostics;
DELETE FROM consults;
DELETE FROM animals;
DELETE FROM addresses;
DELETE FROM cities;
DELETE FROM states;
DELETE FROM roles;
DELETE FROM clients;
DELETE FROM veterinarians;
DELETE FROM users;
DELETE FROM photos;

SELECT setval('addresses_id_seq', 1, false);
SELECT setval('animals_id_seq', 1, false);
SELECT setval('cities_id_seq', 1, false);
SELECT setval('clients_id_seq', 1, false);
SELECT setval('consults_id_seq', 1, false);
SELECT setval('diagnostics_id_seq', 1, false);
SELECT setval('users_id_seq', 1, false);
SELECT setval('vaccinations_id_seq', 1, false);
SELECT setval('vaccines_id_seq', 1, false);
SELECT setval('veterinarians_id_seq', 1, false);

CREATE EXTENSION IF NOT EXISTS unaccent;

-- Populando a tabela ufs
INSERT INTO states (id, name) VALUES
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
INSERT INTO cities (name, state_id) VALUES
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

-- Populando a tabela usuarios -- password: 123
INSERT INTO users (username, password, email, name, document, phone) VALUES
    ('user1', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user1@email.com', 'João Silva', '12345678910', '123456789'),
    ('user2', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user2@email.com', 'Maria Oliveira', '98765432110', '987654321'),
    ('user3', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user3@email.com', 'Pedro Santos', '45678912310', NULL),
    ('user4', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user4@email.com', 'Ana Souza', '85274196310', '654987321'),
    ('user5', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user5@email.com', 'Laura Costa', '15935785210', '789654123'),
    ('user6', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user6@email.com', 'Carlos Oliveira', '36925814710', '987654321'),
    ('user7', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user7@email.com', 'Mariana Silva', '45612378940', NULL),
    ('user8', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user8@email.com', 'Fernando Santos', '98865432110', '123456789'),
    ('user9', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user9@email.com', 'Patricia Souza', '78945612310', '654987321'),
    ('user10', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user10@email.com', 'Rafaela Costa', '15936885210', NULL);

-- Populando a tabela roles
INSERT INTO roles (user_id, role) VALUES 
(1, 'CLIENT'),
(1, 'ADMIN'),
(2, 'CLIENT'),
(2, 'ADMIN'),
(3, 'CLIENT'),
(3, 'ADMIN'),
(4, 'CLIENT'),
(4, 'ADMIN'),
(5, 'CLIENT'),
(5, 'ADMIN'),
(6, 'CLIENT'),
(6, 'ADMIN'),
(7, 'CLIENT'),
(7, 'ADMIN'),
(8, 'CLIENT'),
(8, 'ADMIN'),
(9, 'CLIENT'),
(9, 'ADMIN'),
(10, 'CLIENT'),
(10, 'ADMIN');

-- Populando a tabela clientes
INSERT INTO clients (id) VALUES
    (1),
    (2),
    (3),
    (4),
    (5);

-- Populando a tabela veterinarios
INSERT INTO veterinarians (id, specialty, crmv, crmv_uf) VALUES
    (6, 'Clínica Geral', 'CRMV12345', 'SP'),
    (7, 'Ortopedia', 'CRMV67890', 'RJ'),
    (8, 'Dermatologia', 'CRMV54321', 'MG'),
    (9, 'Oftalmologia', 'CRMV09876', 'RS'),
    (10, 'Cardiologia', 'CRMV13579', 'PR');

-- Populando a tabela enderecos
INSERT INTO addresses (street, neighborhood, zip_code, number, city_id, user_id) VALUES
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
INSERT INTO animals (name, breed, birth_date, client_id, created_at, updated_at) VALUES
    ('Maximus', 'German Shepherd', '2019-03-25', 1, '2023-11-01', '2023-11-01'),
    ('Milo', 'Labrador Retriever', NULL, 2, '2023-11-05', '2023-11-05'),
    ('Coco', 'Pomeranian', '2022-08-10', 3, '2023-11-10', '2023-11-10'),
    ('Oscar', 'Pug', NULL, 4, '2023-11-15', '2023-11-15'),
    ('Buddy', 'Golden Retriever', '2022-07-20', 5, '2023-11-20', '2023-11-20'),
    ('Rocky', 'French Bulldog', NULL, 1, '2023-11-25', '2023-11-25'),
    ('Lola', 'Siberian Husky', '2023-10-05', 2, '2023-12-01', '2023-12-01'),
    ('Bailey', 'Dalmatian', NULL, 3, '2023-12-05', '2023-12-05'),
    ('Cooper', 'Border Collie', '2023-05-15', 4, '2023-12-10', '2023-12-10'),
    ('Duke', 'Rottweiler', NULL, 5, '2023-12-15', '2023-12-15'),
    ('Stella', 'English Bulldog', '2023-01-20', 1, '2023-12-20', '2023-12-20'),
    ('Tucker', 'Boxer', NULL, 2, '2023-12-25', '2023-12-25'),
    ('Zoe', 'Shih Tzu', '2023-09-10', 3, '2024-01-01', '2024-01-01'),
    ('Bailey', 'Yorkshire Terrier', NULL, 4, '2024-01-05', '2024-01-05'),
    ('Bear', 'Bulldog', '2022-04-22', 5, '2024-01-10', '2024-01-10'),
    ('Oliver', 'Cavalier King Charles Spaniel', NULL, 1, '2024-01-15', '2024-01-15'),
    ('Bentley', 'Chihuahua', '2023-06-30', 2, '2024-01-20', '2024-01-20'),
    ('Riley', 'Bernese Mountain Dog', NULL, 3, '2024-01-25', '2024-01-25'),
    ('Lily', 'Doberman Pinscher', '2022-12-10', 4, '2024-02-01', '2024-02-01'),
    ('Charlie', 'Shiba Inu', NULL, 5, '2024-02-05', '2024-02-05'),
    ('Daisy', 'Poodle', '2022-02-15', 1, '2024-02-10', '2024-02-10'),
    ('Lucy', 'Corgi', NULL, 2, '2024-02-15', '2024-02-15'),
    ('Bella', 'Maltese', '2022-10-20', 3, '2024-02-20', '2024-02-20'),
    ('Molly', 'Dachshund', NULL, 4, '2024-02-25', '2024-02-25'),
    ('Rosie', 'English Springer Spaniel', '2023-04-18', 5, '2024-03-01', '2024-03-01'),
    ('Toby', 'Jack Russell Terrier', NULL, 1, '2024-03-05', '2024-03-05'),
    ('Penny', 'Shetland Sheepdog', '2022-03-10', 2, '2024-03-10', '2024-03-10'),
    ('Mia', 'Great Dane', NULL, 3, '2024-03-15', '2024-03-15'),
    ('Milo', 'Australian Shepherd', '2023-07-25', 4, '2024-03-20', '2024-03-20'),
    ('Leo', 'Pit Bull', NULL, 5, '2024-03-25', '2024-03-25'),
    ('Sophie', 'Miniature Schnauzer', '2023-03-01', 1, '2024-04-01', '2024-04-01'),
    ('Luna', 'Golden Retriever', NULL, 2, '2024-04-05', '2024-04-05'),
    ('Jack', 'West Highland White Terrier', '2022-05-12', 3, '2024-04-10', '2024-04-10'),
    ('Rocco', 'Schnoodle', NULL, 4, '2024-04-15', '2024-04-15');
   


-- Populando a tabela vacinas
INSERT INTO vaccines (name, manufacturer, lot, unit_price, stock, expiration_date) VALUES
    ('Vacina A', 'Fabricante A', '123ABC', 50.00, 100, '2024-12-31'),
    ('Vacina B', 'Fabricante B', '456DEF', 60.00, 150, '2024-11-30'),
    ('Vacina C', 'Fabricante C', '789GHI', 70.00, 200, '2024-10-31'),
    ('Vacina D', 'Fabricante D', '101JKL', 80.00, 250, '2024-09-30'),
    ('Vacina E', 'Fabricante E', '112MNO', 90.00, 300, '2024-08-31');

-- Populando a tabela vacinacoes
INSERT INTO vaccinations (date_time, animal_id, veterinarian_id, vaccine_id) VALUES
    ('2023-03-10 08:00:00', 1, 6, 1),
    ('2023-03-11 09:30:00', 2, 7, 2),
    ('2023-03-12 10:45:00', 3, 8, 3),
    ('2023-03-13 11:20:00', 4, 9, 4),
    ('2023-03-14 13:15:00', 5, 10, 5);

-- Populando a tabela consultas
INSERT INTO consults (date_time, animal_id, veterinarian_id, status) VALUES
    ('2023-03-10 08:00:00', 1, 6, 'SCHEDULED'),
    ('2023-03-11 09:30:00', 2, 7, 'COMPLETED'),
    ('2023-03-12 10:45:00', 3, 8, 'CANCELED'),
    ('2023-03-13 11:20:00', 4, 9, 'COMPLETED'),
    ('2023-03-14 13:15:00', 5, 10, 'COMPLETED'),
    ('2023-03-15 14:30:00', 1, 7, 'COMPLETED'),
    ('2023-03-16 15:45:00', 2, 8, 'COMPLETED'),
    ('2023-03-17 16:20:00', 3, 9, 'COMPLETED'),
    ('2023-03-18 17:10:00', 4, 10, 'COMPLETED'),
    ('2023-03-19 18:00:00', 5, 6, 'COMPLETED');

-- Populando a tabela diagnosticos
INSERT INTO diagnostics (diagnosis, comments, consult_id) VALUES
    ('Gripe Canina', 'Prescrever medicação e repouso', 3),
    ('Dor de Ouvido', 'Prescrever antibióticos e monitorar', 5),
    ('Dermatite Alérgica', 'Recomendar dieta especial e tratamento tópico', 6),
    ('Fratura na Pata', 'Encaminhar para cirurgia ortopédica', 7),
    ('Infecção Respiratória', 'Prescrever antibióticos e repouso', 8);
