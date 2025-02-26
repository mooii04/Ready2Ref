-- Inserción de usuarios (árbitros y entrenadores)
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Juan', 'Pérez', 'Gómez', 'jperez', 'jperez@example.com', '600123456', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Ana', 'López', 'Martínez', 'alopez', 'alopez@example.com', '600654321', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Luis', 'García', 'Fernández', 'lgarcia', 'lgarcia@example.com', '600423564', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Carlos', 'Ruiz', 'Sánchez', 'cruiz', 'cruiz@example.com', '600111222', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'María', 'Hernández', 'Díaz', 'mhernandez', 'mhernandez@example.com', '600222333', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Pedro', 'Torres', 'Gómez', 'ptorres', 'ptorres@example.com', '600333444', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Laura', 'Martín', 'Jiménez', 'lmartin', 'lmartin@example.com', '600444555', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Javier', 'Gómez', 'Ruiz', 'jgomez', 'jgomez@example.com', '600555666', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Sara', 'Fernández', 'López', 'sfernandez', 'sfernandez@example.com', '600666777', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Miguel', 'Sánchez', 'Pérez', 'msanchez', 'msanchez@example.com', '600777888', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);
INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, username, email, telefono, password, enabled) VALUES (gen_random_uuid(), 'Natalia', 'Díaz', 'Martínez', 'ndiaz', 'ndiaz@example.com', '600888999', '{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy', true);

-- Inserción de árbitros
INSERT INTO arbitro (id, edad, categoria, fecha_inscripcion, talla_botas, talla_camiseta, talla_calzonas, talla_chandal, foto, fecha_nacimiento) VALUES ((SELECT id FROM usuario WHERE username = 'jperez'), 30, 'OFICIAL', '2020-01-01', 42, 'M', 'M', 'M', 'https://www.example.com/foto1.jpg', '1995-01-01');
INSERT INTO arbitro (id, edad, categoria, fecha_inscripcion, talla_botas, talla_camiseta, talla_calzonas, talla_chandal, foto, fecha_nacimiento) VALUES ((SELECT id FROM usuario WHERE username = 'alopez'), 25, 'PROVINCIAL', '2020-01-01', 40, 'S', 'S', 'S', 'https://www.example.com/foto2.jpg', '2000-01-01');
INSERT INTO arbitro (id, edad, categoria, fecha_inscripcion, talla_botas, talla_camiseta, talla_calzonas, talla_chandal, foto, fecha_nacimiento) VALUES ((SELECT id FROM usuario WHERE username = 'mhernandez'), 28, 'PRIMERA', '2020-01-01', 41, 'M', 'M', 'M', 'https://www.example.com/foto4.jpg', '1997-01-01');
INSERT INTO arbitro (id, edad, categoria, fecha_inscripcion, talla_botas, talla_camiseta, talla_calzonas, talla_chandal, foto, fecha_nacimiento) VALUES ((SELECT id FROM usuario WHERE username = 'ptorres'), 32, 'SEGUNDA', '2020-01-01', 43, 'L', 'L', 'L', 'https://www.example.com/foto5.jpg', '1993-01-01');
INSERT INTO arbitro (id, edad, categoria, fecha_inscripcion, talla_botas, talla_camiseta, talla_calzonas, talla_chandal, foto, fecha_nacimiento) VALUES ((SELECT id FROM usuario WHERE username = 'lmartin'), 27, 'ASISTENTE_PRIMERA', '2020-01-01', 40, 'S', 'S', 'S', 'https://www.example.com/foto6.jpg', '1998-01-01');
INSERT INTO arbitro (id, edad, categoria, fecha_inscripcion, talla_botas, talla_camiseta, talla_calzonas, talla_chandal, foto, fecha_nacimiento) VALUES ((SELECT id FROM usuario WHERE username = 'jgomez'), 29, 'ASISTENTE_SEGUNDA', '2020-01-01', 41, 'M', 'M', 'M', 'https://www.example.com/foto7.jpg', '1996-01-01');
INSERT INTO arbitro (id, edad, categoria, fecha_inscripcion, talla_botas, talla_camiseta, talla_calzonas, talla_chandal, foto, fecha_nacimiento) VALUES ((SELECT id FROM usuario WHERE username = 'sfernandez'), 31, 'ASISTENTE_3RFEF', '2020-01-01', 42, 'L', 'L', 'L', 'https://www.example.com/foto8.jpg', '1994-01-01');
INSERT INTO arbitro (id, edad, categoria, fecha_inscripcion, talla_botas, talla_camiseta, talla_calzonas, talla_chandal, foto, fecha_nacimiento) VALUES ((SELECT id FROM usuario WHERE username = 'msanchez'), 33, 'OFICIAL', '2020-01-01', 43, 'M', 'M', 'M', 'https://www.example.com/foto9.jpg', '1992-01-01');
INSERT INTO arbitro (id, edad, categoria, fecha_inscripcion, talla_botas, talla_camiseta, talla_calzonas, talla_chandal, foto, fecha_nacimiento) VALUES ((SELECT id FROM usuario WHERE username = 'ndiaz'), 26, 'PROVINCIAL', '2020-01-01', 40, 'S', 'S', 'S', 'https://www.example.com/foto10.jpg', '1999-01-01');

INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'jperez'));
INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'alopez'));
INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'lgarcia'));
INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'cruiz'));
INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'mhernandez'));
INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'ptorres'));
INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'lmartin'));
INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'jgomez'));
INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'sfernandez'));
INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'msanchez'));
INSERT INTO refresh_token (created_at, expire_at,  id, user_id) VALUES ('2025-02-25 18:08.309182+00', '2030-02-25 18:08.309182+00', gen_random_uuid(), (SELECT id FROM usuario WHERE username = 'ndiaz'));

-- Inserción de entrenadores
INSERT INTO entrenador (id) VALUES ((SELECT id FROM usuario WHERE username = 'lgarcia'));
INSERT INTO entrenador (id) VALUES ((SELECT id FROM usuario WHERE username = 'cruiz'));

-- Inserción de un pack
INSERT INTO pack (id, nombre, descripcion, precio) VALUES (gen_random_uuid(), 'Pack Básico', 'Incluye 5 entrenamientos', 200.00);
INSERT INTO pack (id, nombre, descripcion, precio) VALUES (gen_random_uuid(), 'Pack Premium', 'Incluye 10 entrenamientos', 350.00);
INSERT INTO pack (id, nombre, descripcion, precio) VALUES (gen_random_uuid(), 'Pack VIP', 'Incluye 15 entrenamientos', 500.00);

-- Inserción de un entrenamiento
INSERT INTO entrenamiento (id_entrenamiento, fecha, entrenador_id) VALUES (gen_random_uuid(), '2025-03-01', (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'lgarcia')));
INSERT INTO entrenamiento (id_entrenamiento, fecha, entrenador_id) VALUES (gen_random_uuid(), '2025-03-02', (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'lgarcia')));
INSERT INTO entrenamiento (id_entrenamiento, fecha, entrenador_id) VALUES (gen_random_uuid(), '2025-03-03', (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'lgarcia')));
INSERT INTO entrenamiento (id_entrenamiento, fecha, entrenador_id) VALUES (gen_random_uuid(), '2025-03-04', (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'lgarcia')));
INSERT INTO entrenamiento (id_entrenamiento, fecha, entrenador_id) VALUES (gen_random_uuid(), '2025-03-05', (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'lgarcia')));
INSERT INTO entrenamiento (id_entrenamiento, fecha, entrenador_id) VALUES (gen_random_uuid(), '2025-03-06', (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'lgarcia')));
INSERT INTO entrenamiento (id_entrenamiento, fecha, entrenador_id) VALUES (gen_random_uuid(), '2025-03-07', (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'cruiz')));
INSERT INTO entrenamiento (id_entrenamiento, fecha, entrenador_id) VALUES (gen_random_uuid(), '2025-03-08', (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'cruiz')));
INSERT INTO entrenamiento (id_entrenamiento, fecha, entrenador_id) VALUES (gen_random_uuid(), '2025-03-09', (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'cruiz')));
INSERT INTO entrenamiento (id_entrenamiento, fecha, entrenador_id) VALUES (gen_random_uuid(), '2025-03-10', (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'cruiz')));

-- Inserción de roles
INSERT INTO user_roles (roles, user_id) VALUES (0, (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'jperez')));
INSERT INTO user_roles (roles, user_id) VALUES (1, (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'alopez')));
INSERT INTO user_roles (roles, user_id) VALUES (2, (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'lgarcia')));
INSERT INTO user_roles (roles, user_id) VALUES (2, (SELECT id FROM entrenador WHERE id = (SELECT id FROM usuario WHERE username = 'cruiz')));
INSERT INTO user_roles (roles, user_id) VALUES (0, (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'mhernandez')));
INSERT INTO user_roles (roles, user_id) VALUES (0, (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'ptorres')));
INSERT INTO user_roles (roles, user_id) VALUES (0, (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'lmartin')));
INSERT INTO user_roles (roles, user_id) VALUES (0, (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'jgomez')));
INSERT INTO user_roles (roles, user_id) VALUES (0, (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'sfernandez')));
INSERT INTO user_roles (roles, user_id) VALUES (0, (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'msanchez')));
INSERT INTO user_roles (roles, user_id) VALUES (0, (SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'ndiaz')));

-- Inserción de mensajes
INSERT INTO mensaje (id, asunto, contenido, fecha_envio, leido, ) VALUES (gen_random_uuid(), 'Entrenamiento Semana 23-29 Diciembre', 'Buenas tardes compañeros, el entreno de esta semana próxima ya esta subido a la plataforma', '2025-02-25', false);


-- Inserción de arbitro_meensaje
INSERT INTO arbitro_mensaje (arbitro_id, mensaje_id) VALUES ((SELECT id FROM arbitro WHERE id = (SELECT id FROM usuario WHERE username = 'alopez')), (SELECT id FROM mensaje WHERE id = (SELECT id FROM mensaje WHERE asunto = 'Entrenamiento Semana 23-29 Diciembre')));