# 🗄️ Dades i Persistència

Mètodes de suport que treballen directament amb els vectors i amb el fitxer de dades. El format del fitxer és a [architecture/data-model.md](../architecture/data-model.md#-format-del-fitxer-dades_botigatxt).

---

## 🔎 Cerca per identificador

Són els **únics mètodes amb valor de retorn** del projecte, tal com permeten les regles del curs. Converteixen un identificador en la posició on és dins dels vectors.

### `int cercarIndexClientPerId(int id)`
Recorre `clientIds` des de la posició 0 fins a `clientCount - 1`. Retorna la posició del client amb aquest `id`, o `-1` si no n'hi ha cap.

### `int cercarIndexProductePerId(int id)`
El mateix, amb `producteIds` i `producteCount`.

```java
int p = cercarIndexProductePerId(vendaProducteIds[i]);
String article = p != -1 ? producteNoms[p] : "#" + vendaProducteIds[i];
```

---

## ➕ Alta als vectors

Afegeixen un registre **al final** dels vectors d'una entitat i n'incrementen el comptador. No validen res: les validacions les fa qui els crida.

| Mètode | Qui el crida |
| :--- | :--- |
| `void afegirClient(int id, String nom, String email, String telefon, boolean actiu)` | `donarAltaClient`, `carregarDadesFitxer`, `inicialitzarDadesBase` |
| `void afegirProducte(int id, String nom, String categoria, double preu, int estoc)` | `donarAltaProducte`, `carregarDadesFitxer`, `inicialitzarDadesBase` |
| `void afegirVenda(int id, int clientId, int producteId, int quantitat, double total, String data)` | `registrarVenda`, `carregarDadesFitxer`, `inicialitzarDadesBase` |

> [!NOTE]
> Aquests mètodes no comproven el límit de 50 registres. Ho fan els mètodes d'alta i `registrarVenda` abans de cridar-los.

---

## 💾 Persistència

### `void guardarDadesFitxer() throws Exception`
1. Construeix un `String` amb tot el contingut del fitxer:
   - un comentari inicial;
   - la secció `[CLIENTS]`;
   - la secció `[PRODUCTES]`;
   - la secció `[VENDES]`.
2. Cada registre és una línia amb els camps separats per `;`.
3. L'escriu amb `Files.writeString(Path.of(rutaFitxerDades), contingut)` i mostra `ok(...)`.

Si no es pot escriure, per exemple perquè la carpeta `projecte/` no existeix al directori actual, l'excepció arriba fins a `main()` i l'aplicació s'atura.

### `void carregarDadesFitxer()`
1. Llig tot el fitxer amb `Files.readString` i el divideix en línies.
2. Recorre les línies:
   - Una línia que comença per `[` canvia la **secció actual** i **posa a zero** el comptador d'aquella entitat. Així les dades del fitxer substitueixen les de demostració.
   - Les línies buides o que comencen per `#` s'ignoren.
   - La resta es divideixen per `;` i es passen a `afegirClient`, `afegirProducte` o `afegirVenda`, segons la secció.
3. Qualsevol error es captura amb `catch (Exception e)` sense mostrar res. Pot ser que el fitxer no existisca o que una línia tinga un format incorrecte. En aquests casos es manté el que ja hi havia.

### `void inicialitzarDadesBase()`
Carrega les **dades de demostració** amb els mètodes `afegir...`. Es crida al principi de `main()`, abans de llegir el fitxer.

| Entitat | Dades |
| :--- | :--- |
| Clients | `#101` Anna Garcia, `#102` Bernat Lopez, `#103` Carla Vidal, `#104` David Mas |
| Productes | `1` Portatil 15 polzades (749,99 €, 10 un.), `2` Ratoli sense fils (19,95 €, 25 un.), `3` Teclat mecanic (49,50 €, 15 un.), `4` Monitor 24 4K (189,00 €, 8 un.), `5` Auriculars Bluetooth (39,90 €, 30 un.) |
| Vendes | `#1001` Anna Garcia · Portatil 15 polzades · 1 un. · 749,99 € · 2026-10-15<br>`#1002` Carla Vidal · Teclat mecanic · 1 un. · 49,50 € · 2026-10-20 |
