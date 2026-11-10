# Tasca 4 · Especificació de Tecnologies i Guia d'Instal·lació

* **Etapa:** Etapa 1 (Nivell Bàsic)
* **Tipus de tasca:** Documentació tècnica d'arquitectura, tecnologies i posada en marxa (Element obligatori 6: *Tecnologies utilitzades*)
* **Branca de treball:** `docs/4-tecnologies-installacio`
* **Fitxers a crear:** `projecte/docs/architecture/overview.md` i `projecte/docs/installation.md`

---

## 1. Objectiu de la Tasca

Documentar les decisions tecnològiques i didàctiques del projecte i redactar la guia d'instal·lació multiplataforma:
1. Crear `projecte/docs/architecture/overview.md` explicant l'estructura interna de `projecte/Main.java`, les 4 capes funcionals, les novetats de **Java 25** utilitzades, la paleta de colors ANSI i les restriccions didàctiques de 1r de FP.
2. Crear `projecte/docs/installation.md` amb els requisits i passos d'instal·lació i execució en **Linux**, **Windows** i **macOS**.

---

## 2. Instruccions Pas a Pas

### Pas 1: Crea i activa la teua branca
```bash
git switch main
git pull origin main
git switch -c docs/4-tecnologies-installacio
```

### Pas 2: Redacta `projecte/docs/architecture/overview.md`
Inclou les seccions següents:
1. **Visió general de l'arquitectura:**
   * Explicar que tota l'aplicació resideix en un únic fitxer (`projecte/Main.java`) utilitzant **classes declarades implícitament** (*Implicitly Declared Classes and Instance Main Methods*, JEP 512 de Java 25).
2. **Estructura interna de `Main.java` (taula dels 9 blocs del fitxer):**
   * `1` Capçalera i importacions (`Files`, `Path`, `Scanner`, `Random`, `static java.lang.IO.*`)
   * `2` Paleta de colors ANSI (`RST`, `BLD`, `DIM`, `BCK`, `RED`, `GRN`, `YLW`, `BLU`, `PUR`, `CYN`, `WHT`, `BRED`, `BGRN`, `BYLW`) i *badges* (`BADGE_ACTIU`, `BADGE_BAIXA`, `BADGE_EN_ESTOC`, `BADGE_BAIX`, `BADGE_ESGOTAT`)
   * `3` Estructures de dades en memòria (vectors paral·lels de capacitat `50`)
   * `4` Mètode principal i panell de control (`main`, `mostrarCapcaleraPrincipal`, `mostrarResumGeneral`, `mostrarMenuPrincipal`)
   * `5` Mètodes d'interfície (`titol`, `nota`, `ok`, `error`, `avis`, `pregunta`, `opcio`, `dada`, `capcalera`, `mostrarTargetes`, `mostrarTaula`)
   * `6` Mòdul de clients (`menuClients`, `llistarClients`, `gestionarClient`, `modificarClient`, `eliminarClient`, `donarAltaClient`)
   * `7` Mòdul de productes i estoc (`menuProductes`, `llistarProductes`, `gestionarProducte`, `modificarProducte`, `reposarEstoc`, `eliminarProducte`, `donarAltaProducte`)
   * `8` Mòdul de vendes (`menuVendes`, `mostrarEstadistiquesVendes`, `llistarVendes`, `cercarVendes`, `registrarVenda`, `imprimirTiquet`)
   * `9` Cerca per ID, alta als vectors, persistència i dades demo (`cercarIndex...`, `afegir...`, `guardarDadesFitxer`, `carregarDadesFitxer`, `inicialitzarDadesBase`)
3. **Decisions de disseny clau:**
   * **Aïllament dels colors ANSI:** Els codis `\u001B[...]` només s'utilitzen dins dels mètodes d'interfície (i `imprimirTiquet`/`llistarProductes` per a la barra). Això evita desquadraments en la resta del codi.
   * **Càlcul d'amplada visible en `mostrarTaula`:** Ús de `.replaceAll("\u001B\\[[;\\d]*m", "").length()` per a descomptar els codis invisibles de les etiquetes abans d'omplir amb espais.
   * **Lectura d'entrada:** `pregunta(text)` i `opcio(text)` només imprimeixen el missatge; la lectura es fa localment amb `scanner.nextLine().trim()`.
4. **Tecnologies utilitzades i Restriccions didàctiques:**
   * Taula amb: Java 25 LTS, `java.lang.IO`, `java.util.Scanner`, `java.nio.file.Files` i `Path`, `java.lang.Math` i `java.util.Random`, ANSI + Unicode, Git + GitHub i Markdown + Mermaid.
   * Taula dels elements del llenguatge permesos i prohibits (prohibit crear `class`, `ArrayList`, `HashMap`, *streams* o *lambdes*).

### Pas 3: Redacta `projecte/docs/installation.md`
Inclou les seccions següents:
1. **Requisits del sistema:**
   * **JDK 25 o superior** (imprescindible perquè les classes implícites i `java.lang.IO` són estàndard sense `--enable-preview`).
   * Terminal amb codificació **UTF-8** i suport ANSI, d'almenys **95 columnes** d'amplària.
2. **Comprovació i instal·lació de Java 25:**
   * Ordre `java --version`.
   * Instruccions amb **SDKMAN!** (Linux/macOS), **winget** (`winget install EclipseAdoptium.Temurin.25.JDK` en Windows) i **Homebrew** (`brew install --cask temurin@25` en macOS).
3. **Descàrrega i execució (sempre des de l'arrel `intermodular/`):**
   ```bash
   git clone <URL_DEL_REPOSITORI>
   cd intermodular
   java projecte/Main.java
   ```
   > Afegir una alerta `> [!IMPORTANT]` recordant que s'ha d'executar des de `intermodular/` i no des de dins de `projecte/` perquè la ruta relativa `"projecte/dades_botiga.txt"` funcione.

---

## 3. Criteris d'Acceptació (*Definition of Done*)

- [ ] `projecte/docs/architecture/overview.md` detalla els 9 blocs de `Main.java`, l'aïllament dels colors ANSI, les tecnologies emprades i les restriccions didàctiques.
- [ ] `projecte/docs/installation.md` explica com instal·lar Java 25 en Linux, Windows i macOS i com executar l'aplicació des de l'arrel del repositori.
- [ ] Tots els enllaços relatius entre documents funcionen correctament.
- [ ] S'ha fet *commit* (`docs(tech): afegeix especificacio de tecnologies i guia d'instal·lacio`), *push* i obert la *Pull Request* vinculada a la *Issue* #4.
