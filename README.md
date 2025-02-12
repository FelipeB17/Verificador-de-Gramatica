# Verificador de Gramática

**Autores:**

- Omar Alexis Palencia Claro (1152270)
- Andrés Felipe Beltrán Assaf (1152262)

## Descripción

Este proyecto consiste en un verificador de gramática implementado en Java como parte de la materia de Teoría de la Computación. Su propósito es analizar la pertenencia de una palabra a una gramática formal utilizando la técnica de análisis sintáctico "shift-reduce parsing". Este método, basado en un enfoque de análisis ascendente (bottom-up parsing), permite reconocer estructuras gramaticales de manera eficiente.

## Tecnologías Utilizadas

- **Lenguaje de Programación:** Java
- **Entorno de Desarrollo:** Apache NetBeans
- **Estructuras de Datos:**
  - `ArrayList` y `HashMap` en la clase `Gramatica` para gestionar reglas y símbolos.
  - `HashMap` en la clase `Parser` para el almacenamiento de reglas de producción.

## Instalación y Ejecución

### Requisitos Previos

Antes de ejecutar el programa, es necesario instalar la versión 21 del JDK. Puede descargarla desde el siguiente enlace:
[Descargar JDK 21](https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.exe)

### Procedimiento de Ejecución

1. **Definición de la Gramática:**
   - En la primera ventana de entrada, se deben introducir los terminales, no terminales y el símbolo inicial.
   - Estos elementos deben estar separados por comas y agrupados entre paréntesis.
   - Ejemplo de formato: `G = (Terminales), (No-terminales), (Inicial)`

2. **Ingreso de Reglas de Producción:**
   - Las reglas deben ingresarse en la segunda ventana de la siguiente manera:
     ```
     Pn = Formacion,Formacion;
     ```
   - Cada elemento en el lado derecho de la producción se separa por comas.
   - Cada regla de producción se separa por un punto y coma.

   **Restricciones:**
   - No se permite que una producción se refiera a sí misma como única formación.
   - Dos o más reglas de producción no pueden compartir la misma formación en el lado derecho.

3. **Verificación de Gramática:**
   - Antes de evaluar una palabra, es necesario presionar el botón "Revisar Gramática".

4. **Evaluación de Palabra:**
   - Se debe ingresar la palabra a verificar y hacer clic en "Revisar palabra".
   - Se generará un mensaje indicando si la palabra pertenece a la gramática definida.

## Licencia

Este proyecto está licenciado bajo MIT, lo que permite su modificación y distribución con la debida atribución a los autores.

## Contacto

Para consultas adicionales, puede comunicarse con los autores a través del siguiente correo institucional:

andresfelipeba@ufps.edu.co

