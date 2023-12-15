USE ciudadanos;

CREATE TABLE ciudadano (
    id INT(11) NOT NULL AUTO_INCREMENT,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    tipo_documento VARCHAR(10) NOT NULL,
    numero_documento VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO ciudadano (apellido_paterno, apellido_materno, nombres, tipo_documento, numero_documento)
VALUES
    ('García', 'Pérez', 'Juan', 'DNI', '12345678'),
    ('Vega', 'Martínez', 'María', 'DNI', '87654321'),
    ('González', 'Fernández', 'Pedro', 'DNI', '23456789'),
    ('Hernández', 'Gómez', 'Luis', 'DNI', '98765432'),
    ('Martínez', 'Vega', 'Ana', 'DNI', '34567890'),
    ('Sánchez', 'González', 'Carlos', 'DNI', '56789012'),
    ('Rodríguez', 'Hernández', 'Laura', 'DNI', '89012345'),
    ('Fernández', 'Sánchez', 'Miguel', 'DNI', '43210987'),
    ('Gómez', 'Rodríguez', 'Paula', 'DNI', '65432109'),
    ('Pérez', 'García', 'Jorge', 'DNI', '21098765'),
    ('López', 'Fernández', 'María', 'DNI', '87654321'),
    ('García', 'Pérez', 'Manuel', 'DNI', '12345678');

COMMIT;