///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 25+

// ============================================================================
// PROJECTE 1: GESTIÓ D'UNA BOTIGA EN LÍNIA (E-COMMERCE)
// Curs 1r FP Informàtica - Versió Didàctica per a Alumnat Inicial
//
// Aquest fitxer utilitza "Implicitly Declared Classes and Instance Main Methods"
// (sense declaració de 'class'). Interfície gràfica de terminal amb disseny
// visual avançat basat exclusivament en codis de color ANSI i caràcters Unicode.
// Totes les dades s'emmagatzemen en vectors i matrius de tipus primitius i String.
// ============================================================================
/*
GUIDELINES:
- NO crees classes propias, todo el código debe estar en un único fichero Main.java utlizando "Implicitly Declared Classes and Instance Main Methods"
- Los métodos pueden aceptar parámetros pero no deben retornar ningun valor, solo se permite la creacion de métodos con retorno int para la busqueda de elementos de un array por su id
- Debes limitarte en la medida de lo posible a utilizar solamente estos elementos del lenguaje:
types: int, boolean, double, char, String
casting: Conversión explícita e implícita de tipos. Integer.parseInt, Double.parseDouble, Boolean.parseBoolean
arithmetic: Operadores aritméticos (+, -, *, /).
char-arithmetic: Operaciones aritméticas sobre caracteres (c - '0', etc.).
modulo: Operador residuo / módulo (%).
booleans: Valores y tipos booleanos.
logical-operators: Operadores booleanos lógicos (&&, ||, !).
relational-operators: Operadores de comparación (<, >, <=, >=, ==, !=).
ternary: Operador condicional ternario (? :).
io: Operaciones de salida estándar por consola (IO.println, System.out).
if-else: Ramificaciones condicionales if, else if, else.
switch: Sentencias y expresiones switch.
for-loop: Bucles de recorrido indexado for.
while-loop: Bucles condicionales while y do-while.
arrays: Vectores / arrays unidimensionales (int[], String[]).
matrices: Matrices bidimensionales (int[][], char[][]).
Math: Funciones de la clase Math (sqrt, pow, abs, max, min).
Random: Generación de números pseudoaleatorios. (nextInt, nextBoolean, nextDouble)
String: metodos de la clase string (length, charAt, toUpperCase, toLowerCase, trim, strip, substring, contains, startsWith, endsWith, isEmpty, replace, equals, equalsIgnoreCase, split, repeat, formatted
Scanner: nextLine, next, nextInt, nextDouble, nextBoolean,
- Haz la mínima gestión de errores posible,  si es imprescindible usar Excepciones, límitalas todo lo posible a la clase Exception
- La aplicación debe ser una demo de las posibilidades, no una aplicación funcional 100% que se deba poner en producción
- No hagas ningún test (unitario, etc)
- Para guardar datos en ficheros, utiliza solamente Files.writeString y Files.readString. No hagas ningúna gestión de ficheros, asume que los ficheros existen, etc.

 */
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Random;
import static java.lang.IO.*;

// ----------------------------------------------------------------------------
// PALETA DE COLORS ANSI (només s'utilitzen a la secció "MÈTODES D'INTERFÍCIE")
// ----------------------------------------------------------------------------
String RST = "\u001B[0m";  // RESET
String BLD = "\u001B[1m";  // BOLD
String DIM = "\u001B[2m";  // DIM
String BCK = "\u001B[30m";  // BLACK
String RED = "\u001B[91m";  // RED
String GRN = "\u001B[92m";  //GREEN
String YLW = "\u001B[93m";  // YELLOW
String BLU = "\u001B[94m";  // BLUE
String PUR = "\u001B[95m";  // PURPLE
String CYN = "\u001B[96m";  // CYAN
String WHT = "\u001B[97m";  // WHITE
String EMP = "";            // EMPTY (for alignment purposes)
String BRED = "\u001B[41m";  // BACKGROUND RED
String BGRN = "\u001B[42m";  // BACKGROUND GREEN
String BYLW = "\u001B[43m";  // BACKGROUND YELLOW

// Etiquetes d'estat (badges) que es mostren dins de les taules
String BADGE_ACTIU    = BGRN + BCK + BLD + " ACTIU " + RST;
String BADGE_BAIXA    = BRED + WHT + BLD + " BAIXA " + RST;
String BADGE_EN_ESTOC = BGRN + BCK + BLD + " EN ESTOC " + RST;
String BADGE_BAIX     = BYLW + BCK + BLD + " BAIX " + RST;
String BADGE_ESGOTAT  = BRED + WHT + BLD + " ESGOTAT " + RST;

// ----------------------------------------------------------------------------
// ESTRUCTURES DE DADES EN MEMÒRIA (VECTORS PARAL·LELS)
// ----------------------------------------------------------------------------

// 1. Clients
int maxClients = 50;
int[] clientIds = new int[maxClients];
String[] clientNoms = new String[maxClients];
String[] clientEmails = new String[maxClients];
String[] clientTelefons = new String[maxClients];
boolean[] clientActius = new boolean[maxClients];
int clientCount = 0;

// 2. Productes i estoc
int maxProductes = 50;
int[] producteIds = new int[maxProductes];
String[] producteNoms = new String[maxProductes];
String[] producteCategories = new String[maxProductes];
double[] productePreus = new double[maxProductes];
int[] producteEstoc = new int[maxProductes];
int producteCount = 0;

// 3. Vendes
int maxVendes = 50;
int[] vendaIds = new int[maxVendes];
int[] vendaClientIds = new int[maxVendes];
int[] vendaProducteIds = new int[maxVendes];
int[] vendaQuantitats = new int[maxVendes];
double[] vendaTotals = new double[maxVendes];
String[] vendaDates = new String[maxVendes];
int vendaCount = 0;

// Objectes globals
Scanner scanner = new Scanner(System.in);
Random random = new Random();
String rutaFitxerDades = "projecte/dades_botiga.txt";

// ----------------------------------------------------------------------------
// MÈTODE PRINCIPAL
// ----------------------------------------------------------------------------
void main() throws Exception {
    inicialitzarDadesBase();
    carregarDadesFitxer();
    mostrarCapcaleraPrincipal();

    while (true) {
        mostrarResumGeneral();
        mostrarMenuPrincipal();
        opcio("Tria una opció [1-5]");

        switch (scanner.nextLine().trim()) {
            case "1" -> menuClients();
            case "2" -> menuProductes();
            case "3" -> menuVendes();
            case "4" -> guardarDadesFitxer();
            case "5" -> {
                capcalera(GRN, "✔ Sessió finalitzada amb èxit! Gràcies per utilitzar la Botiga en Línia.");
                return;
            }
            default -> error("Opció no vàlida. Introdueix un número de l'1 al 5.");
        }
    }
}

void mostrarCapcaleraPrincipal() {
    println();
    println(CYN + BLD + "╭" + EMP + "─────────────────────────────────────────────────────────────────────────────" + EMP + "╮" + RST);
    println(CYN + BLD + "│" + WHT + "   ████████╗███████╗ ██████╗██╗  ██╗   ███████╗██╗  ██╗ ██████╗ ██████╗      " + CYN + "│" + RST);
    println(CYN + BLD + "│" + WHT + "   ╚══██╔══╝██╔════╝██╔════╝██║  ██║   ██╔════╝██║  ██║██╔═══██╗██╔══██╗     " + CYN + "│" + RST);
    println(CYN + BLD + "│" + CYN + "      ██║   █████╗  ██║     ███████║   ███████╗███████║██║   ██║██████╔╝     " + CYN + "│" + RST);
    println(CYN + BLD + "│" + BLU + "      ██║   ██╔══╝  ██║     ██╔══██║   ╚════██║██╔══██║██║   ██║██╔═══╝      " + CYN + "│" + RST);
    println(CYN + BLD + "│" + BLU + "      ██║   ███████╗╚██████╗██║  ██║   ███████║██║  ██║╚██████╔╝██║          " + CYN + "│" + RST);
    println(CYN + BLD + "│" + BLU + "      ╚═╝   ╚══════╝ ╚═════╝╚═╝  ╚═╝   ╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚═╝          " + CYN + "│" + RST);
    println(CYN + BLD + "│" + EMP + "                                                                             " + EMP + "│" + RST);
    println(CYN + BLD + "│" + YLW + "     🛍️   P L A T A F O R M A   D E   C O M E R Ç   E L E C T R Ò N I C       " + CYN + "│" + RST);
    println(CYN + BLD + "│" + DIM + "          Gestió de Clients · Catàleg d'Estoc · Facturació de Vendes         " + CYN + "│" + RST);
    println(CYN + BLD + "╰" + EMP + "─────────────────────────────────────────────────────────────────────────────" + EMP + "╯" + RST);
}

void mostrarResumGeneral() {
    double totalFacturat = 0;
    for (int i = 0; i < vendaCount; i++) totalFacturat += vendaTotals[i];

    int totalEstoc = 0;
    for (int i = 0; i < producteCount; i++) totalEstoc += producteEstoc[i];

    mostrarTargetes(
        new String[]{
                "👥 CLIENTS",
                "📦 ARTICLES",
                "💳 VENDES",
                "💰 FACTURAT"
        },
        new String[]{
                clientCount + " / " + maxClients,
                producteCount + " (" + totalEstoc + " un.)",
                vendaCount + " comandes",
                "%.2f €".formatted(totalFacturat)
        });
}

void mostrarMenuPrincipal() {
    String[][] opcions = {
        {"[1]", "👥  GESTIÓ DE CLIENTS",   "Directori interactiu, consulta, historial i altes"},
        {"[2]", "📦  CATÀLEG I ESTOC",     "Catàleg interactiu, consulta, reposició i altes"},
        {"[3]", "💳  VENDES I FACTURACIÓ", "Mètriques financeres, historial i comandes"},
        {"[4]", "💾  GUARDAR DADES",       "Persistència al fitxer de dades (Java NIO)"},
        {"[5]", "🚪  EIXIR DEL SISTEMA",   "Finalitzar sessió de treball"}
    };
    titol("PANELL DE CONTROL PRINCIPAL");
    mostrarTaula(new String[]{"OPC.", "OPCIÓ", "DESCRIPCIÓ DE L'ACCIÓ"}, new int[]{6, 25, 51}, opcions, opcions.length);
}

// ----------------------------------------------------------------------------
// MÈTODES D'INTERFÍCIE: són els ÚNICS que fan servir colors
// ----------------------------------------------------------------------------
void titol(String text) {
    println("\n" + YLW + BLD + "✦ " + text + " ✦" + RST);
}

void nota(String text) {
    println(DIM + "  " + text + RST);
}

void ok(String text) {
    println("\n" + BGRN + BCK + BLD + " ✔ ÈXIT " + RST + " " + GRN + text + RST);
}

void error(String text) {
    println("\n" + BRED + WHT + BLD + " ✘ ERROR " + RST + " " + RED + text + RST);
}

void avis(String text) {
    println("\n" + BYLW + BCK + BLD + " ⚠ ATENCIÓ " + RST + " " + YLW + text + RST);
}

void pregunta(String text) {
    print(CYN + "  ▪ " + text + ": " + RST);
}

void opcio(String text) {
    print("\n" + CYN + BLD + "➤ " + text + ": " + RST);
}

void dada(String etiqueta, String valor) {
    println("  " + CYN + "%-10s".formatted(etiqueta) + RST + WHT + BLD + valor + RST);
}

void capcalera(String color, String text) {
    println();
    println(color + BLD + "╭" + "─".repeat(77) + "╮" + RST);
    println(color + BLD + "│" + WHT + text + " ".repeat(Math.max(0, 77 - text.length())) + color + "│" + RST);
    println(color + BLD + "╰" + "─".repeat(77) + "╯" + RST);
}

void mostrarTargetes(String[] titols, String[] valors) {
    String[] colors = {CYN, BLU, PUR, GRN};
    String dalt = "";
    String mig = "";
    String valor = "";
    String baix = "";
    for (int i = 0; i < titols.length; i++) {
        String c = colors[i % colors.length];
        dalt  += c + "╭" + "─".repeat(20) + "╮  ";
        mig   += c + "│ " + titols[i] + " ".repeat(19 - titols[i].length()) + "│  ";
        valor += c + "│" + WHT + BLD + "%18s".formatted(valors[i]) + RST + c + "  │  ";
        baix  += c + "╰" + "─".repeat(20) + "╯  ";
    }
    println();
    println(dalt + RST);
    println(mig + RST);
    println(valor + RST);
    println(baix + RST);
}

// Taula amb vores. Només es mostren les primeres 'numFiles' files de 'files'.
void mostrarTaula(String[] capcaleres, int[] amplades, String[][] files, int numFiles) {
    if (numFiles == 0) {
        println(DIM + "  (No hi ha cap registre per mostrar)" + RST);
        return;
    }

    println();

    // Vora superior:  ╭──────┬──────╮
    print(BLU + BLD + "╭");
    for (int c = 0; c < amplades.length; c++) {
        print("─".repeat(amplades[c]) + (c < amplades.length - 1 ? "┬" : "╮"));
    }
    println(RST);

    // Capçalera:  │ ID   │ NOM  │
    print(BLU + BLD + "│");
    for (int c = 0; c < amplades.length; c++) {
        print(" " + WHT + capcaleres[c] + " ".repeat(amplades[c] - capcaleres[c].length() - 1) + BLU + "│");
    }
    println(RST);

    // Separador:  ├──────┼──────┤
    print(BLU + BLD + "├");
    for (int c = 0; c < amplades.length; c++) {
        print("─".repeat(amplades[c]) + (c < amplades.length - 1 ? "┼" : "┤"));
    }
    println(RST);

    // Files de dades: l'identificador en groc i la resta alternant blanc i cian
    for (int f = 0; f < numFiles; f++) {
        print(BLU + BLD + "│" + RST);
        for (int c = 0; c < amplades.length; c++) {
            String color = c == 0 ? YLW + BLD : (f % 2 == 0 ? WHT : CYN);
            // Llargària visible: no comptem els codis de color que porten els badges
            int visible = files[f][c].replaceAll("\u001B\\[[;\\d]*m", "").length();
            print(" " + color + files[f][c] + RST + " ".repeat(Math.max(0, amplades[c] - visible - 1)) + BLU + BLD + "│" + RST);
        }
        println();
    }

    // Vora inferior:  ╰──────┴──────╯
    print(BLU + BLD + "╰");
    for (int c = 0; c < amplades.length; c++) {
        print("─".repeat(amplades[c]) + (c < amplades.length - 1 ? "┴" : "╯"));
    }
    println(RST);
}

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
    afegirClient(101 + clientCount, nom, email, telefon, true);
    ok("Client registrat correctament amb l'identificador #" + clientIds[clientCount - 1]);
}

// ----------------------------------------------------------------------------
// 2. MÒDUL DE PRODUCTES I ESTOC
// ----------------------------------------------------------------------------

void menuProductes() {
    while (true) {
        capcalera(GRN, "📦  MÒDUL DE PRODUCTES I CONTROL D'ESTOC");
        llistarProductes("");
        nota("Total d'articles al catàleg: " + producteCount + " / " + maxProductes);

        opcio("Cercar article per ID, nom o categoria (INTRO per a tornar)");
        String entrada = scanner.nextLine().trim();
        if (entrada.isEmpty()) return;

        // Coincidència exacta pel nom o per l'identificador (1, 01 o #1)
        int idx = -1;
        for (int i = 0; i < producteCount && idx == -1; i++) {
            if (entrada.equalsIgnoreCase(producteNoms[i]) || entrada.equals("" + producteIds[i])
                    || entrada.equals("#" + producteIds[i]) || entrada.equals("%02d".formatted(producteIds[i]))) {
                idx = i;
            }
        }

        if (idx != -1) {
            gestionarProducte(idx);
        } else {
            titol("COINCIDÈNCIES PARCIALS AMB '" + entrada + "'");
            llistarProductes(entrada);
            avis("Cap article coincideix exactament amb '" + entrada + "'.");
            pregunta("Vols donar d'alta un nou article amb aquest nom? [s/N]");
            String confirmacio = scanner.nextLine().trim().toUpperCase();
            if (confirmacio.startsWith("S")) donarAltaProducte(entrada);
        }
    }
}

// Mostra els productes que contenen el text 'filtre' (ID, nom o categoria). "" = tots
void llistarProductes(String filtre) {
    String[][] files = new String[producteCount][];
    int n = 0;
    for (int i = 0; i < producteCount; i++) {
        String id = "%02d".formatted(producteIds[i]);
        String text = "#" + producteIds[i] + " " + id + " " + producteNoms[i] + " " + producteCategories[i];
        if (text.toLowerCase().contains(filtre.toLowerCase())) {
            int estoc = producteEstoc[i];
            int plens = Math.min(10, estoc / 3);   // Barra de 10 caselles (30 unitats = plena)
            String colorBarra = estoc == 0 ? RED : estoc < 10 ? YLW : GRN;
            String barra = colorBarra + "[" + "█".repeat(plens) + "░".repeat(10 - plens) + "]" + RST + " %2d un.".formatted(estoc);
            String disponibilitat = estoc > 10 ? BADGE_EN_ESTOC : estoc > 0 ? BADGE_BAIX : BADGE_ESGOTAT;
            files[n] = new String[]{id, producteNoms[i], producteCategories[i], "%8.2f €".formatted(productePreus[i]), barra, disponibilitat};
            n++;
        }
    }
    mostrarTaula(new String[]{"ID", "NOM PRODUCTE", "CATEGORIA", "PREU UNIT.", "NIVELL D'ESTOC", "DISPONIBILITAT"},
                 new int[]{4, 25, 14, 12, 21, 16}, files, n);
}

void gestionarProducte(int idx) {
    titol("FITXA DE L'ARTICLE");
    dada("ID", "#" + producteIds[idx]);
    dada("Nom", producteNoms[idx]);
    dada("Categoria", producteCategories[idx]);
    dada("Preu", "%.2f €".formatted(productePreus[idx]));
    dada("Estoc", producteEstoc[idx] + " un.");

    titol("HISTORIAL DE VENDES DE '" + producteNoms[idx].toUpperCase() + "'");
    llistarVendes(-1, producteIds[idx], "");

    opcio("Tria una acció [M: Modificar dades, R: Reposar estoc, E: Eliminar del catàleg, INTRO: Tornar]");
    String accio = scanner.nextLine().trim().toUpperCase();
    switch (accio) {
        case "M" -> modificarProducte(idx);
        case "R" -> reposarEstoc(idx);
        case "E" -> eliminarProducte(idx);
        case "" -> { }
        default -> avis("Acció no reconeguda. Tornant al catàleg de productes.");
    }
}

void modificarProducte(int idx) {
    titol("MODIFICACIÓ DE L'ARTICLE #" + producteIds[idx]);
    nota("Prem INTRO sense escriure res per a mantindre el valor actual");

    pregunta("Nou nom [" + producteNoms[idx] + "]");
    String nom = scanner.nextLine().trim();
    if (nom.length() >= 2) producteNoms[idx] = nom;
    else if (!nom.isEmpty()) error("El nom ha de contindre almenys 2 caràcters. Es manté l'anterior.");

    pregunta("Nova categoria [" + producteCategories[idx] + "]");
    String categoria = scanner.nextLine().trim();
    if (!categoria.isEmpty()) producteCategories[idx] = categoria;

    try {
        pregunta("Nou preu en € [" + "%.2f".formatted(productePreus[idx]) + "]");
        String textPreu = scanner.nextLine().trim();
        if (!textPreu.isEmpty()) {
            double preu = Double.parseDouble(textPreu);
            if (preu > 0) productePreus[idx] = preu;
            else error("El preu ha de ser superior a zero. Es manté l'anterior.");
        }

        pregunta("Nou estoc en unitats [" + producteEstoc[idx] + "]");
        String textEstoc = scanner.nextLine().trim();
        if (!textEstoc.isEmpty()) {
            int estoc = Integer.parseInt(textEstoc);
            if (estoc >= 0) producteEstoc[idx] = estoc;
            else error("L'estoc no pot ser negatiu. Es manté l'anterior.");
        }
    } catch (Exception e) {
        error("Format numèric invàlid. Es manté el valor anterior.");
    }

    ok("Dades de l'article #" + producteIds[idx] + " actualitzades correctament.");
}

void reposarEstoc(int idx) {
    pregunta("Unitats a afegir a '" + producteNoms[idx] + "' (estoc actual: " + producteEstoc[idx] + ")");
    try {
        int quantitat = Integer.parseInt(scanner.nextLine().trim());
        if (quantitat > 0) {
            producteEstoc[idx] += quantitat;
            ok("Estoc actualitzat. L'article '" + producteNoms[idx] + "' disposa ara de " + producteEstoc[idx] + " unitats.");
        } else {
            error("La quantitat ha de ser superior a zero.");
        }
    } catch (Exception e) {
        error("Format numèric invàlid.");
    }
}

void eliminarProducte(int idx) {
    int vendesProducte = 0;
    for (int i = 0; i < vendaCount; i++) {
        if (vendaProducteIds[i] == producteIds[idx]) vendesProducte++;
    }
    if (vendesProducte > 0) avis("Aquest article té " + vendesProducte + " comandes registrades a l'historial.");

    pregunta("Confirmes que vols eliminar del catàleg '" + producteNoms[idx] + "'? [s/N]");
    String confirmacio = scanner.nextLine().trim().toUpperCase();
    if (!confirmacio.startsWith("S")) { avis("Operació cancel·lada. No s'han fet canvis."); return; }

    ok("Producte #" + producteIds[idx] + " ('" + producteNoms[idx] + "') eliminat correctament del catàleg.");
    // Desplacem una posició cap a l'esquerra tots els productes que hi ha darrere
    for (int i = idx; i < producteCount - 1; i++) {
        producteIds[i] = producteIds[i + 1];
        producteNoms[i] = producteNoms[i + 1];
        producteCategories[i] = producteCategories[i + 1];
        productePreus[i] = productePreus[i + 1];
        producteEstoc[i] = producteEstoc[i + 1];
    }
    producteCount--;
}

void donarAltaProducte(String nom) {
    if (producteCount >= maxProductes) { error("S'ha assolit la capacitat màxima d'articles (" + maxProductes + ")."); return; }

    titol("REGISTRE DE NOU PRODUCTE");
    pregunta("Nom complet del producte [" + nom + "]");
    String nouNom = scanner.nextLine().trim();
    if (!nouNom.isEmpty()) nom = nouNom;
    if (nom.length() < 2) { error("El nom ha de contindre almenys 2 caràcters."); return; }

    pregunta("Categoria comercial (ex: Ordinadors, Audio, Accessoris)");
    String categoria = scanner.nextLine().trim();
    if (categoria.isEmpty()) categoria = "General";

    try {
        pregunta("Preu unitari en € (ex: 29.99)");
        double preu = Double.parseDouble(scanner.nextLine().trim());
        if (preu <= 0) { error("El preu ha de ser superior a zero."); return; }

        pregunta("Estoc inicial en unitats (ex: 10)");
        int estoc = Integer.parseInt(scanner.nextLine().trim());
        if (estoc < 0) { error("L'estoc no pot ser negatiu."); return; }

        // El nou identificador és el més alt dels existents + 1
        int nouId = 0;
        for (int i = 0; i < producteCount; i++) nouId = Math.max(nouId, producteIds[i]);
        afegirProducte(nouId + 1, nom, categoria, preu, estoc);
        ok("Producte registrat correctament al catàleg amb l'identificador #" + (nouId + 1));
    } catch (Exception e) {
        error("Format numèric invàlid.");
    }
}

// ----------------------------------------------------------------------------
// 3. MÒDUL DE VENDES
// ----------------------------------------------------------------------------

void menuVendes() {
    while (true) {
        capcalera(GRN,"💳  MÒDUL DE VENDES I FACTURACIÓ");
        mostrarEstadistiquesVendes();
        titol("HISTORIAL COMPLET DE VENDES");
        llistarVendes(-1, -1, "");

        opcio("Tria una acció [B: Cercar venda, R: Registrar nova comanda, INTRO: Tornar]");
        String accio = scanner.nextLine().trim();
        switch (accio.toUpperCase()) {
            case "" -> { return; }
            case "R" -> registrarVenda();
            case "B" -> {
                opcio("Cercar per ID de venda, client, article o data");
                String terme = scanner.nextLine().trim();
                cercarVendes(terme);
            }
            default -> cercarVendes(accio);
        }
    }
}

void mostrarEstadistiquesVendes() {
    if (vendaCount == 0) { avis("Encara no hi ha cap venda registrada."); return; }

    double suma = 0;
    double maxim = vendaTotals[0];
    double minim = vendaTotals[0];
    for (int i = 0; i < vendaCount; i++) {
        suma += vendaTotals[i];
        maxim = Math.max(maxim, vendaTotals[i]);
        minim = Math.min(minim, vendaTotals[i]);
    }

    mostrarTargetes(
        new String[]{
                "💳 TOTAL FACTURAT",
                "📈 MITJANA/VENDA",
                "🏆 VENDA MÀXIMA",
                "📉 VENDA MÍNIMA"
        },
        new String[]{
                "%.2f €".formatted(suma),
                "%.2f €".formatted(suma / vendaCount),
                "%.2f €".formatted(maxim),
                "%.2f €".formatted(minim)
        });
}

// Mostra les vendes filtrades per client i/o producte (-1 = qualsevol) i per un text ("" = tot)
void llistarVendes(int clientId, int producteId, String filtre) {
    String[][] files = new String[vendaCount][];
    int n = 0;
    int unitats = 0;
    double total = 0;

    for (int i = 0; i < vendaCount; i++) {
        int c = cercarIndexClientPerId(vendaClientIds[i]);
        int p = cercarIndexProductePerId(vendaProducteIds[i]);
        String client = c != -1 ? clientNoms[c] : "#" + vendaClientIds[i];
        String article = p != -1 ? producteNoms[p] : "#" + vendaProducteIds[i];
        String text = "#" + vendaIds[i] + " " + vendaClientIds[i] + " " + client + " "
                    + vendaProducteIds[i] + " " + article + " " + vendaDates[i];

        boolean mostrar = (clientId == -1 || vendaClientIds[i] == clientId)
                       && (producteId == -1 || vendaProducteIds[i] == producteId)
                       && text.toLowerCase().contains(filtre.toLowerCase());
        if (mostrar) {
            files[n] = new String[]{"#" + vendaIds[i], client, article, vendaQuantitats[i] + " un.",
                                    vendaDates[i], "%9.2f €".formatted(vendaTotals[i])};
            n++;
            unitats += vendaQuantitats[i];
            total += vendaTotals[i];
        }
    }

    mostrarTaula(new String[]{"ID VENDA", "CLIENT", "ARTICLE", "QUANTITAT", "DATA", "TOTAL LIQ."},
                 new int[]{10, 20, 22, 11, 12, 13}, files, n);
    if (n > 0) nota(n + " comandes · " + unitats + " unitats · Facturació: " + "%.2f €".formatted(total));
}

void cercarVendes(String terme) {
    if (terme.isEmpty()) return;
    titol("RESULTATS DE LA CERCA DE VENDES: '" + terme + "'");
    llistarVendes(-1, -1, terme);
    opcio("Prem INTRO per a continuar");
    scanner.nextLine();
}

void registrarVenda() {
    if (vendaCount >= maxVendes) { error("Límit màxim de vendes assolit."); return; }

    llistarClients("");
    pregunta("ID del client comprador");
    int idClient = Integer.parseInt(scanner.nextLine().trim());
    int c = cercarIndexClientPerId(idClient);
    if (c == -1) { error("Client #" + idClient + " inexistent."); return; }

    llistarProductes("");
    pregunta("ID del producte a comprar");
    int idProducte = Integer.parseInt(scanner.nextLine().trim());
    int p = cercarIndexProductePerId(idProducte);
    if (p == -1) { error("Producte #" + idProducte + " no trobat."); return; }

    pregunta("Unitats a comprar");
    int quantitat = Integer.parseInt(scanner.nextLine().trim());
    if (quantitat <= 0) { error("La quantitat ha de ser com a mínim 1 unitat."); return; }
    if (quantitat > producteEstoc[p]) { error("Estoc insuficient! Només en queden " + producteEstoc[p] + " unitats."); return; }

    producteEstoc[p] -= quantitat;
    afegirVenda(1001 + vendaCount, clientIds[c], producteIds[p], quantitat, productePreus[p] * quantitat, "2026-10-22");
    imprimirTiquet(vendaCount - 1, c, p);
    nota("[Informació interna: Estoc restant de " + producteNoms[p] + ": " + producteEstoc[p] + " un.]");
}

// Imprimeix el tiquet de la venda 'v', feta pel client 'c' i del producte 'p' (posicions dins dels vectors)
void imprimirTiquet(int v, int c, int p) {
    String codi = "TIQ-" + vendaIds[v] + "-" + (random.nextInt(9000) + 1000);
    String vora = "─".repeat(66);
    // Cada línia: etiqueta a l'esquerra (30 caràcters) i valor alineat a la dreta (32 caràcters)
    String linia = WHT + BLD + "│  " + RST + CYN + "%-30s" + WHT + BLD + "%32s  │" + RST;

    println();
    println(WHT + BLD + "╭" + vora + "╮");
    println("│             🧾  TIQUET DE VENDA - FACTURACIÓ OFICIAL             │");
    println("│            TECH-SHOP · PLATAFORMA DE COMERÇ ELECTRÒNIC           │");
    println("├" + vora + "┤" + RST);
    println(linia.formatted("Identificador de venda:", "#" + vendaIds[v]));
    println(linia.formatted("Codi de transacció:", codi));
    println(linia.formatted("Data i hora d'emissió:", vendaDates[v] + " · Sessió activa"));
    println(linia.formatted("Client comprador:", clientNoms[c] + " (ID #" + clientIds[c] + ")"));
    println(WHT + BLD + "├" + vora + "┤" + RST);
    println(linia.formatted("Article adquirit:", producteNoms[p]));
    println(linia.formatted("Preu unitari:", "%.2f €".formatted(productePreus[p])));
    println(linia.formatted("Quantitat:", vendaQuantitats[v] + " unitats"));
    println(WHT + BLD + "├" + vora + "┤" + RST);
    println(linia.formatted("TOTAL LIQUIDAT (IVA inclòs):", "%.2f €".formatted(vendaTotals[v])));
    println(WHT + BLD + "╰" + vora + "╯" + RST);
    println(DIM + "                  Gràcies per la vostra compra!" + RST);
}

// ----------------------------------------------------------------------------
// CERCA D'ELEMENTS PER IDENTIFICADOR (retornen la posició o -1 si no existeix)
// ----------------------------------------------------------------------------

int cercarIndexClientPerId(int id) {
    for (int i = 0; i < clientCount; i++) {
        if (clientIds[i] == id) return i;
    }
    return -1;
}

int cercarIndexProductePerId(int id) {
    for (int i = 0; i < producteCount; i++) {
        if (producteIds[i] == id) return i;
    }
    return -1;
}

// ----------------------------------------------------------------------------
// ALTA D'ELEMENTS ALS VECTORS (s'afegeixen sempre al final)
// ----------------------------------------------------------------------------

void afegirClient(int id, String nom, String email, String telefon, boolean actiu) {
    clientIds[clientCount] = id;
    clientNoms[clientCount] = nom;
    clientEmails[clientCount] = email;
    clientTelefons[clientCount] = telefon;
    clientActius[clientCount] = actiu;
    clientCount++;
}

void afegirProducte(int id, String nom, String categoria, double preu, int estoc) {
    producteIds[producteCount] = id;
    producteNoms[producteCount] = nom;
    producteCategories[producteCount] = categoria;
    productePreus[producteCount] = preu;
    producteEstoc[producteCount] = estoc;
    producteCount++;
}

void afegirVenda(int id, int clientId, int producteId, int quantitat, double total, String data) {
    vendaIds[vendaCount] = id;
    vendaClientIds[vendaCount] = clientId;
    vendaProducteIds[vendaCount] = producteId;
    vendaQuantitats[vendaCount] = quantitat;
    vendaTotals[vendaCount] = total;
    vendaDates[vendaCount] = data;
    vendaCount++;
}

// ----------------------------------------------------------------------------
// PERSISTÈNCIA EN FITXERS AMB Files.writeString I Files.readString
// ----------------------------------------------------------------------------

void guardarDadesFitxer() throws Exception {
    String contingut = "# DADES DE LA BOTIGA EN LINIA\n[CLIENTS]\n";
    for (int i = 0; i < clientCount; i++) {
        contingut += clientIds[i] + ";" + clientNoms[i] + ";" + clientEmails[i] + ";" + clientTelefons[i] + ";" + clientActius[i] + "\n";
    }
    contingut += "[PRODUCTES]\n";
    for (int i = 0; i < producteCount; i++) {
        contingut += producteIds[i] + ";" + producteNoms[i] + ";" + producteCategories[i] + ";" + productePreus[i] + ";" + producteEstoc[i] + "\n";
    }
    contingut += "[VENDES]\n";
    for (int i = 0; i < vendaCount; i++) {
        contingut += vendaIds[i] + ";" + vendaClientIds[i] + ";" + vendaProducteIds[i] + ";" + vendaQuantitats[i] + ";" + vendaTotals[i] + ";" + vendaDates[i] + "\n";
    }

    Files.writeString(Path.of(rutaFitxerDades), contingut);
    ok("Totes les dades s'han desat correctament al fitxer físic: " + rutaFitxerDades);
}

void carregarDadesFitxer() {
    try {
        String[] linies = Files.readString(Path.of(rutaFitxerDades)).split("\n");
        String seccio = "";

        for (int i = 0; i < linies.length; i++) {
            String linia = linies[i].trim();
            if (linia.startsWith("[")) {
                // Si el fitxer conté la secció, substitueix les dades de demostració
                seccio = linia;
                if (seccio.equals("[CLIENTS]")) clientCount = 0;
                if (seccio.equals("[PRODUCTES]")) producteCount = 0;
                if (seccio.equals("[VENDES]")) vendaCount = 0;
            } else if (!linia.isEmpty() && !linia.startsWith("#")) {
                String[] c = linia.split(";");
                switch (seccio) {
                    case "[CLIENTS]" -> afegirClient(Integer.parseInt(c[0]), c[1], c[2], c[3], Boolean.parseBoolean(c[4]));
                    case "[PRODUCTES]" -> afegirProducte(Integer.parseInt(c[0]), c[1], c[2], Double.parseDouble(c[3]), Integer.parseInt(c[4]));
                    case "[VENDES]" -> afegirVenda(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]),
                                                   Integer.parseInt(c[3]), Double.parseDouble(c[4]), c[5]);
                }
            }
        }
    } catch (Exception e) {
        // Si el fitxer no existeix, es mantenen les dades de demostració
    }
}

// ----------------------------------------------------------------------------
// DADES DE DEMOSTRACIÓ
// ----------------------------------------------------------------------------

void inicialitzarDadesBase() {
    afegirClient(101, "Anna Garcia", "anna@correu.cat", "611223344", true);
    afegirClient(102, "Bernat Lopez", "bernat@correu.cat", "622334455", true);
    afegirClient(103, "Carla Vidal", "carla@correu.cat", "633445566", true);
    afegirClient(104, "David Mas", "david@correu.cat", "644556677", true);

    afegirProducte(1, "Portatil 15 polzades", "Ordinadors", 749.99, 10);
    afegirProducte(2, "Ratoli sense fils", "Accessoris", 19.95, 25);
    afegirProducte(3, "Teclat mecanic", "Accessoris", 49.50, 15);
    afegirProducte(4, "Monitor 24 4K", "Monitors", 189.00, 8);
    afegirProducte(5, "Auriculars Bluetooth", "Audio", 39.90, 30);

    afegirVenda(1001, 101, 1, 1, 749.99, "2026-10-15");
    afegirVenda(1002, 103, 3, 1, 49.50, "2026-10-20");
}
