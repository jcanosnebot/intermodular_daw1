# Tasca 8 · Manual d'Usuari (CU-01 a CU-10) i Diagrames de Casos d'Ús i Navegació

* **Etapa:** Etapa 2 (Nivell Intermedi)
* **Tipus de tasca:** Especificació de Casos d'Ús (Element obligatori 4) i diagrames Mermaid
* **Branca de treball:** `docs/8-casos-us-navegacio`
* **Fitxers a crear/modificar:** `projecte/docs/user-guide.md` i `projecte/docs/architecture/diagrams.md`

---

## 1. Objectiu de la Tasca

Documentar les interaccions de l'usuari amb el sistema per als mòduls de Clients i Productes i representar-les gràficament:
1. Crear `projecte/docs/user-guide.md` amb el catàleg de casos d'ús (`CU-01` a `CU-10`), l'explicació del panell de control principal i el funcionament pas a pas dels mòduls de Clients i de Productes.
2. Ampliar `projecte/docs/architecture/diagrams.md` afegint el **Diagrama de Casos d'Ús** (`flowchart LR`) i el **Diagrama d'Estats de Navegació entre Pantalles** (`stateDiagram-v2`).

---

## 2. Instruccions Pas a Pas

### Pas 1: Crea la teua branca
```bash
git switch main
git pull origin main
git switch -c docs/8-casos-us-navegacio
```

### Pas 2: Crea `projecte/docs/user-guide.md`
Redacta el manual d'usuari estructurant els casos d'ús següents:

1. **Taula resum de Casos d'Ús (Clients i Productes):**
   * `CU-01`: Consultar directori de clients (`[1]` al menú principal).
   * `CU-02`: Cercar client i consultar la seua fitxa i historial (introduint ID `101`, `#101`, nom, correu o telèfon).
   * `CU-03`: Donar d'alta un nou client (quan el text cercat no coincideix exactament i es confirma amb `S`).
   * `CU-04`: Modificar dades d'un client (`M` des de la fitxa del client; `INTRO` manté el valor actual).
   * `CU-05`: Donar de baixa un client (`E` des de la fitxa; canvia l'estat a `BAIXA` sense esborrar l'historial).
   * `CU-06`: Consultar catàleg i nivell d'estoc (`[2]` al menú principal).
   * `CU-07`: Cercar article i consultar la seua fitxa i vendes (per ID `1`, `01`, `#1`, nom o categoria).
   * `CU-08`: Donar d'alta un nou article al catàleg (`S` quan no hi ha coincidència exacta).
   * `CU-09`: Modificar dades d'un article (`M` des de la fitxa de l'article).
   * `CU-10`: Reposar estoc (`R`) o eliminar un article del catàleg (`E`).
   *(Nota: Els casos d'ús `CU-11` a `CU-15` corresponents a Vendes i Persistència s'afegiran a les Etapes 3 i 4).*
2. **Explicació pas a pas amb captures en text:**
   * Executa `java projecte/Main.java`, entra a les opcions `[1]` i `[2]`, i inclou captures en blocs `text` de la taula de clients, la fitxa d'un client, la taula de productes amb barres d'estoc i la fitxa d'un article.

### Pas 3: Amplia `projecte/docs/architecture/diagrams.md`
Afegeix dos nous diagrames Mermaid al fitxer `diagrams.md` creat a l'Etapa 1:

1. **Secció `2. Casos d'ús` (`flowchart LR`):**
   * Actor `👤 Usuari / Administrador` connectat als casos d'ús agrupats en 4 `subgraph`: `1. Clients` (`CU01`..`CU05`), `2. Productes i estoc` (`CU06`..`CU10`), `3. Vendes` (`CU11`..`CU13`) i `4. Sistema` (`CU14`..`CU15`).
2. **Secció `4. Navegació entre pantalles` (`stateDiagram-v2`):**
   * Representa com l'usuari es mou des de `[*] --> Inici` (`inicialitzarDadesBase()`, `carregarDadesFitxer()`, `mostrarCapcaleraPrincipal()`) cap a `MenuPrincipal`, i d'ací a `ModulClients` (`[1]`), `ModulProductes` (`[2]`), `ModulVendes` (`[3]`), `GuardarDades` (`[4]`) i `Fi` (`[5]`), incloent els subestats de `FitxaClient`, `AltaClient`, `FitxaProducte`, `AltaProducte`, `CercaVendes` i `RegistrarVenda`.

---

## 3. Criteris d'Acceptació (*Definition of Done*)

- [ ] `projecte/docs/user-guide.md` documenta els casos d'ús `CU-01` a `CU-10` amb exemples reals d'entrada per teclat i captures de terminal.
- [ ] `projecte/docs/architecture/diagrams.md` incorpora el diagrama de Casos d'Ús (`flowchart LR`) i el diagrama de Màquina d'Estats de Navegació (`stateDiagram-v2`) sense errors de sintaxi Mermaid.
- [ ] S'ha fet *commit* (`docs(guide): afegeix manual d'usuari CU-01 a CU-10 i diagrames de casos d'us i navegacio`), *push* i obert la *Pull Request* vinculada a la *Issue* #8.
