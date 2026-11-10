# Tasca 5 · Completar Consulta, Cerca i Barra d'Estoc del Catàleg de Productes

* **Etapa:** Etapa 2 (Nivell Intermedi)
* **Tipus de tasca:** Completar codi font incomplet (`TODO`) i documentar API
* **Branca de treball:** `feature/5-cataleg-productes`
* **Fitxers afectats:** `projecte/Main.java` i `projecte/docs/api/products.md`

---

## 1. Objectiu de la Tasca

Implementar la primera meitat del **Mòdul de Productes i Control d'Estoc** completant els 4 punts marcats amb `// TODO` dins dels mètodes:
1. `menuProductes()`: Bucle interactiu del catàleg i cerca de coincidència exacta per nom o per identificador (`1`, `01` o `#1`).
2. `llistarProductes(String filtre)`: Construcció de la taula amb la **barra visual de nivell d'estoc** (`[████░░░░░░]`) i l'etiqueta de disponibilitat (`EN ESTOC`, `BAIX` o `ESGOTAT`).
3. `gestionarProducte(int idx)`: Fitxa detallada de l'article, historial de vendes i menú d'accions (`[M]`, `[R]`, `[E]`).
4. Documentar aquests 3 mètodes en `projecte/docs/api/products.md`.

---

## 2. Codi Incomplet a Completar en `projecte/Main.java`

### Pas 1: Crea la teua branca a partir de `main`
```bash
git switch main
git pull origin main
git switch -c feature/5-cataleg-productes
```

### Pas 2: Substitueix el bloc provisional `// 2. MÒDUL DE PRODUCTES I ESTOC`
En `projecte/Main.java`, substitueix el `menuProductes()` provisional per la plantilla següent i **completa els 4 blocs marcats amb `// TODO`**:

```java
// ----------------------------------------------------------------------------
// 2. MÒDUL DE PRODUCTES I ESTOC
// ----------------------------------------------------------------------------

void menuProductes() {
    while (true) {
        capcalera(GRN, "📦  MÒDUL DE PRODUCTES I CONTROL D'ESTOC");
        llistarProductes("");
        nota("Total d'articles al catàleg: " + producteCount + " / " + maxProductes);

        opcio("Cercar article per ID, nom o categoria (INTRO per a tornar)");
        String entrada = scanner.nextLine().trim();
        if (entrada.isEmpty()) return;

        // Coincidència exacta pel nom o per l'identificador (1, 01 o #1)
        int idx = -1;
        for (int i = 0; i < producteCount && idx == -1; i++) {
            // TODO (1): Comprova si 'entrada' coincideix amb producteNoms[i] (ignorant majúscules),
            // o amb "" + producteIds[i], o amb "#" + producteIds[i], o amb "%02d".formatted(producteIds[i]).
            // Si coincideix, assigna idx = i;
        }

        if (idx != -1) {
            gestionarProducte(idx);
        } else {
            titol("COINCIDÈNCIES PARCIALS AMB '" + entrada + "'");
            llistarProductes(entrada);
            avis("Cap article coincideix exactament amb '" + entrada + "'.");
            pregunta("Vols donar d'alta un nou article amb aquest nom? [s/N]");
            String confirmacio = scanner.nextLine().trim().toUpperCase();
            if (confirmacio.startsWith("S")) donarAltaProducte(entrada);
        }
    }
}

// Mostra els productes que contenen el text 'filtre' (ID, nom o categoria). "" = tots
void llistarProductes(String filtre) {
    String[][] files = new String[producteCount][];
    int n = 0;
    for (int i = 0; i < producteCount; i++) {
        String id = "%02d".formatted(producteIds[i]);
        String text = "#" + producteIds[i] + " " + id + " " + producteNoms[i] + " " + producteCategories[i];
        if (text.toLowerCase().contains(filtre.toLowerCase())) {
            int estoc = producteEstoc[i];
            // TODO (2): Calcula el nombre de blocs plens de la barra (de 0 a 10) usant Math.min(10, estoc / 3)
            int plens = 0;
            String colorBarra = estoc == 0 ? RED : estoc < 10 ? YLW : GRN;
            String barra = colorBarra + "[" + "█".repeat(plens) + "░".repeat(10 - plens) + "]" + RST + " %2d un.".formatted(estoc);

            // TODO (3): Selecciona el badge de disponibilitat segons l'estoc:
            // Si estoc > 10 -> BADGE_EN_ESTOC; si estoc > 0 -> BADGE_BAIX; sinó -> BADGE_ESGOTAT
            String disponibilitat = BADGE_EN_ESTOC;

            files[n] = new String[]{id, producteNoms[i], producteCategories[i], "%8.2f €".formatted(productePreus[i]), barra, disponibilitat};
            n++;
        }
    }
    mostrarTaula(new String[]{"ID", "NOM PRODUCTE", "CATEGORIA", "PREU UNIT.", "NIVELL D'ESTOC", "DISPONIBILITAT"},
                 new int[]{4, 25, 14, 12, 21, 16}, files, n);
}

void gestionarProducte(int idx) {
    titol("FITXA DE L'ARTICLE");
    dada("ID", "#" + producteIds[idx]);
    dada("Nom", producteNoms[idx]);
    dada("Categoria", producteCategories[idx]);
    dada("Preu", "%.2f €".formatted(productePreus[idx]));
    dada("Estoc", producteEstoc[idx] + " un.");

    titol("HISTORIAL DE VENDES DE '" + producteNoms[idx].toUpperCase() + "'");
    llistarVendes(-1, producteIds[idx], "");

    opcio("Tria una acció [M: Modificar dades, R: Reposar estoc, E: Eliminar del catàleg, INTRO: Tornar]");
    String accio = scanner.nextLine().trim().toUpperCase();
    // TODO (4): Completa el switch(accio) perquè:
    // - "M" cride a modificarProducte(idx)
    // - "R" cride a reposarEstoc(idx)
    // - "E" cride a eliminarProducte(idx)
    // - "" no faça res
    // - default mostre avis("Acció no reconeguda. Tornant al catàleg de productes.")
}

// Mètodes provisionals que completarà la Tasca 6:
void modificarProducte(int idx) { avis("Pendent de la Tasca 6."); }
void reposarEstoc(int idx)      { avis("Pendent de la Tasca 6."); }
void eliminarProducte(int idx)   { avis("Pendent de la Tasca 6."); }
void donarAltaProducte(String nom) { avis("Pendent de la Tasca 6."); }
```

### Pistes per als 4 `TODO`:
* **`TODO (1)`:**
  ```java
  if (entrada.equalsIgnoreCase(producteNoms[i]) || entrada.equals("" + producteIds[i])
          || entrada.equals("#" + producteIds[i]) || entrada.equals("%02d".formatted(producteIds[i]))) {
      idx = i;
  }
  ```
* **`TODO (2)`:**
  ```java
  int plens = Math.min(10, estoc / 3);   // Barra de 10 caselles (30 unitats = plena)
  ```
* **`TODO (3)`:**
  ```java
  String disponibilitat = estoc > 10 ? BADGE_EN_ESTOC : estoc > 0 ? BADGE_BAIX : BADGE_ESGOTAT;
  ```
* **`TODO (4)`:**
  ```java
  switch (accio) {
      case "M" -> modificarProducte(idx);
      case "R" -> reposarEstoc(idx);
      case "E" -> eliminarProducte(idx);
      case "" -> { }
      default -> avis("Acció no reconeguda. Tornant al catàleg de productes.");
  }
  ```

---

## 3. Documentació a Crear en `projecte/docs/api/products.md`

Crea el fitxer `projecte/docs/api/products.md` i documenta:
1. La taula de vectors del mòdul (`producteIds`, `producteNoms`, `producteCategories`, `productePreus`, `producteEstoc`, `producteCount`).
2. L'especificació de `void menuProductes()`, `void llistarProductes(String filtre)` (incloent la taula de trams d'estoc: `> 10` verd `EN ESTOC`, `1..10` groc `BAIX`, `0` roig `ESGOTAT`) i `void gestionarProducte(int idx)`.

---

## 4. Verificació i Proves

Comprova que el catàleg mostra les barres d'estoc, filtra per categoria (`Accessoris`) i obri la fitxa de l'article `04`:

```bash
printf "2\n04\n\nAccessoris\nn\n\n5\n" | java projecte/Main.java
```

---

## 5. Criteris d'Acceptació (*Definition of Done*)

- [ ] Els 4 `TODO` estan implementats i `menuProductes`, `llistarProductes` i `gestionarProducte` funcionen sense errors.
- [ ] L'article `#04` (8 unitats) apareix amb barra groga i etiqueta `BAIX`, i la resta amb barra verda i `EN ESTOC`.
- [ ] `projecte/docs/api/products.md` documenta els vectors i els 3 mètodes de consulta.
- [ ] S'ha fet *commit* (`feat(products): implementa consulta, cerca i barra d'estoc del cataleg`), *push* i obert la *Pull Request* vinculada a la *Issue* #5.
