INSERT INTO roles(nombre) VALUES('ROLE_SUPERADMINISTRADOR');
INSERT INTO roles(nombre) VALUES('ROLE_ADMINISTRADOR');
INSERT INTO roles(nombre) VALUES('ROLE_ASESOR');

INSERT INTO tipos_identificaciones(nombre) VALUES('cedula de ciudadania');
INSERT INTO tipos_identificaciones(nombre) VALUES('tarjeta de identidad');
INSERT INTO tipos_identificaciones(nombre) VALUES('cedula de extrangeria');
INSERT INTO tipos_identificaciones(nombre) VALUES('nit');
INSERT INTO tipos_identificaciones(nombre) VALUES('pasaporte');

INSERT INTO usuarios(nombre, apellido, tipo_identificacion, identificacion, usuario, clave, enabled, empresa) VALUES('carlos armando', 'mateus angulo', 1, '1096037321', 'cmatius','$2a$10$e3lmEEFkyOBY3BwGB1BiBuaAqgBcwJw3LceimSdSey8Kf5swIKlLa', true, null);

INSERT INTO usuarios_roles(usuario, rol) VALUES(1,1);