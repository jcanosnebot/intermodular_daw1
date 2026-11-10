# Tasca 6 · Completar Modificació, Reposició d'Estoc, Baixa i Alta de Productes

* **Etapa:** Etapa 2 (Nivell Intermedi + Conflicte Controlat)
* **Tipus de tasca:** Completar codi font incomplet (`TODO`), documentar API i resoldre conflicte Git amb la Tasca 5
* **Branca de treball:** `feature/6-gestio-estoc-altes`
* **Fitxers afectats:** `projecte/Main.java` i `projecte/docs/api/products.md`

---

## 1. Objectiu de la Tasca

Implementar la segona meitat del **Mòdul de Productes i Control d'Estoc** completant els 4 punts marcats amb `// TODO` dins dels mètodes d'operació sobre el catàleg:
1. `modificarProducte(int idx)`: Actualització validada de nom, categoria, preu (`> 0`) i estoc (`>= 0`).
2. `reposarEstoc(int idx)`: Increment d'unitats disponibles en magatzem (`producteEstoc[idx] += quantitat`).
3. `eliminarProducte(int idx)`: **Eliminació física** amb desplaçament dels elements posteriors cap a l'esquerra i decrement de `producteCount`.
4. `donarAltaProducte(String nom)`: Registre d'article nou calculant l'ID amb `Math.max` + 1.
5. Documentar aquests 4 mètodes en `projecte/docs/api/products.md` i **resoldre el conflicte d'integració** amb la Tasca 5.

---

## 2. Codi Incomplet a Completar en `projecte/Main.java`

### Pas 1: Crea la teua branca a partir de `main` (abans que es fusione la Tasca 5)
```bash
git switch main
git pull origin main
git switch -c feature/6-gestio-estoc-altes
```

### Pas 2: Implementa els 4 mètodes d'operació en `projecte/Main.java`
A la secció `// 2. MÒDUL DE PRODUCTES I ESTOC`, afegeix els mètodes següents i **completa els 4 blocs marcats amb `// TODO`**:

```java
void modificarProducte(int idx) {
    titol("MODIFICACIÓ DE L'ARTICLE #" + producteIds[idx]);
    nota("Prem INTRO sense escriure res per a mantindre el valor actual");

    pregunta("Nou nom [" + producteNoms[idx] + "]");
    String nom = scanner.nextLine().trim();
    if (nom.length() >= 2) producteNoms[idx] = nom;
    else if (!nom.isEmpty()) error("El nom ha de contindre almenys 2 caràcters. Es manté l'anterior.");

    pregunta("Nova categoria [" + producteCategories[idx] + "]");
    String categoria = scanner.nextLine().trim();
    if (!categoria.isEmpty()) producteCategories[idx] = categoria;

    try {
        pregunta("Nou preu en € [" + "%.2f".formatted(productePreus[idx]) + "]");
        String textPreu = scanner.nextLine().trim();
        if (!textPreu.isEmpty()) {
            double preu = Double.parseDouble(textPreu);
            // TODO (1): Si preu > 0 actualitza productePreus[idx] = preu;
            // en cas contrari mostra error("El preu ha de ser superior a zero. Es manté l'anterior.");
        }

        pregunta("Nou estoc en unitats [" + producteEstoc[idx] + "]");
        String textEstoc = scanner.nextLine().trim();
        if (!textEstoc.isEmpty()) {
            int estoc = Integer.parseInt(textEstoc);
            if (estoc >= 0) producteEstoc[idx] = estoc;
            else error("L'estoc no pot ser negatiu. Es manté l'anterior.");
        }
    } catch (Exception e) {
        error("Format numèric invàlid. Es manté el valor anterior.");
    }

    ok("Dades de l'article #" + producteIds[idx] + " actualitzades correctament.");
}

void reposarEstoc(int idx) {
    pregunta("Unitats a afegir a '" + producteNoms[idx] + "' (estoc actual: " + producteEstoc[idx] + ")");
    try {
        int quantitat = Integer.parseInt(scanner.nextLine().trim());
        // TODO (2): Si quantitat > 0, suma 'quantitat' a producteEstoc[idx] i mostra:
        // ok("Estoc actualitzat. L'article '" + producteNoms[idx] + "' disposa ara de " + producteEstoc[idx] + " unitats.");
        // Si no, mostra error("La quantitat ha de ser superior a zero.");
    } catch (Exception e) {
        error("Format numèric invàlid.");
    }
}

void eliminarProducte(int idx) {
    int vendesProducte = 0;
    for (int i = 0; i < vendaCount; i++) {
        if (vendaProducteIds[i] == producteIds[idx]) vendesProducte++;
    }
    if (vendesProducte > 0) avis("Aquest article té " + vendesProducte + " comandes registrades a l'historial.");

    pregunta("Confirmes que vols eliminar del catàleg '" + producteNoms[idx] + "'? [s/N]");
    String confirmacio = scanner.nextLine().trim().toUpperCase();
    if (!confirmacio.startsWith("S")) { avis("Operació cancel·lada. No s'han fet canvis."); return; }

    ok("Producte #" + producteIds[idx] + " ('" + producteNoms[idx] + "') eliminat correctament del catàleg.");
    // TODO (3): Desplaça una posició cap a l'esquerra tots els productes des d'i = idx fins a producteCount - 2
    // (producteIds, producteNoms, producteCategories, productePreus i producteEstoc)
    // i després decrementa producteCount--;
}

void donarAltaProducte(String nom) {
    if (producteCount >= maxProductes) { error("S'ha assolit la capacitat màxima d'articles (" + maxProductes + ")."); return; }

    titol("REGISTRE DE NOU PRODUCTE");
    pregunta("Nom complet del producte [" + nom + "]");
    String nouNom = scanner.nextLine().trim();
    if (!nouNom.isEmpty()) nom = nouNom;
    if (nom.length() < 2) { error("El nom ha de contindre almenys 2 caràcters."); return; }

    pregunta("Categoria comercial (ex: Ordinadors, Audio, Accessoris)");
    String categoria = scanner.nextLine().trim();
    if (categoria.isEmpty()) categoria = "General";

    try {
        pregunta("Preu unitari en € (ex: 29.99)");
        double preu = Double.parseDouble(scanner.nextLine().trim());
        if (preu <= 0) { error("El preu ha de ser superior a zero."); return; }

        pregunta("Estoc inicial en unitats (ex: 10)");
        int estoc = Integer.parseInt(scanner.nextLine().trim());
        if (estoc < 0) { error("L'estoc no pot ser negatiu."); return; }

        // TODO (4): Calcula el nou identificador buscant el màxim dels existents amb Math.max(nouId, producteIds[i])
        // i crida a afegirProducte(nouId + 1, nom, categoria, preu, estoc);
        int nouId = 0;

        ok("Producte registrat correctament al catàleg amb l'identificador #" + (nouId + 1));
    } catch (Exception e) {
        error("Format numèric invàlid.");
    }
}
```

### Pistes per als 4 `TODO`:
* **`TODO (1)`:**
  ```java
  if (preu > 0) productePreus[idx] = preu;
  else error("El preu ha de ser superior a zero. Es manté l'anterior.");
  ```
* **`TODO (2)`:**
  ```java
  if (quantitat > 0) {
      producteEstoc[idx] += quantitat;
      ok("Estoc actualitzat. L'article '" + producteNoms[idx] + "' disposa ara de " + producteEstoc[idx] + " unitats.");
  } else {
      error("La quantitat ha de ser superior a zero.");
  }
  ```
* **`TODO (3)`:**
  ```java
  for (int i = idx; i < producteCount - 1; i++) {
      producteIds[i] = producteIds[i + 1];
      producteNoms[i] = producteNoms[i + 1];
      producteCategories[i] = producteCategories[i + 1];
      productePreus[i] = productePreus[i + 1];
      producteEstoc[i] = producteEstoc[i + 1];
  }
  producteCount--;
  ```
* **`TODO (4)`:**
  ```java
  int nouId = 0;
  for (int i = 0; i < producteCount; i++) nouId = Math.max(nouId, producteIds[i]);
  afegirProducte(nouId + 1, nom, categoria, preu, estoc);
  ```

---

## 3. Documentació i Resolució del Conflicte amb la Tasca 5

1. En `projecte/docs/api/products.md`, documenta els 4 mètodes d'operació (`modificarProducte`, `reposarEstoc`, `eliminarProducte` i `donarAltaProducte`).
2. Quan la *Pull Request* de la **Tasca 5** s'haja fusionat a `main`, executa a la teua branca:
   ```bash
   git pull origin main
   ```
3. Resol el conflicte en `projecte/Main.java` i `projecte/docs/api/products.md`:
   * En `Main.java`: conserva els mètodes `menuProductes`, `llistarProductes` i `gestionarProducte` de la Tasca 5 i tot seguit els teus 4 mètodes complets (`modificarProducte`, `reposarEstoc`, `eliminarProducte`, `donarAltaProducte`), esborrant els 4 mètodes provisionals d'una línia que havia posat la Tasca 5.
   * En `docs/api/products.md`: uneix la documentació dels 3 mètodes de consulta amb la dels 4 mètodes d'operació.
4. Comprova que tot funciona amb la prova següent (reposa 5 unitats a l'article `#04` i dona d'alta una `Webcam FullHD`):
   ```bash
   printf "2\n04\nR\n5\nWebcam FullHD\ns\n\nAccessoris\n34.90\n12\n\n5\n" | java projecte/Main.java
   ```

---

## 4. Criteris d'Acceptació (*Definition of Done*)

- [ ] Els 4 `TODO` estan implementats i totes les accions sobre articles (`M`, `R`, `E` i alta) funcionen correctament.
- [ ] El conflicte d'integració amb la Tasca 5 s'ha resolt netament sense marques `<<<<<<<` residuals.
- [ ] `projecte/docs/api/products.md` inclou els 7 mètodes del mòdul de productes.
- [ ] S'ha fet el *commit* de resolució, *push* i s'ha fusionat la *Pull Request* vinculada a la *Issue* #6.
