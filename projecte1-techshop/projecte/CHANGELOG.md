# Registre de Canvis (Changelog)

Tots els canvis notables en aquest projecte seran documentats en aquest fitxer.

L'estructura segueix les recomanacions de [Keep a Changelog](https://keepachangelog.com/ca/1.0.0/) i s'adhereix a [Semantic Versioning](https://semver.org/lang/ca/).

---

## [2.0.0] - 2026-10-24 · Versió final

### ✨ Afegit
- **Cerca per qualsevol camp:**
  - **Clients:** per ID (`101` o `#101`), nom, correu o telèfon.
  - **Articles:** per ID (`1`, `01` o `#1`), nom o categoria.
  - **Vendes:** per ID de venda, client, article o data.
- **Fitxes amb historial:**
  - La fitxa de cada client mostra les seues compres.
  - La fitxa de cada article mostra les seues vendes.
- **Gestió completa del catàleg:**
  - Alta d'articles.
  - Modificació de nom, categoria, preu i estoc.
  - Eliminació d'articles.
- **Modificació de les dades dels clients.**
- **Documentació:** model de dades i fitxa de mètodes de dades i persistència (`docs/api/data.md`), casos d'ús al manual d'usuari, i diagrames de casos d'ús i de navegació.
- `projecte/.gitignore` per a no versionar fitxers compilats ni la carpeta `out/` d'IntelliJ.

### 🔄 Canviat
- **Mòduls sense submenús:**
  - Clients i catàleg mostren directament la llista i un cercador. Una coincidència exacta obri la fitxa; si no n'hi ha, es mostren les parcials i s'ofereix l'alta.
  - Vendes mostra directament les estadístiques, l'historial i les accions.
- **El menú principal** es dibuixa amb `mostrarTaula`. S'ha eliminat el mètode genèric `mostrarMenu`.
- **Refactorització de `Main.java` per a fer-lo més llegible:**
  - Els colors ANSI només apareixen als mètodes d'interfície (`titol`, `nota`, `ok`, `error`, `avis`, `pregunta`, `opcio`, `dada`, `capcalera`, `mostrarTargetes`, `mostrarTaula`).
  - Els colors tenen noms curts (`RST`, `CYN`...).
  - `pregunta` i `opcio` només imprimeixen l'enunciat: la resposta es llig a continuació, en una variable local.
  - `mostrarTaula` i `imprimirTiquet` imprimeixen directament, sense mètodes auxiliars.
  - Un sol mètode de llistat amb filtre per entitat (`llistarClients`, `llistarProductes`, `llistarVendes`).
  - Mètodes `afegirClient`, `afegirProducte` i `afegirVenda` per a afegir registres als vectors.
- **Resum general i estadístiques de vendes** mostrats com a targetes.
- **Requisit de Java 25 definitiu:** ja no cal `--enable-preview`. L'aplicació s'executa des de l'arrel del repositori amb `java projecte/Main.java`.
- **Documentació reescrita** i ajustada al codi final.

### 🗑️ Eliminat
- **Descomptes per volum:** la matriu `taulaDescomptes`, el seu càlcul i la seua taula. El total d'una venda és ara preu × unitats.
- **Documentació duplicada:** `docs/descripcion.md`, `docs/casos-de-uso.md`, `docs/base-datos.md`, `docs/tecnologias.md` i `docs/diagramas/*.mmd`. El seu contingut és ara a `README.md`, `docs/user-guide.md`, `docs/architecture/` i `docs/api/`.
- **Carpeta `out/`** de compilació d'IntelliJ, que contenia còpies antigues del projecte.

### 🐛 Corregit
- **`dades_botiga.txt`:** la venda `#1001` tenia guardat l'import amb el descompte antic (674,99 €). Ara té el seu import real, 749,99 €.

---

## [1.0.0] - 2026-10-22

### ✨ Afegit
- **Interfície Visual TUI Avançada:**
  - Bàner superior amb logotip ASCII art `TECH-SHOP` i degradat de colors ANSI.
  - Barra superior de KPIs d'estat amb mètriques en temps real (clients, articles, vendes, facturació).
  - Menús i submenús interactius amb targetes numerades arrodonides estil *pill badges*.
  - Barres visuals Unicode de nivell d'estoc (`[████░░░░░░]`) amb codi de colors segons disponibilitat.
  - Tiquet digital de compra amb format de caixa registradora de 68 columnes exactes.
  - Panell d'estadístiques financeres amb targetes KPI independents (*dashboard cards*).
- **Mòdul de Descomptes 2D:**
  - Matriu bidimensional `double[][] taulaDescomptes` per aplicar trams automàtics de descompte per volum (5%, 10%, 15%).
- **Persistència Minimalista:**
  - Carregament i desat de dades en format de text delimitat mitjançant `Files.readString` i `Files.writeString` (`dades_botiga.txt`).
- **Documentació Completa per a 4 Audiències:**
  - Guia d'usuaris (`docs/user-guide.md`).
  - Arquitectura i model de dades (`docs/architecture/`).
  - Referència d'API Java (`docs/api/`).
  - Guies de desenvolupament, proves i flux de treball (`docs/development/`).
  - Plantilles d'incidències i pull requests a `.github/`.

### 🔄 Canviat
- S'ha eliminat la dependència externa de TamboUI en favor de codis ANSI natius de terminal, assegurant compatibilitat universal i senzillesa didàctica.
- S'han simplificat les estructures de dades en vectors paral·lels per facilitar l'aprenentatge a l'alumnat de 1r curs de FP.
- Eliminades les funcionalitats fora d'abast pedagògic (carretó temporal multi-article i xarxa de transport).

---

## [0.2.0] - 2026-10-18

### ✨ Afegit
- Mètode principal d'instància utilitzant *Implicitly Declared Classes* (Java 25 preview).
- Gestió bàsica de clients (alta, llistat i cerca per text).
- Gestió bàsica d'estoc amb reposició numèrica.
- Càlculs matemàtics mitjançant la classe estàndard `Math` (`max`, `min`).

---

## [0.1.0] - 2026-10-15

### ✨ Afegit
- Disseny inicial de l'arquitectura i estructures de dades en memòria.
- Definició de requisits tecnològics per al Projecte 1 de desenvolupament col·laboratiu amb GitHub.
