CREATE TABLE provincia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    codigo VARCHAR(10) NOT NULL,
    descripcion VARCHAR(255) -- New field
);