# Proyecto 1. Gestión colaborativa de proyectos de desarrollo con GitHub

- **Duración:** 16 horas
- **Organización:** equipos de entre 3 y 5 personas (recomendado: 4 personas)
- **Número total de tareas:** 16 tareas pequeñas (distribución orientativa de 4 tareas por persona)
- **Fecha límite:** antes de la evaluación
- **Presentaciones:** no se realizarán presentaciones orales
- **Metodología:** aprendizaje práctico y guiado mediante un proyecto simulado
- **Herramientas principales:** GitHub (repositorios, ramas, *pull requests*, incidencias/*issues* y tableros Kanban)
- **Temática del proyecto:** tienda en línea (*e-commerce*)

> [!IMPORTANT]
> **Guía docente del repositorio (para el profesorado):**
> Este documento constituye la **guía didáctica y de planificación para el profesorado**. El repositorio se estructura en dos directorios principales:
> * 📁 **[`projecte/`](projecte/) — Proyecto completo de referencia (solucionario):** contiene la versión final íntegra y funcional de la aplicación (`Main.java`, `dades_botiga.txt`) junto con toda la documentación técnica terminada (`README.md`, `CONTRIBUTING.md`, `CHANGELOG.md` y `docs/`). Representa el resultado exacto al que deben llegar los equipos de alumnos al finalizar la actividad.
> * 📁 **[`enunciat/`](enunciat/) — Material y enunciados para el alumnado (en catalán):** contiene la guía general para el estudiante ([`enunciat/README.md`](enunciat/README.md)), el esqueleto inicial de partida ([`enunciat/etapa0-inicial/`](enunciat/etapa0-inicial/)) y las instrucciones detalladas de las **4 etapas progresivas** ([`etapa1/`](enunciat/etapa1/), [`etapa2/`](enunciat/etapa2/), [`etapa3/`](enunciat/etapa3/) y [`etapa4/`](enunciat/etapa4/)) con las tareas que debe realizar cada grupo.

---

# 1. Finalidad y conceptos básicos

El objetivo de este proyecto es que el alumnado aprenda a utilizar GitHub para gestionar el desarrollo colaborativo de un proyecto informático, trabajando de forma práctica con repositorios, ramas (*branches*), solicitudes de incorporación (*pull requests*), incidencias (*issues*) y tableros Kanban.

El producto que se utilizará durante la actividad será proporcionado por el profesorado. El alumnado no tendrá que diseñar el producto desde cero, definir sus requisitos arquitectónicos ni programar toda la aplicación desde el inicio, ya que se encuentra todavía en una fase inicial de aprendizaje de programación.

El aprendizaje se centrará en **cómo se organiza y gestiona un proyecto de desarrollo en equipo**:

* organizar el trabajo mediante tableros Kanban;
* gestionar incidencias y tareas;
* trabajar colaborativamente con un repositorio central;
* utilizar ramas para aislar cambios;
* realizar *commits* coherentes y bien documentados;
* sincronizar cambios mediante *push* y *pull*;
* crear, comentar y revisar *Pull Requests*;
* resolver conflictos de integración;
* gestionar documentación técnica versionada (Markdown y Mermaid);
* realizar seguimiento continuo del estado del proyecto;
* mantener la trazabilidad completa del trabajo;
* preparar versiones (*releases*) del producto.

### Conceptos básicos trabajados

Durante el desarrollo del proyecto se ejercitarán especialmente las siguientes funcionalidades:

1. **Creación de repositorios y proyectos**, y compartición con colaboradores.
2. **Gestión de *commits*, *push* y *pull*** para el control de versiones local y remoto.
3. **Creación y gestión de ramas (*branches*)** e integración ordenada de cambios (*merge*).
4. **Creación y revisión de *pull requests*** entre miembros del equipo.
5. **Creación, asignación, seguimiento y resolución de incidencias (*issues*)**.
6. **Gestión del tablero Kanban (GitHub Projects)** para organizar y visualizar el flujo de trabajo.

El proyecto funcionará como una **simulación realista de un equipo de desarrollo profesional**.

---

# 2. Organización del trabajo

- **Tamaño de los grupos:** equipos de entre 3 y 5 personas (se recomienda formar grupos de 4 personas).
- **Número total de tareas:** 16 tareas pequeñas, distribuidas en 4 etapas progresivas, con una asignación orientativa de 4 tareas por persona.
- **Fecha límite:** entrega completada antes de la evaluación.
- **Presentaciones:** no se realizarán presentaciones orales. Toda la evaluación se basará en las evidencias observables registradas en GitHub.

Todos los miembros del equipo deberán experimentar los distintos procedimientos y herramientas. Los roles podrán utilizarse de forma transitoria para organizar el trabajo, pero no serán permanentes: el objetivo fundamental es que todos aprendan a realizar las diferentes tareas (autoría, revisión, documentación, gestión de incidencias e integración).

### Responsabilidad del profesorado

El profesorado proporcionará:

* el producto base sobre el que se trabajará (tienda en línea);
* el código fuente inicial (completo en unas partes, incompleto o a modificar en otras);
* los requisitos y especificaciones funcionales;
* los diseños y bocetos iniciales;
* los diagramas de referencia;
* la información necesaria para elaborar la documentación técnica;
* el catálogo de tareas a desarrollar;
* el orden y planificación de las tareas en cuatro etapas progresivas;
* los criterios de aceptación para dar por terminada cada tarea (*Definition of Done*).

### Responsabilidad del alumnado

El alumnado será responsable de **gestionar, implementar y materializar ese trabajo dentro de GitHub**, asegurando el seguimiento del flujo colaborativo, la calidad de las revisiones y la coherencia del repositorio final.

---

# 3. Temática del proyecto: Tienda en línea (*e-commerce*)

El proyecto informático consistirá en el desarrollo colaborativo de una **tienda en línea (*e-commerce*)**.

Las funcionalidades principales que componen el sistema son:

1. **Gestión de clientes:** registro, consulta y administración de los datos de los usuarios y clientes.
2. **Gestión de productos y de stock:** administración del catálogo de artículos, categorías y control de existencias.
3. **Gestión de ventas:** registro de ventas directas vinculando clientes y productos con emisión de tiques y estadísticas de facturación (`Math`).
4. **Persistencia de datos en archivo:** guardado y recuperación de la información en archivo de texto plano (`dades_botiga.txt`) mediante `Files.writeString` y `Files.readString`.
5. **Interfaz de terminal enriquecida con colores ANSI:** uso de códigos de escape ANSI (`\u001B[...]`) para formatear la salida por consola con colores informativos.

---

# 4. Documentación técnica y estructura del repositorio

El resultado final será un repositorio de GitHub que contenga tanto el producto software como todos los artefactos técnicos asociados al proyecto.

### Documentación técnica mínima requerida

El proyecto deberá incluir la documentación técnica necesaria para facilitar su comprensión, mantenimiento y colaboración. Como mínimo, contendrá:

1. **Descripción del proyecto:** explicación clara del objetivo, el alcance y las funcionalidades principales de la tienda en línea (disponible en [`projecte/README.md`](projecte/README.md)).
2. **Diagramas:** representaciones gráficas estandarizadas (mediante Mermaid) que ayuden a comprender la arquitectura y el funcionamiento del sistema ([`projecte/docs/architecture/diagrams.md`](projecte/docs/architecture/diagrams.md)).
3. **Diseño de la base de datos:** estructura de las estructuras de datos, incluyendo entidades, atributos, claves y formato de archivo plano ([`projecte/docs/architecture/data-model.md`](projecte/docs/architecture/data-model.md)).
4. **Casos de uso:** especificación detallada de las interacciones de los usuarios y administradores con el sistema ([`projecte/docs/user-guide.md`](projecte/docs/user-guide.md)).
5. **Guía de contribución (`CONTRIBUTING.md`):** instrucciones precisas para colaborar en el repositorio, convenciones de ramas, formato de mensajes de *commit*, flujo de trabajo con *Pull Requests* y criterios de revisión ([`projecte/CONTRIBUTING.md`](projecte/CONTRIBUTING.md)).
6. **Tecnologías utilizadas:** relación descriptiva de las tecnologías, características del lenguaje (Java 25, clases implícitas, ANSI codes) y justificación didáctica ([`projecte/docs/architecture/overview.md`](projecte/docs/architecture/overview.md)).

### Estructura orientativa del repositorio

```text
projecte/
├── Main.java                 # Código fuente único de la aplicación (clase implícita Java 25)
├── dades_botiga.txt          # Archivo de persistencia de datos (formato plano ';')
├── README.md                 # Visión general del proyecto, instalación y guía de inicio (Elem. 1)
├── CONTRIBUTING.md           # Guía de contribución y flujo de trabajo colaborativo (Elem. 5)
├── CHANGELOG.md              # Historial de versiones y notas de release
│
└── docs/                     # Documentación técnica organizada por audiencias
    ├── README.md             # Índice y mapa general de documentación técnica
    ├── user-guide.md         # Casos de uso e interacciones del usuario (Elem. 4)
    ├── installation.md       # Requisitos y puesta en marcha con Java 25
    ├── deployment.md         # Despliegue, persistencia y copias de seguridad
    ├── troubleshooting.md    # Resolución de incidencias comunes
    ├── architecture/         # Arquitectura del sistema y modelos
    │   ├── overview.md       # Tecnologías, diseño modular y restricciones (Elem. 6)
    │   ├── data-model.md     # Estructuras de datos paralelas y archivo (Elem. 3)
    │   └── diagrams.md       # Diagramas Mermaid: arquitectura, ER, flujo y estados (Elem. 2)
    ├── api/                  # Especificación de módulos internos y helpers UI
    │   ├── README.md         # Convenciones de API interna
    │   ├── ui.md             # Helpers de interfaz de terminal y colores ANSI
    │   ├── customers.md      # Gestión de clientes
    │   ├── products.md       # Catálogo de productos y stock
    │   ├── sales.md          # Registro de ventas y facturación
    │   └── data.md           # Persistencia y gestión de arrays
    └── development/          # Guías para desarrolladores
        ├── environment.md    # Configuración del entorno de desarrollo
        ├── workflow.md       # Flujo de trabajo Git, ramas y revisiones PR
        └── testing.md        # Plan de pruebas manuales y scripts automatizados
```

La documentación será proporcionada por el profesorado en cuanto a su contenido conceptual y técnico. El alumnado deberá incorporarla y mantenerla en el repositorio utilizando los formatos y herramientas establecidos:

* **Markdown** para texto enriquecido estructurado;
* **Mermaid** para diagramas reproducibles y versionados en texto;
* organización clara de directorios y enlaces relativos entre documentos;
* gestión bajo control de versiones exactamente igual que el código fuente.

---

# 5. Objetivos de aprendizaje

Al finalizar la actividad, el alumnado deberá ser capaz de:

### Repositorio
* comprender la estructura interna de un repositorio Git;
* clonar un repositorio remoto y mantenerlo sincronizado;
* trabajar con repositorios remotos en GitHub;
* identificar las diferentes partes de un proyecto de software.

### Gestión de tareas y tablero Kanban
* comprender el concepto y ciclo de vida de una *Issue*;
* interpretar las tareas asignadas y sus criterios de aceptación;
* asignar responsables y colaboradores a cada tarea;
* utilizar etiquetas (*labels*) temáticas y de prioridad;
* trabajar con hitos (*milestones*);
* organizar y mover tarjetas en el tablero Kanban (GitHub Projects) reflejando el estado real del trabajo.

### Control de versiones
* crear y publicar ramas (*branches*);
* cambiar entre ramas de trabajo de forma segura;
* realizar *commits* atómicos y coherentes;
* redactar mensajes de *commit* comprensibles siguiendo convenciones;
* subir cambios al repositorio remoto (*push*);
* actualizar el repositorio local incorporando cambios del equipo (*pull*/*fetch*);
* consultar e interpretar el historial del proyecto.

### Trabajo colaborativo
* trabajar simultáneamente con otros miembros del equipo sin interferencias destructivas;
* evitar trabajar y hacer cambios directamente sobre la rama principal (`main`);
* integrar cambios exclusivamente mediante *Pull Requests*;
* revisar minuciosamente el código y la documentación de los compañeros;
* realizar comentarios constructivos en las revisiones;
* solicitar modificaciones o correcciones antes de autorizar una integración;
* aprobar e integrar (*merge*) *Pull Requests*.

### Conflictos de integración
* comprender por qué aparecen conflictos en Git al trabajar en paralelo;
* identificar e interpretar las marcas de conflicto;
* resolver conflictos de forma consensuada sin pérdida accidental de código;
* verificar la validez del resultado tras la resolución;
* completar satisfactoriamente la integración.

### Documentación técnica
* crear y formatear documentación técnica en Markdown;
* estructurar directorios de documentación (`docs/`);
* modelar diagramas de arquitectura, datos y flujo con Mermaid;
* mantener la documentación bajo el mismo flujo de control de versiones y revisión que el código.

### Gestión del proyecto y trazabilidad
* utilizar GitHub Projects como herramienta de visualización y control de flujo;
* mantener la trazabilidad completa en la cadena: `Tarea → Issue → Rama → Commit → Pull Request → Revisión → Merge → Release`;
* comprender la relación directa entre planificación, ejecución técnica y estado del proyecto.

### Entrega y versionado
* preparar una versión consolidada del proyecto;
* generar etiquetas de versión (*tags*) y publicaciones formales (*Releases*);
* verificar que el repositorio queda en un estado limpio, coherente y funcional.

---

# 6. Flujo de trabajo colaborativo

Durante el proyecto se utilizará de forma rigurosa y progresiva el siguiente flujo de trabajo:

```text
             ┌─────────────────────────┐
             │     Issue en Kanban     │
             │        (Backlog)        │
             └────────────┬────────────┘
                          │ Asignar a miembro
                          ▼
             ┌─────────────────────────┐
             │     Tarea asignada      │
             │      (In Progress)      │
             └────────────┬────────────┘
                          │ Crear rama de trabajo
                          ▼
             ┌─────────────────────────┐
             │     Branch temática     │
             │ (feature/..., docs/...) │
             └────────────┬────────────┘
                          │ Modificaciones
                          ▼
             ┌─────────────────────────┐
             │    Cambios + Commits    │
             │   atómicos y legibles   │
             └────────────┬────────────┘
                          │ Sincronizar
                          ▼
             ┌─────────────────────────┐
             │       Push remoto       │
             └────────────┬────────────┘
                          │ Abrir solicitud
                          ▼
             ┌─────────────────────────┐
             │      Pull Request       │
             │      (In Review)        │
             └────────────┬────────────┘
                          │ Revisión cruzada
                          ▼
             ┌─────────────────────────┐
             │   Revisión por pares    │
             └────────────┬────────────┘
                          │
                   ┌──────┴──────┐
                   │             │
              ¿Requiere     Aprobado
               cambios?          │
                   │             │
             Nuevos commits      │
                   │             │
                   └──────►──────┘
                                 │
                                 ▼
                          ┌─────────────┐
                          │    Merge    │
                          │   a main    │
                          └──────┬──────┘
                                 │ Cerrar Issue
                                 ▼
                          ┌─────────────┐
                          │    Done     │
                          └─────────────┘
```

Este flujo constituye el hilo conductor de toda la actividad. No se busca memorizar comandos aislados, sino comprender **por qué existe cada mecanismo y cómo garantiza la calidad y trazabilidad del proyecto**.

---

# 7. Etapas del proyecto y distribución del trabajo

El proyecto consta de **16 tareas pequeñas** organizadas en **cuatro etapas progresivas** con nivel de dificultad creciente, desarrolladas a lo largo de **16 horas** lectivas (distribuidas en 8 sesiones de 2 horas).

---

## 7.1. Las cuatro etapas del proyecto

```text
┌────────────────────────────────────────────────────────────────────────────┐
│                        PROGRESIÓN DEL PROYECTO                             │
├───────────────────┬───────────────────┬──────────────────┬─────────────────┤
│  Etapa 1 (T1-T4)  │  Etapa 2 (T5-T8)  │ Etapa 3 (T9-T12) │ Etapa 4 (13-16) │
│   Nivel Básico    │ Nivel Intermedio  │  Descomposición  │   Autonomía y   │
│  Familiarización  │ Código incompleto │  de subtareas    │   Complejidad   │
└───────────────────┴───────────────────┴──────────────────┴─────────────────┘
```

### Etapa 1. Tareas 1-4: Nivel básico y familiarización
* **Objetivo:** familiarizarse con el funcionamiento de GitHub, la dinámica del tablero Kanban y el flujo de trabajo colaborativo básico.
* **Metodología:** cada grupo selecciona una tarea del tablero, la gestiona en Kanban, la implementa en una rama propia y completa el ciclo colaborativo abriendo y revisando una *Pull Request*.
* **Tipología de tareas:**
  * copiar un fragmento de código proporcionado, pegarlo en la ubicación adecuada y modificarlo mínimamente;
  * generar documentación técnica inicial en Markdown;
  * elaborar diagramas técnicos a partir de imágenes o bocetos iniciales proporcionados por el profesorado, reproduciéndolos con Mermaid de forma clara, ordenada y siguiendo estándares.

### Etapa 2. Tareas 5-8: Nivel intermedio y completado de código
* **Objetivo:** afianzar el procedimiento de trabajo aumentando el nivel de exigencia técnica y afrontando las primeras situaciones de integración simultánea.
* **Metodología:** se repite el procedimiento de trabajo de la etapa anterior (Issue → Rama → Commit → PR → Review → Merge), pero las tareas exigen mayores conocimientos informáticos.
* **Tipología de tareas:**
  * código fuente con partes incompletas (métodos vacíos, controladores o plantillas a medio desarrollar) que el alumnado deberá completar y verificar;
  * ampliación de la documentación técnica y diseño de datos;
  * resolución de los primeros conflictos de integración generados por modificaciones concurrentes.

### Etapa 3. Tareas 9-12: Descomposición y planificación de tareas
* **Objetivo:** desarrollar la capacidad de análisis y organización del trabajo en equipo.
* **Metodología:** en lugar de recibir tareas atómicas prediseñadas, cada grupo recibe una **tarea de mayor envergadura** que el propio equipo debe subdividir en subtareas más pequeñas, manejables e independientes.
* **Tipología de tareas:**
  * análisis de una funcionalidad completa de la tienda en línea (por ejemplo, el flujo de venta o la persistencia de datos);
  * creación y distribución de las subtareas resultantes en el tablero Kanban;
  * asignación de responsables y seguimiento coordinado del desarrollo mediante ramas y PRs vinculadas.

### Etapa 4. Tareas 13-16: Complejidad avanzada, consolidación y entrega
* **Objetivo:** consolidar la plena autonomía del equipo en la planificación, implementación técnica y gestión colaborativa del repositorio.
* **Metodología:** se mantiene la dinámica de descomposición y desarrollo autónomo sobre tareas con un nivel de complejidad y exigencia técnica superior.
* **Tipología de tareas:**
  * integración de módulos complejos de la tienda en línea (ventas y facturación, catálogo y stock, persistencia);
  * consolidación de la documentación técnica final (casos de uso, guía de contribución, especificación de tecnologías);
  * resolución autónoma de conflictos complejos de integración;
  * empaquetado de la versión final mediante etiquetas (*tags*) y publicación formal de la *Release*.

---

## 7.2. Planificación detallada de las 16 horas lectivas

Las 16 tareas y las 4 etapas se articulan a lo largo de 8 sesiones de 2 horas:

---

### Sesión 1 · Presentación del proyecto y GitHub

**2 horas**

#### Objetivos
Comprender qué es un proyecto de desarrollo, qué papel desempeña GitHub y cómo está organizado el repositorio de la tienda en línea con el que trabajará el equipo.

#### Contenidos
* Qué es un proyecto de desarrollo de software.
* Qué es un repositorio Git y su alojamiento en GitHub.
* Git y GitHub: conceptos básicos y diferencias.
* Organización del equipo y colaboradores.
* Estructura inicial del repositorio de la tienda en línea.
* Rama principal (`main`).
* Introducción a incidencias (*Issues*).

#### Actividades
El profesorado presenta el proyecto de la tienda en línea y el escenario de simulación profesional.

Cada equipo:
1. accede a su repositorio en GitHub;
2. identifica su estructura de carpetas (`docs/`, `src/`);
3. explora el código base proporcionado;
4. consulta la documentación inicial disponible;
5. identifica las primeras *Issues* en el repositorio;
6. reconoce a los miembros del equipo y sus responsabilidades iniciales.

#### Entregables
1. **Repositorio operativo:** el equipo dispone de su repositorio con la estructura inicial proporcionada por el profesorado.
2. **Equipo configurado:** todos los miembros del equipo (entre 3 y 5 personas) aparecen debidamente configurados como colaboradores del repositorio.
3. **README inicial:** el README del proyecto está incorporado y correctamente estructurado en Markdown.
4. **Repositorio explorado:** todos los alumnos han identificado el código, la documentación, la rama principal, las *Issues* y los miembros del equipo.

#### Estado al finalizar
> El equipo dispone de un repositorio funcional y comprende qué representa cada una de sus partes.

---

### Sesión 2 · Issues, tablero Kanban y planificación del trabajo

**2 horas**

#### Objetivos
Aprender a representar y organizar el trabajo del proyecto mediante *Issues* y tableros Kanban en GitHub Projects.

#### Contenidos
* Incidencias (*Issues*) como unidades de trabajo.
* Tareas y criterios de aceptación (*Definition of Done*).
* Asignación de responsables (*Assignees*).
* Etiquetas (*Labels*) temáticas y de prioridad.
* Hitos (*Milestones*).
* Estados del trabajo y visualización ágil.
* Relación entre planificación y ejecución técnica.
* Tablero Kanban en GitHub Projects.

#### Actividades
El alumnado recibe el catálogo de tareas de la **Etapa 1** previamente definidas por el profesorado.

Deberán:
1. localizar las tareas en el repositorio;
2. interpretar su descripción y criterios de finalización;
3. asignar un responsable a cada tarea;
4. aplicar las etiquetas y milestones correspondientes;
5. organizar las tarjetas en el tablero Kanban configurando las columnas:
   ```text
   Backlog | Todo | In Progress | Review | Done
   ```
6. actualizar el estado de las tareas a medida que comience el trabajo;
7. incorporar comentarios en las *Issues* cuando sea necesario registrar información técnica.

#### Entregables
1. **Backlog organizado:** el repositorio contiene las tareas de la Etapa 1 registradas como *Issues* con título, descripción, responsable, etiquetas y milestone.
2. **Tablero Kanban (GitHub Projects):** el equipo dispone de un tablero de trabajo plenamente operativo con las columnas configuradas.
3. **Distribución del trabajo:** las tareas están asignadas a los miembros del equipo de manera equilibrada.
4. **Primera planificación visible:** el equipo puede identificar con claridad qué tareas están pendientes, quién debe realizarlas y en qué estado se encuentran.

#### Estado al finalizar
> El equipo dispone de un backlog estructurado en su tablero Kanban y utiliza GitHub como plataforma de gestión ágil del proyecto.

---

### Sesión 3 · Git: ramas, commits y sincronización (Inicio Etapa 1)

**2 horas**

#### Objetivos
Aprender a realizar cambios de forma independiente y aislada sobre ramas temáticas sin modificar directamente la rama principal, abordando las primeras tareas de la **Etapa 1 (tareas 1 a 4)**.

#### Contenidos
* Repositorio local y repositorio remoto.
* Clonación (`git clone`).
* Creación y cambio de ramas (`git branch`, `git checkout` / `git switch`).
* Registro de cambios (`git commit`).
* Publicación remota (`git push`).
* Actualización local (`git pull` / `git fetch`).
* Inspección del estado y del historial (`git status`, `git log`).

#### Actividades
Cada miembro del equipo toma una tarea sencilla de la **Etapa 1** (tareas 1 a 4: copiar y adaptar un fragmento de código base, crear un archivo de documentación en Markdown o reproducir un diagrama inicial en Mermaid).

El procedimiento riguroso será:
```text
Issue en Kanban
      ↓
Crear Rama temática (feature/..., docs/...)
      ↓
Realizar cambios
      ↓
Crear Commits descriptivos
      ↓
Publicar en remoto (Push)
```

Se prestará especial atención a:
* qué cambios contiene cada *commit* (atomicidad);
* cómo escribir mensajes de *commit* comprensibles y descriptivos;
* por qué nunca debe trabajarse directamente sobre `main`;
* cómo sincronizar y mantener actualizado el repositorio local.

#### Entregables
1. **Una rama por tarea:** cada miembro ha creado y publicado una rama asociada a su tarea (por ejemplo: `feature/1-registro-cliente`, `docs/2-arquitectura`, `feature/3-catalogo-base`, `docs/4-diagrama-clases`).
2. **Cambios realizados:** cada alumno ha implementado la modificación requerida de la Etapa 1.
3. **Commits bien documentados:** los cambios están registrados con mensajes claros.
4. **Ramas publicadas:** todas las ramas están disponibles en el repositorio remoto de GitHub.
5. **Trazabilidad inicial:** puede comprobarse la relación directa `Issue → Rama → Commit`.

#### Estado al finalizar
> Todos los miembros del equipo han trabajado de forma independiente en sus tareas y han publicado sus cambios sin alterar la rama principal.

---

### Sesión 4 · Pull Requests y revisión por pares (Cierre Etapa 1)

**2 horas**

#### Objetivos
Aprender a integrar cambios mediante *Pull Requests* y realizar revisiones cruzadas de código y documentación, completando el ciclo de las **tareas 1 a 4 (Etapa 1)**.

#### Contenidos
* Concepto y ciclo de vida de una *Pull Request* (PR).
* Comparación de cambios y visualización de diferencias (*diff*).
* Revisión por pares (*Peer Review*).
* Comentarios en línea e hilos de discusión.
* Aprobación (*Approve*) y solicitud de cambios (*Request Changes*).
* Integración (*Merge*) y eliminación de ramas integradas.
* Cierre automático y manual de *Issues*.

#### Actividades
Cada alumno abre una *Pull Request* para su tarea de la Etapa 1 vinculándola a su respectiva *Issue*.

Otro miembro del equipo realiza obligatoriamente la revisión cruzada:
* inspección de los cambios introducidos;
* comentarios y sugerencias de mejora;
* aprobación formal o solicitud fundamentada de modificaciones.

Una vez aprobada, se ejecuta el *merge* a la rama principal y se pasa la tarjeta a `Done` en el tablero Kanban.

Se consolidará la cadena completa:
```text
Issue → Branch → Commit → Pull Request → Review → Merge → Issue cerrada
```

#### Entregables
1. **Pull Requests creadas:** una PR por cada una de las tareas 1 a 4 de la Etapa 1.
2. **Revisión cruzada efectiva:** ningún alumno revisa o aprueba su propia PR; todas cuentan con revisión de un compañero.
3. **Comentarios de revisión:** revisiones con observaciones técnicas pertinentes.
4. **Aprobación o cambios:** evidencia explícita de aprobación o corrección de cambios solicitados.
5. **Merge e integración:** las PRs aprobadas han sido integradas en la rama principal.
6. **Tareas 1-4 completadas:** las *Issues* correspondientes están cerradas y el tablero Kanban actualizado a `Done`.

#### Estado al finalizar
> Cada estudiante ha completado el ciclo completo de autoría, revisión por pares e integración de cambios en el proyecto.

---

### Sesión 5 · Documentación técnica y código incompleto (Inicio Etapa 2)

**2 horas**

#### Objetivos
Abordar las **tareas 5 a 8 (Etapa 2)** con un nivel informático superior, completando fragmentos de código proporcionados y estructurando la documentación técnica mínima del proyecto en `docs/`.

#### Contenidos
* Documentación técnica como artefacto de primer nivel bajo control de versiones.
* Estructura formal de `docs/` (descripción, casos de uso, base de datos, tecnologías).
* Diagramas estandarizados con Mermaid (arquitectura, entidad-relación y flujo).
* Completado de código fuente con partes omitidas o métodos sin implementar.
* Enlaces relativos y coherencia documental.

#### Actividades
El profesorado suministra las tareas de la **Etapa 2 (tareas 5 a 8)**:
* módulos de la tienda en línea con partes incompletas que el alumnado debe completar y probar;
* especificaciones detalladas para generar la documentación técnica mínima (descripción, casos de uso, modelo de datos y tecnologías empleadas).

Los miembros del equipo:
1. registran las tareas 5 a 8 en el tablero Kanban;
2. crean ramas de trabajo específicas;
3. completan el código y redactan los documentos Markdown y diagramas Mermaid correspondientes;
4. abren las *Pull Requests* de la Etapa 2.

#### Entregables
1. **Estructura `docs/` operativa:** repositorio con la jerarquía de documentación técnica requerida.
2. **Documentación Markdown rigurosa:** información técnica y diseño incorporados con claridad.
3. **Diagramas Mermaid funcionales:** diagramas versionados y legibles integrados en los documentos.
4. **Código completado:** módulos de la tienda con las partes incompletas implementadas y probadas.
5. **PRs de la Etapa 2 abiertas:** propuestas de integración publicadas y listas para revisión.

#### Estado al finalizar
> El repositorio incorpora código funcional de nivel intermedio y documentación técnica estructurada bajo control de versiones.

---

### Sesión 6 · Trabajo simultáneo y resolución de conflictos (Cierre Etapa 2)

**2 horas**

#### Objetivos
Comprender las causas de los conflictos en Git provocados por modificaciones simultáneas y aprender a resolverlos de forma rigurosa y consensuada, completando la **Etapa 2 (tareas 5 a 8)**.

#### Contenidos
* Trabajo concurrente en equipos de desarrollo.
* Origen de los conflictos de integración en Git.
* Lectura e interpretación de marcas de conflicto (`<<<<<<<`, `=======`, `>>>>>>>`).
* Estrategias de resolución de conflictos.
* Actualización de ramas locales antes de integrar.
* Verificación de la integridad del software tras la resolución.

#### Actividades
El profesorado plantea situaciones en las que los alumnos trabajan sobre zonas coincidentes del código o de la documentación de la tienda en línea, provocando al menos un conflicto controlado.

Los alumnos:
1. identifican la aparición del conflicto al actualizar su rama o en la Pull Request;
2. localizan exactamente las líneas enfrentadas;
3. discuten y acuerdan la versión correcta que debe prevalecer;
4. resuelven el conflicto manualmente eliminando las marcas;
5. verifican que el código funciona y la documentación mantiene coherencia sintáctica;
6. realizan el *commit* de resolución y completan el *merge* de las tareas 5 a 8.

#### Entregables
1. **Conflicto identificado y analizado:** el equipo reconoce la causa técnica del conflicto.
2. **Conflicto resuelto:** integración limpia sin código residual ni pérdidas involuntarias de funcionalidad.
3. **Verificación técnica:** comprobación de que el proyecto sigue siendo plenamente funcional.
4. **Tareas 5 a 8 integradas:** todas las PRs de la Etapa 2 integradas en `main`, incidencias cerradas y tablero Kanban actualizado.

#### Estado al finalizar
> El equipo ha experimentado y superado con éxito situaciones reales de conflicto de integración manteniendo la estabilidad del proyecto.

---

### Sesión 7 · Descomposición de tareas complejas y gestión global (Etapa 3)

**2 horas**

#### Objetivos
Desarrollar la capacidad de análisis y organización descomponiendo una tarea de mayor envergadura en subtareas manejables (**Etapa 3, tareas 9 a 12**) y supervisar la trazabilidad integral del proyecto.

#### Contenidos
* Descomposición funcional de requisitos complejos.
* Definición de subtareas atómicas e independientes.
* Planificación en el tablero Kanban y asignación de responsabilidades.
* Hitos (*milestones*) y seguimiento de entregas intermedias.
* Trazabilidad completa: `Requisito → Issue → Rama → Commits → PR → Review → Merge`.

#### Actividades
El equipo recibe una especificación de mayor envergadura sobre la tienda en línea (por ejemplo, el módulo integral de registro de ventas y emisión de tiques):
1. el equipo analiza la tarea y la subdivide en 4 subtareas pequeñas e independientes (tareas 9 a 12);
2. se crean las *Issues* en GitHub vinculadas al hito de la Etapa 3;
3. se reparten las subtareas entre los miembros del equipo y se colocan en el tablero Kanban;
4. cada miembro implementa su subtarea en su rama correspondiente;
5. se realiza la revisión cruzada, se resuelven dudas e integran los cambios en `main`;
6. se comprueba la trazabilidad de todo el ciclo.

#### Entregables
1. **Descomposición documentada:** la tarea amplia queda desglosada en las tareas 9 a 12 en el tablero Kanban.
2. **Tareas 9-12 implementadas e integradas:** código y documentación correspondientes integrados mediante PRs revisadas.
3. **Tablero Kanban sincronizado:** tarjetas de la Etapa 3 movidas correctamente a `Done`.
4. **Trazabilidad comprobada:** para las tareas de la etapa, puede reconstruirse de forma inmediata su historial completo.
5. **Milestone de Etapa 3 alcanzado:** cumplimiento del hito planificado.

#### Estado al finalizar
> El equipo es capaz de estructurar autónomamente el trabajo complejo en subtareas coordinadas y ejecutarlas con plena trazabilidad en GitHub.

---

### Sesión 8 · Simulación autónoma completa, versión final y Release (Etapa 4)

**2 horas**

#### Objetivos
Aplicar de forma totalmente autónoma el flujo colaborativo aprendido a tareas de alta complejidad (**Etapa 4, tareas 13 a 16**), verificar el cumplimiento de todos los requisitos del proyecto y publicar la versión formal (*Release*).

#### Contenidos
* Autonomía en la gestión de proyectos software.
* Verificación final de calidad de código y documentación técnica.
* Auditoría de cierre de incidencias y estado del tablero Kanban.
* Etiquetado semántico de versiones con Git (*tags*).
* Publicación formal de una *Release* en GitHub con notas de versión (*Release Notes*).
* Cierre y entrega del repositorio.

#### Actividades
El equipo aborda de forma autónoma las **tareas 13 a 16 (Etapa 4)**:
1. analiza los requisitos avanzados finales (persistencia y carga de datos, consolidación del modelo, guía de contribución completa);
2. subdivide, asigna y desarrolla las tareas en ramas independientes;
3. efectúa revisiones cruzadas rigurosas e integra las modificaciones;
4. revisa que la documentación técnica mínima obligatoria esté completa, formateada y enlazada;
5. comprueba que las 16 tareas del proyecto están completadas y cerradas en el tablero Kanban;
6. genera una etiqueta de versión (ej. `v1.0.0`) y publica la *Release* final en GitHub con su resumen de cambios.

#### Entregables
1. **16 tareas completadas:** las 16 tareas pequeñas del proyecto (Etapas 1 a 4) han sido implementadas, revisadas e integradas.
2. **Tablero Kanban finalizado:** todas las tarjetas se encuentran en la columna `Done`.
3. **Documentación técnica íntegra:** los 6 elementos documentales requeridos están presentes y actualizados en `docs/` y en la raíz.
4. **Release final publicada:** creación del tag `v1.0.0` y publicación formal de la *Release* en GitHub con sus notas de versión.
5. **Repositorio final coherente:** entrega completa y funcional lista para su evaluación objetiva antes de la fecha límite.

#### Estado al finalizar
> El equipo entrega un repositorio profesional con 16 tareas completadas, trazabilidad exhaustiva, documentación técnica completa y una Release formal publicada en GitHub sin necesidad de presentación oral.

---

# 8. Estado final del repositorio

Al finalizar las 16 horas y las 16 tareas, el repositorio en GitHub deberá contener:

```text
projecte/
├── Main.java                 # Código fuente único de la aplicación (clase implícita Java 25)
├── dades_botiga.txt          # Archivo de persistencia de datos (formato plano ';')
├── README.md                 # Descripción del proyecto, navegación e instrucciones (Elem. 1)
├── CONTRIBUTING.md           # Guía completa de colaboración y flujo de trabajo Git (Elem. 5)
├── CHANGELOG.md              # Historial de versiones y notas de release
│
└── docs/                     # Documentación técnica organizada por audiencias
    ├── README.md             # Índice y mapa general de documentación técnica
    ├── user-guide.md         # Casos de uso e interacciones de usuarios (Elem. 4)
    ├── installation.md       # Requisitos y puesta en marcha con Java 25
    ├── deployment.md         # Despliegue, persistencia y copias de seguridad
    ├── troubleshooting.md    # Resolución de incidencias comunes
    ├── architecture/         # Arquitectura del sistema y modelos
    │   ├── overview.md       # Tecnologías, diseño modular y restricciones (Elem. 6)
    │   ├── data-model.md     # Estructuras de datos paralelas y archivo (Elem. 3)
    │   └── diagrams.md       # Diagramas Mermaid: arquitectura, ER, flujo y estados (Elem. 2)
    ├── api/                  # Especificación de módulos internos y helpers UI
    │   ├── README.md         # Convenciones de API interna
    │   ├── ui.md             # Helpers de interfaz de terminal y colores ANSI
    │   ├── customers.md      # Gestión de clientes
    │   ├── products.md       # Catálogo de productos y stock
    │   ├── sales.md          # Registro de ventas y facturación
    │   └── data.md           # Persistencia y gestión de arrays
    └── development/          # Guías para desarrolladores
        ├── environment.md    # Configuración del entorno de desarrollo
        ├── workflow.md       # Flujo de trabajo Git, ramas y revisiones PR
        └── testing.md        # Plan de pruebas manuales y scripts automatizados
```

Y en la plataforma GitHub deberán existir todas las evidencias objetivas del proyecto gestionado:

* **16 tareas** gestionadas mediante *Issues* con etiquetas, responsables y asignaciones;
* tablero **Kanban (GitHub Projects)** totalmente actualizado reflejando el histórico de trabajo;
* ramas de trabajo utilizadas para cada tarea antes de su integración;
* historial de *commits* atómicos con mensajes claros y descriptivos;
* **Pull Requests** asociadas a cada una de las 16 tareas, con revisiones por pares, comentarios técnicos y aprobaciones explícitas;
* evidencias de resolución de conflictos de integración;
* etiqueta de versión (*tag*) y **Release formal** (`v1.0.0`) marcando el estado de entrega.

La historia del repositorio deberá permitir reconstruir con precisión cómo se ha desarrollado todo el trabajo en equipo.

---

# 9. Principios metodológicos

### El profesorado proporciona el contenido base
Los alumnos no necesitan tener todavía los conocimientos técnicos avanzados requeridos para diseñar la arquitectura del producto desde cero.

El profesorado proporciona:
> **qué hay que hacer y con qué información de partida.**

El alumnado aprende:
> **cómo organizar, versionar, revisar, documentar e integrar ese trabajo.**

Esto se aplica tanto al código fuente como a la documentación técnica, diagramas y demás artefactos del proyecto.

---

### Aprendizaje progresivo
Cada nueva herramienta aparece cuando resulta necesaria. No se pretende explicar inicialmente todo Git y GitHub de golpe.

El aprendizaje sigue una progresión lógica a lo largo de las cuatro etapas del proyecto:
```text
Repositorio inicial
        ↓
Tablero Kanban y Tareas
        ↓
Ramas temáticas
        ↓
Commits descriptivos
        ↓
Pull Requests
        ↓
Revisión por pares
        ↓
Integración (Merge)
        ↓
Resolución de conflictos
        ↓
Documentación técnica versionada
        ↓
Descomposición de tareas complejas
        ↓
Autonomía y Release
```

---

### Todos hacen de todo
Aunque exista un reparto operativo de responsabilidades en cada etapa, ningún alumno quedará limitado a un único rol.

Todos los miembros del equipo deberán experimentar de manera obligatoria:
* gestión de *Issues* y tarjetas en el tablero Kanban;
* creación y publicación de ramas;
* realización de *commits* con mensajes comprensibles;
* creación de *Pull Requests*;
* revisión técnica y comentarios constructivos en PRs de compañeros;
* aprobación e integración (*merge*);
* redacción de documentación técnica en Markdown;
* elaboración de diagramas con Mermaid;
* participación activa en la resolución de conflictos;
* preparación y publicación de versiones (*releases*).

El objetivo es que comprendan el **proceso completo de ingeniería de software colaborativa**, no solamente una función aislada dentro de un grupo particular.

---

# 10. Evaluación

La evaluación se centrará en **el proceso de trabajo y las evidencias observables generadas en GitHub**, no en la calidad técnica intrínseca del producto de software proporcionado inicialmente por el profesorado.

* **Fecha límite de entrega:** antes de la convocatoria de evaluación correspondiente.
* **Presentaciones orales:** **no se realizarán presentaciones orales**. La evaluación se fundamentará exclusivamente en la inspección objetiva de la actividad registrada en el repositorio y en GitHub.

Se combinarán evidencias del trabajo de equipo y evidencias individuales de cada estudiante.

---

## 10.1. Evidencias observables de evaluación

| Evidencia | Qué permite comprobar |
| :--- | :--- |
| **Repositorio** | Organización general del proyecto y estructura de archivos |
| **Tablero Kanban (Projects)** | Planificación, estado del trabajo y gestión del flujo ágil |
| **Issues** | Definición, seguimiento y resolución de las 16 tareas |
| **Branches** | Aislamiento del trabajo en ramas y respeto de la rama principal |
| **Commits** | Registro progresivo, atómico y comprensible de los cambios |
| **Pull Requests** | Integración formal del trabajo y cumplimiento del flujo de trabajo |
| **Reviews y Comentarios** | Capacidad crítica para revisar el trabajo de los compañeros y comunicarse técnicamente |
| **Documentación técnica** | Inclusión y formato de los 6 documentos técnicos requeridos |
| **Diagramas Mermaid** | Representación gráfica estandarizada de arquitectura, datos y flujos |
| **Resolución de conflictos** | Capacidad para gestionar e integrar modificaciones concurrentes sin pérdidas |
| **Trazabilidad** | Conexión demostrable entre tarea, rama, *commit*, PR, revisión y cierre |
| **Release** | Preparación y empaquetado de la versión final del producto |
| **Participación individual** | Implicación real, equilibrada y continuada de cada estudiante |

---

## 10.2. Criterios de evaluación

### C1. Gestiona las tareas del proyecto y el tablero Kanban
**Evidencias:** *Issues*, etiquetas, asignaciones, hitos (*milestones*) y tablero Kanban (GitHub Projects).  
*Se considera alcanzado cuando el alumno:*
* interpreta correctamente las tareas asignadas en cada una de las cuatro etapas;
* mantiene actualizado el estado de las tareas en el tablero Kanban;
* utiliza adecuadamente las *Issues* para consultar y documentar el trabajo;
* identifica claramente qué tareas están pendientes, en curso y terminadas;
* participa en la descomposición de tareas de gran envergadura en subtareas manejables (Etapas 3 y 4).

---

### C2. Utiliza correctamente el control de versiones en ramas independientes
**Evidencias:** ramas (*branches*), *commits*, historial y sincronización con el repositorio remoto.  
*Se considera alcanzado cuando el alumno:*
* trabaja siempre mediante ramas temáticas asociadas a tareas;
* evita realizar cambios directamente sobre la rama principal (`main`);
* realiza *commits* atómicos y coherentes;
* redacta mensajes de *commit* comprensibles y significativos;
* sincroniza correctamente su trabajo local con el repositorio remoto (`push`/`pull`).

---

### C3. Participa activamente en el proceso de integración y revisión por pares
**Evidencias:** *Pull Requests*, revisiones (*reviews*), comentarios técnicos y fusiones (*merges*).  
*Se considera alcanzado cuando el alumno:*
* crea *Pull Requests* para sus cambios y las vincula con la *Issue* correspondiente;
* revisa minuciosamente el trabajo de sus compañeros de equipo;
* realiza comentarios y observaciones técnicas pertinentes;
* responde adecuadamente a las solicitudes de modificación antes de la integración;
* participa en la aprobación y fusión (*merge*) de cambios.

---

### C4. Gestiona documentación técnica versionada
**Evidencias:** archivos Markdown, diagramas Mermaid, estructura de `docs/` y guía `CONTRIBUTING.md`.  
*Se considera alcanzado cuando el alumno:*
* incorpora los 6 elementos de documentación técnica requeridos (descripción, casos de uso, base de datos, diagramas, guía de contribución y tecnologías);
* utiliza Markdown con formato claro y estructurado;
* elabora diagramas técnicos comprensibles con Mermaid a partir de especificaciones o bocetos;
* mantiene una estructura de directorios documental coherente;
* somete los cambios de documentación al mismo flujo de ramas y revisión que el código.

---

### C5. Resuelve colaborativamente situaciones de conflicto
**Evidencias:** ramas enfrentadas, resolución de conflictos en Git y en *Pull Requests*, historial de resolución.  
*Se considera alcanzado cuando el alumno:*
* comprende el origen de los conflictos por edición concurrente;
* identifica las secciones y líneas enfrentadas;
* consensúa y aplica la resolución correcta sin pérdida accidental de código o documentación;
* comprueba el resultado tras la resolución y completa satisfactoriamente la integración.

---

### C6. Mantiene la trazabilidad integral del proyecto
**Evidencias:** coherencia en la cadena de desarrollo observable en GitHub:
```text
Tarea / Requisito
       ↓
     Issue
       ↓
     Branch
       ↓
    Commits
       ↓
  Pull Request
       ↓
  Peer Review
       ↓
     Merge
       ↓
    Release
```
*Se considera alcanzado cuando:*
* todo cambio relevante en el código o la documentación puede rastrearse de manera unívoca hasta la tarea que lo motivó y viceversa.

---

### C7. Demuestra una participación individual equilibrada en el equipo
**Evidencias:** historial de actividad individual en GitHub (autoría de *commits*, PRs creadas, revisiones emitidas, tareas gestionadas).  
*Se considera alcanzado cuando el alumno:*
* asume de forma equitativa su cuota aproximada de 4 tareas;
* participa activamente tanto en el rol de autor como en el de revisor;
* colabora en todas las fases del proyecto (código, documentación, diagramas, resolución de incidencias e integración);
* no se limita a una única tarea ni delega su responsabilidad en el resto del equipo.

---

### C8. Realiza una entrega final coherente y versionada
**Evidencias:** repositorio final consolidado, estado del tablero Kanban y publicación de la *Release*.  
*Se considera alcanzado cuando el equipo entrega:*
* repositorio ordenado y funcional;
* las 16 tareas completadas, integradas y cerradas;
* documentación técnica completa y navegable;
* historial de Git limpio y coherente;
* versión final identificada mediante etiqueta (*tag*) y formalizada con una *Release* en GitHub.

---

## 10.3. Instrumento de evaluación

Se valorará cada criterio utilizando una escala de cuatro niveles de desempeño:

| Nivel | Descripción |
| :--- | :--- |
| **4 · Consolidado** | Aplica el procedimiento de forma autónoma, rigurosa y correcta en situaciones nuevas. |
| **3 · Adecuado** | Aplica el procedimiento correctamente con pequeñas aclaraciones o correcciones puntuales. |
| **2 · En desarrollo** | Comprende el procedimiento básico, pero requiere asistencia constante o comete errores relevantes de integración. |
| **1 · Inicial** | No logra aplicar el flujo de trabajo de forma funcional incluso con asistencia guiada. |

La evaluación se basará prioritariamente en **evidencias observables y registradas**, evitando apreciaciones subjetivas.

---

## 10.4. Evaluación individual y de equipo

La actividad combina dos dimensiones complementarias:

### Evaluación del equipo
Se obtiene a partir de la calidad global del repositorio y del producto entregado:
* estructura y orden del repositorio;
* estado final del tablero Kanban (todas las tareas cerradas);
* completitud de la documentación técnica y diagramas;
* calidad de las *Pull Requests* y ausencia de conflictos no resueltos;
* trazabilidad global y publicación formal de la *Release*.

### Evaluación individual
Se obtiene a partir de la actividad observable de cada estudiante en GitHub:
* tareas asignadas y completadas (~4 tareas por estudiante);
* ramas creadas y publicadas;
* *commits* realizados y calidad de sus mensajes;
* *Pull Requests* abiertas y defendidas;
* revisiones exhaustivas realizadas a compañeros;
* intervenciones en resolución de incidencias y conflictos;
* aportaciones individuales a la documentación técnica.

Este esquema previene que un estudiante pueda beneficiarse pasivamente del trabajo del resto del grupo sin haber adquirido de manera efectiva las competencias de trabajo colaborativo.

---

# 11. Organización de los materiales en este repositorio docente

Este repositorio ya incluye tanto la solución final completa como el desglose progresivo de tareas listo para entregar al alumnado:

1. **Proyecto completo de referencia ([`projecte/`](projecte/)):**
   Contiene la tienda en línea **TechShop** totalmente terminada y verificada en Java 25 (`projecte/Main.java`, `projecte/dades_botiga.txt`) junto con toda su documentación técnica (`README.md`, `CONTRIBUTING.md`, `CHANGELOG.md` y la carpeta `docs/`). Sirve al profesorado como solucionario y referencia exacta del estado final esperado.
2. **Guía y enunciado por etapas para el alumnado ([`enunciat/`](enunciat/)):**
   Contiene todo el material redactado en catalán dirigido directamente a los equipos de estudiantes:
   * [`enunciat/README.md`](enunciat/README.md): guía general para el alumnado (resumen de objetivos, organización del trabajo, flujo en GitHub y criterios de evaluación).
   * [`enunciat/etapa0-inicial/`](enunciat/etapa0-inicial/): esqueleto base compilable de `projecte/Main.java` con el que los equipos inician su repositorio en la **Sesión 1**.
   * [`enunciat/etapa1/`](enunciat/etapa1/) (**Tareas 1-4**): enunciados guiados paso a paso de nivel básico (módulo de clientes, descripción del proyecto, diagramas base Mermaid y tecnologías/instalación).
   * [`enunciat/etapa2/`](enunciat/etapa2/) (**Tareas 5-8**): plantillas de código incompleto (`TODO`) del módulo de productos y stock, documentación de API/casos de uso y escenario de conflicto de integración controlado entre las Tareas 5 y 6.
   * [`enunciat/etapa3/`](enunciat/etapa3/) (**Tareas 9-12**): especificación global del módulo de ventas, estadísticas y emisión de tiques para que el equipo la analice y descomponga autónomamente en 4 subtareas en el tablero Kanban.
   * [`enunciat/etapa4/`](enunciat/etapa4/) (**Tareas 13-16**): especificación avanzada final (persistencia en archivo con `Files.writeString`/`Files.readString`, guía de contribución, plan de pruebas, despliegue y publicación de la *Release* `v1.0.0`).

---

# 12. Competencia final alcanzada

Al finalizar la actividad, cada estudiante habrá adquirido la competencia necesaria para incorporarse a un equipo de desarrollo real y trabajar con autonomía:

> Recibir una tarea asignada en el tablero Kanban → crear la rama correspondiente → implementar la solución de forma atómica → documentar mediante commits comprensibles → subir los cambios y abrir una Pull Request → solicitar y participar en la revisión por pares → resolver discrepancias y conflictos → integrar en la rama principal → cerrar la tarea y mantener la trazabilidad.

Y comprenderá plenamente que este estándar riguroso de colaboración aplica por igual al **código fuente**, a la **documentación técnica** y a **todos los artefactos del proyecto software**.
