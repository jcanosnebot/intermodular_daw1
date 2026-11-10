# 💻 Entorn de Desenvolupament

Com preparar les eines per a treballar amb el codi del projecte. Per a la instal·lació bàsica de Java, consulta abans la [guia d'instal·lació](../installation.md).

---

## 🛠️ Eines

| Eina | Versió | Per a què |
| :--- | :--- | :--- |
| **JDK** | 25 o superior | Compilar i executar `Main.java`. |
| **Git** | 2.30 o superior | Control de versions. |
| **Editor** | [VS Code](https://code.visualstudio.com/) o [IntelliJ IDEA](https://www.jetbrains.com/idea/) | Editar el codi i la documentació. El repositori ja inclou la configuració d'IntelliJ (`.idea/`, `projecte.iml`). |
| **GitHub CLI** (`gh`) | Opcional | Gestionar incidències i *pull requests* des de la terminal. |

---

## ▶️ Executar i compilar des de la terminal

Sempre des de l'**arrel del repositori**:

```bash
java projecte/Main.java                       # executar (compila en memòria)
javac -d /tmp/botiga projecte/Main.java       # només comprovar que compila
```

> [!NOTE]
> Amb `javac` és millor indicar una carpeta de sortida amb `-d`, perquè no es creen fitxers `.class` dins de `projecte/`. De tota manera, `projecte/.gitignore` ja ignora els `*.class` i la carpeta `out/` d'IntelliJ.

---

## 🔌 VS Code

**Extensions recomanades** (`Ctrl+Shift+X`):
- **Extension Pack for Java** (Microsoft): autocompletat, errors i depuració.
- **Markdown All in One**: dreceres i índexs per a la documentació.
- **Markdown Preview Mermaid Support**: previsualitza els diagrames de `docs/`.

**Executar:** el més senzill és obrir la terminal integrada (`Ctrl+ñ`) a l'arrel i escriure `java projecte/Main.java`.

**Executar amb `F5`:** crea `.vscode/launch.json` a l'arrel del repositori i prem `F5` amb `Main.java` obert a l'editor. És important que `cwd` siga l'arrel, perquè l'aplicació trobe `projecte/dades_botiga.txt`:

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Botiga en Línia",
            "request": "launch",
            "mainClass": "${file}",
            "cwd": "${workspaceFolder}",
            "console": "integratedTerminal"
        }
    ]
}
```

---

## 🧠 IntelliJ IDEA

1. Obri la carpeta **`intermodular/`** (l'arrel), no `projecte/`.
2. Configura el **Project SDK** amb un JDK 25 a *File → Project Structure → Project*, amb el *language level* a 25.
3. A la configuració d'execució de `Main`, posa el **Working directory** a l'arrel del repositori (`$PROJECT_DIR$` si has obert `intermodular/`).
4. Executa amb la terminal integrada (*Run*), perquè els colors ANSI es vegen.

---

## 🖥️ Terminal

L'aplicació fa servir colors ANSI, caràcters de caixa Unicode (`╭ ─ │ █ ░`) i alguns emojis.

- **Font monoespaiada recomanada:** *Cascadia Code*, *JetBrains Mono*, *Fira Code* o *DejaVu Sans Mono*.
- **Codificació:** UTF-8. A Windows, `chcp 65001`.
- **Amplada:** almenys **100 columnes**. La taula més ampla, la del catàleg, en fa 99.

En VS Code, per exemple:
```json
"terminal.integrated.fontFamily": "'Cascadia Code', 'JetBrains Mono', monospace"
```
