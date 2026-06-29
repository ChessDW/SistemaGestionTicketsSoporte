# 🎫 Support Ticket Management System

Un sistema robusto de consola para la gestión y administración de tickets de soporte técnico de TI. Este proyecto fue diseñado aplicando buenas prácticas de desarrollo en **Java**, con un fuerte enfoque en la Programación Orientada a Objetos (POO) y los principios **SOLID**.

---

## 🚀 Características Principales

- **Registro de Usuarios:** Permite registrar empleados y técnicos especializados.
- **Ciclo de Vida de Tickets:** Creación, asignación de técnicos, adición de etiquetas únicas y control estricto de estados.
- **Validaciones de Negocio:** Prevención de modificaciones en tickets cerrados y control de datos duplicados en etiquetas mediante colecciones eficientes.
- **Búsqueda Avanzada:** Filtrado y búsqueda de tickets por ID, título, descripción o estado.
- **Interfaz Robusta por Consola:** Control de excepciones personalizado y validación genérica de entradas de usuario para evitar caídas del sistema.

---

## 🛠️ Tecnologías y Conceptos Aplicados

- **Lenguaje:** Java 17+
- **Paradigma:** Programación Orientada a Objetos (Abstracción, Encapsulamiento, Herencia, Polimorfismo).
- **Principios SOLID:**
  - **S**ingle Responsibility Principle (Clases con responsabilidades únicas).
  - **L**iskov Substitution Principle (Uso de `Tecnico` como extensión natural de `Empleado`).
  - **D**ependency Inversion Principle (Uso de interfaces para desacoplar la lógica).
- **Estructuras de Datos:** - `ArrayList` para el manejo ordenado de registros globales.
  - `HashSet` para garantizar la unicidad de las etiquetas en cada ticket.
- **Robustez:** Creación de excepciones personalizadas (`EmptyListException`, `ElementNotFoundException`) y uso de Genéricos (`Generics`) para utilitarios de consola.

---

## 📁 Estructura del Proyecto

El proyecto está organizado siguiendo una arquitectura limpia basada en capas:

```text
src/
└── com/
    └── soporte/
        ├── Main.java                # Punto de entrada y gestión del menú interactivo
        ├── model/                   # Entidades puras del dominio (Ticket, Empleado, Tecnico)
        ├── enums/                   # Definición de Estados y Prioridades
        ├── service/                 # Abstracción e implementación de la lógica de negocio (Interfaces)
        ├── exceptions/              # Excepciones personalizadas del sistema
        └── utils/                   # Herramientas de formateo y validación de entradas

```
## 💻 Cómo Ejecutar el Proyecto
 1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/ChessDW/SistemaGestionTicketsSoporte.git](https://github.com/ChessDW/SistemaGestionTicketsSoporte.git)
   
   ```
 2. **Navegar al directorio:**
   ```bash
   cd nombre-del-repositorio/src
   
   ```
 3. **Compilar los archivos:**
   ```bash
   javac com/soporte/Main.java
   
   ```
 4. **Ejecutar la aplicación:**
   ```bash
   java com.soporte.Main
   
   ```
## 🧠 Aprendizajes Clave
Este proyecto me permitió consolidar habilidades críticas para el desarrollo de software profesional, tales como el modelado de relaciones entre objetos, la separación estricta de responsabilidades entre la vista (consola) y la lógica de negocio, y la implementación de flujos seguros mediante el manejo avanzado de excepciones.