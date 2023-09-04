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

INSERT INTO empresas (nombre, nit, email, telefono, imagen, enabled) VALUES('transtour', '123456789-1', 'email@ejemplo.com', '123456780', '', true);
INSERT INTO empresas (nombre, nit, email, telefono, imagen, enabled) VALUES('portenita','123456789-2','email1@ejemplo.com', '123456781', '', true);
INSERT INTO empresas (nombre, nit, email, telefono, imagen, enabled) VALUES('agencia de viaje','123456789-3','email2@ejemplo.com', '123456782', '', true);
INSERT INTO empresas (nombre, nit, email, telefono, imagen, enabled) VALUES('viajes del pacifico','123456789-4','email3@ejemplo.com', '123456783', '', true);
INSERT INTO empresas (nombre, nit, email, telefono, imagen, enabled) VALUES('pacific tour','123456789-5','email4@jemplo.com', '123456784', '', true);
INSERT INTO empresas (nombre, nit, email, telefono, imagen, enabled) VALUES('gonzales embarques','123456789-6','email5@ejemplo.com', '123456785', '', true);
INSERT INTO empresas (nombre, nit, email, telefono, imagen, enabled) VALUES('saray store','123456789-7','email6@ejemplo.com', '123456786', '', true);
INSERT INTO empresas (nombre, nit, email, telefono, imagen, enabled) VALUES('calimita tour','123456789-8','email7@ejemplo.com', '123456787', '', true);
INSERT INTO empresas (nombre, nit, email, telefono, imagen, enabled) VALUES('transpacifico','123456789-9','email8@ejemplo.com', '123456788', '', true);
INSERT INTO empresas (nombre, nit, email, telefono, imagen, enabled) VALUES('tura board','123456780-1','email9@ejemplo.com', '123456789', '', true);