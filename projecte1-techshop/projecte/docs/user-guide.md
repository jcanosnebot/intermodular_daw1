# 📖 Manual d'Usuari · Botiga en Línia

Aquest manual explica què es pot fer amb l'aplicació i com fer-ho pas a pas. No cal saber programar.

## 📑 Índex

1. [Posar en marxa l'aplicació](#1-posar-en-marxa-laplicació)
2. [Com es navega](#2-com-es-navega)
3. [Casos d'ús](#casos-dús)
4. [Pantalla inicial](#4-pantalla-inicial)
5. [Mòdul de clients](#5-mòdul-de-clients)
6. [Mòdul de catàleg i estoc](#6-mòdul-de-catàleg-i-estoc)
7. [Mòdul de vendes](#7-mòdul-de-vendes)
8. [Desar les dades i eixir](#8-desar-les-dades-i-eixir)
9. [Limitacions conegudes](#9-limitacions-conegudes)

---

## 1. Posar en marxa l'aplicació

Des de l'**arrel del repositori** (la carpeta que conté `projecte/`):

```bash
java projecte/Main.java
```

Si no tens Java 25 instal·lat, consulta la [guia d'instal·lació](installation.md).

---

## 2. Com es navega

L'aplicació sempre et diu què pots escriure. Hi ha quatre tipus de pregunta:

| Indicador | Què vol dir | Exemple |
| :--- | :--- | :--- |
| `➤ Tria una opció [1-5]:` | Escriu el número de l'opció i prem `INTRO`. | `3` |
| `➤ Tria una acció [M: ..., E: ..., INTRO: Tornar]:` | Escriu la lletra de l'acció. Majúscula o minúscula, és igual. | `m` |
| `▪ Nou nom [Anna Garcia]:` | Camp d'un formulari. Entre claudàtors hi ha el valor actual o el proposat. | `Anna Garcia Puig` |
| `[s/N]` | Confirmació. Només `s` (o `si`) diu que sí. Qualsevol altra cosa, o `INTRO`, diu que no. | `s` |

> [!TIP]
> **`INTRO` sense escriure res sempre torna enrere**: de la fitxa a la llista, i de la llista al menú principal. En els formularis de modificació, `INTRO` manté el valor actual del camp.

L'aplicació respon amb missatges de color:

| Missatge | Significat |
| :--- | :--- |
| ` ✔ ÈXIT ` (verd) | L'operació s'ha fet. |
| ` ✘ ERROR ` (roig) | No s'ha pogut fer. El missatge diu per què. |
| ` ⚠ ATENCIÓ ` (groc) | Avís: operació cancel·lada, cerca sense resultats exactes, etc. |

---

## Casos d'ús

L'únic actor és la **persona gestora de la botiga**, que fa servir l'aplicació des de la consola. El diagrama de casos d'ús és a [`architecture/diagrams.md`](architecture/diagrams.md#2-casos-dús).

| Codi | Cas d'ús | On es fa |
| :--- | :--- | :--- |
| CU-01 | Consultar el resum general de la botiga | Pantalla inicial |
| CU-02 | Cercar i consultar un client | Clients |
| CU-03 | Donar d'alta un client | Clients |
| CU-04 | Modificar les dades d'un client | Clients → fitxa → `M` |
| CU-05 | Donar de baixa un client | Clients → fitxa → `E` |
| CU-06 | Cercar i consultar un article | Catàleg |
| CU-07 | Donar d'alta un article | Catàleg |
| CU-08 | Modificar les dades d'un article | Catàleg → fitxa → `M` |
| CU-09 | Reposar l'estoc d'un article | Catàleg → fitxa → `R` |
| CU-10 | Eliminar un article del catàleg | Catàleg → fitxa → `E` |
| CU-11 | Consultar les estadístiques i l'historial de vendes | Vendes |
| CU-12 | Cercar vendes | Vendes → `B` |
| CU-13 | Registrar una venda i emetre el tiquet | Vendes → `R` |
| CU-14 | Desar les dades al fitxer | Menú principal → `4` |
| CU-15 | Eixir de l'aplicació | Menú principal → `5` |

### CU-01 · Consultar el resum general
- **Flux:** en iniciar, i cada vegada que es torna al menú principal, es mostren quatre targetes: nombre de clients, nombre d'articles amb el total d'unitats en estoc, nombre de vendes i total facturat.

### CU-02 · Cercar i consultar un client
- **Flux principal:**
  1. La persona gestora entra a **Clients**. Es mostra el directori complet.
  2. Escriu un ID (`101` o `#101`), un nom, un correu o un telèfon.
  3. Si coincideix **exactament** amb un client, es mostra la seua fitxa i el seu historial de compres.
- **Flux alternatiu:** si no hi ha coincidència exacta, es mostren els clients que **contenen** el text en algun camp i s'ofereix donar-ne d'alta un de nou (CU-03).

### CU-03 · Donar d'alta un client
- **Precondició:** hi ha menys de 50 clients.
- **Flux principal:**
  1. Des de la cerca sense coincidència exacta, la persona gestora respon `s`.
  2. Confirma el nom (proposat a partir del text cercat) o n'escriu un altre.
  3. Escriu el correu i el telèfon.
  4. El client es crea **actiu**, amb l'identificador següent (`#105`, `#106`...).
- **Errors:** nom de menys de 3 caràcters, o correu sense `@` o sense `.`. En tots dos casos l'alta es cancel·la.

### CU-04 · Modificar un client
- **Flux:** a la fitxa, `M`. Es pregunten el nom, el correu i el telèfon. `INTRO` manté el valor actual.
- **Errors:** si el nou nom o el nou correu no són vàlids, es manté el valor anterior i es continua amb el camp següent.

### CU-05 · Donar de baixa un client
- **Flux:** a la fitxa, `E`, i confirmar amb `s`. El client passa a estat **BAIXA**.
- **Postcondició:** el client no s'esborra: continua a la llista i el seu historial de compres es conserva.

### CU-06 · Cercar i consultar un article
- **Flux principal:** a **Catàleg**, escriure un ID (`1`, `01` o `#1`) o el nom exacte. Es mostra la fitxa de l'article i el seu historial de vendes.
- **Flux alternatiu:** sense coincidència exacta, es mostren els articles que contenen el text en l'ID, el nom o la **categoria**, i s'ofereix donar-ne d'alta un (CU-07).

### CU-07 · Donar d'alta un article
- **Precondició:** hi ha menys de 50 articles.
- **Flux:** confirmar o canviar el nom, i escriure la categoria (si es deixa buida, `General`), el preu i l'estoc inicial. L'article rep l'ID més alt existent + 1.
- **Errors:** nom de menys de 2 caràcters, preu ≤ 0, estoc negatiu o un número mal escrit. L'alta es cancel·la.

### CU-08 · Modificar un article
- **Flux:** a la fitxa, `M`. Es pregunten el nom, la categoria, el preu i l'estoc. `INTRO` manté el valor actual.
- **Errors:** un nom massa curt, un preu ≤ 0 o un estoc negatiu mantenen el valor anterior. Si el preu no és un número, es manté i **ja no es pregunta l'estoc**.

### CU-09 · Reposar estoc
- **Flux:** a la fitxa, `R`, i escriure quantes unitats han arribat. Se sumen a l'estoc actual.
- **Errors:** quantitat ≤ 0 o text no numèric. No es fa cap canvi.

### CU-10 · Eliminar un article
- **Flux:** a la fitxa, `E`. Si l'article té vendes, s'avisa de quantes en té. Es confirma amb `s` i l'article desapareix del catàleg.
- **Postcondició:** les vendes antigues es conserven. A l'historial, en lloc del nom de l'article apareix el seu identificador (`#3`).

### CU-11 · Consultar estadístiques i historial de vendes
- **Flux:** en entrar a **Vendes** es mostren quatre targetes (total facturat, mitjana per venda, venda màxima i venda mínima) i la taula de totes les vendes.

### CU-12 · Cercar vendes
- **Flux:** a Vendes, `B` i el text a cercar. També es pot escriure el text directament, sense `B`. Es mostren les vendes que el contenen en l'ID de venda, el client (ID o nom), l'article (ID o nom) o la data, amb un resum del que s'ha trobat.

### CU-13 · Registrar una venda
- **Precondició:** hi ha menys de 50 vendes.
- **Flux principal:**
  1. A Vendes, `R`. Es mostra la llista de clients i s'escriu l'**ID** del client.
  2. Es mostra el catàleg i s'escriu l'**ID** de l'article.
  3. S'escriuen les unitats.
  4. Es descompta l'estoc, es guarda la venda i s'imprimeix el **tiquet**.
- **Errors:** client o article inexistent, quantitat < 1 o més unitats de les que hi ha en estoc. La venda es cancel·la sense canvis.

### CU-14 · Desar les dades
- **Flux:** al menú principal, `4`. Totes les dades s'escriuen al fitxer `projecte/dades_botiga.txt`.

### CU-15 · Eixir
- **Flux:** al menú principal, `5`. Es mostra un missatge de comiat i l'aplicació es tanca.

> [!WARNING]
> Eixir **no desa** les dades. Els canvis que no s'hagen desat amb l'opció `4` es perden.

---

## 4. Pantalla inicial

En iniciar l'aplicació apareix el logotip de **TECH-SHOP**, les targetes de resum i el menú principal:

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

La targeta **ARTICLES** mostra el nombre d'articles i, entre parèntesis, la suma de totes les unitats en estoc.

---

## 5. Mòdul de clients

Opció `1`. Es mostra directament el directori, sense submenús:

```text
╭──────┬────────────────────────┬──────────────────────────┬────────────┬──────────╮
│ ID   │ NOM DEL CLIENT         │ CORREU ELECTRÒNIC        │ TELÈFON    │ ESTAT    │
├──────┼────────────────────────┼──────────────────────────┼────────────┼──────────┤
│ #101 │ Anna Garcia            │ anna@correu.cat          │ 611223344  │  ACTIU   │
│ #102 │ Bernat Lopez           │ bernat@correu.cat        │ 622334455  │  ACTIU   │
│ #103 │ Carla Vidal            │ carla@correu.cat         │ 633445566  │  ACTIU   │
│ #104 │ David Mas              │ david@correu.cat         │ 644556677  │  ACTIU   │
╰──────┴────────────────────────┴──────────────────────────┴────────────┴──────────╯
  Total de clients registrats: 4 / 50

➤ Cercar client per ID, nom, correu o telèfon (INTRO per a tornar):
```

### Cerca
- **Coincidència exacta** amb l'ID (`101` o `#101`), el nom complet, el correu (sense distingir majúscules) o el telèfon: s'obri la **fitxa**.
- **Sense coincidència exacta:** es mostren els clients que contenen el text en qualsevol camp. Per exemple, `622` troba el client amb el telèfon `622334455`. Després es pregunta si vols donar d'alta un client nou amb aquest nom.

### Fitxa del client
```text
✦ FITXA DEL CLIENT ✦
  ID        #101
  Nom       Anna Garcia
  Correu    anna@correu.cat
  Telèfon   611223344
  Estat      ACTIU

✦ HISTORIAL DE COMPRES DE ANNA GARCIA ✦

╭──────────┬────────────────────┬──────────────────────┬───────────┬────────────┬─────────────╮
│ ID VENDA │ CLIENT             │ ARTICLE              │ QUANTITAT │ DATA       │ TOTAL LIQ.  │
├──────────┼────────────────────┼──────────────────────┼───────────┼────────────┼─────────────┤
│ #1001    │ Anna Garcia        │ Portatil 15 polzades │ 1 un.     │ 2026-10-15 │    749,99 € │
╰──────────┴────────────────────┴──────────────────────┴───────────┴────────────┴─────────────╯
  1 comandes · 1 unitats · Facturació: 749,99 €

➤ Tria una acció [M: Modificar dades, E: Donar de baixa, INTRO: Tornar]:
```

| Acció | Què fa |
| :--- | :--- |
| `M` | Modifica el nom, el correu i el telèfon (CU-04). |
| `E` | Dona de baixa el client (CU-05). |
| `INTRO` | Torna al directori. |

### Regles de les dades d'un client
- **Nom:** almenys 3 caràcters.
- **Correu:** ha de contindre `@` i `.`. Es guarda en minúscules.
- **Telèfon:** text lliure.
- **Identificador:** l'assigna l'aplicació (`#101`, `#102`...).

---

## 6. Mòdul de catàleg i estoc

Opció `2`. Es mostra directament el catàleg:

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

### Com es llig l'estoc
- **Barra:** 10 caselles. Cada casella plena són 3 unitats, i amb 30 unitats o més la barra és plena. És **roja** sense estoc, **groga** amb menys de 10 unitats i **verda** amb 10 o més.
- **Etiqueta de disponibilitat:**

| Etiqueta | Unitats |
| :--- | :--- |
| 🟢 `EN ESTOC` | Més de 10 |
| 🟡 `BAIX` | D'1 a 10 |
| 🔴 `ESGOTAT` | 0 |

### Cerca
- **Coincidència exacta** amb l'ID (`1`, `01` o `#1`) o el nom complet: s'obri la **fitxa** de l'article.
- **Sense coincidència exacta:** es mostren els articles que contenen el text en l'ID, el nom o la categoria. Per exemple, `Accessoris` mostra el ratolí i el teclat. Després es pregunta si vols donar d'alta un article nou amb aquest nom.

### Fitxa de l'article
Mostra l'ID, el nom, la categoria, el preu i l'estoc, i l'historial de vendes de l'article.

```text
➤ Tria una acció [M: Modificar dades, R: Reposar estoc, E: Eliminar del catàleg, INTRO: Tornar]:
```

| Acció | Què fa |
| :--- | :--- |
| `M` | Modifica el nom, la categoria, el preu i l'estoc (CU-08). |
| `R` | Suma unitats a l'estoc (CU-09). |
| `E` | Elimina l'article del catàleg (CU-10). |
| `INTRO` | Torna al catàleg. |

### Regles de les dades d'un article
- **Nom:** almenys 2 caràcters.
- **Categoria:** text lliure. Si es deixa buida en l'alta, es posa `General`.
- **Preu:** número més gran que 0. El decimal s'escriu amb punt, per exemple `29.99`.
- **Estoc:** enter igual o més gran que 0.

---

## 7. Mòdul de vendes

Opció `3`. Es mostren les estadístiques i l'historial complet:

```text
╭────────────────────╮  ╭────────────────────╮  ╭────────────────────╮  ╭────────────────────╮
│ 💳 TOTAL FACTURAT  │  │ 📈 MITJANA/VENDA   │  │ 🏆 VENDA MÀXIMA    │  │ 📉 VENDA MÍNIMA    │
│          799,49 €  │  │          399,75 €  │  │          749,99 €  │  │           49,50 €  │
╰────────────────────╯  ╰────────────────────╯  ╰────────────────────╯  ╰────────────────────╯

✦ HISTORIAL COMPLET DE VENDES ✦

╭──────────┬────────────────────┬──────────────────────┬───────────┬────────────┬─────────────╮
│ ID VENDA │ CLIENT             │ ARTICLE              │ QUANTITAT │ DATA       │ TOTAL LIQ.  │
├──────────┼────────────────────┼──────────────────────┼───────────┼────────────┼─────────────┤
│ #1001    │ Anna Garcia        │ Portatil 15 polzades │ 1 un.     │ 2026-10-15 │    749,99 € │
│ #1002    │ Carla Vidal        │ Teclat mecanic       │ 1 un.     │ 2026-10-20 │     49,50 € │
╰──────────┴────────────────────┴──────────────────────┴───────────┴────────────┴─────────────╯
  2 comandes · 2 unitats · Facturació: 799,49 €

➤ Tria una acció [B: Cercar venda, R: Registrar nova comanda, INTRO: Tornar]:
```

| Acció | Què fa |
| :--- | :--- |
| `B` | Demana un text i mostra les vendes que el contenen (CU-12). |
| `R` | Registra una venda nova (CU-13). |
| Qualsevol altre text | Es fa servir directament com a text de cerca. Per exemple, escriure `Anna` és com fer `B` i després `Anna`. |
| `INTRO` | Torna al menú principal. |

### Registrar una venda pas a pas
1. Es mostra la llista de clients. Escriu l'**ID numèric** del client, per exemple `101`.
2. Es mostra el catàleg. Escriu l'**ID numèric** de l'article, per exemple `2`.
3. Escriu les unitats, per exemple `3`.
4. L'aplicació descompta l'estoc, guarda la venda i imprimeix el tiquet:

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
  [Informació interna: Estoc restant de Ratoli sense fils: 22 un.]
```

- El **total** és el preu unitari × les unitats. No hi ha descomptes.
- El **codi de transacció** acaba amb un número aleatori de 4 xifres.

---

## 8. Desar les dades i eixir

- **`4` · Guardar dades:** escriu tots els clients, articles i vendes al fitxer `projecte/dades_botiga.txt`. La pròxima vegada que s'inicie l'aplicació, es carregaran.
- **`5` · Eixir:** tanca l'aplicació **sense desar**.

---

## 9. Limitacions conegudes

És una demo didàctica i té limitacions conscients. Algunes són bones candidates per a tasques de l'alumnat:

| Limitació | Conseqüència |
| :--- | :--- |
| En **registrar una venda**, els IDs i les unitats han de ser números. | Si s'escriu text, l'aplicació es tanca amb un error `NumberFormatException`. |
| Un client **de baixa** no té cap bloqueig. | Encara se li poden registrar vendes. |
| La **data** de les vendes noves és fixa. | Sempre és `2026-10-22`. |
| Hi ha un **màxim de 50** clients, 50 articles i 50 vendes. | En arribar al límit, l'aplicació no deixa afegir-ne més. |
| No hi ha **desat automàtic**. | Cal fer servir l'opció `4` abans d'eixir. |
