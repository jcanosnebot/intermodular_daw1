# 🔧 Resolució de Problemes

Errors més freqüents en executar o utilitzar la **Botiga en Línia**, amb la causa i la solució de cadascun.

## 📑 Índex

1. [`NoSuchFileException: projecte/dades_botiga.txt` en desar](#1-nosuchfileexception-projectedades_botigatxt-en-desar)
2. [No apareixen les meues dades](#2-no-apareixen-les-meues-dades)
3. [`unnamed classes are a preview feature` o `class, interface, or enum expected`](#3-la-versió-de-java-és-massa-antiga)
4. [L'aplicació es tanca amb `NumberFormatException`](#4-laplicació-es-tanca-amb-numberformatexception)
5. [Apareixen símbols estranys com `←[96m`](#5-apareixen-símbols-estranys-com-96m)
6. [Les vores i els accents es veuen trencats](#6-les-vores-i-els-accents-es-veuen-trencats)
7. [Les taules o targetes es desquadren](#7-les-taules-o-targetes-es-desquadren)
8. [He perdut els canvis en eixir](#8-he-perdut-els-canvis-en-eixir)

---

## 1. `NoSuchFileException: projecte/dades_botiga.txt` en desar

**Símptoma:** en triar l'opció `4`, l'aplicació s'atura amb:
```text
Exception in thread "main" java.nio.file.NoSuchFileException: projecte/dades_botiga.txt
```

**Causa:** s'ha executat des de dins de la carpeta `projecte/`. La ruta del fitxer és relativa al directori actual, i des d'allà Java busca `projecte/projecte/dades_botiga.txt`, que no existeix.

**Solució:** executa des de l'arrel del repositori:
```bash
cd ..                    # torna a intermodular/
java projecte/Main.java
```

---

## 2. No apareixen les meues dades

**Símptoma:** l'aplicació mostra els 4 clients i els 5 productes de demostració, en lloc de les dades desades.

**Causa:** no ha trobat el fitxer `projecte/dades_botiga.txt`. O bé s'executa des de la carpeta incorrecta (problema 1), o bé el fitxer no existeix. En aquests casos l'aplicació no avisa: carrega les dades de demostració sense dir res.

**Solució:** executa des de l'arrel i comprova que el fitxer existeix:
```bash
ls projecte/dades_botiga.txt
```

Si existeix però no es carrega bé, revisa que tinga les seccions `[CLIENTS]`, `[PRODUCTES]` i `[VENDES]` i que cada línia tinga tots els camps separats per `;`. El format és al [model de dades](architecture/data-model.md#-format-del-fitxer-dades_botigatxt).

---

## 3. La versió de Java és massa antiga

**Símptoma:** en executar, apareix un error de compilació com aquests:
```text
projecte/Main.java:53: error: unnamed classes are a preview feature and are disabled by default.
```
```text
projecte/Main.java:53: error: class, interface, or enum expected
```

**Causa:** el JDK actiu és anterior a Java 25. El primer missatge és de Java 21 i el segon de versions més antigues. El codi necessita Java 25 perquè fa servir classes implícites i la classe `java.lang.IO`. Afegir `--enable-preview` **no ho soluciona**.

**Solució:** comprova la versió i instal·la el JDK 25 seguint la [guia d'instal·lació](installation.md):
```bash
java -version
```
Si tens diverses versions instal·lades, assegura't que la 25 és la primera del `PATH`, o fes servir la variable `JAVA_HOME`.

---

## 4. L'aplicació es tanca amb `NumberFormatException`

**Símptoma:**
```text
Exception in thread "main" java.lang.NumberFormatException: For input string: "abc"
```

**Causa:** en **registrar una venda**, s'ha escrit text on calia un número: l'ID del client, l'ID del producte o les unitats. Aquest formulari converteix els números amb `Integer.parseInt` sense controlar l'error. És una limitació coneguda de la demo.

**Solució:** en registrar vendes, escriu només números enters. Per a saber els IDs, mira les taules que es mostren just abans de cada pregunta.

> [!TIP]
> Els formularis de productes sí que controlen aquest error: mostren *Format numèric invàlid* i continuen. Afegir el mateix control a `registrarVenda()` és una bona tasca per a l'alumnat.

---

## 5. Apareixen símbols estranys com `←[96m`

**Causa:** la terminal no interpreta els codis de color ANSI. Passa sobretot amb el `cmd.exe` antic de Windows.

**Solució:**
1. **Recomanat:** fes servir **Windows Terminal**, que és gratuït a la Microsoft Store, o la terminal integrada de VS Code o IntelliJ.
2. Si has de fer servir `cmd`, activa els colors ANSI i obri una finestra nova:
   ```bat
   reg add HKCU\Console /v VirtualTerminalLevel /t REG_DWORD /d 1 /f
   ```

---

## 6. Les vores i els accents es veuen trencats

**Símptoma:** en lloc de `╭──╮`, `█` o `à` apareixen coses com `â”€`, `Ã ` o `?`.

**Causa:** la terminal no està en **UTF-8**.

**Solució:**
- **Windows:** executa `chcp 65001` abans de llançar l'aplicació.
- **Linux / macOS:** comprova que la configuració regional és UTF-8 amb `locale`. Si no ho és, configura-la, per exemple amb `export LANG=ca_ES.UTF-8`.

---

## 7. Les taules o targetes es desquadren

**Causes possibles:**
- **La font de la terminal no és monoespaiada,** o no dibuixa bé els emojis (`👥`, `📦`...). Fes servir una font com *Cascadia Code*, *JetBrains Mono*, *Fira Code* o *DejaVu Sans Mono*.
- **Un text és més llarg que l'amplada de la seua columna**, com un nom de client molt llarg. La taula no talla els textos: la fila es desquadra però l'aplicació continua funcionant.
- **Una capçalera de taula és massa llarga** (només si s'ha modificat el codi). Llavors l'aplicació s'atura amb `IllegalArgumentException: count is negative`. Cal fer la columna més ampla a l'`int[] amplades` corresponent. Vegeu [`mostrarTaula`](api/ui.md#void-mostrartaulastring-capcaleres-int-amplades-string-files-int-numfiles).

---

## 8. He perdut els canvis en eixir

**Causa:** l'aplicació **no desa automàticament**. L'opció `5` tanca sense desar.

**Solució:** abans d'eixir, tria l'opció `4` (Guardar dades). Si cal, recupera la darrera versió del fitxer amb Git o amb una còpia de seguretat. Vegeu [desplegament](deployment.md#-còpies-de-seguretat).
