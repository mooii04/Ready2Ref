-- Inserción de usuarios (árbitros y entrenadores)
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Juan', 'Pérez', 'Gómez', 'jperez', 'jperez@example.com', '600123456', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Ana', 'López', 'Martínez', 'alopez', 'alopez@example.com', '600654321', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Luis', 'García', 'Fernández', 'lgarcia', 'lgarcia@example.com', '600423564', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);

-- Inserción de árbitros
INSERT INTO arbitro (id, edad, categoria, fecha_inscripcion, talla_botas, talla_camiseta, talla_calzonas, talla_chandal, foto, fecha_nacimiento) VALUES ((SELECT id FROM usuario WHERE username = 'jperez'), 30, 'OFICIAL', '2020-01-01', 42, 'M', 'M', 'M', 'https://www.example.com/foto1.jpg', '1995-01-01');
INSERT INTO arbitro (id, edad, categoria, fecha_inscripcion, talla_botas, talla_camiseta, talla_calzonas, talla_chandal, foto, fecha_nacimiento) VALUES ((SELECT id FROM usuario WHERE username = 'alopez'), 25, 'PROVINCIAL', '2020-01-01', 40, 'S', 'S', 'S', 'https://www.example.com/foto2.jpg', '2000-01-01');

INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'jperez'));
INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'alopez'));
INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'lgarcia'));

-- Inserción de entrenadores
INSERT INTO entrenador (id) VALUES ((SELECT id FROM usuario WHERE username = 'lgarcia'));

-- Inserción de un entrenamiento
INSERT INTO entrenamiento (id_entrenamiento, fecha, entrenador_id) VALUES (gen_random_uuid(), '2025-03-01', (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'lgarcia')));

-- Inserción de un pack
INSERT INTO pack (id, nombre, descripcion, precio) VALUES (gen_random_uuid(), 'Pack Básico', 'Incluye 5 entrenamientos', 200.00);

-- Inserción de un recibo
INSERT INTO recibo (id, cantidad, concepto, fecha_pago, metodo_pago, arbitro_id, pack_id) VALUES (gen_random_uuid(), 100.50, 'Pago de arbitraje', '2025-03-01', 'TARJETA', (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'jperez')), (SELECT id FROM pack WHERE nombre = 'Pack Básico'));

-- Inserción de una asistencia
INSERT INTO asistencia (id_asistencia, arbitro_id, entrenamiento_id) VALUES (gen_random_uuid(), (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'jperez')), (SELECT id_entrenamiento FROM entrenamiento WHERE fecha = '2025-03-01'));

-- Inserción de roles
INSERT INTO user_roles (roles, user_id) VALUES (0, (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'jperez')));
INSERT INTO user_roles (roles, user_id) VALUES (1, (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'alopez')));
INSERT INTO user_roles (roles, user_id) VALUES (2, (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'lgarcia')));