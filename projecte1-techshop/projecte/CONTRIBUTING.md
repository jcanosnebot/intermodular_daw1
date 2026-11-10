# 🤝 Guia de Contribució

Normes de treball en equip del projecte **Botiga en Línia**, per a l'alumnat de 1r de FP d'Informàtica que fa el **Projecte 1: Gestió col·laborativa amb GitHub**.

> [!IMPORTANT]
> **Regla d'or:** tota *pull request* que canvie el comportament de l'aplicació **ha d'actualitzar la documentació afectada**. Consulta la [matriu de documentació](#9-matriu-de-documentació-obligatòria).

## 📑 Índex

1. [Preparar l'entorn](#1-preparar-lentorn)
2. [Organització del codi](#2-organització-del-codi)
3. [Normes del codi](#3-normes-del-codi)
4. [Missatges de commit](#4-missatges-de-commit)
5. [Tasques al tauler Kanban](#5-tasques-al-tauler-kanban)
6. [Branques](#6-branques)
7. [Pull requests i revisió](#7-pull-requests-i-revisió)
8. [Comprovacions abans de demanar revisió](#8-comprovacions-abans-de-demanar-revisió)
9. [Matriu de documentació obligatòria](#9-matriu-de-documentació-obligatòria)

---

## 1. Preparar l'entorn

1. Instal·la el **JDK 25** (o superior) i **Git**. Ho explica la [guia d'instal·lació](docs/installation.md).
2. Clona el repositori i comprova que l'aplicació funciona **des de l'arrel**:
   ```bash
   git clone <URL_DEL_REPOSITORI>
   cd intermodular
   java projecte/Main.java
   ```

Pots configurar l'editor amb la guia d'[entorn de desenvolupament](docs/development/environment.md).

---

## 2. Organització del codi

Tot el codi és a [`Main.java`](Main.java), una **classe implícita** sense `class`. El fitxer es divideix en seccions marcades amb comentaris. En aquest ordre:

| Secció | Què hi ha |
| :--- | :--- |
| GUIDELINES | Les restriccions del llenguatge, en un comentari al principi. |
| Paleta de colors ANSI | Constants de color i badges. |
| Estructures de dades | Vectors paral·lels de clients, productes i vendes. |
| Mètode principal | `main()`, logotip, resum i menú principal. |
| Mètodes d'interfície | Els únics mètodes amb colors. |
| 1. Mòdul de clients | |
| 2. Mòdul de productes i estoc | |
| 3. Mòdul de vendes | |
| Cerca per identificador | `cercarIndex...PerId` |
| Alta als vectors | `afegir...` |
| Persistència | Llegir i escriure `dades_botiga.txt`. |
| Dades de demostració | |

L'explicació completa és a l'[arquitectura](docs/architecture/overview.md).

---

## 3. Normes del codi

Totes aquestes normes busquen el mateix: que el codi el puga llegir algú que encara està aprenent a programar.

### Llenguatge permés
- **Només els elements de Java de la llista de GUIDELINES** del principi de `Main.java`. El resum és a les [restriccions del llenguatge](docs/architecture/overview.md#restriccions-del-llenguatge).
- **Sense classes pròpies ni llibreries externes.**
- **Mètodes `void`.** L'única excepció són els mètodes `int` que busquen un element per ID.

### Colors
- **Els codis ANSI només es fan servir dins dels [mètodes d'interfície](docs/api/ui.md).** Si necessites un estil nou, afig un mètode d'interfície; no poses colors al mig d'un mòdul.
- **Etiquetes de color dins d'una taula:** fes servir les constants `BADGE_...`.

### Lectura del teclat
Sempre amb dues línies: l'enunciat i la lectura, en una variable local amb nom descriptiu.
```java
pregunta("Telèfon de contacte");
String telefon = scanner.nextLine().trim();
```

### Format
- **Noms en valencià i *camelCase*:** `clientNoms`, `registrarVenda()`.
- **Colors amb noms curts en majúscules:** `CYN`, `RST`.
- **Sagnat de 4 espais,** sense tabuladors.
- **Comentaris en valencià**, quan el codi no s'explique per si mateix.

---

## 4. Missatges de commit

Format *Conventional Commits*:

```text
<tipus>(<àmbit>): <descripció curta en present>

[cos opcional: per què s'ha fet el canvi]

Closes #<número d'incidència>
```

| Tipus | Quan | Exemple |
| :--- | :--- | :--- |
| `feat` | Funcionalitat nova | `feat(clients): cercar també per telefon` |
| `fix` | Correcció d'un error | `fix(vendes): evitar error amb ID no numeric` |
| `docs` | Només documentació | `docs(api): documentar llistarVendes` |
| `style` | Aspecte visual, sense canviar la lògica | `style(taules): ampliar columna de nom` |
| `refactor` | Reorganitzar codi sense canviar què fa | `refactor(ui): unificar missatges d'error` |

---

## 5. Tasques al tauler Kanban

El projecte té **16 tasques** repartides en 4 etapes al tauler de GitHub Projects:

1. Tria una incidència de la columna **To Do**.
2. Assigna-te-la (**Assign yourself**).
3. Mou-la a **In Progress**.
4. Fes servir el seu número (`#ID`) al nom de la branca i al *commit*.

---

## 6. Branques

- `main` és la branca estable: **mai es puja codi directament**.
- Cada tasca té la seua branca, creada a partir de `main` actualitzada:
  ```bash
  git checkout main
  git pull origin main
  git checkout -b feature/05-cerca-productes
  ```

| Prefix | Ús | Exemple |
| :--- | :--- | :--- |
| `feature/` | Funcionalitat | `feature/05-cerca-productes` |
| `fix/` | Correcció | `fix/09-error-id-text` |
| `docs/` | Documentació | `docs/14-manual-usuari` |

---

## 7. Pull requests i revisió

1. Puja la branca: `git push -u origin feature/05-cerca-productes`.
2. Obri la *pull request*. Es carrega la plantilla [`.github/PULL_REQUEST_TEMPLATE.md`](.github/PULL_REQUEST_TEMPLATE.md). Omple-la i escriu `Closes #5`.
3. Assigna **almenys un company o companya** com a revisor.

**Qui revisa:**
- Executa la branca en local.
- Comprova que no s'han fet servir elements de Java no permesos ni colors fora dels mètodes d'interfície.
- Comprova que la documentació s'ha actualitzat.
- Tria **Approve** o **Request changes**, amb comentaris constructius.

**Qui ha escrit el codi:** quan tinga l'aprovació, fa el **Merge** i esborra la branca.

El pas a pas complet és al [flux de treball](docs/development/workflow.md).

---

## 8. Comprovacions abans de demanar revisió

- [ ] **Compila sense errors:**
  ```bash
  javac -d /tmp/botiga projecte/Main.java
  ```
- [ ] **Funciona:** he provat el canvi amb `java projecte/Main.java`.
- [ ] **Proves de canonada:** passen les [proves de canonada](docs/development/testing.md#1-proves-amb-canonades).
- [ ] **Alineació:** taules, targetes i tiquet continuen ben alineats en una terminal de 100 columnes.
- [ ] **Persistència:** si he canviat les dades, `dades_botiga.txt` es desa i es torna a carregar bé. Si l'he modificat només per a provar, l'he deixat com estava.
- [ ] **Documentació:** he actualitzat els documents de la matriu següent.

---

## 9. Matriu de documentació obligatòria

| Si canvies... | Actualitza... |
| :--- | :--- |
| Què veu o pot fer l'usuari: pantalles, accions, missatges | [`docs/user-guide.md`](docs/user-guide.md) (manual i casos d'ús) i, si cal, les captures del [`README.md`](README.md) |
| Mètodes d'interfície o colors | [`docs/api/ui.md`](docs/api/ui.md) |
| Mòdul de clients | [`docs/api/customers.md`](docs/api/customers.md) |
| Mòdul de productes | [`docs/api/products.md`](docs/api/products.md) |
| Mòdul de vendes o tiquet | [`docs/api/sales.md`](docs/api/sales.md) |
| Cerca per ID, mètodes `afegir...` o persistència | [`docs/api/data.md`](docs/api/data.md) |
| Vectors, regles de les dades o format del fitxer | [`docs/architecture/data-model.md`](docs/architecture/data-model.md) |
| Organització del codi o un flux | [`docs/architecture/overview.md`](docs/architecture/overview.md) i [`docs/architecture/diagrams.md`](docs/architecture/diagrams.md) |
| Requisits de Java o forma d'executar | [`docs/installation.md`](docs/installation.md) i [`docs/deployment.md`](docs/deployment.md) |
| Solució a un error freqüent | [`docs/troubleshooting.md`](docs/troubleshooting.md) |
| Tancament d'una versió | [`CHANGELOG.md`](CHANGELOG.md) |
