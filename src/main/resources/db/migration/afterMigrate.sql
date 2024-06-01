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
    ('user1', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user1@email.com', 'João Silva', '12345678910', '123456789572'),
    ('user2', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user2@email.com', 'Maria Oliveira', '98765432110', '987654321572'),
    ('user3', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user3@email.com', 'Pedro Santos', '45678912310', '123123123572'),
    ('user4', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user4@email.com', 'Ana Souza', '85274196310', '654987321572'),
    ('user5', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user5@email.com', 'Laura Costa', '15935785210', '789654123572'),
    ('user6', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user6@email.com', 'Carlos Oliveira', '36925814710', '987654321572'),
    ('user7', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user7@email.com', 'Mariana Silva', '45612378940', '123123123572'),
    ('user8', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user8@email.com', 'Fernando Santos', '98865432110', '123456789572'),
    ('user9', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user9@email.com', 'Patricia Souza', '78945612310', '654987321572'),
    ('user10', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user10@email.com', 'Rafaela Costa', '15936885210', '123123123572'),
    ('user11', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user11@email.com', 'Guilherme Almeida', '12312312310', '123123123572'),
    ('user12', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user12@email.com', 'Julia Carvalho', '32132132110', '321321321572'),
    ('user13', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user13@email.com', 'Thiago Mendes', '98798798710', '987987987572'),
    ('user14', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user14@email.com', 'Fernanda Lima', '65465465410', '654654654572'),
    ('user15', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user15@email.com', 'Ricardo Pereira', '32165498710', '321654987572'),
    ('user16', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user16@email.com', 'Renata Nogueira', '78912345610', '789123456572'),
    ('user17', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user17@email.com', 'Felipe Souza', '14725836910', '147258369572'),
    ('user18', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user18@email.com', 'Amanda Ribeiro', '25836914710', '258369147572'),
    ('user19', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user19@email.com', 'Gabriel Ferreira', '36914725810', '369147258572'),
    ('user20', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user20@email.com', 'Bruna Cardoso', '14736925810', '147369258572'),
    ('user21', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user21@email.com', 'Lucas Lima', '25814736910', '258147369572'),
    ('user22', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user22@email.com', 'Juliana Araújo', '36925814710', '369258147572'),
    ('user23', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user23@email.com', 'Rafael Castro', '74125836910', '741258369572'),
    ('user24', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user24@email.com', 'Gabriela Teixeira', '85236914710', '852369147572'),
    ('user25', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user25@email.com', 'Eduardo Silva', '96314725810', '963147258572'),
    ('user26', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user26@email.com', 'Camila Souza', '14785236910', '147852369572'),
    ('user27', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user27@email.com', 'Vinicius Gomes', '25874136910', '258741369572'),
    ('user28', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user28@email.com', 'Larissa Martins', '36985214710', '369852147572'),
    ('user29', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user29@email.com', 'Rodrigo Almeida', '74136925810', '741369258572'),
    ('user30', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user30@email.com', 'Aline Cardoso', '85214736910', '852147369572'),
    ('user31', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user31@email.com', 'Fábio Araújo', '96325814710', '963258147572'),
    ('user32', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user32@email.com', 'Carla Ribeiro', '14796325810', '147963258572'),
    ('user33', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user33@email.com', 'Wagner Lima', '25896314710', '258963147572'),
    ('user34', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user34@email.com', 'Tânia Mendes', '36974125810', '369741258572'),
    ('user35', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user35@email.com', 'Leandro Souza', '74185236910', '741852369572'),
    ('user36', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user36@email.com', 'Adriana Teixeira', '85296314710', '852963147572'),
    ('user37', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user37@email.com', 'Fernando Castro', '96374125810', '963741258572'),
    ('user38', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user38@email.com', 'Regina Lima', '14725874110', '147258741572'),
    ('user39', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user39@email.com', 'Bruno Ribeiro', '25836985210', '258369852572'),
    ('user40', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user40@email.com', 'Natália Araújo', '36914796310', '369147963572'),
    ('user41', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user41@email.com', 'Jorge Almeida', '74125896310', '741258963572'),
    ('user42', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user42@email.com', 'Renata Cardoso', '85236974110', '852369741572'),
    ('user43', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user43@email.com', 'Paulo Lima', '96314785210', '963147852572'),
    ('user44', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user44@email.com', 'Tatiana Nogueira', '14736985210', '147369852572'),
    ('user45', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user45@email.com', 'André Silva', '25814796310', '258147963572'),
    ('user46', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user46@email.com', 'Lara Borges', '15975345610', '159753456572'),
    ('user47', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user47@email.com', 'Lucas Fernandes', '98732165410', '987321654572'),
    ('user48', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user48@email.com', 'Paula Souza', '12378945610', '123789456572'),
    ('user49', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user49@email.com', 'Marcos Oliveira', '45612378910', '456123789572'),
    ('user50', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user50@email.com', 'Beatriz Ferreira', '78945612310', '789456123572'),
    ('user51', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user51@email.com', 'Gabriel Mendes', '51345678911', '51345678903'),
    ('user52', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user52@email.com', 'Luiza Carvalho', '52345678912', '52345678903'),
    ('user53', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user53@email.com', 'Thiago Pereira', '53345678913', '53345678903'),
    ('user54', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user54@email.com', 'Aline Silva', '54345678914', '54345678903'),
    ('user55', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user55@email.com', 'Rodrigo Souza', '55345678915', '55345678903'),
    ('user56', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user56@email.com', 'Juliana Rocha', '56345678916', '56345678903'),
    ('user57', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user57@email.com', 'Rafael Lima', '57345678917', '57345678903'),
    ('user58', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user58@email.com', 'Camila Oliveira', '58345678918', '58345678903'),
    ('user59', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user59@email.com', 'Bruno Almeida', '59345678919', '59345678903'),
    ('user60', '$2a$12$9l3NLPa7qYLwGzHbikLciOdKZDlQc9t54pJyD9J0JgPafuR4SyXsC', 'user60@email.com', 'Fernanda Costa', '60345678910', '60345678906');

-- Populando a tabela clientes
INSERT INTO clients (id) VALUES
    (1),
    (2),
    (3),
    (4),
    (5),
    (11),
    (12),
    (13),
    (14),
    (15),
    (16),
    (17),
    (18),
    (19),
    (20),
    (21),
    (22),
    (23),
    (24),
    (25),
    (26),
    (27),
    (28),
    (29),
    (30),
    (31),
    (32),
    (33),
    (34),
    (35),
    (36),
    (37),
    (38),
    (39),
    (40),
    (41),
    (42),
    (43),
    (44),
    (45),
    (46),
    (47),
    (48),
    (49),
    (50);

-- Populando a tabela veterinarios
INSERT INTO veterinarians (id, specialty, crmv, crmv_uf) VALUES
    (6, 'Clínica Geral', 'CRMV12345', 'SP'),
    (7, 'Ortopedia', 'CRMV67890', 'RJ'),
    (8, 'Dermatologia', 'CRMV54321', 'MG'),
    (9, 'Oftalmologia', 'CRMV09876', 'RS'),
    (10, 'Cardiologia', 'CRMV13579', 'PR'),
    (51, 'Clínica Geral', 'CRMV11111', 'SP'),
    (52, 'Ortopedia', 'CRMV22222', 'RJ'),
    (53, 'Dermatologia', 'CRMV33333', 'MG'),
    (54, 'Oftalmologia', 'CRMV44444', 'RS'),
    (55, 'Cardiologia', 'CRMV55555', 'PR'),
    (56, 'Clínica Geral', 'CRMV66666', 'SP'),
    (57, 'Ortopedia', 'CRMV77777', 'RJ'),
    (58, 'Dermatologia', 'CRMV88888', 'MG'),
    (59, 'Oftalmologia', 'CRMV99999', 'RS'),
    (60, 'Cardiologia', 'CRMV00000', 'PR');

-- Populando a tabela roles para clientes
INSERT INTO roles (user_id, role)
SELECT id, 'CLIENT' FROM clients;

INSERT INTO roles (user_id, role)
SELECT id, 'ADMIN' FROM clients;

-- Populando a tabela roles para veterinarios
INSERT INTO roles (user_id, role)
SELECT id, 'CLIENT' FROM veterinarians;

INSERT INTO roles (user_id, role)
SELECT id, 'ADMIN' FROM veterinarians;

-- Populando a tabela enderecos
INSERT INTO addresses (street, neighborhood, zip_code, number, city_id, user_id) VALUES
    ('Rua A', 'Bairro 1', '12345678', 100, 1, 1),
    ('Rua B', 'Bairro 2', '23456789', 200, 2, 2),
    ('Rua C', 'Bairro 3', '34567890', 2, 3, 3),
    ('Rua D', 'Bairro 4', '45678901', 400, 4, 4),
    ('Rua E', 'Bairro 5', '56789012', 500, 5, 5),
    ('Rua F', 'Bairro 6', '67890123', 1, 6, 6),
    ('Rua G', 'Bairro 7', '78901234', 700, 7, 7),
    ('Rua H', 'Bairro 8', '89012345', 800, 8, 8),
    ('Rua I', 'Bairro 9', '90123456', 13, 9, 9),
    ('Rua J', 'Bairro 10', '01234567', 1000, 10, 10),
    ('Rua K', 'Bairro 11', '11234567', 1100, 11, 11),
    ('Rua L', 'Bairro 12', '22345678', 1200, 12, 12),
    ('Rua M', 'Bairro 13', '33456789', 13, 13, 13),
    ('Rua N', 'Bairro 14', '44567890', 1400, 14, 14),
    ('Rua O', 'Bairro 15', '55678901', 1500, 15, 15),
    ('Rua P', 'Bairro 16', '66789012', 23, 16, 16),
    ('Rua Q', 'Bairro 17', '77890123', 1700, 17, 17),
    ('Rua R', 'Bairro 18', '88901234', 1800, 18, 18),
    ('Rua S', 'Bairro 19', '99012345', 20, 19, 19),
    ('Rua T', 'Bairro 20', '00123456', 2000, 20, 20),
    ('Rua U', 'Bairro 21', '11234678', 2100, 21, 21),
    ('Rua V', 'Bairro 22', '22345789', 2200, 22, 22),
    ('Rua W', 'Bairro 23', '33456890', 12, 23, 23),
    ('Rua X', 'Bairro 24', '44567901', 2400, 24, 24),
    ('Rua Y', 'Bairro 25', '55679012', 2500, 25, 25),
    ('Rua Z', 'Bairro 26', '66780123', 23, 26, 26),
    ('Rua AA', 'Bairro 27', '77891234', 2700, 27, 27),
    ('Rua AB', 'Bairro 28', '88902345', 2800, 1, 28),
    ('Rua AC', 'Bairro 29', '99013456', 21, 2, 29),
    ('Rua AD', 'Bairro 30', '00124567', 3000, 3, 30),
    ('Rua AE', 'Bairro 31', '11234789', 3100, 4, 31),
    ('Rua AF', 'Bairro 32', '22345890', 3200, 5, 32),
    ('Rua AG', 'Bairro 33', '33456901', 65, 6, 33),
    ('Rua AH', 'Bairro 34', '44567012', 45, 7, 34),
    ('Rua AI', 'Bairro 35', '55678123', 3500, 8, 35),
    ('Rua AJ', 'Bairro 36', '66789234', 46, 9, 36),
    ('Rua AK', 'Bairro 37', '77890345', 3700, 10, 37),
    ('Rua AL', 'Bairro 38', '88901456', 3800, 11, 38),
    ('Rua AM', 'Bairro 39', '99012567', 47, 12, 39),
    ('Rua AN', 'Bairro 40', '00123678', 4000, 13, 40),
    ('Rua AO', 'Bairro 41', '11234890', 4100, 14, 41),
    ('Rua AP', 'Bairro 42', '22345901', 4200, 15, 42),
    ('Rua AQ', 'Bairro 43', '33457012', 98, 16, 43),
    ('Rua AR', 'Bairro 44', '44568123', 4400, 17, 44),
    ('Rua AS', 'Bairro 45', '55679234', 4500, 18, 45),
    ('Rua AT', 'Bairro 46', '66780345', 79, 19, 46),
    ('Rua AU', 'Bairro 47', '77891456', 4700, 20, 47),
    ('Rua AV', 'Bairro 48', '88902567', 4800, 21, 48),
    ('Rua AW', 'Bairro 49', '99013678', 78, 22, 49),
    ('Rua AX', 'Bairro 50', '00124789', 5000, 23, 50),
    ('Rua A1', 'Bairro 1A', '12345671', 100, 24, 51),
    ('Rua B2', 'Bairro 2B', '23456782', 200, 25, 52),
    ('Rua C3', 'Bairro 3C', '34567893', 300, 27, 53),
    ('Rua D4', 'Bairro 4D', '45678904', 400, 1, 54),
    ('Rua E5', 'Bairro 5E', '56789015', 500, 2, 55),
    ('Rua F6', 'Bairro 6F', '67890126', 600, 3, 56),
    ('Rua G7', 'Bairro 7G', '78901237', 700, 4, 57),
    ('Rua H8', 'Bairro 8H', '89012348', 800, 5, 58),
    ('Rua I9', 'Bairro 9I', '90123459', 900, 6, 59),
    ('Rua J0', 'Bairro 0J', '01234560', 1000, 7, 60);

-- Populando a tabela animais
INSERT INTO animals (name, breed, birth_date, client_id, created_at, updated_at) VALUES
    ('Maximus', 'German Shepherd', '2019-03-25', 1, '2023-11-01', '2023-11-01'),
    ('Milo', 'Labrador Retriever', '2022-07-20', 2, '2023-11-05', '2023-11-05'),
    ('Coco', 'Pomeranian', '2022-08-10', 3, '2023-11-10', '2023-11-10'),
    ('Oscar', 'Pug', '2022-07-20', 4, '2023-11-15', '2023-11-15'),
    ('Buddy', 'Golden Retriever', '2022-07-20', 5, '2023-11-20', '2023-11-20'),
    ('Rocky', 'French Bulldog', '2022-07-20', 1, '2023-11-25', '2023-11-25'),
    ('Lola', 'Siberian Husky', '2023-10-05', 2, '2023-12-01', '2023-12-01'),
    ('Bailey', 'Dalmatian', '2022-07-20', 3, '2023-12-05', '2023-12-05'),
    ('Cooper', 'Border Collie', '2023-05-15', 4, '2023-12-10', '2023-12-10'),
    ('Duke', 'Rottweiler', '2022-07-20', 5, '2023-12-15', '2023-12-15'),
    ('Stella', 'English Bulldog', '2023-01-20', 1, '2023-12-20', '2023-12-20'),
    ('Tucker', 'Boxer', '2022-07-20', 2, '2023-12-25', '2023-12-25'),
    ('Zoe', 'Shih Tzu', '2023-09-10', 3, '2024-01-01', '2024-01-01'),
    ('Bailey', 'Yorkshire Terrier', '2022-07-20', 4, '2024-01-05', '2024-01-05'),
    ('Bear', 'Bulldog', '2022-04-22', 5, '2024-01-10', '2024-01-10'),
    ('Oliver', 'Cavalier King Charles Spaniel', '2022-07-20', 1, '2024-01-15', '2024-01-15'),
    ('Bentley', 'Chihuahua', '2023-06-30', 2, '2024-01-20', '2024-01-20'),
    ('Riley', 'Bernese Mountain Dog', '2022-07-20', 3, '2024-01-25', '2024-01-25'),
    ('Lily', 'Doberman Pinscher', '2022-12-10', 4, '2024-02-01', '2024-02-01'),
    ('Charlie', 'Shiba Inu', '2022-07-20', 5, '2024-02-05', '2024-02-05'),
    ('Daisy', 'Poodle', '2022-02-15', 1, '2024-02-10', '2024-02-10'),
    ('Lucy', 'Corgi', '2022-07-20', 2, '2024-02-15', '2024-02-15'),
    ('Bella', 'Maltese', '2022-10-20', 3, '2024-02-20', '2024-02-20'),
    ('Molly', 'Dachshund', '2022-07-20', 4, '2024-02-25', '2024-02-25'),
    ('Rosie', 'English Springer Spaniel', '2023-04-18', 5, '2024-03-01', '2024-03-01'),
    ('Toby', 'Jack Russell Terrier', '2022-07-20', 1, '2024-03-05', '2024-03-05'),
    ('Penny', 'Shetland Sheepdog', '2022-03-10', 2, '2024-03-10', '2024-03-10'),
    ('Mia', 'Great Dane', '2022-07-20', 3, '2024-03-15', '2024-03-15'),
    ('Milo', 'Australian Shepherd', '2023-07-25', 4, '2024-03-20', '2024-03-20'),
    ('Leo', 'Pit Bull', '2022-07-20', 5, '2024-03-25', '2024-03-25'),
    ('Sophie', 'Miniature Schnauzer', '2023-03-01', 1, '2024-04-01', '2024-04-01'),
    ('Luna', 'Golden Retriever', '2022-07-20', 2, '2024-04-05', '2024-04-05'),
    ('Jack', 'West Highland White Terrier', '2022-05-12', 3, '2024-04-10', '2024-04-10'),
    ('Rocco', 'Schnoodle', '2022-07-20', 4, '2024-04-15', '2024-04-15'),
    ('Kiwi', 'Papagaio', '2019-07-10', 11, '2023-11-01', '2023-11-01'),
    ('Coco', 'Cacatua', '2020-04-05', 12, '2023-11-05', '2023-11-05'),
    ('Sunny', 'Canário', '2018-12-20', 13, '2023-11-10', '2023-11-10'),
    ('Whiskers', 'Persa', '2017-09-15', 14, '2023-11-15', '2023-11-15'),
    ('Tiger', 'Bengal', '2019-02-10', 15, '2023-11-20', '2023-11-20'),
    ('Oreo', 'Gato Americano de Pêlo Curto', '2021-05-25', 16, '2023-11-25', '2023-11-25'),
    ('Buddy', 'Gato Ragdoll', '2020-11-30', 25, '2023-12-01', '2023-12-01'),
    ('Luna', 'Gato Siamês', '2018-07-15', 30, '2023-12-05', '2023-12-05'),
    ('Shadow', 'Gato Maine Coon', '2019-10-20', 42, '2023-12-10', '2023-12-10'),
    ('Milo', 'Gato Scottish Fold', '2020-04-01', 50, '2023-12-15', '2023-12-15'),
    ('Bolinha', 'Papagaio', '2021-05-10', 12, '2023-06-01', '2023-06-01'),
    ('Pipoca', 'Calopsita', '2020-11-15', 13, '2023-07-05', '2023-07-05'),
    ('Zezinho', 'Canário', '2019-03-22', 14, '2023-08-10', '2023-08-10'),
    ('Mingau', 'Persa', '2018-02-14', 15, '2023-09-15', '2023-09-15'),
    ('Tom', 'Siamês', '2020-07-08', 16, '2023-10-20', '2023-10-20'),
    ('Nina', 'Bengal', '2022-12-01', 11, '2023-11-25', '2023-11-25'),
    ('Pretinho', 'Gato Americano de Pêlo Curto', '2021-06-17', 12, '2023-12-30', '2023-12-30'),
    ('Chico', 'Gato Ragdoll', '2019-04-21', 13, '2024-01-15', '2024-01-15'),
    ('Fifi', 'Gato Maine Coon', '2018-09-29', 14, '2024-02-05', '2024-02-05'),
    ('Snow', 'Gato Scottish Fold', '2021-01-13', 15, '2024-03-10', '2024-03-10'),
    ('Rex', 'Golden Retriever', '2017-08-09', 16, '2024-04-01', '2024-04-01'),
    ('Lobo', 'Pastor Alemão', '2020-10-19', 17, '2024-05-05', '2024-05-05'),
    ('Thor', 'Husky Siberiano', '2022-05-27', 18, '2024-06-10', '2024-06-10'),
    ('Bella', 'Labrador', '2019-11-04', 19, '2024-07-15', '2024-07-15'),
    ('Mel', 'Poodle', '2021-03-16', 20, '2024-08-20', '2024-08-20'),
    ('Max', 'Buldogue Francês', '2018-12-23', 21, '2024-09-25', '2024-09-25'),
    ('Molly', 'Beagle', '2020-07-30', 22, '2024-10-30', '2024-10-30'),
    ('Luna', 'Chihuahua', '2022-02-05', 23, '2024-11-10', '2024-11-10'),
    ('Fred', 'Basset Hound', '2019-05-21', 24, '2024-12-15', '2024-12-15'),
    ('Rocky', 'Dachshund', '2020-08-14', 25, '2025-01-20', '2025-01-20'),
    ('Rex', 'Labrador Retriever', '2023-03-10', 1, '2024-05-15', '2024-05-15'),
    ('Boris', 'Golden Retriever', '2022-11-15', 2, '2024-05-16', '2024-05-16'),
    ('Miau', 'Siamese', '2023-07-20', 3, '2024-05-17', '2024-05-17'),
    ('Whiskers', 'Persian', '2023-04-25', 4, '2024-05-18', '2024-05-18'),
    ('Luna', 'German Shepherd', '2023-01-05', 5, '2024-05-19', '2024-05-19'),
    ('Maximus', 'Siberian Husky', '2023-08-30', 16, '2024-05-20', '2024-05-20'),
    ('Lola', 'Beagle', '2023-05-20', 17, '2024-05-21', '2024-05-21'),
    ('Toby', 'Boxer', '2022-12-15', 18, '2024-05-22', '2024-05-22'),
    ('Bella', 'Poodle', '2023-09-10', 19, '2024-05-23', '2024-05-23'),
    ('Charlie', 'Shih Tzu', '2023-06-05', 20, '2024-05-24', '2024-05-24'),
    ('Rocky', 'French Bulldog', '2023-03-10', 11, '2024-05-25', '2024-05-25'),
    ('Simba', 'Maine Coon', '2022-11-15', 12, '2024-05-26', '2024-05-26'),
    ('Sasha', 'Ragdoll', '2023-07-20', 13, '2024-05-27', '2024-05-27'),
    ('Whiskey', 'Bengal', '2023-04-25', 14, '2024-05-28', '2024-05-28'),
    ('Lucky', 'Persian', '2023-01-05', 15, '2024-05-29', '2024-05-29'),
    ('Cleo', 'Labrador Retriever', '2023-08-30', 16, '2024-05-30', '2024-05-30'),
    ('Milo', 'Golden Retriever', '2023-05-20', 17, '2024-05-31', '2024-05-31'),
    ('Sophie', 'Poodle', '2022-12-15', 18, '2024-06-01', '2024-06-01'),
    ('Oscar', 'Siberian Husky', '2023-09-10', 19, '2024-06-02', '2024-06-02'),
    ('Lola', 'German Shepherd', '2023-06-05', 20, '2024-06-03', '2024-06-03'),
    ('Leo', 'Beagle', '2023-03-10', 21, '2024-06-04', '2024-06-04'),
    ('Bella', 'Siamese', '2022-11-15', 22, '2024-06-05', '2024-06-05'),
    ('Max', 'Maine Coon', '2023-07-20', 23, '2024-06-06', '2024-06-06'),
    ('Luna', 'Siberian Husky', '2023-04-25', 24, '2024-06-07', '2024-06-07'),
    ('Tiger', 'Ragdoll', '2023-01-05', 25, '2024-06-08', '2024-06-08'),
    ('Oreo', 'Bengal', '2023-08-30', 26, '2024-06-09', '2024-06-09'),
    ('Ziggy', 'Persian', '2023-05-20', 27, '2024-06-10', '2024-06-10'),
    ('Lily', 'Labrador Retriever', '2023-02-15', 28, '2024-06-11', '2024-06-11'),
    ('Buddy', 'Golden Retriever', '2022-10-30', 29, '2024-06-12', '2024-06-12'),
    ('Nala', 'German Shepherd', '2023-07-05', 30, '2024-06-13', '2024-06-13'),
    ('Mittens', 'Siamese', '2023-04-10', 31, '2024-06-14', '2024-06-14'),
    ('Rocky', 'Maine Coon', '2023-01-15', 32, '2024-06-15', '2024-06-15'),
    ('Shadow', 'Ragdoll', '2023-09-20', 33, '2024-06-16', '2024-06-16'),
    ('Milo', 'Bengal', '2023-06-25', 34, '2024-06-17', '2024-06-17'),
    ('Coco', 'Persian', '2023-03-30', 35, '2024-06-18', '2024-06-18'),
    ('Tigger', 'Labrador Retriever', '2023-11-05', 36, '2024-06-19', '2024-06-19'),
    ('Smokey', 'Golden Retriever', '2023-08-10', 37, '2024-06-20', '2024-06-20'),
    ('Lucy', 'German Shepherd', '2023-05-15', 38, '2024-06-21', '2024-06-21'),
    ('Chloe', 'Siamese', '2023-02-20', 39, '2024-06-22', '2024-06-22'),
    ('Jasper', 'Maine Coon', '2022-12-25', 40, '2024-06-23', '2024-06-23'),
    ('Oliver', 'Ragdoll', '2023-10-01', 41, '2024-06-24', '2024-06-24'),
    ('Smokey', 'Bengal', '2023-07-06', 42, '2024-06-25', '2024-06-25'),
    ('Boots', 'Persian', '2023-04-11', 43, '2024-06-26', '2024-06-26'),
    ('Milo', 'Labrador Retriever', '2023-01-16', 44, '2024-06-27', '2024-06-27'),
    ('Mittens', 'Golden Retriever', '2022-10-31', 45, '2024-06-28', '2024-06-28'),
    ('Luna', 'German Shepherd', '2023-08-05', 46, '2024-06-29', '2024-06-29'),
    ('Buddy', 'Siamese', '2023-05-10', 47, '2024-06-30', '2024-06-30'),
    ('Tiger', 'Maine Coon', '2023-02-15', 48, '2024-07-01', '2024-07-01'),
    ('Oreo', 'Ragdoll', '2022-12-20', 49, '2024-07-02', '2024-07-02'),
    ('Ziggy', 'Bengal', '2023-09-25', 50, '2024-07-03', '2024-07-03');

-- Populando a tabela vacinas
INSERT INTO vaccines (name, manufacturer, lot, unit_price, stock, expiration_date) VALUES
    ('Raiva', 'Zoetis', 'RA12345', 45.00, 100, '2024-12-31'),
    ('V10', 'MSD Animal Health', 'V101234', 60.00, 150, '2024-11-30'),
    ('V8', 'Boehringer Ingelheim', 'V81234', 55.00, 200, '2024-10-31'),
    ('Leptospirose', 'Virbac', 'LEP1234', 50.00, 250, '2024-09-30'),
    ('Gripe Canina', 'Ceva', 'GC12345', 65.00, 300, '2024-08-31'),
    ('Giárdia', 'Zoetis', 'GI12345', 70.00, 110, '2024-07-31'),
    ('Leishmaniose', 'Virbac', 'LEISH123', 80.00, 120, '2024-06-30'),
    ('Tosse dos Canis', 'Boehringer Ingelheim', 'TC12345', 75.00, 130, '2024-06-30'),
    ('Coronavirose Canina', 'Ceva', 'CC12345', 50.00, 140, '2024-08-30'),
    ('V4 Felina', 'Zoetis', 'V41234', 55.00, 150, '2024-10-31'),
    ('Leucemia Felina', 'MSD Animal Health', 'LF12345', 65.00, 160, '2025-02-28'),
    ('Rinotraqueíte', 'Virbac', 'RT12345', 45.00, 170, '2025-01-31'),
    ('Clamidiose', 'Ceva', 'CL12345', 50.00, 180, '2024-12-31'),
    ('Calicivirose', 'Zoetis', 'CV12345', 55.00, 190, '2024-11-30'),
    ('Panleucopenia', 'MSD Animal Health', 'PL12345', 60.00, 200, '2024-10-31'),
    ('Raiva Canina', 'Zoetis', 'RC23456', 45.00, 100, '2024-11-30'),
    ('V11', 'MSD Animal Health', 'V112345', 65.00, 150, '2024-10-31'),
    ('Giardíase', 'Virbac', 'GI23456', 55.00, 200, '2024-09-30'),
    ('Hepatite Canina', 'Ceva', 'HC23456', 50.00, 250, '2024-08-31'),
    ('Parainfluenza Canina', 'Boehringer Ingelheim', 'PIC12345', 60.00, 300, '2024-07-31'),
    ('Parvovirose Canina', 'Zoetis', 'PVC12345', 70.00, 110, '2024-06-30'),
    ('Leishmaniose Canina', 'Virbac', 'LC12345', 80.00, 120, '2025-05-31'),
    ('Tosse dos Canis (Intranasal)', 'Boehringer Ingelheim', 'TCI2345', 75.00, 130, '2024-08-30'),
    ('Parvovirose Felina', 'Ceva', 'PVF12345', 50.00, 140, '2024-10-31'),
    ('Raiva Felina', 'MSD Animal Health', 'RF123456', 45.00, 150, '2025-02-28'),
    ('Herpesvírus Felino', 'Zoetis', 'HVF12345', 55.00, 160, '2025-01-31'),
    ('Calcivirose Felina', 'Virbac', 'CVF12345', 50.00, 170, '2024-12-31'),
    ('Clamidiose Felina', 'Ceva', 'CF123456', 60.00, 180, '2024-11-30'),
    ('Leucemia Felina (Refinada)', 'MSD Animal Health', 'LF23456', 65.00, 190, '2024-10-31'),
    ('Panleucopenia Felina (Modificada)', 'Zoetis', 'PLF12345', 55.00, 200, '2024-09-30');

-- Populando a tabela vacinacoes
INSERT INTO vaccinations (date, hour, animal_id, veterinarian_id, vaccine_id) VALUES
    ('2024-03-10', '09:00', 1, 6, 1),
    ('2024-03-11', '09:00', 2, 7, 2),
    ('2024-03-12', '10:45', 3, 8, 3),
    ('2024-03-13', '11:20', 4, 9, 4),
    ('2024-03-14', '13:15', 5, 10, 5),
    ('2024-03-15', '08:00', 6, 6, 1),
    ('2024-03-16', '09:30', 7, 7, 2),
    ('2024-04-17', '10:45', 8, 8, 3),
    ('2024-04-18', '11:20', 9, 9, 4),
    ('2024-04-19', '13:15', 10, 10, 5),
    ('2024-05-20', '08:00', 11, 51, 1),
    ('2024-05-21', '09:30', 12, 52, 2),
    ('2024-05-22', '10:45', 13, 53, 3),
    ('2024-05-23', '11:20', 14, 54, 4),
    ('2024-05-24', '13:15', 15, 55, 5);

-- Populando a tabela consultas
INSERT INTO consults (date, hour, animal_id, veterinarian_id, status) VALUES
    ('2024-03-10', '08:00', 1, 6, 'SCHEDULED'),
    ('2024-03-11', '09:30', 2, 7, 'COMPLETED'),
    ('2024-03-12', '10:00', 3, 8, 'CANCELED'),
    ('2024-03-13', '11:00', 4, 9, 'COMPLETED'),
    ('2024-03-14', '13:00', 5, 10, 'COMPLETED'),
    ('2024-03-15', '14:30', 1, 7, 'COMPLETED'),
    ('2024-03-16', '15:00', 2, 8, 'COMPLETED'),
    ('2024-03-17', '16:00', 3, 9, 'COMPLETED'),
    ('2024-03-18', '17:00', 4, 10, 'COMPLETED'),
    ('2024-03-19', '18:00', 5, 6, 'COMPLETED'),
    ('2024-03-10', '08:30', 1, 6, 'SCHEDULED'),
    ('2024-03-11', '10:30', 2, 7, 'COMPLETED'),
    ('2024-03-12', '10:30', 3, 8, 'CANCELED'),
    ('2024-03-13', '11:30', 4, 9, 'COMPLETED'),
    ('2024-03-14', '13:30', 5, 10, 'COMPLETED'),
    ('2024-03-15', '14:00', 1, 7, 'COMPLETED'),
    ('2024-03-16', '15:30', 2, 8, 'COMPLETED'),
    ('2024-03-17', '16:30', 3, 9, 'COMPLETED'),
    ('2024-03-18', '17:30', 4, 10, 'COMPLETED'),
    ('2024-03-19', '18:30', 5, 6, 'COMPLETED'),
    ('2024-03-30', '08:30', 16, 6, 'SCHEDULED'),
    ('2024-03-31', '10:00', 17, 7, 'SCHEDULED'),
    ('2024-04-01',  '11:0', 18, 8, 'SCHEDULED'),
    ('2024-04-02',  '12:30', 19, 9, 'SCHEDULED'),
    ('2024-04-03',  '14:00', 20, 10, 'SCHEDULED'),
    ('2024-04-04',  '15:00', 21, 6, 'SCHEDULED'),
    ('2024-04-05',  '16:30', 22, 7, 'SCHEDULED'),
    ('2024-04-06',  '17:30', 23, 8, 'SCHEDULED'),
    ('2024-04-07',  '18:30', 24, 9, 'SCHEDULED'),
    ('2024-04-08',  '17:00', 25, 10, 'SCHEDULED');

-- Populando a tabela diagnosticos
INSERT INTO diagnostics (diagnosis, comments, consult_id) VALUES
    ('Gripe Canina', 'Prescrever medicação e repouso', 2),
    ('Dor de Ouvido', 'Prescrever antibióticos e monitorar', 4),
    ('Dor de Ouvido', 'Prescrever antibióticos e monitorar', 5),
    ('Dermatite Alérgica', 'Recomendar dieta especial e tratamento tópico', 6),
    ('Fratura na Pata', 'Encaminhar para cirurgia ortopédica', 7),
    ('Infecção Respiratória', 'Prescrever antibióticos e repouso', 8),
    ('Gripe Canina', 'Prescrever medicação e repouso', 9),
    ('Dor de Ouvido', 'Prescrever antibióticos e monitorar', 10),
    ('Dermatite Alérgica', 'Recomendar dieta especial e tratamento tópico', 12),
    ('Fratura na Pata', 'Encaminhar para cirurgia ortopédica', 14),
    ('Infecção Respiratória', 'Prescrever antibióticos e repouso', 15),
    ('Gripe Canina', 'Prescrever medicação e repouso', 16),
    ('Infecção Respiratória', 'Prescrever antibióticos e repouso', 17),
    ('Fratura na Pata', 'Encaminhar para cirurgia ortopédica', 18),
    ('Dermatite Alérgica', 'Recomendar dieta especial e tratamento tópico', 19),
    ('Dor de Ouvido', 'Prescrever antibióticos e monitorar', 20);
