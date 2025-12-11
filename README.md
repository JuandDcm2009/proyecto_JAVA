# 📌 Sistema de Gestión de Préstamos – CrediYa S.A.S.

En este proyecto desarrollamos un **sistema de consola en Java** para la empresa **CrediYa S.A.S.**, dedicada a otorgar créditos personales.  
El objetivo es **digitalizar el control de préstamos, empleados, clientes y pagos**, reemplazando el manejo actual en hojas de cálculo por un sistema modular con persistencia en archivos y base de datos **MySQL**.

---

## 🎯 Objetivo General

Desarrollar un sistema modular en Java que permita gestionar préstamos y cobros aplicando:

- **Programación Orientada a Objetos (POO)**: herencia, polimorfismo y encapsulamiento.
- **Manejo de colecciones, archivos y persistencia.**
- **Conexión e interacción con MySQL mediante JDBC.**
- **Buenas prácticas de diseño:** SOLID, patrones de diseño y correcto manejo de excepciones.

---

## 🧩 Módulos del Sistema

### 🔹 1. Módulo de Empleados
Permite:
- Registrar empleados.
- Consultar información de empleados.
- Atributos: **id, nombre, documento, rol, correo, salario**.
- Persistencia en **archivos** y **base de datos MySQL**.

---

### 🔹 2. Módulo de Clientes
Permite:
- Registrar clientes.
- Listar todos los clientes.
- Consultar préstamos asociados.
- Atributos: **id, nombre, documento, correo, teléfono**.

---

### 🔹 3. Módulo de Préstamos
Permite:
- Crear un préstamo asociando cliente y empleado.
- Cálculo automático de:
  - **Monto total** a pagar con interés.
  - **Valor de cuota mensual**.
- Cambiar el estado del préstamo (*pendiente / pagado*).
- Persistencia en **archivo y base de datos**.

---

### 🔹 4. Módulo de Pagos
Permite:
- Registrar pagos o abonos de un préstamo.
- Actualizar el saldo pendiente.
- Mostrar historial completo de pagos.

---

### 🔹 5. Módulo de Reportes
Incluye consultas como:
- Préstamos activos.
- Préstamos vencidos.
- Clientes morosos.
- Filtrado avanzado usando **expresiones Lambda y Stream API**.

---

## 🚀 Funcionalidades Clave

- Gestión interactiva por consola.
- Persistencia híbrida: **archivos + MySQL**.
- Cálculo automático de intereses y cuotas.
- Seguimiento completo de pagos.
- Reportes dinámicos usando **Stream API**.
- Arquitectura modular, escalable y mantenible.

---

## 🛠️ Tecnologías y Herramientas

- **Java**  
- **Paradigma POO**  
- **JDBC**  
- **MySQL**  
- **Colecciones Java (List, Map, etc.)**  
- **Manejo de Archivos (I/O)**  
- **Lambdas y Stream API**

---