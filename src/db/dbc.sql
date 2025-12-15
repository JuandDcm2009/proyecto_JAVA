INSERT INTO roles(id, nombre) VALUES
(1, "Gerente"),
(2, "Acesor Financiero"),
(3, "Gerente de zona");

INSERT INTO continentes (id, nombre) VALUES (1, 'Norte America');
INSERT INTO continentes (id, nombre) VALUES (2, 'Sur America');
INSERT INTO continentes (id, nombre) VALUES (3, 'Europa');

INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Estados Unidos', '+1', 1);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Canada', '+1', 1);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Mexico', '+52', 1);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Bahamas', '+1-242', 1);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Cuba', '+53', 1);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Republica Dominicana', '+1-809', 1);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Jamaica', '+1-876', 1);

INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Colombia', '+57', 2);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Brasil', '+55', 2);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Argentina', '+54', 2);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Chile', '+56', 2);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Peru', '+51', 2);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Ecuador', '+593', 2);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Venezuela', '+58', 2);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Uruguay', '+598', 2);

INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Reino Unido', '+44', 3);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Alemania', '+49', 3);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Francia', '+33', 3);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('España', '+34', 3);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Italia', '+39', 3);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Portugal', '+351', 3);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Suecia', '+46', 3);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Noruega', '+47', 3);
INSERT INTO paises (nombre, codigo, continente_id) VALUES ('Suiza', '+41', 3);

