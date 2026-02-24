# BancoApp - Sistema de Gestión Bancaria (Java Swing + Maven)

BancoApp:
Aplicación de escritorio desarrollada en Java,
simula un sistema de operaciones bancarias.
Permite gestionar cuentas, realizar transferencias, visualizar historial, administrar clientes y empleados, entre otras funciones.

## Características principales

- Login seguro con hash de contraseñas (BCrypt)
- Diferenciación de roles: cliente y empleado
- Creación de clientes desde el panel de empleado
- Transferencias entre cuentas con validaciones
- Historial de transacciones
- UI moderna y centrada con **FlatMacDarkLaf**
- Manejo de sesiones por usuario autenticado
- Organización por capas: `modelo`, `dao`, `ui`, etc.

## Tecnologías usadas

- **Java 17+**
- **Maven** para gestión de dependencias
- **Swing** para la interfaz gráfica
- **FlatLaf** (`com.formdev:flatlaf`) para el diseño visual
- **JDBC** para conexión con base de datos
- **MySQL** como base de datos
- **BCrypt** para el cifrado de contraseñas

## Estructura del proyecto

BancoApp/
│
├── src/
│   ├── dao/               # Acceso a datos (JDBC)
│   ├── modelo/            # Clases del dominio (Usuario, Cuenta, etc.)
│   ├── ui/                # Interfaz gráfica (LoginFrame, MenuClienteFrame, etc.)
│   └── utils/             # Clases de utilidad (hashing, validaciones, etc.)
│
├── resources/             # Archivos de configuración
├── pom.xml                # Configuración de Maven y dependencias

## Usuarios de prueba

Cliente:
- Email: cliente@mail.com
- Contraseña: 1234

Empleado:
- Email: empleado@mail.com
- Contraseña: admin

Cliente 2:
- Email: cliente2@banco.com
- Contraseña: 4567

Empleado 2:
- Email: empleado2@banco.com
- Contraseña: admin2

## Funciones futuras (pendientes/mejoras)

- Cambiar contraseña (en desarrollo)
- Sistema de auditoría/logs
- Soporte multicuenta por cliente
- Persistencia con Hibernate (posible mejora futura)

## Dev
Franco Martín Schimizzi