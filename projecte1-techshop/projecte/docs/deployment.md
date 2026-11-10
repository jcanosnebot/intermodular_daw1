# 🚢 Desplegament i Manteniment

Guia per a qui ha de posar en marxa l'aplicació a l'aula, preparar-ne les dades, fer còpies de seguretat i actualitzar-la.

---

## 🏗️ Model de desplegament

| Característica | Detall |
| :--- | :--- |
| **Tipus d'aplicació** | Programa de consola autònom, d'un sol fitxer (`Main.java`). |
| **Dependències** | Només el JDK 25. No calen llibreries, servidor web ni base de dades. |
| **Dades** | Un fitxer de text pla: `projecte/dades_botiga.txt`. |
| **Xarxa** | No en necessita. |
| **Compilació** | No cal: `java projecte/Main.java` compila i executa en un sol pas. |

---

## ▶️ Execució

```bash
cd intermodular          # arrel del repositori
java projecte/Main.java
```

> [!IMPORTANT]
> La ruta del fitxer de dades és **relativa al directori actual** i està fixada a `Main.java`:
> ```java
> String rutaFitxerDades = "projecte/dades_botiga.txt";
> ```
> Per això cal executar des de l'arrel. Si una aula necessita executar des d'una altra carpeta, s'ha de canviar aquesta línia.

---

## ⚙️ Dades inicials

En arrancar, l'aplicació fa dues coses, en aquest ordre:
1. Carrega les **dades de demostració**, escrites al codi: 4 clients, 5 productes i 2 vendes.
2. Llig `projecte/dades_botiga.txt`. Cada secció que hi trobe (`[CLIENTS]`, `[PRODUCTES]`, `[VENDES]`) **substitueix** la de demostració.

El format exacte del fitxer és al [model de dades](architecture/data-model.md#-format-del-fitxer-dades_botigatxt).

| Vull... | Què he de fer |
| :--- | :--- |
| **Començar amb les dades de demostració** | Esborrar o reanomenar `dades_botiga.txt`. L'aplicació no avisa: carrega les dades del codi. El fitxer es tornarà a crear la primera vegada que es desen les dades (opció `4`). |
| **Preparar unes dades pròpies** | Editar `dades_botiga.txt` amb qualsevol editor de text, respectant les seccions i els `;`. Els decimals s'escriuen amb punt. |
| **Tornar a l'estat original del repositori** | `git checkout -- projecte/dades_botiga.txt` |

> [!WARNING]
> Les dades **només es desen amb l'opció `4`** del menú principal. Si l'aplicació es tanca amb l'opció `5` o amb `Ctrl+C`, es perd tot el que no s'haja desat.

---

## 💾 Còpies de seguretat

Totes les dades són en un sol fitxer, de manera que la còpia és immediata.

**Linux / macOS**
```bash
mkdir -p backups
cp projecte/dades_botiga.txt "backups/dades_botiga_$(date +%Y%m%d_%H%M%S).txt"
```

**Restauració**
```bash
cp backups/dades_botiga_20261024_120000.txt projecte/dades_botiga.txt
```

> [!TIP]
> El fitxer també està versionat amb Git. Si s'ha fet un *commit*, `git log -- projecte/dades_botiga.txt` mostra totes les seues versions anteriors.

---

## 🔄 Actualització

Quan l'equip integra canvis nous a la branca `main`:

```bash
cd intermodular
git pull origin main
java projecte/Main.java
```

Si tenies dades pròpies sense *commit* a `dades_botiga.txt`, fes-ne abans una còpia de seguretat, perquè `git pull` podria entrar en conflicte amb el fitxer.

---

## 🏫 Llançadors per a l'aula

Scripts per a iniciar l'aplicació amb un doble clic. Es posen a l'**arrel del repositori**.

**Linux / macOS (`iniciar_botiga.sh`)**
```bash
#!/usr/bin/env bash
cd "$(dirname "$0")"
java projecte/Main.java
```
Cal donar-li permisos d'execució: `chmod +x iniciar_botiga.sh`.

**Windows (`iniciar_botiga.bat`)**
```bat
@echo off
cd /d "%~dp0"
chcp 65001 > nul
java projecte/Main.java
pause
```
`chcp 65001` activa l'UTF-8 perquè les vores i els accents es vegen bé.
