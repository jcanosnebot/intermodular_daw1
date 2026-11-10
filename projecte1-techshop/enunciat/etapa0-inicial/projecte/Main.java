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

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Random;
import static java.lang.IO.*;

// ----------------------------------------------------------------------------
// PALETA DE COLORS ANSI (només s'utilitzen a la secció "MÈTODES D'INTERFÍCIE")
// ----------------------------------------------------------------------------
String RST = "\u001B[0m";   // RESET
String BLD = "\u001B[1m";   // BOLD
String DIM = "\u001B[2m";   // DIM
String BCK = "\u001B[30m";  // BLACK
String RED = "\u001B[91m";  // RED
String GRN = "\u001B[92m";  // GREEN
String YLW = "\u001B[93m";  // YELLOW
String BLU = "\u001B[94m";  // BLUE
String PUR = "\u001B[95m";  // PURPLE
String CYN = "\u001B[96m";  // CYAN
String WHT = "\u001B[97m";  // WHITE
String EMP = "";            // EMPTY (for alignment purposes)
String BRED = "\u001B[41m"; // BACKGROUND RED
String BGRN = "\u001B[42m"; // BACKGROUND GREEN
String BYLW = "\u001B[43m"; // BACKGROUND YELLOW

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
// 1. MÒDUL DE CLIENTS (A implementar en l'Etapa 1 - Tasca 1)
// ----------------------------------------------------------------------------

void menuClients() {
    avis("Mòdul de Clients pendent d'implementar (Etapa 1 · Tasca 1).");
}

// ----------------------------------------------------------------------------
// 2. MÒDUL DE PRODUCTES I ESTOC (A implementar en l'Etapa 2 - Tasques 5 i 6)
// ----------------------------------------------------------------------------

void menuProductes() {
    avis("Mòdul de Productes i Estoc pendent d'implementar (Etapa 2 · Tasques 5 i 6).");
}

// ----------------------------------------------------------------------------
// 3. MÒDUL DE VENDES (A implementar en l'Etapa 3 - Tasques 9 a 12)
// ----------------------------------------------------------------------------

void menuVendes() {
    avis("Mòdul de Vendes i Facturació pendent d'implementar (Etapa 3 · Tasques 9 a 12).");
}

void llistarVendes(int clientId, int producteId, String filtre) {
    nota("(Llistat d'historial de vendes disponible a partir de l'Etapa 3)");
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
// PERSISTÈNCIA EN FITXERS (A implementar en l'Etapa 4 - Tasques 13 a 16)
// ----------------------------------------------------------------------------

void guardarDadesFitxer() throws Exception {
    avis("Persistència en fitxer pendent d'implementar (Etapa 4).");
}

void carregarDadesFitxer() {
    // S'implementarà a l'Etapa 4 amb Files.readString
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
