/**
 * Konzolová aplikace pro vyzkoušení hierarchie tříd barev.
 * Sada č. 4a
 *
 * Objektový model:
 *   Barva -> BarvaRGB  -> BarvaRGB1 (průměr složek)
 *                      -> BarvaRGB2 (luminance)
 *         -> BarvaCMYK
 *
 * Odpovědi na otázky ze zadání:
 * - Pokud by šlo hodnotu barvy MĚNIT: atributy r, g, b jsou protected + public settery (stávající řešení).
 * - Pokud by hodnotu barvy NEŠLO měnit: stačí atributy označit jako final a settery odstranit.
 */
public class Main {

    public static void main(String[] args) {

        // Vytvoření objektů
        BarvaRGB1 rgb1 = new BarvaRGB1(200, 100, 50);
        BarvaRGB2 rgb2 = new BarvaRGB2(200, 100, 50);
        BarvaCMYK cmyk = new BarvaCMYK(0.22, 0.61, 0.83, 0.0);

        System.out.println("=== Barvy ===");
        System.out.println("RGB1 stav:     " + rgb1.getStav());
        System.out.println("RGB1 hex:      " + rgb1.getStavHex());
        System.out.printf ("RGB1 intenz.:  %.2f (průměr)%n", rgb1.getIntenzita());

        System.out.println();
        System.out.println("RGB2 stav:     " + rgb2.getStav());
        System.out.println("RGB2 hex:      " + rgb2.getStavHex());
        System.out.printf ("RGB2 intenz.:  %.2f (luminance)%n", rgb2.getIntenzita());

        System.out.println();
        System.out.println("CMYK stav:     " + cmyk.getStav());
        System.out.printf ("CMYK intenz.:  %.2f%n", cmyk.getIntenzita());

        // Změna hodnoty barvy
        System.out.println();
        System.out.println("--- Změna barvy RGB1 (setR, setG, setB) ---");
        rgb1.setR(255);
        rgb1.setG(0);
        rgb1.setB(0);
        System.out.println("Po změně: " + rgb1.getStav() + " = " + rgb1.getStavHex());
        System.out.printf("Intenzita po změně: %.2f%n", rgb1.getIntenzita());

        // Přetypování na rodičovskou třídu Barva
        System.out.println();
        System.out.println("--- Přetypování objektů na třídu Barva ---");
        Barva[] barvy = { rgb1, rgb2, cmyk };
        for (Barva b : barvy) {
            System.out.printf("%-12s  intenzita=%.2f  stav=%s%n",
                    b.getClass().getSimpleName(), b.getIntenzita(), b.getStav());
        }
    }
}
