# 🧪 Proves i Verificació

El projecte **no fa servir proves unitàries** com JUnit: caldria introduir classes i llibreries que encara no es veuen a 1r. La verificació es fa de tres maneres:

1. [Proves amb canonades](#1-proves-amb-canonades): automàtiques, des de la terminal.
2. [Casos de prova manuals](#2-casos-de-prova-manuals): abans de cada *pull request*.
3. [Comprovacions de límits i de persistència](#3-comprovacions-de-límits-i-de-persistència).

> [!NOTE]
> Totes les ordres s'executen des de l'**arrel del repositori**. Cap d'aquestes proves tria l'opció `4`, de manera que **no modifiquen** `dades_botiga.txt`.

---

## 1. Proves amb canonades

Una canonada (`|`) envia a l'aplicació, d'una sola vegada, tot el que escriuria una persona amb el teclat. Cada `\n` equival a prémer `INTRO`.

Per a llegir l'eixida sense els codis de color, afegeix `| sed 's/\x1b\[[0-9;]*m//g'` al final de l'ordre.

### Prova 1 · Recorregut ràpid (*smoke test*)
Entra i ix dels tres mòduls i tanca l'aplicació:
```bash
printf "1\n\n2\n\n3\n\n5\n" | java projecte/Main.java > /dev/null; echo "codi d'eixida: $?"
```
✅ **Correcte si:** el codi d'eixida és `0`.

### Prova 2 · Registrar una venda
Client `101`, article `1`, 1 unitat:
```bash
printf "3\nR\n101\n1\n1\n\n5\n" | java projecte/Main.java | grep -E "TIQ-|TOTAL LIQUIDAT|Estoc restant"
```
✅ **Correcte si:**
- es mostra un codi `TIQ-1003-XXXX`;
- el total és `749,99 €`;
- l'estoc restant del portàtil és `9 un.`

### Prova 3 · Donar d'alta un client
```bash
printf "1\nLaura Gil\ns\n\nlaura@correu.cat\n600112233\n\n5\n" | java projecte/Main.java | grep -E "ÈXIT|#105"
```
✅ **Correcte si:** apareix *Client registrat correctament amb l'identificador #105* i, a la llista, la fila de `#105 Laura Gil`.

### Prova 4 · Cerca parcial per telèfon
```bash
printf "1\n622\nn\n\n5\n" | java projecte/Main.java | grep -E "#102|ATENCIÓ"
```
✅ **Correcte si:** surt el client `#102 Bernat Lopez` com a coincidència parcial, i l'avís *Cap client coincideix exactament amb '622'*.

### Prova 5 · Venda amb estoc insuficient
Article `4` (8 unitats), 99 unitats:
```bash
printf "3\nR\n101\n4\n99\n\n5\n" | java projecte/Main.java | grep ERROR
```
✅ **Correcte si:** apareix *Estoc insuficient! Només en queden 8 unitats.*

---

## 2. Casos de prova manuals

Abans de demanar la revisió d'una *pull request*, comprova manualment els casos relacionats amb el teu canvi:

| ID | Mòdul | Acció | Entrada | Resultat esperat |
| :--- | :--- | :--- | :--- | :--- |
| TC-01 | Clients | Cerca exacta per ID | `101` | S'obri la fitxa d'Anna Garcia amb 1 compra. |
| TC-02 | Clients | Cerca exacta per correu | `CARLA@correu.cat` | S'obri la fitxa de Carla Vidal: el correu no distingeix majúscules. |
| TC-03 | Clients | Alta amb nom curt | `Jo` → `s` → `INTRO` | `✘ ERROR` *El nom ha de contindre almenys 3 caràcters.* |
| TC-04 | Clients | Alta amb correu incorrecte | `Pepito Grillo` → `s` → `INTRO` → `pepito` | `✘ ERROR` *Format de correu incorrecte...* |
| TC-05 | Clients | Baixa | `104` → `E` → `s` | `✔ ÈXIT`. A la llista, `#104` surt com a `BAIXA`. |
| TC-06 | Clients | Modificació mantenint valors | `101` → `M` → `INTRO` ×3 | `✔ ÈXIT` i les dades no canvien. |
| TC-07 | Catàleg | Cerca per categoria | `Accessoris` | Coincidències parcials: Ratolí i Teclat. |
| TC-08 | Catàleg | Cerca per ID amb dues xifres | `02` | S'obri la fitxa del Ratolí. |
| TC-09 | Catàleg | Reposar estoc | `02` → `R` → `10` | L'estoc del Ratolí passa de 25 a 35. |
| TC-10 | Catàleg | Alta amb preu negatiu | `Webcam` → `s` → `INTRO` → `INTRO` → `-5` | `✘ ERROR` *El preu ha de ser superior a zero.* |
| TC-11 | Catàleg | Eliminar un article amb vendes | `03` → `E` → `s` | Avís de *1 comandes registrades*. L'article desapareix i, a l'historial de vendes, la venda `#1002` mostra `#3` com a article. |
| TC-12 | Vendes | Cerca directa | `Anna` | Taula amb 1 venda i el resum *1 comandes · 1 unitats · Facturació: 749,99 €*. |
| TC-13 | Vendes | Client inexistent | `R` → `999` | `✘ ERROR` *Client #999 inexistent.* |
| TC-14 | Vendes | Quantitat zero | `R` → `101` → `1` → `0` | `✘ ERROR` *La quantitat ha de ser com a mínim 1 unitat.* |
| TC-15 | General | Opció incorrecta al menú | `9` | `✘ ERROR` *Opció no vàlida...* i es torna a mostrar el menú. |

---

## 3. Comprovacions de límits i de persistència

### Límits dels vectors
- **50 clients, 50 productes i 50 vendes.** Les altes i `registrarVenda()` comproven el límit **abans** de demanar les dades. Si es modifica alguna alta, cal mantindre aquesta comprovació per a no provocar un `ArrayIndexOutOfBoundsException`.
- **Divisió per zero:** `mostrarEstadistiquesVendes()` comprova que `vendaCount` no siga `0` abans de calcular la mitjana.

### Persistència
1. Fes un canvi, per exemple dona d'alta un client.
2. Tria l'opció `4` i després la `5`.
3. Comprova el fitxer:
   ```bash
   cat projecte/dades_botiga.txt
   ```
   ✅ **Correcte si:**
   - el client nou apareix a `[CLIENTS]`;
   - cada línia té els camps separats per `;`;
   - els decimals porten punt (`749.99`).
4. Torna a executar l'aplicació: el client nou ha de continuar a la llista.
5. Torna el fitxer al seu estat original:
   ```bash
   git checkout -- projecte/dades_botiga.txt
   ```

### Alineació visual
Després de qualsevol canvi en textos de pantalla, comprova en una terminal d'almenys 100 columnes que les vores de taules, targetes i tiquet continuen ben tancades.
