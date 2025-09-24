---
name: Pull Request para Android
about: Plantilla para solicitudes de extracción de código en proyectos Android.
title: "[Tipo]: Breve descripción del cambio"
labels: ["pending-review"]
assignees: []
---

## Descripción General

Por favor, describe los cambios que introduces en este Pull Request de manera clara y concisa. ¿Cuál es el problema que resuelve o la funcionalidad que añade?

## Tipo de Cambio

Marca con una `x` todas las que apliquen:

- [ ] Bug fix (corrección de un error no breaking change)
- [ ] New feature (nueva funcionalidad no breaking change)
- [ ] Breaking change (cambio que rompe la compatibilidad con versiones anteriores)
- [ ] Refactor (cambio en la estructura del código sin cambiar su comportamiento)
- [ ] Documentation update (cambios solo en la documentación)
- [ ] Configuration change (actualización de dependencias, Gradle, etc.)
- [ ] Test (adición o modificación de pruebas)
- [ ] UI/UX (cambios visuales o de interacción con el usuario)
- [ ] Performance (mejoras de rendimiento)

## ¿Cuál es el problema que se resuelve? (Si aplica)

* [Enlace al issue o tarea del Jira/Trello/Asana/GitHub](https://github.com/tu_organizacion/tu_repositorio/issues/XXX)
* Describe brevemente el problema que este PR intenta resolver y por qué era necesario.

## Cambios Introducidos

Enumera los cambios clave introducidos en este PR:

-   Cambio 1: Descripción de lo que hace (ej. "Implementa la lógica de inicio de sesión en `LoginViewModel`.")
-   Cambio 2: Descripción de lo que hace (ej. "Añade el composable `LoginScreen` y lo integra con el `ViewModel`.")
-   Cambio 3: Descripción de lo que hace (ej. "Actualiza la versión de la dependencia de Compose Material3 a 1.2.0-beta01.")

## ¿Cómo fue probado?

Describe los pasos que seguiste para probar tus cambios. Incluye:

1.  **Entorno de Prueba:**
    * Dispositivo/Emulador: [Ej. Pixel 5 API 33]
    * Versión de Android: [Ej. Android 13]
2.  **Pasos de Reproducción/Verificación:**
    * **Paso 1:** Navegar a [Pantalla/Feature].
    * **Paso 2:** Realizar [Acción].
    * **Paso 3:** Observar [Resultado esperado].
    * **Paso 4 (para bug fixes):** Verificar que el problema original [Descripción del problema] ya no ocurre.
3.  **Capturas de Pantalla/Videos (si aplica):**
    * [Captura de pantalla o enlace a video que demuestre la funcionalidad o el arreglo]
    * [Captura de pantalla de logs importantes, si aplica]

## Checklist de Revisión (para el autor)

Antes de solicitar la revisión, asegúrate de que todo lo siguiente ha sido completado:

- [ ] Mi código sigue las guías de estilo de código de [Nombre de tu empresa/equipo].
- [ ] He revisado mi propio código y he corregido cualquier error obvio.
- [ ] Mi código tiene comentarios, particularmente en áreas complejas o no obvias.
- [ ] He actualizado la documentación (README, comentarios, etc.) si los cambios lo requieren.
- [ ] Mis cambios no introducen nuevas advertencias o errores en la consola.
- [ ] He añadido pruebas unitarias y/o de integración (si aplica) que cubren mis cambios.
- [ ] Todas las pruebas existentes (unitarias, de instrumentación) pasan con mis cambios.
- [ ] He probado la aplicación en diferentes versiones de Android (si aplica, ej. API 21 y API 33).
- [ ] Los recursos nuevos (strings, drawables, etc.) están debidamente nombrados y localizados si es necesario.
- [ ] Los cambios han sido validados por QA o por el PO (si aplica).

## Posibles Impactos / Riesgos

¿Hay algo que los revisores deban saber sobre los posibles impactos o riesgos de este PR? (ej. impacto en rendimiento, dependencia de un nuevo servicio, posibles efectos secundarios).

- [ ] Sí (describir):
- [ ] No

## Para el Revisor

-   Considera la legibilidad y mantenibilidad del código.
-   Verifica que la funcionalidad propuesta sea correcta.
-   Asegúrate de que no haya regresiones.
-   Comprueba la adherencia a las convenciones de Android y de tu equipo.

---