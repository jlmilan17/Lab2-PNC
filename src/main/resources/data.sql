-- ============================================================
-- Seed para PNC. Corre en cada arranque (destructivo: DELETE + INSERT).
-- IDs generados dinámicamente con gen_random_uuid().
-- ============================================================

-- ---- Wipe en orden inverso de FKs ----
DELETE FROM charges;
UPDATE police_station SET director_id = NULL;
DELETE FROM police_officer;
DELETE FROM police_station;
DELETE FROM person;
DELETE FROM address;

-- ---- Direcciones ----
INSERT INTO address (address_uuid, department, street, municipality, neighborhood) VALUES
(gen_random_uuid(), 'San Salvador',  '1a Calle Poniente',        'San Salvador',         'Centro Histórico'),
(gen_random_uuid(), 'Santa Ana',     'Avenida Independencia',    'Santa Ana',            'Barrio El Calvario'),
(gen_random_uuid(), 'La Libertad',   'Boulevard Constitución',   'Santa Tecla',          'Colonia Miramonte'),
(gen_random_uuid(), 'Sonsonate',     'Calle El Comercio',        'Sonsonate',            'Barrio El Centro'),
(gen_random_uuid(), 'Ahuachapán',    '1a Avenida Norte',         'Ahuachapán',           'Barrio El Calvario'),
(gen_random_uuid(), 'Chalatenango',  'Calle Morazán',            'Chalatenango',         'Barrio El Centro'),
(gen_random_uuid(), 'Cuscatlán',     'Calle Principal',          'Cojutepeque',          'Barrio San José'),
(gen_random_uuid(), 'La Paz',        'Avenida Masferrer',        'Zacatecoluca',         'Colonia San Antonio'),
(gen_random_uuid(), 'Cabañas',       'Calle Central',            'Sensuntepeque',        'Barrio La Esperanza'),
(gen_random_uuid(), 'San Vicente',   'Avenida Cuscatlán',        'San Vicente',          'Barrio El Santuario'),
(gen_random_uuid(), 'Usulután',      'Calle Grimaldi',           'Usulután',             'Barrio La Parroquia'),
(gen_random_uuid(), 'San Miguel',    '4a Calle Oriente',         'San Miguel',           'Barrio San Felipe'),
(gen_random_uuid(), 'Morazán',       'Calle La Paz',             'San Francisco Gotera', 'Barrio El Centro'),
(gen_random_uuid(), 'La Unión',      'Avenida General Cabañas',  'La Unión',             'Barrio Concepción');

-- ---- Personas ----
INSERT INTO person (person_uuid, dui, name, phone_number, address_id)
SELECT gen_random_uuid(), dui, name, phone_number, address_id FROM (
    VALUES
    ('11223344-5', 'María López',    '9999-9991', (SELECT address_uuid FROM address WHERE department = 'La Libertad' LIMIT 1)),
    ('11223344-6', 'Roberto Cruz',   '9999-9992', (SELECT address_uuid FROM address WHERE department = 'Sonsonate' LIMIT 1)),
    ('11223344-7', 'Ana Hernández',  '9999-9993', (SELECT address_uuid FROM address WHERE department = 'Ahuachapán' LIMIT 1)),
    ('11223344-8', 'Luis Mendoza',   '9999-9994', (SELECT address_uuid FROM address WHERE department = 'Chalatenango' LIMIT 1)),
    ('12345678-9', 'Juan Pérez',     '7777-7777', (SELECT address_uuid FROM address WHERE department = 'Cuscatlán' LIMIT 1)),
    ('98765432-1', 'Carlos Ramírez', '8888-8888', (SELECT address_uuid FROM address WHERE department = 'La Paz' LIMIT 1)),
    ('55667788-2', 'Pedro Martínez', '6666-6666', (SELECT address_uuid FROM address WHERE department = 'Cabañas' LIMIT 1)),
    ('11335577-3', 'Luis González',  '5555-5555', (SELECT address_uuid FROM address WHERE department = 'San Vicente' LIMIT 1)),
    ('22446688-4', 'Ana Martínez',   '4444-4444', (SELECT address_uuid FROM address WHERE department = 'Usulután' LIMIT 1)),
    ('33557799-5', 'José Fernández', '3333-3333', (SELECT address_uuid FROM address WHERE department = 'San Miguel' LIMIT 1)),
    ('44668800-6', 'Marta Vargas',   '2222-2222', (SELECT address_uuid FROM address WHERE department = 'Morazán' LIMIT 1)),
    ('55779911-7', 'Sandra Rivera',  '1111-1111', (SELECT address_uuid FROM address WHERE department = 'La Unión' LIMIT 1))
) AS t(dui, name, phone_number, address_id);

-- ---- Estaciones ----
INSERT INTO police_station (police_station_uuid, name, address_id)
SELECT gen_random_uuid(), name, address_id FROM (
    VALUES
    ('Delegación Centro',    (SELECT address_uuid FROM address WHERE department = 'San Salvador' LIMIT 1)),
    ('Delegación Occidente', (SELECT address_uuid FROM address WHERE department = 'Santa Ana' LIMIT 1))
) AS t(name, address_id);

-- ---- Oficiales ----
INSERT INTO police_officer (police_officer_uuid, code_number, badge, person_id, police_station_id)
SELECT gen_random_uuid(), code_number, badge, person_id, police_station_id FROM (
    VALUES
    ('PNC-0001', 'B-001',
        (SELECT person_uuid FROM person WHERE dui = '11223344-5' LIMIT 1),
        (SELECT police_station_uuid FROM police_station WHERE name = 'Delegación Centro' LIMIT 1)),
    ('PNC-0002', 'B-002',
        (SELECT person_uuid FROM person WHERE dui = '11223344-6' LIMIT 1),
        (SELECT police_station_uuid FROM police_station WHERE name = 'Delegación Centro' LIMIT 1)),
    ('PNC-0003', 'B-003',
        (SELECT person_uuid FROM person WHERE dui = '11223344-7' LIMIT 1),
        (SELECT police_station_uuid FROM police_station WHERE name = 'Delegación Occidente' LIMIT 1)),
    ('PNC-0004', 'B-004',
        (SELECT person_uuid FROM person WHERE dui = '11223344-8' LIMIT 1),
        (SELECT police_station_uuid FROM police_station WHERE name = 'Delegación Occidente' LIMIT 1))
) AS t(code_number, badge, person_id, police_station_id);

-- ---- Asignar director a Delegación Centro ----
UPDATE police_station
SET director_id = (SELECT police_officer_uuid FROM police_officer WHERE code_number = 'PNC-0001' LIMIT 1)
WHERE name = 'Delegación Centro';

-- ---- Cargos ----
INSERT INTO charges (charges_uuid, date, charge_type, description, accuser_id, accused_id, registered_by_officer_id, police_station_id)
SELECT gen_random_uuid(), date, charge_type, description, accuser_id, accused_id, registered_by_officer_id, police_station_id FROM (
    VALUES
    ('2026-03-15 10:30:00'::timestamp, 'PENAL',    'Robo agravado en vía pública',
        (SELECT person_uuid FROM person WHERE dui = '12345678-9' LIMIT 1),
        (SELECT person_uuid FROM person WHERE dui = '98765432-1' LIMIT 1),
        (SELECT police_officer_uuid FROM police_officer WHERE code_number = 'PNC-0001' LIMIT 1),
        (SELECT police_station_uuid FROM police_station WHERE name = 'Delegación Centro' LIMIT 1)),
    ('2026-04-02 14:15:00'::timestamp, 'PROCESAL', 'Daños a propiedad ajena',
        (SELECT person_uuid FROM person WHERE dui = '12345678-9' LIMIT 1),
        (SELECT person_uuid FROM person WHERE dui = '55667788-2' LIMIT 1),
        (SELECT police_officer_uuid FROM police_officer WHERE code_number = 'PNC-0002' LIMIT 1),
        (SELECT police_station_uuid FROM police_station WHERE name = 'Delegación Centro' LIMIT 1)),
    ('2026-04-20 09:45:00'::timestamp, 'PENAL',    'Lesiones graves',
        (SELECT person_uuid FROM person WHERE dui = '22446688-4' LIMIT 1),
        (SELECT person_uuid FROM person WHERE dui = '98765432-1' LIMIT 1),
        (SELECT police_officer_uuid FROM police_officer WHERE code_number = 'PNC-0003' LIMIT 1),
        (SELECT police_station_uuid FROM police_station WHERE name = 'Delegación Occidente' LIMIT 1)),
    ('2026-05-01 16:00:00'::timestamp, 'PENAL',    'Amenazas con arma',
        (SELECT person_uuid FROM person WHERE dui = '33557799-5' LIMIT 1),
        (SELECT person_uuid FROM person WHERE dui = '98765432-1' LIMIT 1),
        (SELECT police_officer_uuid FROM police_officer WHERE code_number = 'PNC-0004' LIMIT 1),
        (SELECT police_station_uuid FROM police_station WHERE name = 'Delegación Occidente' LIMIT 1)),
    ('2026-05-10 11:20:00'::timestamp, 'PROCESAL', 'Incumplimiento de contrato',
        (SELECT person_uuid FROM person WHERE dui = '11335577-3' LIMIT 1),
        (SELECT person_uuid FROM person WHERE dui = '55667788-2' LIMIT 1),
        (SELECT police_officer_uuid FROM police_officer WHERE code_number = 'PNC-0001' LIMIT 1),
        (SELECT police_station_uuid FROM police_station WHERE name = 'Delegación Centro' LIMIT 1))
) AS t(date, charge_type, description, accuser_id, accused_id, registered_by_officer_id, police_station_id);
