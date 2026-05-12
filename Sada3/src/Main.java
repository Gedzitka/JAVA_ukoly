/**
 * Konzolová aplikace pro vyzkoušení třídy KomplexniCislo.
 * Sada č. 3
 */
public class Main {

    public static void main(String[] args) {

        KomplexniCislo k1 = new KomplexniCislo(3, 2);   // 3 + 2i
        KomplexniCislo k2 = new KomplexniCislo(1, -4);  // 1 - 4i

        System.out.println("=== Komplexní čísla ===");
        System.out.println("k1 = " + k1);
        System.out.println("k2 = " + k2);
        System.out.println();

        // Sčítání
        System.out.println("--- Sčítání ---");
        System.out.println("k1 + k2 = " + k1.plus(k2));
        System.out.println("k1 + 5  = " + k1.plus(5));

        // Odčítání
        System.out.println();
        System.out.println("--- Odčítání ---");
        System.out.println("k1 - k2 = " + k1.minus(k2));
        System.out.println("k1 - 5  = " + k1.minus(5));

        // Násobení
        System.out.println();
        System.out.println("--- Násobení ---");
        System.out.println("k1 * k2 = " + k1.krat(k2));
        System.out.println("k1 * 2  = " + k1.krat(2));

        // Absolutní hodnota
        System.out.println();
        System.out.println("--- Absolutní hodnota ---");
        System.out.printf("|k1| = %.4f%n", k1.absolutniHodnota());
        System.out.printf("|k2| = %.4f%n", k2.absolutniHodnota());

        // Řetězení operací
        System.out.println();
        System.out.println("--- Řetězení: (k1 + k2) * k1 ---");
        KomplexniCislo vysledek = k1.plus(k2).krat(k1);
        System.out.println("Výsledek = " + vysledek);
    }
}
