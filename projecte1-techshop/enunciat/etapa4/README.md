# Etapa 4 · Complexitat Avançada, Persistència, Consolidació i Release Final (Tasques 13 a 16)

* **Sessió associada:** Sessió 8 (2 hores)
* **Nivell de dificultat:** Avançat (autonomia plena de l'equip i tancament del projecte)
* **Objectius principals:**
  1. Implementar la **persistència de dades en fitxer físic** (`projecte/dades_botiga.txt`) amb `Files.writeString` i `Files.readString`.
  2. Consolidar tota la documentació de governança, col·laboració, proves i desplegament (`CONTRIBUTING.md`, `docs/development/`, `docs/deployment.md`, `docs/troubleshooting.md`, `CHANGELOG.md`).
  3. Auditar que les **16 tasques** i els **6 elements documentals obligatoris** estan complets i publicar formalment la **Release `v1.0.0`** a GitHub.

---

## 1. Descomposició Autònoma de les Tasques 13 a 16

Igual que en l'Etapa 3, l'equip rep el document d'especificació final:
* 📄 [`especificacio-entrega-final.md`](especificacio-entrega-final.md)

L'equip ha de crear el **Milestone `Etapa 4`** a GitHub, definir les **Issues #13, #14, #15 i #16** al tauler Kanban i distribuir-les entre els seus membres.

### Proposta de Divisió per a les Tasques 13 a 16

| Subtasca | Títol recomanat per a la Issue | Branca sugerida | Contingut de la subtasca |
|---|---|---|---|
| **Tasca 13** | Persistència de Dades en Fitxer (`Java NIO`) i Actualització del Model de Dades | `feature/13-persistencia-fitxer` | Implementar `guardarDadesFitxer()` i `carregarDadesFitxer()` en `projecte/Main.java`, crear `projecte/dades_botiga.txt` i actualitzar `docs/architecture/data-model.md`, `docs/api/data.md` i `docs/user-guide.md` (`CU-14` i `CU-15`). |
| **Tasca 14** | Guia de Contribució (`CONTRIBUTING.md`), Flux de Treball i Plantilles GitHub | `docs/14-guia-contribucio-workflow` | Redactar `projecte/CONTRIBUTING.md` (Element 5), `projecte/docs/development/workflow.md`, `projecte/.gitignore` i les plantilles de `.github/` (`PULL_REQUEST_TEMPLATE.md` i `ISSUE_TEMPLATE/`). |
| **Tasca 15** | Entorn de Desenvolupament i Pla de Proves (Canonades i Casos Manuals) | `docs/15-entorn-pla-proves` | Redactar `projecte/docs/development/environment.md` i `projecte/docs/development/testing.md` (proves automatitzades `P1`..`P5` i casos `TC-01`..`TC-15`). |
| **Tasca 16** | Guia de Desplegament, Resolució de Problemes, `CHANGELOG.md` i Auditoria de Release | `docs/16-desplegament-release` | Redactar `projecte/docs/deployment.md`, `projecte/docs/troubleshooting.md`, `projecte/CHANGELOG.md` i fer l'auditoria final d'enllaços abans de publicar la Release `v1.0.0`. |

---

## 2. Procediment de Tancament i Publicació de la Release `v1.0.0`

Un cop les *Pull Requests* de les Tasques 13, 14, 15 i 16 han estat revisades, aprovades i fusionades a `main`:

### Pas 1: Auditoria de Qualitat Final
Comproveu en equip que es compleixen tots els punts següents:
- [ ] **Codi i persistència:** `printf "4\n5\n" | java projecte/Main.java` guarda correctament `projecte/dades_botiga.txt` i ix amb codi `0`.
- [ ] **6 elements documentals obligatoris presents i enllaçats:**
  1. Descripció del projecte → `projecte/README.md`
  2. Diagrames Mermaid (5 diagrames) → `projecte/docs/architecture/diagrams.md`
  3. Disseny de la base de dades → `projecte/docs/architecture/data-model.md`
  4. Casos d'ús (`CU-01` a `CU-15`) → `projecte/docs/user-guide.md`
  5. Guia de contribució → `projecte/CONTRIBUTING.md`
  6. Tecnologies utilitzades → `projecte/docs/architecture/overview.md`
- [ ] **Tauler Kanban i Issues:** Les **16 Issues** (Etapes 1 a 4) estan tancades (*Closed*) i totes les targetes del tauler Kanban es troben a la columna **`Done`**.

### Pas 2: Creació de l'Etiqueta Semàntica (*Git Tag*)
Des del terminal a la branca `main` actualitzada:

```bash
git switch main
git pull origin main

# Crear l'etiqueta anotada v1.0.0
git tag -a v1.0.0 -m "Release v1.0.0: Versio final completa de TechShop E-Commerce"

# Pujar l'etiqueta al repositori remot de GitHub
git push origin v1.0.0
```

### Pas 3: Publicació Formal de la Release a GitHub
1. Aneu a la pàgina principal del vostre repositori a GitHub i feu clic a **Releases** (a la columna dreta) → **Draft a new release**.
2. En **Choose a tag**, seleccioneu `v1.0.0`.
3. En **Release title**, escriviu: `v1.0.0 · Llançament Final de TechShop (Botiga en Línia)`.
4. En la descripció, copieu el resum de canvis de `projecte/CHANGELOG.md` (funcionalitats de clients, productes/estoc, vendes/tiquets, persistència NIO i documentació completa).
5. Feu clic a **Publish release**.
