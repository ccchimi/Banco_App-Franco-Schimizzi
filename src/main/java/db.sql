CREATE DATABASE banco;
USE banco;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    rol ENUM('cliente', 'empleado') NOT NULL
);

CREATE TABLE cuentas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    saldo DECIMAL(10,2) DEFAULT 0.0,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE TABLE transacciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cuenta INT,
    tipo ENUM('deposito', 'retiro', 'transferencia') NOT NULL,
    monto DECIMAL(10,2),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    detalle VARCHAR(255),
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id)
);

CREATE TABLE cajero (
                        id INT PRIMARY KEY,
                        cantidad_disponible DECIMAL(10,2)
);

INSERT INTO cajero (id, cantidad_disponible) VALUES (1, 5000.00);

-- Usuarios con contraseñas hasheadas
-- 1234 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4
-- admin 8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918
-- 4567 db2e7f1bd5ab9968ae76199b7cc74795ca7404d5a08d78567715ce532f9d2669
-- admin2 1c142b2d01aa34e9a36bde480645a57fd69e14155dacfab5a3f9257b77fdc8d8

INSERT INTO usuarios (nombre, email, password, rol)
VALUES
    ('Marcos Morales', 'cliente@banco.com', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'cliente'),
    ('Franco Schimizzi', 'empleado@banco.com', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'empleado'),
    ('Lucia Perez', 'cliente2@banco.com', 'db2e7f1bd5ab9968ae76199b7cc74795ca7404d5a08d78567715ce532f9d2669', 'cliente'),
    ('Diego Rios', 'empleado2@banco.com', '1c142b2d01aa34e9a36bde480645a57fd69e14155dacfab5a3f9257b77fdc8d8', 'empleado');

INSERT INTO cuentas (id_usuario, saldo)
VALUES
    (1, 15000.00),
    (2, 0.00),
    (3, 20000.00),
    (4, 0.00);

-- Para rehacer tablas
DROP TABLE IF EXISTS transacciones;
DROP TABLE IF EXISTS cuentas;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS cajero;