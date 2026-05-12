import java.util.ArrayList;

/**
 * Konzolová aplikace pro vyzkoušení tříd Souradnice a ToolHistoriePohybu.
 * Sada č. 7 – rozšíření úkolu Šachovnice
 *
 * Porovnání klasického pole vs. ArrayList:
 *   - klasické pole: pevná velikost, nutno znát max. počet předem, rychlý přístup přes index
 *   - ArrayList: dynamická velikost, pohodlnější přidávání, minimálně paměťová režie navíc
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Sada 7 – Historie pohybu na šachovnici ===");
        System.out.println();

        // -------------------------------------------------------------------------
        // a) Klasické pole
        // -------------------------------------------------------------------------
        System.out.println("--- a) Klasické pole ---");

        Souradnice[] pole = {
            new Souradnice(0, 0),
            new Souradnice(0, 1),
            new Souradnice(0, 2),
            new Souradnice(1, 2),
            new Souradnice(2, 2)
        };

        System.out.println("Cesta:      " + ToolHistoriePohybu.sestavVyslednouCestu(pole));
        System.out.printf("Vzdálenost: %.4f%n%n", ToolHistoriePohybu.spoctiVzdalenost(pole));

        // Omezení klasického pole – komentář:
        // Pokud bychom nevěděli předem, jak dlouhá cesta bude, musíme pole předem naalokovat
        // s odhadem velikosti nebo průběžně kopírovat do většího (Arrays.copyOf). To je
        // nepohodlné a náchylné k chybám. ArrayList toto řeší automaticky.

        // -------------------------------------------------------------------------
        // b) ArrayList
        // -------------------------------------------------------------------------
        System.out.println("--- b) ArrayList ---");

        ArrayList<Souradnice> seznam = new ArrayList<>();
        seznam.add(new Souradnice(0, 0));
        seznam.add(new Souradnice(0, 1));
        seznam.add(new Souradnice(0, 2));
        seznam.add(new Souradnice(1, 2));
        seznam.add(new Souradnice(2, 2));
        // ArrayList umožňuje kdykoli přidat další bod bez přealokace
        seznam.add(new Souradnice(3, 2));

        System.out.println("Cesta:      " + ToolHistoriePohybu.sestavVyslednouCestu(seznam));
        System.out.printf("Vzdálenost: %.4f%n%n", ToolHistoriePohybu.spoctiVzdalenost(seznam));

        // -------------------------------------------------------------------------
        // Pohyb přes úhlopříčku (sqrt(2))
        // -------------------------------------------------------------------------
        System.out.println("--- Vzdálenost přes úhlopříčku ([0;0] -> [1;1]) ---");
        ArrayList<Souradnice> uhlopricky = new ArrayList<>();
        uhlopricky.add(new Souradnice(0, 0));
        uhlopricky.add(new Souradnice(1, 1));
        System.out.printf("Vzdálenost: %.4f (očekáváno: %.4f)%n",
                ToolHistoriePohybu.spoctiVzdalenost(uhlopricky),
                Math.sqrt(2));
    }
}
