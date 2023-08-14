INSERT INTO empresas(nombre, nit, email, telefono, imagen, enabled) VALUES('transtour', '123456789', 'transtour@gmail.com', '123456789', 'ninguna', true);

INSERT INTO tipos_identificaciones(nombre) VALUES('cedula');

INSERT INTO roles(nombre) VALUES('ROLE_SUPERADMINISTRADOR');
INSERT INTO roles(nombre) VALUES('ROLE_ADMINISTRADOR');
INSERT INTO roles(nombre) VALUES('ROLE_ASESOR');

INSERT INTO usuarios(nombre, apellido, tipo_identificacion, identificacion, usuario, clave, enabled, empresa) VALUES('carlos', 'mateus', 1, '1096037321', 'matius','$2a$10$80BQAlwTXF23tHL28hk34e01H9jzWNWRGeA8hNbHMIl5vAcgPoVa6', true, 1);
INSERT INTO usuarios(nombre, apellido, tipo_identificacion, identificacion, usuario, clave, enabled, empresa) VALUES('eicen', 'viafara', 1, '123456789', 'jauder','$2a$10$80BQAlwTXF23tHL28hk34e01H9jzWNWRGeA8hNbHMIl5vAcgPoVa6', true, 1);

INSERT INTO usuarios_roles(usuario, rol) VALUES(1,1);
INSERT INTO usuarios_roles(usuario, rol) VALUES(2,2);

INSERT INTO destinos(nombre, enabled) VALUES('maguipi', true);
INSERT INTO destinos(nombre, enabled) VALUES('pianguita', true);
INSERT INTO destinos(nombre, enabled) VALUES('la bocana', true);
INSERT INTO destinos(nombre, enabled) VALUES('juanchaco', true);
INSERT INTO destinos(nombre, enabled) VALUES('ladrilleros', true);