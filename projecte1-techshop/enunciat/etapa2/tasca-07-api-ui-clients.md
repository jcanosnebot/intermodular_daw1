# Tasca 7 · Documentació de l'API Interna d'Interfície, Clients i Vectors

* **Etapa:** Etapa 2 (Nivell Intermedi)
* **Tipus de tasca:** Documentació tècnica d'API interna en Markdown
* **Branca de treball:** `docs/7-api-ui-clients`
* **Fitxers a crear:**
  * `projecte/docs/api/README.md`
  * `projecte/docs/api/ui.md`
  * `projecte/docs/api/customers.md`
  * `projecte/docs/api/data.md`

---

## 1. Objectiu de la Tasca

Inspeccionar el codi font de `projecte/Main.java` i documentar de forma estructurada els mètodes de la capa d'interfície, el mòdul de clients i els mètodes auxiliars de cerca i alta als vectors dins del directori `projecte/docs/api/`.

---

## 2. Instruccions Pas a Pas

### Pas 1: Crea la teua branca
```bash
git switch main
git pull origin main
git switch -c docs/7-api-ui-clients
```

### Pas 2: Crea `projecte/docs/api/README.md`
Documenta l'índex general de mètodes de `Main.java` incloent:
1. **Convencions del codi:**
   * Tots els mètodes són `void`, excepte `cercarIndexClientPerId(int id)` i `cercarIndexProductePerId(int id)` que retornen `int` (`-1` si no existeix).
   * Els mètodes d'interfície són els **únics** que fan servir codis de color ANSI.
   * `pregunta(String text)` i `opcio(String text)` només imprimeixen el missatge; la resposta es llig amb `scanner.nextLine().trim()`.
2. **Taula resum de mètodes** agrupats per mòdul amb enllaços a `ui.md`, `customers.md`, `products.md`, `sales.md` i `data.md`.
3. Especificació dels 4 mètodes del bucle principal: `main()`, `mostrarCapcaleraPrincipal()`, `mostrarResumGeneral()` i `mostrarMenuPrincipal()`.

### Pas 3: Crea `projecte/docs/api/ui.md`
Documenta la secció `MÈTODES D'INTERFÍCIE` de `Main.java`:
1. **Taula de la paleta de colors ANSI** (`RST`, `BLD`, `DIM`, `BCK`, `RED`, `GRN`, `YLW`, `BLU`, `PUR`, `CYN`, `WHT`, `EMP`, `BRED`, `BGRN`, `BYLW`).
2. **Taula d'etiquetes d'estat (*badges*)** (`BADGE_ACTIU`, `BADGE_BAIXA`, `BADGE_EN_ESTOC`, `BADGE_BAIX`, `BADGE_ESGOTAT`).
3. **Mètodes de text i missatges:** `titol`, `nota`, `ok`, `error`, `avis`, `pregunta`, `opcio`, `dada` i `capcalera(String color, String text)` amb un exemple de com es veuen en pantalla.
4. **Mètodes d'estructures visuals:** `mostrarTargetes(String[] titols, String[] valors)` i `mostrarTaula(String[] capcaleres, int[] amplades, String[][] files, int numFiles)` explicant com es calcula l'amplada visible amb `.replaceAll("\u001B\\[[;\\d]*m", "").length()`.

### Pas 4: Crea `projecte/docs/api/customers.md`
Documenta el mòdul `1. MÒDUL DE CLIENTS`:
1. Taula dels vectors de clients (`clientIds`, `clientNoms`, `clientEmails`, `clientTelefons`, `clientActius`, `clientCount`, `maxClients`).
2. Especificació dels 6 mètodes:
   * `void menuClients()` (cerca exacta per ID, `#ID`, nom, correu o telèfon; si no hi ha coincidència exacta, mostra coincidències parcials i ofereix donar d'alta).
   * `void llistarClients(String filtre)`
   * `void gestionarClient(int idx)`
   * `void modificarClient(int idx)` (validacions de nom `>= 3` caràcters i correu amb `@` i `.`).
   * `void eliminarClient(int idx)` (baixa lògica: `clientActius[idx] = false`).
   * `void donarAltaClient(String nom)` (ID `101 + clientCount`).

### Pas 5: Crea `projecte/docs/api/data.md`
Documenta els mètodes auxiliars de dades:
1. **Cerca per identificador:** `int cercarIndexClientPerId(int id)` i `int cercarIndexProductePerId(int id)`.
2. **Alta als vectors:** `void afegirClient(...)`, `void afegirProducte(...)` i `void afegirVenda(...)`.
3. **Dades de demostració:** `void inicialitzarDadesBase()`.
*(Nota: La documentació de `guardarDadesFitxer` i `carregarDadesFitxer` s'ampliarà a l'Etapa 4).*

---

## 3. Criteris d'Acceptació (*Definition of Done*)

- [ ] Existeixen els 4 fitxers (`README.md`, `ui.md`, `customers.md` i `data.md`) dins de `projecte/docs/api/`.
- [ ] Les signatures dels mètodes i noms de constants coincideixen al 100% amb `projecte/Main.java`.
- [ ] Tots els enllaços relatius entre els fitxers de `docs/api/` funcionen.
- [ ] S'ha fet *commit* (`docs(api): documenta els metodes d'interficie, clients i vectors`), *push* i obert la *Pull Request* vinculada a la *Issue* #7.
