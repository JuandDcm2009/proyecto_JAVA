
CREATE TABLE roles (
    id INT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE continentes (
    id INT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE paises(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    codigo VARCHAR(25) NOT NULL,
    continente_id INT NOT NULL,
    FOREIGN KEY(continente_id) REFERENCES continentes(id)
);

CREATE TABLE telefonos_clientes(
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(20) UNIQUE NOT NULL,
    pais_id INT NOT NULL,
    FOREIGN KEY(pais_id) REFERENCES paises(id)
);

CREATE TABLE empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    documento_numero VARCHAR(20) NOT NULL UNIQUE,
    documento_tipo ENUM('CC', 'CE') NOT NULL,
    rol_id INT NOT NULL,
    correo VARCHAR(80) UNIQUE NOT NULL,
    salario DECIMAL(15,2) NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    documento_numero VARCHAR(20) NOT NULL UNIQUE,
    documento_tipo ENUM('CC', 'CE') NOT NULL,
    correo VARCHAR(80) UNIQUE NOT NULL,
    telefono_id INT NOT NULL,
    FOREIGN KEY (telefono_id) REFERENCES telefonos_clientes(id)
);

CREATE TABLE prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    empleado_id INT NOT NULL,
    monto DECIMAL(12,2) NOT NULL,
    interes DECIMAL(5,2) NOT NULL,
    cuotas INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    estado ENUM('pendiente', 'pagado') NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (empleado_id) REFERENCES empleados(id)

);

CREATE TABLE pagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prestamo_id INT NOT NULL,
    fecha_pago DATE NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (prestamo_id) REFERENCES prestamos(id)
);

CREATE TABLE prestamos_dos (

  id INT AUTO_INCREMENT PRIMARY KEY,
  cliente_id INT,
  monto DECIMAL(10,2),
  interes_mensual DECIMAL(5,2),
  plazo_meses INT,
  estado VARCHAR(20),
  FOREIGN KEY (cliente_id) REFERENCES clientes(id)

);