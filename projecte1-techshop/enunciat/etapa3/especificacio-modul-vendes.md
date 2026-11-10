# Especificació Funcional i Tècnica · Mòdul de Vendes i Facturació (Etapa 3)

Aquest document descriu tots els requisits de codi i de documentació que l'equip ha d'analitzar, descompondre en **4 subtasques (Tasques 9 a 12)** al tauler Kanban i implementar durant l'**Etapa 3**.

---

## Bloc A · Especificació de Codi en `projecte/Main.java`

Cal substituir els mètodes provisionals de la secció `// 3. MÒDUL DE VENDES` pels **6 mètodes definitius** següents:

### A.1. Bucle interactiu del mòdul: `void menuVendes()`
* **Comportament:**
  1. Dins d'un bucle `while (true)`, mostra la capçalera verda:
     `capcalera(GRN, "💳  MÒDUL DE VENDES I FACTURACIÓ");`
  2. Crida a `mostrarEstadistiquesVendes()`.
  3. Mostra el títol `titol("HISTORIAL COMPLET DE VENDES");` i crida a `llistarVendes(-1, -1, "")`.
  4. Mostra l'opció `opcio("Tria una acció [B: Cercar venda, R: Registrar nova comanda, INTRO: Tornar]");` i llig l'entrada amb `scanner.nextLine().trim()`.
  5. Amb un `switch (accio.toUpperCase())`:
     * `""` -> torna al menú principal (`return;`).
     * `"R"` -> crida a `registrarVenda()`.
     * `"B"` -> demana `opcio("Cercar per ID de venda, client, article o data");`, llig el terme i crida a `cercarVendes(terme)`.
     * `default` -> tracta directament el text escrit com a filtre cridant a `cercarVendes(accio)`.

### A.2. Targetes KPI financeres: `void mostrarEstadistiquesVendes()`
* **Comportament:**
  1. Si `vendaCount == 0`, mostra `avis("Encara no hi ha cap venda registrada.");` i retorna.
  2. Recorre el vector `vendaTotals` des de `0` fins a `vendaCount - 1` calculant:
     * `suma`: suma acumulada de totes les vendes.
     * `maxim`: venda d'import més alt usant `Math.max(maxim, vendaTotals[i])`.
     * `minim`: venda d'import més baix usant `Math.min(minim, vendaTotals[i])`.
  3. Invoca `mostrarTargetes(...)` amb les 4 targetes següents (formatades amb `"%.2f €".formatted(...)`):
     * `"💳 TOTAL FACTURAT"` -> `suma`
     * `"📈 MITJANA/VENDA"` -> `suma / vendaCount`
     * `"🏆 VENDA MÀXIMA"` -> `maxim`
     * `"📉 VENDA MÍNIMA"` -> `minim`

### A.3. Taula d'historial i filtratge: `void llistarVendes(int clientId, int producteId, String filtre)`
* **Paràmetres:**
  * `clientId`: ID del client a filtrar, o `-1` per a qualsevol client.
  * `producteId`: ID del producte a filtrar, o `-1` per a qualsevol producte.
  * `filtre`: cadena de text a buscar (ignorant majúscules/minúscules), o `""` per a mostrar-les totes.
* **Comportament:**
  1. Per a cada venda `i` (`0 .. vendaCount - 1`), busca l'índex del client `c = cercarIndexClientPerId(vendaClientIds[i])` i del producte `p = cercarIndexProductePerId(vendaProducteIds[i])`.
  2. Obté el nom del client (`c != -1 ? clientNoms[c] : "#" + vendaClientIds[i]`) i el nom de l'article (`p != -1 ? producteNoms[p] : "#" + vendaProducteIds[i]`).
  3. Construeix la cadena de cerca concatenant: `"#" + vendaIds[i] + " " + vendaClientIds[i] + " " + client + " " + vendaProducteIds[i] + " " + article + " " + vendaDates[i]`.
  4. Si compleix els tres filtres (`clientId`, `producteId` i `filtre`), afegeix la fila a la matriu `files` i acumula el total d'`unitats` i l'import `total`.
  5. Mostra la taula amb `mostrarTaula(new String[]{"ID VENDA", "CLIENT", "ARTICLE", "QUANTITAT", "DATA", "TOTAL LIQ."}, new int[]{10, 20, 22, 11, 12, 13}, files, n)` i al peu imprimeix:
     `if (n > 0) nota(n + " comandes · " + unitats + " unitats · Facturació: " + "%.2f €".formatted(total));`

### A.4. Cerca de vendes: `void cercarVendes(String terme)`
* **Comportament:**
  1. Si `terme.isEmpty()`, retorna immediatament.
  2. Mostra `titol("RESULTATS DE LA CERCA DE VENDES: '" + terme + "'");`.
  3. Crida a `llistarVendes(-1, -1, terme);`.
  4. Pausa l'execució amb `opcio("Prem INTRO per a continuar"); scanner.nextLine();`.

### A.5. Registre de nova comanda: `void registrarVenda()`
* **Comportament i validacions:**
  1. Comprova que `vendaCount < maxVendes` (si no, mostra `error("Límit màxim de vendes assolit.")` i retorna).
  2. Mostra els clients amb `llistarClients("")`, demana `pregunta("ID del client comprador")` i valida que existisca amb `cercarIndexClientPerId(idClient)` (si retorna `-1`, mostra `error("Client #" + idClient + " inexistent.")` i retorna).
  3. Mostra el catàleg amb `llistarProductes("")`, demana `pregunta("ID del producte a comprar")` i valida que existisca amb `cercarIndexProductePerId(idProducte)` (si retorna `-1`, mostra `error("Producte #" + idProducte + " no trobat.")` i retorna).
  4. Demana `pregunta("Unitats a comprar")` i valida:
     * Si `quantitat <= 0` -> `error("La quantitat ha de ser com a mínim 1 unitat."); return;`
     * Si `quantitat > producteEstoc[p]` -> `error("Estoc insuficient! Només en queden " + producteEstoc[p] + " unitats."); return;`
  5. Descompta l'estoc (`producteEstoc[p] -= quantitat;`), registra la venda (`afegirVenda(1001 + vendaCount, clientIds[c], producteIds[p], quantitat, productePreus[p] * quantitat, "2026-10-22");`), imprimeix el tiquet (`imprimirTiquet(vendaCount - 1, c, p);`) i mostra la nota d'estoc restant.

### A.6. Emissió directa del tiquet: `void imprimirTiquet(int v, int c, int p)`
* **Requisit d'implementació:** Ha d'imprimir el tiquet directament per consola amb `println`, generant un codi de transacció aleatori amb `random.nextInt(9000) + 1000`:

```java
// Imprimeix el tiquet de la venda 'v', feta pel client 'c' i del producte 'p' (posicions dins dels vectors)
void imprimirTiquet(int v, int c, int p) {
    String codi = "TIQ-" + vendaIds[v] + "-" + (random.nextInt(9000) + 1000);
    String vora = "─".repeat(66);
    // Cada línia: etiqueta a l'esquerra (30 caràcters) i valor alineat a la dreta (32 caràcters)
    String linia = WHT + BLD + "│  " + RST + CYN + "%-30s" + WHT + BLD + "%32s  │" + RST;

    println();
    println(WHT + BLD + "╭" + vora + "╮");
    println("│             🧾  TIQUET DE VENDA - FACTURACIÓ OFICIAL             │");
    println("│            TECH-SHOP · PLATAFORMA DE COMERÇ ELECTRÒNIC           │");
    println("├" + vora + "┤" + RST);
    println(linia.formatted("Identificador de venda:", "#" + vendaIds[v]));
    println(linia.formatted("Codi de transacció:", codi));
    println(linia.formatted("Data i hora d'emissió:", vendaDates[v] + " · Sessió activa"));
    println(linia.formatted("Client comprador:", clientNoms[c] + " (ID #" + clientIds[c] + ")"));
    println(WHT + BLD + "├" + vora + "┤" + RST);
    println(linia.formatted("Article adquirit:", producteNoms[p]));
    println(linia.formatted("Preu unitari:", "%.2f €".formatted(productePreus[p])));
    println(linia.formatted("Quantitat:", vendaQuantitats[v] + " unitats"));
    println(WHT + BLD + "├" + vora + "┤" + RST);
    println(linia.formatted("TOTAL LIQUIDAT (IVA inclòs):", "%.2f €".formatted(vendaTotals[v])));
    println(WHT + BLD + "╰" + vora + "╯" + RST);
    println(DIM + "                  Gràcies per la vostra compra!" + RST);
}
```

---

## Bloc B · Especificació de Documentació Tècnica

### B.1. Referència d'API: `projecte/docs/api/sales.md`
Crear el fitxer `projecte/docs/api/sales.md` documentant:
1. Taula dels vectors del mòdul de vendes (`vendaIds`, `vendaClientIds`, `vendaProducteIds`, `vendaQuantitats`, `vendaTotals`, `vendaDates`, `vendaCount`, `maxVendes`).
2. Especificació completa dels 6 mètodes (`menuVendes`, `mostrarEstadistiquesVendes`, `llistarVendes`, `cercarVendes`, `registrarVenda`, `imprimirTiquet`), indicant les fórmules matemàtiques (`Math.max`, `Math.min`, `suma / vendaCount`), els tres usos de `llistarVendes` (historial complet, historial d'un client i historial d'un producte) i una mostra en bloc `text` del tiquet imprés.

### B.2. Manual d'Usuari: `projecte/docs/user-guide.md`
Ampliar `projecte/docs/user-guide.md` afegint:
1. Els casos d'ús **`CU-11`** (Consultar estadístiques i historial de vendes), **`CU-12`** (Cercar vendes per qualsevol camp) i **`CU-13`** (Registrar una nova venda i emetre tiquet) a la taula de casos d'ús.
2. La secció detallada `4. Mòdul de vendes i facturació ([3])` amb captures reals de les targetes KPI, la taula d'historial, els passos de registre d'una venda i el tiquet emés.

### B.3. Diagrama de Seqüència Mermaid: `projecte/docs/architecture/diagrams.md`
Afegir la secció `5. Flux de registre d'una venda (registrarVenda)` al fitxer `projecte/docs/architecture/diagrams.md` amb un diagrama Mermaid de tipus `sequenceDiagram` que represente els participants:
* `👤 Usuari`
* `registrarVenda()`
* `cercarIndex...()`
* `Vectors en memòria`
* `imprimirTiquet()`
incloent els blocs `alt` per als casos d'error (client inexistent, producte no trobat, quantitat invàlida o estoc insuficient) i el cas d'èxit (descompte d'estoc, `afegirVenda` i emissió del tiquet).

---

## Verificació Integral del Mòdul de Vendes

Un cop integrades les 4 subtasques (Tasques 9 a 12) a `main`, verifiqueu el funcionament executant aquesta prova automatitzada que registra una venda de 2 unitats de l'article `2` per al client `102` i comprova el tiquet:

```bash
printf "3\nR\n102\n2\n2\nB\nAnna\n\n\n5\n" | java projecte/Main.java
```
