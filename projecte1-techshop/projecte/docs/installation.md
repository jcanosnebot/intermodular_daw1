# 🛠️ Instal·lació en un Equip Nou

Aquesta guia explica com preparar un ordinador des de zero per a executar la **Botiga en Línia** en Linux, Windows o macOS.

---

## 💻 Requisits

| Requisit | Detall |
| :--- | :--- |
| **JDK 25 o superior** | **Imprescindible.** El codi fa servir les classes implícites i la classe `java.lang.IO`, que no existeixen en versions anteriors. Amb Java 21 o 17 **no funciona**, ni tan sols amb `--enable-preview`. |
| **Git** | Per a clonar el repositori i treballar en equip. |
| **Terminal amb UTF-8 i colors ANSI** | **Linux:** GNOME Terminal, Konsole, etc.<br>**Windows:** Windows Terminal (recomanat).<br>**macOS:** Terminal o iTerm2.<br>També serveix la terminal integrada de VS Code o IntelliJ. |

El maquinari no és cap problema: l'aplicació ocupa uns pocs megabytes de memòria.

---

## 📥 Pas 1 · Instal·lar el JDK 25

**Ubuntu / Debian / Linux Mint**
```bash
sudo apt update
sudo apt install openjdk-25-jdk git
```
Si la teua versió de la distribució no té el paquet, descarrega el JDK de [Adoptium (Eclipse Temurin)](https://adoptium.net/).

**Windows**
1. Descarrega l'instal·lador de **JDK 25** d'[Adoptium (Eclipse Temurin)](https://adoptium.net/) o de [Microsoft Build of OpenJDK](https://learn.microsoft.com/java/openjdk/download).
2. En l'instal·lador, marca l'opció **Add to PATH**.

**macOS** (amb [Homebrew](https://brew.sh/))
```bash
brew install openjdk git
```

**Amb SDKMAN!** (Linux, macOS o WSL)
```bash
curl -s "https://get.sdkman.io" | bash
sdk list java              # tria una versió 25
sdk install java <versió-25>
```

**Comprovació**
```bash
java -version              # ha de dir: openjdk version "25..." o superior
```

---

## 📥 Pas 2 · Clonar el repositori

```bash
git clone <URL_DEL_REPOSITORI>
cd intermodular
```

La carpeta `intermodular/` és l'**arrel del repositori**. A dins hi ha la carpeta `projecte/` amb `Main.java` i `dades_botiga.txt`.

---

## ▶️ Pas 3 · Primera execució

Des de l'arrel del repositori:

```bash
java projecte/Main.java
```

Java compila el fitxer en memòria i l'executa. No cal `javac` ni cap opció addicional. Ha d'aparéixer el logotip **TECH-SHOP**, les quatre targetes de resum i el menú principal.

> [!IMPORTANT]
> Executa sempre des de l'**arrel** (`intermodular/`), no des de `projecte/`. L'aplicació busca el fitxer de dades a `projecte/dades_botiga.txt`, una ruta relativa al directori actual.

---

## ⚡ Alternativa: JBang

[JBang](https://www.jbang.dev/) executa fitxers `.java` com si foren scripts i, si cal, descarrega el JDK correcte:

```bash
curl -Ls https://sh.jbang.dev | bash -s - app setup   # instal·lació
jbang projecte/Main.java                              # des de l'arrel
```

JBang llig les dues primeres línies de `Main.java` (`///usr/bin/env jbang ...` i `//JAVA 25+`) per a saber quina versió de Java ha de fer servir.

Si alguna cosa no funciona, consulta la [resolució de problemes](troubleshooting.md).
