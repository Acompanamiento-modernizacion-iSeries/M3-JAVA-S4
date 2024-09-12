# M3-JAVA-S4

# Taller: Sistema Bancario con Múltiples Cuentas
> **Larry M. Ramírez - Coach Técnico**
## Objetivo.
Los asistentes deben desarrollar un sistema bancario en Java que maneje entity corrientes y de ahorros, aplicando los conceptos de OOP aprendidos en la sesión.
## Instrucciones.
- Implementar la clase base `entity.Cuenta` con los métodos básicos para depositar y retirar dinero, así como para consultar el saldo.
- Crear subclases `entity.CuentaCorriente` y `entity.CuentaAhorros`, añadiendo funcionalidad específica para cada tipo de cuenta (sobregiro y aplicación de interés). 
- Debe utilizar herencia para diferenciar entre `entity.CuentaCorriente` y `entity.CuentaAhorros`.
- Deben implementarse métodos polimórficos para las operaciones comunes, con lógica específica según el tipo de cuenta.
- Crear una clase Banco que gestione varias entity, permitiendo agregar entity, depositar, retirar y consultar saldos.
- Implementar un menú interactivo en la clase `Banco` que permita al usuario realizar operaciones con las entity.

**Nota:** Los talleres deben entregarse por medio de la estrategia establecida para la formación, por medio de un Pull Request desde el repositorio Fork hacia la rama main del repositorio del taller. 


## Solucion
1. Se crea la clase `entity.Cuenta` con los métodos básicos para depositar y retirar dinero, así como para consultar el saldo.
2. Se crean las subclases `entity.CuentaCorriente` y `entity.CuentaAhorros`, añadiendo funcionalidad específica para cada tipo de cuenta (sobregiro y aplicación de interés).
3. Se utiliza herencia para diferenciar entre `entity.CuentaCorriente` y `entity.CuentaAhorros`.
4. Se implementan métodos polimórficos para las operaciones comunes, con lógica específica según el tipo de cuenta.
5. Se crea una clase Banco que gestione varias entity, permitiendo agregar entity, depositar, retirar y consultar saldos.
6. Se implementa un menú interactivo en la clase `Banco` que permita al usuario realizar operaciones con las entity.
7. Se crea la clase `Main` para ejecutar el programa.
8. Se crea la clase `Test` para realizar pruebas unitarias.