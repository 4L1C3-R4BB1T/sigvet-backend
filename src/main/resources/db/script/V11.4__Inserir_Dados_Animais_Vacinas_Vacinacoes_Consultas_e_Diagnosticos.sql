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
