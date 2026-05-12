/**
 * Třída s metodami pro parsování textových údajů.
 * Sada č. 8
 */
public class ParseUtils {

    // Třída je čistě statická – zabraňujeme instanciaci
    private ParseUtils() {
    }

    // =========================================================================
    // Úkol 1a – parseJmenoPrijmeni1
    // Předpoklad: jméno a příjmení oddělené jednou mezerou
    // =========================================================================

    /**
     * Zjistí jméno a příjmení z řetězce odděleného jednou mezerou a vypíše je v apostrofech.
     *
     * @param jmenoPrijmeni textový údaj ve formátu "Jméno Příjmení"
     */
    public static void parseJmenoPrijmeni1(String jmenoPrijmeni) {
        String[] casti = jmenoPrijmeni.split(" ");
        String jmeno    = casti[0];
        String prijmeni = casti[1];
        System.out.println("Jméno:    '" + jmeno + "'");
        System.out.println("Příjmení: '" + prijmeni + "'");
    }

    // =========================================================================
    // Úkol 1b – parseJmenoPrijmeni2
    // Předpoklad: jméno a příjmení oddělené libovolným počtem mezer,
    // možné mezery i na začátku a na konci
    // =========================================================================

    /**
     * Zjistí jméno a příjmení z řetězce s libovolným počtem mezer (včetně krajních) a vypíše je.
     * Správně pracuje i pro formát z úkolu 1a (jedna mezera).
     *
     * @param jmenoPrijmeni textový údaj
     */
    public static void parseJmenoPrijmeni2(String jmenoPrijmeni) {
        // trim() odstraní krajní mezery, split("\\s+") rozdělí na libovolném počtu bílých znaků
        String[] casti  = jmenoPrijmeni.trim().split("\\s+");
        String jmeno    = casti[0];
        String prijmeni = casti[1];
        System.out.println("Jméno:    '" + jmeno + "'");
        System.out.println("Příjmení: '" + prijmeni + "'");
    }

    // =========================================================================
    // Úkol 1c – parseJmenoPrijmeniVrat
    // Jako 1b, ale vrátí výsledky (nejen vypíše)
    // Návratový typ: String[] o dvou prvcích – [0]=jméno, [1]=příjmení
    // =========================================================================

    /**
     * Zjistí jméno a příjmení a vrátí je jako pole řetězců.
     *
     * @param jmenoPrijmeni textový údaj
     * @return pole String[] kde [0] = jméno, [1] = příjmení
     */
    public static String[] parseJmenoPrijmeniVrat(String jmenoPrijmeni) {
        String[] casti = jmenoPrijmeni.trim().split("\\s+");
        return new String[]{ casti[0], casti[1] };
    }

    // =========================================================================
    // Úkol 1d – parseLogin
    // Loginy oddělené středníkem, vrátí klasické pole
    // =========================================================================

    /**
     * Rozdělí textový údaj s loginy oddělenými středníkem a vrátí je jako klasické pole.
     *
     * @param loginy textový údaj, např. "jan;petra;tomas"
     * @return pole loginů
     */
    public static String[] parseLogin(String loginy) {
        String[] pole = loginy.split(";");
        // Oříznutí případných mezer okolo jednotlivých loginů
        for (int i = 0; i < pole.length; i++) {
            pole[i] = pole[i].trim();
        }
        return pole;
    }

    // =========================================================================
    // Úkol 1e1 – parseCislaInt
    // Celá čísla oddělená středníkem
    // =========================================================================

    /**
     * Převede textový údaj s celými čísly oddělenými středníkem na pole int[].
     *
     * @param cisla textový údaj, např. "1;2;3;42"
     * @return pole celých čísel
     * @throws NumberFormatException pokud některá hodnota není celé číslo
     */
    public static int[] parseCislaInt(String cisla) {
        String[] casti = cisla.split(";");
        int[] pole = new int[casti.length];
        for (int i = 0; i < casti.length; i++) {
            pole[i] = Integer.parseInt(casti[i].trim());
        }
        return pole;
    }

    // =========================================================================
    // Úkol 1e2 – parseCislaDouble
    // Reálná čísla oddělená středníkem
    // =========================================================================

    /**
     * Převede textový údaj s reálnými čísly oddělenými středníkem na pole double[].
     *
     * @param cisla textový údaj, např. "1.5;2.75;3.0"
     * @return pole reálných čísel
     * @throws NumberFormatException pokud některá hodnota není reálné číslo
     */
    public static double[] parseCislaDouble(String cisla) {
        String[] casti = cisla.split(";");
        double[] pole = new double[casti.length];
        for (int i = 0; i < casti.length; i++) {
            pole[i] = Double.parseDouble(casti[i].trim());
        }
        return pole;
    }
}
