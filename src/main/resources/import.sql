INSERT INTO roles(nombre) VALUES('ROLE_SUPERADMINISTRADOR');
INSERT INTO roles(nombre) VALUES('ROLE_ADMINISTRADOR');
INSERT INTO roles(nombre) VALUES('ROLE_ASESOR');

INSERT INTO tipos_identificaciones(nombre) VALUES('cedula de ciudadania');
INSERT INTO tipos_identificaciones(nombre) VALUES('tarjeta de identidad');
INSERT INTO tipos_identificaciones(nombre) VALUES('cedula de extrangeria');
INSERT INTO tipos_identificaciones(nombre) VALUES('nit');
INSERT INTO tipos_identificaciones(nombre) VALUES('pasaporte');

INSERT INTO usuarios(nombre, apellido, tipo_identificacion, identificacion, usuario, clave, enabled, empresa) VALUES('carlos armando', 'mateus angulo', 1, '1096037321', 'cmatius','$2a$10$nTvqW/9CfFXXUJQGGnU4d.V3hb6BX6A14Rf8GaJZTOL1SrMn5pCXe', true, null);
INSERT INTO usuarios(nombre, apellido, tipo_identificacion, identificacion, usuario, clave, enabled, empresa) VALUES('eicen jauder', 'viafara cordoba', 1, '1234567890', 'eviafara','$2a$10$nTvqW/9CfFXXUJQGGnU4d.V3hb6BX6A14Rf8GaJZTOL1SrMn5pCXe', true, null);

INSERT INTO usuarios_roles(usuario, rol) VALUES(1,1);
INSERT INTO usuarios_roles(usuario, rol) VALUES(2,2);