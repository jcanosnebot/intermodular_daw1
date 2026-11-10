# 🎨 Mètodes d'Interfície

Aquests mètodes dibuixen la pantalla. Són **els únics que fan servir colors ANSI**: la resta del codi els crida amb text normal, i així no queda ple de codis de color pel mig.

---

## 🎨 Paleta de colors i badges

Constants definides al principi de `Main.java`. Els noms són curts perquè el codi de dibuix quede alineat i es puga llegir:

| Constant | Efecte | Constant | Efecte |
| :--- | :--- | :--- | :--- |
| `RST` | Torna a l'estil normal | `BLU` | Blau |
| `BLD` | Negreta | `PUR` | Lila |
| `DIM` | Text atenuat | `CYN` | Cian |
| `BCK` | Negre | `WHT` | Blanc |
| `RED` | Roig | `BRED` | Fons roig |
| `GRN` | Verd | `BGRN` | Fons verd |
| `YLW` | Groc | `BYLW` | Fons groc |
| `EMP` | Cadena buida (només per a alinear línies del logotip) | | |

**Badges** (etiquetes d'estat que es posen dins de les taules):

| Constant | Aspecte | On s'usa |
| :--- | :--- | :--- |
| `BADGE_ACTIU` | ` ACTIU ` sobre fons verd | Estat d'un client |
| `BADGE_BAIXA` | ` BAIXA ` sobre fons roig | Estat d'un client |
| `BADGE_EN_ESTOC` | ` EN ESTOC ` sobre fons verd | Article amb més de 10 unitats |
| `BADGE_BAIX` | ` BAIX ` sobre fons groc | Article amb d'1 a 10 unitats |
| `BADGE_ESGOTAT` | ` ESGOTAT ` sobre fons roig | Article sense estoc |

---

## 🏠 Pantalla principal

### `void main()`
Punt d'entrada de l'aplicació.
1. Carrega les dades de demostració i després el fitxer.
2. Mostra el logotip.
3. Repeteix el resum general, el menú principal i la lectura de l'opció.

| Opció | Acció |
| :--- | :--- |
| `1` | `menuClients()` |
| `2` | `menuProductes()` |
| `3` | `menuVendes()` |
| `4` | `guardarDadesFitxer()` |
| `5` | Mostra el comiat amb `capcalera` i acaba (`return`). |
| Una altra | `error(...)` |

Està declarat `throws Exception` perquè `guardarDadesFitxer()` pot llançar errors d'escriptura.

### `void mostrarCapcaleraPrincipal()`
Imprimeix directament, línia a línia, el logotip **TECH-SHOP** dins d'una caixa cian.

### `void mostrarResumGeneral()`
Calcula el total facturat i la suma de les unitats en estoc. Després mostra quatre targetes amb `mostrarTargetes`: **clients**, **articles** (amb les unitats), **vendes** i **total facturat**.

### `void mostrarMenuPrincipal()`
Defineix les 5 opcions en una matriu `String[][]` (número, opció i descripció) i la mostra amb `mostrarTaula` sota el títol *PANELL DE CONTROL PRINCIPAL*.

---

## 💬 Missatges i textos

| Mètode | Resultat a la pantalla | Ús |
| :--- | :--- | :--- |
| `void titol(String text)` | `✦ TEXT ✦` en groc i negreta, amb una línia en blanc abans | Títol d'una secció |
| `void nota(String text)` | Text atenuat, sagnat dos espais | Peus de taula i ajudes |
| `void ok(String text)` | ` ✔ ÈXIT ` sobre verd + text verd | Operació feta |
| `void error(String text)` | ` ✘ ERROR ` sobre roig + text roig | Operació no feta |
| `void avis(String text)` | ` ⚠ ATENCIÓ ` sobre groc + text groc | Avisos i cancel·lacions |
| `void dada(String etiqueta, String valor)` | `  Etiqueta  Valor`: l'etiqueta en cian, ocupant 10 caràcters, i el valor en blanc | Línies de les fitxes de client i d'article |

---

## ⌨️ Enunciats per a l'usuari

Aquests dos mètodes **només imprimeixen l'enunciat**. No llegeixen res: la resposta es llig a continuació, en el mètode que fa la pregunta:

```java
pregunta("Correu electrònic");
String email = scanner.nextLine().trim().toLowerCase();
```

| Mètode | Resultat a la pantalla | Ús |
| :--- | :--- | :--- |
| `void pregunta(String text)` | `  ▪ text: ` en cian | Camps de formulari i confirmacions `[s/N]` |
| `void opcio(String text)` | `➤ text: ` en cian i negreta, amb una línia en blanc abans | Triar una opció o una acció, i cercar |

---

## 🖼️ Elements gràfics

### `void capcalera(String color, String text)`
Dibuixa una caixa de 77 columnes amb el `text` a dins. Es fa servir per a la capçalera de cada mòdul i per al missatge de comiat.

| Paràmetre | Descripció |
| :--- | :--- |
| `color` | Color de la vora (per exemple, `GRN`). |
| `text` | Text de la capçalera. Si és més curt de 77 caràcters, s'omple amb espais. |

### `void mostrarTargetes(String[] titols, String[] valors)`
Dibuixa una fila de targetes, una per cada posició dels vectors. Els colors de les vores van rotant: cian, blau, lila, verd.

```text
╭────────────────────╮  ╭────────────────────╮
│ 👥 CLIENTS         │  │ 📦 ARTICLES        │
│            4 / 50  │  │        5 (88 un.)  │
╰────────────────────╯  ╰────────────────────╯
```

| Paràmetre | Descripció |
| :--- | :--- |
| `titols` | Títol de cada targeta. Com a màxim 19 caràcters. |
| `valors` | Valor de cada targeta, alineat a la dreta. Com a màxim 18 caràcters. |

### `void mostrarTaula(String[] capcaleres, int[] amplades, String[][] files, int numFiles)`
Dibuixa una taula amb vores arrodonides. Ho imprimeix tot directament, amb un bucle per cada part:
1. La vora superior: `╭──┬──╮`.
2. La capçalera.
3. El separador: `├──┼──┤`.
4. Les files de dades.
5. La vora inferior: `╰──┴──╯`.

| Paràmetre | Descripció |
| :--- | :--- |
| `capcaleres` | Títol de cada columna. |
| `amplades` | Amplada de cada columna en caràcters, incloent-hi l'espai inicial. |
| `files` | Matriu amb el text de cada cel·la. Pot contindre badges amb color. |
| `numFiles` | Quantes files de `files` s'han de mostrar, començant per la primera. Així `files` pot ser més gran que el nombre de resultats. |

- La **primera columna** (l'identificador) es pinta en groc. La resta de cel·les alternen blanc i cian, fila a fila.
- Per a calcular els espais de cada cel·la, no es compten els codis de color. És l'única línia del projecte que usa `replaceAll`.
- Si `numFiles` és `0`, no dibuixa la taula: mostra `(No hi ha cap registre per mostrar)`.

> [!WARNING]
> Cada capçalera ha de ser **més curta** que l'amplada de la seua columna. Si no ho és, `" ".repeat(...)` rep un número negatiu i l'aplicació s'atura. Les cel·les de dades no tenen aquest problema: si són massa llargues, només es desquadra la línia.

#### Exemple
```java
String[][] files = {
    {"#101", "Anna Garcia", BADGE_ACTIU},
    {"#102", "Bernat Lopez", BADGE_BAIXA}
};
mostrarTaula(new String[]{"ID", "NOM", "ESTAT"}, new int[]{6, 16, 9}, files, files.length);
```
