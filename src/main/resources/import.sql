INSERT INTO roles(nombre) VALUES('ROLE_SUPERADMINISTRADOR');
INSERT INTO roles(nombre) VALUES('ROLE_ADMINISTRADOR');
INSERT INTO roles(nombre) VALUES('ROLE_ASESOR');

INSERT INTO tipos_identificaciones(nombre) VALUES('cedula de ciudadania');
INSERT INTO tipos_identificaciones(nombre) VALUES('tarjeta de identidad');
INSERT INTO tipos_identificaciones(nombre) VALUES('cedula de extrangeria');
INSERT INTO tipos_identificaciones(nombre) VALUES('pasaporte');

INSERT INTO usuarios(nombre, apellido, tipo_identificacion, identificacion, usuario, clave, enabled, empresa) VALUES('carlos armando', 'mateus angulo', 1, '123456789', 'superadmin','$2a$10$tJMhtrgZUVODxjbMaiYGeumyTXGTMwnrBjy1CU/gANgFYZXdbx1dy', true, null);

INSERT INTO usuarios_roles(usuario, rol) VALUES(1,1);

INSERT INTO muelles(nombre) VALUES('muelle 1');
INSERT INTO muelles(nombre) VALUES('muelle 2');
INSERT INTO muelles(nombre) VALUES('muelle 3');
INSERT INTO muelles(nombre) VALUES('muelle 4');
INSERT INTO muelles(nombre) VALUES('muelle 5');

INSERT INTO destinos(nombre, enabled) VALUES('la bocana', true);
INSERT INTO destinos(nombre, enabled) VALUES('juanchaco', true);
INSERT INTO destinos(nombre, enabled) VALUES('ladrilleros', true);
INSERT INTO destinos(nombre, enabled) VALUES('pianguita', true);
INSERT INTO destinos(nombre, enabled) VALUES('maguipi', true);