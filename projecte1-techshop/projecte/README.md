# 🛍️ Botiga en Línia · Projecte de Referència

[![Java](https://img.shields.io/badge/Java-25-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/25/)
[![Terminal](https://img.shields.io/badge/TUI-colors%20ANSI-00B7C3?logo=gnometerminal&logoColor=white)](https://en.wikipedia.org/wiki/ANSI_escape_code)
[![Llicència](https://img.shields.io/badge/Llicència-MIT-green.svg)](LICENSE)
[![FP](https://img.shields.io/badge/FP%20Informàtica-1r%20curs-blue.svg)](https://ceice.gva.es/)

Aplicació de demostració d'una **botiga en línia en consola** (*Terminal User Interface*, TUI) per al **Projecte Intermodular de 1r de FP d'Informàtica**.

És la base del **Projecte 1: Gestió col·laborativa amb GitHub**. L'alumnat no l'ha de programar des de zero: hi treballa en equip amb incidències, tauler Kanban, branques, *commits*, *pull requests* i revisió de codi.

> [!NOTE]
> És una **demo didàctica**, no una aplicació per a producció. Tot el codi és en un únic fitxer, [`Main.java`](Main.java), escrit només amb els elements de Java que es veuen a 1r curs (tipus primitius, `String`, vectors, matrius, bucles i condicionals), sense classes pròpies.

---

## 🌟 Què fa l'aplicació

| Mòdul | Funcionalitats |
| :--- | :--- |
| **👥 Clients** | Directori complet en entrar. Cerca per ID, nom, correu o telèfon. Fitxa amb historial de compres. Modificació, baixa i alta guiada. |
| **📦 Catàleg i estoc** | Catàleg amb barra visual d'estoc i etiqueta de disponibilitat. Cerca per ID, nom o categoria. Fitxa amb historial de vendes. Modificació, reposició, eliminació i alta. |
| **💳 Vendes** | Targetes amb estadístiques (total, mitjana, màxim i mínim). Historial complet. Cerca de vendes. Registre de comandes amb control d'estoc i tiquet. |
| **💾 Persistència** | Desa i carrega totes les dades en un fitxer de text pla, [`dades_botiga.txt`](dades_botiga.txt), amb `Files.writeString` i `Files.readString`. |

---

## 🖥️ Captures de la interfície

> [!TIP]
> A la terminal tot es veu en color. Aquestes captures són text pla per a poder-les llegir a GitHub.

### Pantalla inicial: resum general i menú principal
```text
╭────────────────────╮  ╭────────────────────╮  ╭────────────────────╮  ╭────────────────────╮
│ 👥 CLIENTS         │  │ 📦 ARTICLES        │  │ 💳 VENDES          │  │ 💰 FACTURAT        │
│            4 / 50  │  │        5 (88 un.)  │  │        2 comandes  │  │          799,49 €  │
╰────────────────────╯  ╰────────────────────╯  ╰────────────────────╯  ╰────────────────────╯

✦ PANELL DE CONTROL PRINCIPAL ✦

╭──────┬─────────────────────────┬───────────────────────────────────────────────────╮
│ OPC. │ OPCIÓ                   │ DESCRIPCIÓ DE L'ACCIÓ                             │
├──────┼─────────────────────────┼───────────────────────────────────────────────────┤
│ [1]  │ 👥  GESTIÓ DE CLIENTS   │ Directori interactiu, consulta, historial i altes │
│ [2]  │ 📦  CATÀLEG I ESTOC     │ Catàleg interactiu, consulta, reposició i altes   │
│ [3]  │ 💳  VENDES I FACTURACIÓ │ Mètriques financeres, historial i comandes        │
│ [4]  │ 💾  GUARDAR DADES       │ Persistència al fitxer de dades (Java NIO)        │
│ [5]  │ 🚪  EIXIR DEL SISTEMA   │ Finalitzar sessió de treball                      │
╰──────┴─────────────────────────┴───────────────────────────────────────────────────╯

➤ Tria una opció [1-5]:
```

### Catàleg amb el nivell d'estoc
```text
╭────┬─────────────────────────┬──────────────┬────────────┬─────────────────────┬────────────────╮
│ ID │ NOM PRODUCTE            │ CATEGORIA    │ PREU UNIT. │ NIVELL D'ESTOC      │ DISPONIBILITAT │
├────┼─────────────────────────┼──────────────┼────────────┼─────────────────────┼────────────────┤
│ 01 │ Portatil 15 polzades    │ Ordinadors   │   749,99 € │ [███░░░░░░░] 10 un. │  BAIX          │
│ 02 │ Ratoli sense fils       │ Accessoris   │    19,95 € │ [████████░░] 25 un. │  EN ESTOC      │
│ 03 │ Teclat mecanic          │ Accessoris   │    49,50 € │ [█████░░░░░] 15 un. │  EN ESTOC      │
│ 04 │ Monitor 24 4K           │ Monitors     │   189,00 € │ [██░░░░░░░░]  8 un. │  BAIX          │
│ 05 │ Auriculars Bluetooth    │ Audio        │    39,90 € │ [██████████] 30 un. │  EN ESTOC      │
╰────┴─────────────────────────┴──────────────┴────────────┴─────────────────────┴────────────────╯
  Total d'articles al catàleg: 5 / 50

➤ Cercar article per ID, nom o categoria (INTRO per a tornar):
```

### Tiquet d'una venda
```text
╭──────────────────────────────────────────────────────────────────╮
│             🧾  TIQUET DE VENDA - FACTURACIÓ OFICIAL             │
│            TECH-SHOP · PLATAFORMA DE COMERÇ ELECTRÒNIC           │
├──────────────────────────────────────────────────────────────────┤
│  Identificador de venda:                                  #1003  │
│  Codi de transacció:                              TIQ-1003-7029  │
│  Data i hora d'emissió:              2026-10-22 · Sessió activa  │
│  Client comprador:                        Anna Garcia (ID #101)  │
├──────────────────────────────────────────────────────────────────┤
│  Article adquirit:                            Ratoli sense fils  │
│  Preu unitari:                                          19,95 €  │
│  Quantitat:                                           3 unitats  │
├──────────────────────────────────────────────────────────────────┤
│  TOTAL LIQUIDAT (IVA inclòs):                           59,85 €  │
╰──────────────────────────────────────────────────────────────────╯
                  Gràcies per la vostra compra!
```

---

## ⚡ Inici ràpid

**Requisits:** JDK 25 o superior, i una terminal amb UTF-8 i colors ANSI.

```bash
git clone <URL_DEL_REPOSITORI>
cd intermodular            # arrel del repositori, NO la carpeta projecte/
java projecte/Main.java
```

> [!IMPORTANT]
> L'aplicació s'ha d'executar **des de l'arrel del repositori**, perquè el fitxer de dades es busca a la ruta `projecte/dades_botiga.txt`. Si l'executes des de dins de `projecte/`, s'iniciarà amb les dades de demostració i fallarà en desar. Més detalls a [`docs/troubleshooting.md`](docs/troubleshooting.md).

Amb Java 25 no cal `--enable-preview`: les classes implícites i la classe `IO` ja són definitives.

---

## 📚 Documentació

La documentació s'organitza per a **quatre públics**. L'índex complet és a [`docs/README.md`](docs/README.md).

| Públic | Què necessita? | Documents |
| :--- | :--- | :--- |
| **👤 Usuaris** | Saber què fa l'aplicació i com s'utilitza. | 📖 [Manual d'usuari i casos d'ús](docs/user-guide.md) |
| **💻 Desenvolupadors** | Entendre l'arquitectura, les dades i els mètodes. | 🏛️ [Arquitectura i tecnologies](docs/architecture/overview.md) · 🗄️ [Model de dades](docs/architecture/data-model.md) · 📊 [Diagrames](docs/architecture/diagrams.md) · 📚 [Referència de mètodes](docs/api/README.md) |
| **👥 Col·laboradors** | Treballar en equip amb Git i GitHub. | 🤝 [CONTRIBUTING.md](CONTRIBUTING.md) · ⚙️ [Flux de treball](docs/development/workflow.md) · 🧪 [Proves](docs/development/testing.md) |
| **🚀 Desplegament** | Instal·lar, executar i mantindre l'aplicació. | 🛠️ [Instal·lació](docs/installation.md) · 🚢 [Desplegament](docs/deployment.md) · 🔧 [Resolució de problemes](docs/troubleshooting.md) |

### Els 6 elements de documentació obligatoris del curs

| Element exigit | On és |
| :--- | :--- |
| 1. Descripció del projecte | Aquest `README.md` |
| 2. Diagrames (Mermaid) | [`docs/architecture/diagrams.md`](docs/architecture/diagrams.md) |
| 3. Disseny de la base de dades | [`docs/architecture/data-model.md`](docs/architecture/data-model.md) |
| 4. Casos d'ús | [`docs/user-guide.md`](docs/user-guide.md#casos-dús) |
| 5. Guia de contribució | [`CONTRIBUTING.md`](CONTRIBUTING.md) |
| 6. Tecnologies utilitzades | [`docs/architecture/overview.md`](docs/architecture/overview.md#tecnologies-i-restriccions-didàctiques) |

---

## 📂 Estructura del projecte

```text
projecte/
├── Main.java              # Tot el codi de l'aplicació (classe implícita de Java 25)
├── dades_botiga.txt       # Fitxer de dades (text pla separat per ';')
├── README.md              # Aquest document
├── CHANGELOG.md           # Canvis de cada versió
├── CONTRIBUTING.md        # Guia de col·laboració
├── LICENSE                # Llicència MIT
├── .github/               # Plantilles de pull request i d'incidències
└── docs/
    ├── README.md          # Índex de la documentació
    ├── user-guide.md      # Manual d'usuari i casos d'ús
    ├── installation.md    # Instal·lació en un equip nou
    ├── deployment.md      # Execució, dades i còpies de seguretat
    ├── troubleshooting.md # Problemes freqüents
    ├── architecture/      # Arquitectura, tecnologies, model de dades i diagrames
    ├── api/               # Referència dels mètodes de Main.java
    └── development/       # Entorn, proves i flux de treball
```
