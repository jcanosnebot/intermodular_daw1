# Especificació Funcional i Tècnica · Persistència, Qualitat i Entrega Final (Etapa 4)

Aquest document recull tots els requisits per a les últimes **4 tasques del projecte (Tasques 13 a 16)** que l'equip ha de planificar, implementar, revisar i lliurar en l'**Etapa 4**.

---

## Bloc 1 · Persistència en Fitxer amb `Java NIO` (Tasca 13)

### 1.1. Mètodes a implementar en `projecte/Main.java`
Substituir els mètodes provisionals de la secció `// PERSISTÈNCIA EN FITXERS` per la implementació definitiva utilitzant exclusivament `Files.writeString` i `Files.readString`:

```java
// ----------------------------------------------------------------------------
// PERSISTÈNCIA EN FITXERS AMB Files.writeString I Files.readString
// ----------------------------------------------------------------------------

void guardarDadesFitxer() throws Exception {
    String contingut = "# DADES DE LA BOTIGA EN LINIA\n[CLIENTS]\n";
    for (int i = 0; i < clientCount; i++) {
        contingut += clientIds[i] + ";" + clientNoms[i] + ";" + clientEmails[i] + ";" + clientTelefons[i] + ";" + clientActius[i] + "\n";
    }
    contingut += "[PRODUCTES]\n";
    for (int i = 0; i < producteCount; i++) {
        contingut += producteIds[i] + ";" + producteNoms[i] + ";" + producteCategories[i] + ";" + productePreus[i] + ";" + producteEstoc[i] + "\n";
    }
    contingut += "[VENDES]\n";
    for (int i = 0; i < vendaCount; i++) {
        contingut += vendaIds[i] + ";" + vendaClientIds[i] + ";" + vendaProducteIds[i] + ";" + vendaQuantitats[i] + ";" + vendaTotals[i] + ";" + vendaDates[i] + "\n";
    }

    Files.writeString(Path.of(rutaFitxerDades), contingut);
    ok("Totes les dades s'han desat correctament al fitxer físic: " + rutaFitxerDades);
}

void carregarDadesFitxer() {
    try {
        String[] linies = Files.readString(Path.of(rutaFitxerDades)).split("\n");
        String seccio = "";

        for (int i = 0; i < linies.length; i++) {
            String linia = linies[i].trim();
            if (linia.startsWith("[")) {
                // Si el fitxer conté la secció, substitueix les dades de demostració
                seccio = linia;
                if (seccio.equals("[CLIENTS]")) clientCount = 0;
                if (seccio.equals("[PRODUCTES]")) producteCount = 0;
                if (seccio.equals("[VENDES]")) vendaCount = 0;
            } else if (!linia.isEmpty() && !linia.startsWith("#")) {
                String[] c = linia.split(";");
                switch (seccio) {
                    case "[CLIENTS]" -> afegirClient(Integer.parseInt(c[0]), c[1], c[2], c[3], Boolean.parseBoolean(c[4]));
                    case "[PRODUCTES]" -> afegirProducte(Integer.parseInt(c[0]), c[1], c[2], Double.parseDouble(c[3]), Integer.parseInt(c[4]));
                    case "[VENDES]" -> afegirVenda(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]),
                                                   Integer.parseInt(c[3]), Double.parseDouble(c[4]), c[5]);
                }
            }
        }
    } catch (Exception e) {
        // Si el fitxer no existeix, es mantenen les dades de demostració
    }
}
```

### 1.2. Fitxer inicial `projecte/dades_botiga.txt`
Crear `projecte/dades_botiga.txt` amb el format de seccions (`[CLIENTS]`, `[PRODUCTES]`, `[VENDES]`) i camps separats per punt i coma (`;`) amb punt decimal (`.`) en els imports:

```ini
# DADES DE LA BOTIGA EN LINIA
[CLIENTS]
101;Anna Garcia;anna@correu.cat;611223344;true
102;Bernat Lopez;bernat@correu.cat;622334455;true
103;Carla Vidal;carla@correu.cat;633445566;true
104;David Mas;david@correu.cat;644556677;true
[PRODUCTES]
1;Portatil 15 polzades;Ordinadors;749.99;10
2;Ratoli sense fils;Accessoris;19.95;25
3;Teclat mecanic;Accessoris;49.5;15
4;Monitor 24 4K;Monitors;189.0;8
5;Auriculars Bluetooth;Audio;39.9;30
[VENDES]
1001;101;1;1;749.99;2026-10-15
1002;103;3;1;49.5;2026-10-20
```

### 1.3. Actualització documental associada a Persistència
* En `projecte/docs/architecture/data-model.md`: afegir l'especificació del format de `dades_botiga.txt` i el comportament de càrrega/desat.
* En `projecte/docs/api/data.md`: documentar `guardarDadesFitxer()` i `carregarDadesFitxer()`.
* En `projecte/docs/user-guide.md`: afegir els casos d'ús **`CU-14`** (Guardar dades al fitxer amb l'opció `[4]`), **`CU-15`** (Eixir del sistema amb l'opció `[5]`) i la secció de limitacions conegudes.

---

## Bloc 2 · Guia de Contribució, Flux de Treball i Plantilles GitHub (Tasca 14)

### 2.1. `projecte/CONTRIBUTING.md` (Element obligatori 5)
Redactar la guia completa de col·laboració en l'equip incloent:
1. **Regles d'or de l'equip:** Prohibit fer *push* directe a `main`; tota tasca parteix d'una *Issue*; cap alumne aprova la seua pròpia PR; el codi ha de compilar amb `java projecte/Main.java`.
2. **Restriccions tècniques de `Main.java`:** Fitxer únic amb classe implícita Java 25, vectors paral·lels, aïllament estricte de colors ANSI dins dels mètodes d'interfície, mètodes `void` (excepte cerca d'índex per ID).
3. **Nomenclatura de branques:** `feature/<num>-<descripcio>`, `fix/<num>-<descripcio>`, `docs/<num>-<descripcio>`, `refactor/<num>-<descripcio>`.
4. **Format de missatges de *commit* (*Conventional Commits*):** `<tipus>(<àmbit>): <descripció en imperatiu>` (`feat`, `fix`, `docs`, `refactor`, `style`, `chore`).
5. **Llista de comprovació per a revisar una *Pull Request*** i taula de quins documents de `docs/` cal actualitzar segons el canvi realitzat.

### 2.2. `projecte/docs/development/workflow.md`
Crear la guia operativa del cicle de vida d'una tasca en **7 passos** (amb diagrama Mermaid del tauler Kanban `Backlog → Todo → In Progress → Review → Done` i guia pas a pas per a resoldre conflictes de fusió amb `git pull origin main`).

### 2.3. Plantilles de GitHub i `.gitignore`
* Crear `projecte/.gitignore` excloent `out/` i `*.class`.
* Actualitzar `projecte/.github/PULL_REQUEST_TEMPLATE.md`, `projecte/.github/ISSUE_TEMPLATE/bug.md` i `improvement.md` amb les ordres de verificació de Java 25 (`java projecte/Main.java`).

---

## Bloc 3 · Entorn de Desenvolupament i Pla de Proves (Tasca 15)

### 3.1. `projecte/docs/development/environment.md`
Documentar la configuració recomanada d'entorn per a l'equip:
* Execució per terminal des de l'arrel `intermodular/`.
* Configuració de **VS Code** (*Extension Pack for Java*, codificació UTF-8).
* Configuració d'**IntelliJ IDEA** (*Working directory* apuntant a l'arrel `intermodular/` perquè trobe `projecte/dades_botiga.txt`).
* Configuració bàsica de Git (`user.name`, `user.email`, `pull.rebase false`).

### 3.2. `projecte/docs/development/testing.md`
Documentar l'estratègia de verificació del projecte:
1. **5 proves ràpides amb canonades (`P1` a `P5`)** executables amb `printf "..." | java projecte/Main.java`:
   * `P1`: Fum i navegació pels 3 mòduls (`printf "1\n\n2\n\n3\n\n5\n" | java projecte/Main.java`).
   * `P2`: Cerca, alta, modificació i baixa lògica de client.
   * `P3`: Cerca, reposició d'estoc i alta d'article.
   * `P4`: Registre d'una venda amb emissió de tiquet i cerca.
   * `P5`: Guardat de dades en `projecte/dades_botiga.txt` (amb còpia de seguretat prèvia `cp`).
2. **Taula de 15 casos de prova manuals (`TC-01` a `TC-15`)** cobrint casos vàlids i casos d'error (nom massa curt, correu sense `@`, preu negatiu, estoc insuficient, etc.).

---

## Bloc 4 · Desplegament, Resolució de Problemes i Llançament (Tasca 16)

### 4.1. `projecte/docs/deployment.md`
Documentar com executar i mantindre l'aplicació a l'aula:
* Estructura de fitxers necessària en producció (`projecte/Main.java` i `projecte/dades_botiga.txt`).
* Cicle de vida de les dades en memòria i en disc (diagrama Mermaid de càrrega a l'inici i guardat amb `[4]`).
* Còpies de seguretat (`cp projecte/dades_botiga.txt ...`), restauració a l'estat de fàbrica (`rm projecte/dades_botiga.txt`) i scripts llançadors (`iniciar.sh` i `iniciar.bat`).

### 4.2. `projecte/docs/troubleshooting.md`
Documentar el diagnòstic i solució dels problemes més freqüents:
* Error d'execució des de la carpeta incorrecta (`cd projecte` en comptes de l'arrel `intermodular/`).
* Errors de versió de Java (`release version 21 not supported` o `unnamed classes are a preview feature` en usar JDK < 25).
* `NumberFormatException` en `registrarVenda()` si s'introdueix text en un camp numèric.
* Dades que es perden en tancar sense haver premut `[4] GUARDAR DADES`.
* Caràcters estranys o codis `\u001B[96m` visibles en terminals sense UTF-8/ANSI (`chcp 65001` en Windows).

### 4.3. `projecte/CHANGELOG.md`
Registrar les notes de la versió final del projecte seguint el format *Keep a Changelog* i *Semantic Versioning*.
