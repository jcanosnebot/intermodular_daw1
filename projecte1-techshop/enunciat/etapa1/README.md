# Etapa 1 · Nivell Bàsic i Familiarització amb GitHub (Tasques 1 a 4)

* **Sessions associades:** Sessions 2, 3 i 4 (6 hores)
* **Nivell de dificultat:** Bàsic (guiat pas a pas)
* **Objectiu principal:** Familiaritzar-se amb la gestió d'*Issues*, el tauler Kanban (GitHub Projects), el treball aïllat amb branques (`git switch -c`), la creació de *commits* descriptius i la integració mitjançant *Pull Requests* amb revisió per parelles (*Peer Review*).

---

## 1. Catàleg de Tasques de l'Etapa 1

Cada membre de l'equip (4 persones) ha d'assumir **una tasca** d'aquest llistat:

| Tasca | Fitxer d'instruccions | Tipus | Branca recomanada | Fitxers afectats |
|---|---|---|---|---|
| **Tasca 1** | [`tasca-01-modul-clients.md`](tasca-01-modul-clients.md) | Codi Java (copiar i adaptar) | `feature/1-modul-clients` | `projecte/Main.java` |
| **Tasca 2** | [`tasca-02-descripcio-readme.md`](tasca-02-descripcio-readme.md) | Documentació Markdown | `docs/2-descripcio-projecte` | `projecte/README.md`, `projecte/docs/README.md` |
| **Tasca 3** | [`tasca-03-diagrames-base.md`](tasca-03-diagrames-base.md) | Diagrames Mermaid i Dades | `docs/3-diagrames-model-dades` | `projecte/docs/architecture/diagrams.md`, `projecte/docs/architecture/data-model.md` |
| **Tasca 4** | [`tasca-04-tecnologies-instal.md`](tasca-04-tecnologies-instal.md) | Documentació Tècnica | `docs/4-tecnologies-installacio` | `projecte/docs/architecture/overview.md`, `projecte/docs/installation.md` |

> **Nota per a grups de 3 o 5 persones:**
> * Si sou **3 persones**, repartiu les Tasques 1, 2 i 3 individualment i feu la Tasca 4 de manera conjunta (però seguint igualment el cicle d'Issue, branca i PR).
> * Si sou **5 persones**, dividiu la Tasca 3 en dues branques: una per a `diagrams.md` i una altra per a `data-model.md`.

---

## 2. Procediment de Treball Pas a Pas

### Pas 1: Planificació al Tauler Kanban (Sessió 2)
1. Aneu a la pestanya **Issues** del vostre repositori a GitHub i creeu un **Milestone** anomenat `Etapa 1`.
2. Creeu les **4 Issues** corresponents a les Tasques 1, 2, 3 i 4 copiant l'objectiu i els criteris d'acceptació de cada fitxer.
3. En cada *Issue*, assigneu:
   * **Assignee:** L'alumne responsable de la tasca.
   * **Labels:** `enhancement` (per a codi) o `documentation` (per a docs/diagrames).
   * **Milestone:** `Etapa 1`.
   * **Projects:** Vinculeu la *Issue* al tauler Kanban de l'equip i situeu-la a la columna **`Todo`**.

### Pas 2: Desenvolupament en Branca Pròpia (Sessió 3)
Abans de començar a escriure codi o documentació, moveu la vostra targeta del Kanban a **`In Progress`** i executeu al terminal des de l'arrel del repositori:

```bash
# 1. Assegurar-se d'estar a main i tindre l'última versió
git switch main
git pull origin main

# 2. Crear i canviar a la branca de la vostra tasca (exemple per a la Tasca 1)
git switch -c feature/1-modul-clients
```

Realitzeu els canvis seguint les instruccions detallades del fitxer de la vostra tasca. Comproveu sempre que l'aplicació compila i s'executa sense errors:

```bash
java projecte/Main.java
```

Quan hàgeu verificat els canvis, registreu el *commit* i pugeu la branca a GitHub:

```bash
git status
git add <fitxers-modificats>
git commit -m "feat(clients): implementa el modul interactiu de gestio de clients"
git push -u origin feature/1-modul-clients
```

### Pas 3: Pull Request i Revisió per Parelles (Sessió 4)
1. A GitHub, obriu una **Pull Request (PR)** des de la vostra branca cap a `main`.
2. En la descripció de la PR, escriviu `Closes #<num_issue>` (per exemple, `Closes #1`) perquè la *Issue* es tanque automàticament en fusionar-se.
3. Moveu la targeta del tauler Kanban a la columna **`Review`** i assigneu un company de l'equip com a **Reviewer**.
4. **El company revisor ha de:**
   * Revisar la pestanya *Files changed* de la PR.
   * Comprovar que es compleixen tots els criteris d'acceptació (*Definition of Done*).
   * Escriure almenys un comentari de revisió constructiu i marcar **Approve** (o **Request changes** si cal corregir res).
5. Un cop aprovada, feu clic a **Merge pull request**, elimineu la branca fusionada i verifiqueu que la targeta ha passat a **`Done`**.

---

## 3. Verificació Final de l'Etapa 1

En acabar l'Etapa 1, la branca `main` del vostre repositori ha de tindre:
- [ ] El mòdul de clients (`[1] GESTIÓ DE CLIENTS`) plenament operatiu en `projecte/Main.java`.
- [ ] Els fitxers `projecte/README.md` i `projecte/docs/README.md` complets.
- [ ] Els fitxers `projecte/docs/architecture/diagrams.md` (amb els 2 primers diagrames Mermaid) i `projecte/docs/architecture/data-model.md`.
- [ ] Els fitxers `projecte/docs/architecture/overview.md` i `projecte/docs/installation.md`.
- [ ] Les 4 *Issues* tancades, 4 *Pull Requests* revisades i fusionades, i les 4 targetes a la columna `Done` del Kanban.
