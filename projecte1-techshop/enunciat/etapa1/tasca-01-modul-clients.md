# Tasca 1 · Implementació del Mòdul de Gestió de Clients

* **Etapa:** Etapa 1 (Nivell Bàsic)
* **Tipus de tasca:** Codi font Java (copiar fragment proporcionat, enganxar-lo i adaptar-lo mínimament)
* **Branca de treball:** `feature/1-modul-clients`
* **Fitxer a modificar:** `projecte/Main.java`

---

## 1. Objectiu de la Tasca

Substituir el mètode provisional `menuClients()` de l'esquelet inicial (`projecte/Main.java`) pel mòdul complet i interactiu de **Gestió de Clients**, que permet:
1. Visualitzar la taula de tots els clients registrats amb la seua etiqueta d'estat (`ACTIU` o `BAIXA`).
2. Cercar qualsevol client per **identificador** (`101` o `#101`), **nom**, **correu electrònic** o **telèfon**.
3. Consultar la fitxa detallada d'un client i el seu historial de compres.
4. Modificar les dades d'un client (`[M]`), donar-lo de baixa lògica (`[E]`) o registrar un client nou (`[S]` quan no hi ha coincidència exacta).

---

## 2. Instruccions Pas a Pas

### Pas 1: Crea i activa la teua branca
```bash
git switch main
git pull origin main
git switch -c feature/1-modul-clients
```

### Pas 2: Localitza la secció de Clients en `projecte/Main.java`
Obre `projecte/Main.java` i busca el bloc comentat:
```java
// ----------------------------------------------------------------------------
// 1. MÒDUL DE CLIENTS (A implementar en l'Etapa 1 - Tasca 1)
// ----------------------------------------------------------------------------

void menuClients() {
    avis("Mòdul de Clients pendent d'implementar (Etapa 1 · Tasca 1).");
}
```

### Pas 3: Substitueix el bloc pel codi complet del Mòdul de Clients
Copia el fragment següent i enganxa'l en el lloc del `menuClients()` provisional:

```java
// ----------------------------------------------------------------------------
// 1. MÒDUL DE CLIENTS
// ----------------------------------------------------------------------------

void menuClients() {
    while (true) {
        capcalera(GRN, "👥  MÒDUL DE GESTIÓ DE CLIENTS");
        llistarClients("");
        nota("Total de clients registrats: " + clientCount + " / " + maxClients);

        opcio("Cercar client per ID, nom, correu o telèfon (INTRO per a tornar)");
        String entrada = scanner.nextLine().trim();
        if (entrada.isEmpty()) return;

        // Coincidència exacta amb algun camp del client
        int idx = -1;
        for (int i = 0; i < clientCount && idx == -1; i++) {
            if (entrada.equalsIgnoreCase(clientNoms[i]) || entrada.equalsIgnoreCase(clientEmails[i])
                    || entrada.equals(clientTelefons[i]) || entrada.equals("" + clientIds[i])
                    || entrada.equals("#" + clientIds[i])) {
                idx = i;
            }
        }

        if (idx != -1) {
            gestionarClient(idx);
        } else {
            titol("COINCIDÈNCIES PARCIALS AMB '" + entrada + "'");
            llistarClients(entrada);
            avis("Cap client coincideix exactament amb '" + entrada + "'.");
            pregunta("Vols donar d'alta un nou client amb aquest nom? [s/N]");
            String confirmacio = scanner.nextLine().trim().toUpperCase();
            if (confirmacio.startsWith("S")) donarAltaClient(entrada);
        }
    }
}

// Mostra els clients que contenen el text 'filtre' en algun camp ("" = tots)
void llistarClients(String filtre) {
    String[][] files = new String[clientCount][];
    int n = 0;
    for (int i = 0; i < clientCount; i++) {
        String text = "#" + clientIds[i] + " " + clientNoms[i] + " " + clientEmails[i] + " " + clientTelefons[i];
        if (text.toLowerCase().contains(filtre.toLowerCase())) {
            String estat = clientActius[i] ? BADGE_ACTIU : BADGE_BAIXA;
            files[n] = new String[]{"#" + clientIds[i], clientNoms[i], clientEmails[i], clientTelefons[i], estat};
            n++;
        }
    }
    mostrarTaula(
            new String[]{"ID", "NOM DEL CLIENT", "CORREU ELECTRÒNIC", "TELÈFON", "ESTAT"},
            new int[]{6, 24, 26, 12, 10},
            files,
            n
    );
}

void gestionarClient(int idx) {
    titol("FITXA DEL CLIENT");
    dada("ID", "#" + clientIds[idx]);
    dada("Nom", clientNoms[idx]);
    dada("Correu", clientEmails[idx]);
    dada("Telèfon", clientTelefons[idx]);
    dada("Estat", clientActius[idx] ? BADGE_ACTIU : BADGE_BAIXA);

    titol("HISTORIAL DE COMPRES DE " + clientNoms[idx].toUpperCase());
    llistarVendes(clientIds[idx], -1, "");

    opcio("Tria una acció [M: Modificar dades, E: Donar de baixa, INTRO: Tornar]");
    String accio = scanner.nextLine().trim().toUpperCase();
    switch (accio) {
        case "M" -> modificarClient(idx);
        case "E" -> eliminarClient(idx);
        case "" -> { }
        default -> avis("Acció no reconeguda. Tornant a la llista de clients.");
    }
}

void modificarClient(int idx) {
    titol("MODIFICACIÓ DEL CLIENT #" + clientIds[idx]);
    nota("Prem INTRO sense escriure res per a mantindre el valor actual");

    pregunta("Nou nom [" + clientNoms[idx] + "]");
    String nom = scanner.nextLine().trim();
    if (nom.length() >= 3) clientNoms[idx] = nom;
    else if (!nom.isEmpty()) error("El nom ha de contindre almenys 3 caràcters. Es manté l'anterior.");

    pregunta("Nou correu [" + clientEmails[idx] + "]");
    String email = scanner.nextLine().trim().toLowerCase();
    if (email.contains("@") && email.contains(".")) clientEmails[idx] = email;
    else if (!email.isEmpty()) error("Format de correu incorrecte. Es manté l'anterior.");

    pregunta("Nou telèfon [" + clientTelefons[idx] + "]");
    String telefon = scanner.nextLine().trim();
    if (!telefon.isEmpty()) clientTelefons[idx] = telefon;

    ok("Dades del client #" + clientIds[idx] + " actualitzades correctament.");
}

void eliminarClient(int idx) {
    pregunta("Confirmes que vols donar de baixa el client '" + clientNoms[idx] + "'? [s/N]");
    String confirmacio = scanner.nextLine().trim().toUpperCase();
    if (confirmacio.startsWith("S")) {
        clientActius[idx] = false;
        ok("Client #" + clientIds[idx] + " donat de baixa correctament.");
    } else {
        avis("Operació cancel·lada. No s'han fet canvis.");
    }
}

void donarAltaClient(String nom) {
    if (clientCount >= maxClients) { error("S'ha assolit la capacitat màxima de clients (" + maxClients + ")."); return; }

    titol("REGISTRE DE NOU CLIENT");
    pregunta("Nom complet del client [" + nom + "]");
    String nouNom = scanner.nextLine().trim();
    if (!nouNom.isEmpty()) nom = nouNom;
    if (nom.length() < 3) { error("El nom ha de contindre almenys 3 caràcters."); return; }

    pregunta("Correu electrònic");
    String email = scanner.nextLine().trim().toLowerCase();
    if (!email.contains("@") || !email.contains(".")) { error("Format de correu incorrecte (ha de contindre '@' i '.')."); return; }

    pregunta("Telèfon de contacte");
    String telefon = scanner.nextLine().trim();
    // ADAPTACIÓ MÍNIMA: substitueix el 0 pel càlcul de l'ID correlatiu (101 + clientCount)
    afegirClient(0, nom, email, telefon, true);
    ok("Client registrat correctament amb l'identificador #" + clientIds[clientCount - 1]);
}
```

### Pas 4: Realitza l'adaptació mínima requerida
Fixa't en l'última línia abans de l'`ok(...)` dins del mètode `donarAltaClient(String nom)`:
* Actualment passa `0` com a identificador: `afegirClient(0, nom, email, telefon, true);`
* Modifica aquest primer argument perquè assigne automàticament l'identificador correlatiu a partir de `101`:
  ```java
  afegirClient(101 + clientCount, nom, email, telefon, true);
  ```

---

## 3. Verificació i Proves

Executa aquesta prova ràpida per canonada des de l'arrel del repositori per comprovar que el mòdul llista els clients, obri la fitxa del client `101`, dona d'alta un client nou (`Elena Puig`) amb l'ID `#105` i ix sense errors:

```bash
printf "1\n101\n\nElena Puig\ns\n\nelena@correu.cat\n655443322\n\n5\n" | java projecte/Main.java
```

---

## 4. Criteris d'Acceptació (*Definition of Done*)

- [ ] Els 6 mètodes del mòdul de clients (`menuClients`, `llistarClients`, `gestionarClient`, `modificarClient`, `eliminarClient`, `donarAltaClient`) estan integrats en `projecte/Main.java`.
- [ ] El mètode `donarAltaClient` calcula l'identificador nou amb `101 + clientCount`.
- [ ] No s'ha afegit cap codi de color ANSI fora de la secció `MÈTODES D'INTERFÍCIE`.
- [ ] L'aplicació compila i passa la prova d'execució amb `java projecte/Main.java`.
- [ ] S'ha creat el *commit* descriptiu (`feat(clients): implementa el modul interactiu de gestio de clients`), s'ha pujat la branca i s'ha obert la *Pull Request* vinculada a la *Issue* #1.
