# Etapa 3 · Descomposició i Planificació d'una Tasca Complexa (Tasques 9 a 12)

* **Sessió associada:** Sessió 7 (2 hores)
* **Nivell de dificultat:** Mitjà-Alt (planificació autònoma en equip)
* **Objectiu principal:** Desenvolupar la capacitat d'anàlisi i organització descomponint una especificació funcional gran —el **Mòdul Integral de Vendes, Estadístiques i Emissió de Tiquets**— en **4 subtasques xicotetes, atòmiques i independents (Tasques 9, 10, 11 i 12)**, gestionant tot el seu cicle de vida al tauler Kanban de GitHub.

---

## 1. Metodologia de Treball en l'Etapa 3

A diferència de les Etapes 1 i 2 (on el professorat ja us donava les 4 tasques separades en fitxers individuals), en l'Etapa 3 rebeu un **únic document d'especificació global**:
* 📄 [`especificacio-modul-vendes.md`](especificacio-modul-vendes.md)

### Què ha de fer l'equip abans de programar?

1. **Llegir i analitzar conjuntament l'especificació:**
   Reuniu-vos tot l'equip durant els primers 15-20 minuts i identifiqueu totes les peces que cal construir (mètodes Java en `Main.java`, documentació d'API en `docs/api/sales.md`, casos d'ús `CU-11`..`CU-13` en `docs/user-guide.md` i diagrama de seqüència en `docs/architecture/diagrams.md`).
2. **Dividir el treball en 4 subtasques equilibrades (Tasques 9 a 12):**
   Cada subtasca ha de tindre un objectiu clar, un responsable assignat, una branca pròpia i uns criteris d'acceptació verificables.
3. **Crear el Milestone `Etapa 3` i registrar les 4 Issues a GitHub:**
   Creeu les *Issues* #9, #10, #11 i #12 al repositori, assigneu-les als membres de l'equip amb les seues etiquetes (`enhancement`, `documentation`) i situeu-les a la columna `Todo` del tauler Kanban.

---

## 2. Proposta Orientativa de Descomposició en 4 Subtasques

Tot i que l'equip té autonomia per a organitzar la divisió, us recomanem aquesta partició equilibrada perquè tots els membres puguen treballar sense bloquejar-se mútuament:

| Subtasca | Títol recomanat per a la Issue | Branca sugerida | Contingut de la subtasca |
|---|---|---|---|
| **Tasca 9** | Menú de Vendes, Estadístiques KPI (`Math`) i Llistat/Cerca d'Historial | `feature/9-estadistiques-llistat-vendes` | Implementar `menuVendes()`, `mostrarEstadistiquesVendes()`, `llistarVendes(...)` i `cercarVendes(...)` en `projecte/Main.java`. |
| **Tasca 10** | Registre de Nova Comanda, Validació d'Estoc i Emissió de Tiquet (`Random`) | `feature/10-registre-venda-tiquet` | Implementar `registrarVenda()` i `imprimirTiquet(int v, int c, int p)` en `projecte/Main.java`. |
| **Tasca 11** | Documentació Tècnica de l'API del Mòdul de Vendes | `docs/11-api-vendes` | Crear `projecte/docs/api/sales.md` documentant els vectors i els 6 mètodes del mòdul de vendes i actualitzar `docs/api/README.md`. |
| **Tasca 12** | Casos d'Ús de Vendes (`CU-11` a `CU-13`) i Diagrama de Seqüència Mermaid | `docs/12-casos-us-sequencia-venda` | Completar `projecte/docs/user-guide.md` amb l'ús del mòdul de vendes i afegir el `sequenceDiagram` del procés de venda en `projecte/docs/architecture/diagrams.md`. |

> **Consell de coordinació entre les Tasques 9 i 10:**
> Com que tant la Tasca 9 com la Tasca 10 afegeixen mètodes dins de `// 3. MÒDUL DE VENDES` en `projecte/Main.java`:
> * El responsable de la **Tasca 9** pot deixar un mètode buit provisional `void registrarVenda() {}` perquè la seua branca compile de forma independent.
> * Quan es fusione la Tasca 9, el responsable de la **Tasca 10** fa `git pull origin main` i substitueix el mètode provisional per la implementació completa de `registrarVenda()` i `imprimirTiquet(...)`.

---

## 3. Traçabilitat Exigida en l'Etapa 3

Per a cadascuna de les Tasques 9, 10, 11 i 12 ha de poder comprovar-se a GitHub la cadena completa:

```text
Especificació (Requisit)
       ↓
Issue creada per l'alumne (#9..#12) amb Criteris d'Acceptació i Milestone 'Etapa 3'
       ↓
Targeta al Kanban (Todo → In Progress → Review → Done)
       ↓
Branca temàtica (feature/... o docs/...)
       ↓
Commits atòmics i descriptius
       ↓
Pull Request vinculada ('Closes #9') + Revisió d'un company (Approve) + Merge a main
```

---

## 4. Verificació Final de l'Etapa 3

- [ ] L'equip ha creat per si mateix les *Issues* #9, #10, #11 i #12 ben documentades al tauler Kanban.
- [ ] L'opció `[3] VENDES I FACTURACIÓ` mostra les 4 targetes d'estadístiques (`TOTAL FACTURAT`, `MITJANA/VENDA`, `VENDA MÀXIMA`, `VENDA MÍNIMA`), la taula d'historial, la cerca (`[B]`) i el registre de nova comanda (`[R]`) amb descompte d'estoc i tiquet oficial.
- [ ] A més, dins de la fitxa de qualsevol client (`[1]`) o producte (`[2]`), ja es mostra automàticament la taula amb el seu historial de vendes gràcies a `llistarVendes(...)`!
- [ ] Existeix `projecte/docs/api/sales.md`, `user-guide.md` inclou `CU-11` a `CU-13` i `diagrams.md` inclou el diagrama de seqüència.
