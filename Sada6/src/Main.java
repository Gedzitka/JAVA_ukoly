/**
 * Konzolová aplikace pro vyzkoušení kvadratické rovnice.
 * Sada č. 6 – úkol 2
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Kvadratická rovnice ax² + bx + c = 0 ===");
        System.out.println();

        // --- Příklad 1: dvě kořeny ---
        tiskniVypocty(1, -5, 6);   // x²  - 5x + 6 = 0  → x=2, x=3

        // --- Příklad 2: jeden kořen ---
        tiskniVypocty(1, -2, 1);   // x²  - 2x + 1 = 0  → x=1 (dvojnásobný)

        // --- Příklad 3: žádný reálný kořen ---
        tiskniVypocty(1, 0, 1);    // x²  + 1 = 0       → žádný reálný kořen

        // --- Příklad s objektem KvadratickaRovnice ---
        System.out.println("--- Použití objektu KvadratickaRovnice ---");
        KvadratickaRovnice rovnice = new KvadratickaRovnice(2, -4, -6);
        System.out.println("Rovnice: " + rovnice);
        System.out.printf("f(3)    = %.2f%n", KvadratickaRovniceVypocty.fx(rovnice, 3));
        System.out.printf("D       = %.2f%n", KvadratickaRovniceVypocty.diskriminant(rovnice));
        System.out.println("Počet kořenů: " + KvadratickaRovniceVypocty.pocetKorenu(rovnice));
        double[] k = KvadratickaRovniceVypocty.koreny(rovnice);
        System.out.printf("Kořeny: x1=%.4f, x2=%.4f%n", k[0], k[1]);

        // Změna parametru
        System.out.println();
        rovnice.setC(10);
        System.out.println("Po změně c na 10: " + rovnice);
        System.out.println("Počet kořenů: " + KvadratickaRovniceVypocty.pocetKorenu(rovnice));
    }

    /** Vytiskne kompletní výpočet pro rovnici ax² + bx + c = 0. */
    private static void tiskniVypocty(double a, double b, double c) {
        System.out.printf("--- %.0fx^2 + %.0fx + %.0f = 0 ---%n", a, b, c);
        System.out.printf("D           = %.2f%n", KvadratickaRovniceVypocty.diskriminant(a, b, c));
        System.out.printf("f(0)        = %.2f%n", KvadratickaRovniceVypocty.fx(a, b, c, 0));

        int pocet = KvadratickaRovniceVypocty.pocetKorenu(a, b, c);
        System.out.println("Počet kořenů: " + pocet);

        double[] koreny = KvadratickaRovniceVypocty.koreny(a, b, c);
        if (koreny.length == 2) {
            System.out.printf("Kořeny: x1=%.4f, x2=%.4f%n", koreny[0], koreny[1]);
        } else if (koreny.length == 1) {
            System.out.printf("Kořen:  x=%.4f%n", koreny[0]);
        } else {
            System.out.println("Žádný reálný kořen.");
        }
        System.out.println();
    }
}
