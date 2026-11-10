# 📚 Referència de Mètodes · `Main.java`

Aquesta secció documenta tots els mètodes de [`Main.java`](../../Main.java), agrupats per mòdul. És la referència per a qui ha de **modificar o ampliar el codi**. Per a saber com s'utilitza l'aplicació, consulta el [manual d'usuari](../user-guide.md).

> [!NOTE]
> Com que el fitxer és una *classe implícita*, els mètodes no s'escriuen dins d'un `class Main { ... }`. Són mètodes d'instància que es criden pel nom (`llistarClients("")`) i comparteixen les variables globals del fitxer: els vectors, `scanner`, `random` i els colors.

---

## 📑 Mòduls

| Document | Mètodes |
| :--- | :--- |
| 🎨 [Interfície](ui.md) | `main`, `mostrarCapcaleraPrincipal`, `mostrarResumGeneral`, `mostrarMenuPrincipal`, `titol`, `nota`, `ok`, `error`, `avis`, `pregunta`, `opcio`, `dada`, `capcalera`, `mostrarTargetes`, `mostrarTaula` |
| 👥 [Clients](customers.md) | `menuClients`, `llistarClients`, `gestionarClient`, `modificarClient`, `eliminarClient`, `donarAltaClient` |
| 📦 [Productes i estoc](products.md) | `menuProductes`, `llistarProductes`, `gestionarProducte`, `modificarProducte`, `reposarEstoc`, `eliminarProducte`, `donarAltaProducte` |
| 💳 [Vendes](sales.md) | `menuVendes`, `mostrarEstadistiquesVendes`, `llistarVendes`, `cercarVendes`, `registrarVenda`, `imprimirTiquet` |
| 🗄️ [Dades i persistència](data.md) | `cercarIndexClientPerId`, `cercarIndexProductePerId`, `afegirClient`, `afegirProducte`, `afegirVenda`, `guardarDadesFitxer`, `carregarDadesFitxer`, `inicialitzarDadesBase` |

---

## 📐 Convencions del codi

| Convenció | Detall |
| :--- | :--- |
| **Valor de retorn** | Tots els mètodes són `void`, excepte `cercarIndexClientPerId` i `cercarIndexProductePerId`, que retornen la posició (`int`) o `-1`. |
| **Paràmetre `idx`** | És la **posició** dins dels vectors, no l'identificador. Per exemple, el client `#101` és a la posició `0`. |
| **Lectura del teclat** | Sempre amb dues línies: primer `pregunta(...)` o `opcio(...)` per a mostrar l'enunciat, i després `scanner.nextLine().trim()` en una variable local. |
| **Colors** | Només els mètodes d'[interfície](ui.md) i les constants `BADGE_...` fan servir codis ANSI. |
| **Noms** | En valencià i en *camelCase* (`producteEstoc`, `registrarVenda`). Els colors tenen noms curts en majúscules (`CYN`, `RST`). |

---

## 📄 Format de cada fitxa de mètode

Cada mètode es documenta amb:

1. **Signatura:** tal com apareix al codi.
2. **Què fa:** el propòsit del mètode.
3. **Paràmetres:** si en té.
4. **Comportament:** passos, validacions i casos límit.
5. **Dades:** quins vectors o variables llig o modifica.
