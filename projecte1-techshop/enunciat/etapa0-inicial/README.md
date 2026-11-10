# Etapa 0 (Punt de Partida) · Estat Inicial del Repositori

Aquesta carpeta conté l'**esquelet base del projecte** amb el qual comença cada equip d'alumnes durant la **Sessió 1**.

---

## Contingut del Punt de Partida

```text
projecte/
├── Main.java       # Esquelet funcional amb menú principal, mètodes d'interfície i dades demo
└── README.md       # Fitxer de presentació inicial bàsic
```

### Què ja funciona en aquest esquelet inicial?
1. **Paleta de colors ANSI i etiquetes (*badges*):** Definides al principi del fitxer i encapsulades dins dels mètodes d'interfície.
2. **Vectors paral·lels en memòria:** Estructures de dades per a clients (`clientIds`, `clientNoms`...), productes (`producteIds`, `producteNoms`...) i vendes (`vendaIds`, `vendaTotals`...).
3. **Mètodes d'interfície gràfica de terminal:** `titol`, `nota`, `ok`, `error`, `avis`, `pregunta`, `opcio`, `dada`, `capcalera`, `mostrarTargetes` i `mostrarTaula`.
4. **Panell de control principal (`main`):** Mostra el bàner de **TechShop**, les 4 targetes de resum general (KPIs) i el menú principal amb les 5 opcions.
5. **Dades de demostració (`inicialitzarDadesBase`) i mètodes auxiliars (`afegir...`, `cercarIndex...`):** Carrega 4 clients, 5 productes i 2 vendes de prova en memòria.

### Què està pendent d'implementar pels alumnes (Etapes 1 a 4)?
* **Opció `[1]` Gestió de Clients (`menuClients`):** Es completarà a l'**Etapa 1** (Tasca 1).
* **Opció `[2]` Catàleg i Estoc (`menuProductes`):** Es completarà a l'**Etapa 2** (Tasques 5 i 6).
* **Opció `[3]` Vendes i Facturació (`menuVendes`):** Es descompondrà i implementarà a l'**Etapa 3** (Tasques 9 a 12).
* **Opció `[4]` Guardar i Carregar Dades (`guardarDadesFitxer` i `carregarDadesFitxer`):** Es completarà a l'**Etapa 4** (Tasques 13 a 16).
* **Documentació tècnica completa (`docs/`, `CONTRIBUTING.md`, `CHANGELOG.md`):** S'anirà construint progressivament de l'Etapa 1 a la 4.

---

## Activitats de la Sessió 1 per a l'Equip

1. **Configurar el repositori a GitHub:**
   * Verificar que tots els membres del grup (3-5 persones) tenen permís d'escriptura com a *Collaborators*.
   * Crear el tauler Kanban a **GitHub Projects** amb les 5 columnes:
     `Backlog` | `Todo` | `In Progress` | `Review` | `Done`
2. **Clonar el repositori i provar l'esquelet inicial:**
   ```bash
   git clone <URL_DEL_REPOSITORI>
   cd <NOM_DEL_REPOSITORI>
   java projecte/Main.java
   ```
3. **Explorar el codi base (`projecte/Main.java`):**
   * Identificar com es criden els mètodes visuals (`titol`, `mostrarTaula`, `mostrarTargetes`, `opcio`, `pregunta`) sense utilitzar codis de color ANSI fora de la secció d'interfície.
   * Comprovar què passa en seleccionar les opcions `[1]` a `[4]` (mostren un avís indicant a quina etapa s'implementaran) i l'opció `[5]` (ix del programa).
