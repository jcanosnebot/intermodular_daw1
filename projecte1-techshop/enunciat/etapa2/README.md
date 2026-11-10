# Etapa 2 · Nivell Intermedi, Codi Incomplet i Resolució de Conflictes (Tasques 5 a 8)

* **Sessions associades:** Sessions 5 i 6 (4 hores)
* **Nivell de dificultat:** Intermedi
* **Objectius principals:**
  1. Completar fragments de codi Java amb parts omeses (`TODO`) per a posar en marxa el **Mòdul de Productes i Control d'Estoc**.
  2. Documentar l'API interna (`docs/api/`), els casos d'ús (`docs/user-guide.md`) i ampliar els diagrames Mermaid (`docs/architecture/diagrams.md`).
  3. Experimentar i resoldre de manera consensuada un **conflicte d'integració en Git** provocat pel treball simultani sobre el mateix mòdul.

---

## 1. Catàleg de Tasques de l'Etapa 2

| Tasca | Fitxer d'instruccions | Tipus | Branca recomanada | Fitxers afectats |
|---|---|---|---|---|
| **Tasca 5** | [`tasca-05-cataleg-productes.md`](tasca-05-cataleg-productes.md) | Codi incomplet + API | `feature/5-cataleg-productes` | `projecte/Main.java`, `projecte/docs/api/products.md` |
| **Tasca 6** | [`tasca-06-gestio-estoc-altes.md`](tasca-06-gestio-estoc-altes.md) | Codi incomplet + Conflicte | `feature/6-gestio-estoc-altes` | `projecte/Main.java`, `projecte/docs/api/products.md` |
| **Tasca 7** | [`tasca-07-api-ui-clients.md`](tasca-07-api-ui-clients.md) | Documentació API | `docs/7-api-ui-clients` | `projecte/docs/api/README.md`, `ui.md`, `customers.md`, `data.md` |
| **Tasca 8** | [`tasca-08-casos-us-estats.md`](tasca-08-casos-us-estats.md) | Casos d'Ús + Mermaid | `docs/8-casos-us-navegacio` | `projecte/docs/user-guide.md`, `projecte/docs/architecture/diagrams.md` |

---

## 2. El Conflicte Controlat entre la Tasca 5 i la Tasca 6 (Sessió 6)

En un equip de desenvolupament real, és habitual que dues persones treballen alhora sobre el mateix fitxer (`projecte/Main.java` i `projecte/docs/api/products.md`).

### Com es produeix el conflicte?
1. Els responsables de la **Tasca 5** i de la **Tasca 6** creen les seues branques (`feature/5-cataleg-productes` i `feature/6-gestio-estoc-altes`) al mateix temps a partir de `main`.
2. Tots dos modifiquen la secció `// 2. MÒDUL DE PRODUCTES I ESTOC` de `projecte/Main.java` i el fitxer `projecte/docs/api/products.md`.
3. Es revisa i es fusiona primer la *Pull Request* de la **Tasca 5** a `main`.
4. En aquest moment, la *Pull Request* de la **Tasca 6** mostrarà a GitHub l'avís:
   > ⚠️ **This branch has conflicts that must be resolved**

### Com resoldre el conflicte pas a pas?
El responsable de la **Tasca 6** (amb l'ajuda del company de la Tasca 5) ha d'executar al seu terminal:

```bash
# 1. Situar-se a la branca amb conflicte
git switch feature/6-gestio-estoc-altes

# 2. Portar els últims canvis de main (això dispararà el conflicte)
git pull origin main
```

Git marcarà les zones en conflicte dins de `projecte/Main.java` i `projecte/docs/api/products.md` amb les marques:

```text
<<<<<<< HEAD
(Codi o documentació de la teua branca: Tasca 6)
=======
(Codi o documentació que ja s'ha fusionat a main: Tasca 5)
>>>>>>> origin/main
```

**Passos de resolució:**
1. Obriu `projecte/Main.java` i `projecte/docs/api/products.md` a l'editor.
2. Conserveu els mètodes complets de consulta/cerca de la **Tasca 5** (`menuProductes`, `llistarProductes`, `gestionarProducte`) juntament amb els mètodes complets d'operacions de la **Tasca 6** (`modificarProducte`, `reposarEstoc`, `eliminarProducte`, `donarAltaProducte`), eliminant els mètodes buits provisionals i les marques `<<<<<<<`, `=======` i `>>>>>>>`.
3. Comproveu que **no queda cap marca de conflicte**:
   ```bash
   grep -rn "<<<<<<<" projecte/
   ```
4. Verifiqueu que el programa compila i funciona executant:
   ```bash
   java projecte/Main.java
   ```
5. Registreu la resolució del conflicte i pugeu-la a la PR:
   ```bash
   git add projecte/Main.java projecte/docs/api/products.md
   git commit -m "merge: resol conflicte d'integracio del modul de productes entre T5 i T6"
   git push
   ```

---

## 3. Verificació Final de l'Etapa 2

- [ ] L'opció `[2] CATÀLEG I ESTOC` del menú principal funciona al 100% (llistat amb barra visual d'estoc, cerca per ID/nom/categoria, modificació, reposició d'estoc, eliminació física i alta).
- [ ] S'ha resolt netament el conflicte d'integració entre les Tasques 5 i 6 deixant evidència a la *Pull Request*.
- [ ] La carpeta `projecte/docs/api/` conté `README.md`, `ui.md`, `customers.md`, `products.md` i `data.md`.
- [ ] El fitxer `projecte/docs/user-guide.md` documenta els casos d'ús de Clients i Productes (`CU-01` a `CU-10`) i `diagrams.md` inclou els diagrames de Casos d'Ús i Navegació.
- [ ] Les Issues 5 a 8 estan tancades i a la columna `Done` del Kanban.
