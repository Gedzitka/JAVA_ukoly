import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * LOGICKÁ VRSTVA
 *
 * Třída reprezentující pokladní doklad (účtenku).
 * Spravuje seznam nákupních položek, zaznamenává datum a čas nákupu
 * a umí vygenerovat textový pokladní doklad.
 *
 * Metody:
 *   pridejPolozku(nazev, mnozstvi, cenaBezDph, sazbaDph)
 *   generujPokladniDoklad() : String
 */
public class PokladniDoklad {

    private static final int SIRKA_DOKLADU = 54;
    private static final DateTimeFormatter FORMAT_DATUM_CAS =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private final List<PolozkaDokladu> polozky;
    private final LocalDateTime datumCas;

    /**
     * Vytvoří nový prázdný pokladní doklad s aktuálním datem a časem.
     */
    public PokladniDoklad() {
        this.polozky  = new ArrayList<>();
        this.datumCas = LocalDateTime.now();
    }

    // -------------------------------------------------------------------------
    // Přidání položky
    // -------------------------------------------------------------------------

    /**
     * Přidá nákupní položku do dokladu.
     *
     * @param nazev           název zboží
     * @param mnozstvi        počet kusů
     * @param cenaBezDphZaKus cena za kus bez DPH
     * @param sazbaDph        sazba DPH v % (např. 21.0)
     */
    public void pridejPolozku(String nazev, int mnozstvi,
                               double cenaBezDphZaKus, double sazbaDph) {
        polozky.add(new PolozkaDokladu(nazev, mnozstvi, cenaBezDphZaKus, sazbaDph));
    }

    // -------------------------------------------------------------------------
    // Součty
    // -------------------------------------------------------------------------

    /** Celková cena všech položek bez DPH. */
    public double getCelkemBezDph() {
        double soucet = 0.0;
        for (PolozkaDokladu p : polozky) {
            soucet += p.getCelkovaCenaBezDph();
        }
        return soucet;
    }

    /** Celková výše DPH za všechny položky. */
    public double getCelkemDph() {
        double soucet = 0.0;
        for (PolozkaDokladu p : polozky) {
            soucet += p.getCastkaVDph();
        }
        return soucet;
    }

    /** Celková cena všech položek s DPH. */
    public double getCelkemSDph() {
        return getCelkemBezDph() + getCelkemDph();
    }

    // -------------------------------------------------------------------------
    // Generování dokladu
    // -------------------------------------------------------------------------

    /**
     * Sestaví a vrátí kompletní textový pokladní doklad jako jeden řetězec.
     *
     * @return text pokladního dokladu
     */
    public String generujPokladniDoklad() {
        StringBuilder sb = new StringBuilder();

        // Záhlaví
        sb.append(oddelovac('='));
        sb.append(stredovat("POKLADNI DOKLAD")).append("\n");
        sb.append(oddelovac('='));

        // Datum a čas
        sb.append(String.format("Datum a cas: %s%n", datumCas.format(FORMAT_DATUM_CAS)));
        sb.append(oddelovac('-'));

        // Položky
        if (polozky.isEmpty()) {
            sb.append("  (zadne polozky)\n");
        } else {
            for (int i = 0; i < polozky.size(); i++) {
                sb.append(String.format("%d. %s%n", i + 1, polozky.get(i).formatujRadek()));
            }
        }

        // Součty
        sb.append(oddelovac('='));
        sb.append(String.format("CELKEM bez DPH: %34.2f Kc%n", getCelkemBezDph()));
        sb.append(String.format("CELKEM DPH:     %34.2f Kc%n", getCelkemDph()));
        sb.append(oddelovac('-'));
        sb.append(String.format("CELKEM S DPH:   %34.2f Kc%n", getCelkemSDph()));
        sb.append(oddelovac('='));
        sb.append(stredovat("Dekujeme za Vas nakup!")).append("\n");
        sb.append(oddelovac('='));

        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // Pomocné metody pro formátování
    // -------------------------------------------------------------------------

    private String oddelovac(char znak) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIRKA_DOKLADU; i++) {
            sb.append(znak);
        }
        sb.append("\n");
        return sb.toString();
    }

    private String stredovat(String text) {
        int mezery = (SIRKA_DOKLADU - text.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mezery; i++) {
            sb.append(' ');
        }
        sb.append(text);
        return sb.toString();
    }
}
