# Tasca 3 · Diagrames d'Arquitectura i Disseny del Model de Dades

* **Etapa:** Etapa 1 (Nivell Bàsic)
* **Tipus de tasca:** Reproducció de diagrames Mermaid a partir d'esbossos inicials i disseny de dades (Elements obligatoris 2 i 3)
* **Branca de treball:** `docs/3-diagrames-model-dades`
* **Fitxers a crear:** `projecte/docs/architecture/diagrams.md` i `projecte/docs/architecture/data-model.md`

---

## 1. Objectiu de la Tasca

A partir dels **esbossos inicials de pissarra** proporcionats en aquest enunciat, reproduir de manera estandarditzada amb **Mermaid**:
1. El **Diagrama d'Arquitectura per Capes** (`flowchart TB`) i el **Diagrama Entitat-Relació** (`erDiagram`) dins de `projecte/docs/architecture/diagrams.md`.
2. L'especificació del **Model de Dades en Memòria** (vectors paral·lels, correspondència per índex `i`, atributs de `CLIENT`, `PRODUCTE` i `VENDA`, i diferència entre baixa lògica i baixa física) dins de `projecte/docs/architecture/data-model.md`.

---

## 2. Esbossos Inicials de Referència

### Esbós A: Arquitectura en 4 Capes de `Main.java`
Reprodueix aquest esbós amb un bloc `mermaid` de tipus `flowchart TB` amb `subgraph` per a cada capa:

```text
+-----------------------------------------------------------------------+
| 1. INTERFÍCIE DE TERMINAL (UI) — Únics mètodes amb codis ANSI         |
|   [Colors i Badges]  [Missatges: titol, ok, error, avis, pregunta...] |
|   [Taules i Targetes: mostrarTargetes, mostrarTaula, imprimirTiquet]  |
+-----------------------------------------------------------------------+
                                   ^
                                   |
+-----------------------------------------------------------------------+
| 2. NAVEGACIÓ I MÒDULS DE NEGOCI                                       |
|   [main: mostrarCapcaleraPrincipal, mostrarResumGeneral,              |
|          mostrarMenuPrincipal]                                        |
|      |---> [Mòdul Clients: menuClients, llistar, gestionar,           |
|      |      modificar, eliminar, donarAlta]                           |
|      |---> [Mòdul Productes: menuProductes, llistar, gestionar,       |
|      |      modificar, reposarEstoc, eliminar, donarAlta]             |
|      +---> [Mòdul Vendes: menuVendes, mostrarEstadistiques, llistar,  |
|             cercar, registrarVenda]                                   |
+-----------------------------------------------------------------------+
                                   |
                                   v
+-----------------------------------------------------------------------+
| 3. ESTRUCTURES DE DADES EN MEMÒRIA (Vectors Paral·lels, màx. 50)      |
|   [Vectors Clients: clientIds, clientNoms, clientEmails,              |
|    clientTelefons, clientActius, clientCount]                         |
|   [Vectors Productes: producteIds, producteNoms, producteCategories,  |
|    productePreus, producteEstoc, producteCount]                       |
|   [Vectors Vendes: vendaIds, vendaClientIds, vendaProducteIds,        |
|    vendaQuantitats, vendaTotals, vendaDates, vendaCount]              |
|   [Cerca i Alta: cercarIndexClientPerId, cercarIndexProductePerId,    |
|    afegirClient, afegirProducte, afegirVenda]                         |
+-----------------------------------------------------------------------+
                                   ^
                                   |
+-----------------------------------------------------------------------+
| 4. PERSISTÈNCIA I DADES BASE                                          |
|   [inicialitzarDadesBase]   [guardarDadesFitxer / carregarDadesFitxer]|
|                                         <---> [(dades_botiga.txt)]    |
+-----------------------------------------------------------------------+
```

### Esbós B: Model Entitat-Relació (ER)
Reprodueix aquest esquema amb un bloc `mermaid` de tipus `erDiagram`:

```text
+----------------------------------+         +------------------------------------+
|              CLIENT              |         |               VENDA                |
+----------------------------------+         +------------------------------------+
| PK int     clientIds (101..)     | 1    0..* | PK int    vendaIds (1001..)      |
|    String  clientNoms            |--------<| FK int    vendaClientIds           |
|    String  clientEmails          | realitza| FK int    vendaProducteIds         |
|    String  clientTelefons        |         |    int    vendaQuantitats          |
|    boolean clientActius          |         |    double vendaTotals              |
+----------------------------------+         |    String vendaDates (AAAA-MM-DD)  |
                                             +------------------------------------+
                                                               ^ 0..*
                                                               | inclou
                                                               | 1
                                             +------------------------------------+
                                             |              PRODUCTE              |
                                             +------------------------------------+
                                             | PK int    producteIds (1..)        |
                                             |    String producteNoms             |
                                             |    String producteCategories       |
                                             |    double productePreus            |
                                             |    int    producteEstoc            |
                                             +------------------------------------+
```

---

## 3. Instruccions Pas a Pas

### Pas 1: Crea i activa la teua branca
```bash
git switch main
git pull origin main
git switch -c docs/3-diagrames-model-dades
```

### Pas 2: Crea `projecte/docs/architecture/diagrams.md`
1. Crea la carpeta `projecte/docs/architecture/` si encara no existeix.
2. En `projecte/docs/architecture/diagrams.md`, crea les seccions:
   * **1. Arquitectura de `Main.java`:** Tradueix l'**Esbós A** a un diagrama Mermaid `flowchart TB` amb 4 `subgraph` (`UI`, `APP`, `DATA`, `IO`) i les relacions entre blocs.
   * **3. Model de dades (Entitat-Relació):** Tradueix l'**Esbós B** a un diagrama Mermaid `erDiagram` amb les entitats `CLIENT`, `PRODUCTE` i `VENDA`, indicant els tipus (`int`, `String`, `double`, `boolean`), les claus (`PK`, `FK`) i les cardinalitats (`||--o{`).
   *(Nota: Els diagrames de Casos d'Ús, Navegació i Seqüència de Venda s'afegiran a les Etapes 2 i 3).*

### Pas 3: Crea `projecte/docs/architecture/data-model.md`
Redacta el document de disseny de la base de dades en memòria incloent:
1. **Explicació del patró de Vectors Paral·lels:** Com que no s'utilitzen classes pròpies, cada entitat es reparteix en diversos vectors de capacitat `50` on l'índex `i` (des de `0` fins a `count - 1`) uneix totes les propietats del mateix registre.
2. **Diagrama visual de correspondència per índex `i`:** Exemple per a `i = 0` (`101`, `"Anna Garcia"`, `"anna@correu.cat"`, `"611223344"`, `true`).
3. **Diagrama `erDiagram` Mermaid** i **Taules d'atributs i regles de validació** per a:
   * **Clients:** `maxClients = 50`, ID inicial `101 + clientCount`, nom mínim 3 caràcters, correu amb `@` i `.`, **baixa lògica** (`clientActius[idx] = false`) per no trencar les vendes històriques.
   * **Productes:** `maxProductes = 50`, ID calculat amb `Math.max(...) + 1`, nom mínim 2 caràcters, preu `> 0`, estoc `>= 0`, **eliminació física** desplaçant els elements posteriors una posició a l'esquerra (`i = idx` fins a `producteCount - 2`).
   * **Vendes:** `maxVendes = 50`, ID `1001 + vendaCount`, import total `productePreus[p] * quantitat` (sense descomptes), registres immutables.

---

## 4. Criteris d'Acceptació (*Definition of Done*)

- [ ] `projecte/docs/architecture/diagrams.md` conté els diagrames Mermaid d'Arquitectura (`flowchart TB`) i Entitat-Relació (`erDiagram`) sintàcticament vàlids (es previsualitzen a GitHub sense errors).
- [ ] `projecte/docs/architecture/data-model.md` documenta els vectors paral·lels, les taules de camps/validacions i la diferència entre baixa lògica (clients) i baixa física (productes).
- [ ] Els noms de les variables coincideixen exactament amb els de `projecte/Main.java`.
- [ ] S'ha fet *commit* (`docs(architecture): afegeix diagrames base i disseny del model de dades`), *push* i obert la *Pull Request* vinculada a la *Issue* #3.
