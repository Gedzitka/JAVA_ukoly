import java.util.ArrayList;

/**
 * Nástroj (tool) pro práci s historií pohybu na šachovnici.
 *
 * Poskytuje dvě hlavní metody:
 *   - spoctiVzdalenost: geometrická (euklidovská) vzdálenost celé cesty
 *   - sestavVyslednouCestu: textová reprezentace cesty
 *
 * Každá metoda existuje ve dvou variantách:
 *   a) klasické pole Souradnice[]      – omezení: pevná velikost pole
 *   b) ArrayList<Souradnice>           – dynamicky roste, žádné omezení velikosti
 *
 * Odpověď na otázku ze zadání:
 *   Omezení klasického pole: musí se předem znát (nebo odhadnout) maximální počet prvků.
 *   ArrayList toto omezení nemá – roste automaticky dle potřeby.
 */
public class ToolHistoriePohybu {

    // Třída je čistě statická – zabraňujeme instanciaci
    private ToolHistoriePohybu() {
    }

    // =========================================================================
    // a) Varianta s klasickým polem
    // =========================================================================

    /**
     * Spočítá celkovou geometrickou vzdálenost (součet euklidovských vzdáleností
     * mezi po sobě jdoucími body) pro historii pohybu jako klasické pole.
     *
     * Vzdálenost mezi body [x1;y1] a [x2;y2] = sqrt((x2-x1)² + (y2-y1)²)
     *
     * @param historie pole souřadnic historie pohybu
     * @return celková vzdálenost; 0.0 pro prázdné nebo jednoprvkové pole
     */
    public static double spoctiVzdalenost(Souradnice[] historie) {
        if (historie == null || historie.length < 2) {
            return 0.0;
        }
        double celkova = 0.0;
        for (int i = 1; i < historie.length; i++) {
            celkova += vzdalenostDvouBodu(historie[i - 1], historie[i]);
        }
        return celkova;
    }

    /**
     * Sestaví textovou reprezentaci celé cesty z klasického pole.
     * Formát: [0;0] -> [0;1] -> [1;1]
     *
     * @param historie pole souřadnic historie pohybu
     * @return textová cesta; prázdný řetězec pro null nebo prázdné pole
     */
    public static String sestavVyslednouCestu(Souradnice[] historie) {
        if (historie == null || historie.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < historie.length; i++) {
            if (i > 0) {
                sb.append(" -> ");
            }
            sb.append(historie[i]);
        }
        return sb.toString();
    }

    // =========================================================================
    // b) Varianta s ArrayList
    // =========================================================================

    /**
     * Spočítá celkovou geometrickou vzdálenost pro historii pohybu jako ArrayList.
     *
     * @param historie ArrayList souřadnic historie pohybu
     * @return celková vzdálenost; 0.0 pro prázdný nebo jednoprvkový seznam
     */
    public static double spoctiVzdalenost(ArrayList<Souradnice> historie) {
        if (historie == null || historie.size() < 2) {
            return 0.0;
        }
        double celkova = 0.0;
        for (int i = 1; i < historie.size(); i++) {
            celkova += vzdalenostDvouBodu(historie.get(i - 1), historie.get(i));
        }
        return celkova;
    }

    /**
     * Sestaví textovou reprezentaci celé cesty z ArrayList.
     *
     * @param historie ArrayList souřadnic historie pohybu
     * @return textová cesta; prázdný řetězec pro null nebo prázdný seznam
     */
    public static String sestavVyslednouCestu(ArrayList<Souradnice> historie) {
        if (historie == null || historie.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < historie.size(); i++) {
            if (i > 0) {
                sb.append(" -> ");
            }
            sb.append(historie.get(i));
        }
        return sb.toString();
    }

    // =========================================================================
    // Privátní pomocná metoda
    // =========================================================================

    /** Euklidovská vzdálenost mezi dvěma body. */
    private static double vzdalenostDvouBodu(Souradnice a, Souradnice b) {
        int dx = b.getX() - a.getX();
        int dy = b.getY() - a.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
