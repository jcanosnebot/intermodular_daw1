# 📊 Diagrames · Botiga en Línia

Tots els diagrames estan escrits en **Mermaid** dins d'aquest fitxer Markdown (*documentació com a codi*). GitHub els dibuixa automàticament i es poden revisar en una *pull request* com qualsevol línia de codi.

## 📑 Índex

1. [Arquitectura](#1-arquitectura)
2. [Casos d'ús](#2-casos-dús)
3. [Model de dades](#3-model-de-dades)
4. [Navegació entre pantalles](#4-navegació-entre-pantalles)
5. [Flux d'una venda](#5-flux-duna-venda)

---

## 1. Arquitectura

Parts de `Main.java` i com depenen les unes de les altres. L'explicació és a [overview.md](overview.md).

```mermaid
flowchart TD
    U([👤 Persona gestora]) <-->|teclat i pantalla| MAIN["main()<br/>menú principal"]

    MAIN --> MC["👥 Mòdul de clients"]
    MAIN --> MP["📦 Mòdul de productes"]
    MAIN --> MV["💳 Mòdul de vendes"]
    MAIN -->|opció 4| PER["💾 Persistència"]

    MC & MP & MV --> UI["🎨 Mètodes d'interfície<br/>titol · nota · ok · error · avis<br/>pregunta · opcio · dada · capcalera<br/>mostrarTargetes · mostrarTaula"]
    MC & MP & MV --> DAT["🔎 cercarIndex...PerId<br/>➕ afegirClient / Producte / Venda"]

    DAT --> VEC[("Vectors paral·lels")]
    MC & MP & MV --> VEC
    PER <--> VEC
    PER <-->|Files.readString<br/>Files.writeString| FIT[("dades_botiga.txt")]
```

---

## 2. Casos d'ús

L'especificació de cada cas d'ús és al [manual d'usuari](../user-guide.md#casos-dús).

```mermaid
flowchart LR
    G([👤 Persona<br/>gestora])

    subgraph SIS ["Botiga en Línia"]
        CU01((CU-01<br/>Consultar resum))

        subgraph SC ["Clients"]
            CU02((CU-02<br/>Cercar client))
            CU03((CU-03<br/>Alta client))
            CU04((CU-04<br/>Modificar client))
            CU05((CU-05<br/>Baixa client))
        end

        subgraph SP ["Catàleg"]
            CU06((CU-06<br/>Cercar article))
            CU07((CU-07<br/>Alta article))
            CU08((CU-08<br/>Modificar article))
            CU09((CU-09<br/>Reposar estoc))
            CU10((CU-10<br/>Eliminar article))
        end

        subgraph SV ["Vendes"]
            CU11((CU-11<br/>Estadístiques))
            CU12((CU-12<br/>Cercar vendes))
            CU13((CU-13<br/>Registrar venda))
        end

        CU14((CU-14<br/>Desar dades))
        CU15((CU-15<br/>Eixir))
    end

    G --- CU01 & CU02 & CU06 & CU11 & CU12 & CU13 & CU14 & CU15
    CU02 -. inclou .-> CU03
    CU02 -. inclou .-> CU04
    CU02 -. inclou .-> CU05
    CU06 -. inclou .-> CU07
    CU06 -. inclou .-> CU08
    CU06 -. inclou .-> CU09
    CU06 -. inclou .-> CU10
```

> [!NOTE]
> Les fletxes discontínues indiquen que l'alta, la modificació, la baixa, la reposició i l'eliminació **es fan des de la cerca**: no hi ha cap menú propi per a fer-les.

---

## 3. Model de dades

Entitats, atributs i relacions. Les regles de cada atribut són a [data-model.md](data-model.md).

```mermaid
erDiagram
    CLIENT ||--o{ VENDA : "fa"
    PRODUCTE ||--o{ VENDA : "apareix en"

    CLIENT {
        int clientIds PK
        String clientNoms
        String clientEmails
        String clientTelefons
        boolean clientActius
    }

    PRODUCTE {
        int producteIds PK
        String producteNoms
        String producteCategories
        double productePreus
        int producteEstoc
    }

    VENDA {
        int vendaIds PK
        int vendaClientIds FK
        int vendaProducteIds FK
        int vendaQuantitats
        double vendaTotals
        String vendaDates
    }
```

---

## 4. Navegació entre pantalles

Què passa amb cada tecla. `INTRO` buit sempre torna a la pantalla anterior.

```mermaid
stateDiagram-v2
    [*] --> MenuPrincipal

    MenuPrincipal --> Clients : 1
    MenuPrincipal --> Cataleg : 2
    MenuPrincipal --> Vendes : 3
    MenuPrincipal --> MenuPrincipal : 4 (desar)
    MenuPrincipal --> [*] : 5 (eixir)

    state Clients {
        [*] --> LlistaClients
        LlistaClients --> FitxaClient : coincidència exacta
        LlistaClients --> ParcialsClient : sense coincidència exacta
        FitxaClient --> LlistaClients : M (modificar) / E (baixa) / INTRO
        ParcialsClient --> LlistaClients : s (alta) / N / INTRO
    }

    state Cataleg {
        [*] --> LlistaArticles
        LlistaArticles --> FitxaArticle : coincidència exacta
        LlistaArticles --> ParcialsArticle : sense coincidència exacta
        FitxaArticle --> LlistaArticles : M / R (reposar) / E (eliminar) / INTRO
        ParcialsArticle --> LlistaArticles : s (alta) / N / INTRO
    }

    state Vendes {
        [*] --> EstadistiquesIHistorial
        EstadistiquesIHistorial --> Tiquet : R (registrar)
        EstadistiquesIHistorial --> ResultatsCerca : B o text
        Tiquet --> EstadistiquesIHistorial
        ResultatsCerca --> EstadistiquesIHistorial : INTRO
    }

    Clients --> MenuPrincipal : INTRO
    Cataleg --> MenuPrincipal : INTRO
    Vendes --> MenuPrincipal : INTRO
```

---

## 5. Flux d'una venda

Seqüència completa de `registrarVenda()`, des que es tria `R` fins que s'imprimeix el tiquet.

```mermaid
sequenceDiagram
    autonumber
    actor G as 👤 Gestora
    participant V as 💳 registrarVenda()
    participant D as 🔎 Cerca per ID
    participant M as 🗄️ Vectors
    participant T as 🧾 imprimirTiquet()

    G->>V: R (registrar nova comanda)
    V->>G: llista de clients
    G->>V: ID del client
    V->>D: cercarIndexClientPerId(id)
    alt el client no existeix
        V-->>G: ✘ ERROR Client inexistent
    else el client existeix
        V->>G: catàleg de productes
        G->>V: ID del producte
        V->>D: cercarIndexProductePerId(id)
        alt el producte no existeix
            V-->>G: ✘ ERROR Producte no trobat
        else el producte existeix
            G->>V: unitats
            alt unitats < 1 o més que l'estoc
                V-->>G: ✘ ERROR quantitat no vàlida / estoc insuficient
            else quantitat correcta
                V->>M: producteEstoc[p] -= unitats
                V->>M: afegirVenda(1001 + vendaCount, ..., preu × unitats, data)
                V->>T: imprimirTiquet(venda, client, producte)
                T-->>G: tiquet amb codi TIQ-id-aleatori
                V-->>G: estoc restant del producte
            end
        end
    end
```
