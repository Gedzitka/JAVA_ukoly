/**
 * DATOVÁ VRSTVA
 *
 * Třída reprezentující jednu položku pokladního dokladu (nákupní položku).
 *
 * Ukládá: název, množství, cena bez DPH za kus, sazba DPH v %.
 * Vypočítá: celkovou cenu bez DPH, výši DPH, celkovou cenu s DPH.
 */
public class PolozkaDokladu {

    private final String nazev;
    private final int mnozstvi;
    private final double cenaBezDphZaKus;  // cena za 1 kus bez DPH
    private final double sazbaDph;         // sazba DPH v %, např. 21.0

    /**
     * Vytvoří novou položku dokladu.
     *
     * @param nazev           název zboží
     * @param mnozstvi        počet kusů
     * @param cenaBezDphZaKus jednotková cena bez DPH
     * @param sazbaDph        sazba DPH v procentech (např. 21.0 pro 21 %)
     */
    public PolozkaDokladu(String nazev, int mnozstvi, double cenaBezDphZaKus, double sazbaDph) {
        if (nazev == null || nazev.trim().isEmpty()) {
            throw new IllegalArgumentException("Název položky nesmí být prázdný.");
        }
        if (mnozstvi <= 0) {
            throw new IllegalArgumentException("Množství musí být kladné.");
        }
        if (cenaBezDphZaKus < 0) {
            throw new IllegalArgumentException("Cena bez DPH nesmí být záporná.");
        }
        if (sazbaDph < 0) {
            throw new IllegalArgumentException("Sazba DPH nesmí být záporná.");
        }

        this.nazev            = nazev.trim();
        this.mnozstvi         = mnozstvi;
        this.cenaBezDphZaKus  = cenaBezDphZaKus;
        this.sazbaDph         = sazbaDph;
    }

    // -------------------------------------------------------------------------
    // Gettery
    // -------------------------------------------------------------------------

    public String getNazev()            { return nazev; }
    public int getMnozstvi()            { return mnozstvi; }
    public double getCenaBezDphZaKus()  { return cenaBezDphZaKus; }
    public double getSazbaDph()         { return sazbaDph; }

    // -------------------------------------------------------------------------
    // Výpočty
    // -------------------------------------------------------------------------

    /** Celková cena bez DPH = množství × cena za kus bez DPH */
    public double getCelkovaCenaBezDph() {
        return mnozstvi * cenaBezDphZaKus;
    }

    /** Výše DPH pro celkovou cenu */
    public double getCastkaVDph() {
        return getCelkovaCenaBezDph() * sazbaDph / 100.0;
    }

    /** Celková cena s DPH */
    public double getCelkovaCenaSDph() {
        return getCelkovaCenaBezDph() + getCastkaVDph();
    }

    // -------------------------------------------------------------------------
    // Textová reprezentace položky pro výpis na dokladu
    // -------------------------------------------------------------------------

    /**
     * Vrátí naformátovaný řádek položky pro výpis na dokladu.
     * Formát:
     *   Název                        2 ks × 49,90 Kč
     *     Bez DPH:  99,80 Kč   DPH 21%: 20,96 Kč   S DPH: 120,76 Kč
     */
    public String formatujRadek() {
        return String.format("%-30s %3d ks x %8.2f Kc%n"
                           + "  Bez DPH: %8.2f Kc   DPH %4.0f%%: %7.2f Kc   S DPH: %8.2f Kc",
                nazev, mnozstvi, cenaBezDphZaKus,
                getCelkovaCenaBezDph(), sazbaDph, getCastkaVDph(), getCelkovaCenaSDph());
    }

    @Override
    public String toString() {
        return formatujRadek();
    }
}
